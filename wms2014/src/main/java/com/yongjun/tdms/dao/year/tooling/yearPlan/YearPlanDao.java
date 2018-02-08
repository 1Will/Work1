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
package com.yongjun.tdms.dao.year.tooling.yearPlan;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: YearPlanDao
 * <p>Description: 年度计划数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */
public interface YearPlanDao {
	/**
	 * 根据传入的年度计划ID,获取年度计划
	 * @param yearPlanId 年度计划ID
	 * @return YearPlan 年度计划实体
	 */
	YearPlan loadYearPlan(Long yearPlanId);
	
	/**
	 * 根据传入的年度计划ID集合,获取集合年度计划
	 * @param yearPlanIds 年度计划ID集合
	 * @return List 集合年度计划
	 */
	List<YearPlan> loadAllYearPlans(Long [] yearPlanIds);
	
	/**
	 * 获取集合年度计划
	 * @return List 集合年度计划
	 */
	List<YearPlan> loadAllYearPlans();
	
	/**
	 * 保存年度计划
	 * @param yearPlan 年度计划
	 */
	void storeYearPlan(YearPlan yearPlan);
	
	/**
	 * 删除年度计划
	 * @param yearPlan 年度计划
	 */
	void deleteYearPlan(YearPlan yearPlan);
	
	/**
	 * 根据传入的年度计划集合,删除集合年度计划
	 * @param yearPlans 年度计划集合
	 */
	void deleteAllYearPlans(Collection<YearPlan> yearPlans);
	
	List loadYearPlan(String[] array)throws HibernateException;
	YearPlan loadYearPlanByDepartNameANDYear(Long departmentId,String year);
	
}
