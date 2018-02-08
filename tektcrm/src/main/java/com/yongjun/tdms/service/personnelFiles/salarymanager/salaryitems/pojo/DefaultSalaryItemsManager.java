/*     */ package com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryitems.SalaryItemsDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.SalaryItemsManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSalaryItemsManager extends BaseManager
/*     */   implements SalaryItemsManager
/*     */ {
/*     */   private final SalaryItemsDao salaryItemsDao;
/*     */ 
/*     */   public DefaultSalaryItemsManager(SalaryItemsDao salaryItemsDao)
/*     */   {
/*  27 */     this.salaryItemsDao = salaryItemsDao;
/*     */   }
/*     */ 
/*     */   public void storeSalaryItems(SalaryItems salaryItems)
/*     */   {
/*  34 */     this.salaryItemsDao.storeSalaryItems(salaryItems);
/*     */   }
/*     */ 
/*     */   public SalaryItems loadSalaryItems(Long salaryItemsId)
/*     */   {
/*  43 */     return this.salaryItemsDao.loadSalaryItems(salaryItemsId);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadAllSalaryItemss()
/*     */   {
/*  51 */     return this.salaryItemsDao.loadAllSalaryItemss();
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadAllSalaryItems(Long[] salaryItemsIds)
/*     */   {
/*  60 */     return this.salaryItemsDao.loadAllSalaryItems(salaryItemsIds);
/*     */   }
/*     */ 
/*     */   public void deleteSalaryItems(SalaryItems salaryItems)
/*     */   {
/*  68 */     this.salaryItemsDao.deleteSalaryItems(salaryItems);
/*     */   }
/*     */ 
/*     */   public void deleteAllSalaryItems(List<SalaryItems> salaryItemses)
/*     */   {
/*  76 */     this.salaryItemsDao.deleteAllSalaryItems(salaryItemses);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.salaryItemsDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  97 */     return this.salaryItemsDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledSalaryItems(Collection<SalaryItems> salaryItemses)
/*     */   {
/* 104 */     for (SalaryItems s : salaryItemses) {
/* 105 */       s.setDisabled(true);
/* 106 */       this.salaryItemsDao.storeSalaryItems(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledSalaryItems(Collection<SalaryItems> salaryItemses)
/*     */   {
/* 115 */     for (SalaryItems s : salaryItemses) {
/* 116 */       s.setDisabled(false);
/* 117 */       this.salaryItemsDao.storeSalaryItems(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 126 */     return this.salaryItemsDao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.pojo.DefaultSalaryItemsManager
 * JD-Core Version:    0.6.2
 */