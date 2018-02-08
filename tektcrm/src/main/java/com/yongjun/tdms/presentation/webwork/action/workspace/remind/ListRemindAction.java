/*    */ package com.yongjun.tdms.presentation.webwork.action.workspace.remind;
/*    */ 
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import com.yongjun.tdms.model.workspace.warnning.Remind;
/*    */ import com.yongjun.tdms.service.workspace.remind.RemindManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ListRemindAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = -4231074886758439908L;
/*    */   private RemindManager remindManager;
/*    */   private List<Remind> reminds;
/*    */ 
/*    */   public ListRemindAction(RemindManager remindManager)
/*    */   {
/* 52 */     this.remindManager = remindManager;
/*    */   }
/*    */ 
/*    */   public void prepare() throws Exception {
/* 56 */     if (hasIds("remindIds"))
/* 57 */       this.reminds = this.remindManager.loadReminds(getIds("remindIds"));
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 63 */     return "reminds";
/*    */   }
/*    */   public List<Remind> getReminds() {
/* 66 */     return this.reminds;
/*    */   }
/*    */   public void setReminds(List<Remind> reminds) {
/* 69 */     this.reminds = reminds;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.remind.ListRemindAction
 * JD-Core Version:    0.6.2
 */