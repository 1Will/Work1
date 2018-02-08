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

import com.yongjun.tdms.dao.year.tooling.TechAlterDetailDao;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.year.tooling.TechAlterDetailManager;

/**
 * 
 * <p>Title: DefaultTechAlterDetailManager
 * <p>Description: 技术改造明细业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.tooling.TechAlterDetailManager
 * @version $
 */
public class DefaultTechAlterDetailManager extends CalculateAllFeeManager implements TechAlterDetailManager {
	
	private final TechAlterDetailDao techAlterDetailDao;
	
	public DefaultTechAlterDetailManager(TechAlterDetailDao techAlterDetailDao) {
		this.techAlterDetailDao = techAlterDetailDao;
	}
	
	public TechAlterDetail loadTechAlterDetail(Long techAlterDetailId) {
		return this.techAlterDetailDao.loadTechAlterDetail(techAlterDetailId);
	}

	public List<TechAlterDetail> loadAllTechAlterDetails(
			Long[] techAlterDetailIds) {
		return this.techAlterDetailDao.loadAllTechAlterDetails(techAlterDetailIds);
	}

	public List<TechAlterDetail> loadAllTechAlterDetails() {
		return this.techAlterDetailDao.loadAllTechAlterDetails();
	}

	public void storeTechAlterDetail(TechAlterDetail techAlterDetail) {
		this.techAlterDetailDao.storeTechAlterDetail(techAlterDetail);
		if (null != techAlterDetail.getYearPlan()) {
            //计算年度计划的总费用
			this.calulateAllFeeForYearPlan(techAlterDetail.getYearPlan());
		} else {
			//计算季度计划的总费用
			this.calulateAllFeeForQuarterPlan(techAlterDetail.getQuarterPlan());
		}
	}

	public void deleteTechAlterDetail(TechAlterDetail techAlterDetail) {
		this.techAlterDetailDao.deleteTechAlterDetail(techAlterDetail);
	}

	public void deleteAllTechAlterDetailOfYearPlan(
			Collection<TechAlterDetail> techAlterDetails, YearPlan yearPlan)throws Exception {
		//如果该明细已制定明细,抛出异常,
		for (TechAlterDetail detail : techAlterDetails) {
			if (null != detail.getQuarterTechAlterDetail() && detail.getQuarterTechAlterDetail().size()>0) {
				throw new Exception();
			}
		}
		this.techAlterDetailDao.deleteAllTechAlterDetail(techAlterDetails);
		 //清除年度计划关联的技术改造明细
		for (TechAlterDetail detail : techAlterDetails) {
			yearPlan.getTechAlterDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}

	public void deleteAllTechAlterDetailOfQuarterPlan(
			Collection<TechAlterDetail> techAlterDetails, QuarterPlan quarterPlan) {
		this.techAlterDetailDao.deleteAllTechAlterDetail(techAlterDetails);
		 //清除季度计划关联的技术改造明细,以及改变该明细关联的年度计划的技术改造明细为未列入季度计划状态
		for (TechAlterDetail detail : techAlterDetails) {
			if (null != detail.getYearTechAlterDetail()) {
				this.setNotCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(detail.getYearTechAlterDetail());
			}
			quarterPlan.getTechAlterDetails().remove(detail);
		}
		//更新季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	public void storeQuarterPlanTechAlterDetail(QuarterPlan quarterPlan, String addYearTechAlterDetailIds) {
		String addYearTechAlterDetailId [] = null;
		if (null == addYearTechAlterDetailIds) {
			return ;
		}
		addYearTechAlterDetailId = addYearTechAlterDetailIds.split(",");
		//根据年度计划的技术改造明细ID,创建季度计划的技术改造明细
		for (int i=0; i<addYearTechAlterDetailId.length; i++) {
			TechAlterDetail quarterDetail = new TechAlterDetail();    //创建新的季度计划的技术改造明细
			//根据技术改造明细ID,获取年度计划的技术改造明细ID
			TechAlterDetail yearDetail = this.techAlterDetailDao.loadTechAlterDetail(Long.valueOf(addYearTechAlterDetailId[i]));
			//拷贝年度计划中的备件采购明细到季度计划的技术改造明细
			copyYearTechAlterDetailToQuarterTechAlterDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearTechAlterDetail(yearDetail);      //设置相关的年度计划中的维修保养明细
			this.techAlterDetailDao.storeTechAlterDetail(quarterDetail);   //保存季度计划维修保养明细
			
			this.setCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(yearDetail);    //设置该年度计划的维修保养明细为已列入季度计划
			
			//季度计划添加维修保养明细
			quarterPlan.getTechAlterDetails().add(quarterDetail);
		}
		//计算季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	/**
	 * 拷贝年度计划中的备件采购明细到季度计划的技术改造明细
	 * @param yearDetail  年度计划中的备件采购明细
	 * @param quarterDetail 季度计划的技术改造明细
	 */
	private void copyYearTechAlterDetailToQuarterTechAlterDetail(TechAlterDetail yearDetail, 
			TechAlterDetail quarterDetail) {
		quarterDetail.setToolingType(yearDetail.getToolingType());                //拷贝工装类别
		quarterDetail.setPlanFee(yearDetail.getPlanFee());                        //拷贝计划费用
		quarterDetail.setPlanFinishedDate(yearDetail.getPlanFinishedDate());      //拷贝计划完成日期
		quarterDetail.setTechAlterContent(yearDetail.getTechAlterContent());      //拷贝技术改造内容
		quarterDetail.setReason(yearDetail.getReason());                          //拷贝原因
		quarterDetail.setComment(yearDetail.getComment());                        //拷贝备注
		
	}

	public void setCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(TechAlterDetail yearTechAlterDetail) {
		yearTechAlterDetail.setCreateQuarterFlag(true);
		this.techAlterDetailDao.storeTechAlterDetail(yearTechAlterDetail);
	}

	public void setNotCreatedQuartePlanStatusForTechAlterDetailOfYearPlan(TechAlterDetail yearTechAlterDetail) {
		yearTechAlterDetail.setCreateQuarterFlag(false);
		this.techAlterDetailDao.storeTechAlterDetail(yearTechAlterDetail);
	}

	public double calculateTechAlterDetailFee(Collection<TechAlterDetail> yearTechAlterDetails) {
			double allFee = 0.0;
			for (TechAlterDetail detail : yearTechAlterDetails) {
				allFee += detail.getPlanFee().doubleValue();
			}
			return allFee;
	}

}
