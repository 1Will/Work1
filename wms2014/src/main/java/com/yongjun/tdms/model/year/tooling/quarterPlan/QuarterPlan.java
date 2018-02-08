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
package com.yongjun.tdms.model.year.tooling.quarterPlan;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;

/**
 * 
 * <p>Title: QuarterPlan
 * <p>Description: 季度计划类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: QuarterPlan.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class QuarterPlan extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{

	private static final long serialVersionUID = -4669400479041437715L;
	
	//季度计划编号
	private String planNo;
	//季度计划名称
	private String name;
	//年度
	private String year;
	//季度
	private QuarterType qarterType;
	//计划费用
	private Double planAllFee = 0.0;
	//编制日期
	private Date planCreatedDate;
	//编制人
	private User planCreator;
	//部门
	private Department department;
    //部门名称
	private String deptName;
	//关联的工装制作明细
	private Set<ToolingMakeDetail> toolingMakeDetails = new HashSet<ToolingMakeDetail>();
	//关联的备件采购明细
	private Set<SparePurchaseDetail> sparePurchaseDetails = new HashSet<SparePurchaseDetail>();
    //关联的维修保养明细
	private Set<RepairMaintenanceDetail> repairMaintenanceDetails = new HashSet<RepairMaintenanceDetail>();
	//关联的技术改造明细
	private Set<TechAlterDetail> techAlterDetails = new HashSet<TechAlterDetail>();
	//关联的季度计划明细
	private Set<QuarterPlanDetail> quarterPlanDetails = new HashSet<QuarterPlanDetail>();
	@Override
	public int hashCode() {
		return getPlanNo().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof QuarterPlan)) {
			return false;
		}
		QuarterPlan plan = (QuarterPlan)o;
		if (getPlanNo().equals(plan.getPlanNo())) {
			return true;
		}
		return false;
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

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public Date getPlanCreatedDate() {
		return planCreatedDate;
	}

	public void setPlanCreatedDate(Date planCreatedDate) {
		this.planCreatedDate = planCreatedDate;
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

	public QuarterType getQarterType() {
		return qarterType;
	}

	public void setQarterType(QuarterType qarterType) {
		this.qarterType = qarterType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Set<RepairMaintenanceDetail> getRepairMaintenanceDetails() {
		return repairMaintenanceDetails;
	}

	public void setRepairMaintenanceDetails(
			Set<RepairMaintenanceDetail> repairMaintenanceDetails) {
		this.repairMaintenanceDetails = repairMaintenanceDetails;
	}

	public Set<SparePurchaseDetail> getSparePurchaseDetails() {
		return sparePurchaseDetails;
	}

	public void setSparePurchaseDetails(
			Set<SparePurchaseDetail> sparePurchaseDetails) {
		this.sparePurchaseDetails = sparePurchaseDetails;
	}

	public Set<TechAlterDetail> getTechAlterDetails() {
		return techAlterDetails;
	}

	public void setTechAlterDetails(Set<TechAlterDetail> techAlterDetails) {
		this.techAlterDetails = techAlterDetails;
	}

	public Set<ToolingMakeDetail> getToolingMakeDetails() {
		return toolingMakeDetails;
	}

	public void setToolingMakeDetails(Set<ToolingMakeDetail> toolingMakeDetails) {
		this.toolingMakeDetails = toolingMakeDetails;
	}

	public Set<QuarterPlanDetail> getQuarterPlanDetails() {
		return quarterPlanDetails;
	}

	public void setQuarterPlanDetails(Set<QuarterPlanDetail> quarterPlanDetails) {
		this.quarterPlanDetails = quarterPlanDetails;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
