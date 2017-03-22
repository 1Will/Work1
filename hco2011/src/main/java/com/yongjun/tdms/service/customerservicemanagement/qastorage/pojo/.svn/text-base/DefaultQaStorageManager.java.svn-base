/*     */ package com.yongjun.tdms.service.customerservicemanagement.qastorage.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.qastorage.QaStorageDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.qastorage.QaStorage;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.qastorage.QaStorageManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultQaStorageManager extends BaseManager
/*     */   implements QaStorageManager
/*     */ {
/*     */   private final QaStorageDao dao;
/*     */ 
/*     */   public DefaultQaStorageManager(QaStorageDao dao)
/*     */   {
/*  28 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void deleteAllQaStorage(List<QaStorage> ts)
/*     */   {
/*  37 */     this.dao.deleteAllQaStorage(ts);
/*     */   }
/*     */ 
/*     */   public void deleteQaStorage(QaStorage t)
/*     */   {
/*  44 */     this.dao.deleteQaStorage(t);
/*     */   }
/*     */ 
/*     */   public void disabledAllQaStorage(List<QaStorage> ts)
/*     */   {
/*  51 */     for (QaStorage q : ts) {
/*  52 */       q.setDisabled(true);
/*  53 */       this.dao.storeQaStorage(q);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllQaStorage(List<QaStorage> ts)
/*     */   {
/*  61 */     for (QaStorage q : ts) {
/*  62 */       q.setDisabled(false);
/*  63 */       this.dao.storeQaStorage(q);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadAllQaStorage(Long[] tIds)
/*     */   {
/*  72 */     return this.dao.loadAllQaStorage(tIds);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  83 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  94 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public QaStorage loadQaStorage(Long id)
/*     */   {
/* 102 */     return this.dao.loadQaStorage(id);
/*     */   }
/*     */ 
/*     */   public List<QaStorage> loadQaStorage()
/*     */   {
/* 109 */     return this.dao.loadQaStorage();
/*     */   }
/*     */ 
/*     */   public void storeQaStorage(QaStorage t)
/*     */   {
/* 116 */     this.dao.storeQaStorage(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 124 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.qastorage.pojo.DefaultQaStorageManager
 * JD-Core Version:    0.6.2
 */