/*     */ package com.yongjun.tdms.model.COM.VOC;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Voc extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 2930822699141574095L;
/*     */   private String code;
/*     */   private Date connectDate;
/*     */   private String title;
/*     */   private CustomerInfo customerInfo;
/*     */   private String supplier;
/*     */   private CodeValue importance;
/*     */   private CodeValue type;
/*     */   private PersonnelFiles salesman;
/*     */   private PersonnelFiles principal;
/*     */   private Double odds;
/*     */   private String content;
/*     */   private Double budqet;
/*     */   private String decisionMaker;
/*     */   private ContactArchives linkman;
/*     */   private String phone;
/*     */   private String email;
/*     */   private Date nextTime;
/*     */   private CodeValue status;
/*     */   private String resolvent;
/*     */   private String feedback;
/*     */   private CodeValue resource;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 122 */     return 0;
/*     */   }
/*     */ 
/*     */   public Double getBudqet()
/*     */   {
/* 129 */     return this.budqet;
/*     */   }
/*     */ 
/*     */   public void setBudqet(Double budqet)
/*     */   {
/* 137 */     this.budqet = budqet;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 146 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 154 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Date getConnectDate()
/*     */   {
/* 161 */     return this.connectDate;
/*     */   }
/*     */ 
/*     */   public void setConnectDate(Date connectDate)
/*     */   {
/* 169 */     this.connectDate = connectDate;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 176 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 184 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getDecisionMaker()
/*     */   {
/* 191 */     return this.decisionMaker;
/*     */   }
/*     */ 
/*     */   public void setDecisionMaker(String decisionMaker)
/*     */   {
/* 199 */     this.decisionMaker = decisionMaker;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 206 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 214 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getFeedback()
/*     */   {
/* 221 */     return this.feedback;
/*     */   }
/*     */ 
/*     */   public void setFeedback(String feedback)
/*     */   {
/* 229 */     this.feedback = feedback;
/*     */   }
/*     */ 
/*     */   public CodeValue getImportance()
/*     */   {
/* 236 */     return this.importance;
/*     */   }
/*     */ 
/*     */   public void setImportance(CodeValue importance)
/*     */   {
/* 244 */     this.importance = importance;
/*     */   }
/*     */ 
/*     */   public ContactArchives getLinkman()
/*     */   {
/* 251 */     return this.linkman;
/*     */   }
/*     */ 
/*     */   public void setLinkman(ContactArchives linkman)
/*     */   {
/* 259 */     this.linkman = linkman;
/*     */   }
/*     */ 
/*     */   public Date getNextTime()
/*     */   {
/* 266 */     return this.nextTime;
/*     */   }
/*     */ 
/*     */   public void setNextTime(Date nextTime)
/*     */   {
/* 274 */     this.nextTime = nextTime;
/*     */   }
/*     */ 
/*     */   public Double getOdds()
/*     */   {
/* 281 */     return this.odds;
/*     */   }
/*     */ 
/*     */   public void setOdds(Double odds)
/*     */   {
/* 289 */     this.odds = odds;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 296 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 304 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPrincipal()
/*     */   {
/* 311 */     return this.principal;
/*     */   }
/*     */ 
/*     */   public void setPrincipal(PersonnelFiles principal)
/*     */   {
/* 319 */     this.principal = principal;
/*     */   }
/*     */ 
/*     */   public String getResolvent()
/*     */   {
/* 326 */     return this.resolvent;
/*     */   }
/*     */ 
/*     */   public void setResolvent(String resolvent)
/*     */   {
/* 334 */     this.resolvent = resolvent;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSalesman()
/*     */   {
/* 341 */     return this.salesman;
/*     */   }
/*     */ 
/*     */   public void setSalesman(PersonnelFiles salesman)
/*     */   {
/* 349 */     this.salesman = salesman;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus()
/*     */   {
/* 356 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status)
/*     */   {
/* 364 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getSupplier()
/*     */   {
/* 371 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(String supplier)
/*     */   {
/* 379 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 386 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 394 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public CodeValue getType()
/*     */   {
/* 401 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type)
/*     */   {
/* 409 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public CodeValue getResource()
/*     */   {
/* 417 */     return this.resource;
/*     */   }
/*     */ 
/*     */   public void setResource(CodeValue resource)
/*     */   {
/* 426 */     this.resource = resource;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 434 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 443 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.COM.VOC.Voc
 * JD-Core Version:    0.6.2
 */