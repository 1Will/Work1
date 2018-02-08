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
package com.yongjun.tdms.model.year.budget;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * 
 * <p>Title: BudgetDetail
 * <p>Description: 年度预算详细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class BudgetDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -3468246824541445417L;
	
	//预算编号
	private String budgetNo;
	//名称
	private String budgetName;
	//费用
	private Double fee = 0.0;
	//已列季度计划费用
	private Double quarterPlanFee = 0.0;
	//维修费用
	private Double repairFee = 0.0;
	//工装采购单费用 | 设备申购单费用
	private Double purchaseFee = 0.0;
	//工装采购合同费用 | 设备采购单费用
	private Double purchaseContractFee = 0.0;
	//已发生费用
	private Double occurFee = 0.0;
	//备注
	private String comment;
	//部门
	private Department department;
	//相关的年度预算
	private Budget budget;
	
	@Override
	public int hashCode() {
		return this.getBudgetNo().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof BudgetDetail)) {
			return false;
		}
		BudgetDetail plan = (BudgetDetail)o;
		if (getBudgetNo().equals(plan.getBudgetNo())) {
			return false;
		}
		return false;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getBudgetNo() {
		return budgetNo;
	}

	public void setBudgetNo(String budgetNo) {
		this.budgetNo = budgetNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public Double getQuarterPlanFee() {
		return quarterPlanFee;
	}

	public void setQuarterPlanFee(Double quarterPlanFee) {
		this.quarterPlanFee = quarterPlanFee;
	}

	public Double getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(Double repairFee) {
		this.repairFee = repairFee;
	}

	public Double getPurchaseContractFee() {
		return purchaseContractFee;
	}

	public void setPurchaseContractFee(Double purchaseContractFee) {
		this.purchaseContractFee = purchaseContractFee;
	}

	public Double getPurchaseFee() {
		return purchaseFee;
	}

	public void setPurchaseFee(Double purchaseFee) {
		this.purchaseFee = purchaseFee;
	}

	public Double getOccurFee() {
		return occurFee;
	}

	public void setOccurFee(Double occurFee) {
		this.occurFee = occurFee;
	}

}
