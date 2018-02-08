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

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointProcDao;
import com.yongjun.tdms.model.base.docrefstatus.DocReferenceStatus;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcManager;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: DefaultCheckPointProcManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class DefaultCheckPointProcManager implements CheckPointProcManager{
	private final CheckPointProcDao checkPointProcDao;
	private final CheckPointPlanManager checkPointPlanManager;
	private final CheckPointProcDetailManager checkPointProcDetailManager; 
	private final SequenceManager sequenceManager;
	private final WfJobManager wfJobManager;
	
	public DefaultCheckPointProcManager(CheckPointProcDao checkPointProcDao,
			CheckPointPlanManager checkPointPlanManager,
			CheckPointProcDetailManager checkPointProcDetailManager,
			SequenceManager sequenceManager,
			WfJobManager wfJobManager){
		this.checkPointProcDao=checkPointProcDao;
		this.checkPointPlanManager=checkPointPlanManager;
		this.checkPointProcDetailManager=checkPointProcDetailManager;
		this.sequenceManager  = sequenceManager;
		this.wfJobManager=wfJobManager;
	}

	public void storeCheckPointProc(CheckPointProc proc,CheckPointPlan plan){
		boolean isNew = proc.isNew();
		CheckPointPlan m_beforePlan=new CheckPointPlan();
		m_beforePlan=proc.getPlan();
		if(isNew){
			setProc(plan,proc);
			proc.setManager(plan.getManager());
			checkPointProcDetailManager.loadAllCheckPointProcDetail(plan,proc);
			checkPointPlanManager.updateCheckPointPlanStatus(plan, DocReferenceStatus.REFERENCED);
		}
		else{
			if(m_beforePlan!=plan){
				updateProc(proc);
				setProc(plan,proc);
				//更改新的计划的状态
				checkPointPlanManager.updateCheckPointPlanStatus(plan, DocReferenceStatus.REFERENCED);
				//更改老的计划的状态
				checkPointPlanManager.updateCheckPointPlanStatus(m_beforePlan, DocReferenceStatus.UN_REFERENCE);	
				checkPointProcDetailManager.loadAllCheckPointProcDetail(plan,proc);
			}
			else{
				setProc(plan,proc);
			}
		}
	}
	
	public double getPlanExpenseCollection(CheckPointProc proc){
		return checkPointProcDetailManager.getPlanExpenseCollection(proc);
	}
	
	public double getActualExpenseCollection(CheckPointProc proc){
		return checkPointProcDetailManager.getActualExpenseCollection(proc);
	}
	
	//TODO: this function contain store statement.!
	public void setProc(CheckPointPlan plan,CheckPointProc proc){
		proc.setPlan(plan);
		proc.setDevice(plan.getDevice());
		if (proc.isNew()) {
			String procNo = (String)sequenceManager.generate("-");
			proc.setProcNo(procNo);
		}
		storeProc(proc);
	}
	
	private void updateProc(CheckPointProc proc) {
		proc.getProcDetails().clear();
		storeProc(proc);
	}
	
	private void storeProc(CheckPointProc proc){
		checkPointProcDao.storeCheckPointProc(proc);
	}
	
	public CheckPointProc loadCheckPointProc(Long checkPointProcIds){
		return checkPointProcDao.loadCheckPointProc(checkPointProcIds);
	}
	
	public void deleteCheckPointProc(CheckPointProc checkPointProc) throws WFJobInProcException{
		if (checkPointProc.getJob() != null) {
			throw new WFJobInProcException();
		}
		
		List<CheckPointProc> aryList = new ArrayList<CheckPointProc>();
		aryList.add(checkPointProc);
		updateReferencePlan(aryList);
		
		checkPointProcDao.deleteCheckPointProc(checkPointProc);
	}
	
	public void deleteAllCheckPointProc(List<CheckPointProc> checkPointProcs) throws WFJobInProcException{
		for (CheckPointProc proc : checkPointProcs) {
			if (proc.getJob() != null) {
				throw new WFJobInProcException();
			}
		}
		updateReferencePlan(checkPointProcs);
		checkPointProcDao.deleteAllCheckPointProc(checkPointProcs);
	}
	
	private void updateReferencePlan(List<CheckPointProc> procs) {
		CheckPointPlan plan = new CheckPointPlan();
		for (int i = 0; i < procs.size(); i++) {
			plan = procs.get(i).getPlan();
			plan.setDocStatus(DocReferenceStatus.UN_REFERENCE);
			checkPointPlanManager.storePlan(plan);
		}
	}
	
	public List<CheckPointProc> loadAllCheckPointProc(Long [] checkPointProcIds){
		return checkPointProcDao.loadAllCheckPointProc(checkPointProcIds);
	}
	
	public void cancelJob(CheckPointProc proc) {
		proc.setJob(null);
		storeProc(proc);
		this.wfJobManager.cancelJob(proc);
	}
}
