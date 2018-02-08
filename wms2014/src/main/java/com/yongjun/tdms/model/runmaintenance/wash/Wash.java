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
package com.yongjun.tdms.model.runmaintenance.wash;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
/**
 * <p>Title: Wash
 * <p>Description: 清洗类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: Wash.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Wash extends BaseInfoEntity {
	private static final long serialVersionUID = -2666039329120003952L;
	
	//清洗计划编号
	private String planNo;
	
	//清洗计划名称
	private String name;
	//部门
	private String deptName;
	//计划开始时间
	private Date planBeginDate;
	
	//编制日期
	private Date planCreatedTime;
	
	//报告日期[实施]
	private Date reportDate;
	
	//计划或实施标识[plan|proc]
	private PreRepairModel planProcFlag;
	
	//编制人
	private User planCreator;
	
	//审核人
	private User planAuditor;
	
	//报告人[实施]
	private User reportor;
	
	//部门
	private Department department;
	
	//关联的计划明细
	private Set<WashDetail> planDetails = new HashSet<WashDetail>();
	
	//关联的实施明细[实施]
	private Set<WashDetail> procDetails = new HashSet<WashDetail>();
	
	//清洗实施关联的清洗计划
	private Wash washPlan;
	
	
	private Set<Wash> washProc = new HashSet<Wash>();

	@Override
	public int hashCode() {
		return planNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Wash)) {return false;}
		
		Wash wash = (Wash)o;
		if (this.planNo.equals(wash.getPlanNo())) {return true;}
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

	public User getPlanAuditor() {
		return planAuditor;
	}

	public void setPlanAuditor(User planAuditor) {
		this.planAuditor = planAuditor;
	}

	public Date getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
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

	public Set<WashDetail> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(Set<WashDetail> planDetails) {
		this.planDetails = planDetails;
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

	public Set<WashDetail> getProcDetails() {
		return procDetails;
	}

	public void setProcDetails(Set<WashDetail> procDetails) {
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

	public Wash getWashPlan() {
		return washPlan;
	}

	public void setWashPlan(Wash washPlan) {
		this.washPlan = washPlan;
	}

	public Set<Wash> getWashProc() {
		return washProc;
	}

	public void setWashProc(Set<Wash> washProc) {
		this.washProc = washProc;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
