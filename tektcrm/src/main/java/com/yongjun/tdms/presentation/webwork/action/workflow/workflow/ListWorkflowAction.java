/*     */ package com.yongjun.tdms.presentation.webwork.action.workflow.workflow;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workflow.Workflow;
/*     */ import com.yongjun.tdms.service.workflow.workflow.WorkflowManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListWorkflowAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private WorkflowManager workflowManager;
/*     */   private List<Workflow> workflowList;
/*     */ 
/*     */   public ListWorkflowAction(WorkflowManager workflowManager)
/*     */   {
/*  56 */     this.workflowManager = workflowManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  65 */     return "workflow";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  76 */     if (hasIds("workflowIds")) {
/*  77 */       System.out.println(hasId("workflowIds"));
/*  78 */       this.workflowList = this.workflowManager.loadAllWorkflows(getIds("workflowIds"));
/*  79 */       System.out.println(this.workflowList);
/*     */     }
/*     */ 
/*  82 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/*  95 */       this.workflowManager.deleteAllWorkflows(this.workflowList);
/*  96 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 100 */       e.printStackTrace();
/* 101 */       addActionMessage(getText("delete.error"));
/* 102 */       return "error";
/*     */     }
/* 104 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/* 115 */     String result = this.workflowManager.disabled(this.workflowList);
/* 116 */     if (result.equals("success"))
/*     */     {
/* 118 */       addActionMessage(getText("disabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 122 */       addActionMessage(getText("disabled.failer"));
/*     */     }
/* 124 */     return result;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/* 135 */     String result = this.workflowManager.enabled(this.workflowList);
/* 136 */     if (result.equals("success"))
/*     */     {
/* 138 */       addActionMessage(getText("enabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 142 */       addActionMessage(getText("enabled.failer"));
/*     */     }
/* 144 */     return result;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 157 */     if (isDelete())
/*     */     {
/* 159 */       return delete();
/*     */     }
/* 161 */     if (isDisabled())
/*     */     {
/* 163 */       return disabled();
/*     */     }
/* 165 */     if (isEnable())
/*     */     {
/* 167 */       return enabled();
/*     */     }
/* 169 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.workflow.ListWorkflowAction
 * JD-Core Version:    0.6.2
 */