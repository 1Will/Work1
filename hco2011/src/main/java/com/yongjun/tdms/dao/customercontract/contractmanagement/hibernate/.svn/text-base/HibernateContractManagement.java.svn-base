/*     */ package com.yongjun.tdms.dao.customercontract.contractmanagement.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.customercontract.contractmanagement.ContractManagementDao;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateContractManagement extends BaseHibernateDao
/*     */   implements ContractManagementDao
/*     */ {
/*     */   public void storeContractManagement(ContractManagement t)
/*     */   {
/*  24 */     store(t);
/*     */   }
/*     */ 
/*     */   public ContractManagement loadContractManagement(Long id)
/*     */   {
/*  35 */     return (ContractManagement)load(ContractManagement.class, id);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadContractManagement()
/*     */   {
/*  43 */     return loadAll(ContractManagement.class);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadAllContractManagement(Long[] tIds)
/*     */   {
/*  52 */     return loadAll(ContractManagement.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteContractManagement(ContractManagement t)
/*     */   {
/*  60 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllContractManagement(List<ContractManagement> ts)
/*     */   {
/*  70 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  82 */     return loadByKey(ContractManagement.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  92 */     return loadByKeyArray(ContractManagement.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 101 */     String hql = "select c.code from ContractManagement as c where  c.code like '%" + code + "%'";
/* 102 */     List codeList = getSession().createQuery(hql).list();
/* 103 */     if (codeList.size() > 0) {
/* 104 */       List items = new ArrayList();
/* 105 */       for (int i = 0; i < codeList.size(); i++) {
/* 106 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 107 */         items.add(item);
/*     */       }
/* 109 */       Collections.sort(items);
/* 110 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   public double getSumReturnPrice(long id)
/*     */     throws DaoException
/*     */   {
/* 124 */     String hql = "select sum(c.sum) from ReturnPlan as c where c.contractManagement.id=" + id;
/* 125 */     List codeList = getSession().createQuery(hql).list();
/*     */ 
/* 127 */     if ((null != codeList) && (codeList.size() > 0)) {
/* 128 */       Double item = (Double)codeList.get(0);
/* 129 */       if (null == item) {
/* 130 */         return 0.0D;
/*     */       }
/* 132 */       return item.doubleValue();
/*     */     }
/* 134 */     return 0.0D;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.contractmanagement.hibernate.HibernateContractManagement
 * JD-Core Version:    0.6.2
 */