/*     */ package com.yongjun.tdms.service.CustomerRelationship.additionalInfo.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.additionalInfo.CusAdditionalInfoDao;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.additionalInfo.CusAdditionalInfoManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCusAdditionalInfoManager extends BaseManager
/*     */   implements CusAdditionalInfoManager
/*     */ {
/*     */   public final CusAdditionalInfoDao cusAdditionalInfoDao;
/*     */ 
/*     */   public DefaultCusAdditionalInfoManager(CusAdditionalInfoDao cusAdditionalInfoDao)
/*     */   {
/*  40 */     this.cusAdditionalInfoDao = cusAdditionalInfoDao;
/*     */   }
/*     */ 
/*     */   public void storeCusAdditionalInfo(CusAdditionalInfo cAdd)
/*     */   {
/*  47 */     this.cusAdditionalInfoDao.storeCusAdditionalInfo(cAdd);
/*     */   }
/*     */ 
/*     */   public CusAdditionalInfo loadCusAdditionalInfo(Long cAddId)
/*     */   {
/*  56 */     return this.cusAdditionalInfoDao.loadCusAdditionalInfo(cAddId);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadCusAdditionalInfo()
/*     */   {
/*  64 */     return this.cusAdditionalInfoDao.loadCusAdditionalInfo();
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadAllCusAdditionalInfo(Long[] cAddIds)
/*     */   {
/*  73 */     return this.cusAdditionalInfoDao.loadAllCusAdditionalInfo(cAddIds);
/*     */   }
/*     */ 
/*     */   public void deleteCusAdditionalInfo(CusAdditionalInfo cAdd)
/*     */   {
/*  81 */     this.cusAdditionalInfoDao.deleteCusAdditionalInfo(cAdd);
/*     */   }
/*     */ 
/*     */   public void deleteAllCusAdditionalInfo(List<CusAdditionalInfo> cAdds)
/*     */   {
/*  89 */     this.cusAdditionalInfoDao.deleteAllCusAdditionalInfo(cAdds);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  99 */     return this.cusAdditionalInfoDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 108 */     return this.cusAdditionalInfoDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.CustomerRelationship.additionalInfo.pojo.DefaultCusAdditionalInfoManager
 * JD-Core Version:    0.6.2
 */