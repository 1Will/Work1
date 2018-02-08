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
package com.yongjun.tdms.service.runmaintenance.checkpoint.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointProcDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcDetail;
import com.yongjun.tdms.model.runmaintenance.checkresult.CheckResult;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcDetailManager;

/**
 * @author qs
 * @version $Id: DefaultCheckPointProcDetailManager.java 8881 2007-12-02 03:05:28Z qsun $
 */
public class DefaultCheckPointProcDetailManager implements CheckPointProcDetailManager{
	
	private final CheckPointProcDetailDao checkPointProcDetailDao;
	
	public DefaultCheckPointProcDetailManager(CheckPointProcDetailDao checkPointProcDetailDao){
		this.checkPointProcDetailDao=checkPointProcDetailDao;
	}

	public void storeCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail,CheckPointProc proc){
		checkPointProcDetail.setProc(proc);
		checkPointProcDetailDao.storeCheckPointProcDetail(checkPointProcDetail);
	}

	public void loadAllCheckPointProcDetail(CheckPointPlan plan,CheckPointProc proc){
	    //planDetail的集合
		Set<CheckPointPlanDetail> planSet=plan.getPlanDetails();
		CheckPointProcDetail procDetail;
		Object[] planDetail=planSet.toArray();
		for(int i=0;i<planDetail.length;i++){
			procDetail=new CheckPointProcDetail();
			procDetail.setPlanDetail((CheckPointPlanDetail)planDetail[i]);
			procDetail.setProc(proc);
			procDetail.setCheckResult(CheckResult.NORMAL);
			procDetail.setFee(((CheckPointPlanDetail)planDetail[i]).getFee());
			procDetail.setExceptionDescription("");
			checkPointProcDetailDao.storeCheckPointProcDetail(procDetail);
		}	
	}
	
	public double getPlanExpenseCollection(CheckPointProc proc){
		double planFee=0;
		Set<CheckPointProcDetail> procSet=proc.getProcDetails();
		CheckPointProcDetail procDetail;
		Object[] procDetailArray=procSet.toArray();
		for(int i=0;i<procDetailArray.length;i++){
			procDetail=new CheckPointProcDetail();
			procDetail=(CheckPointProcDetail)procDetailArray[i];
			planFee+=procDetail.getPlanDetail().getFee();
		}	
		return planFee;
	}
	
	public double getActualExpenseCollection(CheckPointProc proc){
		double actualFee=0;
		Set<CheckPointProcDetail> procSet=proc.getProcDetails();
		CheckPointProcDetail procDetail;
		Object[] procDetailArray=procSet.toArray();
		for(int i=0;i<procDetailArray.length;i++){
			procDetail=new CheckPointProcDetail();
			procDetail=(CheckPointProcDetail)procDetailArray[i];
			actualFee+=procDetail.getFee();
		}
		return actualFee;
	}
	
	public CheckPointProcDetail loadCheckPointProcDetail(Long checkPointProcDetailIds){
		return checkPointProcDetailDao.loadCheckPointProcDetail(checkPointProcDetailIds);
	}
	
	public void deleteCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail){
		checkPointProcDetailDao.deleteCheckPointProcDetail(checkPointProcDetail);
	}
	
	public void deleteAllCheckPointProcDetail(Collection<CheckPointProcDetail> checkPointProcDetailIds){
		checkPointProcDetailDao.deleteAllCheckPointProcDetail(checkPointProcDetailIds);
	}
	
	public List<CheckPointProcDetail> loadAllCheckPointProcDetail(Long [] checkPointProcDetailIds){
		return checkPointProcDetailDao.loadAllCheckPointProcDetail(checkPointProcDetailIds);
	}
}
