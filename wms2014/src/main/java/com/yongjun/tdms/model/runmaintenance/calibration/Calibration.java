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
package com.yongjun.tdms.model.runmaintenance.calibration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;

/**
 * @author qs
 * @version $Id: Calibration.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Calibration extends BaseInfoEntity {
	private static final long serialVersionUID = -8846340523466650787L;
	// 标定编号
	private String planNo;
	// 标定名称
	private String planName;
	// 编制人
	private User writer;
	// 编制日期
	private Date makeDate;
	// 计划制定部门
	private Department department;
	//计划制定部门名称
	private String deptName;
	// 审核人
	private User verifyPeople;
	// 批准人
	private User approver;
	// 报告人
	private User reporter;
	// 报告日期
	private Date reportDate;
	//标识为计划或实施
	private PreRepairModel planProcFlag;
	//计划执行月份
	private String month;
	//实施记录关联的计划
	private Calibration calibrationPlan;
	//计划中关联的实施
	private Set<Calibration> calibrationProc = new HashSet<Calibration>();
	// 标定明细
	private Set<CalibrationDetail> calibrationPlanDetails = new HashSet<CalibrationDetail>();
	private Set<CalibrationDetail> calibrationProcDetails = new HashSet<CalibrationDetail>();
	
	public Calibration() {
		
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
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
	
	public Set<CalibrationDetail> getCalibrationPlanDetails() {
		return calibrationPlanDetails;
	}

	public void setCalibrationPlanDetails(
			Set<CalibrationDetail> calibrationPlanDetails) {
		this.calibrationPlanDetails = calibrationPlanDetails;
	}

	public Set<CalibrationDetail> getCalibrationProcDetails() {
		return calibrationProcDetails;
	}

	public void setCalibrationProcDetails(
			Set<CalibrationDetail> calibrationProcDetails) {
		this.calibrationProcDetails = calibrationProcDetails;
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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
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

	public PreRepairModel getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(PreRepairModel planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public Calibration getCalibrationPlan() {
		return calibrationPlan;
	}

	public void setCalibrationPlan(Calibration calibrationPlan) {
		this.calibrationPlan = calibrationPlan;
	}

	public Set<Calibration> getCalibrationProc() {
		return calibrationProc;
	}

	public void setCalibrationProc(Set<Calibration> calibrationProc) {
		this.calibrationProc = calibrationProc;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
