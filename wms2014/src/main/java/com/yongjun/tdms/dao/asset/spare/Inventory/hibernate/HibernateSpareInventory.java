package com.yongjun.tdms.dao.asset.spare.Inventory.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.Inventory.SpareInventoryDao;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;

public class HibernateSpareInventory extends BaseHibernateDao implements
		SpareInventoryDao {

	public List<SpareInventory> loadAllSpareInventory(Long[] spareInventoryIds) {
		return this.loadAll(SpareInventory.class, spareInventoryIds);
	}

	public SpareInventory loadSpareInventory(Long spareInventoryId) {
		return this.load(SpareInventory.class, spareInventoryId);
	}

	public void storeSpareInventory(SpareInventory spareInventory) {
		this.store(spareInventory);
	}

	public void deleteSpareInventory(SpareInventory spareInventory) {
		this.delete(spareInventory);
	}

	public void deleteAllSpareInventory(
			Collection<SpareInventory> spareInventorys) {
		this.deleteAll(spareInventorys);
	}
}
