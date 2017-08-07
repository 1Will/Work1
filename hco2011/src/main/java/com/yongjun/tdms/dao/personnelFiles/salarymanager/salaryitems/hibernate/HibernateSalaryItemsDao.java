/*     */ package com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryitems.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryitems.SalaryItemsDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateSalaryItemsDao extends BaseHibernateDao
/*     */   implements SalaryItemsDao
/*     */ {
/*     */   public void storeSalaryItems(SalaryItems salaryItems)
/*     */   {
/*  24 */     store(salaryItems);
/*     */   }
/*     */ 
/*     */   public SalaryItems loadSalaryItems(Long salaryItemsId)
/*     */   {
/*  33 */     return (SalaryItems)load(SalaryItems.class, salaryItemsId);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadAllSalaryItemss()
/*     */   {
/*  41 */     return loadAll(SalaryItems.class);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadAllSalaryItems(Long[] salaryItemsIds)
/*     */   {
/*  50 */     return loadAll(SalaryItems.class, salaryItemsIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalaryItems(SalaryItems salaryItems)
/*     */   {
/*  58 */     delete(salaryItems);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalaryItems(List<SalaryItems> salaryItemses)
/*     */   {
/*  66 */     deleteAll(salaryItemses);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  77 */     return loadByKey(SalaryItems.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  87 */     return loadByKeyArray(SalaryItems.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  95 */     String hql = "select s.code from SalaryItems as s where  s.code like '%" + code + "%'";
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
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryitems.hibernate.HibernateSalaryItemsDao
 * JD-Core Version:    0.6.2
 */