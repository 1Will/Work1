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

import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBillDetail;

/**
 * <p>Title: InventoryBillDetailDao
 * <p>Description: 清查明细数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
public interface InventoryBillDetailDao {
	/**
	 * 根据传入的查清明细ID，获取清查明细对象
	 * @param inventoryBillDetailId 查清明细ID
	 * @return InventoryBillDetail 清查明细对象
	 */
	InventoryBillDetail loadInventoryBillDetail(Long inventoryBillDetailId);
	
	/**
	 * 根据传入的查清明细ID集合，获取清查明细对象集合
	 * @param inventoryBillDetailIds 查清明细ID集合
	 * @return List 清查明细对象集合
	 */
	List<InventoryBillDetail> loadAllInventoryBillDetails(Long [] inventoryBillDetailIds);
	
	/**
	 * 获取清查明细对象集合
	 * @return List 清查明细对象集合
	 */
	List<InventoryBillDetail> loadAllInventoryBillDetails();
	
	/**
	 * 根据传入的清查明细对象，保存清查明细
	 * @param inventoryBillDetail 清查明细对象
	 */
	void storeInventoryBillDetail(InventoryBillDetail inventoryBillDetail);
	
	/**
	 * 根据传入的清查明细对象，删除清查明细
	 * @param inventoryBillDetail 清查明细对象
	 */
	void deleteInventoryBillDetail(InventoryBillDetail inventoryBillDetail);
	
	/**
	 * 根据传入的清查明细对象集合，删除集合中的清查明细
	 * @param inventoryBillDetails 清查明细对象集合
	 */
	void deleteAllInventoryBillDetails(Collection<InventoryBillDetail> inventoryBillDetails);
}
