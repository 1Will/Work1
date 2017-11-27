/*     */ package com.yongjun.tdms.service.COM.co.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.COM.co.CoDao;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.service.COM.co.CoManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCoManager extends BaseManager
/*     */   implements CoManager
/*     */ {
/*     */   private final CoDao coDao;
/*     */ 
/*     */   public DefaultCoManager(CoDao coDao)
/*     */   {
/*  27 */     this.coDao = coDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllCo(Collection<Co> cos)
/*     */   {
/*  34 */     this.coDao.deleteAllCo(cos);
/*     */   }
/*     */ 
/*     */   public void deleteCo(Co co)
/*     */   {
/*  41 */     this.coDao.deleteCo(co);
/*     */   }
/*     */ 
/*     */   public List<Co> loadAllCo(Long[] coIds)
/*     */   {
/*  49 */     return this.coDao.loadAllCo(coIds);
/*     */   }
/*     */ 
/*     */   public List<Co> loadAllCo()
/*     */   {
/*  56 */     return this.coDao.loadAllCo();
/*     */   }
/*     */ 
/*     */   public List<Co> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  68 */     return this.coDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Co> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  79 */     return this.coDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Co loadCo(Long coId)
/*     */   {
/*  87 */     return this.coDao.loadCo(coId);
/*     */   }
/*     */ 
/*     */   public void storeCo(Co co)
/*     */   {
/*  94 */     this.coDao.storeCo(co);
/*     */   }
/*     */ 
/*     */   public void disabledAllCos(List<Co> co)
/*     */   {
/* 101 */     for (Co c : co) {
/* 102 */       c.setDisabled(true);
/* 103 */       this.coDao.storeCo(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllCos(List<Co> co)
/*     */   {
/* 111 */     for (Co c : co) {
/* 112 */       c.setDisabled(false);
/* 113 */       this.coDao.storeCo(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 122 */     return this.coDao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.COM.co.pojo.DefaultCoManager
 * JD-Core Version:    0.6.2
 */