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
package com.yongjun.tdms.service.runmaintenance.wash.pojo;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.wash.WashDao;
import com.yongjun.tdms.dao.runmaintenance.wash.WashPlanViewDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;
import com.yongjun.tdms.model.runmaintenance.wash.WashPlanView;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;
/**
 * <p>Title: DefaultWashManager
 * <p>Description: 清洗业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.runmaintenance.wash.WashManage
 */
public class DefaultWashManager implements WashManager {
	private final WashDao washDao;
	private final SequenceManager sequenceManager;
	private final WashPlanViewDao washPlanviewDao;
	
	public DefaultWashManager(WashDao washDao, SequenceManager sequenceManager,WashPlanViewDao washPlanviewDao) {
		this.washDao = washDao;
		this.sequenceManager = sequenceManager;
		this.washPlanviewDao = washPlanviewDao;
	}
	
	public Wash loadWash(Long washId) {
		return this.washDao.loadWash(washId);
	}

	public List<Wash> loadAllWashs(Long[] washIds) {
		return this.washDao.loadAllWashs(washIds);
	}

	public List<Wash> loadAllWashs() {
		return this.washDao.loadAllWashs();
	}

	public void storeWash(Wash wash) {
		if (wash.isNew()) {              //新建清洗计划
			String planNo = (String)sequenceManager.generate("-");
			wash.setPlanNo(planNo);
			this.washDao.storeWash(wash);
			copyPlanToProc(wash);
		} else {                       //更新清洗计划
			updatePlanToProc(wash);
			this.washDao.storeWash(wash);
		}
	}

	public void deleteWash(Wash wash) {
		this.washDao.deleteWash(wash);
	}

	public void deleteAllWashs(Collection<Wash> washs){
		this.washDao.deleteAllWashs(washs);
	}
	
	/**
	 * 拷贝清洗计划，生成清洗实施
	 * @param washPlan 清洗计划
	 */
	private void copyPlanToProc(Wash washPlan) {
		Wash washProc = new Wash();
		copyPlanContentToProc(washPlan, washProc);
		washProc.setPlanProcFlag(PreRepairModel.PROC);
		this.washDao.storeWash(washProc);
	}
	
	/**
	 * 拷贝清洗计划的内容到清洗实施中
	 * @param washPlan 清洗计划
	 * @param washProc 清洗实施
	 */
	private void copyPlanContentToProc(Wash washPlan, Wash washProc) {
		washProc.setPlanNo(washPlan.getPlanNo());
		washProc.setName(washPlan.getName());
		washProc.setDepartment(washPlan.getDepartment());
		washProc.setPlanBeginDate(washPlan.getPlanBeginDate());
		washProc.setPlanCreatedTime(washPlan.getPlanCreatedTime());
		washProc.setPlanCreator(washPlan.getPlanCreator());
		washProc.setPlanAuditor(washPlan.getPlanAuditor());
		washProc.setWashPlan(washPlan);
	}
	
	/**
	 * 更新清洗计划内容到清洗实施中
	 * @param washPlan 清洗计划
	 */
	private void updatePlanToProc(Wash washPlan) {
		for (Wash washProc : washPlan.getWashProc()) {
			copyPlanContentToProc(washPlan, washProc);
			this.washDao.storeWash(washProc);
		}
	}

	public void deleteAllWashPlans(Collection<Wash> washPlans) throws Exception{
		for (Wash wash : washPlans) {
			if (null != wash.getPlanDetails()) {
				for (WashDetail planDetail : wash.getPlanDetails()) {
					if (planDetail.getWashResult().equals(WashDetailResult.FINISHED)) {
						throw new Exception();
					}
				}
			}
		}
		this.washDao.deleteAllWashs(washPlans);
	}
	public List<WashPlanView> loadAllWashPlanView(String[] array) throws HibernateException {
		return this.washPlanviewDao.loadAllWashPlanView(array);
		
	}
}
