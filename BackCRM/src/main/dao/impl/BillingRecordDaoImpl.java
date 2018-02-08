package main.dao.impl;

import main.dao.BillingRecordDao;
import main.pojo.BillingRecord;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BillingRecordDaoImpl extends HibernateDaoSupport implements BillingRecordDao {
	

	@Override
	public BillingRecord getBillingRecordById(Long id) {
		BillingRecord billingRecord = null;
		try {
			billingRecord=(BillingRecord) this.getSession().load(BillingRecord.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return billingRecord;
	}
	
	@Override
	public void updateBillingRecordById(BillingRecord billingRecord) {
	      try {
			this.getHibernateTemplate().merge(billingRecord);
		} catch (Exception e) {
		       e.printStackTrace();
		}	
	}
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}


	
    
	
}
