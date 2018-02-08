/*     */ package com.yongjun.tdms.dao.customerservicemanagement.repairs.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.repairs.RepairsDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateRepairs extends BaseHibernateDao
/*     */   implements RepairsDao
/*     */ {
/*     */   public void storeRepairs(Repairs t)
/*     */   {
/*  24 */     store(t);
/*     */   }
/*     */ 
/*     */   public Repairs loadRepairs(Long id)
/*     */   {
/*  35 */     return (Repairs)load(Repairs.class, id);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadRepairs()
/*     */   {
/*  43 */     return loadAll(Repairs.class);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadAllRepairs(Long[] tIds)
/*     */   {
/*  52 */     return loadAll(Repairs.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteRepairs(Repairs t)
/*     */   {
/*  60 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllRepairs(List<Repairs> ts)
/*     */   {
/*  70 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  82 */     return loadByKey(Repairs.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  92 */     return loadByKeyArray(Repairs.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 101 */     String hql = "select c.code from Repairs as c where  c.code like '%" + code + "%'";
/* 102 */     List codeList = getSession().createQuery(hql).list();
/* 103 */     if (null!=codeList && codeList.size() > 0) {
/* 104 */       List items = new ArrayList();
/* 105 */       for (int i = 0; i < codeList.size(); i++) {
/* 106 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 107 */         items.add(item);
/*     */       }
/* 109 */       Collections.sort(items);
/* 110 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customerservicemanagement.repairs.hibernate.HibernateRepairs
 * JD-Core Version:    0.6.2
 */