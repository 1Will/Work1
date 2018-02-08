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

import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;

/**
 * 
 * <p>Title: purchasePlanDao
 * <p>Description: 设备年度采购计划数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface PurchasePlanDao {
	/**
	 * 根据传入的设备年度采购计划ID,获取设备年度采购计划
	 * @param purchasePlanId 设备年度采购计划ID
	 * @return PurchasePlan 设备年度采购计划实体
	 */
	PurchasePlan loadPurchasePlan(Long purchasePlanId);
	
	/**
	 * 根据传入的设备年度采购计划ID集合,获取集合设备年度采购计划
	 * @param purchasePlanIds 设备年度采购计划ID集合
	 * @return List 集合设备年度采购计划
	 */
	List<PurchasePlan> loadAllPurchasePlans(Long [] purchasePlanIds);
	
	/**
	 * 获取集合设备年度采购计划
	 * @return List 集合设备年度采购计划
	 */
	List<PurchasePlan> loadAllPurchasePlans();
	
	/**
	 * 保存设备年度采购计划
	 * @param purchasePlan 设备年度采购计划
	 */
	void storePurchasePlan(PurchasePlan purchasePlan);
	
	/**
	 * 删除设备年度采购计划
	 * @param purchasePlan 设备年度采购计划
	 */
	void deletePurchasePlan(PurchasePlan purchasePlan);
	
	/**
	 * 根据传入的设备年度采购计划集合,删除集合设备年度采购计划
	 * @param purchasePlans 设备年度采购计划集合
	 */
	void deleteAllPurchasePlans(Collection<PurchasePlan> purchasePlans);
}
