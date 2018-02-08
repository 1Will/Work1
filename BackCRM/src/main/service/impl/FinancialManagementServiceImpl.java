package main.service.impl;

import org.hibernate.Session;

import main.dao.FinancialManagementDao;
import main.pojo.FinancialManagement;
import main.service.FinancialManagementService;

public class FinancialManagementServiceImpl implements FinancialManagementService {
         
   private FinancialManagementDao financialManagementDao;


	@Override
	public FinancialManagement getFinancialManagementById(Long id) {
		return financialManagementDao.getFinancialManagementById(id);
	}
	@Override
	public void updateFinancialManagementById(FinancialManagement fManagement) {
        financialManagementDao.updateFinancialManagementById(fManagement);		
	}
   
	@Override
	public Session getSuperSession() {
		return financialManagementDao.getSuperSession();
	}

	public FinancialManagementDao getFinancialManagementDao() {
		return financialManagementDao;
	}

	public void setFinancialManagementDao(
			FinancialManagementDao financialManagementDao) {
		this.financialManagementDao = financialManagementDao;
	}


	
	
}
