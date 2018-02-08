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
package com.yongjun.tdms.workflow.service.approver.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.dao.security.UserDao;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.workflow.dao.approver.JobApproverDao;
import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfJobApproverManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * @author qs
 * @version $Id: DefaultWfJobApproverManager.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class DefaultWfJobApproverManager extends BaseManager implements
		WfJobApproverManager {
	private final UserDao userDao;
	private final WfDocStateManager wfDocStateManager;
	private final JobApproverDao jobApproverDao;
	
	public DefaultWfJobApproverManager(UserDao userDao,
			WfDocStateManager wfDocStateManager,
			JobApproverDao jobApproverDao) {
		this.userDao = userDao;
		this.wfDocStateManager = wfDocStateManager;
		this.jobApproverDao=jobApproverDao;
	}

	public List<JobApprover> createJobApprovers(Long[] approvers, Long finalApprovers) {
		List<JobApprover> approverList = new ArrayList<JobApprover>();
		
		if (null != approvers) {
			for (Long userId : approvers) {
				JobApprover ja = this.createJobApprover(userId, false);
				approverList.add(ja);
			}
		}
		
		JobApprover ja = this.createJobApprover(finalApprovers, true);
		approverList.add(ja);
		
		return approverList;
	}
	
	private JobApprover createJobApprover(Long userId, boolean isFinalApprover) {
		User user = userDao.loadUser(userId);
		JobApprover jobApprover = new JobApprover();
		jobApprover.setApprover(user);
		jobApprover.setFinalApr(isFinalApprover);
		DocState ds = wfDocStateManager.loadDocStateByCode("DOC_APPROVING");
		jobApprover.setDocState(ds);
		return jobApprover;
	}
   
	public void store(JobApprover jobApprover){
		jobApproverDao.storeJobApprover(jobApprover);
	}
	
	public JobApprover loadJobApprover(Long id){
		return jobApproverDao.loadJobApprover(id);
	}
	
	public void updateJobApprover(Job job, DocState docState, User user) {
		for (JobApprover jobApprover : job.getApprovers()) {
			if (jobApprover.getApprover().equals(user)) {
				logger.debug("doc state changed! from " + jobApprover.getDocState().getCode() + ", to " + docState.getCode());
				jobApprover.setDocState(docState);
				//job.setApprovers(jobApprover);
			}
		}
	}
}
