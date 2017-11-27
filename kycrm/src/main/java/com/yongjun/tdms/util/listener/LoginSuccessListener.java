/*    */ package com.yongjun.tdms.util.listener;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.security.UserManager;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletRequestEvent;
/*    */ import javax.servlet.ServletRequestListener;
/*    */ import org.acegisecurity.Authentication;
/*    */ import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.ApplicationListener;
/*    */ 
/*    */ public class LoginSuccessListener
/*    */   implements ServletRequestListener, ApplicationListener
/*    */ {
/*    */   private UserManager userManager;
/* 18 */   private static String remoteIp = "";
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent event)
/*    */   {
/*    */     User user;
/* 24 */     if ((event instanceof AuthenticationSuccessEvent)) {
/* 25 */       AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent)event;
/* 26 */       Authentication auth = authEvent.getAuthentication();
/* 27 */       String userName = auth.getName();
/* 28 */       user = this.userManager.getUserByLoginName(userName);
/*    */     }
/*    */   }
/*    */ 
/*    */   public UserManager getUserManager() {
/* 33 */     return this.userManager;
/*    */   }
/*    */ 
/*    */   public void setUserManager(UserManager userManager) {
/* 37 */     this.userManager = userManager;
/*    */   }
/*    */ 
/*    */   public void requestDestroyed(ServletRequestEvent arg0) {
/*    */   }
/*    */ 
/*    */   public void requestInitialized(ServletRequestEvent srEvent) {
/* 44 */     ServletRequest sr = srEvent.getServletRequest();
/* 45 */     remoteIp = sr.getRemoteAddr();
/*    */   }
/*    */   public static String getRemoteIp() {
/* 48 */     return remoteIp;
/*    */   }
/*    */   public static void setRemoteIp(String remoteIp) {
/* 51 */     remoteIp = remoteIp;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.util.listener.LoginSuccessListener
 * JD-Core Version:    0.6.2
 */