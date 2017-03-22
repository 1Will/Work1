/*    */ package com.yongjun.tdms.presentation.webwork.action.security;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.Group;
/*    */ import com.yongjun.pluto.service.security.GroupManager;
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ListGroupFrameAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final GroupManager groupManger;
/*    */   private List<List<Group>> groupsByGrouping;
/*    */ 
/*    */   public ListGroupFrameAction(GroupManager groupManger)
/*    */   {
/* 20 */     this.groupManger = groupManger;
/*    */   }
/*    */   public void prepare() throws Exception {
/* 23 */     this.groupsByGrouping = new ArrayList();
/* 24 */     List<Integer> steps = this.groupManger.getStepAfterGroupingByStep();
/* 25 */     for (Integer step : steps) {
/* 26 */       List groups = this.groupManger.getCommunicationGroupsByStep(step.intValue());
/* 27 */       this.groupsByGrouping.add(groups);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String execute() {
/* 32 */     return "success";
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 41 */     return "groupQuery";
/*    */   }
/*    */   public List<List<Group>> getGroupsByGrouping() {
/* 44 */     return this.groupsByGrouping;
/*    */   }
/*    */   public void setGroupsByGrouping(List<List<Group>> groupsByGrouping) {
/* 47 */     this.groupsByGrouping = groupsByGrouping;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.security.ListGroupFrameAction
 * JD-Core Version:    0.6.2
 */