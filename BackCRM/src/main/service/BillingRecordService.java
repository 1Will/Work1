package main.service;


import org.hibernate.Session;

import main.pojo.BillingRecord;

public interface BillingRecordService {
	
	  public BillingRecord getBillingRecordById(Long id); //����id ��ȡ��Ʊ��Ϣ����

	  public void updateBillingRecordById(BillingRecord billingRecord); //����id ���¿�Ʊ��Ϣ����
	    
	    public Session getSuperSession();
	    
	    
	    
}
