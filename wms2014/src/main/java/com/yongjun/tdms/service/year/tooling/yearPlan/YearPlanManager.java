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

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: YearPlanManager
 * <p>Description: 年度计划业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface YearPlanManager {
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
	@Transactional
	void storeYearPlan(YearPlan yearPlan) throws Exception;
	
	/**
	 * 删除年度计划
	 * @param yearPlan 年度计划
	 */
	@Transactional
	void deleteYearPlan(YearPlan yearPlan);
	
	/**
	 * 根据传入的年度计划集合,删除集合年度计划
	 * @param yearPlans 年度计划集合
	 */
	@Transactional
	void deleteAllYearPlans(Collection<YearPlan> yearPlans);
	
	/**
	 * 根据传入的年度计划集合,失效集合年度计划
	 * @param yearPlans 年度计划集合
	 */
	@Transactional
	void disabledAllYearPlans(Collection<YearPlan> yearPlans);
	
	/**
	 * 根据传入的年度计划集合,有效集合年度计划
	 * @param yearPlans 年度计划集合
	 */
	@Transactional
	void enabledAllYearPlans(Collection<YearPlan> yearPlans)throws Exception;
	
	List<YearPlanDetailView>loadAllYearPlanDetailByYearPlanId(Long yearPlanId);
	/**
	 * 锁定传入的年度计划集合
	 * @param yearPlans 年度计划集合
	 */
	@Transactional
	void lockedAllYearPlan(Collection<YearPlan> yearPlans);
	
	/**
	 * 解锁传入的年度计划集合
	 * @param yearPlans 年度计划集合
	 */
	@Transactional
	void unLockedAllYearPlan(Collection<YearPlan> yearPlans);
	@Transactional
	void generateYearDudget(Collection<YearPlan> yearPlans, String year);
	public List<YearPlanDetailView> loadYearPlanDetailView(String[] array) throws HibernateException;
	
	public YearPlan loadYearPlanByDepartNameANDYear(Long departmentId,String year);
	

	
} 
