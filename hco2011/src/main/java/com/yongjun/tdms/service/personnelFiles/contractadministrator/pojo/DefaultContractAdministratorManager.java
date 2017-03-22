/*     */ package com.yongjun.tdms.service.personnelFiles.contractadministrator.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.contractadministrator.ContractAdministratorDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
/*     */ import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultContractAdministratorManager extends BaseManager
/*     */   implements ContractAdministratorManager
/*     */ {
/*     */   private final ContractAdministratorDao contractAdministratordao;
/*     */ 
/*     */   public DefaultContractAdministratorManager(ContractAdministratorDao contractAdministratordao)
/*     */   {
/*  27 */     this.contractAdministratordao = contractAdministratordao;
/*     */   }
/*     */ 
/*     */   public void storeContractAdministrator(ContractAdministrator pf)
/*     */   {
/*  34 */     this.contractAdministratordao.storeContractAdministrator(pf);
/*     */   }
/*     */ 
/*     */   public void deleteContractAdministrator(ContractAdministrator pf)
/*     */   {
/*  42 */     this.contractAdministratordao.deleteContractAdministrator(pf);
/*     */   }
/*     */ 
/*     */   public void deleteAllContractAdministrator(Collection<ContractAdministrator> pfs)
/*     */   {
/*  50 */     this.contractAdministratordao.deleteAllContractAdministrator(pfs);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadAllContractAdministrator(Long[] pfIds)
/*     */   {
/*  59 */     return this.contractAdministratordao.loadAllContractAdministrator(pfIds);
/*     */   }
/*     */ 
/*     */   public ContractAdministrator loadContractAdministrator(Long pfId)
/*     */   {
/*  68 */     return this.contractAdministratordao.loadContractAdministrator(pfId);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadAllContractAdministrator()
/*     */   {
/*  76 */     return this.contractAdministratordao.loadAllContractAdministrator();
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.contractAdministratordao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.contractAdministratordao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledContractAdministrators(Collection<ContractAdministrator> pfs)
/*     */   {
/* 105 */     for (ContractAdministrator c : pfs) {
/* 106 */       c.setDisabled(true);
/* 107 */       this.contractAdministratordao.storeContractAdministrator(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledContractAdministrators(Collection<ContractAdministrator> pfs)
/*     */   {
/* 116 */     for (ContractAdministrator c : pfs) {
/* 117 */       c.setDisabled(false);
/* 118 */       this.contractAdministratordao.storeContractAdministrator(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 127 */     return this.contractAdministratordao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.contractadministrator.pojo.DefaultContractAdministratorManager
 * JD-Core Version:    0.6.2
 */