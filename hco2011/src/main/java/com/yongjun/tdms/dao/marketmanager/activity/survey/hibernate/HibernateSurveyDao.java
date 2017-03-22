/*     */ package com.yongjun.tdms.dao.marketmanager.activity.survey.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.activity.survey.SurveyDao;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.survey.Survey;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateSurveyDao extends BaseHibernateDao
/*     */   implements SurveyDao
/*     */ {
/*     */   public void storeSurvey(Survey survey)
/*     */   {
/*  44 */     store(survey);
/*     */   }
/*     */ 
/*     */   public void deleteSurvey(Survey survey)
/*     */   {
/*  54 */     delete(survey);
/*     */   }
/*     */ 
/*     */   public void deleteAllSurvey(Collection<Survey> surveyIds)
/*     */   {
/*  64 */     deleteAll(surveyIds);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadAllSurvey(Long[] surveyIds)
/*     */   {
/*  75 */     return loadAll(Survey.class, surveyIds);
/*     */   }
/*     */ 
/*     */   public Survey loadSurvey(Long surveyId)
/*     */   {
/*  86 */     return (Survey)load(Survey.class, surveyId);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadAllSurvey()
/*     */   {
/*  95 */     return loadAll(Survey.class);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 111 */     return loadByKey(Survey.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Survey> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 127 */     return loadByKeyArray(Survey.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 138 */     String hql = "select sur.code from Survey as sur where sur.organization.id=" + orgId + " and sur.code like '%" + code + "%'";
/*     */ 
/* 140 */     List codeList = getSession().createQuery(hql).list();
/* 141 */     if (codeList.size() > 0) {
/* 142 */       List items = new ArrayList();
/* 143 */       for (int i = 0; i < codeList.size(); i++) {
/* 144 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 145 */         items.add(item);
/*     */       }
/* 147 */       Collections.sort(items);
/* 148 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.activity.survey.hibernate.HibernateSurveyDao
 * JD-Core Version:    0.6.2
 */