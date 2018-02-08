package com.yongjun.tdms.service.asset.spare.Inventory.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.Inventory.SpareInventoryDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryDetailManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;

public class DefaultSpareInventoryManager extends BaseManager implements
		SpareInventoryManager {
	private final SpareInventoryDao spareInventoryDao;
	
	private final SpareInventoryDetailManager spareInventoryDetailManager;

	private final SpareManager spareManager;
	
	private final SequenceManager spareInventorySequenceManager;
	//private final SequenceManager sequenceManager;

	public DefaultSpareInventoryManager(SpareInventoryDao spareInventoryDao,SpareInventoryDetailManager spareInventoryDetailManager
			,SpareManager spareManager,SequenceManager spareInventorySequenceManager
			) {
		this.spareInventoryDao = spareInventoryDao;
		this.spareManager = spareManager;
		this.spareInventoryDetailManager=spareInventoryDetailManager;
		this.spareInventorySequenceManager = spareInventorySequenceManager;
		//this.sequenceManager=sequenceManager;
	}

	public List<SpareInventory> loadAllSpareInventorys(Long[] spareInventoryIds) {
		return spareInventoryDao.loadAllSpareInventory(spareInventoryIds);
	}

	public SpareInventory loadSpareInventory(Long spareInventoryId) {
		return spareInventoryDao.loadSpareInventory(spareInventoryId);
	}

	public void storeSpareInventory(SpareInventory spareInventory) {
		if (spareInventory.isNew()) {
			String spareInventoryNo = (String) spareInventorySequenceManager.generate("-");
			spareInventory.setInventoryNo(spareInventoryNo);
		}
		spareInventoryDao.storeSpareInventory(spareInventory);
	}

	public void storeSpareInventory(SpareInventory spareInventory,
			String inventorySpareStock) {
		storeSpareInventory(spareInventory);
		retriveSpareStock(spareInventory);
		String[] ary = inventorySpareStock.split(",");
		Spare spare = new Spare();
		SpareInventoryDetail spareInventoryDetail;
		for (int i = 0; i < ary.length; i = i + 2) {
			spareInventoryDetail=new SpareInventoryDetail();
			spare = spareManager.loasSpare(Long.valueOf(ary[i]));
			spareInventoryDetail.setSpare(spare);
			spareInventoryDetail.setInventory(spareInventory);
			spareInventoryDetail.setCurrentSysStocks(spare.getStocks());
			spareInventoryDetail.setInventoryNum(Long.valueOf(ary[i+1]));
			spareInventoryDetailManager.storeSpareInventoryDetail(spareInventoryDetail);
			spare.setStocks(Long.valueOf(ary[i+1]));
			spareManager.storeSpare(spare);
		}
	}
	
	private void retriveSpareStock(SpareInventory spareInventory){
		Spare spare;
		for(SpareInventoryDetail detail:spareInventory.getInventoryDetails()){
			spare=detail.getSpare();
			spare.setStocks(detail.getCurrentSysStocks());
			spareManager.storeSpare(spare);
		}
		spareInventory.getInventoryDetails().clear();
	}

	public void deleteSpareInventory(SpareInventory spareInventory) {
		spareInventoryDao.deleteSpareInventory(spareInventory);
	}
   //TODO 失效所有的盘点单
	public void disabledAllInventoryBill(Collection<SpareInventory> spareInventorys) {
		for(SpareInventory spares:spareInventorys){
			spares.setDisabled(true);
			spareInventoryDao.storeSpareInventory(spares);
		}
		
	}
   //TODO 有效所有的盘点单
	public void enabledAllInventoryBill(Collection<SpareInventory> spareInventorys) {
		for(SpareInventory spares:spareInventorys){
			spares.setDisabled(false);
			spareInventoryDao.storeSpareInventory(spares);
		}
		
	}
}
