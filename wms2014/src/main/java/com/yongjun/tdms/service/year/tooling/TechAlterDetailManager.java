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
 * <p>Title: TechAlterDetailManager
 * <p>Description: 技术改造明细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface TechAlterDetailManager {
	/**
	 * 根据传入的技术改造明细ID,获取技术改造明细
	 * @param techAlterDetailId 技术改造明细ID
	 * @return TechAlterDetail 技术改造明细实体
	 */
	TechAlterDetail loadTechAlterDetail(Long techAlterDetailId);
	
	/**
	 * 根据传入的技术改造明细ID集合,获取技术改造明细集合
	 * @param techAlterDetailIds 技术改造明细ID集合
	 * @return List 技术改造明细集合
	 */
	List<TechAlterDetail> loadAllTechAlterDetails(Long [] techAlterDetailIds);
	
	/**
	 * 获取技术改造明细集合
	 * @return List 技术改造明细集合
	 */
	List<TechAlterDetail> loadAllTechAlterDetails();
	
	/**
	 * 保存传入的技术改造明细,并更新年度计划或季度计划的总费用
	 * @param techAlterDetail 技术改造明细实体
	 */
	@Transactional
	void storeTechAlterDetail(TechAlterDetail techAlterDetail);
	
	/**
	 * 删除传入的技术改造明细
	 * @param techAlterDetail 技术改造明细实体
	 */
	@Transactional
	void deleteTechAlterDetail(TechAlterDetail techAlterDetail);
	
	/**
	 * 删除传入的集合技术改造明细,并更新年度计划的总费用
	 * @param techAlterDetails 集合技术改造明细
	 * @param yearPlan 年度计划
	 * @exception 当该明细已制作为季度计划时,则抛出异常
	 */
	@Transactional
	void deleteAllTechAlterDetailOfYearPlan(Collection<TechAlterDetail> techAlterDetails, YearPlan yearPlan)throws Exception;
	
	/**
	 * 删除传入的集合技术改造明细,并更新季度计划的总费用
	 * @param techAlterDetails 集合技术改造明细
	 * @param yearPlan 季度计划
	 */
	@Transactional
	void deleteAllTechAlterDetailOfQuarterPlan(Collection<TechAlterDetail> techAlterDetails, QuarterPlan quarterPlan);
	
	/**
	 * 从年度计划的技术改造明细中创建季度计划的技术改造明细
	 * @param quarterPlan 季度计划
	 * @param addYearTechAlterDetailIds 年度计划的技术改造明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanTechAlterDetail(QuarterPlan quarterPlan, String addYearTechAlterDetailIds );
	
	/**
	 * 设置年度计划的技术改造明细的状态为列入季度计划状态
	 * @param yearTechAlterDetail 年度计划的技术改造明细
	 */
	@Transactional
	void setCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(TechAlterDetail yearTechAlterDetail);
	
	/**
	 * 设置年度计划的技术改造明细的状态为未列入季度计划状态
	 * @param yearTechAlterDetail 年度计划的技术改造明细
	 */
	void setNotCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(TechAlterDetail yearTechAlterDetail);
	
	/**
	 * 计算技术改造明细所有费用
	 * @param yearTechAlterDetails 技术改造明细集合
	 * @return
	 */
	double calculateTechAlterDetailFee(Collection<TechAlterDetail> yearTechAlterDetails);
}
