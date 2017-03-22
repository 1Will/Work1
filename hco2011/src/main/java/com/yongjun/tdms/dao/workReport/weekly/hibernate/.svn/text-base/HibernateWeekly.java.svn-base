/*     */ package com.yongjun.tdms.dao.workReport.weekly.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workReport.weekly.WeeklyDao;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateWeekly extends BaseHibernateDao
/*     */   implements WeeklyDao
/*     */ {
/*     */   public void storeWeekly(Weekly weekly)
/*     */   {
/*  46 */     super.store(weekly);
/*     */   }
/*     */ 
/*     */   public void deleteWeekly(Weekly weekly)
/*     */   {
/*  55 */     super.delete(weekly);
/*     */   }
/*     */ 
/*     */   public void deleteAllWeekly(Collection<Weekly> weeklys)
/*     */   {
/*  64 */     super.deleteAll(weeklys);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadAllWeekly(Long[] weeklyIds)
/*     */   {
/*  74 */     return super.loadAll(Weekly.class, weeklyIds);
/*     */   }
/*     */ 
/*     */   public Weekly loadWeekly(Long weeklyId)
/*     */   {
/*  84 */     return (Weekly)super.load(Weekly.class, weeklyId);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadAllWeekly()
/*     */   {
/*  93 */     return super.loadAll(Weekly.class);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 104 */     return super.loadByKey(Weekly.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Weekly> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 115 */     return super.loadByKeyArray(Weekly.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxWeeklyCode(String code, Long pfId, Long orgId)
/*     */   {
/* 125 */     String hql = "select w.code from Weekly as w where w.rapporteur.id=" + pfId + " and w.organization.id=" + orgId + " and w.code like '%" + code + "%'";
/* 126 */     List codeList = getSession().createQuery(hql).list();
/* 127 */     if (codeList.size() > 0) {
/* 128 */       List items = new ArrayList();
/* 129 */       for (int i = 0; i < codeList.size(); i++) {
/* 130 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("_0") + 2);
/* 131 */         items.add(item);
/*     */       }
/* 133 */       Collections.sort(items);
/* 134 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workReport.weekly.hibernate.HibernateWeekly
 * JD-Core Version:    0.6.2
 */