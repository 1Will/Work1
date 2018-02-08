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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDao;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDetailDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DailyRepair;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DeviceChange;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PivotalSpare;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PricisionCheck;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;

/**
 * <p>Title: CalculateFeeManager
 * <p>Description: 运维计划计算计划总费用,日常维修费用,精度检查费用,改造费用,关键备件费用,总费用的业务处理类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public abstract class CalculateFeeManager {
	private RunmaintainPlanDetailDao runmaintainPlanDetailDao;
	private RunmaintainPlanDao runmaintainPlanDao;
	
	public RunmaintainPlanDao getRunmaintainPlanDao() {
		return runmaintainPlanDao;
	}
	
	public void setRunmaintainPlanDao(RunmaintainPlanDao runmaintainPlanDao) {
		this.runmaintainPlanDao = runmaintainPlanDao;
	}
	
	public RunmaintainPlanDetailDao getRunmaintainPlanDetailDao() {
		return runmaintainPlanDetailDao;
	}
	
	public void setRunmaintainPlanDetailDao(
			RunmaintainPlanDetailDao runmaintainPlanDetailDao) {
		this.runmaintainPlanDetailDao = runmaintainPlanDetailDao;
	}
	
	/**
	 * 累计运维计划关联的运维计划明细的所有费用,并更新运维计划的计划总费用
	 * @param runmaintainPlan 运维计划
	 */
	public void calculateRunmaintainPlanAllFee(RunmaintainPlan runmaintainPlan) {
		double allFee = 0;                  //用来累计运维计划明细的所有费
		for (RunmaintainPlanDetail detail : runmaintainPlan.getRunmaintainPlanDetails()) {
			if (null != detail.getAllFee()) {
				//累计运维计划明细费用
				allFee += detail.getAllFee().doubleValue();
			}
		}
		//更新运维计划的计划总费用
		runmaintainPlan.setPlanAllFee(Double.valueOf(allFee));
		this.runmaintainPlanDao.storeRunmaintainPlan(runmaintainPlan);
	}
	
	/**
	 * 累计运维计划明细关联的日常维修费用
	 * @param runmaintainPlanDetail 运维计划明细
	 */
	public void calculateDailyRepairFee(RunmaintainPlanDetail runmaintainPlanDetail) {
		double dailyRepairFee = 0;              //用来累计日常维修的费用
		for (DailyRepair dailyRepair : runmaintainPlanDetail.getDailyRepairs()) {
			if (null != dailyRepair.getFee()) {
				//累计日常维修的费用
				dailyRepairFee += dailyRepair.getFee();
			}
		}
		//更新运维计划明细的日常维修总费用
		runmaintainPlanDetail.setDailyRepairFee(Double.valueOf(dailyRepairFee));
		//计算运维计划明细的各项费用总和
		calculcateRunmaintainPlanDetailAllFee(runmaintainPlanDetail);
		this.runmaintainPlanDetailDao.storeRunmaintainPlanDetail(runmaintainPlanDetail);
		//更新运维计划的计划总费用
		calculateRunmaintainPlanAllFee(runmaintainPlanDetail.getRunmaintainPlan());
	}
	
	/**
	 * 累计运维计划明细关联的精度检查费用
	 * @param runmaintainPlanDetail 运维计划明细
	 */
	public void calculatePricisionCheckFee(RunmaintainPlanDetail runmaintainPlanDetail) {
		double pricisionCheckFee = 0;                //用来累计精度检查的费用
		for (PricisionCheck pricisionCheck : runmaintainPlanDetail.getPricisionChecks()) {
			if (null != pricisionCheck.getFee()) {
				//累计精度检查的费用
				pricisionCheckFee += pricisionCheck.getFee().doubleValue();
			}
		}
		//更新运维计划明细的精度检查总费用
		runmaintainPlanDetail.setPricisionCheckFee(Double.valueOf(pricisionCheckFee));
		//计算运维计划明细的各项费用总和
		calculcateRunmaintainPlanDetailAllFee(runmaintainPlanDetail);
		this.runmaintainPlanDetailDao.storeRunmaintainPlanDetail(runmaintainPlanDetail);
		//更新运维计划的计划总费用
		calculateRunmaintainPlanAllFee(runmaintainPlanDetail.getRunmaintainPlan());
	}
	
	/**
	 * 累计运维计划明细关联的设备改造费用
	 * @param runmaintainPlanDetail 运维计划明细
	 */
	public void calculateChangeFee(RunmaintainPlanDetail runmaintainPlanDetail) {
		double changeFee = 0;                    //用来累计设备改造的费用
		for (DeviceChange deviceChange : runmaintainPlanDetail.getDeviceChanges()) {
			if (null != deviceChange.getFee()) {
				//累计设备改造的费用
				changeFee += deviceChange.getFee().doubleValue();
			}
		}
		//更新运维计划明细的设备改造总费用
		runmaintainPlanDetail.setChangeFee(Double.valueOf(changeFee));
		//计算运维计划明细的各项费用总和
		calculcateRunmaintainPlanDetailAllFee(runmaintainPlanDetail);
		this.runmaintainPlanDetailDao.storeRunmaintainPlanDetail(runmaintainPlanDetail);
		//更新运维计划的计划总费用
		calculateRunmaintainPlanAllFee(runmaintainPlanDetail.getRunmaintainPlan());
	}
	
	/**
	 * 累计运维计划明细的关键备件费用
	 * @param runmaintainPlanDetail 运维计划明细
	 */
	public void calculatePivotalSpareFee(RunmaintainPlanDetail runmaintainPlanDetail) {
		double pivotalSpareFee = 0;                //用来累计关键备件的费用
		for (PivotalSpare pivotalSpare : runmaintainPlanDetail.getPivotalSpares()) {
			if (null != pivotalSpare.getAllFee()) {
				//累计关键备件的费用
				pivotalSpareFee += pivotalSpare.getAllFee().doubleValue();
			}
		}
		//更新运维计划明细的关键备件总费用
		runmaintainPlanDetail.setPivotalSpareFee(Double.valueOf(pivotalSpareFee));
		//计算运维计划明细的各项费用总和
		calculcateRunmaintainPlanDetailAllFee(runmaintainPlanDetail);
		this.runmaintainPlanDetailDao.storeRunmaintainPlanDetail(runmaintainPlanDetail);
		//更新运维计划的计划总费用
		calculateRunmaintainPlanAllFee(runmaintainPlanDetail.getRunmaintainPlan());
	}
	
	//计算运维计划明细的各项费用总和
	private void calculcateRunmaintainPlanDetailAllFee(RunmaintainPlanDetail runmaintainPlanDetail) {
		double allFee = 0;                       //计算运维计划明细的各项费用总和
		allFee = runmaintainPlanDetail.getDailyRepairFee().doubleValue() + runmaintainPlanDetail.getPricisionCheckFee().doubleValue()
				+ runmaintainPlanDetail.getChangeFee().doubleValue() + runmaintainPlanDetail.getPivotalSpareFee().doubleValue();
		//更新总费用
		runmaintainPlanDetail.setAllFee(Double.valueOf(allFee));
	}
}
