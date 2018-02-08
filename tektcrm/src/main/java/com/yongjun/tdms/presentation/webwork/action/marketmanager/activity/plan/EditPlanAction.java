/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.plan;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.plan.Plan;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.plan.PlanManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class EditPlanAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -2891008006157242044L;
/*     */   private final PlanManager planManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private Plan plan;
/*     */ 
/*     */   public EditPlanAction(PlanManager planManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  73 */     this.planManager = planManager;
/*  74 */     this.codeValueManager = codeValueManager;
/*  75 */     this.userManager = userManager;
/*  76 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  85 */     if (hasId("plan.id"))
/*  86 */       this.plan = this.planManager.loadPlan(getId("plan.id"));
/*     */     else
/*  88 */       this.plan = new Plan();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  97 */     boolean isNew = this.plan.isNew();
/*     */ 
/*  99 */     if (hasId("persons.id")) {
/* 100 */       PersonnelFiles persons = new PersonnelFiles();
/* 101 */       persons = this.personnelFilesManager.loadPersonnel(getId("persons.id"));
/* 102 */       this.plan.setPersons(persons);
/*     */     }
/*     */ 
/* 105 */     this.plan.setOrganization(this.userManager.getOrganization());
/*     */     try
/*     */     {
/* 108 */       if (isNew) {
/* 109 */         String newCode = autoCompleteCode();
/* 110 */         this.plan.setCode(newCode);
/* 111 */         this.planManager.storePlan(this.plan);
/* 112 */         addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.plan.getCode() })));
/*     */ 
/* 114 */         return "new";
/*     */       }
/* 116 */       this.planManager.storePlan(this.plan);
/* 117 */       addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.plan.getCode() })));
/*     */ 
/* 119 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 122 */       e.printStackTrace();
/* 123 */       if (isNew) {
/* 124 */         addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.plan.getCode() })));
/*     */       }
/*     */       else
/*     */       {
/* 128 */         addActionMessage(getText("edit.error", Arrays.asList(new Object[] { this.plan.getCode() })));
/*     */       }
/*     */     }
/* 131 */     return "error";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 140 */     String maxCode = this.planManager.getMaxPFCode("SCJH", this.userManager.getOrganization().getId());
/* 141 */     if (null != maxCode) {
/* 142 */       int num = Integer.parseInt(maxCode) + 1;
/* 143 */       if (num < 10)
/* 144 */         return "SCJH-000" + num;
/* 145 */       if (num < 100)
/* 146 */         return "SCJH-00" + num;
/* 147 */       if (num < 1000) {
/* 148 */         return "SCJH-0" + num;
/*     */       }
/* 150 */       return "SCJH-" + num;
/*     */     }
/*     */ 
/* 154 */     return "SCJH-0001";
/*     */   }
/*     */ 
/*     */   public Plan getPlan()
/*     */   {
/* 162 */     return this.plan;
/*     */   }
/*     */ 
/*     */   public void setPlan(Plan plan)
/*     */   {
/* 170 */     this.plan = plan;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.plan.EditPlanAction
 * JD-Core Version:    0.6.2
 */