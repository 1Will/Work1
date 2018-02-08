package main.service.impl;

import org.hibernate.Session;

import main.dao.SupplierDao;
import main.pojo.Supplier;
import main.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {
         
 private SupplierDao supplierDao;

@Override
public Supplier getSupplierById(Long id) {
	return supplierDao.getSupplierById(id);
}

@Override
public Session getSuperSession() {
	return supplierDao.getSuperSession();
}

public SupplierDao getSupplierDao() {
	return supplierDao;
}

public void setSupplierDao(SupplierDao supplierDao) {
	this.supplierDao = supplierDao;
}

   
   

	
	
}
