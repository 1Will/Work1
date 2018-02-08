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

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.tooling.TechAlterDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * 
 * <p>Title: ListQuarterToolingMakeDetail
 * <p>Description: 季度计划技术改造详细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListQuarterTechAlterDetailAction extends ValueListAction {
	private static final long serialVersionUID = -4400117688263488955L;
	
	private final QuarterPlanDetailManager techAlterDetailManager;
	private final QuarterPlanManager quarterPlanManager;
	private final BudgetDetailManager budgetDetailManager;
	
	//技术改造明细
	private List<QuarterPlanDetail> techAlterDetails;
	//季度计划
	private QuarterPlan quarterPlan;
	//选中的年度计划的技术改造明细ID集合
	private String yearTechAlterDetailIds;

	public ListQuarterTechAlterDetailAction(QuarterPlanDetailManager techAlterDetailManager,
			QuarterPlanManager quarterPlanManager,
			BudgetDetailManager budgetDetailManager) {
		this.techAlterDetailManager = techAlterDetailManager;
		this.quarterPlanManager = quarterPlanManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
		if (this.hasIds("techAlterDetailIds")) { //如果请求中包含"techAlterDetailIds",则根据"techAlterDetailIds"获取技术改造明细集合   
			this.techAlterDetails = this.techAlterDetailManager.loadAllQuarterPlanDetails(this.getIds("techAlterDetailIds"));
		}
		if (!StringUtils.isEmpty(request.getParameter("yearTechAlterDetailIds"))) {
			//获取请求中从年度计划中选择的技术改造明细ID
			this.yearTechAlterDetailIds = request.getParameter("yearTechAlterDetailIds");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		//当页面点击从年度计划中选择按钮,则从年度计划的工装制作明细中创建季度计划的工装制作明细
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
	 * 从年度计划的技术改造明细中创建季度计划的技术改造明细
	 * @return
	 */
	public String createQuarterPlanFromYearPlan() {
		this.techAlterDetailManager.storeQuarterPlanTechAlterDetail(this.quarterPlan, this.yearTechAlterDetailIds);
		return SUCCESS;
	}
	
	/**
	 * 删除页面选中的技术改造明细
	 * @return SUCCESS
	 */
	public String delete() {
	    //删除季度计划所关联的技术改造明细,如果技术改造明细已被制作为采购单，则不能删除
		try {
			this.techAlterDetailManager.deleteAllTechAlterDetailOfQuarterPlan(this.techAlterDetails, this.quarterPlan);
		} catch (Exception e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {                                        //如果是季度计划
		return "quarterTechAlterDetails";
	}
	
	/**
	 * 往查询技术改造详细的hql语句中设置年度计划或季度计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();                                          //如果是季度计划
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
	
	//获取超出年度预算的费用
	public Double getOverBudgetFee() {
		if (null != this.quarterPlan) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.TECH_ALTER.toString());
			if (null != budgetDetail) {
				if (budgetDetail.getQuarterPlanFee() > budgetDetail.getFee()) {
					return budgetDetail.getQuarterPlanFee() - budgetDetail.getFee();
				}
			}
		}
		return null;
	}

}
