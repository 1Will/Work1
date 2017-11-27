/*     */ package com.yongjun.tdms.service.financeManagement.paymentorder.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.financeManagement.paymentorder.PaymentorderDao;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentorder.PaymentorderManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultPaymentorderManager extends BaseManager
/*     */   implements PaymentorderManager
/*     */ {
/*     */   private final PaymentorderDao paymentorderDao;
			private final YongJunSequenceManager yongJunSequenceManager;
/*     */ 
/*     */   public DefaultPaymentorderManager(PaymentorderDao paymentorderDao,YongJunSequenceManager yongJunSequenceManager)
/*     */   {
/*  24 */     this.paymentorderDao = paymentorderDao;
			  this.yongJunSequenceManager = yongJunSequenceManager;
/*     */   }
/*     */ 
/*     */   public void storePaymentorder(Paymentorder t)
/*     */   {
			  if(t.isNew()){
				  t.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_PAYMENTORDER));
			  }
/*  31 */     this.paymentorderDao.storePaymentorder(t);
/*     */   }
/*     */ 
/*     */   public Paymentorder loadPaymentorder(Long id)
/*     */   {
/*  40 */     return this.paymentorderDao.loadPaymentorder(id);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadPaymentorder()
/*     */   {
/*  48 */     return this.paymentorderDao.loadPaymentorder();
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadAllPaymentorder(Long[] tIds)
/*     */   {
/*  58 */     return this.paymentorderDao.loadAllPaymentorder(tIds);
/*     */   }
/*     */ 
/*     */   public void deletePaymentorder(Paymentorder t)
/*     */   {
/*  66 */     this.paymentorderDao.deletePaymentorder(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllPaymentorder(List<Paymentorder> ts)
/*     */   {
/*  74 */     this.paymentorderDao.deleteAllPaymentorder(ts);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  85 */     return this.paymentorderDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  95 */     return this.paymentorderDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 103 */     return this.paymentorderDao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllPaymentorder(List<Paymentorder> ts)
/*     */   {
/* 110 */     for (Paymentorder paymentorder : ts) {
/* 111 */       paymentorder.setDisabled(true);
/* 112 */       this.paymentorderDao.storePaymentorder(paymentorder);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllPaymentorder(List<Paymentorder> ts)
/*     */   {
/* 121 */     for (Paymentorder paymentorder : ts) {
/* 122 */       paymentorder.setDisabled(false);
/* 123 */       this.paymentorderDao.storePaymentorder(paymentorder);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.financeManagement.paymentorder.pojo.DefaultPaymentorderManager
 * JD-Core Version:    0.6.2
 */