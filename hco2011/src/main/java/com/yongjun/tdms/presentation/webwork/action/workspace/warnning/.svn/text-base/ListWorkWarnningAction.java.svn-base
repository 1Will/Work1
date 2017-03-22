/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.warnning;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
/*     */ import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListWorkWarnningAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -5846328003010519171L;
/*     */   private final WorkWarnningManager workWarnningManager;
/*     */   private boolean onlyRead;
/*     */   private boolean onlyUnRead;
/*     */   private List<WorkWarnning> workWarnnings;
/*     */ 
/*     */   public ListWorkWarnningAction(WorkWarnningManager workWarnningManager)
/*     */   {
/*  46 */     this.workWarnningManager = workWarnningManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  54 */     return "warnnings";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  62 */     if (isRead()) {
/*  63 */       return readWorkWarnning();
/*     */     }
/*     */ 
/*  66 */     if (isUnRead()) {
/*  67 */       return unReadWorkWarnning();
/*     */     }
/*  69 */     return "success";
/*     */   }
/*     */ 
/*     */   public String readWorkWarnning()
/*     */   {
/*  77 */     this.workWarnningManager.readAllWorkWarnnings(this.workWarnnings);
/*  78 */     addActionMessage(getText("warnning.read.success"));
/*  79 */     return "success";
/*     */   }
/*     */ 
/*     */   public String unReadWorkWarnning()
/*     */   {
/*  87 */     this.workWarnningManager.unReadAllWorkWarnnings(this.workWarnnings);
/*  88 */     addActionMessage(getText("warnning.unRead.success"));
/*  89 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */   {
/*  95 */     if (hasIds("workWarnningIds"))
/*  96 */       this.workWarnnings = this.workWarnningManager.loadAllWorkWarnnings(getIds("workWarnningIds"));
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 104 */     Map map = super.getRequestParameterMap();
/* 105 */     if (this.onlyRead) {
/* 106 */       map.put("onlyRead", Boolean.valueOf(true));
/*     */     }
/* 108 */     if (this.onlyUnRead) {
/* 109 */       map.put("onlyUnRead", Boolean.valueOf(true));
/*     */     }
/* 111 */     if (this.request.getParameter("workWarnning.id") != null) {
/* 112 */       int rNId = Integer.valueOf(this.request.getParameter("workWarnning.id")).intValue();
/* 113 */       map.put("workWarnning.id", Integer.valueOf(rNId));
/*     */     }
/* 115 */     return map;
/*     */   }
/*     */ 
/*     */   public boolean isOnlyRead() {
/* 119 */     return this.onlyRead;
/*     */   }
/*     */ 
/*     */   public void setOnlyRead(boolean onlyRead) {
/* 123 */     this.onlyRead = onlyRead;
/*     */   }
/*     */ 
/*     */   public boolean isOnlyUnRead() {
/* 127 */     return this.onlyUnRead;
/*     */   }
/*     */ 
/*     */   public void setOnlyUnRead(boolean onlyUnRead) {
/* 131 */     this.onlyUnRead = onlyUnRead;
/*     */   }
/*     */ 
/*     */   private boolean isRead()
/*     */   {
/* 139 */     return hasKey("read");
/*     */   }
/*     */ 
/*     */   private boolean isUnRead()
/*     */   {
/* 147 */     return hasKey("unRead");
/*     */   }
/*     */ 
/*     */   public User getLoginUser()
/*     */   {
/* 155 */     return this.userManager.getUser();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.warnning.ListWorkWarnningAction
 * JD-Core Version:    0.6.2
 */