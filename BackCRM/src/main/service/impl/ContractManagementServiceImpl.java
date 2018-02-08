package main.service.impl;

import org.hibernate.Session;

import main.dao.ContractManagementDao;
import main.pojo.ContractManagement;
import main.service.ContractManagementService;

public class ContractManagementServiceImpl implements ContractManagementService {
         
   private ContractManagementDao contractManagementDao;


	@Override
	public ContractManagement getContractManagementById(Long id) {
		return contractManagementDao.getContractManagementById(id);
	}
   
	@Override
	public void updateContractManagementById(ContractManagement cManagement) {
		 contractManagementDao.updateContractManagementById(cManagement);
	}

	@Override
	public Session getSuperSession() {
		return contractManagementDao.getSuperSession();
	}

	public ContractManagementDao getContractManagementDao() {
		return contractManagementDao;
	}

	public void setContractManagementDao(ContractManagementDao contractManagementDao) {
		this.contractManagementDao = contractManagementDao;
	}


	
	
	
	
	
	
	
}
