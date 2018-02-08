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
package com.yongjun.tdms.model.runmaintenance.repair;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.workflow.model.job.Job;

/**
 * <p>Title: PreRepairPlan
 * <p>Description: 预防性维修计划类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: PreRepairPlan.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PreRepairPlan extends BaseInfoEntity {
	private static final long serialVersionUID = 8926917573985675522L;

	// 计划编号
	private String planNo;
	
	// 计划名称
	private String name;
	
	// 计划开始日期
	private Date beginDate;
	
	//实施开始日期
	//private Date procBeginDate;
	
	//执行状态
	private PreRepairResult procResult;
	
	// 完成状态[实施]
	private String procFinishStatus;
	
	// 计划制定部门
	private Department department;
	
	//计划制定部门名称
	private String deptName;
	
	// 计划编制人
	private User planCreator;
	
	// 编制日期
	private Date planCreatedTime;
	
	// 计划总费用额
	private Double planAllFee;
	
	// 实际总费用额
	private Double procAllFee;
	
	// 计划审核人
	private User planAuditor;
	
	//报告人[实施]
	private User reportor;
	
	//报告日期[实施]
	private Date reportDate;
	
	//标识为计划或实施
	private PreRepairModel planProcFlag;
	
	//工装设备标识[工装|设备]
	private SysModel toolingDevFlag;	
	
	//预防性维修计划审核状态[未提交][已提交]
	private String status;
	
	//预防性维修计划提交工作流的审核说明
	private String submitComment;
	
	//预防性维修计划提交工作流的审核人和最终审核人
	private String approvers;
	
	//预防性维修计划提交工作流关联的审核工作
	private Job job;
	
	//实施记录关联的计划
	private PreRepairPlan preRepairPlan;
	
	//计划中关联的实施
	private Set<PreRepairPlan> preRepairProc = new HashSet<PreRepairPlan>();
	
	// 预防性维修计划明细
	private Set<PreRepairPlanDetail> planDetails = new HashSet<PreRepairPlanDetail>();
	
	//预防性维修实施明细
	private Set<PreRepairPlanDetail> procDetails = new HashSet<PreRepairPlanDetail>();
	
	public PreRepairPlan() {
		
	}
	
	public Set<PreRepairPlanDetail> getPlanDetails() {
		return this.planDetails;
	}
	
	public void setPlanDetails(Set<PreRepairPlanDetail> planDetails) {
		this.planDetails = planDetails;
	}
	
	public User getPlanAuditor() {
		return planAuditor;
	}

	public void setPlanAuditor(User planAuditor) {
		this.planAuditor = planAuditor;
	}

	public User getPlanCreator() {
		return planCreator;
	}

	public void setPlanCreator(User planCreator) {
		this.planCreator = planCreator;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Date getPlanCreatedTime() {
		return planCreatedTime;
	}

	public void setPlanCreatedTime(Date planCreatedTime) {
		this.planCreatedTime = planCreatedTime;
	}

	public PreRepairResult getProcResult() {
		return procResult;
	}

	public void setProcResult(PreRepairResult procResult) {
		this.procResult = procResult;
	}

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public Double getProcAllFee() {
		return procAllFee;
	}

	public void setProcAllFee(Double procAllFee) {
		this.procAllFee = procAllFee;
	}

	public User getReportor() {
		return reportor;
	}

	public void setReportor(User reportor) {
		this.reportor = reportor;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getProcFinishStatus() {
		return procFinishStatus;
	}

	public void setProcFinishStatus(String procFinishStatus) {
		this.procFinishStatus = procFinishStatus;
	}

	public String getApprovers() {
		return approvers;
	}

	public void setApprovers(String approvers) {
		this.approvers = approvers;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitComment() {
		return submitComment;
	}

	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}

	public PreRepairModel getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(PreRepairModel planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public PreRepairPlan getPreRepairPlan() {
		return preRepairPlan;
	}

	public void setPreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlan = preRepairPlan;
	}

	public Set<PreRepairPlan> getPreRepairProc() {
		return preRepairProc;
	}

	public void setPreRepairProc(Set<PreRepairPlan> preRepairProc) {
		this.preRepairProc = preRepairProc;
	}

	public Set<PreRepairPlanDetail> getProcDetails() {
		return procDetails;
	}

	public void setProcDetails(Set<PreRepairPlanDetail> procDetails) {
		this.procDetails = procDetails;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String getDomainModelProperty() {
		// TODO Auto-generated method stub
		return this.getProperties().getProperty("pre_repair_plan");
	}
	
}
