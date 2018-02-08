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


import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.budget.BudgetManager;

/**
 * 
 * <p>Title: ListBudgetDetailAction
 * <p>Description: 年度预算明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListBudgetDetailAction extends ValueListAction {
	private static final long serialVersionUID = -8997448681564313012L;
	
	private final BudgetManager budgetManager;
	private final BudgetDetailManager budgetDetailManager;
	
	//年度预算
	private Budget budget;
	//年度预算明细
	private List<BudgetDetail> budgetDetails;
	//资产标识[TOOLING 工装][DEVICE 设备]
	private String toolingDevFlag;

	public ListBudgetDetailAction(BudgetManager budgetManager, 
			BudgetDetailManager budgetDetailManager) {
		this.budgetManager = budgetManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("budget.id")) {         //如果请求中包含"budget.id",则根据"budget.id"获取年度预算对象
			this.budget = this.budgetManager.loadBudget(this.getId("budget.id"));
		}
		if (this.hasIds("budgetDetailIds")) { //如果请求中包含"budgetDetailIds",则根据"budgetDetailIds"获取年度预算明细集合   
			this.budgetDetails = this.budgetDetailManager.loadAllBudgetDetails(this.getIds("budgetDetailIds"));
		}
		if (this.hasId("toolingDevFlag")) {          //根据请求中的"toolingDevFlag"参数的值,来设置资产标识
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}

		this.setFirst(false);
	}
	
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.budgetDetailManager.deleteAllBudgetDetails(this.budget,this.budgetDetails);
		//从预算中移除预算明细
		for (BudgetDetail detail : budgetDetails) {
			budget.getBudgetDetail().remove(detail);
		}
		//计算年度预算明细的总费用
		this.budgetManager.calculateBudgetAllFee(budget);
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "yearBudgetDetails";
	}

	/**
	 * 往查询年度预算明细的hql语句中设置年度预算的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();                                        
        if (this.hasId("budget.id")){
			map.put("budget.id", this.getId("budget.id"));
		}
		return map;
	}
	
	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
