/*     */ package com.yongjun.tdms.dao.base.jobs.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.base.jobs.JobsDao;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateJobs extends BaseHibernateDao
/*     */   implements JobsDao
/*     */ {
/*     */   public void storeJobs(Jobs job)
/*     */   {
/*  43 */     super.store(job);
/*     */   }
/*     */ 
/*     */   public void deleteJobs(Jobs job)
/*     */   {
/*  52 */     super.delete(job);
/*     */   }
/*     */ 
/*     */   public void deleteAllJobs(Collection<Jobs> jobs)
/*     */   {
/*  61 */     super.deleteAll(jobs);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadAllJobs(Long[] jobsIds)
/*     */   {
/*  71 */     return super.loadAll(Jobs.class, jobsIds);
/*     */   }
/*     */ 
/*     */   public Jobs loadJobs(Long jobsId)
/*     */   {
/*  81 */     return (Jobs)super.load(Jobs.class, jobsId);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadAllJobs()
/*     */   {
/*  90 */     return super.loadAll(Jobs.class);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKey(Jobs.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 112 */     return super.loadByKeyArray(Jobs.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.jobs.hibernate.HibernateJobs
 * JD-Core Version:    0.6.2
 */