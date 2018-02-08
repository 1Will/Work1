package com.yongjun.tdms.service.runmaintenance.inventory.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDetailDao;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBillDetail;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryBillDetailManager;

public class DefaultInventoryBillDetailManager extends BaseManager implements InventoryBillDetailManager{

	private InventoryBillDetailDao inventoryBillDetailDao;
	public DefaultInventoryBillDetailManager(InventoryBillDetailDao inventoryBillDetailDao){
		this.inventoryBillDetailDao=inventoryBillDetailDao;
		
	}
	
	public InventoryBillDetail loadInventoryBillDetail(Long inventoryBillDetailId) {
		return  inventoryBillDetailDao.loadInventoryBillDetail(inventoryBillDetailId);
		
	}

	public List<InventoryBillDetail> loadAllInventoryBillDetails(Long[] inventoryBillDetailIds) {
		
		return  inventoryBillDetailDao.loadAllInventoryBillDetails(inventoryBillDetailIds);
	}

	public List<InventoryBillDetail> loadAllInventoryBillDetails() {
		
		return inventoryBillDetailDao.loadAllInventoryBillDetails();
	}

	public void storeInventoryBillDetail(InventoryBillDetail inventoryBillDetail) {
		inventoryBillDetailDao.storeInventoryBillDetail(inventoryBillDetail);
		
	}

	public void deleteInventoryBillDetail(InventoryBillDetail inventoryBillDetail) {
	
		inventoryBillDetailDao.deleteInventoryBillDetail(inventoryBillDetail);
	}

	public void deleteAllInventoryBillDetails(Collection<InventoryBillDetail> inventoryBillDetails) {
		inventoryBillDetailDao.deleteAllInventoryBillDetails(inventoryBillDetails);
		
	}

}
