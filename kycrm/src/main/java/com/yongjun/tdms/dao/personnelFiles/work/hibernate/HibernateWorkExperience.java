/*     */ package com.yongjun.tdms.dao.personnelFiles.work.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.work.WorkExperienceDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.WorkExperience;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateWorkExperience extends BaseHibernateDao
/*     */   implements WorkExperienceDao
/*     */ {
/*     */   public void storeWorkExperience(WorkExperience we)
/*     */   {
/*  44 */     super.store(we);
/*     */   }
/*     */ 
/*     */   public void deleteWorkExperience(WorkExperience we)
/*     */   {
/*  53 */     super.delete(we);
/*     */   }
/*     */ 
/*     */   public void deleteAllWorkExperience(Collection<WorkExperience> wes)
/*     */   {
/*  62 */     super.deleteAll(wes);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadAllWorkExperience(Long[] weIds)
/*     */   {
/*  72 */     return super.loadAll(WorkExperience.class, weIds);
/*     */   }
/*     */ 
/*     */   public WorkExperience loadWorkExperience(Long weId)
/*     */   {
/*  82 */     return (WorkExperience)super.load(WorkExperience.class, weId);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadAllWorkExperience()
/*     */   {
/*  91 */     return super.loadAll(WorkExperience.class);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(WorkExperience.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(WorkExperience.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.work.hibernate.HibernateWorkExperience
 * JD-Core Version:    0.6.2
 */