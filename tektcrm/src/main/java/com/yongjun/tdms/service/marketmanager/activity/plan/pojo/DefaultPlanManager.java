/*     */ package com.yongjun.tdms.service.marketmanager.activity.plan.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.activity.plan.PlanDao;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.plan.Plan;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.plan.PlanManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultPlanManager extends BaseManager
/*     */   implements PlanManager
/*     */ {
/*     */   private final PlanDao planDao;
/*     */ 
/*     */   public DefaultPlanManager(PlanDao planDao)
/*     */   {
/*  33 */     this.planDao = planDao;
/*     */   }
/*     */ 
/*     */   public void storePlan(Plan plan)
/*     */   {
/*  42 */     this.planDao.storePlan(plan);
/*     */   }
/*     */ 
/*     */   public void deletePlan(Plan plan)
/*     */   {
/*  52 */     this.planDao.deletePlan(plan);
/*     */   }
/*     */ 
/*     */   public void deleteAllPlan(Collection<Plan> plans)
/*     */   {
/*  62 */     this.planDao.deleteAllPlan(plans);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadAllPlan(Long[] planIds)
/*     */   {
/*  74 */     return this.planDao.loadAllPlan(planIds);
/*     */   }
/*     */ 
/*     */   public Plan loadPlan(Long planId)
/*     */   {
/*  85 */     return this.planDao.loadPlan(planId);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadAllPlan()
/*     */   {
/*  94 */     return this.planDao.loadAllPlan();
/*     */   }
/*     */ 
/*     */   public List<Plan> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 110 */     return this.planDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.planDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllPlan(Collection<Plan> planList)
/*     */   {
/* 136 */     for (Plan a : planList) {
/* 137 */       a.setDisabled(true);
/* 138 */       this.planDao.storePlan(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllPlan(Collection<Plan> planList)
/*     */   {
/* 149 */     for (Plan a : planList) {
/* 150 */       a.setDisabled(false);
/* 151 */       this.planDao.storePlan(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 161 */     return this.planDao.getMaxPFCode(code, orgId);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.activity.plan.pojo.DefaultPlanManager
 * JD-Core Version:    0.6.2
 */