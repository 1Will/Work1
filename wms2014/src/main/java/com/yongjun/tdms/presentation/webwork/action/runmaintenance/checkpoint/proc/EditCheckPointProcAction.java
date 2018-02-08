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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.proc;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcManager;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: EditCheckPointProcAction.java 9463 2007-12-18 07:01:12Z mwei $
 */
public class EditCheckPointProcAction extends PrepareAction {

	private static final long serialVersionUID = -506573693296763147L;

	private final CheckPointProcManager checkPointProcManager;
	private final CheckPointPlanManager checkPointPlanManager;
	private final DeviceCardManager deviceManager;
	private final WfDocApproverManager wfDocApproverManager;
	private final WfJobManager wfJobManager;
	private final UserManager userManager;
	
	private final Log log = LogFactory.getLog(getClass());

	private CheckPointProc proc;

	private CheckPointPlan plan;

	private DeviceCard device;

	private  double planExpenseCollection = 0;

	private  double actualExpenseCollection = 0;

	public EditCheckPointProcAction(
			CheckPointProcManager checkPointProcManager,
			CheckPointPlanManager checkPointPlanManager,
			DeviceCardManager deviceManager,
			WfDocApproverManager wfDocApproverManager,
			WfJobManager wfJobManager,
			UserManager userManager) {
		this.checkPointPlanManager = checkPointPlanManager;
		this.checkPointProcManager = checkPointProcManager;
		this.deviceManager = deviceManager;
		this.wfDocApproverManager=wfDocApproverManager;
		this.wfJobManager=wfJobManager;
		this.userManager = userManager;
	}

	public void prepare() throws Exception {
		if (null == this.proc) {
			if (this.hasId("proc.id")) {
				this.proc = this.checkPointProcManager.loadCheckPointProc(this
						.getId("proc.id"));
				this.plan = proc.getPlan();
				this.device = proc.getDevice();
				this.actualExpenseCollection=this.checkPointProcManager.getActualExpenseCollection(proc);
			} else {
				this.proc = new CheckPointProc();
				this.plan = new CheckPointPlan();
				this.device = new DeviceCard();
			}
		}
		if (this.hasId("plan.id")) {
			this.plan = this.checkPointPlanManager.loadPlan(this
					.getId("plan.id"));
		}

		if (this.hasId("device.id")) {
			this.device = this.deviceManager
					.loadDevice(this.getId("device.id"));
		}

	}

	public String delete() {
		try {
			checkPointProcManager.deleteCheckPointProc(proc);
		} catch (Exception e) {
			this.addActionMessage(this.getText("checkPointProc.delete.error"));
		}
		this.addActionMessage(this.getText("checkPointProc.delete.success",
				Arrays.asList(new Object[] { proc.getName() })));
		return SUCCESS;
	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		if(this.isSubmitDoc()){
			submitDoc();
		}
		if(this.isCancelSubmittedDoc()){
			cancelSubmittedDoc();
		}
		return SUCCESS;
	}

	public String save() {
		if (this.isDelete()) {
			return this.delete();
		}
		if(this.isSubmitDoc()){
			return this.submitDoc();
		}	
		if(this.isCancelSubmittedDoc()){
			return this.cancelSubmittedDoc();
		}
		boolean isNew = this.proc.isNew();
		
		Long[] ids = null;
		if (this.hasIds("approverIds")) {
			ids = getIds("approverIds");
		}
		Long finalId = null;
		if (this.hasId("finalApproverId")) {
			finalId = this.getId("finalApproverId");
		}
		if (ids != null || finalId != null ) {
			String sId = "";
			if (ids != null) {
				List<User> users = userManager.loadAllUsers(ids);
				for (User u : users) {
					sId += u.getId() + ":" + u.getLoginName() + "#";
				}
			}
			if (finalId != null) {
				sId += finalId + ":F";
			}
			
			this.proc.setApprovers(sId);
		}
		
		if (isNew) {
			proc.setStatus(getText("doc.unsubmit"));
		}
		
		String comment = request.getParameter("approv.comment");
		proc.setSubmitComment(comment);
		
		this.checkPointProcManager.storeCheckPointProc(proc, plan);
		if (isNew) {
			this.addActionMessage(this.getText("checkPointProc.add.success",
					Arrays.asList(new Object[] { proc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("checkPointProc.edit.success",
					Arrays.asList(new Object[] { proc.getName() })));
			return SUCCESS;
		}
	}

	public CheckPointProc getProc() {
		return proc;
	}

	public void setProc(CheckPointProc proc) {
		this.proc = proc;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public CheckPointPlan getPlan() {
		return plan;
	}

	public void setPlan(CheckPointPlan plan) {
		this.plan = plan;
	}

	public double getActualExpenseCollection() {
		return actualExpenseCollection;
	}

	public void setActualExpenseCollection(double actualExpenseCollection) {
		this.actualExpenseCollection = actualExpenseCollection;
	}

	public double getPlanExpenseCollection() {
		return planExpenseCollection;
	}

	public void setPlanExpenseCollection(double planExpenseCollection) {
		this.planExpenseCollection = planExpenseCollection;
	}
	
	public List<User> getDocFinalApprovers() {
		return wfDocApproverManager.loadAllFinalApprovers(CheckPointProc.class);
	}
	
	public String getApprovers() {
		String s = wfJobManager.getJobApprovers(proc);
		if (StringUtils.isEmpty(s)) {
			s = proc.getApprovers();
		}
		return s;
	}
	
	public Long getFinalApprover() {
		return wfJobManager.getJobFinalApproverId(proc);
	}
	
	public Job getJob() {
		return wfJobManager.getJob(proc);
	}
	
	public boolean isDocSubmitted() {
		if (proc.isNew()) {
			return false;
		}
		return wfJobManager.isJobSubmitted(proc);
	}
	
	public String submitDoc() {
		log.debug("begin submitDoc...");
		Long[] ids = null;
		if (this.hasIds("approverIds")) {
			ids = getIds("approverIds");
			for (int i = 0; i < ids.length; i++) {
				log.debug("id is : " + ids[i]);
			}
		}
		Long finalId = null;
		finalId = this.getId("finalApproverId");
		
		String comment = request.getParameter("approv.comment");
		try {
			Job job = this.wfJobManager.submitJob(proc, ids, finalId, comment, proc.getProcNo(), proc.getName());
			proc.setJob(job);
			checkPointProcManager.storeCheckPointProc(proc, plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private boolean isCancelSubmittedDoc() {
		return hasKey("cancelSubmitDoc");
	}
	
	private boolean isSubmitDoc() {
		return hasKey("submitDoc");
	}
	
	public String cancelSubmittedDoc() {
		log.debug("begin cancel submitted doc ...");
		try {
			this.checkPointProcManager.cancelJob(proc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
