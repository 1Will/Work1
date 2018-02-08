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
package com.yongjun.tdms.dao.year.device.purchasePlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;

/**
 * 
 * <p>Title: PurchasePlanDetailDao
 * <p>Description: 设备年度采购计划明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface PurchasePlanDetailDao {
	/**
	 * 根据传入的设备年度采购计划明细ID,获取设备年度采购计划明细
	 * @param purchasePlanDetailId 设备年度采购计划明细ID
	 * @return PurchasePlanDetail 设备年度采购计划明细实体
	 */
	PurchasePlanDetail loadPurchasePlanDetail(Long purchasePlanDetailId);
	
	/**
	 * 根据传入的设备年度采购计划明细ID集合,获取集合设备年度采购计划明细
	 * @param purchasePlanDetailIds 设备年度采购计划明细ID集合
	 * @return List 集合设备年度采购计划明细
	 */
	List<PurchasePlanDetail> loadAllPurchasePlanDetails(Long [] purchasePlanDetailIds);
	
	/**
	 * 获取集合设备年度采购计划明细
	 * @return List 集合设备年度采购计划明细
	 */
	List<PurchasePlanDetail> loadAllPurchasePlanDetails();
	
	/**
	 * 保存设备年度采购计划明细
	 * @param purchasePlanDetail 设备年度采购计划明细
	 */
	void storePurchasePlanDetail(PurchasePlanDetail purchasePlanDetail);
	
	/**
	 * 删除设备年度采购计划明细
	 * @param purchasePlanDetail 设备年度采购计划明细
	 */
	void deletePurchasePlanDetail(PurchasePlanDetail purchasePlanDetail);
	
	/**
	 * 根据传入的设备年度采购计划明细集合,删除集合设备年度采购计划明细
	 * @param purchasePlanDetails 设备年度采购计划明细集合
	 */
	void deleteAllPurchasePlanDetails(Collection<PurchasePlanDetail> purchasePlanDetails);
}
