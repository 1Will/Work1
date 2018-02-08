package com.yongjun.tdms.dao.asset.spare.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.SpareInventoryDetailDao;
import com.yongjun.tdms.model.asset.spare.SpareInventoryDetail;

public class HibernateSpareInventoryDetail extends BaseHibernateDao implements
		SpareInventoryDetailDao{
	public List<SpareInventoryDetail> loadAllSpareInventoryDetails(
			Long[] spareInventoryDetailIds){
		return this.loadAll(SpareInventoryDetail.class,spareInventoryDetailIds);
	}

	public SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId){
		return this.load(SpareInventoryDetail.class,spareInventoryDetailId);
	}

	public void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail){
		this.store(spareInventoryDetail);
	}

	public void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail){
		this.delete(spareInventoryDetail);
	}

	public void deleteAllSpareInventoryDetail(
			Collection<SpareInventoryDetail> spareInventoryDetails){
		this.deleteAll(spareInventoryDetails);
	}
}
