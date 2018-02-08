package com.yongjun.tdms.dao.asset.spare.Inventory;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;

public interface SpareInventoryDao {
	List<SpareInventory> loadAllSpareInventory(Long[] spareInventoryIds);

	SpareInventory loadSpareInventory(Long spareInventoryId);

	void storeSpareInventory(SpareInventory spareInventory);

	void deleteSpareInventory(SpareInventory spareInventory);

	void deleteAllSpareInventory(Collection<SpareInventory> spareInventorys);

}
