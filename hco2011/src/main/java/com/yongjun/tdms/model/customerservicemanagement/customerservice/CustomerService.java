/*     */ package com.yongjun.tdms.model.customerservicemanagement.customerservice;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CustomerService extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String serviceTitle;
/*     */   private CustomerInfo customerInfo;
/*     */   private PersonnelFiles salesman;
/*     */   private ContactArchives linkman;
/*     */   private String telephone;
/*     */   private CodeValue serviceType;
/*     */   private CodeValue serviceWay;
/*     */   private float costTime;
/*     */   private double expense;
/*     */   private Date startTime;
/*     */   private CodeValue state;
/*     */   private String serviceContent;
/*     */   private String customerFeedback;
/*     */   private String qa;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 104 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 112 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 119 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public float getCostTime()
/*     */   {
/* 127 */     return this.costTime;
/*     */   }
/*     */ 
/*     */   public void setCostTime(float costTime)
/*     */   {
/* 134 */     this.costTime = costTime;
/*     */   }
/*     */ 
/*     */   public String getCustomerFeedback()
/*     */   {
/* 142 */     return this.customerFeedback;
/*     */   }
/*     */ 
/*     */   public void setCustomerFeedback(String customerFeedback)
/*     */   {
/* 149 */     this.customerFeedback = customerFeedback;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 157 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 164 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public double getExpense()
/*     */   {
/* 172 */     return this.expense;
/*     */   }
/*     */ 
/*     */   public void setExpense(double expense)
/*     */   {
/* 179 */     this.expense = expense;
/*     */   }
/*     */ 
/*     */   public ContactArchives getLinkman()
/*     */   {
/* 187 */     return this.linkman;
/*     */   }
/*     */ 
/*     */   public void setLinkman(ContactArchives linkman)
/*     */   {
/* 194 */     this.linkman = linkman;
/*     */   }
/*     */ 
/*     */   public String getQa()
/*     */   {
/* 202 */     return this.qa;
/*     */   }
/*     */ 
/*     */   public void setQa(String qa)
/*     */   {
/* 209 */     this.qa = qa;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 217 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 224 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSalesman()
/*     */   {
/* 232 */     return this.salesman;
/*     */   }
/*     */ 
/*     */   public void setSalesman(PersonnelFiles salesman)
/*     */   {
/* 239 */     this.salesman = salesman;
/*     */   }
/*     */ 
/*     */   public String getServiceContent()
/*     */   {
/* 247 */     return this.serviceContent;
/*     */   }
/*     */ 
/*     */   public void setServiceContent(String serviceContent)
/*     */   {
/* 254 */     this.serviceContent = serviceContent;
/*     */   }
/*     */ 
/*     */   public String getServiceTitle()
/*     */   {
/* 262 */     return this.serviceTitle;
/*     */   }
/*     */ 
/*     */   public void setServiceTitle(String serviceTitle)
/*     */   {
/* 269 */     this.serviceTitle = serviceTitle;
/*     */   }
/*     */ 
/*     */   public CodeValue getServiceType()
/*     */   {
/* 277 */     return this.serviceType;
/*     */   }
/*     */ 
/*     */   public void setServiceType(CodeValue serviceType)
/*     */   {
/* 284 */     this.serviceType = serviceType;
/*     */   }
/*     */ 
/*     */   public CodeValue getServiceWay()
/*     */   {
/* 292 */     return this.serviceWay;
/*     */   }
/*     */ 
/*     */   public void setServiceWay(CodeValue serviceWay)
/*     */   {
/* 299 */     this.serviceWay = serviceWay;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 307 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 314 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public CodeValue getState()
/*     */   {
/* 322 */     return this.state;
/*     */   }
/*     */ 
/*     */   public void setState(CodeValue state)
/*     */   {
/* 329 */     this.state = state;
/*     */   }
/*     */ 
/*     */   public String getTelephone()
/*     */   {
/* 337 */     return this.telephone;
/*     */   }
/*     */ 
/*     */   public void setTelephone(String telephone)
/*     */   {
/* 344 */     this.telephone = telephone;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.customerservicemanagement.customerservice.CustomerService
 * JD-Core Version:    0.6.2
 */