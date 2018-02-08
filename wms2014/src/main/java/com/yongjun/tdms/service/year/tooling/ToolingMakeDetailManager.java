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

import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;


/**
 * 
 * <p>Title: ToolingMakeDetailManager
 * <p>Description: 工装制作详细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
@Transactional(readOnly=true)
public interface ToolingMakeDetailManager {
	/**
	 * 根据传入的工装制作详细ID,获取工装制作详细
	 * @param toolingMakeDetailId 工装制作详细ID
	 * @return ToolingMakeDetail 工装制作详细实体
	 */
	ToolingMakeDetail loadToolingMakeDetail(Long toolingMakeDetailId);
	
	/**
	 * 根据传入的工装制作详细ID集合,获取集合工装制作详细
	 * @param toolingMakeDetailIds 工装制作详细ID集合
	 * @return List 集合工装制作详细
	 */
	List<ToolingMakeDetail> loadAllToolingMakeDetails(Long [] toolingMakeDetailIds);
	
	/**
	 * 获取集合工装制作详细
	 * @return List 集合工装制作详细
	 */
	List<ToolingMakeDetail> loadAllToolingMakeDetails();
	
	/**
	 * 保存传入的工装制作详细
	 * @param toolingMakeDetail 工装制作详细实体
	 */
	@Transactional
	void storeToolingMakeDetail(ToolingMakeDetail toolingMakeDetail);
	
	/**
	 * 删除传入的工装制作详细
	 * @param toolingMakeDetail 工装制作详细
	 */
	@Transactional
	void deleteToolingMakeDetail(ToolingMakeDetail toolingMakeDetail);
	
	/**
	 * 删除传入的年度计划的集合中的工装制作详细，并更新年度计划的总费用
	 * @param toolingMakeDetails 工装制作详细集合
	 * @param yearPlan 年度计划
	 */
	@Transactional
	void deleteAllToolingMakeDetailOfYearPlan(Collection<ToolingMakeDetail> toolingMakeDetails, YearPlan yearPlan) throws Exception;
	
	/**
	 * 删除传入的季度计划的集合中的工装制作详细，并更新季度计划的总费用
	 * @param toolingMakeDetails 工装制作详细集合
	 * @param quarterPlan 季度计划
	 */
	@Transactional
	void deleteAllToolingMakeDetailOfQuarterPlan(Collection<ToolingMakeDetail> toolingMakeDetails, QuarterPlan quarterPlan);
	
	/**
	 * 从年度计划的工装制作明细中创建季度计划的工装制作明细
	 * @param quarterPlan 季度计划
	 * @param addYearToolingQuarterPlanDetailIds  年度计划的工装制作明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanToolingMakeDetail(QuarterPlan quarterPlan, String addYearToolingQuarterPlanDetailIds);
	
	/**
	 * 设置年度计划的工装制作明细的状态为列入季度计划状态
	 * @param yearTechAlterDetail 年度计划的工装制作明细
	 */
	@Transactional
	void setCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(ToolingMakeDetail yearToolingMakeDetail);
	
	/**
	 * 设置年度计划的工装制作明细的状态为未列入季度计划状态
	 * @param yearTechAlterDetail 年度计划的工装制作明细
	 */
	void setNotCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(ToolingMakeDetail yearToolingMakeDetail);
	
	/**
	 * 锁定工装制作明细
	 * @param yearToolingMakeDetails 年度计划的工装制作明细集合
	 */
	@Transactional
	void lockedToolingMakeDetail(Collection<ToolingMakeDetail> yearToolingMakeDetails);
	
	/**
	 * 解锁工装制作明细
	 * @param yearToolingMakeDetails 年度计划的工装制作明细集合
	 */
	@Transactional
	void ubLockedToolingMakeDetail(Collection<ToolingMakeDetail> yearToolingMakeDetails);
	
	/**
	 * 计算工装制作明细所有费用
	 * @param yearToolingMakeDetails 工装制作明细集合
	 * @return
	 */
	double CalculateToolingMakeDetailFee(Collection<ToolingMakeDetail> yearToolingMakeDetails);
	
}
