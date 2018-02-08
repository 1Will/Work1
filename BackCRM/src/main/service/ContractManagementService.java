package main.service;


import org.hibernate.Session;

import main.pojo.ContractManagement;

public interface ContractManagementService {
	
	   public ContractManagement getContractManagementById(Long id); //根据id 获取合同集合
	    
	    public void updateContractManagementById(ContractManagement cManagement); //根据id 更新合同集合
	   
	    public Session getSuperSession();
	    
	    
	    
}
