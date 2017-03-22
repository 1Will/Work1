/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.survey;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.survey.Survey;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.survey.SurveyManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class EditSurveyAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -8112817988952534846L;
/*     */   private final SurveyManager surveyManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private Survey survey;
/*     */ 
/*     */   public EditSurveyAction(SurveyManager surveyManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  73 */     this.surveyManager = surveyManager;
/*  74 */     this.codeValueManager = codeValueManager;
/*  75 */     this.userManager = userManager;
/*  76 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  84 */     if (hasId("survey.id"))
/*  85 */       this.survey = this.surveyManager.loadSurvey(getId("survey.id"));
/*     */     else
/*  87 */       this.survey = new Survey();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  96 */     boolean isNew = this.survey.isNew();
/*     */ 
/*  99 */     if (hasId("persons.id")) {
/* 100 */       PersonnelFiles persons = new PersonnelFiles();
/* 101 */       persons = this.personnelFilesManager.loadPersonnel(getId("persons.id"));
/* 102 */       this.survey.setPersons(persons);
/*     */     }
/*     */ 
/* 105 */     this.survey.setOrganization(this.userManager.getOrganization());
/*     */     try
/*     */     {
/* 108 */       if (isNew) {
/* 109 */         String newCode = autoCompleteCode();
/* 110 */         this.survey.setCode(newCode);
/* 111 */         this.surveyManager.storeSurvey(this.survey);
/* 112 */         addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.survey.getCode() })));
/*     */ 
/* 114 */         return "new";
/*     */       }
/* 116 */       this.surveyManager.storeSurvey(this.survey);
/* 117 */       addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.survey.getCode() })));
/*     */ 
/* 119 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 122 */       e.printStackTrace();
/* 123 */       if (isNew) {
/* 124 */         addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.survey.getCode() })));
/*     */       }
/*     */       else
/*     */       {
/* 128 */         addActionMessage(getText("edit.error", Arrays.asList(new Object[] { this.survey.getCode() })));
/*     */       }
/*     */     }
/* 131 */     return "error";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 140 */     String maxCode = this.surveyManager.getMaxPFCode("SCDC", this.userManager.getOrganization().getId());
/* 141 */     if (null != maxCode) {
/* 142 */       int num = Integer.parseInt(maxCode) + 1;
/* 143 */       if (num < 10)
/* 144 */         return "SCDC-000" + num;
/* 145 */       if (num < 100)
/* 146 */         return "SCDC-00" + num;
/* 147 */       if (num < 1000) {
/* 148 */         return "SCDC-0" + num;
/*     */       }
/* 150 */       return "SCDC-" + num;
/*     */     }
/*     */ 
/* 154 */     return "SCDC-0001";
/*     */   }
/*     */ 
/*     */   public Survey getSurvey()
/*     */   {
/* 162 */     return this.survey;
/*     */   }
/*     */ 
/*     */   public void setSurvey(Survey survey)
/*     */   {
/* 169 */     this.survey = survey;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.survey.EditSurveyAction
 * JD-Core Version:    0.6.2
 */