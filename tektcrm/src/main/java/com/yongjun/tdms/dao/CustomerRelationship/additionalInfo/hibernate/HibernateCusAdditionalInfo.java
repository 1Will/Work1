/*     */ package com.yongjun.tdms.dao.CustomerRelationship.additionalInfo.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.additionalInfo.CusAdditionalInfoDao;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateCusAdditionalInfo extends BaseHibernateDao
/*     */   implements CusAdditionalInfoDao
/*     */ {
/*     */   public void storeCusAdditionalInfo(CusAdditionalInfo cAdd)
/*     */   {
/*  40 */     store(cAdd);
/*     */   }
/*     */ 
/*     */   public CusAdditionalInfo loadCusAdditionalInfo(Long cAddId)
/*     */   {
/*  49 */     return (CusAdditionalInfo)load(CusAdditionalInfo.class, cAddId);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadCusAdditionalInfo()
/*     */   {
/*  57 */     return loadAll(CusAdditionalInfo.class);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadAllCusAdditionalInfo(Long[] cAddIds)
/*     */   {
/*  66 */     return loadAll(CusAdditionalInfo.class, cAddIds);
/*     */   }
/*     */ 
/*     */   public void deleteCusAdditionalInfo(CusAdditionalInfo cAdd)
/*     */   {
/*  74 */     delete(cAdd);
/*     */   }
/*     */ 
/*     */   public void deleteAllCusAdditionalInfo(List<CusAdditionalInfo> cAdds)
/*     */   {
/*  82 */     deleteAll(cAdds);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  92 */     return loadByKey(CusAdditionalInfo.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<CusAdditionalInfo> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return loadByKeyArray(CusAdditionalInfo.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.additionalInfo.hibernate.HibernateCusAdditionalInfo
 * JD-Core Version:    0.6.2
 */