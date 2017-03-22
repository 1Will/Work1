/*     */ package com.yongjun.tdms.service.customerservicemanagement.customercomplaint.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.customercomplaint.CustomerComplaintDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customercomplaint.CustomerComplaint;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.customercomplaint.CustomerComplaintManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCustomerComplaintManager extends BaseManager
/*     */   implements CustomerComplaintManager
/*     */ {
/*     */   private final CustomerComplaintDao dao;
/*     */ 
/*     */   public DefaultCustomerComplaintManager(CustomerComplaintDao dao)
/*     */   {
/*  26 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void deleteAllCustomerComplaint(List<CustomerComplaint> ts)
/*     */   {
/*  34 */     this.dao.deleteAllCustomerComplaint(ts);
/*     */   }
/*     */ 
/*     */   public void deleteCustomerComplaint(CustomerComplaint t)
/*     */   {
/*  41 */     this.dao.deleteCustomerComplaint(t);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadAllCustomerComplaint(Long[] tIds)
/*     */   {
/*  51 */     return this.dao.loadAllCustomerComplaint(tIds);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  62 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  73 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public CustomerComplaint loadCustomerComplaint(Long id)
/*     */   {
/*  81 */     return this.dao.loadCustomerComplaint(id);
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> loadCustomerComplaint()
/*     */   {
/*  88 */     return this.dao.loadCustomerComplaint();
/*     */   }
/*     */ 
/*     */   public void storeCustomerComplaint(CustomerComplaint t)
/*     */   {
/*  95 */     this.dao.storeCustomerComplaint(t);
/*     */   }
/*     */ 
/*     */   public void disabledAllCustomerComplaint(List<CustomerComplaint> ts)
/*     */   {
/* 102 */     for (CustomerComplaint c : ts) {
/* 103 */       c.setDisabled(true);
/* 104 */       this.dao.storeCustomerComplaint(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllCustomerComplaint(List<CustomerComplaint> ts)
/*     */   {
/* 113 */     for (CustomerComplaint c : ts) {
/* 114 */       c.setDisabled(false);
/* 115 */       this.dao.storeCustomerComplaint(c);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 124 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.customercomplaint.pojo.DefaultCustomerComplaintManager
 * JD-Core Version:    0.6.2
 */