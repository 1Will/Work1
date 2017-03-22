/*     */ package com.yongjun.tdms.service.base.document.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.tdms.dao.base.document.ApplicationDocDao;
/*     */ import com.yongjun.tdms.model.base.document.ApplicationDoc;
/*     */ import com.yongjun.tdms.service.base.document.ApplicationDocManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class DefaultApplicationDocManager extends BaseManager
/*     */   implements ApplicationDocManager
/*     */ {
/*     */   private final ApplicationDocDao applicationDocDao;
/*     */   private final UserManager userManager;
/*     */   protected Properties systemParameterConfiguration;
/*     */ 
/*     */   public DefaultApplicationDocManager(ApplicationDocDao applicationDocDao, UserManager userManager)
/*     */   {
/*  41 */     this.applicationDocDao = applicationDocDao;
/*  42 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public ApplicationDoc loadApplicationDoc(Long applicationDocId) {
/*  46 */     return this.applicationDocDao.loadApplicationDoc(applicationDocId);
/*     */   }
/*     */ 
/*     */   public List<ApplicationDoc> loadAllApplicationDocs(Long[] applicationDocIds) {
/*  50 */     return this.applicationDocDao.loadAllApplicationDocs(applicationDocIds);
/*     */   }
/*     */ 
/*     */   public List<ApplicationDoc> loadAllApplicationDocs() {
/*  54 */     return this.applicationDocDao.loadAllApplicationDocs();
/*     */   }
/*     */ 
/*     */   public void storeApplicationDoc(ApplicationDoc applicationDoc) {
/*  58 */     applicationDoc.setCreator(this.userManager.getUser().getName());
/*  59 */     this.applicationDocDao.storeApplicationDoc(applicationDoc);
/*     */   }
/*     */ 
/*     */   public void deleteApplicationDoc(ApplicationDoc applicationDoc) {
/*  63 */     this.applicationDocDao.deleteApplicationDoc(applicationDoc);
/*     */   }
/*     */ 
/*     */   public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs) {
/*  67 */     this.applicationDocDao.deleteAllApplicationDocs(applicationDocs);
/*     */   }
/*     */ 
/*     */   public Integer getNumberOfManualDoc()
/*     */   {
/*  73 */     return this.applicationDocDao.getNumberOfManualDoc();
/*     */   }
/*     */ 
/*     */   public Properties getSystemParameterConfiguration() {
/*  77 */     return this.systemParameterConfiguration;
/*     */   }
/*     */ 
/*     */   public void setSystemParameterConfiguration(Properties systemParameterConfiguration)
/*     */   {
/*  82 */     this.systemParameterConfiguration = systemParameterConfiguration;
/*     */   }
/*     */ 
/*     */   public boolean isMostNumberForTheManualDoc() {
/*  86 */     String manualDocNumber = this.systemParameterConfiguration.getProperty("help_manual_number");
/*  87 */     if (manualDocNumber != null)
/*     */     {
/*  89 */       Integer number = getNumberOfManualDoc();
/*     */ 
/*  91 */       if (number.compareTo(Integer.valueOf(manualDocNumber)) >= 0) {
/*  92 */         return true;
/*     */       }
/*     */     }
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public Integer getMostUploadNumberForManualDoc() {
/*  99 */     Integer number = Integer.valueOf(0);
/* 100 */     String manualDocNumber = this.systemParameterConfiguration.getProperty("help_manual_number");
/* 101 */     if (null != manualDocNumber) {
/* 102 */       number = Integer.valueOf(manualDocNumber);
/*     */     }
/* 104 */     return number;
/*     */   }
/*     */ 
/*     */   public List<ApplicationDoc> getAllManualDoc() {
/* 108 */     return this.applicationDocDao.getAllManualDoc();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.document.pojo.DefaultApplicationDocManager
 * JD-Core Version:    0.6.2
 */