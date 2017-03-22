/*     */ package com.yongjun.tdms.dao.personnelFiles.relation.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.relation.SocialRelationsDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.SocialRelations;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateSocialRelations extends BaseHibernateDao
/*     */   implements SocialRelationsDao
/*     */ {
/*     */   public void storeSocialRelations(SocialRelations sr)
/*     */   {
/*  44 */     super.store(sr);
/*     */   }
/*     */ 
/*     */   public void deleteSocialRelations(SocialRelations sr)
/*     */   {
/*  53 */     super.delete(sr);
/*     */   }
/*     */ 
/*     */   public void deleteAllSocialRelations(Collection<SocialRelations> srs)
/*     */   {
/*  62 */     super.deleteAll(srs);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadAllSocialRelations(Long[] srIds)
/*     */   {
/*  72 */     return super.loadAll(SocialRelations.class, srIds);
/*     */   }
/*     */ 
/*     */   public SocialRelations loadSocialRelations(Long srId)
/*     */   {
/*  82 */     return (SocialRelations)super.load(SocialRelations.class, srId);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadAllSocialRelations()
/*     */   {
/*  91 */     return super.loadAll(SocialRelations.class);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(SocialRelations.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(SocialRelations.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.relation.hibernate.HibernateSocialRelations
 * JD-Core Version:    0.6.2
 */