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
package com.yongjun.tdms.service.year.tooling.pojo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDao;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDao;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;

/**
 * 
 * <p>Title: CalculateAllFeeManager
 * <p>Description: 计算年度计算年度计划或季度计划的总费用的抽象类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public abstract class CalculateAllFeeManager {
	protected transient final Log logger = LogFactory.getLog(this.getClass());
	
	//访问年度计划数据的接口类
	private YearPlanDao yearPlanDao;
    //访问季度计划数据的接口类
	private QuarterPlanDao quarterPlanDao;
	
	public QuarterPlanDao getQuarterPlanDao() {
		return quarterPlanDao;
	}
	public void setQuarterPlanDao(QuarterPlanDao quarterPlanDao) {
		this.quarterPlanDao = quarterPlanDao;
	}
	public YearPlanDao getYearPlanDao() {
		return yearPlanDao;
	}
	public void setYearPlanDao(YearPlanDao yearPlanDao) {
		this.yearPlanDao = yearPlanDao;
	}
	
	/**
	 * 计算工装制作明细，备件采购明细，维修保养明细，技术改造明细的所有费用和，
	 * 来更新年度计划的总费用
	 * @param yearPlan 年度计划
	 */
	public void calulateAllFeeForYearPlan(YearPlan yearPlan) {
		double allFee = 0.0;          //用来累计年度计划的总费用
//		if (null != yearPlan.getToolingMakeDetails()) {     //累计工装制作明细费用
//			for (ToolingMakeDetail detail : yearPlan.getToolingMakeDetails()) {
//				allFee += detail.getAllPrice().doubleValue();
//			}
//		}
//		if (null != yearPlan.getSparePurchaseDetails()) {  //累计备件采购明细费用
//			for (SparePurchaseDetail detail : yearPlan.getSparePurchaseDetails()) {
//				allFee += detail.getAllPrice().doubleValue();
//			}
//		}
//		if (null != yearPlan.getRepairMaintenanceDetails()) {    //累计维修保养明细费用
//			for (RepairMaintenanceDetail detail : yearPlan.getRepairMaintenanceDetails()) {
//				allFee += detail.getPlanFee().doubleValue();
//			}
//		}
//		if (null != yearPlan.getTechAlterDetails()) {           //累计技术改造明细费用
//			for (TechAlterDetail detail : yearPlan.getTechAlterDetails()) {
//				allFee += detail.getPlanFee().doubleValue();
//			}
//		}
		if (null != yearPlan.getYearPlanDetails()) {
			for (YearPlanDetail detail : yearPlan.getYearPlanDetails()) {
				allFee += detail.getAllPrice().doubleValue();
			}
		}
		//更新年度计划的总费用
		yearPlan.setPlanAllFee(Double.valueOf(allFee));
		this.yearPlanDao.storeYearPlan(yearPlan);
	}
	
	/**
	 * 计算工装制作明细，备件采购明细，维修保养明细，技术改造明细的所有费用和，
	 * 来更新季度计划的总费用
	 * @param quarterPlan
	 */
	public  QuarterPlan calulateAllFeeForQuarterPlan(QuarterPlan quarterPlan) {
		double allFee = 0.0;          //用来累计季度计划的总费用
//		if (null != quarterPlan.getToolingMakeDetails()) {     //累计工装制作明细费用
//			for (ToolingMakeDetail detail : quarterPlan.getToolingMakeDetails()) {
//				allFee += detail.getAllPrice().doubleValue();
//			}
//		}
//		if (null != quarterPlan.getSparePurchaseDetails()) {  //累计备件采购明细费用
//			for (SparePurchaseDetail detail : quarterPlan.getSparePurchaseDetails()) {
//				allFee += detail.getAllPrice().doubleValue();
//			}
//		}
//		if (null != quarterPlan.getRepairMaintenanceDetails()) {    //累计维修保养明细费用
//			for (RepairMaintenanceDetail detail : quarterPlan.getRepairMaintenanceDetails()) {
//				allFee += detail.getPlanFee().doubleValue();
//			}
//		}
//		if (null != quarterPlan.getTechAlterDetails()) {           //累计技术改造明细费用
//			for (TechAlterDetail detail : quarterPlan.getTechAlterDetails()) {
//				allFee += detail.getPlanFee().doubleValue();
//			}
//		}
		//计算季度计划明细总费用
		if (null != quarterPlan.getQuarterPlanDetails()) {
			for (QuarterPlanDetail detail : quarterPlan.getQuarterPlanDetails()) {
				allFee += detail.getAllPrice();
			}
		}
		//更新季度计划的总费用
		quarterPlan.setPlanAllFee(Double.valueOf(allFee));
		this.quarterPlanDao.storeQuarterPlan(quarterPlan);
		return quarterPlan;
		
	}
	
	
	
}
