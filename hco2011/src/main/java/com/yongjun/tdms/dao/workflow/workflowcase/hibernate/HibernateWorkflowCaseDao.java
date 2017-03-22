/*     */ package com.yongjun.tdms.dao.workflow.workflowcase.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workflow.workflowcase.WorkflowCaseDao;
/*     */ import com.yongjun.tdms.model.workflow.WorkflowCase;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateWorkflowCaseDao extends BaseHibernateDao
/*     */   implements WorkflowCaseDao
/*     */ {
/*     */   public void deleteAllWorkflowCases(Collection<WorkflowCase> workflowCases)
/*     */   {
/*  47 */     super.deleteAll(workflowCases);
/*     */   }
/*     */ 
/*     */   public void deleteWorkflowCase(WorkflowCase workflowCase)
/*     */   {
/*  57 */     super.delete(workflowCase);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadAllWorkflowCases(Long[] workflowCaseIds)
/*     */   {
/*  68 */     return super.loadAll(WorkflowCase.class, workflowCaseIds);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadAllWorkflowCases()
/*     */   {
/*  77 */     return super.loadAll(WorkflowCase.class);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  90 */     return super.loadByKey(WorkflowCase.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 103 */     return super.loadByKeyArray(WorkflowCase.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public WorkflowCase loadWorkflowCase(Long workflowCaseId)
/*     */   {
/* 113 */     return (WorkflowCase)super.load(WorkflowCase.class, workflowCaseId);
/*     */   }
/*     */ 
/*     */   public void storeWorkflowCase(WorkflowCase workflowCase)
/*     */   {
/* 122 */     super.store(workflowCase);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 132 */     String hql = "select wfc.code from WorkflowCase as wfc where wfc.organization.id=" + orgId + " and wfc.code like '%" + code + "%'";
/*     */ 
/* 134 */     List codeList = getSession().createQuery(hql).list();
/* 135 */     if (codeList.size() > 0) {
/* 136 */       List items = new ArrayList();
/* 137 */       for (int i = 0; i < codeList.size(); i++) {
/* 138 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 139 */         items.add(item);
/*     */       }
/* 141 */       Collections.sort(items);
/* 142 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.workflowcase.hibernate.HibernateWorkflowCaseDao
 * JD-Core Version:    0.6.2
 */