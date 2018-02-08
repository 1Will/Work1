package main.service;


import org.hibernate.Session;

import main.pojo.PaymentOrder;

public interface PaymentOrderService {
	
	  public PaymentOrder getPaymentOrderById(Long id); //根据id 获取付款信息集合
	    
	    public Session getSuperSession();
	    
	    
	    
}
