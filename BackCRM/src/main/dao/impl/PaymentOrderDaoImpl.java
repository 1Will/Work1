package main.dao.impl;

import main.dao.PaymentOrderDao;
import main.pojo.PaymentOrder;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class PaymentOrderDaoImpl extends HibernateDaoSupport implements PaymentOrderDao {
	

	@Override
	public PaymentOrder getPaymentOrderById(Long id) {
		PaymentOrder paymentOrder = null;
		try {
			paymentOrder=(PaymentOrder) this.getSession().load(PaymentOrder.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return paymentOrder;
	}
	
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}
	
    
	
}
