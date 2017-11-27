/*     */ package com.yongjun.tdms.service.customercontract.receivedpayments.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customercontract.receivedpayments.ReceivedPaymentsDao;
/*     */ import com.yongjun.tdms.model.customercontract.receivedpayments.ReceivedPayments;
/*     */ import com.yongjun.tdms.service.customercontract.receivedpayments.ReceivedPaymentsManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultReceivedPaymentsManager extends BaseManager
/*     */   implements ReceivedPaymentsManager
/*     */ {
/*     */   private ReceivedPaymentsDao receivedPaymentsDao;
/*     */ 
/*     */   public DefaultReceivedPaymentsManager(ReceivedPaymentsDao receivedPaymentsDao)
/*     */   {
/*  28 */     this.receivedPaymentsDao = receivedPaymentsDao;
/*     */   }
/*     */ 
/*     */   public void storeReceivedPayments(ReceivedPayments receivedPayments)
/*     */   {
/*  35 */     this.receivedPaymentsDao.storeReceivedPayments(receivedPayments);
/*     */   }
/*     */ 
/*     */   public void deleteReceivedPayments(ReceivedPayments receivedPayments)
/*     */   {
/*  43 */     this.receivedPaymentsDao.deleteReceivedPayments(receivedPayments);
/*     */   }
/*     */ 
/*     */   public void deleteAllReceivedPayments(Collection<ReceivedPayments> receivedPayments)
/*     */   {
/*  51 */     this.receivedPaymentsDao.deleteAllReceivedPayments(receivedPayments);
/*     */   }
/*     */ 
/*     */   public List<ReceivedPayments> loadAllReceivedPayments(Long[] receivedPaymentsIds)
/*     */   {
/*  60 */     return this.receivedPaymentsDao.loadAllReceivedPayments(receivedPaymentsIds);
/*     */   }
/*     */ 
/*     */   public ReceivedPayments loadReceivedPayments(Long receivedPaymentsId)
/*     */   {
/*  69 */     return this.receivedPaymentsDao.loadReceivedPayments(receivedPaymentsId);
/*     */   }
/*     */ 
/*     */   public List<ReceivedPayments> loadAllReceivedPayments()
/*     */   {
/*  77 */     return this.receivedPaymentsDao.loadAllReceivedPayments();
/*     */   }
/*     */ 
/*     */   public List<ReceivedPayments> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return this.receivedPaymentsDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ReceivedPayments> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  99 */     return this.receivedPaymentsDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllReceivedPayments(List<ReceivedPayments> receivedPayments)
/*     */   {
/* 106 */     for (ReceivedPayments r : receivedPayments) {
/* 107 */       r.setDisabled(true);
/* 108 */       this.receivedPaymentsDao.storeReceivedPayments(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllReceivedPayments(List<ReceivedPayments> receivedPayments)
/*     */   {
/* 116 */     for (ReceivedPayments r : receivedPayments) {
/* 117 */       r.setDisabled(false);
/* 118 */       this.receivedPaymentsDao.storeReceivedPayments(r);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.receivedpayments.pojo.DefaultReceivedPaymentsManager
 * JD-Core Version:    0.6.2
 */