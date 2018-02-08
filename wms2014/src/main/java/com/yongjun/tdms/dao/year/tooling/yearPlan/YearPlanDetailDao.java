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
package com.yongjun.tdms.dao.year.tooling.yearPlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;

/**
 * <p>Title: YearPlanDetailDao
 * <p>Description: 年度计划明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface YearPlanDetailDao {
	/**
	 * 根据传入的年度计划明细ID,获取年度计划明细
	 * @param yearPlanDetailId 年度计划明细ID
	 * @return YearPlanDetail 年度计划明细实体
	 */
	YearPlanDetail loadYearPlanDetail(Long yearPlanDetailId);
	
	/**
	 * 根据传入的年度计划明细ID集合,获取集合年度计划明细
	 * @param yearPlanDetailIds 年度计划明细ID集合
	 * @return List 集合年度计划明细
	 */
	List<YearPlanDetail> loadAllYearPlanDetails(Long [] yearPlanDetailIds);
	
	/**
	 * 获取集合年度计划明细
	 * @return List 集合年度计划明细
	 */
	List<YearPlanDetail> loadAllYearPlanDetails();
	
	/**
	 * 保存年度计划明细
	 * @param yearPlanDetail 年度计划明细
	 */
	void storeYearPlanDetail(YearPlanDetail yearPlanDetail);
	
	/**
	 * 删除年度计划明细
	 * @param yearPlanDetail 年度计划明细
	 */
	void deleteYearPlanDetail(YearPlanDetail yearPlanDetail);
	
	/**
	 * 根据传入的年度计划明细集合,删除集合年度计划明细
	 * @param yearPlanDetails 年度计划明细集合
	 */
	void deleteAllYearPlanDetails(Collection<YearPlanDetail> yearPlanDetails);
	
	/**
	 * 根据传入的年度计划id，年度计划明细类别，获取该类计划明细的所有费用
	 * @param detailType
	 * @param yearPlanId
	 * @return
	 */
	Double getAllPriceByDetailTypeAndYearPlanId(String detailType, Long yearPlanId);
}
