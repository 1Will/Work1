/*     */ package com.yongjun.tdms.model.report.custcontact;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CustContactReport extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String custCode;
/*     */   private String custName;
/*     */   private String custType;
/*     */   private String country;
/*     */   private String province;
/*     */   private String city;
/*     */   private String industry;
/*     */   private String companyNature;
/*     */   private String content;
/*     */   private String attention;
/*     */   private String pioneer;
/*     */   private String servingPeople;
/*     */   private Date visitDate;
/*     */   private Date nextVisitDate;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  52 */     if (arg0 == this) {
/*  53 */       return true;
/*     */     }
/*  55 */     if (!(arg0 instanceof CustContactReport)) {
/*  56 */       return false;
/*     */     }
/*     */ 
/*  59 */     CustContactReport ccr = (CustContactReport)arg0;
/*     */ 
/*  61 */     if (!ccr.getId().equals(getId())) {
/*  62 */       return false;
/*     */     }
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  70 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getAttention() {
/*  74 */     return this.attention;
/*     */   }
/*     */ 
/*     */   public void setAttention(String attention) {
/*  78 */     this.attention = attention;
/*     */   }
/*     */ 
/*     */   public String getCity() {
/*  82 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(String city) {
/*  86 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getCompanyNature() {
/*  90 */     return this.companyNature;
/*     */   }
/*     */ 
/*     */   public void setCompanyNature(String companyNature) {
/*  94 */     this.companyNature = companyNature;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/*  98 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 102 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getCountry() {
/* 106 */     return this.country;
/*     */   }
/*     */ 
/*     */   public void setCountry(String country) {
/* 110 */     this.country = country;
/*     */   }
/*     */ 
/*     */   public String getCustCode() {
/* 114 */     return this.custCode;
/*     */   }
/*     */ 
/*     */   public void setCustCode(String custCode) {
/* 118 */     this.custCode = custCode;
/*     */   }
/*     */ 
/*     */   public String getCustName() {
/* 122 */     return this.custName;
/*     */   }
/*     */ 
/*     */   public void setCustName(String custName) {
/* 126 */     this.custName = custName;
/*     */   }
/*     */ 
/*     */   public String getCustType() {
/* 130 */     return this.custType;
/*     */   }
/*     */ 
/*     */   public void setCustType(String custType) {
/* 134 */     this.custType = custType;
/*     */   }
/*     */ 
/*     */   public String getIndustry() {
/* 138 */     return this.industry;
/*     */   }
/*     */ 
/*     */   public void setIndustry(String industry) {
/* 142 */     this.industry = industry;
/*     */   }
/*     */ 
/*     */   public Date getNextVisitDate() {
/* 146 */     return this.nextVisitDate;
/*     */   }
/*     */ 
/*     */   public void setNextVisitDate(Date nextVisitDate) {
/* 150 */     this.nextVisitDate = nextVisitDate;
/*     */   }
/*     */ 
/*     */   public String getPioneer() {
/* 154 */     return this.pioneer;
/*     */   }
/*     */ 
/*     */   public void setPioneer(String pioneer) {
/* 158 */     this.pioneer = pioneer;
/*     */   }
/*     */ 
/*     */   public String getProvince() {
/* 162 */     return this.province;
/*     */   }
/*     */ 
/*     */   public void setProvince(String province) {
/* 166 */     this.province = province;
/*     */   }
/*     */ 
/*     */   public String getServingPeople() {
/* 170 */     return this.servingPeople;
/*     */   }
/*     */ 
/*     */   public void setServingPeople(String servingPeople) {
/* 174 */     this.servingPeople = servingPeople;
/*     */   }
/*     */ 
/*     */   public Date getVisitDate() {
/* 178 */     return this.visitDate;
/*     */   }
/*     */ 
/*     */   public void setVisitDate(Date visitDate) {
/* 182 */     this.visitDate = visitDate;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.report.custcontact.CustContactReport
 * JD-Core Version:    0.6.2
 */