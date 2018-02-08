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
package com.yongjun.tdms.workflow.service.job.pojo;

import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.workflow.WorkFlowUtil;
import com.yongjun.tdms.workflow.dao.approver.JobApproverDao;
import com.yongjun.tdms.workflow.dao.job.JobDao;
import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.doctype.DocType;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfJobApproverManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;
import com.yongjun.tdms.workflow.service.doctype.WfDocTypeManager;
import com.yongjun.tdms.workflow.service.job.WfJobHistoryManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: DefaultWfJobManager.java 8064 2007-10-26 08:12:04Z qsun $
 */
public class DefaultWfJobManager extends BaseManager implements WfJobManager {
	private final WfDocTypeManager wfDocTypeManager;
	private final JobDao jobDao;
	private final JobApproverDao jobApproverDao;
	private final WfJobApproverManager wfJobApproverManager;
	private final WfDocStateManager wfDocStateManager;
	private final WfJobHistoryManager wfJobHistoryManager;
	private final UserManager userManager;
	
	public DefaultWfJobManager(WfDocTypeManager wfDocTypeManager,
			JobDao jobDao, JobApproverDao jobApproverDao,
			WfJobApproverManager wfJobApproverManager,
			WfDocStateManager wfDocStateManager, 
			WfJobHistoryManager wfJobHistoryManager,
			UserManager userManager) {
		this.wfDocTypeManager = wfDocTypeManager;
		this.jobDao = jobDao;
		this.jobApproverDao = jobApproverDao;
		this.wfJobApproverManager = wfJobApproverManager;
		this.wfDocStateManager = wfDocStateManager;
		this.wfJobHistoryManager = wfJobHistoryManager;
		this.userManager = userManager;
	}
	
	public Job submitJob(BaseInfoEntity pojo, Long[] approverIds,
			Long finalApproverId, String comment, String docNo, String docName) throws Exception {
		logger.debug("begin submitJob ...");
		if (false == isValidDoc(pojo)) {
			throw new Exception("doc is invalid");
		}
		
		if (isJobSubmitted(pojo)) {
			// step 2. reset all flag in workflow_in_proc
			throw new Exception("doc alerady submitted!");
		}
		
		// step 1.
		Job job = new Job();
		job.setDocIdentify(pojo.getId());
		job.setComment(comment);
		// TODO: hardcode here!
		DocState ds = wfDocStateManager.loadDocStateByCode("DOC_APPROVING");
		job.setDocState(ds);

		DocType docType = wfDocTypeManager.loadDocTypeByCode(getClzID(pojo));
		job.setDocNo(docNo);
		job.setDocType(docType);
		job.setName("JOB");
		job.setDocName(docName);

		List<JobApprover> approverList = wfJobApproverManager
				.createJobApprovers(approverIds, finalApproverId);

		for (JobApprover ja : approverList) {
			ja.setJob(job);
			job.getApprovers().add(ja);
		}

		this.jobDao.storeJob(job);
		
		logger.debug("end submitJob ...");
		
		return job;
	}
	
	private boolean isValidDoc(BaseInfoEntity pojo) {
		return wfDocTypeManager.isValidDocType(pojo);
	}
	
	public boolean isJobSubmitted(BaseInfoEntity pojo) {
		Job job = jobDao.loadJobByDocIdAndType(pojo.getId(), getClzID(pojo));
		return job != null;
	}

	@Deprecated
	public void approveJob(JobApprover approver) {
		jobApproverDao.storeJobApprover(approver);
	}

	public void approveJob(Job job, DocState docState) {
		User user = userManager.getUser();
		//wfSignatureLogManager.makeLog(job, docState, user);
		wfJobApproverManager.updateJobApprover(job, docState, user);
		jobDao.storeJob(job);
	}

	public Job loadJobById(Long id) {
		return jobDao.loadJobById(id);
	}
	
	public Job loadJobByIdAndType(Long id, String type) {
		return jobDao.loadJobByDocIdAndType(id, type);
	}

	public String getJobApprovers(BaseInfoEntity pojo) {
		Job job = jobDao.loadJobByDocIdAndType(pojo.getId(), getClzID(pojo));
		
		String approvers = "";
		
		if (null == job) {
			return approvers;
		}
		
		for (int i = 0; i < job.getApprovers().size(); i ++) {
			JobApprover ap = (JobApprover)job.getApprovers().toArray()[i];
			
			if (ap.isFinalApr()) {
				continue;
			}
			approvers += ap.getApprover().getName();
			if (i + 1 < job.getApprovers().size()) {
				approvers += ", ";
			}
		}
		return approvers.replaceAll(",\\s$", "");
	}

	public Long getJobFinalApproverId(BaseInfoEntity pojo) {
		Job job = jobDao.loadJobByDocIdAndType(pojo.getId(), getClzID(pojo));
		
		if (null == job) {
			return -1L;
		}
		
		for (JobApprover ja : job.getApprovers()) {
			if (ja.isFinalApr()) {
				return ja.getApprover().getId();
			}
		}
		return -1L;
	}
	
	public void cancelJob(BaseInfoEntity pojo) {
		Job job = this.jobDao.loadJobByDocIdAndType(pojo.getId(), getClzID(pojo));
		wfJobHistoryManager.makeHistory(job);
		this.jobDao.deleteJob(job);
	}

	public List<JobApprover> loadApproversExceptMe(Job job) {
		return jobApproverDao.loadApproversExceptSpecialOne(job, userManager.getUser());
	}
	
	public Job getJob(BaseInfoEntity pojo) {
		return jobDao.loadJobByDocIdAndType(pojo.getId(), getClzID(pojo));
	}
	
	private  String getClzID(BaseInfoEntity clz) {
		return WorkFlowUtil.getClzID(clz);
	}

	public boolean isInProc(List list) {
		return false;
	}
}
