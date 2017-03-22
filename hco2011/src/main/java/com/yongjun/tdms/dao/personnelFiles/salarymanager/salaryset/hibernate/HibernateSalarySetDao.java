/*    */ package com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryset.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryset.SalarySetDao;
/*    */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateSalarySetDao extends BaseHibernateDao
/*    */   implements SalarySetDao
/*    */ {
/*    */   public void storeSalarySet(SalarySet salarySet)
/*    */   {
/* 22 */     store(salarySet);
/*    */   }
/*    */ 
/*    */   public SalarySet loadSalarySet(Long salarySetId)
/*    */   {
/* 31 */     return (SalarySet)load(SalarySet.class, salarySetId);
/*    */   }
/*    */ 
/*    */   public List<SalarySet> loadAllSalarySets()
/*    */   {
/* 39 */     return loadAll(SalarySet.class);
/*    */   }
/*    */ 
/*    */   public List<SalarySet> loadAllSalarySet(Long[] salarySetIds)
/*    */   {
/* 48 */     return loadAll(SalarySet.class, salarySetIds);
/*    */   }
/*    */ 
/*    */   public void deleteSalarySet(SalarySet salarySet)
/*    */   {
/* 56 */     delete(salarySet);
/*    */   }
/*    */ 
/*    */   public void deleteAllSalarySet(List<SalarySet> salarySets)
/*    */   {
/* 64 */     deleteAll(salarySets);
/*    */   }
/*    */ 
/*    */   public List<SalarySet> loadByKey(String key, Object value)
/*    */     throws DaoException
/*    */   {
/* 75 */     return loadByKey(SalarySet.class, key, value);
/*    */   }
/*    */ 
/*    */   public List<SalarySet> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*    */     throws DaoException
/*    */   {
/* 85 */     return loadByKeyArray(SalarySet.class, keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryset.hibernate.HibernateSalarySetDao
 * JD-Core Version:    0.6.2
 */