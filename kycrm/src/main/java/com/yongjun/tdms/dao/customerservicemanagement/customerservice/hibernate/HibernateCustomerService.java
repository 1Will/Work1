/*     */ package com.yongjun.tdms.dao.customerservicemanagement.customerservice.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.customerservice.CustomerServiceDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customerservice.CustomerService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateCustomerService extends BaseHibernateDao
/*     */   implements CustomerServiceDao
/*     */ {
/*     */   public void deleteAllCustomerService(List<CustomerService> ts)
/*     */   {
/*  22 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public void deleteCustomerService(CustomerService t)
/*     */   {
/*  29 */     delete(t);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadAllCustomerService(Long[] tIds)
/*     */   {
/*  37 */     return loadAll(CustomerService.class, tIds);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  48 */     return loadByKey(CustomerService.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  59 */     return loadByKeyArray(CustomerService.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public CustomerService loadCustomerService(Long id)
/*     */   {
/*  67 */     return (CustomerService)load(CustomerService.class, id);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadCustomerService()
/*     */   {
/*  75 */     return loadAll(CustomerService.class);
/*     */   }
/*     */ 
/*     */   public void storeCustomerService(CustomerService t)
/*     */   {
/*  82 */     store(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  90 */     String hql = "select c.code from CustomerService as c where  c.code like '%" + code + "%'";
/*  91 */     List codeList = getSession().createQuery(hql).list();
/*  92 */     if (null!= codeList && codeList.size() > 0) {
/*  93 */       List items = new ArrayList();
/*  94 */       for (int i = 0; i < codeList.size(); i++) {
/*  95 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  96 */         items.add(item);
/*     */       }
/*  98 */       Collections.sort(items);
/*  99 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customerservicemanagement.customerservice.hibernate.HibernateCustomerService
 * JD-Core Version:    0.6.2
 */