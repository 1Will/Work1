/*     */ package com.yongjun.tdms.model.COM.rejection;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Rejection extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -5397068300480886551L;
/*     */   private String code;
/*     */   private String company;
/*     */   private CustomerInfo customerInfo;
/*     */   private Co co;
/*     */   private PersonnelFiles saleMan;
/*     */   private Products products;
/*     */   private Double sum;
/*     */   private Date rejectionDate;
/*     */   private String reason;
/*     */   private String remark;
/*     */ 
/*     */   public Co getCo()
/*     */   {
/*  66 */     return this.co;
/*     */   }
/*     */ 
/*     */   public void setCo(Co co)
/*     */   {
/*  74 */     this.co = co;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  81 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  89 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getCompany()
/*     */   {
/*  96 */     return this.company;
/*     */   }
/*     */ 
/*     */   public void setCompany(String company)
/*     */   {
/* 104 */     this.company = company;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 111 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 119 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public Products getProducts()
/*     */   {
/* 126 */     return this.products;
/*     */   }
/*     */ 
/*     */   public void setProducts(Products products)
/*     */   {
/* 134 */     this.products = products;
/*     */   }
/*     */ 
/*     */   public String getReason()
/*     */   {
/* 141 */     return this.reason;
/*     */   }
/*     */ 
/*     */   public void setReason(String reason)
/*     */   {
/* 149 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */   public Date getRejectionDate()
/*     */   {
/* 156 */     return this.rejectionDate;
/*     */   }
/*     */ 
/*     */   public void setRejectionDate(Date rejectionDate)
/*     */   {
/* 164 */     this.rejectionDate = rejectionDate;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 171 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 179 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSaleMan()
/*     */   {
/* 186 */     return this.saleMan;
/*     */   }
/*     */ 
/*     */   public void setSaleMan(PersonnelFiles saleMan)
/*     */   {
/* 194 */     this.saleMan = saleMan;
/*     */   }
/*     */ 
/*     */   public Double getSum()
/*     */   {
/* 201 */     return this.sum;
/*     */   }
/*     */ 
/*     */   public void setSum(Double sum)
/*     */   {
/* 209 */     this.sum = sum;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 226 */     return 0;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.COM.rejection.Rejection
 * JD-Core Version:    0.6.2
 */