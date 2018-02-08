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
package com.yongjun.tdms.dao.year.budget;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.budget.BudgetDetail;

/**
 * 
 * <p>Title: BudgetDetailDao
 * <p>Description: 年度预算详细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface BudgetDetailDao {
	/**
	 * 根据传入的年度预算详细ID,获取年度预算详细
	 * @param budgetDetailId 年度预算详细ID
	 * @return BudgetDetail 年度预算详细实体
	 */
	BudgetDetail loadBudgetDetail(Long budgetDetailId);
	
	/**
	 * 根据传入的年度预算详细ID集合,获取集合年度预算详细
	 * @param budgetDetailIds 年度预算详细ID集合
	 * @return List 集合年度预算详细
	 */
	List<BudgetDetail> loadAllBudgetDetails(Long [] budgetDetailIds);
	
	/**
	 * 获取集合年度预算详细
	 * @return List 集合年度预算详细
	 */
	List<BudgetDetail> loadAllBudgetDetails();
	
	/**
	 * 保存年度预算详细
	 * @param BudgetDetail 年度预算详细
	 */
	void storeBudgetDetail(BudgetDetail budgetDetail);
	
	/**
	 * 删除年度预算详细
	 * @param BudgetDetail 年度预算详细
	 */
	void deleteBudgetDetail(BudgetDetail budgetDetail);
	
	/**
	 * 根据传入的年度预算详细集合,删除集合年度预算详细
	 * @param BudgetDetails 年度预算详细集合
	 */
	void deleteAllBudgetDetails(Collection<BudgetDetail> budgetDetails);
	
	/**
	 * 获取所有有效记录的预算编号
	 * @return List 预算编号集合
	 */
	List<String> loadAllBudgetNoOfEnabled();
	
	/**
	 * 根据传入的部门id，预算id，获取预算明细
	 * @param departmentId 部门id
	 * @param budgetId 预算id
	 * @return BudgetDetail 预算明细实体
	 */
	BudgetDetail getBudgetDetailByDepartmentAndBudget(Long departmentId, Long budgetId, String budgetNo);
	
	/**
	 * 根据传入的部门id，预算编号，年度，获取预算明细
	 * @param departmentId 部门id
	 * @param budgetNo 预算编号
	 * @param year 年度
	 * @return BudgetDetail 预算明细
	 */
	BudgetDetail getYearBudgetByDepartmentAndBudgetNoAndYear(Long departmentId, String budgetNo, String year);
	
	/**
	 * 根据年度预算编号获取年度预算
	 * @param budgetNo 年度预算编号
	 * @return BudgetDetail 年度预算
	 */
	BudgetDetail getYearBudgetDetailByBudgetNo(String budgetNo);
}
