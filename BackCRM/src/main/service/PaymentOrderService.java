package main.service;


import org.hibernate.Session;

import main.pojo.PaymentOrder;

public interface PaymentOrderService {
	
	  public PaymentOrder getPaymentOrderById(Long id); //����id ��ȡ������Ϣ����
	    
	    public Session getSuperSession();
	    
	    
	    
}
