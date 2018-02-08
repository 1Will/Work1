package main.service;


import org.hibernate.Session;

import main.pojo.FinancialManagement;

public interface FinancialManagementService {
	
	   public FinancialManagement getFinancialManagementById(Long id); //根据id 获取收款集合
	    
	    public void updateFinancialManagementById(FinancialManagement fManagement); //根据id 更新收款信息集合
	   
	    public Session getSuperSession();
	    
	    
	    
}
