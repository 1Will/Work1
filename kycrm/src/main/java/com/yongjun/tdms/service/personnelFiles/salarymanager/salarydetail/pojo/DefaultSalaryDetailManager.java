/*     */ package com.yongjun.tdms.service.personnelFiles.salarymanager.salarydetail.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salarydetail.SalaryDetailDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarydetail.SalaryDetail;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salarydetail.SalaryDetailManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSalaryDetailManager extends BaseManager
/*     */   implements SalaryDetailManager
/*     */ {
/*     */   private final SalaryDetailDao dao;
/*     */ 
/*     */   public DefaultSalaryDetailManager(SalaryDetailDao dao)
/*     */   {
/*  23 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeSalaryDetail(SalaryDetail t)
/*     */   {
/*  33 */     this.dao.storeSalaryDetail(t);
/*     */   }
/*     */ 
/*     */   public SalaryDetail loadSalaryDetail(Long id)
/*     */   {
/*  42 */     return this.dao.loadSalaryDetail(id);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadSalaryDetail()
/*     */   {
/*  50 */     return this.dao.loadSalaryDetail();
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadAllSalaryDetail(Long[] tIds)
/*     */   {
/*  60 */     return this.dao.loadAllSalaryDetail(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalaryDetail(SalaryDetail t)
/*     */   {
/*  68 */     this.dao.deleteSalaryDetail(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalaryDetail(List<SalaryDetail> ts)
/*     */   {
/*  76 */     this.dao.deleteAllSalaryDetail(ts);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<SalaryDetail> loadByKeyArray(String[] keyNames, Object[] keyValues)
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
/*     */   public void disabledAllSalaryDetail(List<SalaryDetail> ts)
/*     */   {
/* 113 */     for (SalaryDetail contractManagement : ts) {
/* 114 */       contractManagement.setDisabled(true);
/* 115 */       this.dao.storeSalaryDetail(contractManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllSalaryDetail(List<SalaryDetail> ts)
/*     */   {
/* 124 */     for (SalaryDetail contractManagement : ts) {
/* 125 */       contractManagement.setDisabled(false);
/* 126 */       this.dao.storeSalaryDetail(contractManagement);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salarydetail.pojo.DefaultSalaryDetailManager
 * JD-Core Version:    0.6.2
 */