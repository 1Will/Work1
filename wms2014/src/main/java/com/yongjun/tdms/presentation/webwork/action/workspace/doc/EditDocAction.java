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
package com.yongjun.tdms.presentation.webwork.action.workspace.doc;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;

import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.job.Job;

import com.yongjun.tdms.workflow.service.approver.WfJobApproverManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;


/**
 * @author qs
 * @version $Id: EditDocAction.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class EditDocAction extends PrepareAction {
	private static final long serialVersionUID = -7318717998655567437L;
	private Log log = LogFactory.getLog(getClass());
	private Job job;
	private JobApprover approver;
	private final WfDocStateManager wfDocStateManager;
	private final WfJobManager wfJobManager; 
	private final WfJobApproverManager wfJobApproverManager;
	
	
	public EditDocAction (WfDocStateManager wfDocStateManager, WfJobManager wfJobManager,WfJobApproverManager wfJobApproverManager) {
		this.wfDocStateManager = wfDocStateManager;
		this.wfJobManager = wfJobManager;
		this.wfJobApproverManager=wfJobApproverManager;
	}
	
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public void prepare() throws Exception {
		if (hasId("doc.id")) {
			this.job = wfJobManager.loadJobById(this.getId("doc.id"));
		}
		if(hasId("approver.id")){
			this.approver=wfJobApproverManager.loadJobApprover(this.getId("approver.id"));
		}

	}
	
	public String save() {
		String approvedResult = request.getParameter("approved.result");
		DocState docState = null;
		if (StringUtils.isNotEmpty(approvedResult)) {
			docState = wfDocStateManager.loadDocStateByCode(approvedResult);
		}
		
		log.debug("approve result : " + approvedResult + ", docState : " + docState.getCode());
		job.setDocState(docState);
		wfJobManager.approveJob(job, docState);
		
		this.addActionMessage(this.getText("job.add.success", Arrays
					.asList(new Object[] { job.getDocName() })));


		return SUCCESS;
	}

	public JobApprover getApprover() {
		return approver;
	}

	public void setApprover(JobApprover approver) {
		this.approver = approver;
	}

}
