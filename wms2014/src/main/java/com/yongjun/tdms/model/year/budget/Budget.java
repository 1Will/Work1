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
import com.yongjun.tdms.model.SysModel;

/**
 * 
 * <p>Title: Budget
 * <p>Description: 年度预算类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: Budget.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Budget extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -1372039243471942204L;
	
	//年度预算编号
	private String yearBudgetNo;
	//年度预算名称
	private String name;
	//年度
	private String year;
	//预算总费用
	private Double allFee = 0.0;
	//编制日期
	private Date planCreatedDate = new Date();
	//编制人
	private User planCreator;
	//资产标识[TOOLING 工装][DEVICE 设备] 默认为设备
	private SysModel toolingDevFlag = SysModel.DEVICE;
	//关联的预算详细
	private Set<BudgetDetail> budgetDetail = new HashSet<BudgetDetail>();
	
	@Override
	public int hashCode() {
		return this.getYearBudgetNo().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof Budget)) {
			return false;
		}
		Budget plan = (Budget)o;
		if (getYearBudgetNo().equals(plan.getYearBudgetNo())) {
			return false;
		}
		return false;
	}

	public Double getAllFee() {
		return allFee;
	}

	public void setAllFee(Double allFee) {
		this.allFee = allFee;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYearBudgetNo() {
		return yearBudgetNo;
	}

	public void setYearBudgetNo(String yearBudgetNo) {
		this.yearBudgetNo = yearBudgetNo;
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

	public Set<BudgetDetail> getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(Set<BudgetDetail> budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
