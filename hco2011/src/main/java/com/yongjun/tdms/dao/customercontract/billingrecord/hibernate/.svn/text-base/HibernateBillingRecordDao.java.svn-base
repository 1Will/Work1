/*    */ package com.yongjun.tdms.dao.customercontract.billingrecord.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.customercontract.billingrecord.BillingRecordDao;
/*    */ import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateBillingRecordDao extends BaseHibernateDao
/*    */   implements BillingRecordDao
/*    */ {
/*    */   public void storeBillingRecord(BillingRecord t)
/*    */   {
/* 22 */     store(t);
/*    */   }
/*    */ 
/*    */   public BillingRecord loadBillingRecord(Long id)
/*    */   {
/* 31 */     return (BillingRecord)load(BillingRecord.class, id);
/*    */   }
/*    */ 
/*    */   public List<BillingRecord> loadBillingRecord()
/*    */   {
/* 39 */     return loadAll(BillingRecord.class);
/*    */   }
/*    */ 
/*    */   public List<BillingRecord> loadAllBillingRecord(Long[] tIds)
/*    */   {
/* 48 */     return loadAll(BillingRecord.class, tIds);
/*    */   }
/*    */ 
/*    */   public void deleteBillingRecord(BillingRecord t)
/*    */   {
/* 56 */     delete(t);
/*    */   }
/*    */ 
/*    */   public void deleteAllBillingRecord(List<BillingRecord> ts)
/*    */   {
/* 64 */     deleteAll(ts);
/*    */   }
/*    */ 
/*    */   public List<BillingRecord> loadByKey(String key, Object value)
/*    */     throws DaoException
/*    */   {
/* 75 */     return loadByKey(BillingRecord.class, key, value);
/*    */   }
/*    */ 
/*    */   public List<BillingRecord> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*    */     throws DaoException
/*    */   {
/* 85 */     return loadByKeyArray(BillingRecord.class, keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.billingrecord.hibernate.HibernateBillingRecordDao
 * JD-Core Version:    0.6.2
 */