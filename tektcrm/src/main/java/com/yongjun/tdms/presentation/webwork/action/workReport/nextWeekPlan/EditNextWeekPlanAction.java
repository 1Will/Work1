/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.nextWeekPlan;
/*     */ 
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.nextWeekPlan.NextWeekPlan;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.nextWeekPlan.NextWeekPlanManager;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class EditNextWeekPlanAction extends PrepareAction
/*     */ {
/*     */   private NextWeekPlanManager nextWeekPlanManager;
/*     */   private WeeklyManager weeklyManager;
/*     */   private UserManager userManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private NextWeekPlan nextWeekPlan;
/*     */   private Weekly weekly;
/*     */   private PersonnelFiles loginUser;
/*     */ 
/*     */   public EditNextWeekPlanAction(NextWeekPlanManager nextWeekPlanManager, WeeklyManager weeklyManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  64 */     this.nextWeekPlanManager = nextWeekPlanManager;
/*  65 */     this.weeklyManager = weeklyManager;
/*  66 */     this.userManager = userManager;
/*  67 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  72 */     if (hasId("weekly.id")) {
/*  73 */       this.weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));
/*     */     }
/*     */     else
/*     */     {
/*  77 */       this.weekly = new Weekly();
/*     */     }
/*  79 */     if (hasId("nextWeekPlan.id")) {
/*  80 */       this.nextWeekPlan = this.nextWeekPlanManager.loadNextWeekPlan(getId("nextWeekPlan.id"));
/*     */     }
/*     */     else
/*     */     {
/*  84 */       this.nextWeekPlan = new NextWeekPlan();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save() {
/*  89 */     boolean isNew = this.nextWeekPlan.isNew();
/*     */ 
/*  91 */     this.nextWeekPlan.setWeekly(this.weekly);
/*     */ 
/*  93 */     this.nextWeekPlan.setRapporteur(this.weekly.getRapporteur());
/*     */ 
/*  95 */     this.nextWeekPlan.setOrganization(this.userManager.getOrganization());
/*     */     try
/*     */     {
/*  98 */       this.nextWeekPlanManager.storeNextWeekPlan(this.nextWeekPlan);
/*  99 */       if (isNew) {
/* 100 */         addActionMessage(getText("nextWeekPlan.add.success", Arrays.asList(new Object[] { this.nextWeekPlan.getId() })));
/*     */ 
/* 102 */         return "new";
/*     */       }
/*     */ 
/* 105 */       addActionMessage(getText("nextWeekPlan.edit.success", Arrays.asList(new Object[] { this.nextWeekPlan.getId() })));
/*     */ 
/* 107 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 111 */       if (isNew) {
/* 112 */         addActionMessage(getText("nextWeekPlan.add.error"));
/*     */       }
/*     */       else
/* 115 */         addActionMessage(getText("nextWeekPlan.edit.error"));
/*     */     }
/* 117 */     return "error";
/*     */   }
/*     */ 
/*     */   public NextWeekPlan getNextWeekPlan()
/*     */   {
/* 122 */     return this.nextWeekPlan;
/*     */   }
/*     */ 
/*     */   public void setNextWeekPlan(NextWeekPlan nextWeekPlan) {
/* 126 */     this.nextWeekPlan = nextWeekPlan;
/*     */   }
/*     */ 
/*     */   public Weekly getWeekly() {
/* 130 */     return this.weekly;
/*     */   }
/*     */ 
/*     */   public void setWeekly(Weekly weekly) {
/* 134 */     this.weekly = weekly;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getLoginUser()
/*     */   {
/* 139 */     return this.loginUser;
/*     */   }
/*     */ 
/*     */   public void setLoginUser(PersonnelFiles loginUser) {
/* 143 */     this.loginUser = loginUser;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.nextWeekPlan.EditNextWeekPlanAction
 * JD-Core Version:    0.6.2
 */