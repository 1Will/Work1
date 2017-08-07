/*     */ package com.yongjun.tdms.dao.COM.contractCustomer.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.COM.contractCustomer.ContractCustomerDao;
/*     */ import com.yongjun.tdms.model.COM.contractCustomer.ContractCustomer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateContractCustomerDao extends BaseHibernateDao
/*     */   implements ContractCustomerDao
/*     */ {
/*     */   public void storeContractCustomer(ContractCustomer cc)
/*     */   {
/*  24 */     store(cc);
/*     */   }
/*     */ 
/*     */   public void deleteContractCustomer(ContractCustomer cc)
/*     */   {
/*  32 */     delete(cc);
/*     */   }
/*     */ 
/*     */   public void deleteAllContractCustomer(Collection<ContractCustomer> ccs)
/*     */   {
/*  40 */     deleteAll(ccs);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadAllContractCustomer(Long[] ccIds)
/*     */   {
/*  49 */     return loadAll(ContractCustomer.class, ccIds);
/*     */   }
/*     */ 
/*     */   public ContractCustomer loadContractCustomer(Long ccId)
/*     */   {
/*  58 */     return (ContractCustomer)load(ContractCustomer.class, ccId);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadAllContractCustomer()
/*     */   {
/*  66 */     return loadAll(ContractCustomer.class);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  77 */     return loadByKey(ContractCustomer.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  88 */     return loadByKeyArray(ContractCustomer.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  96 */     String hql = "select c.code from ContractCustomer as c where  c.code like '%" + code + "%'";
/*  97 */     List codeList = getSession().createQuery(hql).list();
/*  98 */     if (null!=codeList && codeList.size() > 0) {
/*  99 */       List items = new ArrayList();
/* 100 */       for (int i = 0; i < codeList.size(); i++) {
/* 101 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 102 */         items.add(item);
/*     */       }
/* 104 */       Collections.sort(items);
/* 105 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 107 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.contractCustomer.hibernate.HibernateContractCustomerDao
 * JD-Core Version:    0.6.2
 */