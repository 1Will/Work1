/*     */ package com.yongjun.tdms.model.base.duty;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ 
/*     */ public class Duty extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private Jobs jobName;
/*     */   private Department dept;
/*     */   private CodeValue perType;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  51 */     if (arg0 == this) {
/*  52 */       return true;
/*     */     }
/*  54 */     if (!(arg0 instanceof Duty)) {
/*  55 */       return false;
/*     */     }
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  63 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  70 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  77 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/*  84 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/*  91 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Jobs getJobName()
/*     */   {
/*  98 */     return this.jobName;
/*     */   }
/*     */ 
/*     */   public void setJobName(Jobs jobName)
/*     */   {
/* 105 */     this.jobName = jobName;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 112 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 119 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public CodeValue getPerType()
/*     */   {
/* 127 */     return this.perType;
/*     */   }
/*     */ 
/*     */   public void setPerType(CodeValue perType)
/*     */   {
/* 135 */     this.perType = perType;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.duty.Duty
 * JD-Core Version:    0.6.2
 */