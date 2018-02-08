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

import com.yongjun.tdms.dao.year.tooling.ToolingMakeDetailDao;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.year.tooling.ToolingMakeDetailManager;

/**
 * 
 * <p>Title: DefaultToolingMakeDetailManager
 * <p>Description: 工装制作详细业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.tooling.yearPlan.ToolingMakeDetailManager
 * @version $Id:$
 */
public class DefaultToolingMakeDetailManager extends CalculateAllFeeManager implements
		ToolingMakeDetailManager {
	private final ToolingMakeDetailDao toolingMakeDetailDao;
	
	public DefaultToolingMakeDetailManager(ToolingMakeDetailDao toolingMakeDetailDao) {
		this.toolingMakeDetailDao = toolingMakeDetailDao;
	}
	
	public ToolingMakeDetail loadToolingMakeDetail(Long toolingMakeDetailId) {
		return this.toolingMakeDetailDao.loadToolingMakeDetail(toolingMakeDetailId);
	}

	public List<ToolingMakeDetail> loadAllToolingMakeDetails(
			Long[] toolingMakeDetailIds) {
		return this.toolingMakeDetailDao.loadAllToolingMakeDetails(toolingMakeDetailIds);
	}

	public List<ToolingMakeDetail> loadAllToolingMakeDetails() {
		return this.toolingMakeDetailDao.loadAllToolingMakeDetails();
	}

	public void storeToolingMakeDetail(ToolingMakeDetail toolingMakeDetail) {
		this.toolingMakeDetailDao.storeToolingMakeDetail(toolingMakeDetail);
		if (null != toolingMakeDetail.getYearPlan()) {
			//计算年度计划的总费用
			this.calulateAllFeeForYearPlan(toolingMakeDetail.getYearPlan());
		} else {
			//计算季度计划的总费用
			this.calulateAllFeeForQuarterPlan(toolingMakeDetail.getQuarterPlan());
		}
	}

	public void deleteToolingMakeDetail(ToolingMakeDetail toolingMakeDetail) {
		this.toolingMakeDetailDao.deleteToolingMakeDetail(toolingMakeDetail);
	}

	public void deleteAllToolingMakeDetailOfQuarterPlan(
			Collection<ToolingMakeDetail> toolingMakeDetails, QuarterPlan quarterPlan) {
		//删除传入工装制作明细
		this.toolingMakeDetailDao.deleteAllToolingMakeDetail(toolingMakeDetails);
        //清除季度计划关联的工装制作明细,以及改变该明细关联的年度计划的工装制作明细为未列入季度计划状态
		for (ToolingMakeDetail detail : toolingMakeDetails) {
			if (null != detail.getYearToolingMakeDetail()) {
				this.setNotCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(detail.getYearToolingMakeDetail());
			}
			quarterPlan.getToolingMakeDetails().remove(detail);
		}
		//更新季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	public void deleteAllToolingMakeDetailOfYearPlan(Collection<ToolingMakeDetail> toolingMakeDetails, 
			YearPlan yearPlan) throws Exception{
		//如果该明细已制定季度明细,抛出异常,
		for (ToolingMakeDetail detail : toolingMakeDetails) {
			if (null != detail.getQuarterToolingMakeDetail() && detail.getQuarterToolingMakeDetail().size()>0) {
				throw new Exception();
			}
		}
		//删除传入工装制作明细
		this.toolingMakeDetailDao.deleteAllToolingMakeDetail(toolingMakeDetails);
		//清除年度计划关联的工装制作明细
		for (ToolingMakeDetail detail : toolingMakeDetails) {
			yearPlan.getToolingMakeDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	public void storeQuarterPlanToolingMakeDetail(QuarterPlan quarterPlan, 
			String addYearToolingQuarterPlanDetailIds) {
		String addYearToolingQuarterPlanDetailId [] = null;
		if (null == addYearToolingQuarterPlanDetailIds) {
			return ;
		}
		addYearToolingQuarterPlanDetailId = addYearToolingQuarterPlanDetailIds.split(",");
		//根据年度计划的工装制作明细ID,创建季度计划的工装制作明细
		for (int i=0; i<addYearToolingQuarterPlanDetailId.length; i++) {
			ToolingMakeDetail quarterDetail = new ToolingMakeDetail();    //创建新的季度计划的工装制作明细
			//根据工装制作明细ID,获取年度计划的工装制作明细ID
			ToolingMakeDetail  yearDetail = this.toolingMakeDetailDao.loadToolingMakeDetail(Long.valueOf(addYearToolingQuarterPlanDetailId[i]));
			//拷贝年度计划中的工装制作明细到季度计划的工装制作明细
			copyYearToolingMakeDetailToQuarterToolingMakeDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearToolingMakeDetail(yearDetail);       //设置相关的年度计划中的工装制作明细
			this.toolingMakeDetailDao.storeToolingMakeDetail(quarterDetail);     //保存季度计划工装制作明细
			
			//设置该年度计划的工装制作明细为已列入季度计划
			this.setCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(yearDetail);
			
			//季度计划添加工装制作明细
			quarterPlan.getToolingMakeDetails().add(quarterDetail);
		}
		//计算季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	/**
	 * 拷贝年度计划中的工装制作明细到季度计划的工装制作明细
	 * @param yearDetail 年度计划中的工装制作明细
	 * @param quarterDetail 季度计划的工装制作明细
	 */
	private void copyYearToolingMakeDetailToQuarterToolingMakeDetail(ToolingMakeDetail yearDetail, 
			ToolingMakeDetail quarterDetail) {
		quarterDetail.setName(yearDetail.getName());	                    //拷贝品名
		quarterDetail.setCategory(yearDetail.getCategory());                //拷贝种类
		quarterDetail.setSpecification(yearDetail.getSpecification());      //拷贝规格
		quarterDetail.setModel(yearDetail.getModel());                      //拷贝型号
		quarterDetail.setUnitPrice(yearDetail.getUnitPrice());              //拷贝单价
		quarterDetail.setNumber(yearDetail.getNumber());                    //拷贝数量
		quarterDetail.setAllPrice(yearDetail.getAllPrice());                //拷贝总价
		quarterDetail.setPlanFinishedDate(yearDetail.getPlanFinishedDate());   //拷贝计划完成日期
		quarterDetail.setRequestReason(yearDetail.getRequestReason());         //拷贝需求原因
		quarterDetail.setComment(yearDetail.getComment());                     //拷贝备注
		
	}

	public void setCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(ToolingMakeDetail yearToolingMakeDetail) {
		yearToolingMakeDetail.setCreateQuarterFlag(true);
		this.toolingMakeDetailDao.storeToolingMakeDetail(yearToolingMakeDetail);
	}

	public void setNotCreatedQuartePlanStatusForToolingMakeDetailOfYearPlan(ToolingMakeDetail yearToolingMakeDetail) {
		yearToolingMakeDetail.setCreateQuarterFlag(false);
		this.toolingMakeDetailDao.storeToolingMakeDetail(yearToolingMakeDetail);
	}

	public void lockedToolingMakeDetail(Collection<ToolingMakeDetail> yearToolingMakeDetails) {
		for (ToolingMakeDetail yearToolingMakeDetail : yearToolingMakeDetails) {
			yearToolingMakeDetail.setLockedFlag(true);      //设置年度计划的工装制作明细为锁定状态
			this.toolingMakeDetailDao.storeToolingMakeDetail(yearToolingMakeDetail);
		}
	}

	public void ubLockedToolingMakeDetail(Collection<ToolingMakeDetail> yearToolingMakeDetails) {
		for (ToolingMakeDetail yearToolingMakeDetail : yearToolingMakeDetails) {
			yearToolingMakeDetail.setLockedFlag(false);      //设置年度计划的工装制作明细为解锁状态
			this.toolingMakeDetailDao.storeToolingMakeDetail(yearToolingMakeDetail);
		}
		
	}

	public double CalculateToolingMakeDetailFee(Collection<ToolingMakeDetail> yearToolingMakeDetails) {
		double allFee = 0.0;
		for (ToolingMakeDetail detail : yearToolingMakeDetails) {
			allFee += detail.getAllPrice().doubleValue();
		}
		return allFee;
	}
}
