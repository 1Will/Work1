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
package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.ToolingPurchaseBillDetailView;

/**
 * @author qs
 * @version $Id: PurchaseBillManager.java 11279 2008-03-12 01:12:13Z mwei $
 */
@Transactional(readOnly=true)
public interface PurchaseBillManager {
	@Transactional
	PurchaseBill loadPurchaseBill(Long purchasId);
	@Transactional
	List<ToolingPurchaseBillDetailView> loadPurchaseBillDtlByBillId(Long purchasId);
	List<PurchaseBill> loadPurchaseBills(Long[] PurchaseBillIds);

	@Transactional
	void storePurchaseBill(PurchaseBill purchaseBill);
	@Transactional
	void deletePurchaseBill(PurchaseBill purchaseBill);
	@Transactional
	void deleteAllPurchaseBills(Collection<PurchaseBill> PurchaseBills);
	@Transactional
	void disabledAllPurchases(Collection<PurchaseBill> purchases)throws Exception;
	@Transactional
	void enabledAllPurchasers(Collection<PurchaseBill> purchases);
    //累加已付金额的具体方法
	@Transactional
	void updatePurchaseBillPayInformation(PurchaseBill purchaseBill);
	//累加合同总金额的具体方法
	@Transactional
   void addPurchaseConTotalPrice(PurchaseBill purchaseBill);
	
	List<ToolingPurchaseBillDetailView> loadPurchaseBillDtlByBillId(List ids);
	

}
