package com.yongjun.tdms.presentation.webwork.action.workspace.doc;


import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: ListSignatureLogAction.java 7654 2007-09-29 07:53:05Z qsun $
 */
public class ListSignatureLogAction extends PrepareAction {
	private static final long serialVersionUID = -2002844340878603721L;
	private final WfJobManager wfJobManager;
	private Job job;
	private List<JobApprover> JobApprovers = new ArrayList<JobApprover>(); 
	
	public ListSignatureLogAction(WfJobManager wfJobManager) {
		this.wfJobManager = wfJobManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("job.id")) {
			 this.job = wfJobManager.loadJobById(this.getId("job.id"));
			 this.JobApprovers = wfJobManager.loadApproversExceptMe(job);
		}
	}
	
	public List<JobApprover> getJobApprovers() {
		return this.JobApprovers;
	}
	
	public void setJobApprovers(List<JobApprover> jobApprovers) {
		this.JobApprovers = jobApprovers;
	}
	
	public Job getJob() {
		return this.job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public String list () {
		return SUCCESS;
	}

}
