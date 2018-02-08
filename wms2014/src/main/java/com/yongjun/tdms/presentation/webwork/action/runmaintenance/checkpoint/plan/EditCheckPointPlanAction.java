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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.plan;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: EditCheckPointPlanAction.java 11417 2008-03-18 10:43:51Z wzou $
 */
public class EditCheckPointPlanAction extends PrepareAction {
	private static final long serialVersionUID = -5647253045380984559L;

	private final Log log = LogFactory.getLog(getClass());
	private CheckPointPlan plan;
	private DeviceCard device;
	private User manager;
	private CheckPointRule rule;

	private final CheckPointPlanManager checkPointPlanManager;
	private final DeviceCardManager deviceCardManager;
	private final CheckPointRuleManager checkPointRuleManager;
	private final CheckPointPlanDetailManager checkPointPlanDetailManager;
	private final WfDocApproverManager wfDocApproverManager;
	private final WfJobManager wfJobManager;
	private final UserManager userManager;
	

	public EditCheckPointPlanAction(
			CheckPointPlanManager checkPointPlanManager,
			DeviceCardManager deviceCardManager,
			CheckPointRuleManager checkPointRuleManager,
			CheckPointPlanDetailManager checkPointPlanDetailManager,
			WfDocApproverManager wfDocApproverManager,
			WfJobManager wfJobManager,
			UserManager userManager) {
		this.checkPointPlanManager = checkPointPlanManager;
		this.deviceCardManager = deviceCardManager;
		this.checkPointRuleManager = checkPointRuleManager;
		this.checkPointPlanDetailManager = checkPointPlanDetailManager;
		this.wfDocApproverManager = wfDocApproverManager;
		this.wfJobManager = wfJobManager;
		this.userManager = userManager;
	}

	public CheckPointPlan getPlan() {
		return this.plan;
	}

	public void setCheckPointPlan(CheckPointPlan plan) {
		this.plan = plan;
	}

	public void prepare() throws Exception {
		if (this.plan == null) {
			if (this.hasId("plan.id")) {
				this.plan = this.checkPointPlanManager.loadPlan(this
						.getId("plan.id"));
				this.device=plan.getDevice();
				this.rule=plan.getRule();
			} else {
				this.device=new DeviceCard();
				this.plan = new CheckPointPlan();
				this.rule=new CheckPointRule();
			}
		}
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this
					.getId("device.id"));
		}
		if(this.hasId("rule.id")){
			this.rule = this.checkPointRuleManager.loadRule(this.getId("rule.id"));
		}
		if(this.hasId("manager.id")){
			this.manager = this.userManager.loadUser(this.getId("manager.id"));
		}
	}
	
	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		if (this.isSubmitDoc()) {
			submitDoc();
		}
		if (this.isCancelSubmittedDoc()) {
			cancelSubmittedDoc();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.addActionMessage(this.getText("checkPointPlan.invalid.success",
				Arrays.asList(new Object[] { plan.getName() })));
		return SUCCESS;
	}
	
	private boolean isSubmitDoc() {
		return hasKey("submitDoc");
	}
	
	private boolean isCancelSubmittedDoc() {
		return hasKey("cancelSubmitDoc");
	}

	public String save() {
		if (this.isSubmitDoc()) {
			return this.submitDoc();
		}
		
		if (this.isCancelSubmittedDoc()) {
			return this.cancelSubmittedDoc();
		}
		if (log.isDebugEnabled()) {
			log.debug("plan [no,name] is " + plan.getPlanNo() + ","
					+ plan.getName());
		}

		boolean isNew = this.plan.isNew();
		plan.setDevice(device);
		plan.setRule(rule);
		plan.setManager(manager);
		String comment = request.getParameter("approv.comment");
		plan.setSubmitComment(comment);
		
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
			
			this.plan.setApprovers(sId);
		}
		
		if (isNew) {
			plan.setStatus(getText("doc.unsubmit"));
		}
		this.checkPointPlanManager.storePlan(plan, rule);
		if (isNew) {
//			loadAllCheckPointPlanDetail();
			this.addActionMessage(this.getText("checkPointPlan.add.success",
					Arrays.asList(new Object[] { plan.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("checkPointPlan.edit.success",
					Arrays.asList(new Object[] { plan.getName() })));
			return SUCCESS;
		}
	}
	

//	public void loadAllCheckPointPlanDetail(){
////		System.out.println("rule.id====================="+rule.getId());
//		//Set<CheckPointRuleDetail> ruleSet=rule.getRuleDetails();
//		CheckPointPlanDetail planDetail;
//		//Object[] ruleDetail=ruleSet.toArray();
//		for(int i=0;i<ruleDetail.length;i++){
//			planDetail=new CheckPointPlanDetail();
//			planDetail.setPart(((CheckPointRuleDetail) ruleDetail[i]).getPart());
//			planDetail.setContent(((CheckPointRuleDetail) ruleDetail[i]).getContent());
//			planDetail.setMethod(((CheckPointRuleDetail) ruleDetail[i]).getMethod());
//			planDetail.setTool(((CheckPointRuleDetail) ruleDetail[i]).getTool());
//			planDetail.setRule(((CheckPointRuleDetail) ruleDetail[i]).getRule());
//			planDetail.setComment(((CheckPointRuleDetail) ruleDetail[i]).getRule());
//			planDetail.setFee(((CheckPointRuleDetail) ruleDetail[i]).getFee());
//			planDetail.setRuleDetail((CheckPointRuleDetail)ruleDetail[i]);
////			planDetail.setRuleDetail((CheckPointRuleDetail) ruleDetail[i]);
//			planDetail.setPlan((plan));
//			checkPointPlanDetailManager.storePlanDetail(planDetail);
//		}
//		
//	}
	
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
			Job job = this.wfJobManager.submitJob(plan, ids, finalId, comment, plan.getPlanNo(), plan.getName());
			plan.setJob(job);
			checkPointPlanManager.storePlan(plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String cancelSubmittedDoc() {
		log.debug("begin cancel submitted doc ...");
		try {
			this.checkPointPlanManager.cancelJob(plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<DeviceCard> getDevices() {
		return deviceCardManager.loadAllDevices();
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public void setPlan(CheckPointPlan plan) {
		this.plan = plan;
	}

	public CheckPointRule getRule() {
		return rule;
	}

	public void setRule(CheckPointRule rule) {
		this.rule = rule;
	}
	
	public List<User> getDocFinalApprovers() {
		return wfDocApproverManager.loadAllFinalApprovers(CheckPointPlan.class);
	}
	
	public boolean isDocSubmitted() {
		if (plan.isNew()) {
			return false;
		}
		return wfJobManager.isJobSubmitted(plan);
	}
	
	public String getApprovers() {
		String s = wfJobManager.getJobApprovers(plan);
		if (StringUtils.isEmpty(s)) {
			s = plan.getApprovers();
		}
		return s;
	}
	
	public Long getFinalApprovers() {
		return wfJobManager.getJobFinalApproverId(plan);
	}
	
	public Job getJob() {
		return wfJobManager.getJob(plan);
	}

}
