/*     */ package com.yongjun.tdms.dao.marketmanager.targetmanager.individualtarget.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.targetmanager.individualtarget.IndividualTargetDao;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.individualtarget.IndividualTarget;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateIndividualTarget extends BaseHibernateDao
/*     */   implements IndividualTargetDao
/*     */ {
/*     */   public void deleteAllIndividualTarget(List<IndividualTarget> ts)
/*     */   {
/*  24 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public void deleteIndividualTarget(IndividualTarget t)
/*     */   {
/*  31 */     delete(t);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadAllIndividualTarget(Long[] tIds)
/*     */   {
/*  39 */     return loadAll(IndividualTarget.class, tIds);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  50 */     return loadByKey(IndividualTarget.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  61 */     return loadByKeyArray(IndividualTarget.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public IndividualTarget loadIndividualTarget(Long id)
/*     */   {
/*  69 */     return (IndividualTarget)load(IndividualTarget.class, id);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadIndividualTarget()
/*     */   {
/*  76 */     return loadAll(IndividualTarget.class);
/*     */   }
/*     */ 
/*     */   public void storeIndividualTarget(IndividualTarget t)
/*     */   {
/*  83 */     store(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  91 */     String hql = "select c.code from IndividualTarget as c where  c.code like '%" + code + "%'";
/*  92 */     List codeList = getSession().createQuery(hql).list();
/*  93 */     if (codeList.size() > 0) {
/*  94 */       List items = new ArrayList();
/*  95 */       for (int i = 0; i < codeList.size(); i++) {
/*  96 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  97 */         items.add(item);
/*     */       }
/*  99 */       Collections.sort(items);
/* 100 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.targetmanager.individualtarget.hibernate.HibernateIndividualTarget
 * JD-Core Version:    0.6.2
 */