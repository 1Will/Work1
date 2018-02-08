/*     */ package com.yongjun.tdms.service.financialmanagement.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.financialmanagement.FinancialManagementDao;
/*     */ import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
/*     */ import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultFinancialManagementManager extends BaseManager
/*     */   implements FinancialManagementManager
/*     */ {
/*     */   private final FinancialManagementDao financialManagementDao;
            private final YongJunSequenceManager yongJunSequenceManager;
/*     */ 
/*     */   public DefaultFinancialManagementManager(FinancialManagementDao financialManagementDao,YongJunSequenceManager yongJunSequenceManager)
/*     */   {
/*  27 */     this.financialManagementDao = financialManagementDao;
              this.yongJunSequenceManager=yongJunSequenceManager;
/*     */   }
/*     */ 
/*     */   public void storeFinancialManagement(FinancialManagement financialManagement)
/*     */   {
	if(financialManagement.isNew()){
		financialManagement.setCode((String)this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_FINANCIALMANAGENT));
	    }
	
	
/*  35 */     this.financialManagementDao.storeFinancialManagement(financialManagement);
/*     */   }
/*     */ 
/*     */   public FinancialManagement loadFinancialManagement(Long financialManagementId)
/*     */   {
/*  44 */     return this.financialManagementDao.loadFinancialManagement(financialManagementId);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadAllFinancialManagements()
/*     */   {
/*  52 */     return this.financialManagementDao.loadAllFinancialManagements();
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadAllFinancialManagement(Long[] financialManagementIds)
/*     */   {
/*  61 */     return this.financialManagementDao.loadAllFinancialManagement(financialManagementIds);
/*     */   }
/*     */ 
/*     */   public void deleteFinancialManagement(FinancialManagement financialManagement)
/*     */   {
/*  69 */     this.financialManagementDao.deleteFinancialManagement(financialManagement);
/*     */   }
/*     */ 
/*     */   public void deleteAllFinancialManagement(List<FinancialManagement> financialManagements)
/*     */   {
/*  77 */     this.financialManagementDao.deleteAllFinancialManagement(financialManagements);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  88 */     return this.financialManagementDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.financialManagementDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllFinancialManagements(List<FinancialManagement> financialManagements)
/*     */   {
/* 106 */     for (FinancialManagement f : financialManagements) {
/* 107 */       f.setDisabled(true);
/* 108 */       this.financialManagementDao.storeFinancialManagement(f);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllFinancialManagements(List<FinancialManagement> financialManagements)
/*     */   {
/* 117 */     for (FinancialManagement f : financialManagements) {
/* 118 */       f.setDisabled(false);
/* 119 */       this.financialManagementDao.storeFinancialManagement(f);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 128 */     return this.financialManagementDao.getMaxPFCode(code);
/*     */   }
/*     */
			public HashMap getDataMap(String staDate, String endDate) {
				return this.financialManagementDao.getDataMap(staDate, endDate);
			} 
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.financialmanagement.pojo.DefaultFinancialManagementManager
 * JD-Core Version:    0.6.2
 */