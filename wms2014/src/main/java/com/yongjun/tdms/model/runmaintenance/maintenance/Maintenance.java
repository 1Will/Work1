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
package com.yongjun.tdms.model.runmaintenance.maintenance;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;

/**
 * @author wzou
 * @version $Id: Maintenance.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Maintenance extends BaseInfoEntity {

	private static final long serialVersionUID = 2268464498550348823L;
	// 保养编号
	private String planNo;
	// 保养名称
	private String planName;
	// 编制人
	private User writer;
	// 编制日期
	private Date makeDate;
	// 计划制定部门
	private Department department;
	// 计划执行月份
	private String month;
	// 审核人
	private User verifyPeople;
	// 报告人
	private User reporter;
	// 报告日期
	private Date reportDate;
	// 保养类型
	private MaintenanceType result=MaintenanceType.one_maintenance;
	// 标识为计划或实施
	private PreRepairModel planProcFlag;
	// 实施记录关联的计划
	private Maintenance maintenancePlan;
	// 计划中关联的实施
	private Set<Maintenance> maintenanceProc = new HashSet<Maintenance>();
	// 保养明细
	private Set<MaintenanceDetail> maintenancePlanDetials = new HashSet<MaintenanceDetail>();
	private Set<MaintenanceDetail> maintenanceProcDetials = new HashSet<MaintenanceDetail>();
	
	private boolean disabled;
	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public Maintenance() {
		
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Maintenance getMaintenancePlan() {
		return maintenancePlan;
	}
	public void setMaintenancePlan(Maintenance maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}
	public Set<MaintenanceDetail> getMaintenancePlanDetials() {
		return maintenancePlanDetials;
	}
	public void setMaintenancePlanDetials(
			Set<MaintenanceDetail> maintenancePlanDetials) {
		this.maintenancePlanDetials = maintenancePlanDetials;
	}
	public Set<Maintenance> getMaintenanceProc() {
		return maintenanceProc;
	}
	public void setMaintenanceProc(Set<Maintenance> maintenanceProc) {
		this.maintenanceProc = maintenanceProc;
	}
	public Set<MaintenanceDetail> getMaintenanceProcDetials() {
		return maintenanceProcDetials;
	}
	public void setMaintenanceProcDetials(
			Set<MaintenanceDetail> maintenanceProcDetials) {
		this.maintenanceProcDetials = maintenanceProcDetials;
	}
	public Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
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
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getVerifyPeople() {
		return verifyPeople;
	}
	public void setVerifyPeople(User verifyPeople) {
		this.verifyPeople = verifyPeople;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public MaintenanceType getResult() {
		return result;
	}
	public void setResult(MaintenanceType result) {
		this.result = result;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
}
