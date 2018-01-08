/*     */ package com.yongjun.tdms.presentation.webwork.action.activitiFlow;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.activitiFlow.HistoryTaskinstManager;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;

/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ 
/*     */ public class ListMyHistoryTaskAction extends ValueListAction
/*     */ {
	    
/*     */   private static final long serialVersionUID = 1L;
/*     */   private HistoryTaskinstManager historyTaskinstManager;
/*     */   private FlowManager flowManager;
/*     */   private List<HistoryTaskinst> historyTaskinstList;
/*     */   private Flow flow;
/*     */ 
/*     */   public ListMyHistoryTaskAction(HistoryTaskinstManager historyTaskinstManager, FlowManager flowManager)
/*     */   {
/*  67 */     this.historyTaskinstManager = historyTaskinstManager;
/*  68 */     this.flowManager = flowManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  77 */     return "historyTaskinst";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  85 */     Map map = super.getRequestParameterMap();
              User user = this.userManager.getUser();
              map.put("assignee.code", user.getCode());
/*  89 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 100 */     if (hasIds("myHistoryTaskinstIds"))
/*     */     {
/* 102 */       this.historyTaskinstList = this.historyTaskinstManager.loadAllHistoryTaskinsts(getIds("myHistoryTaskinstIds"));
/*     */     }
/* 104 */     System.out.println(hasId("flow.id"));
/* 105 */     System.out.println(this.request.getParameter("flow.id"));
/* 106 */     if (hasId("flow.id")) {
/* 107 */       Long flowId = getId("flow.id");
/* 108 */       this.flow = this.flowManager.loadFlow(flowId);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 122 */       System.out.println(this.historyTaskinstList);
/* 123 */       this.historyTaskinstManager.deleteAllHistoryTaskinsts(this.historyTaskinstList);
/* 124 */       addActionMessage(getText("historyTaskinst.delete.success"));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 128 */       e.printStackTrace();
/* 129 */       addActionMessage(getText("historyTaskinst.delete.error"));
/* 130 */       return "error";
/*     */     }
/* 132 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/* 143 */     String result = this.historyTaskinstManager.disabled(this.historyTaskinstList);
/* 144 */     if (result.equals("success"))
/*     */     {
/* 146 */       addActionMessage(getText("historyTaskinst.disabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 150 */       addActionMessage(getText("historyTaskinst.disabled.error"));
/*     */     }
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/* 163 */     String result = this.historyTaskinstManager.enabled(this.historyTaskinstList);
/* 164 */     if (result.equals("success"))
/*     */     {
/* 166 */       addActionMessage(getText("historyTaskinst.enabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 170 */       addActionMessage(getText("historyTaskinst.enabled.error"));
/*     */     }
/* 172 */     return result;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 186 */     if (isDelete())
/*     */     {
/* 188 */       return delete();
/*     */     }
/* 190 */     if (isDisabled())
/*     */     {
/* 192 */       return disabled();
/*     */     }
/* 194 */     if (isEnable())
/*     */     {
/* 196 */       return enabled();
/*     */     }
/* 198 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.point.ListPointAction
 * JD-Core Version:    0.6.2
 */