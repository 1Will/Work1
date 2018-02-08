package com.yongjun.tdms.dao.asset.spare.Inventory.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.Inventory.SpareInventoryDetailDao;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;

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

	public SpareInventoryDetail getTop1SpareInventoryDetailByDetailId(Long detailId) {
		
		SpareInventoryDetail spareInventoryDetail = null;
		try {
			String hql = "FROM 	SpareInventoryDetail spareInventoryDetail WHERE spareInventoryDetail.id = :detailId";
			Query query = getSession().createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			query.setParameter("detailId",detailId);
				
			spareInventoryDetail = (SpareInventoryDetail)query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return spareInventoryDetail;
	}
	
}
