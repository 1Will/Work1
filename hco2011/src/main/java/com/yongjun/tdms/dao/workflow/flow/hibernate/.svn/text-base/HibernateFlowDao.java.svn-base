/*     */ package com.yongjun.tdms.dao.workflow.flow.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workflow.flow.FlowDao;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateFlowDao extends BaseHibernateDao
/*     */   implements FlowDao
/*     */ {
/*     */   public void deleteAllFlows(Collection<Flow> flows)
/*     */   {
/*  45 */     super.deleteAll(flows);
/*     */   }
/*     */ 
/*     */   public void deleteFlow(Flow flow)
/*     */   {
/*  55 */     super.delete(flow);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadAllFlows(Long[] flowIds)
/*     */   {
/*  66 */     return super.loadAll(Flow.class, flowIds);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadAllFlows()
/*     */   {
/*  75 */     return super.loadAll(Flow.class);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(Flow.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(Flow.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Flow loadFlow(Long flowId)
/*     */   {
/* 111 */     return (Flow)super.load(Flow.class, flowId);
/*     */   }
/*     */ 
/*     */   public void storeFlow(Flow flow)
/*     */   {
/* 120 */     super.store(flow);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.flow.hibernate.HibernateFlowDao
 * JD-Core Version:    0.6.2
 */