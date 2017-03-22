/*     */ package com.yongjun.tdms.service.personnelFiles.work.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.work.WorkExperienceDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.WorkExperience;
/*     */ import com.yongjun.tdms.service.personnelFiles.work.WorkExperienceManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultWorkExperienceManager extends BaseManager
/*     */   implements WorkExperienceManager
/*     */ {
/*     */   public final WorkExperienceDao workExperienceaDao;
/*     */ 
/*     */   public DefaultWorkExperienceManager(WorkExperienceDao workExperienceaDao)
/*     */   {
/*  48 */     this.workExperienceaDao = workExperienceaDao;
/*     */   }
/*     */ 
/*     */   public void storeWorkExperience(WorkExperience we)
/*     */   {
/*  57 */     this.workExperienceaDao.storeWorkExperience(we);
/*     */   }
/*     */ 
/*     */   public void deleteWorkExperience(WorkExperience we)
/*     */   {
/*  66 */     this.workExperienceaDao.deleteWorkExperience(we);
/*     */   }
/*     */ 
/*     */   public void deleteAllWorkExperience(Collection<WorkExperience> wes)
/*     */   {
/*  75 */     this.workExperienceaDao.deleteAllWorkExperience(wes);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadAllWorkExperience(Long[] weIds)
/*     */   {
/*  85 */     return this.workExperienceaDao.loadAllWorkExperience(weIds);
/*     */   }
/*     */ 
/*     */   public WorkExperience loadWorkExperience(Long weId)
/*     */   {
/*  95 */     return this.workExperienceaDao.loadWorkExperience(weId);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadAllWorkExperience()
/*     */   {
/* 104 */     return this.workExperienceaDao.loadAllWorkExperience();
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.workExperienceaDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<WorkExperience> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.workExperienceaDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledWorkExperiences(Collection<WorkExperience> wes)
/*     */   {
/* 135 */     for (WorkExperience we : wes) {
/* 136 */       we.setDisabled(true);
/* 137 */       this.workExperienceaDao.storeWorkExperience(we);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledWorkExperiences(Collection<WorkExperience> wes)
/*     */   {
/* 147 */     for (WorkExperience we : wes) {
/* 148 */       we.setDisabled(false);
/* 149 */       this.workExperienceaDao.storeWorkExperience(we);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.work.pojo.DefaultWorkExperienceManager
 * JD-Core Version:    0.6.2
 */