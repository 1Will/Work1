/*     */ package com.yongjun.tdms.dao.workReport.daily.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workReport.daily.DailyDao;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateDaily extends BaseHibernateDao
/*     */   implements DailyDao
/*     */ {
/*     */   public void storeDaily(Daily daily)
/*     */   {
/*  44 */     super.store(daily);
/*     */   }
/*     */ 
/*     */   public void deleteDaily(Daily daily)
/*     */   {
/*  53 */     super.delete(daily);
/*     */   }
/*     */ 
/*     */   public void deleteAllDaily(Collection<Daily> dailys)
/*     */   {
/*  62 */     super.deleteAll(dailys);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadAllDaily(Long[] dailyIds)
/*     */   {
/*  72 */     return super.loadAll(Daily.class, dailyIds);
/*     */   }
/*     */ 
/*     */   public Daily loadDaily(Long dailyId)
/*     */   {
/*  82 */     return (Daily)super.load(Daily.class, dailyId);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadAllDaily()
/*     */   {
/*  91 */     return super.loadAll(Daily.class);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(Daily.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(Daily.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workReport.daily.hibernate.HibernateDaily
 * JD-Core Version:    0.6.2
 */