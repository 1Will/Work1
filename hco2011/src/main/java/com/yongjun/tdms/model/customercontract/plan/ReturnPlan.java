/*     */ package com.yongjun.tdms.model.customercontract.plan;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ReturnPlan extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -390769290028684397L;
/*     */   private PersonnelFiles payee;
/*     */   private CustomerInfo customerInfo;
/*     */   private ContactArchives contactArchives;
/*     */   private String phone;
/*     */   private Date planDate;
/*     */   private ContractManagement contractManagement;
/*     */   private Date paytime;
/*     */   private CodeValue payment;
/*     */   private CodeValue batch;
/*  61 */   private Double sum = Double.valueOf(0.0D);
/*     */ 
/*  65 */   private Double factSum = Double.valueOf(0.0D);
/*     */   private String currency;
/*     */   private String isOrNot;
/*     */   private String notOrIs;
/*     */   private String billingOrNot;
/*     */ 
/*     */   public String getBillingOrNot()
/*     */   {
/*  92 */     return this.billingOrNot;
/*     */   }
/*     */ 
/*     */   public void setBillingOrNot(String billingOrNot)
/*     */   {
/* 100 */     this.billingOrNot = billingOrNot;
/*     */   }
/*     */ 
/*     */   public Double getFactSum()
/*     */   {
/* 107 */     return this.factSum;
/*     */   }
/*     */ 
/*     */   public void setFactSum(Double factSum)
/*     */   {
/* 115 */     this.factSum = factSum;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 122 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 130 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public Date getPlanDate()
/*     */   {
/* 137 */     return this.planDate;
/*     */   }
/*     */ 
/*     */   public void setPlanDate(Date planDate)
/*     */   {
/* 145 */     this.planDate = planDate;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 162 */     return 0;
/*     */   }
/*     */ 
/*     */   public CodeValue getBatch()
/*     */   {
/* 169 */     return this.batch;
/*     */   }
/*     */ 
/*     */   public ContactArchives getContactArchives()
/*     */   {
/* 176 */     return this.contactArchives;
/*     */   }
/*     */ 
/*     */   public void setContactArchives(ContactArchives contactArchives)
/*     */   {
/* 184 */     this.contactArchives = contactArchives;
/*     */   }
/*     */ 
/*     */   public ContractManagement getContractManagement()
/*     */   {
/* 191 */     return this.contractManagement;
/*     */   }
/*     */ 
/*     */   public void setContractManagement(ContractManagement contractManagement)
/*     */   {
/* 199 */     this.contractManagement = contractManagement;
/*     */   }
/*     */ 
/*     */   public String getCurrency()
/*     */   {
/* 207 */     return this.currency;
/*     */   }
/*     */ 
/*     */   public void setCurrency(String currency)
/*     */   {
/* 215 */     this.currency = currency;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 222 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 230 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public String getIsOrNot()
/*     */   {
/* 237 */     return this.isOrNot;
/*     */   }
/*     */ 
/*     */   public void setIsOrNot(String isOrNot)
/*     */   {
/* 245 */     this.isOrNot = isOrNot;
/*     */   }
/*     */ 
/*     */   public String getNotOrIs()
/*     */   {
/* 252 */     return this.notOrIs;
/*     */   }
/*     */ 
/*     */   public void setNotOrIs(String notOrIs)
/*     */   {
/* 260 */     this.notOrIs = notOrIs;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPayee()
/*     */   {
/* 267 */     return this.payee;
/*     */   }
/*     */ 
/*     */   public void setPayee(PersonnelFiles payee)
/*     */   {
/* 275 */     this.payee = payee;
/*     */   }
/*     */ 
/*     */   public CodeValue getPayment()
/*     */   {
/* 282 */     return this.payment;
/*     */   }
/*     */ 
/*     */   public void setPayment(CodeValue payment)
/*     */   {
/* 290 */     this.payment = payment;
/*     */   }
/*     */ 
/*     */   public Date getPaytime()
/*     */   {
/* 297 */     return this.paytime;
/*     */   }
/*     */ 
/*     */   public void setPaytime(Date paytime)
/*     */   {
/* 305 */     this.paytime = paytime;
/*     */   }
/*     */ 
/*     */   public Double getSum()
/*     */   {
/* 312 */     return this.sum;
/*     */   }
/*     */ 
/*     */   public void setSum(Double sum)
/*     */   {
/* 320 */     this.sum = sum;
/*     */   }
/*     */ 
/*     */   public void setBatch(CodeValue batch)
/*     */   {
/* 328 */     this.batch = batch;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.customercontract.plan.ReturnPlan
 * JD-Core Version:    0.6.2
 */