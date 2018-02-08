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
package com.yongjun.tdms.service.year.tooling.quarterPlan.pojo;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.year.tooling.QuarterPlanDetailViewDao;
import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDao;
import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDetailDao;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.QuarterPlanDetailView;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.tooling.RepairMaintenanceDetailManager;
import com.yongjun.tdms.service.year.tooling.SparePurchaseDetailManager;
import com.yongjun.tdms.service.year.tooling.TechAlterDetailManager;
import com.yongjun.tdms.service.year.tooling.ToolingMakeDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;

/**
 * 
 * <p>Title: DefaultQuarterPlanManager
 * <p>Description: 季度计划业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager
 * @version $Id:$
 */
public class DefaultQuarterPlanManager implements QuarterPlanManager {
	
	private final QuarterPlanDao quarterPlanDao;
	private final SequenceManager sequenceManager;
	private final ToolingMakeDetailManager toolingMakeDetailManager;
	private final SparePurchaseDetailManager sparePurchaseManager;
	private final RepairMaintenanceDetailManager repairMaintenanceDetailManager;
	private final TechAlterDetailManager techAlterDetailManager;
	private final QuarterPlanDetailViewDao  quarterPlanDetailViewDao;
	private final QuarterPlanDetailDao quarterPlanDetailDao;
	
	private BudgetDetailManager budgetDetailManager;
	
	public DefaultQuarterPlanManager(QuarterPlanDao quarterPlanDao,
			SequenceManager sequenceManager,
			ToolingMakeDetailManager toolingMakeDetailManager,
			SparePurchaseDetailManager sparePurchaseManager,
			RepairMaintenanceDetailManager repairMaintenanceDetailManager,
			TechAlterDetailManager techAlterDetailManager,
			QuarterPlanDetailViewDao  quarterPlanDetailViewDao,
			QuarterPlanDetailDao quarterPlanDetailDao) {
		this.quarterPlanDao = quarterPlanDao;
		this.sequenceManager = sequenceManager;
		this.toolingMakeDetailManager = toolingMakeDetailManager;
		this.sparePurchaseManager = sparePurchaseManager;
		this.repairMaintenanceDetailManager = repairMaintenanceDetailManager;
		this.techAlterDetailManager = techAlterDetailManager;
		this.quarterPlanDetailViewDao=quarterPlanDetailViewDao;
		this.quarterPlanDetailDao = quarterPlanDetailDao;
	}
	
	public QuarterPlan loadQuarterPlan(Long quarterPlanId) {
		return this.quarterPlanDao.loadQuarterPlan(quarterPlanId);
	}

	public List<QuarterPlan> loadAllQuarterPlans(Long[] quarterPlanIds) {
		return this.quarterPlanDao.loadAllQuarterPlans(quarterPlanIds);
	}

	public List<QuarterPlan> loadAllQuarterPlans() {
		return this.quarterPlanDao.loadAllQuarterPlans();
	}

	public void storeQuarterPlan(QuarterPlan quarterPlan) {
		if (quarterPlan.isNew()) {
			String planNo = (String)sequenceManager.generate("-");
			quarterPlan.setPlanNo(planNo);
		}
		this.quarterPlanDao.storeQuarterPlan(quarterPlan);
	}

	public void deleteQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlanDao.deleteQuarterPlan(quarterPlan);
	}

	public void deleteAllQuarterPlans(Collection<QuarterPlan> quarterPlans) {
		this.quarterPlanDao.deleteAllQuarterPlans(quarterPlans);
	}

	public void disabledAllQuarterPlans(Collection<QuarterPlan> quarterPlans) {
		for (QuarterPlan quarterPlan : quarterPlans) {
			quarterPlan.setDisabled(true);
			this.quarterPlanDao.storeQuarterPlan(quarterPlan);
			//从年度预算中移除列入季度计划的费用
			removeQuarterPlanFeeFromYearBudget(quarterPlan);
			//改变季度计划中各个明细中关联的年度计划中明细的列入季度计划的状态
			setNotCreatedQuarterPlanStatusForDetailOfYearPlan(quarterPlan);
			
		}
	}

	/**
	 * 从年度预算中移除列入季度计划的费用
	 * @param quarterPlan 季度计划
	 */
	private void removeQuarterPlanFeeFromYearBudget(QuarterPlan quarterPlan) {
		// 按照季度计划明细类型获取季度计划明细工装制作费用
		Double toolingMakeFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TOOLING_MAKE.toString().trim());
		// 按照季度计划明细类型获取季度计划明细维修保养费用
		Double repairMaintenanceFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString().trim());
		//按照季度计划明细类型获取季度计划明细备件制作费用
		Double sparePurchaseFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//按照季度计划明细类型获取季度计划明细技术改造费用
		Double techAlterFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TECH_ALTER.toString().trim());
		
		//移除工装制作费用
		if (null != toolingMakeFee && toolingMakeFee >0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.TOOLING_MAKE.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
					&& (toolingMakeFee != null && toolingMakeFee > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-toolingMakeFee);
			}
		}
		//移除维修保养费用
		if (null != repairMaintenanceFee && repairMaintenanceFee > 0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.REPAIR_MAINTENANCE.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-repairMaintenanceFee);
			}
		}
		//移除备件采购费用
		if (null != sparePurchaseFee && sparePurchaseFee>0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.SPARE_PURCHASE.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-sparePurchaseFee);
			}
		}
		//移除技术改造费用
		if (null != techAlterFee && techAlterFee > 0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.TECH_ALTER.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-techAlterFee);
			}
		}
	}
	public void enabledAllQuarterPlans(Collection<QuarterPlan> quarterPlans) throws Exception {
		for (QuarterPlan quarterPlan : quarterPlans) {
			//当有效一条季度计划时，查询出其中有没有有效的记录
			QuarterPlan quarterPlanOfEnabled = (QuarterPlan) quarterPlanDao.
												loadQuarterPlanByDepartIdAndYearAndQuarterId(
														 quarterPlan.getDepartment().getId(),
														 quarterPlan.getYear(),
														 quarterPlan.getQarterType().toString());
			//如果有有效的季度计划，则抛出异常显示提示信息
			if(quarterPlanOfEnabled !=null){
				//当同时选择好多条季度计划来有效时，则也抛出异常提示，并将所有选中季度的状态还置为无效
				for (QuarterPlan quarterPlanOfDisabled : quarterPlans){
					quarterPlanOfDisabled.setDisabled(true);
				}
				throw new Exception();
			//没有有效的季度计划，就把选中的一条季度计划有效
			}else{
				quarterPlan.setDisabled(false);
				this.quarterPlanDao.storeQuarterPlan(quarterPlan);
				//从年度预算中移除加入季度计划的费用
				addQuarterPlanFeeFromYearBudget(quarterPlan);
				//改变季度计划中各个明细中关联的年度计划中明细的为已列入季度计划的状态
				setCreatedQuarterPlanStatusForDetailOfYearPlan(quarterPlan);
			}
		}

	}
	/**
	 * 从年度预算中移除加入季度计划的费用
	 * @param quarterPlan 季度计划
	 */
	private void addQuarterPlanFeeFromYearBudget(QuarterPlan quarterPlan) {
		// 按照季度计划明细类型获取季度计划明细工装制作费用
		Double toolingMakeFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TOOLING_MAKE.toString().trim());
		// 按照季度计划明细类型获取季度计划明细维修保养费用
		Double repairMaintenanceFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString().trim());
		//按照季度计划明细类型获取季度计划明细备件制作费用
		Double sparePurchaseFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//按照季度计划明细类型获取季度计划明细技术改造费用
		Double techAlterFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TECH_ALTER.toString().trim());
		
		//移除工装制作费用
		if (null != toolingMakeFee && toolingMakeFee >0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.TOOLING_MAKE.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
					&& (toolingMakeFee != null && toolingMakeFee > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()+toolingMakeFee);
			}
		}
		//移除维修保养费用
		if (null != repairMaintenanceFee && repairMaintenanceFee > 0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.REPAIR_MAINTENANCE.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()+repairMaintenanceFee);
			}
		}
		//移除备件采购费用
		if (null != sparePurchaseFee && sparePurchaseFee>0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.SPARE_PURCHASE.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()+sparePurchaseFee);
			}
		}
		//移除技术改造费用
		if (null != techAlterFee && techAlterFee > 0) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.TECH_ALTER.toString());	
			//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
			if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()+techAlterFee);
			}
		}
	}
    //改变季度计划中各个明细中关联的年度计划中明细的为未列入季度计划的状态
	private void setNotCreatedQuarterPlanStatusForDetailOfYearPlan(QuarterPlan quarterPlan) {
		for (ToolingMakeDetail detail : quarterPlan.getToolingMakeDetails()) {
			if (null != detail.getYearToolingMakeDetail()) {
				//改变该明细关联的年度计划关联的工装制作明细的为未列入季度计划
				this.toolingMakeDetailManager.setNotCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(detail.getYearToolingMakeDetail());
			}
		}
		for (SparePurchaseDetail detail : quarterPlan.getSparePurchaseDetails()) {
			if (null != detail.getYearSparePurchaseDetail()) {
				//改变该明细关联的年度计划关联的备件采购明细的为未列入季度计划
				this.sparePurchaseManager.setNotCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(detail.getYearSparePurchaseDetail());
			}
		}
		for (RepairMaintenanceDetail detail : quarterPlan.getRepairMaintenanceDetails()) {
			if (null != detail.getYearRepairMaintenanceDetail()) {
				//改变该明细关联的年度计划关联的维修保养明细的为未列入季度计划
				this.repairMaintenanceDetailManager.setNotCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(detail.getYearRepairMaintenanceDetail());
			}
		}
		for (TechAlterDetail detail : quarterPlan.getTechAlterDetails()) {
			if (null != detail.getYearTechAlterDetail()) {
				//改变该明细关联的年度计划关联的技术改造明细的为未列入季度计划
				this.techAlterDetailManager.setNotCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(detail.getYearTechAlterDetail());
			}
		}
	}
	
	//改变季度计划中各个明细中关联的年度计划中明细的为已列入季度计划的状态
	private void setCreatedQuarterPlanStatusForDetailOfYearPlan(QuarterPlan quarterPlan) {
		for (ToolingMakeDetail detail : quarterPlan.getToolingMakeDetails()) {
			if (null != detail.getYearToolingMakeDetail()) {
				//改变该明细关联的年度计划关联的工装制作明细的为列入季度计划
				this.toolingMakeDetailManager.setCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(detail.getYearToolingMakeDetail());
			}
		}
		for (SparePurchaseDetail detail : quarterPlan.getSparePurchaseDetails()) {
			if (null != detail.getYearSparePurchaseDetail()) {
				//改变该明细关联的年度计划关联的备件采购明细的为列入季度计划
				this.sparePurchaseManager.setCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(detail.getYearSparePurchaseDetail());
			}
		}
		for (RepairMaintenanceDetail detail : quarterPlan.getRepairMaintenanceDetails()) {
			if (null != detail.getYearRepairMaintenanceDetail()) {
				//改变该明细关联的年度计划关联的维修保养明细的为列入季度计划
				this.repairMaintenanceDetailManager.setCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(detail.getYearRepairMaintenanceDetail());
			}
		}
		for (TechAlterDetail detail : quarterPlan.getTechAlterDetails()) {
			if (null != detail.getYearTechAlterDetail()) {
				//改变该明细关联的年度计划关联的技术改造明细的为列入季度计划
				this.techAlterDetailManager.setCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(detail.getYearTechAlterDetail());
			}
		}
	}

	public List<QuarterPlanDetailView> loadAllQuarterPlanDetailByQuarterPlanId(Long QuarterPlanId) {
		return this.quarterPlanDetailViewDao.loadQuarterPlanDetail(QuarterPlanId);
	}

	public List<QuarterPlanDetailView> loadQuarterPlanDetailView(String[] array) throws HibernateException {
		return quarterPlanDetailViewDao.loadQuarterPlanDetailView(array);
	}

	public QuarterPlan loadQuarterPlanByDepartIdAndYearAndQuarterId(Long departmentId, String year, String qarterTypeId) {
		
		return quarterPlanDao.loadQuarterPlanByDepartIdAndYearAndQuarterId(departmentId,year,qarterTypeId);
	}

	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}

	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}

	public List loadQuarterPlanListByDepartIdAndYearAndQuarterId(Long departmentId, String year, String qarterTypeId) {
	
		return quarterPlanDao.loadQuarterPlanListByDepartIdAndYearAndQuarterId(departmentId,year,qarterTypeId);
	}

	public List<QuarterPlan> loadListQuarterPlanByDepartIdAndYearAndQuarterId(Long departmentId, String year, String qarterTypeId) {
		return quarterPlanDao.loadListQuarterPlanByDepartIdAndYearAndQuarterId(departmentId,year,qarterTypeId);
	}

}
