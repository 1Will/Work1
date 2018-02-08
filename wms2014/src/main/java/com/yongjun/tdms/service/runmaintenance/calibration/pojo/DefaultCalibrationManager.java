/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.runmaintenance.calibration.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import com.yongjun.pluto.exception.CustomizeRuntimeException;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDao;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDetailDao;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDetailViewDao;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetailView;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationResultType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationManager;

/**
 * <p>Title: DefaultCalibrationManager
 * <p>Description: 工装标定业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:  $
 */
public class DefaultCalibrationManager implements CalibrationManager{
	private final CalibrationDao calibrationDao;
	private final SequenceManager sequenceManager;
	private final CalibrationDetailDao calibrationDetailDao;
	private final CalibrationDetailViewDao calibrationDetailViewDao;
	
	public DefaultCalibrationManager(CalibrationDao calibrationDao,
			SequenceManager sequenceManager,
			CalibrationDetailDao calibrationDetailDao,
			CalibrationDetailViewDao calibrationDetailViewDao){
		this.calibrationDao = calibrationDao;
		this.sequenceManager = sequenceManager;
		this.calibrationDetailDao = calibrationDetailDao;
		this.calibrationDetailViewDao = calibrationDetailViewDao;
	}
	
	public List<Calibration> loadAllCalibrations(Long[] calibrationIds) {
		return this.calibrationDao.loadAllCalibrations(calibrationIds);
	}

	public void deleteAllCalibrations(Collection<Calibration> calibrations) throws Exception {
		for (Calibration calibration : calibrations){
			for (CalibrationDetail detail : calibration.getCalibrationPlanDetails()){
				if (detail.getResult().equals(CalibrationResultType.FINISHED)){
					throw new Exception();
				}
			}
		}
		for (Calibration calibration : calibrations){
			this.calibrationDetailDao.deleteAllcalibrationDetails(calibration.getCalibrationPlanDetails());
			for(Calibration proc : calibration.getCalibrationProc()){
				this.calibrationDao.deleteCalibrations(proc);
			}
		}
		this.calibrationDao.deleteAllCalibrations(calibrations);
	}

	public void storeCalibration(Calibration calibration) {
		if(calibration.isNew()) {
				String planNo = (String)sequenceManager.generate("-");
				calibration.setPlanNo(planNo);
				this.calibrationDao.storeCalibration(calibration);
				copyCalibration(calibration);
			}else {
				updatePlanToProc(calibration);
				this.calibrationDao.storeCalibration(calibration);
			}
	}

	public Calibration loadCalibration(Long calibrationId) {
		return this.calibrationDao.loadCalibration(calibrationId);
	}
	
	private void copyCalibration(Calibration plan){
		Calibration proc = new Calibration();
		proc.setPlanProcFlag(PreRepairModel.PROC);                 //设置实施标识 
		copyPlanToProc(plan,proc);
		this.calibrationDao.storeCalibration(proc);
	}
	
	private void copyPlanToProc(Calibration plan,Calibration proc){
		proc.setCalibrationPlan(plan);
		proc.setPlanNo(plan.getPlanNo());
		proc.setPlanName(plan.getPlanName());
		proc.setDepartment(plan.getDepartment());
		proc.setWriter(plan.getWriter());
		proc.setApprover(plan.getApprover());
		proc.setVerifyPeople(plan.getVerifyPeople());
		proc.setMakeDate(plan.getMakeDate());
		proc.setMonth(plan.getMonth());
	}
	private void updatePlanToProc(Calibration plan) {
		for (Calibration proc : plan.getCalibrationProc()) {
			copyPlanToProc(plan,proc);
			this.calibrationDao.storeCalibration(proc);
		}
	}

	public void deleteCalibrations(Calibration calibration) {
		this.calibrationDao.deleteCalibrations(calibration);
	}

	public List<CalibrationDetailView> loadAllCalibrationDetailView(String[] array) throws HibernateException {
		return this.calibrationDetailViewDao.loadAllCalibrationDetailView(array);
		
	}
	/**
	 * 失效
	 * @throws Exception 
	 */
	public void disabledAllCalibration(List<Calibration> calibrations) throws Exception {
		ifCalibrationDetailResult(calibrations);
		for(Calibration calibration : calibrations){
			//获得该标定计划下的标定实施的记录
			Set<Calibration> calibrationProcs = calibration.getCalibrationProc();
			if(calibrationProcs.size()>0){
				//失效标定计划的时候同时把标定实施也给失效
				for(Calibration calibrationProc : calibrationProcs){
					calibrationProc.setDisabled(true);
					calibrationDao.storeCalibration(calibrationProc);
				}
			}
			calibration.setDisabled(true);
			calibrationDao.storeCalibration(calibration);
		}
	}
	
	private void ifCalibrationDetailResult(List<Calibration> calibrations) throws Exception {
		for(Calibration cbr : calibrations){
			Set<CalibrationDetail> cbrDtls = cbr.getCalibrationPlanDetails();
			if(cbrDtls.size()>0){
				for(CalibrationDetail cbrDtl : cbrDtls){
					if(cbrDtl.getResult().equals(CalibrationResultType.FINISHED)){
						throw new CustomizeRuntimeException(cbrDtl.getPlan().getPlanNo());
					}
				}
			}
		}
	}

	/**
	 * 有效
	 */
	public void enabledAllCalibration(List<Calibration> calibrations) {
		for(Calibration calibration : calibrations){
			//获得该标定计划下的标定实施的记录
			Set<Calibration> calibrationProcs = calibration.getCalibrationProc();
			if(calibrationProcs.size()>0){
				//有效标定计划的时候同时把标定实施也给有效
				for(Calibration calibrationProc : calibrationProcs){
					calibrationProc.setDisabled(false);
					calibrationDao.storeCalibration(calibrationProc);
				}	
			}
			calibration.setDisabled(false);
			calibrationDao.storeCalibration(calibration);
		}
	}
	
}
