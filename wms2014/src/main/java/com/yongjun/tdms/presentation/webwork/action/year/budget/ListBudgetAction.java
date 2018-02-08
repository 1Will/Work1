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
package com.yongjun.tdms.presentation.webwork.action.year.budget;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.service.year.budget.BudgetManager;

/**
 * 
 * <p>Title: ListBudgetAction
 * <p>Description: 年度预算页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListBudgetAction extends ValueListAction {

	private static final long serialVersionUID = 5332957095768054072L;
	
	private final BudgetManager budgetManager;
	
	//年度预算集合
	private List<Budget> budgets;
	//资产标识[TOOLING 工装][DEVICE 设备]
	private String toolingDevFlag;
	
	public ListBudgetAction(BudgetManager budgetManager) {
		this.budgetManager = budgetManager;
	}
	
	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		//根据页面传入的"yearPlanIds"(年度计划ID集合),获取年度计划集合
		if (this.hasIds("budgetIds")) {
			this.budgets = this.budgetManager.loadAllBudgets(this.getIds("budgetIds"));
		}
		if (this.hasId("toolingDevFlag")) {       //设置资产标识
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 * 处理有效和失效的操作
	 */
	public String execute() {
		//处理失效
		if (this.isDisabled()) {
			return disabled();
		}
		//处理有效
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	
	/**
	 * 失效页面选中的年度预算
	 * @return SUCCESS
	 */
	public String disabled() {
		this.budgetManager.disabledAllBudgets(this.budgets);
		this.addActionMessage(this.getText("budgets.disabled.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.budgetManager.enabledAllBudgets(this.budgets);
		this.addActionMessage(this.getText("budgets.enabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			//返回设备年度预算的hql的id
			
			return "deviceYearBudgets";
		} else {
			//返回工装年度预算的hql的id
		
			return "toolingYearBudgets";
		}
	}
	
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
