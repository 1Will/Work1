package com.yongjun.tdms.dao.prophase.supplier.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierCertificationDao;

import com.yongjun.tdms.model.prophase.supplier.SupplierCertification;

public class HibernateSupplierCertification extends BaseHibernateDao implements SupplierCertificationDao {

	public void storeCertification(SupplierCertification supplierCertification) {
	   this.store(supplierCertification);
		
	}

	public SupplierCertification loadCertification(Long supplierCertificationIds) {
		
		return this.load(SupplierCertification.class,supplierCertificationIds);
	}

	public void deleteCertification(SupplierCertification supplierCertification) {
		this.delete(supplierCertification);
		
	}

	public void deleteAllCertifications(Collection<SupplierCertification> supplierCertificationIds) {
		this.deleteAll(supplierCertificationIds);
		
	}

	public List<SupplierCertification> loadAllCertification(Long[] supplierCertificationIds) {
		
		return this.loadAll(SupplierCertification.class,supplierCertificationIds);
	}

	public List<SupplierCertification> loadAllCertification() {
		
		return this.loadAll(SupplierCertification.class);
	}

}
