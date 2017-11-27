/*     */ package com.yongjun.tdms.dao.marketmanager.targetmanager.departmenttarget.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.targetmanager.departmenttarget.DepartmentTargetDao;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.departmenttarget.DepartmentTarget;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateDepartmentTarget extends BaseHibernateDao
/*     */   implements DepartmentTargetDao
/*     */ {
/*     */   public void deleteAllDepartmentTarget(List<DepartmentTarget> ts)
/*     */   {
/*  23 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public void deleteDepartmentTarget(DepartmentTarget t)
/*     */   {
/*  30 */     delete(t);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadAllDepartmentTarget(Long[] tIds)
/*     */   {
/*  38 */     return loadAll(DepartmentTarget.class, tIds);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  49 */     return loadByKey(DepartmentTarget.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  60 */     return loadByKeyArray(DepartmentTarget.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public DepartmentTarget loadDepartmentTarget(Long id)
/*     */   {
/*  68 */     return (DepartmentTarget)load(DepartmentTarget.class, id);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadDepartmentTarget()
/*     */   {
/*  75 */     return loadAll(DepartmentTarget.class);
/*     */   }
/*     */ 
/*     */   public void storeDepartmentTarget(DepartmentTarget t)
/*     */   {
/*  82 */     store(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  90 */     String hql = "select c.code from DepartmentTarget as c where  c.code like '%" + code + "%'";
/*  91 */     List codeList = getSession().createQuery(hql).list();
/*  92 */     if (null!= codeList && codeList.size() > 0) {
/*  93 */       List items = new ArrayList();
/*  94 */       for (int i = 0; i < codeList.size(); i++) {
/*  95 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  96 */         items.add(item);
/*     */       }
/*  98 */       Collections.sort(items);
/*  99 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.targetmanager.departmenttarget.hibernate.HibernateDepartmentTarget
 * JD-Core Version:    0.6.2
 */