/*     */ package com.yongjun.tdms.model.CustomerRelationship.customerProfiles;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.advisory.Advisory;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CustomerInfo extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private String abbreviations;
/*     */   private CodeValue customerType;
			private CodeValue classification;
/*     */   private CodeValue industry;
/*     */   private CodeValue companyNature;
/*     */   private String legalPerson;
/*     */   private Area country;
/*     */   private Area province;
/*     */   private Area city;
/*     */   private String keyContacter;
/*     */   private String telphone;
/*     */   private String mobilePhone;
/*     */   private String chuanzhen;
/*     */   private String qitalink;
/*     */   private String web;
/*     */   private String fax;
/*     */   private String email;
/*     */   private String qq;
/*     */   private Date setupTime;
/*  60 */   private Double registeredCapital = Double.valueOf(0.0D);
/*  61 */   private Integer personCount = Integer.valueOf(0);
/*     */   private String saleman;
/*     */   private PersonnelFiles salesman;
/*     */   private String postCode;
/*     */   private String address;
/*     */   private String businessScope;
/*     */   private String dept;
/*     */   private String duty;
/*     */   private String isOrNot;
/*     */   private CodeValue familiarityType;
/*     */   private CusAdditionalInfo additional;
/*     */   private CodeValue step;
			private CodeValue state;
/*  73 */   private Set<ContactArchives> contact = new HashSet();
/*  74 */   private Set<Advisory> advisory = new HashSet();
/*     */   private String effectDescribe;
/*     */   private String advisoryContent;
/*     */   private Date archiveTime;
/*     */   private String parlorDept;
/*     */   private CodeValue resource;
/*  88 */   private Float customerInfoIntegrity = Float.valueOf(0.0F);
			private Long unconnect = Long.valueOf(0L);
			private Long backVisitSum = Long.valueOf(0L);
			private Date nearestBackVisitDate;
/*     */   public String getChuanzhen() {
/*     */	   return chuanzhen;
/*     */  }
/*     */   public void setChuanzhen(String chuanzhen) {
/*     */	   this.chuanzhen = chuanzhen;
/*     */  }
/*     */   public String getQitalink() {
/*     */	   return qitalink;
/*     */  }
/*     */   public void setQitalink(String qitalink) {
/*     */	   this.qitalink = qitalink;
/*     */  }
/*     */   public String getWeb() {
/*     */	   return web;
/*     */  }
/*     */   public void setWeb(String web) {
/*     */	  this.web = web;
/*     */  }
/*     */ 
/*     */   public Float getCustomerInfoIntegrity() {
/*  91 */     if (null == this.customerInfoIntegrity) {
/*  92 */       return Float.valueOf(0.0F);
/*     */     }
/*  94 */     return this.customerInfoIntegrity;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfoIntegrity(Float customerInfoIntegrity) {
/*  98 */     this.customerInfoIntegrity = customerInfoIntegrity;
/*     */   }
/*     */ 
/*     */   public Set<Advisory> getAdvisory() {
/* 102 */     return this.advisory;
/*     */   }
/*     */ 
/*     */   public void setAdvisory(Set<Advisory> advisory) {
/* 106 */     this.advisory = advisory;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 111 */     if (arg0 == this) {
/* 112 */       return true;
/*     */     }
/* 114 */     if (!(arg0 instanceof CustomerInfo)) {
/* 115 */       return false;
/*     */     }
/*     */ 
/* 118 */     CustomerInfo customer = (CustomerInfo)arg0;
/*     */ 
/* 120 */     if (!customer.getId().equals(getId())) {
/* 121 */       return false;
/*     */     }
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 128 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getAbbreviations()
/*     */   {
/* 135 */     return this.abbreviations;
/*     */   }
/*     */ 
/*     */   public void setAbbreviations(String abbreviations)
/*     */   {
/* 142 */     this.abbreviations = abbreviations;
/*     */   }
/*     */ 
/*     */   public CusAdditionalInfo getAdditional() {
/* 146 */     return this.additional;
/*     */   }
/*     */ 
/*     */   public void setAdditional(CusAdditionalInfo additional) {
/* 150 */     this.additional = additional;
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
/*     */   public String getBusinessScope()
/*     */   {
/* 171 */     return this.businessScope;
/*     */   }
/*     */ 
/*     */   public void setBusinessScope(String businessScope)
/*     */   {
/* 178 */     this.businessScope = businessScope;
/*     */   }
/*     */ 
/*     */   public Area getCity()
/*     */   {
/* 185 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(Area city)
/*     */   {
/* 192 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 199 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 206 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public CodeValue getCompanyNature()
/*     */   {
/* 213 */     return this.companyNature;
/*     */   }
/*     */ 
/*     */   public void setCompanyNature(CodeValue companyNature)
/*     */   {
/* 220 */     this.companyNature = companyNature;
/*     */   }
/*     */ 
/*     */   public Set<ContactArchives> getContact()
/*     */   {
/* 227 */     return this.contact;
/*     */   }
/*     */ 
/*     */   public void setContact(Set<ContactArchives> contact)
/*     */   {
/* 234 */     this.contact = contact;
/*     */   }
/*     */ 
/*     */   public Area getCountry()
/*     */   {
/* 241 */     return this.country;
/*     */   }
/*     */ 
/*     */   public void setCountry(Area country)
/*     */   {
/* 248 */     this.country = country;
/*     */   }
/*     */ 
/*     */   public CodeValue getCustomerType()
/*     */   {
/* 255 */     return this.customerType;
/*     */   }
/*     */ 
/*     */   public void setCustomerType(CodeValue customerType)
/*     */   {
/* 262 */     this.customerType = customerType;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 269 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 276 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getFax()
/*     */   {
/* 283 */     return this.fax;
/*     */   }
/*     */ 
/*     */   public void setFax(String fax)
/*     */   {
/* 290 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */   public CodeValue getIndustry()
/*     */   {
/* 297 */     return this.industry;
/*     */   }
/*     */ 
/*     */   public void setIndustry(CodeValue industry)
/*     */   {
/* 304 */     this.industry = industry;
/*     */   }
/*     */ 
/*     */   public String getKeyContacter()
/*     */   {
/* 311 */     return this.keyContacter;
/*     */   }
/*     */ 
/*     */   public void setKeyContacter(String keyContacter)
/*     */   {
/* 318 */     this.keyContacter = keyContacter;
/*     */   }
/*     */ 
/*     */   public String getLegalPerson()
/*     */   {
/* 325 */     return this.legalPerson;
/*     */   }
/*     */ 
/*     */   public void setLegalPerson(String legalPerson)
/*     */   {
/* 332 */     this.legalPerson = legalPerson;
/*     */   }
/*     */ 
/*     */   public String getMobilePhone()
/*     */   {
/* 339 */     return this.mobilePhone;
/*     */   }
/*     */ 
/*     */   public void setMobilePhone(String mobilePhone)
/*     */   {
/* 346 */     this.mobilePhone = mobilePhone;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 353 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 360 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Integer getPersonCount()
/*     */   {
/* 367 */     return this.personCount;
/*     */   }
/*     */ 
/*     */   public void setPersonCount(Integer personCount)
/*     */   {
/* 374 */     this.personCount = personCount;
/*     */   }
/*     */ 
/*     */   public String getPostCode()
/*     */   {
/* 381 */     return this.postCode;
/*     */   }
/*     */ 
/*     */   public void setPostCode(String postCode)
/*     */   {
/* 388 */     this.postCode = postCode;
/*     */   }
/*     */ 
/*     */   public Area getProvince()
/*     */   {
/* 395 */     return this.province;
/*     */   }
/*     */ 
/*     */   public void setProvince(Area province)
/*     */   {
/* 402 */     this.province = province;
/*     */   }
/*     */ 
/*     */   public String getQq()
/*     */   {
/* 409 */     return this.qq;
/*     */   }
/*     */ 
/*     */   public void setQq(String qq)
/*     */   {
/* 416 */     this.qq = qq;
/*     */   }
/*     */ 
/*     */   public Double getRegisteredCapital()
/*     */   {
/* 423 */     return this.registeredCapital;
/*     */   }
/*     */ 
/*     */   public void setRegisteredCapital(Double registeredCapital)
/*     */   {
/* 430 */     this.registeredCapital = registeredCapital;
/*     */   }
/*     */ 
/*     */   public String getSaleman()
/*     */   {
/* 437 */     return this.saleman;
/*     */   }
/*     */ 
/*     */   public void setSaleman(String saleman)
/*     */   {
/* 444 */     this.saleman = saleman;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSalesman() {
/* 448 */     return this.salesman;
/*     */   }
/*     */ 
/*     */   public void setSalesman(PersonnelFiles salesman) {
/* 452 */     this.salesman = salesman;
/*     */   }
/*     */ 
/*     */   public Date getSetupTime()
/*     */   {
/* 459 */     return this.setupTime;
/*     */   }
/*     */ 
/*     */   public void setSetupTime(Date setupTime)
/*     */   {
/* 466 */     this.setupTime = setupTime;
/*     */   }
/*     */ 
/*     */   public String getTelphone()
/*     */   {
/* 473 */     return this.telphone;
/*     */   }
/*     */ 
/*     */   public void setTelphone(String telphone)
/*     */   {
/* 480 */     this.telphone = telphone;
/*     */   }
/*     */ 
/*     */   public CodeValue getResource()
/*     */   {
/* 488 */     return this.resource;
/*     */   }
/*     */ 
/*     */   public void setResource(CodeValue resource)
/*     */   {
/* 497 */     this.resource = resource;
/*     */   }
/*     */ 
/*     */   public String getDept()
/*     */   {
/* 505 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(String dept)
/*     */   {
/* 514 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public String getDuty()
/*     */   {
/* 522 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(String duty)
/*     */   {
/* 531 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public CodeValue getFamiliarityType()
/*     */   {
/* 540 */     return this.familiarityType;
/*     */   }
/*     */ 
/*     */   public void setFamiliarityType(CodeValue familiarityType)
/*     */   {
/* 549 */     this.familiarityType = familiarityType;
/*     */   }
/*     */ 
/*     */   public String getIsOrNot()
/*     */   {
/* 557 */     return this.isOrNot;
/*     */   }
/*     */ 
/*     */   public void setIsOrNot(String isOrNot)
/*     */   {
/* 566 */     this.isOrNot = isOrNot;
/*     */   }
/*     */ 
/*     */   public CodeValue getStep()
/*     */   {
/* 574 */     return this.step;
/*     */   }
/*     */ 
/*     */   public void setStep(CodeValue step)
/*     */   {
/* 583 */     this.step = step;
/*     */   }
/*     */ 
/*     */   public String getAdvisoryContent()
/*     */   {
/* 590 */     return this.advisoryContent;
/*     */   }
/*     */ 
/*     */   public void setAdvisoryContent(String advisoryContent)
/*     */   {
/* 597 */     this.advisoryContent = advisoryContent;
/*     */   }
/*     */ 
/*     */   public String getParlorDept()
/*     */   {
/* 605 */     return this.parlorDept;
/*     */   }
/*     */ 
/*     */   public void setParlorDept(String parlorDept)
/*     */   {
/* 612 */     this.parlorDept = parlorDept;
/*     */   }
/*     */ 
/*     */   public Date getArchiveTime()
/*     */   {
/* 619 */     return this.archiveTime;
/*     */   }
/*     */ 
/*     */   public void setArchiveTime(Date archiveTime)
/*     */   {
/* 626 */     this.archiveTime = archiveTime;
/*     */   }
/*     */ 
/*     */   public String getEffectDescribe()
/*     */   {
/* 633 */     return this.effectDescribe;
/*     */   }
/*     */ 
/*     */   public void setEffectDescribe(String effectDescribe)
/*     */   {
/* 640 */     this.effectDescribe = effectDescribe;
/*     */   }
			public CodeValue getState() {
				return state;
			}
			public void setState(CodeValue state) {
				this.state = state;
			}
/*     */   public Long getUnconnect()
/*     */   {
/*  89 */     return this.unconnect;
/*     */   }
/*     */ 
/*     */   public void setUnconnect(Long unconnect)
/*     */   {
/*  98 */     this.unconnect = unconnect;
/*     */   }
/*     */   public Long getBackVisitSum()
/*     */   {
/* 106 */     return this.backVisitSum;
/*     */   }
/*     */ 
/*     */   public void setBackVisitSum(Long backVisitSum)
/*     */   {
/* 115 */     this.backVisitSum = backVisitSum;
/*     */   }
/*     */
			public Date getNearestBackVisitDate() {
				return nearestBackVisitDate;
			}
			public void setNearestBackVisitDate(Date nearestBackVisitDate) {
				this.nearestBackVisitDate = nearestBackVisitDate;
			}
			public CodeValue getClassification() {
				return classification;
			}
			public void setClassification(CodeValue classification) {
				this.classification = classification;
			} 
			

/*     */ }


/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo
 * JD-Core Version:    0.6.2
 */