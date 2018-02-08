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

import java.util.Arrays;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.service.year.budget.BudgetManager;

/**
 * 
 * <p>Title: EditBudgetAction
 * <p>Description: 年度预算页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditBudgetAction extends PrepareAction {
	private static final long serialVersionUID = 8381654379833604478L;

	private final BudgetManager budgetManager;
	private final UserManager userManager;
	
	//年度预算
	private Budget budget;
	//资产标识[TOOLING 工装][DEVICE 设备]
	private String toolingDevFlag;

	public EditBudgetAction(BudgetManager budgetManager, UserManager userManager) {
		this.budgetManager = budgetManager;
		this.userManager = userManager;
	}
	
	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == this.budget) {
			if (this.hasId("budget.id")) {  
				this.budget = this.budgetManager.loadBudget(this.getId("budget.id"));
			} else {
				this.budget = new Budget();
			}
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 * 当页面点击保存按钮,保存年度计划的信息
	 * @return SUCCESS
	 */
	public String save() {
		boolean isNew = this.budget.isNew();
		
		if (this.hasId("planCreator.id")) {      //设置编制人
			this.budget.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		if (this.toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			//设置该预算为设备年度预算
			this.budget.setToolingDevFlag(SysModel.DEVICE);
		} else {
			//设置该预算为工装预算
			this.budget.setToolingDevFlag(SysModel.TOOLING);
		}
		this.budgetManager.storeBudget(budget);
		
		if (isNew) {
			this.addActionMessage(this.getText("budget.add.success", Arrays
					.asList(new Object[] { budget.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("budget.edit.success",
							Arrays.asList(new Object[] { budget.getName() })));
			return SUCCESS;
		} 
		
	}
	
	public Budget getBudget() {
		return budget;
	}
	
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
	/**
	 * 获取当前登陆的用户 
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
