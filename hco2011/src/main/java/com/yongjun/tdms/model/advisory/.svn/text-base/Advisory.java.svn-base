/*     */ package com.yongjun.tdms.model.advisory;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Advisory extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 2852814739777217687L;
/*     */   private String name;
/*     */   private String shortName;
/*     */   private CodeValue customerType;
/*     */   private String connectPeople;
/*     */   private CodeValue industry;
/*     */   private CodeValue companyNature;
/*     */   private CodeValue statue;
/*     */   private String legalPerson;
/*     */   private String dept;
/*     */   private String duty;
/*     */   private String officePhone;
/*     */   private String mobile;
/*     */   private String fax;
/*     */   private String email;
/*     */   private String qq;
/*     */   private String customerServiceName;
/*     */   private Date advisoryTime;
/*  68 */   private boolean isNoBack = true;
/*     */   private String address;
/*     */   private String advisoryContent;
/*     */   private String comment;
/*     */   private Area country;
/*     */   private Area province;
/*     */   private Area city;
/*     */   private CodeValue infoSource;
/*     */   private PersonnelFiles customerServicePerson;
/*     */   private CustomerInfo customer;
/*  88 */   private boolean sex = false;
/*     */   private String effectDescribe;
/*     */   private String enterpriseSynopsis;
/*  94 */   private Integer personCount = Integer.valueOf(0);
/*     */ 
/*  96 */   private Double registeredCapital = Double.valueOf(0.0D);
/*     */   private String parlorDept;
/*     */   private Date setupTime;
/* 102 */   private Float customerInfoIntegrity = Float.valueOf(0.0F);
/*     */ 
/*     */   public Float getCustomerInfoIntegrity()
/*     */   {
/* 107 */     return this.customerInfoIntegrity;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfoIntegrity(Float customerInfoIntegrity)
/*     */   {
/* 114 */     this.customerInfoIntegrity = customerInfoIntegrity;
/*     */   }
/*     */ 
/*     */   public String getParlorDept()
/*     */   {
/* 121 */     return this.parlorDept;
/*     */   }
/*     */ 
/*     */   public void setParlorDept(String parlorDept)
/*     */   {
/* 128 */     this.parlorDept = parlorDept;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 135 */     if (this == o) {
/* 136 */       return true;
/*     */     }
/* 138 */     if (!(o instanceof Advisory)) {
/* 139 */       return false;
/*     */     }
/* 141 */     Advisory a = (Advisory)o;
/* 142 */     if (!a.getId().equals(getId())) {
/* 143 */       return false;
/*     */     }
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 150 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/* 157 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 164 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getAdvisoryContent()
/*     */   {
/* 171 */     return this.advisoryContent;
/*     */   }
/*     */ 
/*     */   public void setAdvisoryContent(String advisoryContent)
/*     */   {
/* 178 */     this.advisoryContent = advisoryContent;
/*     */   }
/*     */ 
/*     */   public Date getAdvisoryTime()
/*     */   {
/* 185 */     return this.advisoryTime;
/*     */   }
/*     */ 
/*     */   public void setAdvisoryTime(Date advisoryTime)
/*     */   {
/* 192 */     this.advisoryTime = advisoryTime;
/*     */   }
/*     */ 
/*     */   public Area getCity()
/*     */   {
/* 199 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(Area city)
/*     */   {
/* 206 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/* 213 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 220 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public CodeValue getCompanyNature()
/*     */   {
/* 227 */     return this.companyNature;
/*     */   }
/*     */ 
/*     */   public void setCompanyNature(CodeValue companyNature)
/*     */   {
/* 234 */     this.companyNature = companyNature;
/*     */   }
/*     */ 
/*     */   public String getConnectPeople()
/*     */   {
/* 241 */     return this.connectPeople;
/*     */   }
/*     */ 
/*     */   public void setConnectPeople(String connectPeople)
/*     */   {
/* 248 */     this.connectPeople = connectPeople;
/*     */   }
/*     */ 
/*     */   public Area getCountry()
/*     */   {
/* 255 */     return this.country;
/*     */   }
/*     */ 
/*     */   public void setCountry(Area country)
/*     */   {
/* 262 */     this.country = country;
/*     */   }
/*     */ 
/*     */   public String getCustomerServiceName()
/*     */   {
/* 269 */     return this.customerServiceName;
/*     */   }
/*     */ 
/*     */   public void setCustomerServiceName(String customerServiceName)
/*     */   {
/* 276 */     this.customerServiceName = customerServiceName;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getCustomerServicePerson() {
/* 280 */     return this.customerServicePerson;
/*     */   }
/*     */ 
/*     */   public void setCustomerServicePerson(PersonnelFiles customerServicePerson) {
/* 284 */     this.customerServicePerson = customerServicePerson;
/*     */   }
/*     */ 
/*     */   public void setNoBack(boolean isNoBack) {
/* 288 */     this.isNoBack = isNoBack;
/*     */   }
/*     */ 
/*     */   public CodeValue getCustomerType()
/*     */   {
/* 295 */     return this.customerType;
/*     */   }
/*     */ 
/*     */   public void setCustomerType(CodeValue customerType)
/*     */   {
/* 302 */     this.customerType = customerType;
/*     */   }
/*     */ 
/*     */   public String getDept()
/*     */   {
/* 309 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(String dept)
/*     */   {
/* 316 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public String getDuty()
/*     */   {
/* 323 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(String duty)
/*     */   {
/* 330 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 337 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 344 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getFax()
/*     */   {
/* 351 */     return this.fax;
/*     */   }
/*     */ 
/*     */   public void setFax(String fax)
/*     */   {
/* 358 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */   public CodeValue getIndustry()
/*     */   {
/* 365 */     return this.industry;
/*     */   }
/*     */ 
/*     */   public void setIndustry(CodeValue industry)
/*     */   {
/* 372 */     this.industry = industry;
/*     */   }
/*     */ 
/*     */   public CodeValue getInfoSource()
/*     */   {
/* 379 */     return this.infoSource;
/*     */   }
/*     */ 
/*     */   public void setInfoSource(CodeValue infoSource)
/*     */   {
/* 386 */     this.infoSource = infoSource;
/*     */   }
/*     */ 
/*     */   public boolean getIsNoBack()
/*     */   {
/* 393 */     return this.isNoBack;
/*     */   }
/*     */ 
/*     */   public void setIsNoBack(boolean isNoBack)
/*     */   {
/* 400 */     this.isNoBack = isNoBack;
/*     */   }
/*     */ 
/*     */   public String getLegalPerson()
/*     */   {
/* 407 */     return this.legalPerson;
/*     */   }
/*     */ 
/*     */   public void setLegalPerson(String legalPerson)
/*     */   {
/* 414 */     this.legalPerson = legalPerson;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 421 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile)
/*     */   {
/* 428 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 435 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 442 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getOfficePhone()
/*     */   {
/* 449 */     return this.officePhone;
/*     */   }
/*     */ 
/*     */   public void setOfficePhone(String officePhone)
/*     */   {
/* 456 */     this.officePhone = officePhone;
/*     */   }
/*     */ 
/*     */   public Area getProvince()
/*     */   {
/* 463 */     return this.province;
/*     */   }
/*     */ 
/*     */   public void setProvince(Area province)
/*     */   {
/* 470 */     this.province = province;
/*     */   }
/*     */ 
/*     */   public String getQq()
/*     */   {
/* 477 */     return this.qq;
/*     */   }
/*     */ 
/*     */   public void setQq(String qq)
/*     */   {
/* 484 */     this.qq = qq;
/*     */   }
/*     */ 
/*     */   public String getShortName()
/*     */   {
/* 491 */     return this.shortName;
/*     */   }
/*     */ 
/*     */   public void setShortName(String shortName)
/*     */   {
/* 498 */     this.shortName = shortName;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomer()
/*     */   {
/* 505 */     return this.customer;
/*     */   }
/*     */ 
/*     */   public void setCustomer(CustomerInfo customer)
/*     */   {
/* 512 */     this.customer = customer;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatue()
/*     */   {
/* 519 */     return this.statue;
/*     */   }
/*     */ 
/*     */   public void setStatue(CodeValue statue)
/*     */   {
/* 526 */     this.statue = statue;
/*     */   }
/*     */ 
/*     */   public boolean isSex()
/*     */   {
/* 534 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(boolean sex)
/*     */   {
/* 542 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   public String getEffectDescribe()
/*     */   {
/* 549 */     return this.effectDescribe;
/*     */   }
/*     */ 
/*     */   public void setEffectDescribe(String effectDescribe)
/*     */   {
/* 556 */     this.effectDescribe = effectDescribe;
/*     */   }
/*     */ 
/*     */   public String getEnterpriseSynopsis()
/*     */   {
/* 563 */     return this.enterpriseSynopsis;
/*     */   }
/*     */ 
/*     */   public void setEnterpriseSynopsis(String enterpriseSynopsis)
/*     */   {
/* 570 */     this.enterpriseSynopsis = enterpriseSynopsis;
/*     */   }
/*     */ 
/*     */   public Integer getPersonCount()
/*     */   {
/* 577 */     return this.personCount;
/*     */   }
/*     */ 
/*     */   public void setPersonCount(Integer personCount)
/*     */   {
/* 584 */     this.personCount = personCount;
/*     */   }
/*     */ 
/*     */   public Double getRegisteredCapital()
/*     */   {
/* 591 */     return this.registeredCapital;
/*     */   }
/*     */ 
/*     */   public void setRegisteredCapital(Double registeredCapital)
/*     */   {
/* 598 */     this.registeredCapital = registeredCapital;
/*     */   }
/*     */ 
/*     */   public Date getSetupTime()
/*     */   {
/* 605 */     return this.setupTime;
/*     */   }
/*     */ 
/*     */   public void setSetupTime(Date setupTime)
/*     */   {
/* 612 */     this.setupTime = setupTime;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.advisory.Advisory
 * JD-Core Version:    0.6.2
 */