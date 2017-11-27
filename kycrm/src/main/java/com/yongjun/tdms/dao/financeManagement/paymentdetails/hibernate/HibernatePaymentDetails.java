/*     */ package com.yongjun.tdms.dao.financeManagement.paymentdetails.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.financeManagement.paymentdetails.PaymentDetailsDao;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentdetails.PaymentDetails;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernatePaymentDetails extends BaseHibernateDao
/*     */   implements PaymentDetailsDao
/*     */ {
/*     */   public void storePaymentDetails(PaymentDetails t)
/*     */   {
/*  22 */     store(t);
/*     */   }
/*     */ 
/*     */   public PaymentDetails loadPaymentDetails(Long id)
/*     */   {
/*  31 */     return (PaymentDetails)load(PaymentDetails.class, id);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadPaymentDetails()
/*     */   {
/*  39 */     return loadAll(PaymentDetails.class);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadAllPaymentDetails(Long[] tIds)
/*     */   {
/*  48 */     return loadAll(PaymentDetails.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deletePaymentDetails(PaymentDetails t)
/*     */   {
/*  56 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllPaymentDetails(List<PaymentDetails> ts)
/*     */   {
/*  64 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  75 */     return loadByKey(PaymentDetails.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  85 */     return loadByKeyArray(PaymentDetails.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 104 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.financeManagement.paymentdetails.hibernate.HibernatePaymentDetails
 * JD-Core Version:    0.6.2
 */