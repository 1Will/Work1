/*     */ package com.yongjun.tdms.service.marketmanager.activity.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.activity.ActivityDao;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.Activity;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.ActivityManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultActivityManager extends BaseManager
/*     */   implements ActivityManager
/*     */ {
/*     */   private final ActivityDao activityDao;
/*     */ 
/*     */   public DefaultActivityManager(ActivityDao activityDao)
/*     */   {
/*  45 */     this.activityDao = activityDao;
/*     */   }
/*     */ 
/*     */   public void storeActivity(Activity activity)
/*     */   {
/*  54 */     this.activityDao.storeActivity(activity);
/*     */   }
/*     */ 
/*     */   public void deleteActivity(Activity activity)
/*     */   {
/*  64 */     this.activityDao.deleteActivity(activity);
/*     */   }
/*     */ 
/*     */   public void deleteAllActivity(Collection<Activity> activitys)
/*     */   {
/*  74 */     this.activityDao.deleteAllActivity(activitys);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadAllActivity(Long[] activityIds)
/*     */   {
/*  86 */     return this.activityDao.loadAllActivity(activityIds);
/*     */   }
/*     */ 
/*     */   public Activity loadActivity(Long activityId)
/*     */   {
/*  97 */     return this.activityDao.loadActivity(activityId);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadAllActivity()
/*     */   {
/* 106 */     return this.activityDao.loadAllActivity();
/*     */   }
/*     */ 
/*     */   public List<Activity> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 122 */     return this.activityDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 138 */     return this.activityDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllActivity(Collection<Activity> activityList)
/*     */   {
/* 148 */     for (Activity a : activityList) {
/* 149 */       a.setDisabled(true);
/* 150 */       this.activityDao.storeActivity(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllActivity(Collection<Activity> activityList)
/*     */   {
/* 161 */     for (Activity a : activityList) {
/* 162 */       a.setDisabled(false);
/* 163 */       this.activityDao.storeActivity(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 173 */     return this.activityDao.getMaxPFCode(code, orgId);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.activity.pojo.DefaultActivityManager
 * JD-Core Version:    0.6.2
 */