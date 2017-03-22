/*     */ package com.yongjun.tdms.dao.personnelFiles.salarymanager.salarydetail.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salarydetail.SalaryDetailDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarydetail.SalaryDetail;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateSalaryDetail extends BaseHibernateDao
/*     */   implements SalaryDetailDao
/*     */ {
/*     */   public void storeSalaryDetail(SalaryDetail t)
/*     */   {
/*  24 */     store(t);
/*     */   }
/*     */ 
/*     */   public SalaryDetail loadSalaryDetail(Long id)
/*     */   {
/*  35 */     return (SalaryDetail)load(SalaryDetail.class, id);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadSalaryDetail()
/*     */   {
/*  43 */     return loadAll(SalaryDetail.class);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadAllSalaryDetail(Long[] tIds)
/*     */   {
/*  52 */     return loadAll(SalaryDetail.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalaryDetail(SalaryDetail t)
/*     */   {
/*  60 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalaryDetail(List<SalaryDetail> ts)
/*     */   {
/*  70 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  82 */     return loadByKey(SalaryDetail.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  92 */     return loadByKeyArray(SalaryDetail.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 101 */     String hql = "select c.code from SalaryDetail as c where  c.code like '%" + code + "%'";
/* 102 */     List codeList = getSession().createQuery(hql).list();
/* 103 */     if (codeList.size() > 0) {
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
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.salarymanager.salarydetail.hibernate.HibernateSalaryDetail
 * JD-Core Version:    0.6.2
 */