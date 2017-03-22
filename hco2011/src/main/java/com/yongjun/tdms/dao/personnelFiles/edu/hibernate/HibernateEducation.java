/*     */ package com.yongjun.tdms.dao.personnelFiles.edu.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.edu.EducationDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.Education;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateEducation extends BaseHibernateDao
/*     */   implements EducationDao
/*     */ {
/*     */   public void storeEducation(Education edu)
/*     */   {
/*  44 */     super.store(edu);
/*     */   }
/*     */ 
/*     */   public void deleteEducation(Education edu)
/*     */   {
/*  53 */     super.delete(edu);
/*     */   }
/*     */ 
/*     */   public void deleteAllEducation(Collection<Education> edus)
/*     */   {
/*  62 */     super.deleteAll(edus);
/*     */   }
/*     */ 
/*     */   public List<Education> loadAllEducation(Long[] eduIds)
/*     */   {
/*  72 */     return super.loadAll(Education.class, eduIds);
/*     */   }
/*     */ 
/*     */   public Education loadEducation(Long eduId)
/*     */   {
/*  82 */     return (Education)super.load(Education.class, eduId);
/*     */   }
/*     */ 
/*     */   public List<Education> loadAllEducation()
/*     */   {
/*  91 */     return super.loadAll(Education.class);
/*     */   }
/*     */ 
/*     */   public List<Education> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(Education.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Education> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(Education.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.edu.hibernate.HibernateEducation
 * JD-Core Version:    0.6.2
 */