/*     */ package com.yongjun.tdms.dao.COM.remit.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.COM.remit.RemitDao;
/*     */ import com.yongjun.tdms.model.COM.remit.Remit;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateRemitDao extends BaseHibernateDao
/*     */   implements RemitDao
/*     */ {
/*     */   public void deleteAllRemit(Collection<Remit> remits)
/*     */   {
/*  24 */     deleteAll(remits);
/*     */   }
/*     */ 
/*     */   public void deleteRemit(Remit remit)
/*     */   {
/*  31 */     delete(remit);
/*     */   }
/*     */ 
/*     */   public List<Remit> loadAllRemit(Long[] remitIds)
/*     */   {
/*  39 */     return loadAll(Remit.class, remitIds);
/*     */   }
/*     */ 
/*     */   public List<Remit> loadAllRemit()
/*     */   {
/*  46 */     return loadAll(Remit.class);
/*     */   }
/*     */ 
/*     */   public List<Remit> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  57 */     return loadByKey(Remit.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Remit> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  68 */     return loadByKeyArray(Remit.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Remit loadRemit(Long remitId)
/*     */   {
/*  76 */     return (Remit)load(Remit.class, remitId);
/*     */   }
/*     */ 
/*     */   public void storeRemit(Remit remit)
/*     */   {
/*  83 */     store(remit);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  92 */     String hql = "select r.code from Remit as r where  r.code like '%" + code + "%'";
/*  93 */     List codeList = getSession().createQuery(hql).list();
/*  94 */     if (codeList.size() > 0) {
/*  95 */       List items = new ArrayList();
/*  96 */       for (int i = 0; i < codeList.size(); i++) {
/*  97 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  98 */         items.add(item);
/*     */       }
/* 100 */       Collections.sort(items);
/* 101 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.remit.hibernate.HibernateRemitDao
 * JD-Core Version:    0.6.2
 */