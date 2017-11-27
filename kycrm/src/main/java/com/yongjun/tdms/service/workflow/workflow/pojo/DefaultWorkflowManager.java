/*     */ package com.yongjun.tdms.service.workflow.workflow.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workflow.workflow.WorkflowDao;
/*     */ import com.yongjun.tdms.model.workflow.Workflow;
/*     */ import com.yongjun.tdms.service.workflow.workflow.WorkflowManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultWorkflowManager extends BaseManager
/*     */   implements WorkflowManager
/*     */ {
/*     */   private static final String SUCCESS = "success";
/*     */   private static final String ERROR = "error";
/*     */   private WorkflowDao workflowDao;
/*     */ 
/*     */   public DefaultWorkflowManager(WorkflowDao workflowDao)
/*     */   {
/*  58 */     this.workflowDao = workflowDao;
/*     */   }
/*     */ 
/*     */   public void setWorkflowDao(WorkflowDao workflowDao)
/*     */   {
/*  66 */     this.workflowDao = workflowDao;
/*     */   }
/*     */ 
/*     */   public WorkflowDao getWorkflowDao()
/*     */   {
/*  74 */     return this.workflowDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllWorkflows(Collection<Workflow> workflows)
/*     */   {
/*  84 */     getWorkflowDao().deleteAllWorkflows(workflows);
/*     */   }
/*     */ 
/*     */   public void deleteWorkflow(Workflow workflow)
/*     */   {
/*  94 */     getWorkflowDao().deleteWorkflow(workflow);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadAllWorkflows(Long[] workflowIds)
/*     */   {
/* 105 */     return getWorkflowDao().loadAllWorkflows(workflowIds);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadAllWorkflows()
/*     */   {
/* 114 */     return getWorkflowDao().loadAllWorkflows();
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 127 */     return getWorkflowDao().loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Workflow> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 140 */     return getWorkflowDao().loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Workflow loadWorkflow(Long workflowId)
/*     */   {
/* 150 */     return getWorkflowDao().loadWorkflow(workflowId);
/*     */   }
/*     */ 
/*     */   public void storeWorkflow(Workflow workflow)
/*     */   {
/* 159 */     getWorkflowDao().storeWorkflow(workflow);
/*     */   }
/*     */ 
/*     */   public String disabled(List<Workflow> workflowList)
/*     */   {
/* 170 */     boolean flag = true;
/*     */ 
/* 179 */     if (flag)
/*     */     {
/* 181 */       for (Workflow workflow : workflowList)
/*     */       {
/* 183 */         workflow.setDisabled(true);
/* 184 */         getWorkflowDao().storeWorkflow(workflow);
/*     */       }
/* 186 */       return "success";
/*     */     }
/* 188 */     return "error";
/*     */   }
/*     */ 
/*     */   public String enabled(List<Workflow> workflowList)
/*     */   {
/*     */     try
/*     */     {
/* 201 */       for (Workflow workflow : workflowList)
/*     */       {
/* 203 */         workflow.setDisabled(false);
/* 204 */         getWorkflowDao().storeWorkflow(workflow);
/*     */       }
/* 206 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 210 */       e.printStackTrace();
/* 211 */     }return "error";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.workflow.pojo.DefaultWorkflowManager
 * JD-Core Version:    0.6.2
 */