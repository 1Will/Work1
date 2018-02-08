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
package com.yongjun.tdms.service.year.device.purchasePlan;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanView;

/**
 * 
 * <p>Title: PurchasePlanManager
 * <p>Description: 设备年度采购计划业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface PurchasePlanManager {
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
	@Transactional
	void storePurchasePlan(PurchasePlan purchasePlan);
	
	/**
	 * 删除设备年度采购计划
	 * @param purchasePlan 设备年度采购计划
	 */
	@Transactional
	void deletePurchasePlan(PurchasePlan purchasePlan);
	
	/**
	 * 根据传入的设备年度采购计划集合,删除集合设备年度采购计划
	 * @param purchasePlans 设备年度采购计划集合
	 */
	@Transactional
	void deleteAllPurchasePlans(Collection<PurchasePlan> purchasePlans);
	
	/**
	 * 根据传入的设备年度采购计划集合,失效集合设备年度采购计划
	 * @param purchasePlans 设备年度采购计划集合
	 */
	@Transactional
	void disabledAllPurchasePlans(Collection<PurchasePlan> purchasePlans);
	
	/**
	 * 根据传入的设备年度采购计划集合,有效集合设备年度采购计划
	 * @param purchasePlans 设备年度采购计划集合
	 */
	@Transactional
	void enabledAllPurchasePlans(Collection<PurchasePlan> purchasePlans);
	
	/**
	 * 计算传入的设备年度采购计划的总费用
	 * @param purchasePlan 设备年度采购计划
	 */
	@Transactional
	void calulateAllFeeForPurchasePlan(PurchasePlan purchasePlan);

	public List<PurchasePlanView> loadAllPurchasePlanView(String[] array) throws HibernateException;
}
