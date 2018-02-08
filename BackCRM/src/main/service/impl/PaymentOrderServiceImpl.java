package main.service.impl;

import org.hibernate.Session;

import main.dao.PaymentOrderDao;
import main.pojo.PaymentOrder;
import main.service.PaymentOrderService;

public class PaymentOrderServiceImpl implements PaymentOrderService {
         
   private PaymentOrderDao paymentOrderDao;

@Override
public PaymentOrder getPaymentOrderById(Long id) {
	return paymentOrderDao.getPaymentOrderById(id);
}

@Override
public Session getSuperSession() {
	return paymentOrderDao.getSuperSession();
}

public PaymentOrderDao getPaymentOrderDao() {
	return paymentOrderDao;
}

public void setPaymentOrderDao(PaymentOrderDao paymentOrderDao) {
	this.paymentOrderDao = paymentOrderDao;
}

   
   

	
	
}
