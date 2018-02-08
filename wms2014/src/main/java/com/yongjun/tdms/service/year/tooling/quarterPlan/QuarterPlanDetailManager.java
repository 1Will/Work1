/**
 * 
 */
package com.yongjun.tdms.service.year.tooling.quarterPlan;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;

/**
 * @author zhangzhibao
 *
 */
@Transactional(readOnly=true)
public interface QuarterPlanDetailManager {
	/**
	 * 根据传入的季度计划明细ID,获取季度计划明细
	 * @param quarterPlanDetailId 季度计划明细ID
	 * @return quarterPlanDetail 季度计划明细实体
	 */
	QuarterPlanDetail loadQuarterPlanDetail(Long quarterPlanDetailId);
	
	/**
	 * 根据传入的季度计划明细ID集合,获取集合季度计划明细
	 * @param quarterPlanDetailIds 季度计划明细ID集合
	 * @return List 集合季度计划明细
	 */
	List<QuarterPlanDetail> loadAllQuarterPlanDetails(Long [] quarterPlanDetailIds);
	
	/**
	 * 获取集合季度计划明细
	 * @return List 集合季度计划明细
	 */
	List<QuarterPlanDetail> loadAllQuarterPlanDetails();
	
	/**
	 * 保存季度计划明细
	 * @param quarterPlanDetail 季度计划明细
	 */
	@Transactional
	void storeQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail);
	
	/**
	 * 删除季度计划明细
	 * @param QuarterPlanDetail 季度计划明细
	 */
	@Transactional
	void deleteQuarterPlanDetail(QuarterPlanDetail QuarterPlanDetail);
	
	/**
	 * 根据传入的季度计划明细集合,删除集合季度计划明细
	 * @param quarterPlanDetails 季度计划明细集合
	 */
	@Transactional
	void deleteAllQuarterPlanDetails(Collection<QuarterPlanDetail> quarterPlanDetails);
	
	/**
	 * 从年度计划的工装制作明细中创建季度计划的工装制作明细
	 * @param quarterPlan 季度计划
	 * @param addYearToolingQuarterPlanDetailIds  年度计划的工装制作明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanToolingMakeDetail(QuarterPlan quarterPlan, String addYearToolingQuarterPlanDetailIds);
	
	/**
	 * 删除传入的季度计划的集合中的工装制作详细，并更新季度计划的总费用
	 * @param toolingMakeDetails 工装制作详细集合
	 * @param quarterPlan 季度计划
	 */
	@Transactional
	void deleteAllToolingMakeDetailOfQuarterPlan(Collection<QuarterPlanDetail> toolingMakeDetails, QuarterPlan quarterPlan)throws Exception;
	
	/**
	 * 保存季度计划中的备件采购明细
	 * @param quarterPlanDetail
	 */
	@Transactional
	void storeQuarterPlanSparePurchaseDetail(QuarterPlanDetail quarterPlanDetail);
	
	/**
	 * 从年度计划的备件采购明细中创建季度计划的备件采购明细
	 * @param quarterPlan 季度计划
	 * @param addYearSparePurchaseDetailIds 年度计划的备件采购明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanSparePurchaseDetail(QuarterPlan quarterPlan, String addYearSparePurchaseDetailIds);
	
	/**
	 * 更新备件采购明细的数量，需求日期，需求原因，备注的值
	 * @param quarterPlan 季度计划
	 * @param allSparePurchaseDetailIds 备件采购明细id
	 * @param allNumbers 数量
	 * @param allRequestDates 需求日期
	 * @param allRequestReasons 需求原因
	 * @param allComments 备注
	 */
	@Transactional
	void storeSpareForQuarterPlan(QuarterPlan quarterPlan, String allSparePurchaseDetailIds, String allNumbers, String allRequestDates,
			String allRequestReasons, String allComments,String allUnitPrices);
	
	/**
	 * 为季度计划的备件采购添加新的备件
	 * @param quarterPlan 季度计划
	 * @param spareIds 新的备件ID
	 */
	@Transactional
	void storeNewAddSpareForQuarterPlan(QuarterPlan quarterPlan, String spareIds);
	
	/**
	 * 删除传入季度计划的集合备件采购明细,并更新季度计划的总费用
	 * @param sparePurchaseDetails 集合备件采购明细
	 * @param yearPlan 季度计划
	 */
	@Transactional
	void deleteAllSparePurchaseDetailOfQuarterPlan(Collection<QuarterPlanDetail> sparePurchaseDetails, 
			QuarterPlan quarterPlan) throws Exception;
	
	/**
	 * 从年度计划的维修保养明细中创建季度计划的维修保养明细
	 * @param quarterPlan 季度计划
	 * @param addRepairMaintenanceDetailIds  年度计划的维修保养明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanRepairMaintenanceDetail(QuarterPlan quarterPlan,String addRepairMaintenanceDetailIds);
	
	/**
	 * 删除传入的集合维修保养明细,并更新季度计划的总费用
	 * @param repairMaintenanceDetails 集合维修保养明细
	 * @param yearPlan 年度计划
	 */
	@Transactional
	void deleteAllRepairMaintenanceDetailOfQuarterPlan(Collection<QuarterPlanDetail> repairMaintenanceDetails,
			QuarterPlan yearPlan)throws Exception;
	
	/**
	 * 从年度计划的技术改造明细中创建季度计划的技术改造明细
	 * @param quarterPlan 季度计划
	 * @param addYearTechAlterDetailIds 年度计划的技术改造明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanTechAlterDetail(QuarterPlan quarterPlan, String addYearTechAlterDetailIds );
	
	/**
	 * 删除传入的集合技术改造明细,并更新季度计划的总费用
	 * @param techAlterDetails 集合技术改造明细
	 * @param yearPlan 季度计划
	 */
	@Transactional
	void deleteAllTechAlterDetailOfQuarterPlan(Collection<QuarterPlanDetail> techAlterDetails, 
			QuarterPlan quarterPlan)throws Exception;
	
	/**
	 * 设置年度计划的明细的状态为列入季度计划状态
	 * @param yearPlanDetail 年度计划的明细
	 */
	@Transactional
	void setCreatedQuartePlanStatusForYearPlanDetail(YearPlanDetail yearPlanDetail);
	
	/**
	 * 设置年度计划的明细的状态为未列入季度计划状态
	 * @param yearPlanDetail 年度计划的明细
	 */
	@Transactional
	void setNotCreatedQuartePlanStatusForYearPlanDetail(YearPlanDetail yearPlanDetail);
}
