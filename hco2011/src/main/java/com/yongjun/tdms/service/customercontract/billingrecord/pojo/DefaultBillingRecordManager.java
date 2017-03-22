/*     */ package com.yongjun.tdms.service.customercontract.billingrecord.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customercontract.billingrecord.BillingRecordDao;
/*     */ import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
/*     */ import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultBillingRecordManager extends BaseManager
/*     */   implements BillingRecordManager
/*     */ {
/*     */   private final BillingRecordDao billingRecordDao;
/*     */ 
/*     */   public DefaultBillingRecordManager(BillingRecordDao billingRecordDao)
/*     */   {
/*  26 */     this.billingRecordDao = billingRecordDao;
/*     */   }
/*     */ 
/*     */   public void storeBillingRecord(BillingRecord t)
/*     */   {
/*  33 */     this.billingRecordDao.storeBillingRecord(t);
/*     */   }
/*     */ 
/*     */   public BillingRecord loadBillingRecord(Long id)
/*     */   {
/*  42 */     return this.billingRecordDao.loadBillingRecord(id);
/*     */   }
/*     */ 
/*     */   public List<BillingRecord> loadBillingRecord()
/*     */   {
/*  50 */     return this.billingRecordDao.loadBillingRecord();
/*     */   }
/*     */ 
/*     */   public List<BillingRecord> loadAllBillingRecord(Long[] tIds)
/*     */   {
/*  59 */     return this.billingRecordDao.loadAllBillingRecord(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteBillingRecord(BillingRecord t)
/*     */   {
/*  67 */     this.billingRecordDao.deleteBillingRecord(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllBillingRecord(List<BillingRecord> ts)
/*     */   {
/*  75 */     this.billingRecordDao.deleteAllBillingRecord(ts);
/*     */   }
/*     */ 
/*     */   public List<BillingRecord> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  86 */     return this.billingRecordDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<BillingRecord> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  96 */     return this.billingRecordDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllBillingRecord(List<BillingRecord> billingRecords)
/*     */   {
/* 103 */     for (BillingRecord b : billingRecords) {
/* 104 */       b.setDisabled(true);
/* 105 */       this.billingRecordDao.storeBillingRecord(b);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllBillingRecord(List<BillingRecord> billingRecords)
/*     */   {
/* 114 */     for (BillingRecord b : billingRecords) {
/* 115 */       b.setDisabled(false);
/* 116 */       this.billingRecordDao.storeBillingRecord(b);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.billingrecord.pojo.DefaultBillingRecordManager
 * JD-Core Version:    0.6.2
 */