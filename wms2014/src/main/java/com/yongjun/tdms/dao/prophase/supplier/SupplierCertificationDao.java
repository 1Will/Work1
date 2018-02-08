package com.yongjun.tdms.dao.prophase.supplier;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.prophase.supplier.SupplierCertification;

public interface SupplierCertificationDao {
	
	void storeCertification(SupplierCertification supplierCertification);

	SupplierCertification loadCertification(Long supplierCertificationIds);

	void deleteCertification(SupplierCertification supplierCertification);

	void deleteAllCertifications(Collection<SupplierCertification> supplierCertificationIds);

	List<SupplierCertification> loadAllCertification(Long[] supplierCertificationIds);
	
	List<SupplierCertification> loadAllCertification();
}
