/*     */ package com.yongjun.tdms.model.personnelFiles.contractadministrator;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ContractAdministrator extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 790495290281529372L;
/*     */   private String personnelCode;
/*     */   private PersonnelFiles personnelName;
/*     */   private Department dept;
/*     */   private Duty duty;
/*     */   private String idNumber;
/*     */   private String contractCode;
/*     */   private CodeValue contractType;
/*     */   private Date signingDate;
/*     */   private Date contractEndDate;
/*     */   private String payAccount;
/*     */   private String socialInsuranceAccount;
/*     */   private String capitalReserveAccount;
/*     */   private Institution institustion;
/*     */   private PersonnelFiles principalName;
/*     */   private String expectationStipend;
/*     */   private String relationWay;
/*     */ 
/*     */   public String getExpectationStipend()
/*     */   {
/* 130 */     return this.expectationStipend;
/*     */   }
/*     */ 
/*     */   public void setExpectationStipend(String expectationStipend)
/*     */   {
/* 136 */     this.expectationStipend = expectationStipend;
/*     */   }
/*     */ 
/*     */   public Institution getInstitustion()
/*     */   {
/* 142 */     return this.institustion;
/*     */   }
/*     */ 
/*     */   public void setInstitustion(Institution institustion)
/*     */   {
/* 148 */     this.institustion = institustion;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPrincipalName()
/*     */   {
/* 154 */     return this.principalName;
/*     */   }
/*     */ 
/*     */   public void setPrincipalName(PersonnelFiles principalName)
/*     */   {
/* 160 */     this.principalName = principalName;
/*     */   }
/*     */ 
/*     */   public String getRelationWay()
/*     */   {
/* 166 */     return this.relationWay;
/*     */   }
/*     */ 
/*     */   public void setRelationWay(String relationWay)
/*     */   {
/* 172 */     this.relationWay = relationWay;
/*     */   }
/*     */ 
/*     */   public String getContractCode()
/*     */   {
/* 179 */     return this.contractCode;
/*     */   }
/*     */ 
/*     */   public void setContractCode(String contractCode)
/*     */   {
/* 187 */     this.contractCode = contractCode;
/*     */   }
/*     */ 
/*     */   public Date getContractEndDate()
/*     */   {
/* 194 */     return this.contractEndDate;
/*     */   }
/*     */ 
/*     */   public void setContractEndDate(Date contractEndDate)
/*     */   {
/* 202 */     this.contractEndDate = contractEndDate;
/*     */   }
/*     */ 
/*     */   public CodeValue getContractType()
/*     */   {
/* 210 */     return this.contractType;
/*     */   }
/*     */ 
/*     */   public void setContractType(CodeValue contractType)
/*     */   {
/* 218 */     this.contractType = contractType;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/* 225 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/* 233 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Duty getDuty()
/*     */   {
/* 240 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(Duty duty)
/*     */   {
/* 248 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public String getIdNumber()
/*     */   {
/* 255 */     return this.idNumber;
/*     */   }
/*     */ 
/*     */   public void setIdNumber(String idNumber)
/*     */   {
/* 263 */     this.idNumber = idNumber;
/*     */   }
/*     */ 
/*     */   public String getPersonnelCode()
/*     */   {
/* 270 */     return this.personnelCode;
/*     */   }
/*     */ 
/*     */   public void setPersonnelCode(String personnelCode)
/*     */   {
/* 278 */     this.personnelCode = personnelCode;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelName()
/*     */   {
/* 285 */     return this.personnelName;
/*     */   }
/*     */ 
/*     */   public void setPersonnelName(PersonnelFiles personnelName)
/*     */   {
/* 293 */     this.personnelName = personnelName;
/*     */   }
/*     */ 
/*     */   public Date getSigningDate()
/*     */   {
/* 300 */     return this.signingDate;
/*     */   }
/*     */ 
/*     */   public void setSigningDate(Date signingDate)
/*     */   {
/* 308 */     this.signingDate = signingDate;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 317 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 325 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCapitalReserveAccount()
/*     */   {
/* 331 */     return this.capitalReserveAccount;
/*     */   }
/*     */ 
/*     */   public void setCapitalReserveAccount(String capitalReserveAccount)
/*     */   {
/* 337 */     this.capitalReserveAccount = capitalReserveAccount;
/*     */   }
/*     */ 
/*     */   public String getPayAccount()
/*     */   {
/* 343 */     return this.payAccount;
/*     */   }
/*     */ 
/*     */   public void setPayAccount(String payAccount)
/*     */   {
/* 349 */     this.payAccount = payAccount;
/*     */   }
/*     */ 
/*     */   public String getSocialInsuranceAccount()
/*     */   {
/* 355 */     return this.socialInsuranceAccount;
/*     */   }
/*     */ 
/*     */   public void setSocialInsuranceAccount(String socialInsuranceAccount)
/*     */   {
/* 361 */     this.socialInsuranceAccount = socialInsuranceAccount;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator
 * JD-Core Version:    0.6.2
 */