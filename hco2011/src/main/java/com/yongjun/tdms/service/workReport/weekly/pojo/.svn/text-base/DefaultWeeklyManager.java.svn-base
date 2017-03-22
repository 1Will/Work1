/*     */ package com.yongjun.tdms.service.workReport.weekly.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workReport.weekly.WeeklyDao;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultWeeklyManager extends BaseManager
/*     */   implements WeeklyManager
/*     */ {
/*     */   private final WeeklyDao weeklyDao;
/*     */ 
/*     */   public DefaultWeeklyManager(WeeklyDao weeklyDao)
/*     */   {
/*  49 */     this.weeklyDao = weeklyDao;
/*     */   }
/*     */ 
/*     */   public void storeWeekly(Weekly weekly)
/*     */   {
/*  58 */     this.weeklyDao.storeWeekly(weekly);
/*     */   }
/*     */ 
/*     */   public void deleteWeekly(Weekly weekly)
/*     */   {
/*  67 */     this.weeklyDao.deleteWeekly(weekly);
/*     */   }
/*     */ 
/*     */   public void deleteAllWeekly(Collection<Weekly> weeklys)
/*     */   {
/*  76 */     this.weeklyDao.deleteAllWeekly(weeklys);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadAllWeekly(Long[] weeklyIds)
/*     */   {
/*  86 */     return this.weeklyDao.loadAllWeekly(weeklyIds);
/*     */   }
/*     */ 
/*     */   public Weekly loadWeekly(Long weeklyId)
/*     */   {
/*  96 */     return this.weeklyDao.loadWeekly(weeklyId);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadAllWeekly()
/*     */   {
/* 105 */     return this.weeklyDao.loadAllWeekly();
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 116 */     return this.weeklyDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 127 */     return this.weeklyDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxWeeklyCode(String code, Long pfId, Long orgId)
/*     */   {
/* 136 */     return this.weeklyDao.getMaxWeeklyCode(code, pfId, orgId);
/*     */   }
/*     */ 
/*     */   public void disabledWeekly(Collection<Weekly> weeklyList) {
/* 140 */     for (Weekly w : weeklyList) {
/* 141 */       w.setDisabled(true);
/* 142 */       this.weeklyDao.storeWeekly(w);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledWeekly(Collection<Weekly> weeklyList)
/*     */   {
/* 148 */     for (Weekly w : weeklyList) {
/* 149 */       w.setDisabled(false);
/* 150 */       this.weeklyDao.storeWeekly(w);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workReport.weekly.pojo.DefaultWeeklyManager
 * JD-Core Version:    0.6.2
 */