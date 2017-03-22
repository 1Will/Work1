/*     */ package com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryset.SalarySetDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.SalarySetManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSalarySetManager extends BaseManager
/*     */   implements SalarySetManager
/*     */ {
/*     */   private final SalarySetDao salarySetDao;
/*     */ 
/*     */   public DefaultSalarySetManager(SalarySetDao salarySetDao)
/*     */   {
/*  27 */     this.salarySetDao = salarySetDao;
/*     */   }
/*     */ 
/*     */   public void storeSalarySet(SalarySet salarySet)
/*     */   {
/*  34 */     this.salarySetDao.storeSalarySet(salarySet);
/*     */   }
/*     */ 
/*     */   public SalarySet loadSalarySet(Long salarySetId)
/*     */   {
/*  43 */     return this.salarySetDao.loadSalarySet(salarySetId);
/*     */   }
/*     */ 
/*     */   public List<SalarySet> loadAllSalarySets()
/*     */   {
/*  51 */     return this.salarySetDao.loadAllSalarySets();
/*     */   }
/*     */ 
/*     */   public List<SalarySet> loadAllSalarySet(Long[] salarySetIds)
/*     */   {
/*  60 */     return this.salarySetDao.loadAllSalarySet(salarySetIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalarySet(SalarySet salarySet)
/*     */   {
/*  68 */     this.salarySetDao.deleteSalarySet(salarySet);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalarySet(List<SalarySet> salarySetes)
/*     */   {
/*  76 */     this.salarySetDao.deleteAllSalarySet(salarySetes);
/*     */   }
/*     */ 
/*     */   public List<SalarySet> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.salarySetDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<SalarySet> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  97 */     return this.salarySetDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledSalarySets(Collection<SalarySet> salarySets)
/*     */   {
/* 104 */     for (SalarySet s : salarySets) {
/* 105 */       s.setDisabled(true);
/* 106 */       this.salarySetDao.storeSalarySet(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledSalarySets(Collection<SalarySet> salarySetes)
/*     */   {
/* 115 */     for (SalarySet s : salarySetes) {
/* 116 */       s.setDisabled(false);
/* 117 */       this.salarySetDao.storeSalarySet(s);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.pojo.DefaultSalarySetManager
 * JD-Core Version:    0.6.2
 */