/*     */ package com.yongjun.tdms.dao.marketmanager.activity.plan.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.activity.plan.PlanDao;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.plan.Plan;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernatePlanDao extends BaseHibernateDao
/*     */   implements PlanDao
/*     */ {
/*     */   public void storePlan(Plan plan)
/*     */   {
/*  44 */     store(plan);
/*     */   }
/*     */ 
/*     */   public void deletePlan(Plan plan)
/*     */   {
/*  54 */     delete(plan);
/*     */   }
/*     */ 
/*     */   public void deleteAllPlan(Collection<Plan> planIds)
/*     */   {
/*  64 */     deleteAll(planIds);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadAllPlan(Long[] planIds)
/*     */   {
/*  75 */     return loadAll(Plan.class, planIds);
/*     */   }
/*     */ 
/*     */   public Plan loadPlan(Long planId)
/*     */   {
/*  86 */     return (Plan)load(Plan.class, planId);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadAllPlan()
/*     */   {
/*  95 */     return loadAll(Plan.class);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 111 */     return loadByKey(Plan.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Plan> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 127 */     return loadByKeyArray(Plan.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 138 */     String hql = "select p.code from Plan as p where p.organization.id=" + orgId + " and p.code like '%" + code + "%'";
/*     */ 
/* 140 */     List codeList = getSession().createQuery(hql).list();
/* 141 */     if (codeList.size() > 0) {
/* 142 */       List items = new ArrayList();
/* 143 */       for (int i = 0; i < codeList.size(); i++) {
/* 144 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 145 */         items.add(item);
/*     */       }
/* 147 */       Collections.sort(items);
/* 148 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.activity.plan.hibernate.HibernatePlanDao
 * JD-Core Version:    0.6.2
 */