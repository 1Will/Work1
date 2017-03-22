/*     */ package com.yongjun.tdms.dao.customerservicemanagement.customercomplaint.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.customercomplaint.CustomerComplaintDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customercomplaint.CustomerComplaint;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateCustomerComplaint extends BaseHibernateDao
/*     */   implements CustomerComplaintDao
/*     */ {
/*     */   public void deleteAllCustomerComplaint(List<CustomerComplaint> ts)
/*     */   {
/*  23 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public void deleteCustomerComplaint(CustomerComplaint t)
/*     */   {
/*  30 */     delete(t);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadAllCustomerComplaint(Long[] tIds)
/*     */   {
/*  38 */     return loadAll(CustomerComplaint.class, tIds);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  49 */     return loadByKey(CustomerComplaint.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  60 */     return loadByKeyArray(CustomerComplaint.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public CustomerComplaint loadCustomerComplaint(Long id)
/*     */   {
/*  68 */     return (CustomerComplaint)load(CustomerComplaint.class, id);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadCustomerComplaint()
/*     */   {
/*  75 */     return loadAll(CustomerComplaint.class);
/*     */   }
/*     */ 
/*     */   public void storeCustomerComplaint(CustomerComplaint t)
/*     */   {
/*  82 */     store(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  90 */     String hql = "select c.code from CustomerComplaint as c where  c.code like '%" + code + "%'";
/*  91 */     List codeList = getSession().createQuery(hql).list();
/*  92 */     if (codeList.size() > 0) {
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
 * Qualified Name:     com.yongjun.tdms.dao.customerservicemanagement.customercomplaint.hibernate.HibernateCustomerComplaint
 * JD-Core Version:    0.6.2
 */