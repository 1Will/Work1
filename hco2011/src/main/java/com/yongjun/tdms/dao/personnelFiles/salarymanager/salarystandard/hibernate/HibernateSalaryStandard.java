/*     */ package com.yongjun.tdms.dao.personnelFiles.salarymanager.salarystandard.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salarystandard.SalaryStandardDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateSalaryStandard extends BaseHibernateDao
/*     */   implements SalaryStandardDao
/*     */ {
/*     */   public void storeSalaryStandard(SalaryStandard t)
/*     */   {
/*  24 */     store(t);
/*     */   }
/*     */ 
/*     */   public SalaryStandard loadSalaryStandard(Long id)
/*     */   {
/*  35 */     return (SalaryStandard)load(SalaryStandard.class, id);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadSalaryStandard()
/*     */   {
/*  43 */     return loadAll(SalaryStandard.class);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadAllSalaryStandard(Long[] tIds)
/*     */   {
/*  52 */     return loadAll(SalaryStandard.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalaryStandard(SalaryStandard t)
/*     */   {
/*  60 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalaryStandard(List<SalaryStandard> ts)
/*     */   {
/*  70 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  82 */     return loadByKey(SalaryStandard.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  92 */     return loadByKeyArray(SalaryStandard.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 101 */     String hql = "select c.code from SalaryStandard as c where  c.code like '%" + code + "%'";
/* 102 */     List codeList = getSession().createQuery(hql).list();
/* 103 */     if (null!= codeList && codeList.size() > 0) {
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
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.salarymanager.salarystandard.hibernate.HibernateSalaryStandard
 * JD-Core Version:    0.6.2
 */