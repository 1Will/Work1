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

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.year.tooling.RepairMaintenanceDetailDao;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.year.tooling.RepairMaintenanceDetailManager;

/**
 * 
 * <p>Title: DefaultRepairMaintenanceDetailManager
 * <p>Description: 维修保养明细处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.tooling.RepairMaintenanceDetailManager
 * @version $Id:$
 */
public class DefaultRepairMaintenanceDetailManager extends CalculateAllFeeManager implements
		RepairMaintenanceDetailManager {
	private final RepairMaintenanceDetailDao repairMaintenanceDetailDao;
	
	public DefaultRepairMaintenanceDetailManager(RepairMaintenanceDetailDao repairMaintenanceDetailDao) {
		this.repairMaintenanceDetailDao = repairMaintenanceDetailDao;
	}
	
	public RepairMaintenanceDetail loadRepairMaintenanceDetail(
			Long repairMaintenanceDetailId) {
		return this.repairMaintenanceDetailDao.loadRepairMaintenanceDetail(repairMaintenanceDetailId);
	}

	public List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails(
			Long[] repairMaintenanceDetailIds) {
		return this.repairMaintenanceDetailDao.loadAllRepairMaintenanceDetails(repairMaintenanceDetailIds);
	}

	public List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails() {
		return this.repairMaintenanceDetailDao.loadAllRepairMaintenanceDetails();
	}

	public void storeRepairMaintenanceDetail(
			RepairMaintenanceDetail repairMaintenanceDetail) {
		this.repairMaintenanceDetailDao.storeRepairMaintenanceDetail(repairMaintenanceDetail);
		if (null != repairMaintenanceDetail.getYearPlan()) {
			//计算年度计划的总费用
			this.calulateAllFeeForYearPlan(repairMaintenanceDetail.getYearPlan());
		} else {
			//计算季度计划的总费用
			this.calulateAllFeeForQuarterPlan(repairMaintenanceDetail.getQuarterPlan());
		}
	}

	public void deleteRepairMaintenanceDetail(
			RepairMaintenanceDetail repairMaintenanceDetail) {
		this.repairMaintenanceDetailDao.deleteRepairMaintenanceDetail(repairMaintenanceDetail);
	}

	public void deleteAllRepairMaintenanceDetailOfYearPlan (
			Collection<RepairMaintenanceDetail> repairMaintenanceDetails, YearPlan yearPlan) throws Exception{
		//如果明细已被制作季度计划,抛出异常
		for (RepairMaintenanceDetail detail : repairMaintenanceDetails) {
			if (null != detail.getQuarterRepairMaintenanceDetail() && detail.getQuarterRepairMaintenanceDetail().size() > 0) {
				throw new Exception();
			}
		}
		this.repairMaintenanceDetailDao.deleteAllRepairMaintenanceDetail(repairMaintenanceDetails);
		 //清除年度计划关联的维修保养明细
		for (RepairMaintenanceDetail detail : repairMaintenanceDetails) {
			yearPlan.getRepairMaintenanceDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}

	public void deleteAllRepairMaintenanceDetailOfQuarterPlan(
			Collection<RepairMaintenanceDetail> repairMaintenanceDetails, QuarterPlan quarterPlan) {
		this.repairMaintenanceDetailDao.deleteAllRepairMaintenanceDetail(repairMaintenanceDetails);
		 //清除季度计划关联的维修保养明细
		for (RepairMaintenanceDetail detail : repairMaintenanceDetails) {
			if (null != detail.getYearRepairMaintenanceDetail()) {
				this.setNotCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(detail.getYearRepairMaintenanceDetail());
			}
			quarterPlan.getRepairMaintenanceDetails().remove(detail);
		}
		//更新季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	public void storeQuarterPlanRepairMaintenanceDetail(QuarterPlan quarterPlan, 
			String addRepairMaintenanceDetailIds) {
		String addRepairMaintenanceDetailId [] = null;
		if (null == addRepairMaintenanceDetailIds) {
			return ;
		}
		addRepairMaintenanceDetailId = addRepairMaintenanceDetailIds.split(",");
		//根据年度计划的维修保养明细ID,创建季度计划的维修保养明细
		for (int i=0; i<addRepairMaintenanceDetailId.length; i++) {
			RepairMaintenanceDetail quarterDetail = new RepairMaintenanceDetail();    //创建新的季度计划的维修保养明细
			//根据维修保养明细ID,获取年度计划的维修保养明细ID
			RepairMaintenanceDetail yearDetail = this.repairMaintenanceDetailDao.loadRepairMaintenanceDetail(Long.valueOf(addRepairMaintenanceDetailId[i]));
			//拷贝年度计划中的备件采购明细到季度计划的维修保养明细
			copyYearRepairMaintenanceDetailToQuarterRepairMaintenanceDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearRepairMaintenanceDetail(yearDetail);      //设置相关的年度计划中的维修保养明细
			this.repairMaintenanceDetailDao.storeRepairMaintenanceDetail(quarterDetail);    //保存季度计划维修保养明细
			
			//设置该年度计划的维修保养明细为已列入季度计划
			this.setCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(yearDetail);
			
			//季度计划添加维修保养明细
			quarterPlan.getRepairMaintenanceDetails().add(quarterDetail);
		}
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	/**
	 * 拷贝年度计划中的备件采购明细到季度计划的维修保养明细
	 */
	private void copyYearRepairMaintenanceDetailToQuarterRepairMaintenanceDetail(RepairMaintenanceDetail yearDetail,
			RepairMaintenanceDetail quarterDetail) {
		quarterDetail.setTooling(yearDetail.getTooling());             //拷贝工装
		quarterDetail.setPlanFee(yearDetail.getPlanFee());             //拷贝计划费用
		quarterDetail.setPlanFinishedDate(yearDetail.getPlanFinishedDate());    //拷贝计划完成日期
		quarterDetail.setReason(yearDetail.getReason());                        //拷贝原因
		quarterDetail.setComment(yearDetail.getComment());                      //拷贝备注
	}

	public void setCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(RepairMaintenanceDetail yearRepairMaintenanceDetail) {
		yearRepairMaintenanceDetail.setCreateQuarterFlag(true);
		this.repairMaintenanceDetailDao.storeRepairMaintenanceDetail(yearRepairMaintenanceDetail);
	}

	public void setNotCreatedQuartePlanStatusForRepairMaintenanceDetailOfYearPlan(RepairMaintenanceDetail yearRepairMaintenanceDetail) {
		yearRepairMaintenanceDetail.setCreateQuarterFlag(false);
		this.repairMaintenanceDetailDao.storeRepairMaintenanceDetail(yearRepairMaintenanceDetail);
	}

	public double calculateRepairMaintenanceDetailFee(Collection<RepairMaintenanceDetail> yearRepairMaintenanceDetails) {
		double allFee = 0.0;
		for (RepairMaintenanceDetail detail : yearRepairMaintenanceDetails) {
			allFee += detail.getPlanFee().doubleValue();
		}
		return allFee;
	}

}
