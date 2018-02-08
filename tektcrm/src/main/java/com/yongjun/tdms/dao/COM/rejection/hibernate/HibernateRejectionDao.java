/*     */ package com.yongjun.tdms.dao.COM.rejection.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.COM.rejection.RejectionDao;
/*     */ import com.yongjun.tdms.model.COM.rejection.Rejection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateRejectionDao extends BaseHibernateDao
/*     */   implements RejectionDao
/*     */ {
/*     */   public void storeRejection(Rejection rejection)
/*     */   {
/*  25 */     store(rejection);
/*     */   }
/*     */ 
/*     */   public void deleteRejection(Rejection rejection)
/*     */   {
/*  33 */     deleteRejection(rejection);
/*     */   }
/*     */ 
/*     */   public void deleteAllRejection(Collection<Rejection> rejections)
/*     */   {
/*  41 */     deleteAll(rejections);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadAllRejection(Long[] rejectionIds)
/*     */   {
/*  50 */     return loadAll(Rejection.class, rejectionIds);
/*     */   }
/*     */ 
/*     */   public Rejection loadRejection(Long rejectionId)
/*     */   {
/*  59 */     return (Rejection)load(Rejection.class, rejectionId);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadAllRejection()
/*     */   {
/*  67 */     return loadAll(Rejection.class);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  78 */     return loadByKey(Rejection.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  89 */     return loadByKeyArray(Rejection.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  98 */     String hql = "select r.code from Rejection as r where  r.code like '%" + code + "%'";
/*  99 */     List codeList = getSession().createQuery(hql).list();
/* 100 */     if (null!=codeList && codeList.size() > 0) {
/* 101 */       List items = new ArrayList();
/* 102 */       for (int i = 0; i < codeList.size(); i++) {
/* 103 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 104 */         items.add(item);
/*     */       }
/* 106 */       Collections.sort(items);
/* 107 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 109 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.rejection.hibernate.HibernateRejectionDao
 * JD-Core Version:    0.6.2
 */