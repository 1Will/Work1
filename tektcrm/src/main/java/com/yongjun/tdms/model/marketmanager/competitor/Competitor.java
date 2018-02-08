/*     */ package com.yongjun.tdms.model.marketmanager.competitor;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ 
/*     */ public class Competitor extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String companyName;
/*     */   private String companyLegal;
/*     */   private String telephone;
/*     */   private String fax;
/*     */   private String website;
/*     */   private String scale;
/*     */   private CodeValue industry;
/*     */   private CodeValue nature;
/*     */   private String business;
/*     */   private String strategy;
/*     */   private String targetMarket;
/*     */   private String trend;
/*     */   private CodeValue ability;
/*     */   private CustomerInfo customerInfo;
/*     */   private Products products;
/*     */   private Double price;
/*     */   private String address;
/*     */   private String resource;
/*     */   private String advantage;
/*     */   private String inferior;
/*     */   private String response;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 145 */     return 0;
/*     */   }
/*     */ 
/*     */   public CodeValue getAbility()
/*     */   {
/* 152 */     return this.ability;
/*     */   }
/*     */ 
/*     */   public void setAbility(CodeValue ability)
/*     */   {
/* 159 */     this.ability = ability;
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/* 166 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 173 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getAdvantage()
/*     */   {
/* 180 */     return this.advantage;
/*     */   }
/*     */ 
/*     */   public void setAdvantage(String advantage)
/*     */   {
/* 187 */     this.advantage = advantage;
/*     */   }
/*     */ 
/*     */   public String getBusiness()
/*     */   {
/* 194 */     return this.business;
/*     */   }
/*     */ 
/*     */   public void setBusiness(String business)
/*     */   {
/* 201 */     this.business = business;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 208 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 215 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getCompanyLegal()
/*     */   {
/* 222 */     return this.companyLegal;
/*     */   }
/*     */ 
/*     */   public void setCompanyLegal(String companyLegal)
/*     */   {
/* 229 */     this.companyLegal = companyLegal;
/*     */   }
/*     */ 
/*     */   public String getCompanyName()
/*     */   {
/* 236 */     return this.companyName;
/*     */   }
/*     */ 
/*     */   public void setCompanyName(String companyName)
/*     */   {
/* 243 */     this.companyName = companyName;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 250 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 257 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public String getFax()
/*     */   {
/* 264 */     return this.fax;
/*     */   }
/*     */ 
/*     */   public void setFax(String fax)
/*     */   {
/* 271 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */   public CodeValue getIndustry()
/*     */   {
/* 278 */     return this.industry;
/*     */   }
/*     */ 
/*     */   public void setIndustry(CodeValue industry)
/*     */   {
/* 285 */     this.industry = industry;
/*     */   }
/*     */ 
/*     */   public String getInferior()
/*     */   {
/* 292 */     return this.inferior;
/*     */   }
/*     */ 
/*     */   public void setInferior(String inferior)
/*     */   {
/* 299 */     this.inferior = inferior;
/*     */   }
/*     */ 
/*     */   public CodeValue getNature()
/*     */   {
/* 306 */     return this.nature;
/*     */   }
/*     */ 
/*     */   public void setNature(CodeValue nature)
/*     */   {
/* 313 */     this.nature = nature;
/*     */   }
/*     */ 
/*     */   public Double getPrice()
/*     */   {
/* 320 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(Double price)
/*     */   {
/* 327 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public Products getProducts()
/*     */   {
/* 334 */     return this.products;
/*     */   }
/*     */ 
/*     */   public void setProducts(Products products)
/*     */   {
/* 341 */     this.products = products;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 348 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 355 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public String getResource()
/*     */   {
/* 362 */     return this.resource;
/*     */   }
/*     */ 
/*     */   public void setResource(String resource)
/*     */   {
/* 369 */     this.resource = resource;
/*     */   }
/*     */ 
/*     */   public String getResponse()
/*     */   {
/* 376 */     return this.response;
/*     */   }
/*     */ 
/*     */   public void setResponse(String response)
/*     */   {
/* 383 */     this.response = response;
/*     */   }
/*     */ 
/*     */   public String getScale()
/*     */   {
/* 390 */     return this.scale;
/*     */   }
/*     */ 
/*     */   public void setScale(String scale)
/*     */   {
/* 397 */     this.scale = scale;
/*     */   }
/*     */ 
/*     */   public String getStrategy()
/*     */   {
/* 404 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public void setStrategy(String strategy)
/*     */   {
/* 411 */     this.strategy = strategy;
/*     */   }
/*     */ 
/*     */   public String getTargetMarket()
/*     */   {
/* 418 */     return this.targetMarket;
/*     */   }
/*     */ 
/*     */   public void setTargetMarket(String targetMarket)
/*     */   {
/* 425 */     this.targetMarket = targetMarket;
/*     */   }
/*     */ 
/*     */   public String getTelephone()
/*     */   {
/* 432 */     return this.telephone;
/*     */   }
/*     */ 
/*     */   public void setTelephone(String telephone)
/*     */   {
/* 439 */     this.telephone = telephone;
/*     */   }
/*     */ 
/*     */   public String getTrend()
/*     */   {
/* 446 */     return this.trend;
/*     */   }
/*     */ 
/*     */   public void setTrend(String trend)
/*     */   {
/* 453 */     this.trend = trend;
/*     */   }
/*     */ 
/*     */   public String getWebsite()
/*     */   {
/* 460 */     return this.website;
/*     */   }
/*     */ 
/*     */   public void setWebsite(String website)
/*     */   {
/* 467 */     this.website = website;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.marketmanager.competitor.Competitor
 * JD-Core Version:    0.6.2
 */