/*     */ package com.yongjun.tdms.model.CustomerRelationship.additionalInfo;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ 
/*     */ public class CusAdditionalInfo extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String licenseNumber;
/*     */   private String taxNumber;
/*     */   private Double turnover;
/*     */   private String bank;
/*     */   private String bankAccount;
/*     */   private String website;
/*     */   private String comment;
/*     */   private CustomerInfo ci;
            private  long middlManagementNum;//中层管理人数
            private  long collegeNum;//大专人数
            private  long undergraduateNum;//本科人数
            private  long researcherNum;//研究人员数
            private  long employeeInsurance;//职工参保数
            private  long masterNum;//研究生以上人数
            
            //上传图片
            private String logoPath;
            private String backgroundPath;
/*     */ 	
           
            public String getLogoPath() {
				return logoPath;
			}
			public void setLogoPath(String logoPath) {
				this.logoPath = logoPath;
			}
			public String getBackgroundPath() {
				return backgroundPath;
			}
			public void setBackgroundPath(String backgroundPath) {
				this.backgroundPath = backgroundPath;
			}
            

/*     */   public String getBank()
/*     */   {
/*  51 */     return this.bank;
/*     */   }
/*     */ 
/*     */   public void setBank(String bank)
/*     */   {
/*  58 */     this.bank = bank;
/*     */   }
/*     */ 
/*     */   public String getBankAccount()
/*     */   {
/*  65 */     return this.bankAccount;
/*     */   }
/*     */ 
/*     */   public void setBankAccount(String bankAccount)
/*     */   {
/*  72 */     this.bankAccount = bankAccount;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCi()
/*     */   {
/*  79 */     return this.ci;
/*     */   }
/*     */ 
/*     */   public void setCi(CustomerInfo ci)
/*     */   {
/*  86 */     this.ci = ci;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/*  93 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 100 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public String getLicenseNumber()
/*     */   {
/* 107 */     return this.licenseNumber;
/*     */   }
/*     */ 
/*     */   public void setLicenseNumber(String licenseNumber)
/*     */   {
/* 114 */     this.licenseNumber = licenseNumber;
/*     */   }
/*     */ 
/*     */   public String getTaxNumber()
/*     */   {
/* 121 */     return this.taxNumber;
/*     */   }
/*     */ 
/*     */   public void setTaxNumber(String taxNumber)
/*     */   {
/* 128 */     this.taxNumber = taxNumber;
/*     */   }
/*     */ 
/*     */   public Double getTurnover()
/*     */   {
/* 135 */     return this.turnover;
/*     */   }
/*     */ 
/*     */   public void setTurnover(Double turnover)
/*     */   {
/* 142 */     this.turnover = turnover;
/*     */   }
/*     */ 
/*     */   public String getWebsite()
/*     */   {
/* 149 */     return this.website;
/*     */   }
/*     */ 
/*     */   public void setWebsite(String website)
/*     */   {
/* 156 */     this.website = website;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 161 */     if (arg0 == this) {
/* 162 */       return true;
/*     */     }
/* 164 */     if (!(arg0 instanceof CusAdditionalInfo)) {
/* 165 */       return false;
/*     */     }
/*     */ 
/* 168 */     CusAdditionalInfo additional = (CusAdditionalInfo)arg0;
/*     */ 
/* 170 */     if (!additional.getId().equals(getId())) {
/* 171 */       return false;
/*     */     }
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 179 */     return 0;
/*     */   }
			public long getMiddlManagementNum() {
				return middlManagementNum;
			}
			public void setMiddlManagementNum(long middlManagementNum) {
				this.middlManagementNum = middlManagementNum;
			}
			public long getCollegeNum() {
				return collegeNum;
			}
			public void setCollegeNum(long collegeNum) {
				this.collegeNum = collegeNum;
			}
			public long getUndergraduateNum() {
				return undergraduateNum;
			}
			public void setUndergraduateNum(long undergraduateNum) {
				this.undergraduateNum = undergraduateNum;
			}
			public long getResearcherNum() {
				return researcherNum;
			}
			public void setResearcherNum(long researcherNum) {
				this.researcherNum = researcherNum;
			}
			public long getEmployeeInsurance() {
				return employeeInsurance;
			}
			public void setEmployeeInsurance(long employeeInsurance) {
				this.employeeInsurance = employeeInsurance;
			}
			public long getMasterNum() {
				return masterNum;
			}
			public void setMasterNum(long masterNum) {
				this.masterNum = masterNum;
			}
			
			
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo
 * JD-Core Version:    0.6.2
 */