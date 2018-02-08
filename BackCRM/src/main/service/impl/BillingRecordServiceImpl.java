package main.service.impl;

import org.hibernate.Session;

import main.dao.BillingRecordDao;
import main.pojo.BillingRecord;
import main.service.BillingRecordService;

public class BillingRecordServiceImpl implements BillingRecordService {
         
   private BillingRecordDao billingRecordDao;

   
   @Override
   public BillingRecord getBillingRecordById(Long id) {
	   return billingRecordDao.getBillingRecordById(id);
   }
   
   @Override
   public void updateBillingRecordById(BillingRecord billingRecord) {
        billingRecordDao.updateBillingRecordById(billingRecord);	   
   }
   
   @Override
   public Session getSuperSession() {
	   return billingRecordDao.getSuperSession();
   }


public BillingRecordDao getBillingRecordDao() {
	return billingRecordDao;
}

public void setBillingRecordDao(BillingRecordDao billingRecordDao) {
	this.billingRecordDao = billingRecordDao;
}



	
	
}
