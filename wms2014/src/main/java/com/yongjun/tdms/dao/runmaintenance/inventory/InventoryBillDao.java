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
package com.yongjun.tdms.dao.runmaintenance.inventory;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;

/**
 * <p>Title: InventoryBillDao
 * <p>Description: 清查数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
public interface InventoryBillDao {
	/**
	 * 根据传入的清查单ID，获取清查单
	 * 
	 * @param inventoryBillId 清查单ID
	 * @return InventoryBill 清查单
	 */
	InventoryBill loadInventoryBill(Long inventoryBillId);
	
	/**
	 * 根据传入的清查单ID集合，获取清查单集合
	 * 
	 * @param faultBillIds 清查单ID集合
	 * @return List 清查单集合
	 */
	List<InventoryBill> loadInventoryBills(Long [] inventoryBillIds);
	
	/**
	 * 获取集合中的清查单
	 * 
	 * @return List 清查单集合
	 */
	List<InventoryBill> loadInventoryBills();
	
	/**
	 * 保存清查单
	 * 
	 * @param inventoryBill 清查单实体
	 * @return
	 */
	void storeInventoryBill(InventoryBill inventoryBill);
	
	/**
	 * 删除清查单
	 * 
	 * @param inventoryBill 清查单实体
	 * @return
	 */
	void deleteInventoryBill(InventoryBill inventoryBill);
	
	/**
	 * 根据传入的清查单集合，删除集合中的清查单
	 * 
	 * @param inventoryBills 清查单集合
	 * @return
	 */
	void deleteAllInventoryBills(Collection<InventoryBill> inventoryBills);
}
