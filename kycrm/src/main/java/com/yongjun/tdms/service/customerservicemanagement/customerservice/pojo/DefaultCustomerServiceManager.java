/*     */ package com.yongjun.tdms.service.customerservicemanagement.customerservice.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.customerservice.CustomerServiceDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customerservice.CustomerService;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.customerservice.CustomerServiceManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCustomerServiceManager extends BaseManager
/*     */   implements CustomerServiceManager
/*     */ {
/*     */   private final CustomerServiceDao dao;
/*     */ 
/*     */   public DefaultCustomerServiceManager(CustomerServiceDao dao)
/*     */   {
/*  26 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void deleteAllCustomerService(List<CustomerService> ts)
/*     */   {
/*  33 */     this.dao.deleteAllCustomerService(ts);
/*     */   }
/*     */ 
/*     */   public void deleteCustomerService(CustomerService t)
/*     */   {
/*  40 */     this.dao.deleteCustomerService(t);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadAllCustomerService(Long[] tIds)
/*     */   {
/*  48 */     return this.dao.loadAllCustomerService(tIds);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  59 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  70 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public CustomerService loadCustomerService(Long id)
/*     */   {
/*  78 */     return this.dao.loadCustomerService(id);
/*     */   }
/*     */ 
/*     */   public List<CustomerService> loadCustomerService()
/*     */   {
/*  85 */     return this.dao.loadCustomerService();
/*     */   }
/*     */ 
/*     */   public void storeCustomerService(CustomerService t)
/*     */   {
/*  92 */     this.dao.storeCustomerService(t);
/*     */   }
/*     */ 
/*     */   public void disabledAllCustomerService(List<CustomerService> ts)
/*     */   {
/*  99 */     for (CustomerService c : ts) {
/* 100 */       c.setDisabled(true);
/* 101 */       this.dao.storeCustomerService(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllCustomerService(List<CustomerService> ts)
/*     */   {
/* 110 */     for (CustomerService c : ts) {
/* 111 */       c.setDisabled(false);
/* 112 */       this.dao.storeCustomerService(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 121 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.customerservice.pojo.DefaultCustomerServiceManager
 * JD-Core Version:    0.6.2
 */