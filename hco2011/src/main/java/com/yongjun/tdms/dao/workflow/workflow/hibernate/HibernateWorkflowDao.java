/*     */ package com.yongjun.tdms.dao.workflow.workflow.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workflow.workflow.WorkflowDao;
/*     */ import com.yongjun.tdms.model.workflow.Workflow;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateWorkflowDao extends BaseHibernateDao
/*     */   implements WorkflowDao
/*     */ {
/*     */   public void deleteAllWorkflows(Collection<Workflow> workflows)
/*     */   {
/*  45 */     super.deleteAll(workflows);
/*     */   }
/*     */ 
/*     */   public void deleteWorkflow(Workflow workflow)
/*     */   {
/*  55 */     super.delete(workflow);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadAllWorkflows(Long[] workflowIds)
/*     */   {
/*  66 */     return super.loadAll(Workflow.class, workflowIds);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadAllWorkflows()
/*     */   {
/*  75 */     return super.loadAll(Workflow.class);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(Workflow.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(Workflow.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Workflow loadWorkflow(Long workflowId)
/*     */   {
/* 111 */     return (Workflow)super.load(Workflow.class, workflowId);
/*     */   }
/*     */ 
/*     */   public void storeWorkflow(Workflow workflow)
/*     */   {
/* 120 */     super.store(workflow);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.workflow.hibernate.HibernateWorkflowDao
 * JD-Core Version:    0.6.2
 */