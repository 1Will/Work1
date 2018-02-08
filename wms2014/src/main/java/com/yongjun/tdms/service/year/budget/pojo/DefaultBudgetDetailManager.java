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
import com.yongjun.tdms.dao.year.budget.BudgetDetailDao;
import com.yongjun.tdms.model.year.budget.Budget;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.budget.BudgetManager;

/**
 * 
 * <p>Title: DefaultBudgetDetailManager
 * <p>Description: 年度预算明细业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.budget.BudgetDetailManager
 * @version $Id:$
 */
public class DefaultBudgetDetailManager implements BudgetDetailManager {
	
	private final BudgetDetailDao budgetDetailDao;
	//private final BudgetManager budgetManager;
	private Properties resourceParameterConfiguration;
	
	public DefaultBudgetDetailManager(BudgetDetailDao budgetDetailDao) {
		this.budgetDetailDao = budgetDetailDao;
		//this.budgetManager = budgetManager;
	}
	
	public BudgetDetail loadBudgetDetail(Long budgetDetailId) {
		return this.budgetDetailDao.loadBudgetDetail(budgetDetailId);
	}

	public List<BudgetDetail> loadAllBudgetDetails(Long[] budgetDetailIds) {
		return this.budgetDetailDao.loadAllBudgetDetails(budgetDetailIds);
	}

	public List<BudgetDetail> loadAllBudgetDetails() {
		return this.budgetDetailDao.loadAllBudgetDetails();
	}

	public void storeBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetailDao.storeBudgetDetail(budgetDetail);
	
		//计算年度预算明细的总费用
		//this.budgetManager.calculateBudgetAllFee(budgetDetail.getBudget());
	}

	public void deleteBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetailDao.deleteBudgetDetail(budgetDetail);
	}

	public void deleteAllBudgetDetails(Budget budget, Collection<BudgetDetail> budgetDetails) {
		this.budgetDetailDao.deleteAllBudgetDetails(budgetDetails);
		//计算年度预算明细的总费用
		//this.budgetManager.calculateBudgetAllFee(budget);
	}

	public List<String> loadAllBudgetNoOfEnabled() {
		return this.budgetDetailDao.loadAllBudgetNoOfEnabled();
	}

	public BudgetDetail getBudgetDetailByDepartmentAndBudget(Long departmentId, Long budgetId, String budgetNo) {
		return this.budgetDetailDao.getBudgetDetailByDepartmentAndBudget(departmentId, budgetId, budgetNo);
	}
	
	public BudgetDetail getBudgetDetail(Department dept, String year, String detailType) {
		BudgetDetail budgetDetail = null;
		String budgetNo = "";
		if (detailType.equals(YearPlanDetailCategory.TOOLING_MAKE.toString())) {      //计算工装制作列入季度计划的费用
			//预算编号
			budgetNo = dept.getCode() + this.getResourceParameterConfiguration().getProperty("toolingMakeDetailCode")
            + year;
		}
		if (detailType.equals(YearPlanDetailCategory.SPARE_PURCHASE.toString())) {      //计算备件采购列入季度计划的费用
			//预算编号
			budgetNo = dept.getCode() + this.getResourceParameterConfiguration().getProperty("sparePurchaseDetailCode")
            + year;
		}
		if (detailType.equals(YearPlanDetailCategory.REPAIR_MAINTENANCE.toString())) {      //计算维修保养列入季度计划的费用
			//预算编号
			budgetNo = dept.getCode() + this.getResourceParameterConfiguration().getProperty("repairMaintenanceDetailCode")
            + year;
		}
		if (detailType.equals(YearPlanDetailCategory.TECH_ALTER.toString())) {      //计算技术改造列入季度计划的费用
			//预算编号
			budgetNo = dept.getCode() + this.getResourceParameterConfiguration().getProperty("techAlterDetailCode")
            + year;
		}
		budgetDetail = this.budgetDetailDao.getYearBudgetByDepartmentAndBudgetNoAndYear(dept.getId(), budgetNo, year);
		return budgetDetail;
	}

	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}

	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}

	public BudgetDetail getYearBudgetDetailByBudgetNo(String budgetNo) {
		return this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
	}

	public void removeRepairFeeFromBudgetDetail(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {
			if (null != detail.getRepairFee() && detail.getRepairFee() > 0) {
				detail.setRepairFee(detail.getRepairFee() - fee);	
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	}

	public void addRepairFeeFromBudgetDetail(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {
			if (null != detail.getRepairFee() && detail.getRepairFee() > 0) {
				detail.setRepairFee(detail.getRepairFee() + fee);	
			} else {
				detail.setRepairFee(fee);
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	
	}

	public void removeParchaseFeeFromBudgetDetail(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {
			if (null != detail.getPurchaseFee() && detail.getPurchaseFee() > 0) {
				detail.setPurchaseFee(detail.getPurchaseFee() - fee);	
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	}

	public void addParchaseFeeFromBudgetDetail(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {
			if (null != detail.getPurchaseFee() && detail.getPurchaseFee() > 0) {
				detail.setPurchaseFee(detail.getPurchaseFee() + fee);	
			} else {
				detail.setPurchaseFee(fee);
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	
		
	}

	public void removeParchaseContractFeeFromBudgetDetail(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {
			if (null != detail.getPurchaseContractFee() && detail.getPurchaseContractFee() > 0) {
				detail.setPurchaseContractFee(detail.getPurchaseContractFee() - fee);	
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	}

	public void addParchaseContractFeeFromBudgetDetail(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {
			if (null != detail.getPurchaseContractFee() && detail.getPurchaseContractFee() > 0) {
				detail.setPurchaseContractFee(detail.getPurchaseContractFee() + fee);	
			} else {
				detail.setPurchaseContractFee(fee);
			}
			this.budgetDetailDao.storeBudgetDetail(detail);		
		}
		
	}

	public void removeOccurFeeFromBudgetDetailByBudgetNo(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {    //如果预算明细存在，则把该预算明细的已发生费用减去fee
			if (null != detail.getOccurFee() && detail.getOccurFee() > 0) {
				detail.setOccurFee(detail.getOccurFee() - fee);
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	}

	public void addOccurFeeFromBudgetDetailByBudgetNo(String budgetNo, Double fee) {
		BudgetDetail detail = this.budgetDetailDao.getYearBudgetDetailByBudgetNo(budgetNo);
		if (null != detail && null != fee) {    //如果预算明细存在，则把该预算明细的已发生费用加上fee
			if (null != detail.getOccurFee() && detail.getOccurFee() > 0) {
				detail.setOccurFee(detail.getOccurFee() + fee);	
			} else {
				detail.setOccurFee(fee);
			}
			this.budgetDetailDao.storeBudgetDetail(detail);
		}
	}
	
	
	
}
