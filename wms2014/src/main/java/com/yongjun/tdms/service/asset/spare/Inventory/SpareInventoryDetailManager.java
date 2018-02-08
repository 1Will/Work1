package com.yongjun.tdms.service.asset.spare.Inventory;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;

public interface SpareInventoryDetailManager {
	List<SpareInventoryDetail> loadAllSpareInventoryDetails(Long[] spareInventoryDetailIds);
	SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId);
	@Transactional
	void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);
	@Transactional
	void storeSpareInventoryBillDtlFromAccount(SpareInventory spareInventoryBill,String addSpareAccountIds);
	@Transactional
	void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);
	@Transactional
	void deleteAllSpareInventoryDetail(Collection<SpareInventoryDetail> spareInventoryDetails,SpareInventory spareInventoryBill);
	@Transactional
	void storeSpareInventoryBillDtl(SpareInventory spareInventoryBill,String allSpareInventoryBillDtlId,String allSpareInventoryBillDtlNumber, String allSpareInventoryBillDtlComment); 
	
}
