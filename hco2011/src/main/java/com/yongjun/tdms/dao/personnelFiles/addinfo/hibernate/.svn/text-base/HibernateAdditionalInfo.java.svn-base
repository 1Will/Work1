/*     */ package com.yongjun.tdms.dao.personnelFiles.addinfo.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.addinfo.AdditionalInfoDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.AdditionalInfo;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateAdditionalInfo extends BaseHibernateDao
/*     */   implements AdditionalInfoDao
/*     */ {
/*     */   public void storeAdditional(AdditionalInfo addInfo)
/*     */   {
/*  44 */     super.store(addInfo);
/*     */   }
/*     */ 
/*     */   public void deleteAdditional(AdditionalInfo addInfo)
/*     */   {
/*  53 */     super.delete(addInfo);
/*     */   }
/*     */ 
/*     */   public void deleteAllAdditional(Collection<AdditionalInfo> addInfos)
/*     */   {
/*  62 */     super.deleteAll(addInfos);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadAllAdditional(Long[] addInfoIds)
/*     */   {
/*  72 */     return super.loadAll(AdditionalInfo.class, addInfoIds);
/*     */   }
/*     */ 
/*     */   public AdditionalInfo loadAdditional(Long addInfoId)
/*     */   {
/*  82 */     return (AdditionalInfo)super.load(AdditionalInfo.class, addInfoId);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadAllAdditional()
/*     */   {
/*  91 */     return super.loadAll(AdditionalInfo.class);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(AdditionalInfo.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(AdditionalInfo.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.addinfo.hibernate.HibernateAdditionalInfo
 * JD-Core Version:    0.6.2
 */