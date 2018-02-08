package com.yongjun.tdms.model.runmaintenance.lubrication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.pluto.model.security.Department;

public class LubricationPlan extends BaseInfoEntity{
	private static final long serialVersionUID = -4993291729957434065L;
	//月份
	//private String planMonth;
	
	// 计划编号
	private String planNo;

	// 计划名称
	private String name;

	// 计划制定部门
	private Department department;

	// 计划编制人
	private User planCreator;

	// 编制日期
	private Date planCreatedTime;
	
	//报告人
	private User reportor;
	
	//报告日期
	private Date reportDate;
	
	//计划执行月份
	private String month;
	
	//关联的计划明细
	private Set<LubricationPlanDetail> planDetails = new HashSet<LubricationPlanDetail>();
	
	//关联的实施明细[实施]
	private Set<LubricationPlanDetail> procDetails = new HashSet<LubricationPlanDetail>();
	
	//润滑实施关联的润滑计划
	private LubricationPlan lubricationPlan;
	
	private Set<LubricationPlan> lubricationProc = new HashSet<LubricationPlan>();
	
	//计划或实施标识[plan|proc]
	private PreRepairModel planProcFlag;

	// 计划审核人
	//private User planAuditor;

	// 完成状态[实施]
	//private String procFinishStatus;

	// 执行状态
	//private LubricationPlanStatus procResult;
	
	public LubricationPlan() {
		
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

	public Date getPlanCreatedTime() {
		return planCreatedTime;
	}

	public void setPlanCreatedTime(Date planCreatedTime) {
		this.planCreatedTime = planCreatedTime;
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

	public User getPlanCreator() {
		return planCreator;
	}

	public void setPlanCreator(User planCreator) {
		this.planCreator = planCreator;
	}

	public LubricationPlan getLubricationPlan() {
		return lubricationPlan;
	}

	public void setLubricationPlan(LubricationPlan lubricationPlan) {
		this.lubricationPlan = lubricationPlan;
	}

	public Set<LubricationPlan> getLubricationProc() {
		return lubricationProc;
	}

	public void setLubricationProc(Set<LubricationPlan> lubricationProc) {
		this.lubricationProc = lubricationProc;
	}

	public Set<LubricationPlanDetail> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(Set<LubricationPlanDetail> planDetails) {
		this.planDetails = planDetails;
	}

	public Set<LubricationPlanDetail> getProcDetails() {
		return procDetails;
	}

	public void setProcDetails(Set<LubricationPlanDetail> procDetails) {
		this.procDetails = procDetails;
	}

	public PreRepairModel getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(PreRepairModel planProcFlag) {
		this.planProcFlag = planProcFlag;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
