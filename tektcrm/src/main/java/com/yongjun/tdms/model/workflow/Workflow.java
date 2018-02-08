/*     */ package com.yongjun.tdms.model.workflow;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ 
/*     */ public class Workflow extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private Flow flow;
/*     */   private Department department;
/*     */   private CodeValue affectObject;
/*     */   private int openOrNot;
/*     */   private String remark;
/*     */ 
/*     */   public Workflow()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Workflow(String code, String name, Flow flow, CodeValue affectObject, int openOrNot, String remark)
/*     */   {
/*  81 */     this.code = code;
/*  82 */     this.name = name;
/*  83 */     this.flow = flow;
/*  84 */     this.affectObject = affectObject;
/*  85 */     this.openOrNot = openOrNot;
/*  86 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public CodeValue getAffectObject()
/*     */   {
/*  95 */     return this.affectObject;
/*     */   }
/*     */ 
/*     */   public void setAffectObject(CodeValue affectObject)
/*     */   {
/* 103 */     this.affectObject = affectObject;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 111 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 119 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Flow getFlow()
/*     */   {
/* 127 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public void setFlow(Flow flow)
/*     */   {
/* 135 */     this.flow = flow;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 143 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 151 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getOpenOrNot()
/*     */   {
/* 159 */     return this.openOrNot;
/*     */   }
/*     */ 
/*     */   public void setOpenOrNot(int openOrNot)
/*     */   {
/* 167 */     this.openOrNot = openOrNot;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 175 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 183 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 202 */     return 0;
/*     */   }
/*     */ 
/*     */   public String toString(Workflow workflow)
/*     */   {
/* 213 */     StringBuffer stringBuffer = new StringBuffer();
/* 214 */     stringBuffer.append("id=" + workflow.getId() + ", ");
/* 215 */     stringBuffer.append("code=" + workflow.getCode() + ", ");
/* 216 */     stringBuffer.append("name=" + workflow.getName() + ", ");
/* 217 */     stringBuffer.append("flow=" + workflow.getFlow().getName() + ", ");
/* 218 */     stringBuffer.append("affectObject=" + workflow.getAffectObject() + ", ");
/* 219 */     stringBuffer.append("openOrNot=" + workflow.getOpenOrNot() + ", ");
/* 220 */     stringBuffer.append("remark=" + workflow.getRemark() + ", ");
/* 221 */     stringBuffer.append("disabled=" + workflow.getDisabled() + ", ");
/* 222 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static long getSerialVersionUID()
/*     */   {
/* 229 */     return 1L;
/*     */   }
/*     */ 
/*     */   public Department getDepartment()
/*     */   {
/* 237 */     return this.department;
/*     */   }
/*     */ 
/*     */   public void setDepartment(Department department)
/*     */   {
/* 244 */     this.department = department;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workflow.Workflow
 * JD-Core Version:    0.6.2
 */