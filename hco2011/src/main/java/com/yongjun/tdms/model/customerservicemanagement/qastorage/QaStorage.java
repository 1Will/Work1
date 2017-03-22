/*     */ package com.yongjun.tdms.model.customerservicemanagement.qastorage;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ 
/*     */ public class QaStorage extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String applyProduct;
/*     */   private String versionNumber;
/*     */   private CodeValue type;
/*     */   private CodeValue state;
/*     */   private CodeValue severityDegree;
/*     */   private String question;
/*     */   private String resolveProject;
/*     */   private String innerPrompt;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  74 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getApplyProduct()
/*     */   {
/*  82 */     return this.applyProduct;
/*     */   }
/*     */ 
/*     */   public void setApplyProduct(String applyProduct)
/*     */   {
/*  89 */     this.applyProduct = applyProduct;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  97 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 104 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getInnerPrompt()
/*     */   {
/* 112 */     return this.innerPrompt;
/*     */   }
/*     */ 
/*     */   public void setInnerPrompt(String innerPrompt)
/*     */   {
/* 119 */     this.innerPrompt = innerPrompt;
/*     */   }
/*     */ 
/*     */   public String getQuestion()
/*     */   {
/* 127 */     return this.question;
/*     */   }
/*     */ 
/*     */   public void setQuestion(String question)
/*     */   {
/* 134 */     this.question = question;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 142 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 149 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public String getResolveProject()
/*     */   {
/* 157 */     return this.resolveProject;
/*     */   }
/*     */ 
/*     */   public void setResolveProject(String resolveProject)
/*     */   {
/* 164 */     this.resolveProject = resolveProject;
/*     */   }
/*     */ 
/*     */   public CodeValue getSeverityDegree()
/*     */   {
/* 172 */     return this.severityDegree;
/*     */   }
/*     */ 
/*     */   public void setSeverityDegree(CodeValue severityDegree)
/*     */   {
/* 179 */     this.severityDegree = severityDegree;
/*     */   }
/*     */ 
/*     */   public CodeValue getState()
/*     */   {
/* 187 */     return this.state;
/*     */   }
/*     */ 
/*     */   public void setState(CodeValue state)
/*     */   {
/* 194 */     this.state = state;
/*     */   }
/*     */ 
/*     */   public CodeValue getType()
/*     */   {
/* 202 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type)
/*     */   {
/* 209 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getVersionNumber()
/*     */   {
/* 217 */     return this.versionNumber;
/*     */   }
/*     */ 
/*     */   public void setVersionNumber(String versionNumber)
/*     */   {
/* 224 */     this.versionNumber = versionNumber;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.customerservicemanagement.qastorage.QaStorage
 * JD-Core Version:    0.6.2
 */