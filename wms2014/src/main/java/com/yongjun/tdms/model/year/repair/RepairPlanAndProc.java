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
package com.yongjun.tdms.model.year.repair;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairResult;

/**
 * 
 * 
 * <p>Title: RepairPlanAndProc
 * <p>Description: 年度大项修类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: RepairPlanAndProc.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairPlanAndProc extends BaseInfoEntity {
	private static final long serialVersionUID = -5353079607838497436L;
	
	//大项修计划编号
	private String planNo;
	
	//大项修计划名称
	private String name;
	
	//年度
	private String year;
	
	//大项修计划总费用
	private Double planAllFee;
	
	//大项修实际总费用
	private Double procAllFee;
	
	//大项修报告日期
	private Date reportDate;
	
	//大项修报告人
	private User reportor;
	
	//大项修计划和实施标识[PLAN][PROC]
	private PreRepairModel planProcFlag;
	
	//大项修计划完成状态
	private String procFinishStatus;
	
	//大项修计划的执行状态[未执行][执行中][已完成]
	private PreRepairResult procStatus;
	
	//大项修计划编制日期[默认当前时间]
	private Date planCreatedTime;
	
	//大项修计划编制人
	private User planCreator;
	
	//大项修计划审核人
	private User planAuditor;
	
	//部门
	private Department department;
	
	private String departName;
	
	//大项修实施中关联的计划
	private RepairPlanAndProc repairPlan;
	
	//大项修计划中关联的实施
	private Set<RepairPlanAndProc> repairProc = new HashSet<RepairPlanAndProc>();
	
	//大项修计划或实施中资产的标识[工装][设备]
	private SysModel toolingDevFlag;
	
	/*workflow begin*/
	//审核状态 默认[未提交]
	//private String status;
	
	//审核说明
	//private String submitComment;
	
	//审核人和最终审核人的组合
	//private String approvers;
	
	//与大项修相关的审核工作
	//private Job job;
	/*workflow end*/
	
	// 预防性维修计划明细
	private Set<RepairPlanAndProcDetail> planDetails = new HashSet<RepairPlanAndProcDetail>();
	
	//预防性维修实施明细
	private Set<RepairPlanAndProcDetail> procDetails = new HashSet<RepairPlanAndProcDetail>();
	
	public RepairPlanAndProc(){}
	
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

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public User getPlanAuditor() {
		return planAuditor;
	}

	public void setPlanAuditor(User planAuditor) {
		this.planAuditor = planAuditor;
	}

	public Date getPlanCreatedTime() {
		return planCreatedTime;
	}

	public void setPlanCreatedTime(Date planCreatedTime) {
		this.planCreatedTime = planCreatedTime;
	}

	public User getPlanCreator() {
		return planCreator;
	}

	public void setPlanCreator(User planCreator) {
		this.planCreator = planCreator;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public PreRepairModel getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(PreRepairModel planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public Double getProcAllFee() {
		return procAllFee;
	}

	public void setProcAllFee(Double procAllFee) {
		this.procAllFee = procAllFee;
	}

	public String getProcFinishStatus() {
		return procFinishStatus;
	}

	public void setProcFinishStatus(String procFinishStatus) {
		this.procFinishStatus = procFinishStatus;
	}

	public PreRepairResult getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(PreRepairResult procStatus) {
		this.procStatus = procStatus;
	}

	public RepairPlanAndProc getRepairPlan() {
		return repairPlan;
	}

	public void setRepairPlan(RepairPlanAndProc repairPlan) {
		this.repairPlan = repairPlan;
	}

	public Set<RepairPlanAndProc> getRepairProc() {
		return repairProc;
	}

	public void setRepairProc(Set<RepairPlanAndProc> repairProc) {
		this.repairProc = repairProc;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	@Override
	public int hashCode() {
		return getPlanNo().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof RepairPlanAndProc)) {
			return false;
		}
		RepairPlanAndProc plan = (RepairPlanAndProc)o;
		if (getPlanNo().equals(plan.getPlanNo())) {
			return false;
		}
		return false;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Set<RepairPlanAndProcDetail> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(Set<RepairPlanAndProcDetail> planDetails) {
		this.planDetails = planDetails;
	}

	public Set<RepairPlanAndProcDetail> getProcDetails() {
		return procDetails;
	}

	public void setProcDetails(Set<RepairPlanAndProcDetail> procDetails) {
		this.procDetails = procDetails;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public User getReportor() {
		return reportor;
	}

	public void setReportor(User reportor) {
		this.reportor = reportor;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

}
