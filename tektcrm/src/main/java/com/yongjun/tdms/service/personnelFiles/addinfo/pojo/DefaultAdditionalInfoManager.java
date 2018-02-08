/*     */ package com.yongjun.tdms.service.personnelFiles.addinfo.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.addinfo.AdditionalInfoDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.AdditionalInfo;
/*     */ import com.yongjun.tdms.service.personnelFiles.addinfo.AdditionalInfoManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultAdditionalInfoManager extends BaseManager
/*     */   implements AdditionalInfoManager
/*     */ {
/*     */   private final AdditionalInfoDao additionalDao;
/*     */ 
/*     */   public DefaultAdditionalInfoManager(AdditionalInfoDao additionalDao)
/*     */   {
/*  48 */     this.additionalDao = additionalDao;
/*     */   }
/*     */ 
/*     */   public void storeAdditional(AdditionalInfo addInfo)
/*     */   {
/*  57 */     this.additionalDao.storeAdditional(addInfo);
/*     */   }
/*     */ 
/*     */   public void deleteAdditional(AdditionalInfo addInfo)
/*     */   {
/*  66 */     this.additionalDao.deleteAdditional(addInfo);
/*     */   }
/*     */ 
/*     */   public void deleteAllAdditional(Collection<AdditionalInfo> addInfos)
/*     */   {
/*  75 */     this.additionalDao.deleteAllAdditional(addInfos);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadAllAdditional(Long[] addInfoIds)
/*     */   {
/*  85 */     return this.additionalDao.loadAllAdditional(addInfoIds);
/*     */   }
/*     */ 
/*     */   public AdditionalInfo loadAdditional(Long addInfoId)
/*     */   {
/*  95 */     return this.additionalDao.loadAdditional(addInfoId);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadAllAdditional()
/*     */   {
/* 104 */     return this.additionalDao.loadAllAdditional();
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.additionalDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<AdditionalInfo> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.additionalDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.addinfo.pojo.DefaultAdditionalInfoManager
 * JD-Core Version:    0.6.2
 */