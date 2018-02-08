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
package com.yongjun.tdms.service.year.tooling;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: RepairMaintenanceDetailManager
 * <p>Description: 维修保养明细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface RepairMaintenanceDetailManager {
	/**
	 * 根据传入的维修保养明细ID,获取维修保养明细
	 * @param repairMaintenanceDetailId 维修保养明细ID
	 * @return RepairMaintenanceDetail 维修保养明细实体
	 */
	RepairMaintenanceDetail loadRepairMaintenanceDetail(Long repairMaintenanceDetailId);
	
	/**
	 * 根据传入的维修保养明细ID集合,获取维修保养明细集合
	 * @param repairMaintenanceDetailIds 维修保养明细ID集合
	 * @return List 维修保养明细集合
	 */
	List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails(Long [] repairMaintenanceDetailIds);
	
	/**
	 * 获取维修保养明细集合
	 * @return List 维修保养明细集合
	 */
	List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails();
	
	/**
	 * 保存传入的维修保养明细,并更新年度计划或季度计划的总费用
	 * @param repairMaintenanceDetail 维修保养明细实体
	 */
	@Transactional
	void storeRepairMaintenanceDetail(RepairMaintenanceDetail repairMaintenanceDetail);
	
	/**
	 * 删除传入的维修保养明细
	 * @param repairMaintenanceDetail 维修保养明细实体
	 */
	@Transactional
	void deleteRepairMaintenanceDetail(RepairMaintenanceDetail repairMaintenanceDetail);
	
	/**
	 * 删除传入的集合维修保养明细,并更新年度计划的总费用
	 * @param repairMaintenanceDetails 集合维修保养明细
	 * @param yearPlan 年度计划
	 * @exception 如果该明细已被制作季度计划,则抛出异常
	 */
	@Transactional
	void deleteAllRepairMaintenanceDetailOfYearPlan(Collection<RepairMaintenanceDetail> repairMaintenanceDetails, YearPlan yearPlan)throws Exception;
	
	/**
	 * 删除传入的集合维修保养明细,并更新季度计划的总费用
	 * @param repairMaintenanceDetails 集合维修保养明细
	 * @param yearPlan 年度计划
	 */
	@Transactional
	void deleteAllRepairMaintenanceDetailOfQuarterPlan(Collection<RepairMaintenanceDetail> repairMaintenanceDetails, QuarterPlan yearPlan);
	
	/**
	 * 从年度计划的维修保养明细中创建季度计划的维修保养明细
	 * @param quarterPlan 季度计划
	 * @param addRepairMaintenanceDetailIds  年度计划的维修保养明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanRepairMaintenanceDetail(QuarterPlan quarterPlan,String addRepairMaintenanceDetailIds);
	
	/**
	 * 设置年度计划的维修保养明细的状态为列入季度计划状态
	 * @param yearRepairMaintenanceDetail 年度计划的维修保养明细
	 */
	@Transactional
	void setCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(RepairMaintenanceDetail yearRepairMaintenanceDetail);
	
	/**
	 * 设置年度计划的维修保养明细的状态为未列入季度计划状态
	 * @param yearRepairMaintenanceDetail 年度计划的维修保养明细
	 */
	void setNotCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(RepairMaintenanceDetail yearRepairMaintenanceDetail);

	/**
	 * 计算维修保养明细所有费用
	 * @param yearRepairMaintenanceDetails 维修保养明细集合
	 * @return
	 */
	double calculateRepairMaintenanceDetailFee(Collection<RepairMaintenanceDetail> yearRepairMaintenanceDetails);
}
