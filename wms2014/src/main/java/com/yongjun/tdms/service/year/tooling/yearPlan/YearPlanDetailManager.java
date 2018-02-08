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
package com.yongjun.tdms.service.year.tooling.yearPlan;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;

/**
 * <p>Title: YearPlanDetailManager
 * <p>Description: 年度计划明细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface YearPlanDetailManager {
	/**
	 * 根据传入的年度计划明细ID,获取年度计划明细
	 * @param yearPlanDetailId 年度计划明细ID
	 * @return YearPlanDetail 年度计划明细实体
	 */
	YearPlanDetail loadYearPlanDetail(Long yearPlanDetailId);
	
	/**
	 * 根据传入的年度计划明细ID集合,获取集合年度计划明细
	 * @param yearPlanDetailIds 年度计划明细ID集合
	 * @return List 集合年度计划明细
	 */
	List<YearPlanDetail> loadAllYearPlanDetails(Long [] yearPlanDetailIds);
	
	/**
	 * 获取集合年度计划明细
	 * @return List 集合年度计划明细
	 */
	List<YearPlanDetail> loadAllYearPlanDetails();
	
	/**
	 * 保存年度计划明细
	 * @param yearPlanDetail 年度计划明细
	 */
	@Transactional
	void storeYearPlanDetail(YearPlanDetail yearPlanDetail);
	
	/**
	 * 删除年度计划明细
	 * @param yearPlanDetail 年度计划明细
	 */
	@Transactional
	void deleteYearPlanDetail(YearPlanDetail yearPlanDetail);
	
	/**
	 * 根据传入的年度计划明细集合,删除集合年度计划明细
	 * @param yearPlanDetails 年度计划明细集合
	 */
	@Transactional
	void deleteAllYearPlanDetails(Collection<YearPlanDetail> yearPlanDetails);
	
	/**
	 * 为年度计划的备件采购添加新的备件
	 * @param yearPlan 年度计划
	 * @param spareIds 新的备件ID
	 */
	@Transactional
	void storeNewAddSpareForYearPlan(YearPlan yearPlan, String spareIds);
	
	/**
	 * 更新备件采购明细的数量，需求日期，需求原因，备注的值
	 * @param allSparePurchaseDetailIds 备件采购明细id
	 * @param allNumbers 数量
	 * @param allRequestDates 需求日期
	 * @param allRequestReasons 需求原因
	 * @param allComments 备注
	 */
	@Transactional
	void storeSpareForYearPlan(YearPlan yearPlan, String allSparePurchaseDetailIds, String allNumbers, String allRequestDates,
			String allRequestReasons, String allComments,String allUnitPrices);
	
	/**
	 * 删除传入的年度计划的集合中的工装制作详细，并更新年度计划的总费用
	 * @param toolingMakeDetails 工装制作详细集合
	 * @param yearPlan 年度计划
	 */
	@Transactional
	void deleteAllToolingMakeDetailOfYearPlan(Collection<YearPlanDetail> toolingMakeDetails,
			YearPlan yearPlan) throws Exception;

	/**
	 * 删除传入年度计划的集合备件采购明细,并更新年度计划的总费用
	 * @param sparePurchaseDetails 集合备件采购明细
	 * @param yearPlan 年度计划
	 * @exception 当改明细已被制作季度计划,抛出异常
	 */
	@Transactional
	void deleteAllSparePurchaseDetailOfYearPlan(Collection<YearPlanDetail> sparePurchaseDetails, 
			YearPlan yearPlan)throws Exception;
	
	/**
	 * 删除传入的集合维修保养明细,并更新年度计划的总费用
	 * @param repairMaintenanceDetails 集合维修保养明细
	 * @param yearPlan 年度计划
	 * @exception 如果该明细已被制作季度计划,则抛出异常
	 */
	@Transactional
	void deleteAllRepairMaintenanceDetailOfYearPlan(Collection<YearPlanDetail> repairMaintenanceDetails, 
			YearPlan yearPlan)throws Exception;
	
	/**
	 * 删除传入的集合技术改造明细,并更新年度计划的总费用
	 * @param techAlterDetails 集合技术改造明细
	 * @param yearPlan 年度计划
	 * @exception 当该明细已制作为季度计划时,则抛出异常
	 */
	@Transactional
	void deleteAllTechAlterDetailOfYearPlan(Collection<YearPlanDetail> techAlterDetails, 
			YearPlan yearPlan)throws Exception;
	
	/**
	 * 根据传入的年度计划id，年度计划明细类别，获取该类计划明细的所有费用
	 * @param detailType
	 * @param yearPlanId
	 * @return
	 */
	Double getAllPriceByDetailTypeAndYearPlanId(String detailType, Long yearPlanId);
	
	/**
	 * 根据传入的年度计划，获取该年度计划关联的工装制作的总费用
	 * @param yearPlan　年度计划
	 * @return　Double　工装制作的总费用
	 */
	Double getToolingMakeAllFee(YearPlan yearPlan);
	
	/**
	 * 根据传入的年度计划，获取该年度计划关联的备件采购的总费用
	 * @param yearPlan　年度计划
	 * @return　Double　备件采购的总费用
	 */
	Double getSparePurchaseAllFee(YearPlan yearPlan);
	
	/**
	 * 根据传入的年度计划，获取该年度计划关联的维修保养的总费用
	 * @param yearPlan　年度计划
	 * @return　Double　维修保养的总费用
	 */
	Double getRepairMaintenanceAllFee(YearPlan yearPlan);
	
	/**
	 * 根据传入的年度计划，获取该年度计划关联的技术改造的总费用
	 * @param yearPlan　年度计划
	 * @return　Double　技术改造的总费用
	 */
	Double getTechAlterAllFee(YearPlan yearPlan);
	
}
