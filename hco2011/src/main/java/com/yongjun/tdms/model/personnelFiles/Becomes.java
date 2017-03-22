/*     */ package com.yongjun.tdms.model.personnelFiles;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Becomes extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -6073430202935104958L;
/*     */   private String code;
/*     */   private String name;
/*     */   private String personnelCode;
/*     */   private Institution inst;
/*     */   private Department dept;
/*     */   private Duty duty;
/*     */   private String contractCode;
/*     */   private Date becomesDate;
/*     */   private String mobilephone;
/*     */   private String telephone;
/*     */   private CodeValue status;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  83 */     return 0;
/*     */   }
/*     */ 
/*     */   public Date getBecomesDate()
/*     */   {
/*  91 */     return this.becomesDate;
/*     */   }
/*     */ 
/*     */   public void setBecomesDate(Date becomesDate)
/*     */   {
/* 100 */     this.becomesDate = becomesDate;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 108 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 117 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getContractCode()
/*     */   {
/* 125 */     return this.contractCode;
/*     */   }
/*     */ 
/*     */   public void setContractCode(String contractCode)
/*     */   {
/* 134 */     this.contractCode = contractCode;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/* 142 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/* 151 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Duty getDuty()
/*     */   {
/* 159 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(Duty duty)
/*     */   {
/* 168 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public Institution getInst()
/*     */   {
/* 176 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(Institution inst)
/*     */   {
/* 185 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */   public String getMobilephone()
/*     */   {
/* 193 */     return this.mobilephone;
/*     */   }
/*     */ 
/*     */   public void setMobilephone(String mobilephone)
/*     */   {
/* 202 */     this.mobilephone = mobilephone;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 210 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 219 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getPersonnelCode()
/*     */   {
/* 227 */     return this.personnelCode;
/*     */   }
/*     */ 
/*     */   public void setPersonnelCode(String personnelCode)
/*     */   {
/* 236 */     this.personnelCode = personnelCode;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus()
/*     */   {
/* 244 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status)
/*     */   {
/* 253 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getTelephone()
/*     */   {
/* 261 */     return this.telephone;
/*     */   }
/*     */ 
/*     */   public void setTelephone(String telephone)
/*     */   {
/* 270 */     this.telephone = telephone;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.Becomes
 * JD-Core Version:    0.6.2
 */