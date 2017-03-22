/*     */ package com.yongjun.tdms.service.workReport.daily.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workReport.daily.DailyDao;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
/*     */ import com.yongjun.tdms.service.workReport.daily.DailyManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultDailyManager extends BaseManager
/*     */   implements DailyManager
/*     */ {
/*     */   private final DailyDao dailyDao;
/*     */ 
/*     */   public DefaultDailyManager(DailyDao dailyDao)
/*     */   {
/*  48 */     this.dailyDao = dailyDao;
/*     */   }
/*     */ 
/*     */   public void storeDaily(Daily daily)
/*     */   {
/*  57 */     this.dailyDao.storeDaily(daily);
/*     */   }
/*     */ 
/*     */   public void deleteDaily(Daily daily)
/*     */   {
/*  66 */     this.dailyDao.deleteDaily(daily);
/*     */   }
/*     */ 
/*     */   public void deleteAllDaily(Collection<Daily> dailys)
/*     */   {
/*  75 */     this.dailyDao.deleteAllDaily(dailys);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadAllDaily(Long[] dailyIds)
/*     */   {
/*  85 */     return this.dailyDao.loadAllDaily(dailyIds);
/*     */   }
/*     */ 
/*     */   public Daily loadDaily(Long dailyId)
/*     */   {
/*  95 */     return this.dailyDao.loadDaily(dailyId);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadAllDaily()
/*     */   {
/* 104 */     return this.dailyDao.loadAllDaily();
/*     */   }
/*     */ 
/*     */   public List<Daily> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.dailyDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.dailyDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workReport.daily.pojo.DefaultDailyManager
 * JD-Core Version:    0.6.2
 */