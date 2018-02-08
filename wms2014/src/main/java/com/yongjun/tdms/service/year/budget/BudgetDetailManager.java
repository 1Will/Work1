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

/**
 * 
 * <p>Title: BudgetDetailManager
 * <p>Description: 年度预算详细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface BudgetDetailManager {
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
	@Transactional
	void storeBudgetDetail(BudgetDetail budgetDetail);
	
	/**
	 * 删除年度预算详细
	 * @param budget 年度预算
	 * @param BudgetDetail 年度预算详细
	 */
	@Transactional
	void deleteBudgetDetail(BudgetDetail budgetDetail);
	
	/**
	 * 根据传入的年度预算详细集合,删除集合年度预算详细
	 * @param BudgetDetails 年度预算详细集合
	 */
	@Transactional
	void deleteAllBudgetDetails(Budget budget, Collection<BudgetDetail> budgetDetails);
	
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
	 * 通过部门，年度获取年度预算明细
	 * @param dept 部门
	 * @param year 年度
	 * @return BudgetDetail 
	 */
	BudgetDetail getBudgetDetail(Department dept, String year, String detailType);
	
	/**
	 * 根据年度预算编号获取年度预算
	 * @param budgetNo 年度预算编号
	 * @return BudgetDetail 年度预算
	 */
	BudgetDetail getYearBudgetDetailByBudgetNo(String budgetNo);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的维修费用减去fee
	 * @param budgetNo 预算编号
	 * @param fee  要减去的费用
	 */
	@Transactional
	void removeRepairFeeFromBudgetDetail(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的维修费用加上fee
	 * @param budgetNo 预算编号
	 * @param fee 要加的费用
	 */
	@Transactional
	void addRepairFeeFromBudgetDetail(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的工装采购单费用 | 设备申购单费用减去fee
	 * @param budgetNo  预算编号
	 * @param fee 工装采购单费用 | 设备申购单费用
	 */
	@Transactional
	void removeParchaseFeeFromBudgetDetail(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的工装采购单费用 | 设备申购单费用加上fee
	 * @param budgetNo 预算编号
	 * @param fee 工装采购单费用 | 设备申购单费用
	 */
	@Transactional
	void addParchaseFeeFromBudgetDetail(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的工装采购合同费用 | 设备采购单费用减去fee
	 * @param budgetNo  预算编号
	 * @param fee 工装采购合同费用 | 设备采购单费用
	 */
	@Transactional
	void removeParchaseContractFeeFromBudgetDetail(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的工装采购合同费用 | 设备采购单费用加上fee
	 * @param budgetNo 预算编号
	 * @param fee 工装采购合同费用 | 设备采购单费用
	 */
	@Transactional
	void addParchaseContractFeeFromBudgetDetail(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的已发生费用减去fee
	 * @param budgetNo 预算编号
	 * @param fee  减去的费用
	 */
	@Transactional
	void removeOccurFeeFromBudgetDetailByBudgetNo(String budgetNo, Double fee);
	
	/**
	 * 根据传入的预算编号获取年度预算，并把该年度预算的已发生费用加上fee
	 * @param budgetNo 预算编号
	 * @param fee 加上的费用
	 */
	@Transactional
	void addOccurFeeFromBudgetDetailByBudgetNo(String budgetNo, Double fee);
}
