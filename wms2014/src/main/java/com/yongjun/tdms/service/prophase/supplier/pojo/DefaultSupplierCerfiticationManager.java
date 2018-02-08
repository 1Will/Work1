package com.yongjun.tdms.service.prophase.supplier.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.prophase.supplier.SupplierCertificationDao;
import com.yongjun.tdms.model.prophase.supplier.SupplierCertification;
import com.yongjun.tdms.service.prophase.supplier.SupplierCerfiticationManager;

public class DefaultSupplierCerfiticationManager implements SupplierCerfiticationManager{
    private final  SupplierCertificationDao supplierCerfiticatioDao;
    public DefaultSupplierCerfiticationManager(SupplierCertificationDao supplierCerfiticatioDao){
    	this.supplierCerfiticatioDao=supplierCerfiticatioDao;
    }
	public void storeCertification(SupplierCertification supplierCertification) {
		this.supplierCerfiticatioDao.storeCertification(supplierCertification);
		
	}

	public SupplierCertification loadCertification(Long supplierCertificationIds) {
		
		return this.supplierCerfiticatioDao.loadCertification(supplierCertificationIds);
	}

	public void deleteCertification(SupplierCertification supplierCertification) {
		this.supplierCerfiticatioDao.deleteCertification(supplierCertification);
		
	}

	public void deleteAllCertifications(Collection<SupplierCertification> supplierCertificationIds) {
		this.supplierCerfiticatioDao.deleteAllCertifications(supplierCertificationIds);
		
	}

	public List<SupplierCertification> loadAllCertification(Long[] supplierCertificationIds) {
		// TODO Auto-generated method stub
		return this.supplierCerfiticatioDao.loadAllCertification(supplierCertificationIds);
	}

	public List<SupplierCertification> loadAllCertification() {
		// TODO Auto-generated method stub
		return this.loadAllCertification();
	}

}
