/*     */ package com.yongjun.tdms.dao.personnelFiles.contractadministrator.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.contractadministrator.ContractAdministratorDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateContractAdministratorDao extends BaseHibernateDao
/*     */   implements ContractAdministratorDao
/*     */ {
/*     */   public void storeContractAdministrator(ContractAdministrator pf)
/*     */   {
/*  24 */     store(pf);
/*     */   }
/*     */ 
/*     */   public void deleteContractAdministrator(ContractAdministrator pf)
/*     */   {
/*  32 */     delete(pf);
/*     */   }
/*     */ 
/*     */   public void deleteAllContractAdministrator(Collection<ContractAdministrator> pfs)
/*     */   {
/*  40 */     deleteAll(pfs);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadAllContractAdministrator(Long[] pfIds)
/*     */   {
/*  49 */     return loadAll(ContractAdministrator.class, pfIds);
/*     */   }
/*     */ 
/*     */   public ContractAdministrator loadContractAdministrator(Long pfId)
/*     */   {
/*  58 */     return (ContractAdministrator)load(ContractAdministrator.class, pfId);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadAllContractAdministrator()
/*     */   {
/*  66 */     return loadAll(ContractAdministrator.class);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  77 */     return loadByKey(ContractAdministrator.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  88 */     return loadByKeyArray(ContractAdministrator.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  96 */     String hql = "select ca.contractCode from ContractAdministrator as ca where ca.contractCode like '%" + code + "%'";
/*     */ 
/*  98 */     List codeList = getSession().createQuery(hql).list();
/*  99 */     if (null!= codeList && codeList.size() > 0) {
/* 100 */       List items = new ArrayList();
/* 101 */       for (int i = 0; i < codeList.size(); i++) {
/* 102 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 103 */         items.add(item);
/*     */       }
/* 105 */       Collections.sort(items);
/* 106 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.contractadministrator.hibernate.HibernateContractAdministratorDao
 * JD-Core Version:    0.6.2
 */