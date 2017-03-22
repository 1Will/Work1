/*    */ package com.yongjun.tdms.presentation.webwork.action.security;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.Group;
/*    */ import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.security.GroupManager;
/*    */ import com.yongjun.pluto.service.security.UserManager;
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ListUserFrameAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final GroupManager groupManager;
/*    */   private Group groupFrame;
/*    */   private List<User> users;
/*    */   private List<Group> groups;
/* 23 */   private List<User> disUsers = new ArrayList();
/*    */ 
/* 25 */   ListUserFrameAction(GroupManager groupManager) { this.groupManager = groupManager;
/* 26 */     this.userManager = this.userManager; }
/*    */ 
/*    */   public void prepare() throws Exception {
/* 29 */     if ((!StringUtils.isEmpty(this.request.getParameter("first"))) && (this.request.getParameter("first").equals("true")))
/*    */     {
/* 31 */       return;
/*    */     }
/* 33 */     if (hasId("groupFrame.id")) {
/* 34 */       this.groupFrame = this.groupManager.loadGroup(getId("groupFrame.id"));
/* 35 */       this.groups = this.groupManager.getGroupsByParentGroup(getId("groupFrame.id"));
/*    */ 
/* 43 */       List list = this.groupManager.displaySortUser(this.groupFrame.getId());
/* 44 */       this.users = new ArrayList();
/* 45 */       for (int i = 0; i < list.size(); i++) {
/* 46 */         User user = this.userManager.loadUser((Long)list.get(i));
/* 47 */         this.users.add(user);
/*    */       }
/*    */     } else {
/* 50 */       this.groups = this.groupManager.getCommunicationGroupsByStep(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 56 */     return null;
/*    */   }
/*    */   public Group getGroupFrame() {
/* 59 */     return this.groupFrame;
/*    */   }
/*    */   public void setGroupFrame(Group groupFrame) {
/* 62 */     this.groupFrame = groupFrame;
/*    */   }
/*    */   public List<Group> getGroups() {
/* 65 */     return this.groups;
/*    */   }
/*    */   public void setGroups(List<Group> groups) {
/* 68 */     this.groups = groups;
/*    */   }
/*    */   public List<User> getUsers() {
/* 71 */     return this.users;
/*    */   }
/*    */   public void setUsers(List<User> users) {
/* 74 */     this.users = users;
/*    */   }
/*    */   public List<User> getDisUsers() {
/* 77 */     return this.disUsers;
/*    */   }
/*    */   public void setDisUsers(List<User> disUsers) {
/* 80 */     this.disUsers = disUsers;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.security.ListUserFrameAction
 * JD-Core Version:    0.6.2
 */