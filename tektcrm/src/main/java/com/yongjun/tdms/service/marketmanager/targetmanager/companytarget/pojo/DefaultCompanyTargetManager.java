/*     */ package com.yongjun.tdms.service.marketmanager.targetmanager.companytarget.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.targetmanager.companytarget.CompanyTargetDao;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.companytarget.CompanyTarget;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.companytarget.CompanyTargetManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCompanyTargetManager extends BaseManager
/*     */   implements CompanyTargetManager
/*     */ {
/*     */   private final CompanyTargetDao dao;
/*     */ 
/*     */   public DefaultCompanyTargetManager(CompanyTargetDao dao)
/*     */   {
/*  28 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeCompanyTarget(CompanyTarget t)
/*     */   {
/*  35 */     this.dao.storeCompanyTarget(t);
/*     */   }
/*     */ 
/*     */   public CompanyTarget loadCompanyTarget(Long id)
/*     */   {
/*  44 */     return this.dao.loadCompanyTarget(id);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadCompanyTarget()
/*     */   {
/*  52 */     return this.dao.loadCompanyTarget();
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadAllCompanyTarget(Long[] tIds)
/*     */   {
/*  61 */     return this.dao.loadAllCompanyTarget(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteCompanyTarget(CompanyTarget t)
/*     */   {
/*  69 */     this.dao.deleteCompanyTarget(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllCompanyTarget(List<CompanyTarget> ts)
/*     */   {
/*  77 */     this.dao.deleteAllCompanyTarget(ts);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  88 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<CompanyTarget> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllCompanyTarget(List<CompanyTarget> ts)
/*     */   {
/* 106 */     for (CompanyTarget targetManagement : ts) {
/* 107 */       targetManagement.setDisabled(true);
/* 108 */       this.dao.storeCompanyTarget(targetManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllCompanyTarget(List<CompanyTarget> ts)
/*     */   {
/* 119 */     for (CompanyTarget targetManagement : ts) {
/* 120 */       targetManagement.setDisabled(false);
/* 121 */       this.dao.storeCompanyTarget(targetManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 130 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.targetmanager.companytarget.pojo.DefaultCompanyTargetManager
 * JD-Core Version:    0.6.2
 */