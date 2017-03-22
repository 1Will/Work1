/*     */ package com.yongjun.tdms.model.workspace.ontheroadBill;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class OnTheRoadBill extends BaseInfoEntity
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
/*     */   private String failReason;
/*     */ 
/*     */   public String getFailReason()
/*     */   {
/*  50 */     return this.failReason;
/*     */   }
/*     */ 
/*     */   public void setFailReason(String failReason)
/*     */   {
/*  59 */     this.failReason = failReason;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getApplyPerson()
/*     */   {
/*  64 */     return this.applyPerson;
/*     */   }
/*     */ 
/*     */   public void setApplyPerson(PersonnelFiles applyPerson)
/*     */   {
/*  69 */     this.applyPerson = applyPerson;
/*     */   }
/*     */ 
/*     */   public String getBetreffzeile()
/*     */   {
/*  74 */     return this.betreffzeile;
/*     */   }
/*     */ 
/*     */   public void setBetreffzeile(String betreffzeile)
/*     */   {
/*  79 */     this.betreffzeile = betreffzeile;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  84 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  89 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/*  94 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  99 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/* 104 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/* 109 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 114 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 119 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Date getStartTime() {
/* 123 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 127 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus() {
/* 131 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status) {
/* 135 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 156 */     return 0;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill
 * JD-Core Version:    0.6.2
 */