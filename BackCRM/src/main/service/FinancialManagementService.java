package main.service;


import org.hibernate.Session;

import main.pojo.FinancialManagement;

public interface FinancialManagementService {
	
	   public FinancialManagement getFinancialManagementById(Long id); //����id ��ȡ�տ��
	    
	    public void updateFinancialManagementById(FinancialManagement fManagement); //����id �����տ���Ϣ����
	   
	    public Session getSuperSession();
	    
	    
	    
}
