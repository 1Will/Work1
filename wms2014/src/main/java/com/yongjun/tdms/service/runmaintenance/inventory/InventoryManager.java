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
package com.yongjun.tdms.service.runmaintenance.inventory;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;

/**
 * <p>Title: InventoryManager
 * <p>Description: 设备清查详细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: InventoryManager.java 10842 2008-02-01 02:23:10Z qsun $
 */
@Transactional(readOnly=true)
public interface InventoryManager {
	/**
     * 设备清单ID集合，获取集合到数据库
     * 
     * @param InventoryIds		设备清单反馈单ID集合
     * @return List				设备清单反馈单集合
     */
	List<InventoryBill> loadAllInventoryBill(Long[] InventoryIds);
	/**
	 * 根据传入的设备清单集合，删除集合中的设备反馈单
	 * 
	 * @param inventorybills	设备清单集合
	 * @return
	 */
	@Transactional
	void deleteAllInventoryBill(List inventorybills);
	/**
	 * 根据传入的设备清单ID，导出该设备清单对象
	 * 
	 * @param inventorybillId	设备清单ID
	 * @return
	 */
	public InventoryBill loadInventoryBill(Long inventorybillId);
	/**
	 * 根据传入的设备清单对象，保存该设备清单对象
	 * 
	 * @param inventorybill	设备清单对象
	 * @return
	 */
	@Transactional
	void storeInventoryBill(InventoryBill inventorybill);

	/**
	 * 根据传入的设备清单集合,失效集合中的清单
	 * @param inventorybill 设备清单集合
	 */
	@Transactional
	void disabledAllInventoryBills(Collection<InventoryBill> inventoryBills);
	
	@Transactional
	void enabledAllInventoryBills(Collection<InventoryBill> inventoryBills);
}
