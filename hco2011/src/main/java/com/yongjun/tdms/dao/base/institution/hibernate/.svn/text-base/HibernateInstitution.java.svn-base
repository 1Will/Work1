/*     */ package com.yongjun.tdms.dao.base.institution.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.tdms.dao.base.institution.InstitutionDao;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateInstitution extends BaseHibernateDao
/*     */   implements InstitutionDao
/*     */ {
/*     */   public void storeInstitution(Institution institution)
/*     */   {
/*  50 */     super.store(institution);
/*     */   }
/*     */ 
/*     */   public void deleteInstitution(Institution institution)
/*     */   {
/*  59 */     super.delete(institution);
/*     */   }
/*     */ 
/*     */   public void deleteAllInstitution(Collection<Institution> institutions)
/*     */   {
/*  68 */     super.deleteAll(institutions);
/*     */   }
/*     */ 
/*     */   public List<Institution> loadAllInstitution(Long[] institutionIds)
/*     */   {
/*  78 */     return super.loadAll(Institution.class, institutionIds);
/*     */   }
/*     */ 
/*     */   public Institution loadInstitution(Long institutionId)
/*     */   {
/*  88 */     return (Institution)super.load(Institution.class, institutionId);
/*     */   }
/*     */ 
/*     */   public List<Institution> loadAllInstitution()
/*     */   {
/*  97 */     return super.loadAll(Institution.class);
/*     */   }
/*     */ 
/*     */   public List<Institution> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 109 */     return loadByKey(Institution.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public int getInstMaxId()
/*     */   {
/* 118 */     int maxId = Integer.parseInt(getSession().getNamedQuery("GetInstMaxId").uniqueResult().toString());
/* 119 */     return maxId;
/*     */   }
/*     */ 
/*     */   public int getInstSteps()
/*     */   {
/* 128 */     int count = ((Integer)getSession().getNamedQuery("GetInstSteps").uniqueResult()).intValue();
/* 129 */     return count + 1;
/*     */   }
/*     */ 
/*     */   public List<Institution> getInstsByStep(int step)
/*     */   {
/* 141 */     return getSession().getNamedQuery("GetInstsByStep").setParameter("step", Integer.valueOf(step)).list();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.institution.hibernate.HibernateInstitution
 * JD-Core Version:    0.6.2
 */