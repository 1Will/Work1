package com.yongjun.tdms.service.asset.spare.pojo;

import java.util.Collection;
import java.util.List;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareInventoryDetailDao;
import com.yongjun.tdms.model.asset.spare.SpareInventoryDetail;
import com.yongjun.tdms.service.asset.spare.SpareInventoryDetailManager;

public class DefaultSpareInventoryDetailManager extends BaseManager implements
		SpareInventoryDetailManager {
	private final SpareInventoryDetailDao spareInventoryDetailDao;
	
	public DefaultSpareInventoryDetailManager(SpareInventoryDetailDao spareInventoryDetailDao){
		this.spareInventoryDetailDao=spareInventoryDetailDao;
	}
	public List<SpareInventoryDetail> loadAllSpareInventoryDetails(
			Long[] spareInventoryDetailIds){
		return spareInventoryDetailDao.loadAllSpareInventoryDetails(spareInventoryDetailIds);
	}

	public SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId){
		return spareInventoryDetailDao.loadSpareInventoryDetail(spareInventoryDetailId);
	}

	public void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail){
		spareInventoryDetailDao.storeSpareInventoryDetail(spareInventoryDetail);
	}

	public void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail){
		spareInventoryDetailDao.deleteSpareInventoryDetail(spareInventoryDetail);
	}

	public void deleteAllSpareInventoryDetail(
			Collection<SpareInventoryDetail> spareInventoryDetails){
		spareInventoryDetailDao.deleteAllSpareInventoryDetail(spareInventoryDetails);
	}
}
