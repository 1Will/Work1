/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.survey;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.survey.Survey;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.survey.SurveyManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListSurveyAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1058245216957037870L;
/*     */   private final SurveyManager surveyManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private List<Survey> surveyList;
/*     */ 
/*     */   public ListSurveyAction(SurveyManager surveyManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  65 */     this.surveyManager = surveyManager;
/*  66 */     this.codeValueManager = codeValueManager;
/*  67 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  76 */     if (hasIds("surveyIds")) {
/*  77 */       this.surveyList = this.surveyManager.loadAllSurvey(getIds("surveyIds"));
/*     */     }
/*     */     else
/*     */     {
/*  81 */       this.surveyList = new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  89 */     if (isDisabled()) {
/*  90 */       return disable();
/*     */     }
/*  92 */     if (isEnable()) {
/*  93 */       return enable();
/*     */     }
/*  95 */     if (isDelete()) {
/*  96 */       return delete();
/*     */     }
/*  98 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 106 */     return "survey";
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/*     */     try
/*     */     {
/* 114 */       this.surveyManager.disabledAllSurvey(this.surveyList);
/*     */     }
/*     */     catch (Exception e) {
/* 117 */       e.printStackTrace();
/*     */     }
/* 119 */     addActionMessage(getText("disabled.success"));
/* 120 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 128 */       this.surveyManager.deleteAllSurvey(this.surveyList);
/* 129 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       addActionMessage(getText("delete.error"));
/*     */     }
/* 135 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 142 */     this.surveyManager.enabledAllSurvey(this.surveyList);
/* 143 */     addActionMessage(getText("enabled.success"));
/* 144 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.survey.ListSurveyAction
 * JD-Core Version:    0.6.2
 */