/*     */ package com.yongjun.tdms.dao.marketmanager.targetmanager.companytarget.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.targetmanager.companytarget.CompanyTargetDao;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.companytarget.CompanyTarget;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateCompanyTarget extends BaseHibernateDao
/*     */   implements CompanyTargetDao
/*     */ {
/*     */   public void storeCompanyTarget(CompanyTarget t)
/*     */   {
/*  23 */     store(t);
/*     */   }
/*     */ 
/*     */   public CompanyTarget loadCompanyTarget(Long id)
/*     */   {
/*  32 */     return (CompanyTarget)load(CompanyTarget.class, id);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadCompanyTarget()
/*     */   {
/*  40 */     return loadAll(CompanyTarget.class);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadAllCompanyTarget(Long[] tIds)
/*     */   {
/*  49 */     return loadAll(CompanyTarget.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteCompanyTarget(CompanyTarget t)
/*     */   {
/*  58 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllCompanyTarget(List<CompanyTarget> ts)
/*     */   {
/*  66 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  77 */     return loadByKey(CompanyTarget.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  87 */     return loadByKeyArray(CompanyTarget.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  95 */     String hql = "select c.code from CompanyTarget as c where  c.code like '%" + code + "%'";
/*  96 */     List codeList = getSession().createQuery(hql).list();
/*  97 */     if (null!= codeList && codeList.size() > 0) {
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
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.targetmanager.companytarget.hibernate.HibernateCompanyTarget
 * JD-Core Version:    0.6.2
 */