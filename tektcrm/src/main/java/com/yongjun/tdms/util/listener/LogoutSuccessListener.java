/*    */ package com.yongjun.tdms.util.listener;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.security.UserManager;
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
/*    */ 
/*    */ public class LogoutSuccessListener
/*    */   implements HttpSessionListener
/*    */ {
/*    */   private UserManager userManager;
/*    */ 
/*    */   public void sessionCreated(HttpSessionEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void sessionDestroyed(HttpSessionEvent sEvent)
/*    */   {
/* 43 */     User user = this.userManager.getUser();
/*    */   }
/*    */ 
/*    */   public UserManager getUserManager() {
/* 47 */     return this.userManager;
/*    */   }
/*    */ 
/*    */   public void setUserManager(UserManager userManager) {
/* 51 */     this.userManager = userManager;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.util.listener.LogoutSuccessListener
 * JD-Core Version:    0.6.2
 */