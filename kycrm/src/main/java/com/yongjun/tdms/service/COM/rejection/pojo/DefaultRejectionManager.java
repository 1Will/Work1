/*     */ package com.yongjun.tdms.service.COM.rejection.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.COM.rejection.RejectionDao;
/*     */ import com.yongjun.tdms.model.COM.rejection.Rejection;
/*     */ import com.yongjun.tdms.service.COM.rejection.RejectionManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultRejectionManager extends BaseManager
/*     */   implements RejectionManager
/*     */ {
/*     */   private final RejectionDao rejectionDao;
/*     */ 
/*     */   public DefaultRejectionManager(RejectionDao rejectionDao)
/*     */   {
/*  27 */     this.rejectionDao = rejectionDao;
/*     */   }
/*     */ 
/*     */   public void storeRejection(Rejection rejection)
/*     */   {
/*  34 */     this.rejectionDao.storeRejection(rejection);
/*     */   }
/*     */ 
/*     */   public void deleteRejection(Rejection rejection)
/*     */   {
/*  42 */     this.rejectionDao.deleteRejection(rejection);
/*     */   }
/*     */ 
/*     */   public void deleteAllRejection(Collection<Rejection> rejections)
/*     */   {
/*  50 */     this.rejectionDao.deleteAllRejection(rejections);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadAllRejection(Long[] rejectionIds)
/*     */   {
/*  59 */     return this.rejectionDao.loadAllRejection(rejectionIds);
/*     */   }
/*     */ 
/*     */   public Rejection loadRejection(Long rejectionId)
/*     */   {
/*  68 */     return this.rejectionDao.loadRejection(rejectionId);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadAllRejection()
/*     */   {
/*  76 */     return this.rejectionDao.loadAllRejection();
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.rejectionDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Rejection> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllRejections(List<Rejection> rejections)
/*     */   {
/* 106 */     for (Rejection r : rejections) {
/* 107 */       r.setDisabled(true);
/* 108 */       this.rejectionDao.storeRejection(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllRejections(List<Rejection> rejections)
/*     */   {
/* 117 */     for (Rejection r : rejections) {
/* 118 */       r.setDisabled(false);
/* 119 */       this.rejectionDao.storeRejection(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 129 */     return this.rejectionDao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.COM.rejection.pojo.DefaultRejectionManager
 * JD-Core Version:    0.6.2
 */