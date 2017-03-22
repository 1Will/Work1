/*    */ package com.yongjun.tdms.presentation.webwork.action.workspace.warnning;
/*    */ 
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
/*    */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnningDetail;
/*    */ import com.yongjun.tdms.service.workspace.warnning.WorkWarnningDetailManager;
/*    */ import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ListWorkWarnningDetailAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private WorkWarnningDetailManager workWarnningDetailManager;
/*    */   private WorkWarnningManager workWarnningManager;
/*    */   private List<WorkWarnningDetail> workWarnningDetails;
/* 57 */   String type = "";
/*    */ 
/*    */   public void prepare()
/*    */   {
/* 63 */     Long warnId = Long.valueOf(this.request.getParameter("workWarnningId"));
/*    */ 
/* 65 */     if ((null != warnId) && (warnId.longValue() > 0L)) {
/* 66 */       WorkWarnning w = this.workWarnningManager.loadWorkWarnning(warnId);
/* 67 */       w.setReadFlag(true);
/* 68 */       this.workWarnningManager.storeWorkWarnning(w);
/*    */     }
/* 70 */     WorkWarnning workWarnning = this.workWarnningManager.loadWorkWarnning(warnId);
/* 71 */     this.type = workWarnning.getType().trim();
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 76 */     return "workWarnningDetail";
/*    */   }
/*    */ 
/*    */   public void setWorkWarnningDetailManager(WorkWarnningDetailManager workWarnningDetailManager)
/*    */   {
/* 81 */     this.workWarnningDetailManager = workWarnningDetailManager;
/*    */   }
/*    */ 
/*    */   public void setWorkWarnningManager(WorkWarnningManager workWarnningManager) {
/* 85 */     this.workWarnningManager = workWarnningManager;
/*    */   }
/*    */ 
/*    */   public String getType()
/*    */   {
/* 92 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type)
/*    */   {
/* 99 */     this.type = type;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.warnning.ListWorkWarnningDetailAction
 * JD-Core Version:    0.6.2
 */