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
package com.yongjun.tdms.service.asset.spare.Inventory;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;


public interface SpareInventoryManager {
	
	/**
	 * 根据传入的盘点单对象的ID数组号,加裁所有的盘点单对象
	 * 
	 * @param spareInventoryIds 盘点单对象的ID数组
	 * @return 盘点单对象数组
	 */	
	List<SpareInventory> loadAllSpareInventorys(Long[] spareInventoryIds);

	/**
	 * 根据传入的盘点单对象的ID号,加裁盘点单对象
	 * 
	 * @param spareInventoryId 盘点单对象的ID号
	 * @return 盘点单对象
	 */
	SpareInventory loadSpareInventory(Long spareInventoryId);

	
	/**
	 * 根据传入的盘点单对象,存储盘点单对象
	 * 
	 * @param spareInventory 盘点单对象
	 * @return 
	 */
	@Transactional
	void storeSpareInventory(SpareInventory spareInventory);
	
	
	/**
	 * 根据传入的盘点单对象和安全库存值,更新盘点单和备件对象的安全库存
	 * 
	 * @param spareInventory 盘点单对象
	 * @param inventorySpareStock 盘点对象安全库存
	 * @return 
	 */
	@Transactional
	void storeSpareInventory(SpareInventory spareInventory,
			String inventorySpareStock);

	/**
	 * 根据传入的盘点单对象和安全库存值,更新盘点单和备件对象的安全库存
	 * 
	 * @param spareInventory 盘点单对象
	 * @param inventorySpareStock 盘点对象安全库存
	 * @return 
	 */
	@Transactional
	void deleteSpareInventory(SpareInventory spareInventory);
	/**
	 * 失效所有的盘点单
	 * @param spareInventorys
	 */
	@Transactional
	void disabledAllInventoryBill(Collection<SpareInventory> spareInventorys);
    /**
     * 有效所有的盘点单 
     * @param spareInventorys
     */
	@Transactional
	void enabledAllInventoryBill(Collection<SpareInventory> spareInventorys);

}
