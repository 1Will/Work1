/*     */ package com.yongjun.tdms.model.COM.co;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Co extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 7995259807854733449L;
/*     */   private String code;
/*     */   private CustomerInfo customerInfo;
/*     */   private CodeValue type;
/*     */   private Products products;
/*     */   private Date orderTime;
/*     */   private Date deliveryTime;
/*     */   private CodeValue deliveryWay;
/*     */   private ContactArchives linkman;
/*     */   private String phone;
/*     */   private PersonnelFiles saleman;
/*     */   private String address;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  89 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/*  97 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 106 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 114 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 123 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Date getDeliveryTime()
/*     */   {
/* 131 */     return this.deliveryTime;
/*     */   }
/*     */ 
/*     */   public void setDeliveryTime(Date deliveryTime)
/*     */   {
/* 140 */     this.deliveryTime = deliveryTime;
/*     */   }
/*     */ 
/*     */   public CodeValue getDeliveryWay()
/*     */   {
/* 148 */     return this.deliveryWay;
/*     */   }
/*     */ 
/*     */   public void setDeliveryWay(CodeValue deliveryWay)
/*     */   {
/* 157 */     this.deliveryWay = deliveryWay;
/*     */   }
/*     */ 
/*     */   public ContactArchives getLinkman()
/*     */   {
/* 165 */     return this.linkman;
/*     */   }
/*     */ 
/*     */   public void setLinkman(ContactArchives linkman)
/*     */   {
/* 174 */     this.linkman = linkman;
/*     */   }
/*     */ 
/*     */   public Date getOrderTime()
/*     */   {
/* 182 */     return this.orderTime;
/*     */   }
/*     */ 
/*     */   public void setOrderTime(Date orderTime)
/*     */   {
/* 191 */     this.orderTime = orderTime;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 199 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 208 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public Products getProducts()
/*     */   {
/* 216 */     return this.products;
/*     */   }
/*     */ 
/*     */   public void setProducts(Products products)
/*     */   {
/* 225 */     this.products = products;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 233 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 242 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSaleman()
/*     */   {
/* 250 */     return this.saleman;
/*     */   }
/*     */ 
/*     */   public void setSaleman(PersonnelFiles saleman)
/*     */   {
/* 259 */     this.saleman = saleman;
/*     */   }
/*     */ 
/*     */   public CodeValue getType()
/*     */   {
/* 267 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type)
/*     */   {
/* 276 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 284 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 293 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.COM.co.Co
 * JD-Core Version:    0.6.2
 */