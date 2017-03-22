/*     */ package com.yongjun.tdms.service.COM.remit.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.COM.remit.RemitDao;
/*     */ import com.yongjun.tdms.model.COM.remit.Remit;
/*     */ import com.yongjun.tdms.service.COM.remit.RemitManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultRemitManager extends BaseManager
/*     */   implements RemitManager
/*     */ {
/*     */   private final RemitDao remitDao;
/*     */ 
/*     */   public DefaultRemitManager(RemitDao remitDao)
/*     */   {
/*  26 */     this.remitDao = remitDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllRemit(Collection<Remit> remits)
/*     */   {
/*  33 */     this.remitDao.deleteAllRemit(remits);
/*     */   }
/*     */ 
/*     */   public void deleteRemit(Remit remit)
/*     */   {
/*  40 */     this.remitDao.deleteRemit(remit);
/*     */   }
/*     */ 
/*     */   public void disabledAllRemits(List<Remit> remit)
/*     */   {
/*  47 */     for (Remit r : remit) {
/*  48 */       r.setDisabled(true);
/*  49 */       storeRemit(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllRemits(List<Remit> remit)
/*     */   {
/*  57 */     for (Remit r : remit) {
/*  58 */       r.setDisabled(false);
/*  59 */       storeRemit(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Remit> loadAllRemit(Long[] remitIds)
/*     */   {
/*  69 */     return this.remitDao.loadAllRemit(remitIds);
/*     */   }
/*     */ 
/*     */   public List<Remit> loadAllRemit()
/*     */   {
/*  76 */     return this.remitDao.loadAllRemit();
/*     */   }
/*     */ 
/*     */   public List<Remit> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.remitDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Remit> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.remitDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Remit loadRemit(Long remitId)
/*     */   {
/* 106 */     return this.remitDao.loadRemit(remitId);
/*     */   }
/*     */ 
/*     */   public void storeRemit(Remit remit)
/*     */   {
/* 113 */     this.remitDao.storeRemit(remit);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 122 */     return this.remitDao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.COM.remit.pojo.DefaultRemitManager
 * JD-Core Version:    0.6.2
 */