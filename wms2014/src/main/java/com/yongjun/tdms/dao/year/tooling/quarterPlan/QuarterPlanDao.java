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
package com.yongjun.tdms.dao.year.tooling.quarterPlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;

/**
 * 
 * <p>Title: QuarterPlanDao
 * <p>Description: 季度计划数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface QuarterPlanDao {
	/**
	 * 根据传入的季度计划ID,获取季度计划
	 * @param quarterPlanId 季度计划ID
	 * @return QuarterPlan 季度计划实体
	 */
	QuarterPlan loadQuarterPlan(Long quarterPlanId);
	
	/**
	 * 根据传入的季度计划ID集合,获取集合季度计划
	 * @param quarterPlanIds 季度计划ID集合
	 * @return List 集合季度计划
	 */
	List<QuarterPlan> loadAllQuarterPlans(Long [] quarterPlanIds);
	
	/**
	 * 获取集合季度计划
	 * @return List 集合季度计划
	 */
	List<QuarterPlan> loadAllQuarterPlans();
	
	/**
	 * 保存季度计划
	 * @param quarterPlan 季度计划
	 */
	void storeQuarterPlan(QuarterPlan quarterPlan);
	
	/**
	 * 删除季度计划
	 * @param quarterPlan 季度计划
	 */
	void deleteQuarterPlan(QuarterPlan quarterPlan);
	
	/**
	 * 根据传入的季度计划集合,删除集合季度计划
	 * @param quarterPlans 季度计划集合
	 */
	void deleteAllQuarterPlans(Collection<QuarterPlan> quarterPlans);
	QuarterPlan loadQuarterPlanByDepartIdAndYearAndQuarterId(Long departmentId,String year,String  qarterTypeId);
	List loadQuarterPlanListByDepartIdAndYearAndQuarterId(Long departmentId,String year,String  qarterTypeId);
	List<QuarterPlan> loadListQuarterPlanByDepartIdAndYearAndQuarterId(Long departmentId, String year, String qarterTypeId);
	
}
