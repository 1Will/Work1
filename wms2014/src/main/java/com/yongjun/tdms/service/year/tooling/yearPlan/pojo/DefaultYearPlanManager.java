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
package com.yongjun.tdms.service.year.tooling.yearPlan.pojo;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.year.tooling.YearPlanDetailViewDao;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDao;
import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.year.budget.BudgetManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * 
 * <p>Title: DefaultYearPlanManager
 * <p>Description: 年度计划业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager
 * @version $Id:$
 */
public class DefaultYearPlanManager implements YearPlanManager {

	private final YearPlanDao yearPlanDao;
	private final SequenceManager sequenceManager;
	private final YearPlanDetailViewDao yearPlanDetailDao;
	private final BudgetManager budgetManager;
	
	public DefaultYearPlanManager(YearPlanDao yearPlanDao,
			SequenceManager sequenceManager,
			YearPlanDetailViewDao yearPlanDetailDao,
			BudgetManager budgetManager) {
		this.yearPlanDao = yearPlanDao;
		this.sequenceManager = sequenceManager;
		this.yearPlanDetailDao = yearPlanDetailDao;
		this.budgetManager = budgetManager;
	}
	
	public YearPlan loadYearPlan(Long yearPlanId) {
		return this.yearPlanDao.loadYearPlan(yearPlanId);
	}

	public List<YearPlan> loadAllYearPlans(Long[] yearPlanIds) {
		return this.yearPlanDao.loadAllYearPlans(yearPlanIds);
	}

	public List<YearPlan> loadAllYearPlans() {
		return this.yearPlanDao.loadAllYearPlans();
	}

	public void storeYearPlan(YearPlan yearPlan) throws Exception{
	
		if (yearPlan.isNew()) {           //如果是新建年度计划,则生成计划编号
			String planNo = (String)sequenceManager.generate("-");
			yearPlan.setPlanNo(planNo);
			
		}
		this.yearPlanDao.storeYearPlan(yearPlan);
	}

	public void deleteYearPlan(YearPlan yearPlan) {
		this.yearPlanDao.deleteYearPlan(yearPlan);
	}

	public void deleteAllYearPlans(Collection<YearPlan> yearPlans) {
		this.yearPlanDao.deleteAllYearPlans(yearPlans);
	}

	public void disabledAllYearPlans(Collection<YearPlan> yearPlans) {
		for (YearPlan yearPlan : yearPlans) {
			yearPlan.setDisabled(true);              //设置disabled等于true,表示失效改记录
			this.yearPlanDao.storeYearPlan(yearPlan);
		}
	}

	public void enabledAllYearPlans(Collection<YearPlan> yearPlans) throws Exception {
		for (YearPlan yearPlan : yearPlans) {
			YearPlan yearPlanOfEnabled = yearPlanDao.loadYearPlanByDepartNameANDYear(yearPlan.getDepartment().getId(),yearPlan.getYear());
			if(yearPlanOfEnabled !=null){
				for(YearPlan yearPlanOfDisabled : yearPlans){
					yearPlanOfDisabled.setDisabled(true);
				}
				
				throw new Exception();
			}else{
				yearPlan.setDisabled(false);              //设置disabled等于false,表示有效改记录
				this.yearPlanDao.storeYearPlan(yearPlan);
			}
		}
	}

	public List<YearPlanDetailView> loadAllYearPlanDetailByYearPlanId(Long yearPlanId) {
		return this.yearPlanDetailDao.loadYearPlanDetail(yearPlanId);
	}

	public void lockedAllYearPlan(Collection<YearPlan> yearPlans) {
		for (YearPlan yearPlan : yearPlans) {
			yearPlan.setLockedFlag(true);      //锁定年度计划
			this.yearPlanDao.storeYearPlan(yearPlan);
		}
	}

	public void unLockedAllYearPlan(Collection<YearPlan> yearPlans) {
		for (YearPlan yearPlan : yearPlans) {
			yearPlan.setLockedFlag(false);      //解锁年度计划
			if (yearPlan.isApproveFlag()) {        //该年度计划已经生成了年度预算
				this.budgetManager.cancelYearBudgetByYearPlan(yearPlan);     //从年度预算中去掉相关的解锁的年度计划
			}
			yearPlan.setApproveFlag(false);       //标识该年度计划没有生成年度预算
			this.yearPlanDao.storeYearPlan(yearPlan);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List loadYearPlan(String[] array)throws HibernateException {
		return yearPlanDao.loadYearPlan(array);
	}
	
	public void generateYearDudget(Collection<YearPlan> yearPlans, String year) {
		this.budgetManager.generateYearBudgetByYearPlans(yearPlans, year);
	}

	public List<YearPlanDetailView> loadYearPlanDetailView(String[] array) throws HibernateException {
		
		return yearPlanDetailDao.loadYearPlanDetailView(array);
	}

	public YearPlan loadYearPlanByDepartNameANDYear(Long departmentId, String year) {
		 
		return yearPlanDao.loadYearPlanByDepartNameANDYear(departmentId,year);
	}
	
	
}
