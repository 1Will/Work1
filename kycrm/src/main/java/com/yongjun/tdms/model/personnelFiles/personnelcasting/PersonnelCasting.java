/*     */ package com.yongjun.tdms.model.personnelFiles.personnelcasting;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class PersonnelCasting extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private String fileNo;
/*     */   private String inst;
/*     */   private String dept;
/*     */   private String duty;
/*     */   private String contractCode;
/*     */   private Date regularizedDate;
/*     */   private String mobile;
/*     */   private String telphone;
/*     */   private String status;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  43 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  49 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  55 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getContractCode()
/*     */   {
/*  61 */     return this.contractCode;
/*     */   }
/*     */ 
/*     */   public void setContractCode(String contractCode)
/*     */   {
/*  67 */     this.contractCode = contractCode;
/*     */   }
/*     */ 
/*     */   public String getDept()
/*     */   {
/*  73 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(String dept)
/*     */   {
/*  79 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public String getDuty()
/*     */   {
/*  85 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(String duty)
/*     */   {
/*  91 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public String getFileNo()
/*     */   {
/*  97 */     return this.fileNo;
/*     */   }
/*     */ 
/*     */   public void setFileNo(String fileNo)
/*     */   {
/* 103 */     this.fileNo = fileNo;
/*     */   }
/*     */ 
/*     */   public String getInst()
/*     */   {
/* 109 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(String inst)
/*     */   {
/* 115 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 121 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile)
/*     */   {
/* 127 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 133 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 139 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Date getRegularizedDate()
/*     */   {
/* 145 */     return this.regularizedDate;
/*     */   }
/*     */ 
/*     */   public void setRegularizedDate(Date regularizedDate)
/*     */   {
/* 151 */     this.regularizedDate = regularizedDate;
/*     */   }
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 157 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 163 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getTelphone()
/*     */   {
/* 169 */     return this.telphone;
/*     */   }
/*     */ 
/*     */   public void setTelphone(String telphone)
/*     */   {
/* 175 */     this.telphone = telphone;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.personnelcasting.PersonnelCasting
 * JD-Core Version:    0.6.2
 */