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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.budget.BudgetManager;

/**
 * 
 * <p>Title: EditBudgetDetailAction
 * <p>Description:年度预算明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditBudgetDetailAction extends PrepareAction {
	private static final long serialVersionUID = 6546915255250646957L;
	
	private final BudgetManager budgetManager;
	private final BudgetDetailManager budgetDetailManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	//年度预算
	private Budget budget;
	//年度预算明细
	private BudgetDetail budgetDetail;
	//资产标识[TOOLING 工装][DEVICE 设备]
	private String toolingDevFlag;

	public EditBudgetDetailAction(BudgetManager budgetManager,
			BudgetDetailManager budgetDetailManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.budgetManager = budgetManager;
		this.budgetDetailManager = budgetDetailManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}
	
	/**
	 * 为其他方法准备数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("budget.id")) {          //如果请求中包含"budget.id",则根据"budget.id"获取年度预算对象
			this.budget = this.budgetManager.loadBudget(this.getId("budget.id"));
		}
		if (null == this.budgetDetail) {
			if(this.hasId("budgetDetail.id")) {
				 //如果请求中有"budgetDetail.id",则根据"budgetDetail.id"获取年度预算明细
				this.budgetDetail = this.budgetDetailManager.loadBudgetDetail(this.getId("budgetDetail.id"));
			} else {
				 //如果请求中没有"budgetDetail.id",则创建年度预算明细
				this.budgetDetail = new BudgetDetail();
			}
		}
	}
	
	/**
	 * 如果点击保存按钮，保存维修保养明细
	 * @return
	 */
    public String save() {
    	boolean isNew = this.budgetDetail.isNew();
    	
    	if (this.hasId("department.id")) {            //设置部门
    		this.budgetDetail.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
    	}
    	this.budgetDetail.setBudget(budget);         //设置年度预算
    	this.budgetDetailManager.storeBudgetDetail(this.budgetDetail);
    	//计算年度预算明细的总费用
    	this.budgetManager.calculateBudgetAllFee(this.budgetDetail.getBudget());
		if (isNew) {
			this.addActionMessage(this.getText("budgetDetail.add.success", Arrays
					.asList(new Object[] { budgetDetail.getBudgetName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("budgetDetail.edit.success", Arrays
					.asList(new Object[] {budgetDetail.getBudgetName() })));
			return SUCCESS;
		}
    }

	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	 public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
	
	 /**
	  * 获取所有有效记录的预算编号的值,并且移除当前的预算编号
	  * @return List 预算编号集合
	  */
	public List<String> getAllEnableBudgetNo() {
		//获取所有有效记录的预算编号的值
		List <String> list = this.budgetDetailManager.loadAllBudgetNoOfEnabled();
		if (null != this.budgetDetail.getBudgetNo()) {       //如果当前预算编号不为空，则移除当前编号
			list.remove(this.budgetDetail.getBudgetNo());
		}
		return list;
	}
	
	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
