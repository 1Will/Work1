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
package com.yongjun.tdms.service.year.budget.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.year.budget.BudgetDao;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.budget.BudgetManager;
import com.yongjun.tdms.service.year.tooling.RepairMaintenanceDetailManager;
import com.yongjun.tdms.service.year.tooling.SparePurchaseDetailManager;
import com.yongjun.tdms.service.year.tooling.TechAlterDetailManager;
import com.yongjun.tdms.service.year.tooling.ToolingMakeDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;

/**
 * 
 * <p>Title: DefaultBudgetManager
 * <p>Description: 年度预算业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.budget.BudgetManager
 * @version $Id:$
 */
public class DefaultBudgetManager implements BudgetManager {
	
	private final BudgetDao budgetDao;
	private final SequenceManager sequenceManager;
//	private final ToolingMakeDetailManager toolingMakeDetailManager;
//	private final SparePurchaseDetailManager sparePurchaseDetailManager;
//	private final RepairMaintenanceDetailManager repairMaintenanceDetailManager;
//	private final TechAlterDetailManager techAlterDetailManager;
	private final YearPlanDetailManager yearPlanDetailManager;
	private final BudgetDetailManager budgetDetailManager;
	private final YearPlanDao yearPlanDao;
	
	private Properties resourceParameterConfiguration; 
	
	public DefaultBudgetManager(BudgetDao budgetDao, SequenceManager sequenceManager,
			YearPlanDetailManager yearPlanDetailManager,
			BudgetDetailManager budgetDetailManager,
			YearPlanDao yearPlanDao) {
//			ToolingMakeDetailManager toolingMakeDetailManager,
//			SparePurchaseDetailManager sparePurchaseDetailManager,
//			RepairMaintenanceDetailManager repairMaintenanceDetailManager,
//			TechAlterDetailManager techAlterDetailManager) {
		this.budgetDao = budgetDao;
		this.sequenceManager = sequenceManager;
		this.yearPlanDetailManager = yearPlanDetailManager;
		this.budgetDetailManager = budgetDetailManager;
		this.yearPlanDao = yearPlanDao;
//		this.toolingMakeDetailManager = toolingMakeDetailManager;
//		this.sparePurchaseDetailManager = sparePurchaseDetailManager;
//		this.repairMaintenanceDetailManager = repairMaintenanceDetailManager;
//		this.techAlterDetailManager = techAlterDetailManager;
	}
	
	public Budget loadBudget(Long budgetId) {
		return this.budgetDao.loadBudget(budgetId);
	}

	public List<Budget> loadAllBudgets(Long[] budgetIds) {
		return this.budgetDao.loadAllBudgets(budgetIds);
	}

	public List<Budget> loadAllBudgets() {
		return this.budgetDao.loadAllBudgets();
	}

	public void storeBudget(Budget budget) {
		if (budget.isNew()) {
			//自动生成年度预算编号
			String yearBudgetNo = (String)sequenceManager.generate("-");
			budget.setYearBudgetNo(yearBudgetNo);
		}
		this.budgetDao.storeBudget(budget);
	}

	public void deleteBudget(Budget budget) {
		this.budgetDao.deleteBudget(budget);
	}

	public void deleteAllBudgets(Collection<Budget> budgets) {
		this.budgetDao.deleteAllBudgets(budgets);
	}

	public void disabledAllBudgets(Collection<Budget> budgets) {
		for (Budget budget : budgets) {
			budget.setDisabled(true);                     //失效该年度预算
			this.budgetDao.storeBudget(budget);
		}
	}

	public void enabledAllBudgets(Collection<Budget> budgets) {
		for (Budget budget : budgets) {
			budget.setDisabled(false);                 //有效该年度预算
			this.budgetDao.storeBudget(budget);
		}
	}

	public void calculateBudgetAllFee(Budget budget) {
		double allFee = 0.0;                 //用累计费用
		for (BudgetDetail detail : budget.getBudgetDetail()) {
			//累计预算明细的费用
			allFee += detail.getFee().doubleValue();
		}
		//更新年度预算的总费用
		budget.setAllFee(Double.valueOf(allFee));
		this.budgetDao.storeBudget(budget);
	}

	public void generateYearBudgetByYearPlans(Collection<YearPlan> yearPlans, String year) {
		Budget budget = null;
		budget = this.budgetDao.getYearBudgetByYear(year);
		if (null == budget) {
			budget = new Budget();
			//生成年度预算
			generateBudgetContent(budget, year);
		}
		//生成年度预算明细
		generateBudgetDetailContent(budget,yearPlans);
		//计算预算明细总费用
		this.calculateBudgetAllFee(budget);

	}
	
	public void cancelYearBudgetByYearPlan(YearPlan yearPlan) {
		Budget budget = this.budgetDao.getYearBudgetByYear(yearPlan.getYear());
		if (null == budget) {
			return ;
		}
		
		Double toolingMakeAllFee = this.yearPlanDetailManager.getToolingMakeAllFee(yearPlan);          //工装制作总费用
		Double sparePurchaseAllFee = this.yearPlanDetailManager.getSparePurchaseAllFee(yearPlan);        //备件采购总费用
		Double repairMaintenanceAllFee = this.yearPlanDetailManager.getRepairMaintenanceAllFee(yearPlan);    //维修保养总费用
		Double techAlterAllFee = this.yearPlanDetailManager.getTechAlterAllFee(yearPlan);            //技术改造总费用
		
		if (null != budget) {
			if (null != toolingMakeAllFee) {           //清除工装制作费用
                //预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("toolingMakeDetailCode")
				                  + yearPlan.getYear();
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null != budgetDetail && budgetDetail.getFee()>0) {
					budgetDetail.setFee(budgetDetail.getFee()-toolingMakeAllFee);
					this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				}
				
			}
			if (null != sparePurchaseAllFee) {        //清除备件采购费用
                //预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("sparePurchaseDetailCode")
				                  + yearPlan.getYear();
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null != budgetDetail && budgetDetail.getFee()>0) {
					budgetDetail.setFee(budgetDetail.getFee()-sparePurchaseAllFee);
					this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				}
			}
			if (null != repairMaintenanceAllFee) {         //清除维修保养费用
                //预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("repairMaintenanceDetailCode")
				                  + yearPlan.getYear();
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null != budgetDetail && budgetDetail.getFee()>0) {
					budgetDetail.setFee(budgetDetail.getFee()-repairMaintenanceAllFee);	
					this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				}
			}
			if (null != techAlterAllFee) {                  //清除技术改造费用
                //预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("techAlterDetailCode")
				                  + yearPlan.getYear();
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null != budgetDetail && budgetDetail.getFee()>0) {
					budgetDetail.setFee(budgetDetail.getFee()-techAlterAllFee);
					this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				}
			}
		}
		//计算预算明细总费用
		this.calculateBudgetAllFee(budget);
	}
	
	private void generateBudgetContent(Budget budget, String year) {
		String budgetName = year + this.resourceParameterConfiguration.getProperty("year")
		                    + this.resourceParameterConfiguration.getProperty("JACCompanyName") + this.resourceParameterConfiguration.getProperty("yearBudgetName");
		budget.setName(budgetName);         //设置年度预算名称
		budget.setYear(year); //设置年度预算年度
		budget.setToolingDevFlag(SysModel.TOOLING); //设置工装/设备标识
		this.storeBudget(budget);
	}
	
	private void generateBudgetDetailContent(Budget budget, Collection<YearPlan> yearPlans) {
		for (YearPlan yearPlan : yearPlans) {
			if (yearPlan.isApproveFlag()) {
				continue;
			}
			Double toolingMakeAllFee = this.yearPlanDetailManager.getToolingMakeAllFee(yearPlan);          //工装制作总费用
			Double sparePurchaseAllFee = this.yearPlanDetailManager.getSparePurchaseAllFee(yearPlan);        //备件采购总费用
			Double repairMaintenanceAllFee = this.yearPlanDetailManager.getRepairMaintenanceAllFee(yearPlan);    //维修保养总费用
			Double techAlterAllFee = this.yearPlanDetailManager.getTechAlterAllFee(yearPlan);            //技术改造总费用
			//生成各部门的工装制造明细预算
			if (null != toolingMakeAllFee) {
				//预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("toolingMakeDetailCode")
				                  + yearPlan.getYear();
				//从根据部门,预算的id和预算编号，到预算明细表中查询是否有预算明细对象，没有则创建新的预算明细对象
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null == budgetDetail) {
					budgetDetail = new BudgetDetail();
				}
				//设置年度预算明细编号
				budgetDetail.setBudgetNo(budgetNo);  
				//设置年度预算明细名称
				budgetDetail.setBudgetName(yearPlan.getDeptName() + this.getResourceParameterConfiguration().getProperty("toolingMakeDetailName"));
				//设置年度预算明细部门
				budgetDetail.setDepartment(yearPlan.getDepartment());
				//设置年度预算明细费用
				budgetDetail.setFee(budgetDetail.getFee() + toolingMakeAllFee);
				budgetDetail.setBudget(budget);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				//设置年度预算明细与年度预算关联
				budget.getBudgetDetail().add(budgetDetail);
			
			}
			//生成各部门的备件采购明细预算
			if (null != sparePurchaseAllFee) {
				//预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("sparePurchaseDetailCode")
				                  + yearPlan.getYear();
				//从根据部门,预算的id和预算编号，到预算明细表中查询是否有预算明细对象，没有则创建新的预算明细对象
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null == budgetDetail) {
					budgetDetail = new BudgetDetail();
				}
				//设置年度预算明细编号
				budgetDetail.setBudgetNo(budgetNo);  
				//设置年度预算明细名称
				budgetDetail.setBudgetName(yearPlan.getDeptName() + this.getResourceParameterConfiguration().getProperty("sparePurchaseDetailName"));
				//设置年度预算明细部门
				budgetDetail.setDepartment(yearPlan.getDepartment()); 
				//设置年度预算明细费用
				budgetDetail.setFee(budgetDetail.getFee() + sparePurchaseAllFee);
				budgetDetail.setBudget(budget);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				//设置年度预算明细与年度预算关联
				budget.getBudgetDetail().add(budgetDetail);
			}
			//生成各部门的维修保养明细预算
			if (null != repairMaintenanceAllFee) {
				//预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("repairMaintenanceDetailCode")
				                  + yearPlan.getYear();
				//从根据部门,预算的id和预算编号，到预算明细表中查询是否有预算明细对象，没有则创建新的预算明细对象
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null == budgetDetail) {
					budgetDetail = new BudgetDetail();
				}
				//设置年度预算明细编号
				budgetDetail.setBudgetNo(budgetNo);  
				//设置年度预算明细名称
				budgetDetail.setBudgetName(yearPlan.getDeptName() + this.getResourceParameterConfiguration().getProperty("repairMaintenanceDetailName"));
				//设置年度预算明细部门
				budgetDetail.setDepartment(yearPlan.getDepartment()); 
				//设置年度预算明细费用
				budgetDetail.setFee(budgetDetail.getFee() + repairMaintenanceAllFee);
				budgetDetail.setBudget(budget);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				//设置年度预算明细与年度预算关联
				budget.getBudgetDetail().add(budgetDetail);
			
			}
			//生成各部门的技术改造明细预算
			if (null != techAlterAllFee) {
				//预算编号
				String budgetNo = yearPlan.getDepartment().getCode() + this.getResourceParameterConfiguration().getProperty("techAlterDetailCode")
				                  + yearPlan.getYear();
				//从根据部门,预算的id和预算编号，到预算明细表中查询是否有预算明细对象，没有则创建新的预算明细对象
				BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetailByDepartmentAndBudget(
						yearPlan.getDepartment().getId(), budget.getId(),budgetNo);
				if (null == budgetDetail) {
					budgetDetail = new BudgetDetail();
				}
				//设置年度预算明细编号
				budgetDetail.setBudgetNo(budgetNo);  
				//设置年度预算明细名称
				budgetDetail.setBudgetName(yearPlan.getDeptName() + this.getResourceParameterConfiguration().getProperty("techAlterDetailName"));
				//设置年度预算明细部门
				budgetDetail.setDepartment(yearPlan.getDepartment()); 
				//设置年度预算明细费用
				budgetDetail.setFee(budgetDetail.getFee() + techAlterAllFee);
				budgetDetail.setBudget(budget);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				//设置年度预算明细与年度预算关联
				budget.getBudgetDetail().add(budgetDetail);
			}
			yearPlan.setApproveFlag(true);           //标识该年度计划已生成年度预算
			this.yearPlanDao.storeYearPlan(yearPlan);
		}
		this.budgetDao.storeBudget(budget);
		
	}
	
	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}

	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}


	

}
