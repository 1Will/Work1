/*     */ package com.yongjun.tdms.service.financeManagement.paymentdetails.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.financeManagement.paymentdetails.PaymentDetailsDao;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentdetails.PaymentDetails;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentdetails.PaymentDetailsManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultPaymentDetailsManager extends BaseManager
/*     */   implements PaymentDetailsManager
/*     */ {
/*     */   private final PaymentDetailsDao paymentDetailsDao;
/*     */ 
/*     */   public DefaultPaymentDetailsManager(PaymentDetailsDao paymentDetailsDao)
/*     */   {
/*  26 */     this.paymentDetailsDao = paymentDetailsDao;
/*     */   }
/*     */ 
/*     */   public void storePaymentDetails(PaymentDetails t)
/*     */   {
/*  34 */     this.paymentDetailsDao.storePaymentDetails(t);
/*     */   }
/*     */ 
/*     */   public PaymentDetails loadPaymentDetails(Long id)
/*     */   {
/*  43 */     return this.paymentDetailsDao.loadPaymentDetails(id);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadPaymentDetails()
/*     */   {
/*  51 */     return this.paymentDetailsDao.loadPaymentDetails();
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadAllPaymentDetails(Long[] tIds)
/*     */   {
/*  60 */     return this.paymentDetailsDao.loadAllPaymentDetails(tIds);
/*     */   }
/*     */ 
/*     */   public void deletePaymentDetails(PaymentDetails t)
/*     */   {
/*  68 */     this.paymentDetailsDao.deletePaymentDetails(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllPaymentDetails(List<PaymentDetails> ts)
/*     */   {
/*  76 */     this.paymentDetailsDao.deleteAllPaymentDetails(ts);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.paymentDetailsDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<PaymentDetails> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  97 */     return this.paymentDetailsDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 105 */     return this.paymentDetailsDao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllPaymentDetails(List<PaymentDetails> ts)
/*     */   {
/* 112 */     for (PaymentDetails p : ts) {
/* 113 */       p.setDisabled(true);
/* 114 */       this.paymentDetailsDao.storePaymentDetails(p);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllPaymentDetails(List<PaymentDetails> ts)
/*     */   {
/* 123 */     for (PaymentDetails p : ts) {
/* 124 */       p.setDisabled(false);
/* 125 */       this.paymentDetailsDao.storePaymentDetails(p);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.financeManagement.paymentdetails.pojo.DefaultPaymentDetailsManager
 * JD-Core Version:    0.6.2
 */