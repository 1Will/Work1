/*     */ package com.yongjun.tdms.service.COM.contractCustomer.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.COM.contractCustomer.ContractCustomerDao;
/*     */ import com.yongjun.tdms.model.COM.contractCustomer.ContractCustomer;
/*     */ import com.yongjun.tdms.service.COM.contractCustomer.ContractCustomerManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultContractCustomer extends BaseManager
/*     */   implements ContractCustomerManager
/*     */ {
/*     */   private final ContractCustomerDao contractCustomerDao;
/*     */ 
/*     */   public DefaultContractCustomer(ContractCustomerDao contractCustomerDao)
/*     */   {
/*  27 */     this.contractCustomerDao = contractCustomerDao;
/*     */   }
/*     */ 
/*     */   public void storeContractCustomer(ContractCustomer cc)
/*     */   {
/*  34 */     this.contractCustomerDao.storeContractCustomer(cc);
/*     */   }
/*     */ 
/*     */   public void deleteContractCustomer(ContractCustomer cc)
/*     */   {
/*  42 */     this.contractCustomerDao.deleteContractCustomer(cc);
/*     */   }
/*     */ 
/*     */   public void deleteAllContractCustomer(Collection<ContractCustomer> ccs)
/*     */   {
/*  50 */     this.contractCustomerDao.deleteAllContractCustomer(ccs);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadAllContractCustomer(Long[] ccIds)
/*     */   {
/*  59 */     return this.contractCustomerDao.loadAllContractCustomer(ccIds);
/*     */   }
/*     */ 
/*     */   public ContractCustomer loadContractCustomer(Long ccId)
/*     */   {
/*  68 */     return this.contractCustomerDao.loadContractCustomer(ccId);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadAllContractCustomer()
/*     */   {
/*  76 */     return this.contractCustomerDao.loadAllContractCustomer();
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.contractCustomerDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.contractCustomerDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllContractCustomers(List<ContractCustomer> contractCustomer)
/*     */   {
/* 104 */     for (ContractCustomer c : contractCustomer) {
/* 105 */       c.setDisabled(true);
/* 106 */       this.contractCustomerDao.storeContractCustomer(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllContractCustomers(List<ContractCustomer> contractCustomer)
/*     */   {
/* 113 */     for (ContractCustomer c : contractCustomer) {
/* 114 */       c.setDisabled(false);
/* 115 */       this.contractCustomerDao.storeContractCustomer(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 124 */     return this.contractCustomerDao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.COM.contractCustomer.pojo.DefaultContractCustomer
 * JD-Core Version:    0.6.2
 */