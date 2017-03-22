/*     */ package com.yongjun.tdms.service.marketmanager.activity.survey.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.activity.survey.SurveyDao;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.survey.Survey;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.survey.SurveyManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSurveyManager
/*     */   implements SurveyManager
/*     */ {
/*     */   private final SurveyDao surveyDao;
/*     */ 
/*     */   public DefaultSurveyManager(SurveyDao surveyDao)
/*     */   {
/*  44 */     this.surveyDao = surveyDao;
/*     */   }
/*     */ 
/*     */   public void storeSurvey(Survey survey)
/*     */   {
/*  53 */     this.surveyDao.storeSurvey(survey);
/*     */   }
/*     */ 
/*     */   public void deleteSurvey(Survey survey)
/*     */   {
/*  63 */     this.surveyDao.deleteSurvey(survey);
/*     */   }
/*     */ 
/*     */   public void deleteAllSurvey(Collection<Survey> surveys)
/*     */   {
/*  73 */     this.surveyDao.deleteAllSurvey(surveys);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadAllSurvey(Long[] surveyIds)
/*     */   {
/*  85 */     return this.surveyDao.loadAllSurvey(surveyIds);
/*     */   }
/*     */ 
/*     */   public Survey loadSurvey(Long surveyId)
/*     */   {
/*  96 */     return this.surveyDao.loadSurvey(surveyId);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadAllSurvey()
/*     */   {
/* 105 */     return this.surveyDao.loadAllSurvey();
/*     */   }
/*     */ 
/*     */   public List<Survey> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 121 */     return this.surveyDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 137 */     return this.surveyDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllSurvey(Collection<Survey> surveyList)
/*     */   {
/* 147 */     for (Survey a : surveyList) {
/* 148 */       a.setDisabled(true);
/* 149 */       this.surveyDao.storeSurvey(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllSurvey(Collection<Survey> surveyList)
/*     */   {
/* 160 */     for (Survey a : surveyList) {
/* 161 */       a.setDisabled(false);
/* 162 */       this.surveyDao.storeSurvey(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 173 */     return this.surveyDao.getMaxPFCode(code, orgId);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.activity.survey.pojo.DefaultSurveyManager
 * JD-Core Version:    0.6.2
 */