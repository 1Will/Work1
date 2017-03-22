/*     */ package com.yongjun.tdms.presentation.webwork.action.workflow.flow;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListFlowAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private FlowManager flowManager;
/*     */   private List<Flow> flowList;
/*     */ 
/*     */   public ListFlowAction(FlowManager flowManager)
/*     */   {
/*  55 */     this.flowManager = flowManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  64 */     return "flow";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  75 */     if (hasIds("flowIds")) {
/*  76 */       this.flowList = this.flowManager.loadAllFlows(getIds("flowIds"));
/*     */     }
/*  78 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/*  91 */       this.flowManager.deleteAllFlows(this.flowList);
/*  92 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  96 */       e.printStackTrace();
/*  97 */       addActionMessage(getText("delete.error"));
/*  98 */       return "error";
/*     */     }
/* 100 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/* 111 */     String result = this.flowManager.disabled(this.flowList);
/* 112 */     if (result.equals("success"))
/*     */     {
/* 114 */       addActionMessage(getText("disabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 118 */       addActionMessage(getText("disabled.error"));
/*     */     }
/* 120 */     return result;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/* 131 */     String result = this.flowManager.enabled(this.flowList);
/* 132 */     if (result.equals("success"))
/*     */     {
/* 134 */       addActionMessage(getText("enabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 138 */       addActionMessage(getText("enabled.error"));
/*     */     }
/* 140 */     return result;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 153 */     if (isDelete())
/*     */     {
/* 155 */       return delete();
/*     */     }
/* 157 */     if (isDisabled())
/*     */     {
/* 159 */       return disabled();
/*     */     }
/* 161 */     if (isEnable())
/*     */     {
/* 163 */       return enabled();
/*     */     }
/* 165 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.flow.ListFlowAction
 * JD-Core Version:    0.6.2
 */