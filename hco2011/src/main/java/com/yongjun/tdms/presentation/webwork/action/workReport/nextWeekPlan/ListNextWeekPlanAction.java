/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.nextWeekPlan;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workReport.nextWeekPlan.NextWeekPlan;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.nextWeekPlan.NextWeekPlanManager;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListNextWeekPlanAction extends ValueListAction
/*     */ {
/*     */   private NextWeekPlanManager nextWeekPlanManager;
/*     */   private WeeklyManager weeklyManager;
/*     */   private UserManager userManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private List<NextWeekPlan> nextWeekPlanList;
/*     */   private Weekly weekly;
/*     */ 
/*     */   public ListNextWeekPlanAction(NextWeekPlanManager nextWeekPlanManager, WeeklyManager weeklyManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  65 */     this.nextWeekPlanManager = nextWeekPlanManager;
/*  66 */     this.weeklyManager = weeklyManager;
/*  67 */     this.userManager = userManager;
/*  68 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  73 */     if (isDelete()) {
/*  74 */       return delete();
/*     */     }
/*  76 */     return super.execute();
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  81 */     if (hasIds("nextWeekPlanIds")) {
/*  82 */       this.nextWeekPlanList = this.nextWeekPlanManager.loadAllNextWeekPlan(getIds("nextWeekPlanIds"));
/*     */     }
/*     */     else
/*     */     {
/*  86 */       this.nextWeekPlanList = new ArrayList();
/*     */     }
/*  88 */     if (hasId("weekly.id")) {
/*  89 */       this.weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));
/*     */     }
/*     */ 
/*  92 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  97 */     return "NextWeekPlan";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 102 */     Map map = super.getRequestParameterMap();
/* 103 */     if (this.weekly != null) {
/* 104 */       map.put("weekly.id", this.weekly.getId());
/* 105 */       map.put("onlyValid", "onlyValid");
/* 106 */       map.put("orgId", this.userManager.getOrganization().getId());
/* 107 */       map.put("rapporteur.id", this.weekly.getRapporteur().getId());
/*     */     }
/*     */ 
/* 110 */     return map;
/*     */   }
/*     */ 
/*     */   private User getLoginUser() {
/* 114 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public String delete() {
/* 118 */     this.nextWeekPlanManager.deleteAllNextWeekPlan(this.nextWeekPlanList);
/* 119 */     return "success";
/*     */   }
/*     */ 
/*     */   public Weekly getWeekly() {
/* 123 */     return this.weekly;
/*     */   }
/*     */ 
/*     */   public void setWeekly(Weekly weekly) {
/* 127 */     this.weekly = weekly;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.nextWeekPlan.ListNextWeekPlanAction
 * JD-Core Version:    0.6.2
 */