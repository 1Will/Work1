/*     */ package com.yongjun.tdms.dao.marketmanager.activity.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.activity.ActivityDao;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.Activity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateActivityDao extends BaseHibernateDao
/*     */   implements ActivityDao
/*     */ {
/*     */   public void storeActivity(Activity activity)
/*     */   {
/*  44 */     store(activity);
/*     */   }
/*     */ 
/*     */   public void deleteActivity(Activity activity)
/*     */   {
/*  54 */     delete(activity);
/*     */   }
/*     */ 
/*     */   public void deleteAllActivity(Collection<Activity> activityIds)
/*     */   {
/*  64 */     deleteAll(activityIds);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadAllActivity(Long[] activityIds)
/*     */   {
/*  75 */     return loadAll(Activity.class, activityIds);
/*     */   }
/*     */ 
/*     */   public Activity loadActivity(Long activityId)
/*     */   {
/*  86 */     return (Activity)load(Activity.class, activityId);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadAllActivity()
/*     */   {
/*  95 */     return loadAll(Activity.class);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 111 */     return loadByKey(Activity.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Activity> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 127 */     return loadByKeyArray(Activity.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 137 */     String hql = "select act.code from Activity as act where act.organization.id=" + orgId + " and act.code like '%" + code + "%'";
/*     */ 
/* 139 */     List codeList = getSession().createQuery(hql).list();
/* 140 */     if (codeList.size() > 0) {
/* 141 */       List items = new ArrayList();
/* 142 */       for (int i = 0; i < codeList.size(); i++) {
/* 143 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 144 */         items.add(item);
/*     */       }
/* 146 */       Collections.sort(items);
/* 147 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.activity.hibernate.HibernateActivityDao
 * JD-Core Version:    0.6.2
 */