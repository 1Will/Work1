package main.service;


import org.hibernate.Session;

import main.pojo.ContractManagement;

public interface ContractManagementService {
	
	   public ContractManagement getContractManagementById(Long id); //����id ��ȡ��ͬ����
	    
	    public void updateContractManagementById(ContractManagement cManagement); //����id ���º�ͬ����
	   
	    public Session getSuperSession();
	    
	    
	    
}
