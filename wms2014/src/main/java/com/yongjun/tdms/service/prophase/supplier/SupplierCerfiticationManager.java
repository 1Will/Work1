package com.yongjun.tdms.service.prophase.supplier;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.supplier.SupplierCertification;
@Transactional(readOnly = true)
public interface SupplierCerfiticationManager {
	@Transactional
	void storeCertification(SupplierCertification supplierCertification);

	SupplierCertification loadCertification(Long supplierCertificationIds);
	@Transactional
	void deleteCertification(SupplierCertification supplierCertification);
	@Transactional
	void deleteAllCertifications(Collection<SupplierCertification> supplierCertificationIds);

	List<SupplierCertification> loadAllCertification(Long[] supplierCertificationIds);
	
	List<SupplierCertification> loadAllCertification();
}
