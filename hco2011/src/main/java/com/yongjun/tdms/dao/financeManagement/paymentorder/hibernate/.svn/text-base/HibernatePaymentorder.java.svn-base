/*     */ package com.yongjun.tdms.dao.financeManagement.paymentorder.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.financeManagement.paymentorder.PaymentorderDao;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernatePaymentorder extends BaseHibernateDao
/*     */   implements PaymentorderDao
/*     */ {
/*     */   public void storePaymentorder(Paymentorder t)
/*     */   {
/*  24 */     store(t);
/*     */   }
/*     */ 
/*     */   public Paymentorder loadPaymentorder(Long id)
/*     */   {
/*  33 */     return (Paymentorder)load(Paymentorder.class, id);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadPaymentorder()
/*     */   {
/*  41 */     return loadAll(Paymentorder.class);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadAllPaymentorder(Long[] tIds)
/*     */   {
/*  50 */     return loadAll(Paymentorder.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deletePaymentorder(Paymentorder t)
/*     */   {
/*  58 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllPaymentorder(List<Paymentorder> ts)
/*     */   {
/*  66 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  77 */     return loadByKey(Paymentorder.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<Paymentorder> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  87 */     return loadByKeyArray(Paymentorder.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  95 */     String hql = "select c.code from Paymentorder as c where  c.code like '%" + code + "%'";
/*  96 */     List codeList = getSession().createQuery(hql).list();
/*  97 */     if (codeList.size() > 0) {
/*  98 */       List items = new ArrayList();
/*  99 */       for (int i = 0; i < codeList.size(); i++) {
/* 100 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 101 */         items.add(item);
/*     */       }
/* 103 */       Collections.sort(items);
/* 104 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 106 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.financeManagement.paymentorder.hibernate.HibernatePaymentorder
 * JD-Core Version:    0.6.2
 */