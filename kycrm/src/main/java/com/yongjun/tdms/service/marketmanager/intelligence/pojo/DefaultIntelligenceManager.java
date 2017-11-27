/*     */ package com.yongjun.tdms.service.marketmanager.intelligence.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.intelligence.IntelligenceDao;
/*     */ import com.yongjun.tdms.model.marketmanager.intelligence.Intelligence;
/*     */ import com.yongjun.tdms.service.marketmanager.intelligence.IntelligenceManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultIntelligenceManager extends BaseManager
/*     */   implements IntelligenceManager
/*     */ {
/*     */   private final IntelligenceDao intelligenceDao;
/*     */ 
/*     */   public DefaultIntelligenceManager(IntelligenceDao intelligenceDao)
/*     */   {
/*  45 */     this.intelligenceDao = intelligenceDao;
/*     */   }
/*     */ 
/*     */   public void storeIntelligence(Intelligence intelligence)
/*     */   {
/*  54 */     this.intelligenceDao.storeIntelligence(intelligence);
/*     */   }
/*     */ 
/*     */   public void deleteIntelligence(Intelligence intelligence)
/*     */   {
/*  64 */     this.intelligenceDao.deleteIntelligence(intelligence);
/*     */   }
/*     */ 
/*     */   public void deleteAllIntelligence(Collection<Intelligence> intelligences)
/*     */   {
/*  74 */     this.intelligenceDao.deleteAllIntelligence(intelligences);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadAllIntelligence(Long[] intelligenceIds)
/*     */   {
/*  86 */     return this.intelligenceDao.loadAllIntelligence(intelligenceIds);
/*     */   }
/*     */ 
/*     */   public Intelligence loadIntelligence(Long intelligenceId)
/*     */   {
/*  97 */     return this.intelligenceDao.loadIntelligence(intelligenceId);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadAllIntelligence()
/*     */   {
/* 106 */     return this.intelligenceDao.loadAllIntelligence();
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 122 */     return this.intelligenceDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 138 */     return this.intelligenceDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllIntelligence(Collection<Intelligence> intelligenceList)
/*     */   {
/* 148 */     for (Intelligence a : intelligenceList) {
/* 149 */       a.setDisabled(true);
/* 150 */       this.intelligenceDao.storeIntelligence(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllIntelligence(Collection<Intelligence> intelligenceList)
/*     */   {
/* 161 */     for (Intelligence a : intelligenceList) {
/* 162 */       a.setDisabled(false);
/* 163 */       this.intelligenceDao.storeIntelligence(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 173 */     return this.intelligenceDao.getMaxPFCode(code, orgId);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.intelligence.pojo.DefaultIntelligenceManager
 * JD-Core Version:    0.6.2
 */