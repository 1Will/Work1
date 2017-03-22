/*     */ package com.yongjun.tdms.service.customercontract.contractmanagement.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customercontract.contractmanagement.ContractManagementDao;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultContractManagementManager extends BaseManager
/*     */   implements ContractManagementManager
/*     */ {
/*     */   private final ContractManagementDao dao;
/*     */ 
/*     */   public DefaultContractManagementManager(ContractManagementDao dao)
/*     */   {
/*  23 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeContractManagement(ContractManagement t)
/*     */   {
/*  33 */     this.dao.storeContractManagement(t);
/*     */   }
/*     */ 
/*     */   public ContractManagement loadContractManagement(Long id)
/*     */   {
/*  42 */     return this.dao.loadContractManagement(id);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadContractManagement()
/*     */   {
/*  50 */     return this.dao.loadContractManagement();
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadAllContractManagement(Long[] tIds)
/*     */   {
/*  60 */     return this.dao.loadAllContractManagement(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteContractManagement(ContractManagement t)
/*     */   {
/*  68 */     this.dao.deleteContractManagement(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllContractManagement(List<ContractManagement> ts)
/*     */   {
/*  76 */     this.dao.deleteAllContractManagement(ts);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 106 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllContractManagement(List<ContractManagement> ts)
/*     */   {
/* 113 */     for (ContractManagement contractManagement : ts) {
/* 114 */       contractManagement.setDisabled(true);
/* 115 */       this.dao.storeContractManagement(contractManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllContractManagement(List<ContractManagement> ts)
/*     */   {
/* 124 */     for (ContractManagement contractManagement : ts) {
/* 125 */       contractManagement.setDisabled(false);
/* 126 */       this.dao.storeContractManagement(contractManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public double getSumReturnPrice(long id)
/*     */     throws DaoException
/*     */   {
/* 136 */     return this.dao.getSumReturnPrice(id);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.contractmanagement.pojo.DefaultContractManagementManager
 * JD-Core Version:    0.6.2
 */