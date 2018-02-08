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
package com.yongjun.tdms.service.year.tooling;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: SparePurchaseDetailManager
 * <p>Description: 备件采购明细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface SparePurchaseDetailManager {
	/**
	 * 根据传入的备件采购明细ID,获取备件采购明细
	 * @param sparePurchaseDetailId 备件采购明细ID
	 * @return SparePurchaseDetail 备件采购明细实体
	 */
	SparePurchaseDetail loadSparePurchaseDetail(Long sparePurchaseDetailId);
	
	/**
	 * 根据传入的备件采购明细ID集合,获取备件采购明细集合
	 * @param sparePurchaseDetailIds 备件采购明细ID集合
	 * @return List 备件采购明细集合
	 */
	List<SparePurchaseDetail> loadAllSparePurchaseDetails(Long [] sparePurchaseDetailIds);
	
	/**
	 * 获取备件采购明细集合
	 * @return List 备件采购明细集合
	 */
	List<SparePurchaseDetail> loadAllSparePurchaseDetails();
	
	/**
	 * 保存传入的备件采购明细
	 * @param sparePurchaseDetail 备件采购明细实体
	 */
	@Transactional
	void storeSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail);
	
	/**
	 * 删除传入的备件采购明细
	 * @param sparePurchaseDetail  备件采购明细实体
	 */
	@Transactional
	void deleteSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail);
	
	/**
	 * 删除传入年度计划的集合备件采购明细,并更新年度计划的总费用
	 * @param sparePurchaseDetails 集合备件采购明细
	 * @param yearPlan 年度计划
	 * @exception 当改明细已被制作季度计划,抛出异常
	 */
	@Transactional
	void deleteAllSparePurchaseDetailOfYearPlan(Collection<SparePurchaseDetail> sparePurchaseDetails, YearPlan yearPlan)throws Exception;
	
	/**
	 * 删除传入季度计划的集合备件采购明细,并更新季度计划的总费用
	 * @param sparePurchaseDetails 集合备件采购明细
	 * @param yearPlan 季度计划
	 */
	@Transactional
	void deleteAllSparePurchaseDetailOfQuarterPlan(Collection<SparePurchaseDetail> sparePurchaseDetails, QuarterPlan quarterPlan);
	
	/**
	 * 从年度计划的备件采购明细中创建季度计划的备件采购明细
	 * @param quarterPlan 季度计划
	 * @param addYearSparePurchaseDetailIds 年度计划的备件采购明细ID字符串
	 */
	@Transactional
	void storeQuarterPlanSparePurchaseDetail(QuarterPlan quarterPlan, String addYearSparePurchaseDetailIds);
	
	/**
	 * 设置年度计划的备件采购明细的状态为列入季度计划状态
	 * @param yearSparePurchaseDetail 年度计划的备件采购明细
	 */
	@Transactional
	void setCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(SparePurchaseDetail yearSparePurchaseDetail);
	
	/**
	 * 设置年度计划的备件采购明细的状态为未列入季度计划状态
	 * @param yearSparePurchaseDetail 年度计划的备件采购明细
	 */
	void setNotCreatedQuartePlanStatusForSparePurchaseDetailOfYearPlan(SparePurchaseDetail yearSparePurchaseDetail);
	
	/**
	 * 为年度计划的备件采购添加新的备件
	 * @param yearPlan 年度计划
	 * @param spareIds 新的备件ID
	 */
	@Transactional
	void storeNewAddSpareForYearPlan(YearPlan yearPlan, String spareIds);
	
	/**
	 * 更新备件采购明细的数量，需求日期，需求原因，备注的值
	 * @param allSparePurchaseDetailIds 备件采购明细id
	 * @param allNumbers 数量
	 * @param allRequestDates 需求日期
	 * @param allRequestReasons 需求原因
	 * @param allComments 备注
	 */
	@Transactional
	void storeSpareForYearPlan(YearPlan yearPlan, String allSparePurchaseDetailIds, String allNumbers, String allRequestDates,
			String allRequestReasons, String allComments,String allUnitPrices);
	
	/**
	 * 计算备件采购明细所有费用
	 * @param yearSparePurchaseDetails 备件采购明细集合
	 * @return
	 */
	double CalculateSparePurchaseDetailFee(Collection<SparePurchaseDetail> yearSparePurchaseDetails);
}
