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
package com.yongjun.tdms.dao.year.tooling.quarterPlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;

/**
 * <p>Title: QuarterPlanDetailDao
 * <p>Description: 季度计划明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface QuarterPlanDetailDao {
	/**
	 * 根据传入的季度计划明细ID,获取季度计划明细
	 * @param quarterPlanDetailId 季度计划明细ID
	 * @return quarterPlanDetail 季度计划明细实体
	 */
	QuarterPlanDetail loadQuarterPlanDetail(Long quarterPlanDetailId);
	
	/**
	 * 根据传入的季度计划明细ID集合,获取集合季度计划明细
	 * @param quarterPlanDetailIds 季度计划明细ID集合
	 * @return List 集合季度计划明细
	 */
	List<QuarterPlanDetail> loadAllQuarterPlanDetails(Long [] quarterPlanDetailIds);
	
	/**
	 * 获取集合季度计划明细
	 * @return List 集合季度计划明细
	 */
	List<QuarterPlanDetail> loadAllQuarterPlanDetails();
	
	/**
	 * 保存季度计划明细
	 * @param quarterPlanDetail 季度计划明细
	 */
	void storeQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail);
	
	/**
	 * 删除季度计划明细
	 * @param QuarterPlanDetail 季度计划明细
	 */
	void deleteQuarterPlanDetail(QuarterPlanDetail QuarterPlanDetail);
	
	/**
	 * 根据传入的季度计划明细集合,删除集合季度计划明细
	 * @param quarterPlanDetails 季度计划明细集合
	 */
	void deleteAllQuarterPlanDetails(Collection<QuarterPlanDetail> quarterPlanDetails);
	
	/**
	 * 根据季度计划ID,季度计划明细类型，获取季度计划明细相应类型的总费用
	 * @param quarterPlanId 季度计划ID
	 * @param detailType 季度计划明细类型
	 * @return
	 */
	Double getAllQuarterPlanDetailFeeByQuarterPlanId(Long quarterPlanId, String detailType);
}
