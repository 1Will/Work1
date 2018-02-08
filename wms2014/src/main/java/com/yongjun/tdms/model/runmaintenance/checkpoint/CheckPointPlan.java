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
package com.yongjun.tdms.model.runmaintenance.checkpoint;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: CheckPointPlan.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CheckPointPlan extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = 7147099557616001689L;
	private String planNo;
	private String name;
	private Date scheduleTime;
	private String content;
	private String request;
	private String comment;
	private int docStatus;
	private String status;
	private String submitComment;
	private String approvers;
	private Double fee;
	private User manager;
	private String ruleType;
	private DeviceCard device;
	private CheckPointRule rule;
	private Set<CheckPointPlanDetail> planDetails= new HashSet<CheckPointPlanDetail>();
	private User Checker;
	
	public CheckPointPlan() {
		
	}
	
	public String getApprovers() {
		return approvers;
	}
	
	public void setApprovers(String approvers) {
		this.approvers = approvers;
	}
	
	public String getSubmitComment() {
		return submitComment;
	}
	
	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public CheckPointRule getRule() {
		return this.rule;
	}
	
	public void setRule(CheckPointRule rule) {
		this.rule = rule;
	}
	
	public DeviceCard getDevice() {
		return this.device;
	}
	
	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	
	public void setChecker(User checker) {
		this.Checker = checker;
	}
	
	public User getChecker() {
		return this.Checker;
	}
	
	@Override
	public int hashCode() {
		return planNo.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof CheckPointPlan)) { return false; }
		
		CheckPointPlan plan = (CheckPointPlan)o;
		
		if (!(planNo.equals(plan.getPlanNo()))) { return false; }
		
		return true;
	}

	public Set<CheckPointPlanDetail> getPlanDetails() {
		return this.planDetails;
	}

	public void setPlanDetails(Set<CheckPointPlanDetail> planDetails) {
		this.planDetails = planDetails;
	}

//	public void createPlanFromRule(CheckPointRule rule, Date scheduleTime) {
//		
////		this.planNo = rule.getRuleNo();
//		this.rule = rule;
//		this.name = rule.getName();
//		this.content = "";
//		this.Checker = rule.getManager();
//		this.request = rule.getRequest();
//		this.comment = rule.getComment();
//		this.device = rule.getDevice();
//		//TODO: 这里实现有问题，应该是下个月的每隔2天的计划
//		
//		//this.scheduleTime = DateUtil.addDaysToDate(rule.getLastCheckTime(), rule.getPeriod());
//		this.scheduleTime = scheduleTime;
//		
//		for (Iterator iter = rule.getRuleDetails().iterator(); iter.hasNext(); ) {
//			CheckPointRuleDetail rd = (CheckPointRuleDetail)iter.next();
//			CheckPointPlanDetail pd = new CheckPointPlanDetail();
//			pd.createDetailFromRuleDetail(rd);
//			pd.setPlan(this);
//			this.planDetails.add(pd);
//		}
//	}

	public int getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(int docStatus) {
		this.docStatus = docStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

}
