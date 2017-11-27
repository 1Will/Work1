/*     */ package com.yongjun.tdms.service.COM.VOC.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.COM.VOC.VocDao;
/*     */ import com.yongjun.tdms.model.COM.VOC.Voc;
/*     */ import com.yongjun.tdms.service.COM.VOC.VocManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultVocManager extends BaseManager
/*     */   implements VocManager
/*     */ {
/*     */   private final VocDao vocDao;
/*     */ 
/*     */   public DefaultVocManager(VocDao vocDao)
/*     */   {
/*  27 */     this.vocDao = vocDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllVoc(Collection<Voc> vocs)
/*     */   {
/*  34 */     this.vocDao.deleteAllVoc(vocs);
/*     */   }
/*     */ 
/*     */   public void deleteVoc(Voc voc)
/*     */   {
/*  41 */     this.vocDao.deleteVoc(voc);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadAllVoc(Long[] vocIds)
/*     */   {
/*  49 */     return this.vocDao.loadAllVoc(vocIds);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadAllVoc()
/*     */   {
/*  56 */     return this.vocDao.loadAllVoc();
/*     */   }
/*     */ 
/*     */   public List<Voc> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  68 */     return this.vocDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  79 */     return this.vocDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Voc loadVoc(Long vocId)
/*     */   {
/*  87 */     return this.vocDao.loadVoc(vocId);
/*     */   }
/*     */ 
/*     */   public void storeVoc(Voc voc)
/*     */   {
/*  94 */     this.vocDao.storeVoc(voc);
/*     */   }
/*     */ 
/*     */   public void disabledAllVocs(List<Voc> voc)
/*     */   {
/* 101 */     for (Voc c : voc) {
/* 102 */       c.setDisabled(true);
/* 103 */       this.vocDao.storeVoc(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllVoc(List<Voc> voc)
/*     */   {
/* 111 */     for (Voc c : voc) {
/* 112 */       c.setDisabled(false);
/* 113 */       this.vocDao.storeVoc(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 122 */     return this.vocDao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.COM.VOC.pojo.DefaultVocManager
 * JD-Core Version:    0.6.2
 */