/*    */ package com.yongjun.tdms.dao.customercontract.receivedpayments.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.customercontract.receivedpayments.ReceivedPaymentsDao;
/*    */ import com.yongjun.tdms.model.customercontract.receivedpayments.ReceivedPayments;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateReceivedPaymentsDao extends BaseHibernateDao
/*    */   implements ReceivedPaymentsDao
/*    */ {
/*    */   public void storeReceivedPayments(ReceivedPayments receivedPayments)
/*    */   {
/* 23 */     store(receivedPayments);
/*    */   }
/*    */ 
/*    */   public void deleteReceivedPayments(ReceivedPayments receivedPayments)
/*    */   {
/* 31 */     delete(receivedPayments);
/*    */   }
/*    */ 
/*    */   public void deleteAllReceivedPayments(Collection<ReceivedPayments> receivedPayments)
/*    */   {
/* 39 */     deleteAll(receivedPayments);
/*    */   }
/*    */ 
/*    */   public List<ReceivedPayments> loadAllReceivedPayments(Long[] receivedPaymentsIds)
/*    */   {
/* 48 */     return loadAll(ReceivedPayments.class, receivedPaymentsIds);
/*    */   }
/*    */ 
/*    */   public ReceivedPayments loadReceivedPayments(Long receivedPaymentsId)
/*    */   {
/* 57 */     return (ReceivedPayments)load(ReceivedPayments.class, receivedPaymentsId);
/*    */   }
/*    */ 
/*    */   public List<ReceivedPayments> loadAllReceivedPayments()
/*    */   {
/* 65 */     return loadAll(ReceivedPayments.class);
/*    */   }
/*    */ 
/*    */   public List<ReceivedPayments> loadByKey(String keyName, Object keyValue)
/*    */     throws DaoException
/*    */   {
/* 76 */     return loadByKey(ReceivedPayments.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ReceivedPayments> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*    */     throws DaoException
/*    */   {
/* 87 */     return loadByKeyArray(ReceivedPayments.class, keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.receivedpayments.hibernate.HibernateReceivedPaymentsDao
 * JD-Core Version:    0.6.2
 */