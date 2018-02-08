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

import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDetailDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.year.tooling.pojo.CalculateAllFeeManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;

/**
 * <p>Title: DefaultYearPlanDetailManager
 * <p>Description: 年度计划明细业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class DefaultYearPlanDetailManager extends CalculateAllFeeManager implements YearPlanDetailManager {

	private final YearPlanDetailDao yearPlanDetailDao;
	private final SpareManager spareManager;
	
	public DefaultYearPlanDetailManager(YearPlanDetailDao yearPlanDetailDao,
			SpareManager spareManager) {
		this.yearPlanDetailDao = yearPlanDetailDao;
		this.spareManager = spareManager;
	}
	
	public YearPlanDetail loadYearPlanDetail(Long yearPlanDetailId) {
		return this.yearPlanDetailDao.loadYearPlanDetail(yearPlanDetailId);
	}

	public List<YearPlanDetail> loadAllYearPlanDetails(Long[] yearPlanDetailIds) {
		return this.yearPlanDetailDao.loadAllYearPlanDetails(yearPlanDetailIds);
	}

	public List<YearPlanDetail> loadAllYearPlanDetails() {
		return this.yearPlanDetailDao.loadAllYearPlanDetails();
	}

	public void storeYearPlanDetail(YearPlanDetail yearPlanDetail) {
		this.yearPlanDetailDao.storeYearPlanDetail(yearPlanDetail);
         //计算年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlanDetail.getYearPlan());
	}

	public void deleteYearPlanDetail(YearPlanDetail yearPlanDetail) {
		this.yearPlanDetailDao.deleteYearPlanDetail(yearPlanDetail);
	}

	public void deleteAllYearPlanDetails(
			Collection<YearPlanDetail> yearPlanDetails) {
		this.yearPlanDetailDao.deleteAllYearPlanDetails(yearPlanDetails);
	}
	
	public void deleteAllToolingMakeDetailOfYearPlan(Collection<YearPlanDetail> toolingMakeDetails, 
			YearPlan yearPlan) throws Exception{
		//如果该明细已制定季度明细,抛出异常,
		for (YearPlanDetail detail : toolingMakeDetails) {
			if (detail.isCreateQuarterFlag()) {
				throw new Exception();
			}
		}
		//删除传入工装制作明细
		this.yearPlanDetailDao.deleteAllYearPlanDetails(toolingMakeDetails);
		//清除年度计划关联的工装制作明细
		for (YearPlanDetail detail : toolingMakeDetails) {
			yearPlan.getYearPlanDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	
	public void deleteAllSparePurchaseDetailOfYearPlan(
			Collection<YearPlanDetail> sparePurchaseDetails, YearPlan yearPlan) throws Exception {
		//如果明细已被制作季度计划,抛出异常
		for (YearPlanDetail detail : sparePurchaseDetails) {
			if (detail.isCreateQuarterFlag()) {
				throw new Exception();
			}
		}
		this.yearPlanDetailDao.deleteAllYearPlanDetails(sparePurchaseDetails);
        //清除年度计划关联的备件采购明细
		for (YearPlanDetail detail : sparePurchaseDetails) {
			yearPlan.getYearPlanDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	
	public void deleteAllRepairMaintenanceDetailOfYearPlan (
			Collection<YearPlanDetail> repairMaintenanceDetails, YearPlan yearPlan) throws Exception{
		//如果明细已被制作季度计划,抛出异常
		for (YearPlanDetail detail : repairMaintenanceDetails) {
			if (detail.isCreateQuarterFlag()) {
				throw new Exception();
			}
		}
		this.yearPlanDetailDao.deleteAllYearPlanDetails(repairMaintenanceDetails);
		 //清除年度计划关联的维修保养明细
		for (YearPlanDetail detail : repairMaintenanceDetails) {
			yearPlan.getYearPlanDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	
	public void deleteAllTechAlterDetailOfYearPlan(
			Collection<YearPlanDetail> techAlterDetails, YearPlan yearPlan)throws Exception {
		//如果该明细已制定明细,抛出异常,
		for (YearPlanDetail detail : techAlterDetails) {
			if (detail.isCreateQuarterFlag()) {
				throw new Exception();
			}
		}
		this.yearPlanDetailDao.deleteAllYearPlanDetails(techAlterDetails);
		 //清除年度计划关联的技术改造明细
		for (YearPlanDetail detail : techAlterDetails) {
			yearPlan.getYearPlanDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	
	public void storeNewAddSpareForYearPlan(YearPlan yearPlan, String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i=0; i<spareIds.length; i++) {
			YearPlanDetail sparePurchaseDetail = new YearPlanDetail();
			sparePurchaseDetail.setYearPlan(yearPlan);
			Spare spare = this.spareManager.loasSpare(Long.valueOf(spareIds[i]));
			//设置关联的备件
			sparePurchaseDetail.setSpare(spare);
			//设置备件编号
			sparePurchaseDetail.setGraphNo(spare.getSpareNo());
			//设置备件名称
			sparePurchaseDetail.setName(spare.getName());
			//设置备件规格
			sparePurchaseDetail.setSpecification(spare.getSpecification());
			//设置备件型号
			sparePurchaseDetail.setModel(spare.getModelSpecs());
			//设置备件单价
			sparePurchaseDetail.setUnitPrice(spare.getUnitPrice());
			//设置备件类别名称
			if (null != spare.getCategory()) {
				sparePurchaseDetail.setCategoryName(spare.getCategory().getName());
				sparePurchaseDetail.setCategory(spare.getCategory());
			}
			//设置备件明细类别名称
			if (null != spare.getSpareDetailType()) {
				sparePurchaseDetail.setDetailCategoryName(spare.getSpareDetailType().getName());
				sparePurchaseDetail.setDetailCategory(spare.getSpareDetailType());
			}
			//设置数量单位
			if(null != spare.getUnit()){
				sparePurchaseDetail.setCalUnit(spare.getUnit());
			}
			sparePurchaseDetail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
			this.yearPlanDetailDao.storeYearPlanDetail(sparePurchaseDetail);
		}
		
	}

	public void storeSpareForYearPlan(YearPlan yearPlan, String allSparePurchaseDetailIds, String allNumbers, String allRequestDates, 
			String allRequestReasons, String allComments,String allUnitPrices) {
		String [] allSparePurchaseDetailId = null;      //采购明细id数组
		String [] allNumber = null;                     //数量数组
		String [] allRequestDate = null;                //需求日期数组
		String [] allRequestReason = null;              //需求原因数组
		String [] allComment = null;                   //备注数组
		String [] allUnitPrice = null;                 //单价数组
		
		//备件采购明细id分割
		if (null != allSparePurchaseDetailIds) {
			allSparePurchaseDetailId =  allSparePurchaseDetailIds.split(",");
		}
		//数量分割
		if (null != allNumbers) {
			allNumber = allNumbers.split(",");
		}
		//需求日期分割
		if (null != allRequestDates) {
			allRequestDate = allRequestDates.split(",");
		}
		//需求原因分割
		if (null != allRequestReasons) {
			allRequestReason = allRequestReasons.split(",");
		}
		//备注分割
		if (null != allComments) {
			allComment = allComments.split(",");
		}
		//单价分割
		if (null != allUnitPrices) {
			allUnitPrice = allUnitPrices.split(",");
		}
        //更新备件采购明细的数量，需求日期，需求原因，备注的值
		this.updateSparePurchaseDetailValueForYearPlan(yearPlan, allSparePurchaseDetailId,allNumber,
				allRequestDate, allRequestReason, allComment,allUnitPrice);
        //计算年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	
	//更新备件采购明细的数量，需求日期，需求原因，备注的值
	private void updateSparePurchaseDetailValueForYearPlan (YearPlan yearPlan, String [] allSparePurchaseDetailId, String [] allNumber, String [] allRequestDate,
			String [] allRequestReason, String [] allComment,String [] allUnitPrice) {
		int sparePurchaseCount = 0;
		
		while (null != allSparePurchaseDetailId && sparePurchaseCount<allSparePurchaseDetailId.length) {
			YearPlanDetail sparePurchaseDetail = this.yearPlanDetailDao.loadYearPlanDetail(Long.valueOf(allSparePurchaseDetailId[sparePurchaseCount]));
		   
			if (null != allUnitPrice) {     //设置备件采购明细单价
		    	for (int i=0; i<allUnitPrice.length; i=i+2) {
		    		if (allUnitPrice[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setUnitPrice(Double.valueOf(allUnitPrice[i+1]));
		    			break;
		    		} else {
		    			sparePurchaseDetail.setUnitPrice(0.0);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setUnitPrice(0.0);
		    }
			if (null != allNumber) {     //设置备件采购明细数量
		    	for (int i=0; i<allNumber.length; i=i+2) {
		    		if (allNumber[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setNumber(Integer.valueOf(allNumber[i+1]));
		    			//设置总价
		    			sparePurchaseDetail.setAllPrice(sparePurchaseDetail.getUnitPrice()*Integer.valueOf(allNumber[i+1]));
		    			break;
		    		} else {
		    			sparePurchaseDetail.setNumber(0);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setNumber(0);
		    }
		    if (null != allRequestDate) {      //设置备件采购明细需求日期
		    	for (int i=0; i<allRequestDate.length; i=i+2) {
		    		if (allRequestDate[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setRequestDate(DateUtil.toDate(allRequestDate[i+1], "yyyy-MM-dd"));
		    			break;
		    		} else {
		    			sparePurchaseDetail.setRequestDate(null);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setRequestDate(null);
		    }
		    if (null != allRequestReason) {    //设置备件采购明细需求原因   
		    	for (int i=0; i<allRequestReason.length; i=i+2) {
		    		if (allRequestReason[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setRequestReason(allRequestReason[i+1]);
		    			break;
		    		} else {
		    			sparePurchaseDetail.setRequestReason(null);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setRequestReason(null);
		    }
		    if (null != allComment) {    //设置备件采购明细备注  
		    	for (int i=0; i<allComment.length; i=i+2) {
		    		if (allComment[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setComment(allComment[i+1]);
		    			break;
		    		} else {
		    			sparePurchaseDetail.setComment(null);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setComment(null);
		    }
		    sparePurchaseCount++;
		    this.yearPlanDetailDao.storeYearPlanDetail(sparePurchaseDetail);
		    yearPlan.getYearPlanDetails().add(sparePurchaseDetail);
		}
	}

	public Double getAllPriceByDetailTypeAndYearPlanId(String detailType, Long yearPlanId) {
		return this.yearPlanDetailDao.getAllPriceByDetailTypeAndYearPlanId(detailType, yearPlanId);
	}

	public Double getRepairMaintenanceAllFee(YearPlan yearPlan) {
		return this.yearPlanDetailDao.getAllPriceByDetailTypeAndYearPlanId(
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString(), yearPlan.getId());
	}

	public Double getSparePurchaseAllFee(YearPlan yearPlan) {
		return this.yearPlanDetailDao.getAllPriceByDetailTypeAndYearPlanId(
				YearPlanDetailCategory.SPARE_PURCHASE.toString(), yearPlan.getId());
	}

	public Double getTechAlterAllFee(YearPlan yearPlan) {
		return this.yearPlanDetailDao.getAllPriceByDetailTypeAndYearPlanId(
				YearPlanDetailCategory.TECH_ALTER.toString(), yearPlan.getId());
	}

	public Double getToolingMakeAllFee(YearPlan yearPlan) {
		return this.yearPlanDetailDao.getAllPriceByDetailTypeAndYearPlanId(
				YearPlanDetailCategory.TOOLING_MAKE.toString(), yearPlan.getId());
	}

}
