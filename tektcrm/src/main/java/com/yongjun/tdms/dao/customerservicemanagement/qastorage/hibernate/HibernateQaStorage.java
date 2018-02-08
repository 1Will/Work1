/*     */ package com.yongjun.tdms.dao.customerservicemanagement.qastorage.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.qastorage.QaStorageDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.qastorage.QaStorage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateQaStorage extends BaseHibernateDao
/*     */   implements QaStorageDao
/*     */ {
/*     */   public void deleteAllQaStorage(List<QaStorage> ts)
/*     */   {
/*  24 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public void deleteQaStorage(QaStorage t)
/*     */   {
/*  32 */     delete(t);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadAllQaStorage(Long[] tIds)
/*     */   {
/*  40 */     return loadAll(QaStorage.class, tIds);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  51 */     return loadByKey(QaStorage.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  62 */     return loadByKeyArray(QaStorage.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public QaStorage loadQaStorage(Long id)
/*     */   {
/*  71 */     return (QaStorage)load(QaStorage.class, id);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadQaStorage()
/*     */   {
/*  78 */     return loadAll(QaStorage.class);
/*     */   }
/*     */ 
/*     */   public void storeQaStorage(QaStorage t)
/*     */   {
/*  86 */     store(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  94 */     String hql = "select c.code from QaStorage as c where  c.code like '%" + code + "%'";
/*  95 */     List codeList = getSession().createQuery(hql).list();
/*  96 */     if (null!= codeList && codeList.size() > 0) {
/*  97 */       List items = new ArrayList();
/*  98 */       for (int i = 0; i < codeList.size(); i++) {
/*  99 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 100 */         items.add(item);
/*     */       }
/* 102 */       Collections.sort(items);
/* 103 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customerservicemanagement.qastorage.hibernate.HibernateQaStorage
 * JD-Core Version:    0.6.2
 */