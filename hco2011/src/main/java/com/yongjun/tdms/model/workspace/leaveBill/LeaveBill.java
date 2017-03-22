/*     */ package com.yongjun.tdms.model.workspace.leaveBill;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class LeaveBill extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 8908956162303433991L;
/*     */   private String code;
/*     */   private Date createDate;
/*     */   private PersonnelFiles applyPerson;
/*     */   private Department dept;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private CodeValue status;
/*     */   private String betreffzeile;
/*  33 */   private Double manHour = Double.valueOf(0.0D);
/*     */   private CodeValue type;
/*     */   private String failReason;
/*     */ 
/*     */   public String getFailReason()
/*     */   {
/*  44 */     return this.failReason;
/*     */   }
/*     */ 
/*     */   public void setFailReason(String failReason)
/*     */   {
/*  53 */     this.failReason = failReason;
/*     */   }
/*     */ 
/*     */   public CodeValue getType() {
/*  57 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type) {
/*  61 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public Double getManHour() {
/*  65 */     return this.manHour;
/*     */   }
/*     */ 
/*     */   public void setManHour(Double manHour) {
/*  69 */     this.manHour = manHour;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getApplyPerson()
/*     */   {
/*  74 */     return this.applyPerson;
/*     */   }
/*     */ 
/*     */   public void setApplyPerson(PersonnelFiles applyPerson)
/*     */   {
/*  79 */     this.applyPerson = applyPerson;
/*     */   }
/*     */ 
/*     */   public String getBetreffzeile()
/*     */   {
/*  84 */     return this.betreffzeile;
/*     */   }
/*     */ 
/*     */   public void setBetreffzeile(String betreffzeile)
/*     */   {
/*  89 */     this.betreffzeile = betreffzeile;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  94 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  99 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 104 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 109 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/* 114 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/* 119 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 124 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 129 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Date getStartTime() {
/* 133 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 137 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus() {
/* 141 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status) {
/* 145 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 166 */     return 0;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workspace.leaveBill.LeaveBill
 * JD-Core Version:    0.6.2
 */