/*    */ package com.yongjun.tdms.presentation.webwork.action;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.security.UserManager;
/*    */ import com.yongjun.pluto.webwork.action.UserLoginAction;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TdmsLoginAction extends UserLoginAction
/*    */ {
/*    */   private static final long serialVersionUID = -2286308339990443246L;
/*    */   private Map wmsSession;
/*    */   private User user;
/*    */   private final UserManager userManager;
/*    */ 
/*    */   public TdmsLoginAction(UserManager userManager)
/*    */   {
/* 34 */     this.userManager = userManager;
/*    */   }
/*    */ 
/*    */   public String execute() throws Exception
/*    */   {
/* 39 */     String result = super.execute();
/* 40 */     String targetUrl = (String)this.wmsSession.get("ACEGI_SECURITY_TARGET_URL");
/* 41 */     if ((targetUrl != null) && (!targetUrl.contains("frameset.html"))) {
/* 42 */       return "logout";
/*    */     }
/*    */ 
/* 45 */     return result;
/*    */   }
/*    */ 
/*    */   public void setSession(Map session) {
/* 49 */     super.setSession(session);
/* 50 */     this.wmsSession = session;
/*    */   }
/*    */ 
/*    */   public User getUser()
/*    */   {
/* 55 */     return this.userManager.getUser();
/*    */   }
/*    */ 
/*    */   public void setUser(User user) {
/* 59 */     this.user = user;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.TdmsLoginAction
 * JD-Core Version:    0.6.2
 */