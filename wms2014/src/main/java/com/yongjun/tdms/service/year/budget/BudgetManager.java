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
package com.yongjun.tdms.service.year.budget;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: BudgetManager
 * <p>Description: 年度预算业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface BudgetManager {
	/**
	 * 根据传入的年度预算ID,获取年度预算
	 * @param budgetId 年度预算ID
	 * @return Budget 年度预算实体
	 */
	Budget loadBudget(Long budgetId);
	
	/**
	 * 根据传入的年度预算ID集合,获取集合年度预算
	 * @param budgetIds 年度预算ID集合
	 * @return List 集合年度预算
	 */
	List<Budget> loadAllBudgets(Long [] budgetIds);
	
	/**
	 * 获取集合年度预算
	 * @return List 集合年度预算
	 */
	List<Budget> loadAllBudgets();
	
	/**
	 * 保存年度预算
	 * @param budget 年度预算
	 */
	@Transactional
	void storeBudget(Budget budget);
	
	/**
	 * 删除年度预算
	 * @param budget 年度预算
	 */
	@Transactional
	void deleteBudget(Budget budget);
	
	/**
	 * 根据传入的年度预算集合,删除集合年度预算
	 * @param budgets 年度预算集合
	 */
	@Transactional
	void deleteAllBudgets(Collection<Budget> budgets);
	
	/**
	 * 根据传入的年度预算集合,失效集合年度预算
	 * @param budgets 年度预算集合
	 */
	@Transactional
	void disabledAllBudgets(Collection<Budget> budgets);
	
	/**
	 * 根据传入的年度预算集合,有效集合年度预算
	 * @param budgets 年度预算集合
	 */
	@Transactional
	void enabledAllBudgets(Collection<Budget> budgets);
	
	/**
	 * 根据传入的年度预算,计算该年度预算中的总费用
	 * @param budget 年度预算
	 */
	@Transactional
	void calculateBudgetAllFee(Budget budget);
	
	/**
	 * 根据传入的年度计划,生成年度预算
	 * @param yearPlan
	 */
	@Transactional
	void generateYearBudgetByYearPlans(Collection<YearPlan> yearPlan, String year);
	
	/**
	 * 根据传入的年度计划，从相关的年度预算中取消该年度计划的费用
	 * @param yearPlan 年度计划
	 */
	@Transactional
	void cancelYearBudgetByYearPlan(YearPlan yearPlan);
	

}
