/*     */ package com.yongjun.tdms.presentation.webwork.action.workflow.workflow;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Workflow;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;
/*     */ import com.yongjun.tdms.service.workflow.workflow.WorkflowManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditWorkflowAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 732668911930478662L;
/*     */   private WorkflowManager workflowManager;
/*     */   private FlowManager flowManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private Workflow workflow;
/*     */ 
/*     */   public EditWorkflowAction(WorkflowManager workflowManager, FlowManager flowManager, CodeValueManager codeValueManager)
/*     */   {
/*  74 */     this.workflowManager = workflowManager;
/*  75 */     this.flowManager = flowManager;
/*  76 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  88 */     if (null == this.workflow)
/*     */     {
/*  90 */       if (hasId("workflow.id"))
/*     */       {
/*  92 */         this.workflow = this.workflowManager.loadWorkflow(getId("workflow.id"));
/*     */       }
/*     */       else
/*     */       {
/*  96 */         this.workflow = new Workflow();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 101 */       this.workflow = new Workflow();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 113 */     boolean isNew = this.workflow.isNew();
/*     */     try
/*     */     {
/* 116 */       if (hasId("flow.id"))
/*     */       {
/* 118 */         this.workflow.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request.getParameter("flow.id"))));
/*     */ 
/* 120 */         this.workflow.setDepartment(this.flowManager.loadFlow(Long.valueOf(this.request.getParameter("flow.id"))).getDepartment());
/*     */       }
/* 122 */       if (hasId("affectObject"))
/*     */       {
/* 124 */         this.workflow.setAffectObject(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("affectObject"))));
/*     */       }
/*     */ 
/* 128 */       String[] keyNames = new String[2];
/* 129 */       keyNames[0] = "department";
/* 130 */       keyNames[1] = "affectObject";
/* 131 */       Object[] keyValues = new Object[2];
/* 132 */       keyValues[0] = this.workflow.getFlow().getDepartment().getId();
/* 133 */       keyValues[1] = this.workflow.getAffectObject().getId();
/* 134 */       List workFlowList = this.workflowManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/* 136 */       if (isNew)
/*     */       {
/* 138 */         if (null == workFlowList)
/*     */         {
/* 140 */           this.workflowManager.storeWorkflow(this.workflow);
/*     */         }
/*     */         else
/*     */         {
/* 144 */           if (this.workflow.getOpenOrNot() == 0) {
/* 145 */             int openOrNot = 0;
/* 146 */             for (int i = 0; i < workFlowList.size(); i++) {
/* 147 */               openOrNot += ((Workflow)workFlowList.get(i)).getOpenOrNot();
/*     */             }
/* 149 */             if (openOrNot == workFlowList.size()) {
/* 150 */               this.workflowManager.storeWorkflow(this.workflow);
/*     */             }
/*     */             else {
/* 153 */               addActionMessage(getText("add.exist", Arrays.asList(new Object[] { this.workflow.getDepartment().getName(), this.workflow.getAffectObject().getName() })));
/*     */ 
/* 155 */               return "error";
/*     */             }
/*     */           }
/* 158 */           if (this.workflow.getOpenOrNot() == 1) {
/* 159 */             this.workflowManager.storeWorkflow(this.workflow);
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 165 */         int openOrNot = 0;
/* 166 */         Workflow workFlowDBList = null;
/* 167 */         for (int i = 0; i < workFlowList.size(); i++) {
/* 168 */           openOrNot += ((Workflow)workFlowList.get(i)).getOpenOrNot();
/* 169 */           if (((Workflow)workFlowList.get(i)).getOpenOrNot() == 0) {
/* 170 */             workFlowDBList = (Workflow)workFlowList.get(i);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 175 */         if (this.workflow.getOpenOrNot() == 0)
/*     */         {
/* 178 */           if ((openOrNot == workFlowList.size()) || ((openOrNot == workFlowList.size() - 1) && (this.workflow.getId() == workFlowDBList.getId()))) {
/* 179 */             this.workflowManager.storeWorkflow(this.workflow);
/*     */           } else {
/* 181 */             addActionMessage(getText("edit.exist", Arrays.asList(new Object[] { this.workflow.getDepartment().getName(), this.workflow.getAffectObject().getName() })));
/*     */ 
/* 183 */             return "error";
/*     */           }
/*     */         }
/*     */ 
/* 187 */         if (this.workflow.getOpenOrNot() == 1) {
/* 188 */           this.workflowManager.storeWorkflow(this.workflow);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 194 */       e.printStackTrace();
/* 195 */       addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.workflow.getCode() })));
/*     */ 
/* 197 */       return "error";
/*     */     }
/*     */ 
/* 200 */     if (isNew)
/*     */     {
/* 202 */       addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.workflow.getCode() })));
/*     */ 
/* 204 */       return "new";
/*     */     }
/*     */ 
/* 208 */     addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.workflow.getCode() })));
/*     */ 
/* 210 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Flow> getAllFlow()
/*     */   {
/* 221 */     return this.flowManager.loadAllFlows();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllAffectObject()
/*     */   {
/*     */     try
/*     */     {
/* 232 */       List father = this.codeValueManager.loadByKey("code", "021");
/* 233 */       List allAffectObject = new ArrayList();
/* 234 */       if ((null != father) && (!father.isEmpty()));
/* 236 */       return this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)father.get(0)).getId());
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 243 */       e.printStackTrace();
/*     */     }
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */   public Workflow getWorkflow()
/*     */   {
/* 253 */     return this.workflow;
/*     */   }
/*     */ 
/*     */   public void setWorkflow(Workflow workflow)
/*     */   {
/* 261 */     this.workflow = workflow;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.workflow.EditWorkflowAction
 * JD-Core Version:    0.6.2
 */