package com.yongjun.tdms.dao.runmaintenance.trusteeship.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.trusteeship.TrusteeshipDetailDao;
import com.yongjun.tdms.model.runmaintenance.trusteeship.TrusteeshipDetail;

public class HibernateTrusteeshipDetail extends BaseHibernateDao implements
		TrusteeshipDetailDao {

	public TrusteeshipDetail loadTrusteeshipDetail(Long TrusteeshipDetailId) {

		return this.load(TrusteeshipDetail.class, TrusteeshipDetailId);
	}

	
	public List<TrusteeshipDetail> loadTrusteeshipDetails() {

		return this.loadAll(TrusteeshipDetail.class);
	}

	public void storeTrusteeshipDetail(TrusteeshipDetail trusteeshipDetail) {
		this.store(trusteeshipDetail);

	}

	public void deleteTrusteeshipDetail(TrusteeshipDetail trusteeshipDetail) {

		this.delete(trusteeshipDetail);
	}

	public void deleteAllTrusteeshipDetails(Collection<TrusteeshipDetail> trusteeshipDetails) {
			
		this.deleteAll(trusteeshipDetails);

	}

	public List<TrusteeshipDetail> loadAllTrusteeshipDetails(Long[] TrusteeshipDetailIds) {
		return this.loadAll(TrusteeshipDetail.class, TrusteeshipDetailIds);
	}

}
