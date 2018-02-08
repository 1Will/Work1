/*     */ package com.yongjun.tdms.model.marketmanager.intelligence;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Intelligence extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private PersonnelFiles persons;
/*     */   private CustomerInfo customerInfo;
/*     */   private ContactArchives contactArchives;
/*     */   private String telephone;
/*     */   private String email;
/*     */   private Date analysisTime;
/*     */   private CodeValue important;
/*     */   private String theme;
/*     */   private String content;
/*     */   private String competProductTrend;
/*     */   private String competPriceTrend;
/*     */   private String competPromoteTrend;
/*     */   private String competAdTrend;
/*     */   private String competDistributeChannel;
/*     */   private String customerFeedback;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 126 */     return 0;
/*     */   }
/*     */ 
/*     */   public Date getAnalysisTime()
/*     */   {
/* 133 */     return this.analysisTime;
/*     */   }
/*     */ 
/*     */   public void setAnalysisTime(Date analysisTime)
/*     */   {
/* 140 */     this.analysisTime = analysisTime;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 147 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 154 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getCompetAdTrend()
/*     */   {
/* 161 */     return this.competAdTrend;
/*     */   }
/*     */ 
/*     */   public void setCompetAdTrend(String competAdTrend)
/*     */   {
/* 168 */     this.competAdTrend = competAdTrend;
/*     */   }
/*     */ 
/*     */   public String getCompetDistributeChannel()
/*     */   {
/* 175 */     return this.competDistributeChannel;
/*     */   }
/*     */ 
/*     */   public void setCompetDistributeChannel(String competDistributeChannel)
/*     */   {
/* 182 */     this.competDistributeChannel = competDistributeChannel;
/*     */   }
/*     */ 
/*     */   public String getCompetPriceTrend()
/*     */   {
/* 189 */     return this.competPriceTrend;
/*     */   }
/*     */ 
/*     */   public void setCompetPriceTrend(String competPriceTrend)
/*     */   {
/* 196 */     this.competPriceTrend = competPriceTrend;
/*     */   }
/*     */ 
/*     */   public String getCompetProductTrend()
/*     */   {
/* 203 */     return this.competProductTrend;
/*     */   }
/*     */ 
/*     */   public void setCompetProductTrend(String competProductTrend)
/*     */   {
/* 210 */     this.competProductTrend = competProductTrend;
/*     */   }
/*     */ 
/*     */   public String getCompetPromoteTrend()
/*     */   {
/* 217 */     return this.competPromoteTrend;
/*     */   }
/*     */ 
/*     */   public void setCompetPromoteTrend(String competPromoteTrend)
/*     */   {
/* 224 */     this.competPromoteTrend = competPromoteTrend;
/*     */   }
/*     */ 
/*     */   public ContactArchives getContactArchives()
/*     */   {
/* 231 */     return this.contactArchives;
/*     */   }
/*     */ 
/*     */   public void setContactArchives(ContactArchives contactArchives)
/*     */   {
/* 238 */     this.contactArchives = contactArchives;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 245 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 252 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getCustomerFeedback()
/*     */   {
/* 259 */     return this.customerFeedback;
/*     */   }
/*     */ 
/*     */   public void setCustomerFeedback(String customerFeedback)
/*     */   {
/* 266 */     this.customerFeedback = customerFeedback;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 273 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 280 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 287 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 294 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public CodeValue getImportant()
/*     */   {
/* 301 */     return this.important;
/*     */   }
/*     */ 
/*     */   public void setImportant(CodeValue important)
/*     */   {
/* 308 */     this.important = important;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersons()
/*     */   {
/* 315 */     return this.persons;
/*     */   }
/*     */ 
/*     */   public void setPersons(PersonnelFiles persons)
/*     */   {
/* 322 */     this.persons = persons;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 329 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 336 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public String getTelephone()
/*     */   {
/* 343 */     return this.telephone;
/*     */   }
/*     */ 
/*     */   public void setTelephone(String telephone)
/*     */   {
/* 350 */     this.telephone = telephone;
/*     */   }
/*     */ 
/*     */   public String getTheme()
/*     */   {
/* 357 */     return this.theme;
/*     */   }
/*     */ 
/*     */   public void setTheme(String theme)
/*     */   {
/* 364 */     this.theme = theme;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.marketmanager.intelligence.Intelligence
 * JD-Core Version:    0.6.2
 */