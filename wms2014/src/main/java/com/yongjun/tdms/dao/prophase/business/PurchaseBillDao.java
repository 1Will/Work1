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
package com.yongjun.tdms.dao.prophase.business;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;

/**
 * @author qs
 * @version $Id: PurchaseBillDao.java 11279 2008-03-12 01:12:13Z mwei $
 */
public interface PurchaseBillDao {
	/**
	 * 根据传入的采购单ID，获取采购单
	 * 
	 * @param purchasId 采购单ID
	 * @return PurchaseBill 采购单
	 */
	PurchaseBill loadPurchaseBill(Long purchasId);
	/**
	 * 根据传入的采购单ID集合，获取采购单集合
	 * 
	 * @param MigrateIds 采购单ID集合
	 * @return List 采购单集合
	 */
	List<PurchaseBill> loadPurchaseBills(Long[] PurchaseBillIds);

	/**
	 * 保存采购单实体
	 * 
	 * @param PurchaseBill 采购单实体
	 * @return
	 */
	void storePurchaseBill(PurchaseBill purchaseBill);
	/**
	 * 删除采购单实体
	 * 
	 * @param purchaseBill 采购单实体
	 * @return
	 */
	void deletePurchaseBill(PurchaseBill purchaseBill);
	/**
	 * 根据传入的采购单集合，删除集合中的采购单
	 * 
	 * @param PurchaseBills 采购单集合
	 * @return
	 */
	void deleteAllPurchaseBills(Collection<PurchaseBill> PurchaseBills);
	//PurchaseBill loadPurchaseBillByBillNo(String billNo);
	void storeTotalPrice(PurchaseBill purchaseBill);
	
	public String GetMaxBillNoByBillCode(String billCode);
	
}
