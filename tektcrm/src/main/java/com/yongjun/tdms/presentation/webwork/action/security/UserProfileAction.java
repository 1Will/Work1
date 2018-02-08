/*    */ package com.yongjun.tdms.presentation.webwork.action.security;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.Department;
/*    */ import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.security.UserManager;
/*    */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*    */ import java.util.List;
/*    */ 
/*    */ public class UserProfileAction extends PrepareAction
/*    */ {
/*    */   private static final long serialVersionUID = -748045157523446454L;
/*    */   private final UserManager userManager;
/*    */   private User user;
/* 38 */   private boolean firstChangePasswordFlag = false;
/*    */ 
/* 40 */   public UserProfileAction(UserManager userManager) { this.userManager = userManager; }
/*    */ 
/*    */   public void prepare()
/*    */     throws Exception
/*    */   {
/* 45 */     this.user = this.userManager.getUser();
/*    */   }
/*    */ 
/*    */   public String save()
/*    */   {
/* 50 */     this.user.setOrganization(this.userManager.getUser().getOrganization());
/* 51 */     this.userManager.storeUser(this.user);
/* 52 */     addActionMessage(getText("userProfile.edit.success"));
/* 53 */     return "success";
/*    */   }
/*    */ 
/*    */   public User getUser() {
/* 57 */     return this.user;
/*    */   }
/*    */ 
/*    */   public List<Department> getDepartments() {
/* 61 */     return this.userManager.getDepartments();
/*    */   }
/*    */ 
/*    */   public boolean isFirstChangePasswordFlag() {
/* 65 */     return this.firstChangePasswordFlag;
/*    */   }
/*    */ 
/*    */   public void setFirstChangePasswordFlag(boolean firstChangePasswordFlag) {
/* 69 */     this.firstChangePasswordFlag = firstChangePasswordFlag;
/*    */   }
/*    */ 
/*    */   public void setUser(User user) {
/* 73 */     this.user = user;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.security.UserProfileAction
 * JD-Core Version:    0.6.2
 */