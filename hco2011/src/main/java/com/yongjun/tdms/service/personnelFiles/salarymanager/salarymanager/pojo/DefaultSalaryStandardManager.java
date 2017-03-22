/*     */ package com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salarystandard.SalaryStandardDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.SalaryStandardManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSalaryStandardManager extends BaseManager
/*     */   implements SalaryStandardManager
/*     */ {
/*     */   private final SalaryStandardDao dao;
/*     */ 
/*     */   public DefaultSalaryStandardManager(SalaryStandardDao dao)
/*     */   {
/*  23 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeSalaryStandard(SalaryStandard t)
/*     */   {
/*  33 */     this.dao.storeSalaryStandard(t);
/*     */   }
/*     */ 
/*     */   public SalaryStandard loadSalaryStandard(Long id)
/*     */   {
/*  42 */     return this.dao.loadSalaryStandard(id);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadSalaryStandard()
/*     */   {
/*  50 */     return this.dao.loadSalaryStandard();
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadAllSalaryStandard(Long[] tIds)
/*     */   {
/*  60 */     return this.dao.loadAllSalaryStandard(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalaryStandard(SalaryStandard t)
/*     */   {
/*  68 */     this.dao.deleteSalaryStandard(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalaryStandard(List<SalaryStandard> ts)
/*     */   {
/*  76 */     this.dao.deleteAllSalaryStandard(ts);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<SalaryStandard> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 106 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllSalaryStandard(List<SalaryStandard> ts)
/*     */   {
/* 113 */     for (SalaryStandard contractManagement : ts) {
/* 114 */       contractManagement.setDisabled(true);
/* 115 */       this.dao.storeSalaryStandard(contractManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllSalaryStandard(List<SalaryStandard> ts)
/*     */   {
/* 124 */     for (SalaryStandard contractManagement : ts) {
/* 125 */       contractManagement.setDisabled(false);
/* 126 */       this.dao.storeSalaryStandard(contractManagement);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.pojo.DefaultSalaryStandardManager
 * JD-Core Version:    0.6.2
 */