/*     */ package com.yongjun.tdms.presentation.webwork.action.security;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListUsersByTephoneAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 6596923569257153107L;
/*     */   protected final UserManager userManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private List<User> users;
/*     */   private String multipleSelect;
/*     */   private List<Long> listFilterUserIds;
/*     */   private String filterUserIds;
/*     */ 
/*     */   public String getMultipleSelect()
/*     */   {
/*  30 */     return this.multipleSelect;
/*     */   }
/*     */ 
/*     */   public void setMultipleSelect(String multipleSelect) {
/*  34 */     this.multipleSelect = multipleSelect;
/*     */   }
/*     */ 
/*     */   public ListUsersByTephoneAction(UserManager userManager, DepartmentManager departmentManager) {
/*  38 */     this.userManager = userManager;
/*  39 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  44 */     if (isDisable()) {
/*  45 */       return disable();
/*     */     }
/*  47 */     return "success";
/*     */   }
/*     */ 
/*     */   private boolean isDisable() {
/*  51 */     return hasKey("disable");
/*     */   }
/*     */ 
/*     */   private String disable() {
/*  55 */     this.userManager.disableAllUsers(this.users);
/*  56 */     addActionMessage(getText("users.disable.success"));
/*  57 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  62 */     if ((this.users == null) && (hasIds("userIds"))) {
/*  63 */       this.users = this.userManager.loadAllUsers(getIds("userIds"));
/*     */     }
/*  65 */     if (hasId("multiple"))
/*  66 */       this.multipleSelect = this.request.getParameter("multiple");
/*     */     else {
/*  68 */       this.multipleSelect = "F";
/*     */     }
/*  70 */     if ((null == this.listFilterUserIds) && 
/*  71 */       (hasId("filterUserIds"))) {
/*  72 */       if ((!isFirst()) || (isSearch()))
/*     */       {
/*  74 */         String[] filterUserId = this.request.getParameter("filterUserIds").split(",");
/*     */ 
/*  76 */         this.listFilterUserIds = new ArrayList();
/*  77 */         for (int i = 0; i < filterUserId.length; i++)
/*     */         {
/*  79 */           this.listFilterUserIds.add(Long.valueOf(filterUserId[i]));
/*     */         }
/*     */       }
/*  82 */       this.filterUserIds = this.request.getParameter("filterUserIds");
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  88 */     Map map = super.getRequestParameterMap();
/*  89 */     if (hasId("filterUserIds")) {
/*  90 */       map.put("filterUserIds", this.listFilterUserIds);
/*     */     }
/*  92 */     return map;
/*     */   }
/*     */ 
/*     */   public boolean isSearch()
/*     */   {
/* 101 */     return hasKey("search");
/*     */   }
/*     */ 
/*     */   protected String getAdapterName() {
/* 105 */     return "users1";
/*     */   }
/*     */ 
/*     */   public List getDepartments()
/*     */   {
/* 113 */     return this.departmentManager.createSelectDepartments(getText("select.option.all"));
/*     */   }
/*     */ 
/*     */   public String getFilterUserIds()
/*     */   {
/* 118 */     return this.filterUserIds;
/*     */   }
/*     */ 
/*     */   public void setFilterUserIds(String filterUserIds) {
/* 122 */     this.filterUserIds = filterUserIds;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.security.ListUsersByTephoneAction
 * JD-Core Version:    0.6.2
 */