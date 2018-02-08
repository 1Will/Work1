/*     */ package com.yongjun.tdms.service.advisory.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.advisory.AdvisoryDao;
/*     */ import com.yongjun.tdms.model.advisory.Advisory;
/*     */ import com.yongjun.tdms.service.advisory.AdvisoryManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultAdvisoryManager extends BaseManager
/*     */   implements AdvisoryManager
/*     */ {
/*     */   private final AdvisoryDao advisoryDao;
/*     */ 
/*     */   public DefaultAdvisoryManager(AdvisoryDao advisoryDao)
/*     */   {
/*  19 */     this.advisoryDao = advisoryDao;
/*     */   }
/*     */   public void deleteAdvisory(Advisory advisory) {
/*  22 */     this.advisoryDao.deleteAdvisory(advisory);
/*     */   }
/*     */ 
/*     */   public void deleteAllAdvisory(Collection<Advisory> advisorys) {
/*  26 */     this.advisoryDao.deleteAllAdvisory(advisorys);
/*     */   }
/*     */ 
/*     */   public Advisory loadAdvisory(Long advisoryId) {
/*  30 */     return this.advisoryDao.loadAdvisory(advisoryId);
/*     */   }
/*     */ 
/*     */   public List<Advisory> loadAllAdvisory(Long[] advisoryIds) {
/*  34 */     return this.advisoryDao.loadAllAdvisory(advisoryIds);
/*     */   }
/*     */ 
/*     */   public List<Advisory> loadAllAdvisorys() {
/*  38 */     return this.advisoryDao.loadAllAdvisorys();
/*     */   }
/*     */ 
/*     */   public List<Advisory> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  42 */     return this.advisoryDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public void storeAdvisory(Advisory advisory)
/*     */   {
/*  47 */     setInfoIntegrity(advisory);
/*  48 */     this.advisoryDao.storeAdvisory(advisory);
/*     */   }
/*     */ 
/*     */   public void disabledAllAdvisorys(Collection<Advisory> advisorys) {
/*  52 */     for (Advisory advisory : advisorys) {
/*  53 */       advisory.setDisabled(true);
/*  54 */       this.advisoryDao.storeAdvisory(advisory);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllAdvisorys(Collection<Advisory> advisorys) {
/*  59 */     for (Advisory advisory : advisorys) {
/*  60 */       advisory.setDisabled(false);
/*  61 */       this.advisoryDao.storeAdvisory(advisory);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setInfoIntegrity(Advisory advisory)
/*     */   {
/*  70 */     float base = 60.0F;
/*  71 */     if ((null != advisory.getEnterpriseSynopsis()) && (!"".equals(advisory.getEnterpriseSynopsis()))) {
/*  72 */       base += 8.0F;
/*     */     }
/*  74 */     if ((null != advisory.getEffectDescribe()) && (!"".equals(advisory.getEffectDescribe()))) {
/*  75 */       base += 8.0F;
/*     */     }
/*  77 */     if ((null != advisory.getAdvisoryContent()) && (!"".equals(advisory.getAdvisoryContent()))) {
/*  78 */       base += 15.0F;
/*     */     }
/*     */ 
/*  81 */     if ((null != advisory.getLegalPerson()) && (!"".equals(advisory.getLegalPerson()))) {
/*  82 */       base += 3.0F;
/*     */     }
/*     */ 
/*  85 */     if ((null != advisory.getRegisteredCapital()) && (0.0D != advisory.getRegisteredCapital().doubleValue())) {
/*  86 */       base += 3.0F;
/*     */     }
/*     */ 
/*  89 */     if ((null != advisory.getPersonCount()) && (0 != advisory.getPersonCount().intValue())) {
/*  90 */       base += 3.0F;
/*     */     }
/*  92 */     if (null != advisory.getSetupTime()) {
/*  93 */       base += 3.0F;
/*     */     }
/*  95 */     if ((null != advisory.getAddress()) && (!"".equals(advisory.getAddress()))) {
/*  96 */       base += 3.0F;
/*     */     }
/*     */ 
/*  99 */     if ((null != advisory.getEmail()) && (!"".equals(advisory.getEmail()))) {
/* 100 */       base += 3.0F;
/*     */     }
/* 102 */     if ((null != advisory.getQq()) && (!"".equals(advisory.getQq()))) {
/* 103 */       base += 3.0F;
/*     */     }
/*     */ 
/* 106 */     if ((null != advisory.getCustomerServiceName()) && (!"".equals(advisory.getCustomerServiceName()))) {
/* 107 */       base += 3.0F;
/*     */     }
/* 109 */     if ((null != advisory.getCustomerServiceName()) && (!"".equals(advisory.getCustomerServiceName()))) {
/* 110 */       base += 3.0F;
/*     */     }
/* 112 */     advisory.setCustomerInfoIntegrity(Float.valueOf(base));
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.advisory.pojo.DefaultAdvisoryManager
 * JD-Core Version:    0.6.2
 */