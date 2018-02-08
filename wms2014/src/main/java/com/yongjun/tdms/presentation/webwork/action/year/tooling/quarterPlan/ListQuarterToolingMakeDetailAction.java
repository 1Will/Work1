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
package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;


/**
 * 
 * <p>Title: ListQuarterToolingMakeDetail
 * <p>Description: 季度计划工装制造详细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListQuarterToolingMakeDetailAction extends ValueListAction {
	private static final long serialVersionUID = -460218013168549832L;
	private final QuarterPlanDetailManager toolingMakeDetailManager;
	private final QuarterPlanManager quarterPlanManager;
	private final BudgetDetailManager budgetDetailManager;
	
	//工装制作集合
	private List<QuarterPlanDetail> toolingMakeDetails;
	//季度计划
	private QuarterPlan quarterPlan;
	//选中的年度计划的工装制作明细ID集合
	private String yearToolingMakeDetailIds;
	
	public ListQuarterToolingMakeDetailAction(QuarterPlanDetailManager toolingMakeDetailManager, 
			QuarterPlanManager quarterPlanManager,
			BudgetDetailManager budgetDetailManager) {
		this.toolingMakeDetailManager = toolingMakeDetailManager;
		this.quarterPlanManager = quarterPlanManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	/**
	 * 准备其他方法需要使用的数据,工装制作集合的数据
	 */
	public void prepare() {
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
        //如果请求中包含"toolingMakeDetailIds",则根据"toolingMakeDetailIds"获取工装制作详细集合
		if (this.hasIds("toolingMakeDetailIds")) {    
			this.toolingMakeDetails = this.toolingMakeDetailManager.loadAllQuarterPlanDetails(this.getIds("toolingMakeDetailIds"));
		}
		if (this.hasId("yearToolingMakeDetailIds")) {
			this.yearToolingMakeDetailIds = request.getParameter("yearToolingMakeDetailIds");
		} 
		this.setFirst(false);                
	}
	
	/**
	 * 1、当页面点击删除按钮,则删除选中的工装制作详细
	 * 2、当页面点击从年度计划中选择按钮,则从年度计划的工装制作明细中创建季度计划的工装制作明细
	 */
	public String execute() {
		//删除选定的年度计划的工装制作详细或季度计划的工装制作详细
		if (this.isDelete()) {     
			return delete();
		}
		//把年度计划的工装制作详细拷贝到季度计划的工装制作详细
		if (this.isCopy()) {
			return createQuarterPlanFromYearPlan();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击了"从年度计划选择的按钮"
	 * @return
	 */
	public boolean isCopy() {
		return this.hasKey("copy");
	}
	
	/**
	 * 从年度计划的工装制作明细中创建季度计划的工装制作明细
	 * @return
	 */
	public String createQuarterPlanFromYearPlan() {
		this.toolingMakeDetailManager.storeQuarterPlanToolingMakeDetail(this.quarterPlan,yearToolingMakeDetailIds);
		return SUCCESS;
	}
	/**
	 * 删除页面选中的工装制作详细
	 * @return
	 */
	public String delete() {
	    //,删除季度计划所关联的工装制作明细，如果工装制作明细已被制作为采购单，则不能删除
		try {
			this.toolingMakeDetailManager.deleteAllToolingMakeDetailOfQuarterPlan(
					this.toolingMakeDetails, this.quarterPlan);
		} catch(Exception e) {
			return ERROR;
		}

		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "quarterToolingMakeDetails";
	}
	
	/**
	 * 往查询工装制作详细的hql语句中设置年度计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("quarterPlan.id")){
			map.put("quarterPlan.id", this.getId("quarterPlan.id"));
        }
		return map;
	}
	
	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}
	
	public Double getOverBudgetFee() {
		if (null != this.quarterPlan) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.TOOLING_MAKE.toString());
			if (null != budgetDetail) {
				if (budgetDetail.getQuarterPlanFee() > budgetDetail.getFee()) {
					return budgetDetail.getQuarterPlanFee() - budgetDetail.getFee();
				}
			}
		}
		return null;
	}
}
