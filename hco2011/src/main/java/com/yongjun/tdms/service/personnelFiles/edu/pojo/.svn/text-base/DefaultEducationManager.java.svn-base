/*     */ package com.yongjun.tdms.service.personnelFiles.edu.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.edu.EducationDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.Education;
/*     */ import com.yongjun.tdms.service.personnelFiles.edu.EducationManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultEducationManager extends BaseManager
/*     */   implements EducationManager
/*     */ {
/*     */   private final EducationDao educationDao;
/*     */ 
/*     */   public DefaultEducationManager(EducationDao educationDao)
/*     */   {
/*  48 */     this.educationDao = educationDao;
/*     */   }
/*     */ 
/*     */   public void storeEducation(Education edu)
/*     */   {
/*  57 */     this.educationDao.storeEducation(edu);
/*     */   }
/*     */ 
/*     */   public void deleteEducation(Education edu)
/*     */   {
/*  66 */     this.educationDao.deleteEducation(edu);
/*     */   }
/*     */ 
/*     */   public void deleteAllEducation(Collection<Education> edus)
/*     */   {
/*  75 */     this.educationDao.deleteAllEducation(edus);
/*     */   }
/*     */ 
/*     */   public List<Education> loadAllEducation(Long[] eduIds)
/*     */   {
/*  85 */     return this.educationDao.loadAllEducation(eduIds);
/*     */   }
/*     */ 
/*     */   public Education loadEducation(Long eduId)
/*     */   {
/*  95 */     return this.educationDao.loadEducation(eduId);
/*     */   }
/*     */ 
/*     */   public List<Education> loadAllEducation()
/*     */   {
/* 104 */     return this.educationDao.loadAllEducation();
/*     */   }
/*     */ 
/*     */   public List<Education> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.educationDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Education> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.educationDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledEducations(Collection<Education> edus)
/*     */   {
/* 135 */     for (Education edu : edus) {
/* 136 */       edu.setDisabled(true);
/* 137 */       this.educationDao.storeEducation(edu);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledEducations(Collection<Education> edus)
/*     */   {
/* 147 */     for (Education edu : edus) {
/* 148 */       edu.setDisabled(false);
/* 149 */       this.educationDao.storeEducation(edu);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.edu.pojo.DefaultEducationManager
 * JD-Core Version:    0.6.2
 */