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

import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.year.tooling.SparePurchaseDetailDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.year.tooling.SparePurchaseDetailManager;

/**
 * 
 * <p>Title: DefaultSparePurchaseDetailManager
 * <p>Description:备件采购明细业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.tooling.SparePurchaseDetailManager
 * @version $Id:$
 */
public class DefaultSparePurchaseDetailManager extends CalculateAllFeeManager implements
		SparePurchaseDetailManager {
	private final SparePurchaseDetailDao sparePurchaseDetailDao;
	private final SpareManager spareManager;
	
	public DefaultSparePurchaseDetailManager(SparePurchaseDetailDao sparePurchaseDetailDao,
			SpareManager spareManager) {
		this.sparePurchaseDetailDao = sparePurchaseDetailDao;
		this.spareManager = spareManager;
	}
	
	public SparePurchaseDetail loadSparePurchaseDetail(
			Long sparePurchaseDetailId) {
		return this.sparePurchaseDetailDao.loadSparePurchaseDetail(sparePurchaseDetailId);
	}
	
	public List<SparePurchaseDetail> loadAllSparePurchaseDetails(
			Long[] sparePurchaseDetailIds) {
		return this.sparePurchaseDetailDao.loadAllSparePurchaseDetails(sparePurchaseDetailIds);
	}

	public List<SparePurchaseDetail> loadAllSparePurchaseDetails() {
		return this.sparePurchaseDetailDao.loadAllSparePurchaseDetails();
	}

	public void storeSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail) {
		this.sparePurchaseDetailDao.storeSparePurchaseDetail(sparePurchaseDetail);
		if (null != sparePurchaseDetail.getYearPlan()) {
            //计算年度计划的总费用
			this.calulateAllFeeForYearPlan(sparePurchaseDetail.getYearPlan());
		} else {
			//计算季度计划的总费用
			this.calulateAllFeeForQuarterPlan(sparePurchaseDetail.getQuarterPlan());
		}
	}

	public void deleteSparePurchaseDetail(
			SparePurchaseDetail sparePurchaseDetail) {
		this.sparePurchaseDetailDao.deleteSparePurchaseDetail(sparePurchaseDetail);
	}

	public void deleteAllSparePurchaseDetailOfYearPlan(
			Collection<SparePurchaseDetail> sparePurchaseDetails, YearPlan yearPlan) throws Exception {
		//如果明细已被制作季度计划,抛出异常
		for (SparePurchaseDetail detail : sparePurchaseDetails) {
			if (null != detail.getQuarterSparePurchaseDetail() && detail.getQuarterSparePurchaseDetail().size()>0) {
				throw new Exception();
			}
		}
		this.sparePurchaseDetailDao.deleteAllSparePurchaseDetail(sparePurchaseDetails);
        //清除年度计划关联的备件采购明细
		for (SparePurchaseDetail detail : sparePurchaseDetails) {
			yearPlan.getSparePurchaseDetails().remove(detail);
		}
		//更新年度计划的总费用
		this.calulateAllFeeForYearPlan(yearPlan);
	}
	
	public void deleteAllSparePurchaseDetailOfQuarterPlan(Collection<SparePurchaseDetail> sparePurchaseDetails, 
			QuarterPlan quarterPlan) {
		this.sparePurchaseDetailDao.deleteAllSparePurchaseDetail(sparePurchaseDetails);
        //清除季度计划关联的备件采购明细,以及改变该明细关联的年度计划的备件采购明细为未列入季度计划状态
		for (SparePurchaseDetail detail : sparePurchaseDetails) {
			if (null != detail.getYearSparePurchaseDetail()) {
				this.setNotCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(detail.getYearSparePurchaseDetail());
			}
			quarterPlan.getSparePurchaseDetails().remove(detail);
		}
		//更新季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	public void storeQuarterPlanSparePurchaseDetail(QuarterPlan quarterPlan, 
			String addYearSparePurchaseDetailIds) {
		String addYearSparePurchaseDetailId [] = null;
		if (null == addYearSparePurchaseDetailIds) {
			return ;
		}
		addYearSparePurchaseDetailId = addYearSparePurchaseDetailIds.split(",");
		//根据年度计划的备件采购明细ID,创建季度计划的备件采购明细
		for (int i=0; i<addYearSparePurchaseDetailId.length; i++) {
			SparePurchaseDetail quarterDetail = new SparePurchaseDetail();    //创建新的季度计划的备件采购明细
			//根据备件采购明细ID,获取年度计划的备件采购明细ID
			SparePurchaseDetail yearDetail = this.sparePurchaseDetailDao.loadSparePurchaseDetail(Long.valueOf(addYearSparePurchaseDetailId[i]));
			//拷贝年度计划中的备件采购明细到季度计划的备件采购明细
			copyYearSparePurchaseDetailToQuarterSparePurchaseDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearSparePurchaseDetail(yearDetail);      //设置相关的年度计划中的备件采购明细
			this.sparePurchaseDetailDao.storeSparePurchaseDetail(quarterDetail);    //保存季度计划备件采购明细
			
			//设置该年度计划的备件采购明细为已列入季度计划
			this.setCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(yearDetail);
			
			//季度计划添加备件采购明细
			quarterPlan.getSparePurchaseDetails().add(quarterDetail);
		}
		
		//计算季度计划的总费用
		this.calulateAllFeeForQuarterPlan(quarterPlan);
	}
	
	/**
	 * 拷贝年度计划中的备件采购明细到季度计划的备件采购明细
	 * @param yearDetail 年度计划中的备件采购明细
	 * @param quarterDetail 季度计划的备件采购明细
	 */
	private void copyYearSparePurchaseDetailToQuarterSparePurchaseDetail(SparePurchaseDetail yearDetail, 
			SparePurchaseDetail quarterDetail) {
		quarterDetail.setName(yearDetail.getName());              //  拷贝品名
//		quarterDetail.setCategory(yearDetail.getCategory());      //  拷贝类别
//		quarterDetail.setDetailCategory(yearDetail.getDetailCategory());       //拷贝明细类别
		quarterDetail.setSpecification(yearDetail.getSpecification());         //拷贝规格
		quarterDetail.setModel(yearDetail.getModel());                         //拷贝型号
		quarterDetail.setUnitPrice(yearDetail.getUnitPrice());                 //拷贝单价
		quarterDetail.setNumber(yearDetail.getNumber());                       //拷贝数量
		quarterDetail.setAllPrice(yearDetail.getAllPrice());                   //拷贝总价
		quarterDetail.setRequestDate(yearDetail.getRequestDate());             //拷贝需求日期
		quarterDetail.setUsedTooling(yearDetail.getUsedTooling());             //拷贝使用工装
		quarterDetail.setRequestReason(yearDetail.getRequestReason());         //拷贝需求原因
		quarterDetail.setComment(yearDetail.getComment());                    //拷贝备注
	}

	public void setCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(SparePurchaseDetail yearSparePurchaseDetail) {
		yearSparePurchaseDetail.setCreateQuarterFlag(true);
		this.sparePurchaseDetailDao.storeSparePurchaseDetail(yearSparePurchaseDetail);
	}

	public void setNotCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(SparePurchaseDetail yearSparePurchaseDetail) {
		yearSparePurchaseDetail.setCreateQuarterFlag(false);
		this.sparePurchaseDetailDao.storeSparePurchaseDetail(yearSparePurchaseDetail);
	}

	public void storeNewAddSpareForYearPlan(YearPlan yearPlan, String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i=0; i<spareIds.length; i++) {
			SparePurchaseDetail sparePurchaseDetail = new SparePurchaseDetail();
			sparePurchaseDetail.setYearPlan(yearPlan);
			Spare spare = this.spareManager.loasSpare(Long.valueOf(spareIds[i]));
			//设置关联的备件
			sparePurchaseDetail.setSpare(spare);
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
			}
			//设置备件明细类别名称
			if (null != spare.getSpareDetailType()) {
				sparePurchaseDetail.setDetailCategoryName(spare.getSpareDetailType().getName());
			}
			this.sparePurchaseDetailDao.storeSparePurchaseDetail(sparePurchaseDetail);
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
			SparePurchaseDetail sparePurchaseDetail = this.sparePurchaseDetailDao.loadSparePurchaseDetail(Long.valueOf(allSparePurchaseDetailId[sparePurchaseCount]));
		   
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
		    this.sparePurchaseDetailDao.storeSparePurchaseDetail(sparePurchaseDetail);
		    yearPlan.getSparePurchaseDetails().add(sparePurchaseDetail);
		}
	}

	public double CalculateSparePurchaseDetailFee(Collection<SparePurchaseDetail> yearSparePurchaseDetails) {
		double allFee = 0.0;
		for (SparePurchaseDetail detail : yearSparePurchaseDetails) {
			allFee += detail.getAllPrice().doubleValue();
		}
		return allFee;
	}
}
