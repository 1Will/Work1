/*     */ package com.yongjun.tdms.dao.workReport.nextWeekPlan.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workReport.nextWeekPlan.NextWeekPlanDao;
/*     */ import com.yongjun.tdms.model.workReport.nextWeekPlan.NextWeekPlan;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateNextWeekPlan extends BaseHibernateDao
/*     */   implements NextWeekPlanDao
/*     */ {
/*     */   public void storeNextWeekPlan(NextWeekPlan nextWeekPlan)
/*     */   {
/*  44 */     super.store(nextWeekPlan);
/*     */   }
/*     */ 
/*     */   public void deleteNextWeekPlan(NextWeekPlan nextWeekPlan)
/*     */   {
/*  53 */     super.delete(nextWeekPlan);
/*     */   }
/*     */ 
/*     */   public void deleteAllNextWeekPlan(Collection<NextWeekPlan> nextWeekPlans)
/*     */   {
/*  62 */     super.deleteAll(nextWeekPlans);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadAllNextWeekPlan(Long[] nextWeekPlanIds)
/*     */   {
/*  72 */     return super.loadAll(NextWeekPlan.class, nextWeekPlanIds);
/*     */   }
/*     */ 
/*     */   public NextWeekPlan loadNextWeekPlan(Long nextWeekPlanId)
/*     */   {
/*  82 */     return (NextWeekPlan)super.load(NextWeekPlan.class, nextWeekPlanId);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadAllNextWeekPlan()
/*     */   {
/*  91 */     return super.loadAll(NextWeekPlan.class);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(NextWeekPlan.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<NextWeekPlan> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(NextWeekPlan.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workReport.nextWeekPlan.hibernate.HibernateNextWeekPlan
 * JD-Core Version:    0.6.2
 */