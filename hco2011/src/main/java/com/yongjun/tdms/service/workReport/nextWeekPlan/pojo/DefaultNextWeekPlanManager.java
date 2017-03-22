/*     */ package com.yongjun.tdms.service.workReport.nextWeekPlan.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workReport.nextWeekPlan.NextWeekPlanDao;
/*     */ import com.yongjun.tdms.model.workReport.nextWeekPlan.NextWeekPlan;
/*     */ import com.yongjun.tdms.service.workReport.nextWeekPlan.NextWeekPlanManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultNextWeekPlanManager extends BaseManager
/*     */   implements NextWeekPlanManager
/*     */ {
/*     */   private final NextWeekPlanDao nextWeekPlanDao;
/*     */ 
/*     */   public DefaultNextWeekPlanManager(NextWeekPlanDao nextWeekPlanDao)
/*     */   {
/*  48 */     this.nextWeekPlanDao = nextWeekPlanDao;
/*     */   }
/*     */ 
/*     */   public void storeNextWeekPlan(NextWeekPlan nextWeekPlan)
/*     */   {
/*  57 */     this.nextWeekPlanDao.storeNextWeekPlan(nextWeekPlan);
/*     */   }
/*     */ 
/*     */   public void deleteNextWeekPlan(NextWeekPlan nextWeekPlan)
/*     */   {
/*  66 */     this.nextWeekPlanDao.deleteNextWeekPlan(nextWeekPlan);
/*     */   }
/*     */ 
/*     */   public void deleteAllNextWeekPlan(Collection<NextWeekPlan> nextWeekPlans)
/*     */   {
/*  75 */     this.nextWeekPlanDao.deleteAllNextWeekPlan(nextWeekPlans);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadAllNextWeekPlan(Long[] nextWeekPlanIds)
/*     */   {
/*  85 */     return this.nextWeekPlanDao.loadAllNextWeekPlan(nextWeekPlanIds);
/*     */   }
/*     */ 
/*     */   public NextWeekPlan loadNextWeekPlan(Long nextWeekPlanId)
/*     */   {
/*  95 */     return this.nextWeekPlanDao.loadNextWeekPlan(nextWeekPlanId);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadAllNextWeekPlan()
/*     */   {
/* 104 */     return this.nextWeekPlanDao.loadAllNextWeekPlan();
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.nextWeekPlanDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.nextWeekPlanDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workReport.nextWeekPlan.pojo.DefaultNextWeekPlanManager
 * JD-Core Version:    0.6.2
 */