/*     */ package com.yongjun.tdms.dao.customercontract.plan.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.dao.customercontract.plan.ReturnPlanDao;
/*     */ import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateReturnPlanDao extends BaseHibernateDao
/*     */   implements ReturnPlanDao
/*     */ {
/*     */   public void storeReturnPlan(ReturnPlan returnPlan)
/*     */   {
/*  26 */     store(returnPlan);
/*     */   }
/*     */ 
/*     */   public void deleteReturnPlan(ReturnPlan returnPlan)
/*     */   {
/*  34 */     delete(returnPlan);
/*     */   }
/*     */ 
/*     */   public void deleteAllReturnPlans(Collection<ReturnPlan> returnPlans)
/*     */   {
/*  42 */     deleteAll(returnPlans);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadAllReturnPlans(Long[] returnPlanIds)
/*     */   {
/*  51 */     return loadAll(ReturnPlan.class, returnPlanIds);
/*     */   }
/*     */ 
/*     */   public ReturnPlan loadReturnPlan(Long returnPlanId)
/*     */   {
/*  60 */     return (ReturnPlan)load(ReturnPlan.class, returnPlanId);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadAllReturnPlans()
/*     */   {
/*  68 */     return loadAll(ReturnPlan.class);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  79 */     return loadByKey(ReturnPlan.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  90 */     return loadByKeyArray(ReturnPlan.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> contractManagementAndBatch(Long contractManagementId, Long batchsId, String code, boolean isNew)
/*     */   {
/* 103 */     String hql = null;
/* 104 */     if (isNew) {
/* 105 */       hql = "from ReturnPlan as r where r.contractManagement.id = " + contractManagementId + " and r.batch.id=" + batchsId;
/*     */     }
/*     */     else {
/* 108 */       hql = "from ReturnPlan as r where  r.disabled = 0 and r.contractManagement.id = " + contractManagementId + " and r.batch.id=" + batchsId;
/*     */     }
/*     */ 
/* 116 */     Session session = getSession();
/* 117 */     List list = new ArrayList();
/* 118 */     list = session.createQuery(hql).list();
/* 119 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> contractAndBatch(Long contractManagementId, boolean isNew)
/*     */   {
/* 130 */     String hql = null;
/* 131 */     if (isNew) {
/* 132 */       hql = "select distinct r.batch.id,r.batch.name from ReturnPlan as r where r.isOrNot=1 and r.contractManagement.id = " + contractManagementId;
/*     */     }
/*     */     else
/*     */     {
/* 136 */       hql = "select distinct r.batch.id,r.batch.name from ReturnPlan as r where r.isOrNot=1 and  r.disabled = 0 and r.contractManagement.id = " + contractManagementId;
/*     */     }
/*     */ 
/* 144 */     Session session = getSession();
/* 145 */     List list = new ArrayList();
/* 146 */     list = session.createQuery(hql).list();
/* 147 */     return list;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.plan.hibernate.HibernateReturnPlanDao
 * JD-Core Version:    0.6.2
 */