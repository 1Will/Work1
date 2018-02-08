package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.OperaterCertDao;
import com.yongjun.tdms.model.asset.device.OperateCert;

public class HibernateOperaterCert extends BaseHibernateDao implements OperaterCertDao{

	public List<OperateCert> loadAllOperateCert(Long[] operateCertIds) {
		return this.loadAll(OperateCert.class,operateCertIds);
	}

	public void deleteAllOperateCert(Collection<OperateCert> operateCert) {
		this.deleteAll(operateCert);
	}

	public OperateCert loadOperateCert(Long operateCertId) {
		return this.load(OperateCert.class,operateCertId);
	}

	public void storeOperateCert(OperateCert operateCert) {
		this.store(operateCert);
	}

}
