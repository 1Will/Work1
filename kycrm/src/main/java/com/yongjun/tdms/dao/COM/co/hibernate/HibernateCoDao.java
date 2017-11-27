/*     */ package com.yongjun.tdms.dao.COM.co.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.COM.co.CoDao;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateCoDao extends BaseHibernateDao
/*     */   implements CoDao
/*     */ {
/*     */   public void deleteAllCo(Collection<Co> cos)
/*     */   {
/*  25 */     deleteAll(cos);
/*     */   }
/*     */ 
/*     */   public void deleteCo(Co co)
/*     */   {
/*  32 */     delete(co);
/*     */   }
/*     */ 
/*     */   public List<Co> loadAllCo(Long[] coIds)
/*     */   {
/*  40 */     return loadAll(Co.class, coIds);
/*     */   }
/*     */ 
/*     */   public List<Co> loadAllCo()
/*     */   {
/*  47 */     return loadAll(Co.class);
/*     */   }
/*     */ 
/*     */   public List<Co> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  59 */     return loadByKey(Co.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Co> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  70 */     return loadByKeyArray(Co.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Co loadCo(Long coId)
/*     */   {
/*  78 */     return (Co)load(Co.class, coId);
/*     */   }
/*     */ 
/*     */   public void storeCo(Co co)
/*     */   {
/*  85 */     store(co);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  93 */     String hql = "select c.code from Co as c where  c.code like '%" + code + "%'";
/*  94 */     List codeList = getSession().createQuery(hql).list();
/*  95 */     if (null!=codeList&&codeList.size() > 0) {
/*  96 */       List items = new ArrayList();
/*  97 */       for (int i = 0; i < codeList.size(); i++) {
/*  98 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  99 */         items.add(item);
/*     */       }
/* 101 */       Collections.sort(items);
/* 102 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.co.hibernate.HibernateCoDao
 * JD-Core Version:    0.6.2
 */