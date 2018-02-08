package com.yongjun.tdms.dao.runmaintenance.trusteeship.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.trusteeship.*;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;

public class HibernateTrusteeship extends BaseHibernateDao implements
		TrusteeshipDao {

	public Trusteeship loadTrusteeship(Long TrusteeshipId) {

		return this.load(Trusteeship.class,TrusteeshipId);
	}

	public List<Trusteeship> loadTrusteeships(Long[] TrusteeshipIds) {

		return this.loadAll(Trusteeship.class,TrusteeshipIds);
	}

	public List<Trusteeship> loadTrusteeships() {

		return this.loadAll(Trusteeship.class);
	}

	public void storeTrusteeship(Trusteeship trusteeship) {
		this.store(trusteeship);

	}

	public void deleteTrusteeship(Trusteeship trusteeship) {
		this.delete(trusteeship);

	}

	public void deleteAllTrusteeships(Collection<Trusteeship> Trusteeships) {
		this.deleteAll(Trusteeships);

	}

}
