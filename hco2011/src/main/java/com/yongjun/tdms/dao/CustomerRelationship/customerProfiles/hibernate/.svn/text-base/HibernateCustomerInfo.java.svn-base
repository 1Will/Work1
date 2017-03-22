/*     */ package com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.CustomerInfoDao;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateCustomerInfo extends BaseHibernateDao
/*     */   implements CustomerInfoDao
/*     */ {
/*     */   public void storeCustomerInfo(CustomerInfo ci)
/*     */   {
/*  43 */     super.store(ci);
/*     */   }
/*     */ 
/*     */   public CustomerInfo loadCustomerInfo(Long ciId)
/*     */   {
/*  53 */     return (CustomerInfo)super.load(CustomerInfo.class, ciId);
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> loadAllCustomerInfo()
/*     */   {
/*  62 */     return super.loadAll(CustomerInfo.class);
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> loadAllCustomerInfo(Long[] ciIds)
/*     */   {
/*  72 */     return super.loadAll(CustomerInfo.class, ciIds);
/*     */   }
/*     */ 
/*     */   public void deleteCustomerInfo(CustomerInfo ci)
/*     */   {
/*  81 */     super.delete(ci);
/*     */   }
/*     */ 
/*     */   public void deleteAllCustomerInfo(List<CustomerInfo> ciIds)
/*     */   {
/*  90 */     super.deleteAll(ciIds);
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKey(CustomerInfo.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> getCustomerByName(String name) {
/* 105 */     String hql = "from CustomerInfo c where c.disabled = false and c.name like '%" + name + "%'";
/* 106 */     return getSession().createQuery(hql).list();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.hibernate.HibernateCustomerInfo
 * JD-Core Version:    0.6.2
 */