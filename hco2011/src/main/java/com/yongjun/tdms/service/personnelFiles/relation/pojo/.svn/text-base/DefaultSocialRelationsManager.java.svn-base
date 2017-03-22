/*     */ package com.yongjun.tdms.service.personnelFiles.relation.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.relation.SocialRelationsDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.SocialRelations;
/*     */ import com.yongjun.tdms.service.personnelFiles.relation.SocialRelationsManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSocialRelationsManager extends BaseManager
/*     */   implements SocialRelationsManager
/*     */ {
/*     */   private final SocialRelationsDao socialRelationsDao;
/*     */ 
/*     */   public DefaultSocialRelationsManager(SocialRelationsDao socialRelationsDao)
/*     */   {
/*  48 */     this.socialRelationsDao = socialRelationsDao;
/*     */   }
/*     */ 
/*     */   public void storeSocialRelations(SocialRelations sr)
/*     */   {
/*  57 */     this.socialRelationsDao.storeSocialRelations(sr);
/*     */   }
/*     */ 
/*     */   public void deleteSocialRelations(SocialRelations sr)
/*     */   {
/*  66 */     this.socialRelationsDao.deleteSocialRelations(sr);
/*     */   }
/*     */ 
/*     */   public void deleteAllSocialRelations(Collection<SocialRelations> srs)
/*     */   {
/*  75 */     this.socialRelationsDao.deleteAllSocialRelations(srs);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadAllSocialRelations(Long[] srIds)
/*     */   {
/*  85 */     return this.socialRelationsDao.loadAllSocialRelations(srIds);
/*     */   }
/*     */ 
/*     */   public SocialRelations loadSocialRelations(Long srId)
/*     */   {
/*  95 */     return this.socialRelationsDao.loadSocialRelations(srId);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadAllSocialRelations()
/*     */   {
/* 104 */     return this.socialRelationsDao.loadAllSocialRelations();
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.socialRelationsDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<SocialRelations> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.socialRelationsDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledSocialRelations(Collection<SocialRelations> srs)
/*     */   {
/* 135 */     for (SocialRelations sr : srs) {
/* 136 */       sr.setDisabled(true);
/* 137 */       this.socialRelationsDao.storeSocialRelations(sr);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledSocialRelations(Collection<SocialRelations> srs)
/*     */   {
/* 147 */     for (SocialRelations sr : srs) {
/* 148 */       sr.setDisabled(false);
/* 149 */       this.socialRelationsDao.storeSocialRelations(sr);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.relation.pojo.DefaultSocialRelationsManager
 * JD-Core Version:    0.6.2
 */