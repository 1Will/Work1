package com.yongjun.tdms.service.runmaintenance.inventory;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBillDetail;
@Transactional(readOnly=true)
public interface InventoryBillDetailManager {
	/**
     * 设备清单明细ID集合，获取集合到数据库
     * 
     * @param inventoryBillDetailId		设备清单明细反馈单ID集合
     * @return InventoryBillDetail				设备清单明细反馈单集合
     */
	InventoryBillDetail loadInventoryBillDetail(Long inventoryBillDetailId);
	/**
	 * 根据传入的设备清单明细集合，删除集合中的设备明细反馈单
	 * 
	 * @param inventoryBillDetailIds	设备清单明细集合
	 * @return list
	 */
	List<InventoryBillDetail> loadAllInventoryBillDetails(Long [] inventoryBillDetailIds);
	/**
	 * 根据传入的设备清单ID，导出该设备清单对象
	 * 
	 * @param inventorybillId	设备清单ID
	 * @return
	 */
	List<InventoryBillDetail> loadAllInventoryBillDetails();
	/**
	 * 根据传入的设备清单对象，保存该设备清单对象
	 * 
	 * @param inventorybill	设备清单对象
	 * @return
	 */
	@Transactional
	void storeInventoryBillDetail(InventoryBillDetail inventoryBillDetail);
	/**
	 * 根据传入的设备清单集合,失效集合中的清单
	 * @param inventorybill 设备清单集合
	 */
	@Transactional
	void deleteInventoryBillDetail(InventoryBillDetail inventoryBillDetail);
	@Transactional
	void deleteAllInventoryBillDetails(Collection<InventoryBillDetail> inventoryBillDetails);
}
