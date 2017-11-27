/*     */ package com.yongjun.tdms.model.workflow;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Flow extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private Department department;
/*     */   private float allowTime;
/*     */   private Set<Point> pointSet;
/*     */   private int openOrNot;
/*     */   private String remark;
/*     */ 
/*     */   public Flow()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Flow(String code, String name, float allowTime, int openOrNot, String remark, Set<Point> pointSet, Department department)
/*     */   {
/*  83 */     this.code = code;
/*  84 */     this.name = name;
/*  85 */     this.allowTime = allowTime;
/*  86 */     this.openOrNot = openOrNot;
/*  87 */     this.remark = remark;
/*  88 */     this.pointSet = pointSet;
/*  89 */     this.department = department;
/*     */   }
/*     */ 
/*     */   public float getAllowTime()
/*     */   {
/*  97 */     return this.allowTime;
/*     */   }
/*     */ 
/*     */   public void setAllowTime(float allowTime)
/*     */   {
/* 105 */     this.allowTime = allowTime;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 113 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 121 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 129 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 137 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Department getDepartment()
/*     */   {
/* 145 */     return this.department;
/*     */   }
/*     */ 
/*     */   public void setDepartment(Department department)
/*     */   {
/* 153 */     this.department = department;
/*     */   }
/*     */ 
/*     */   public int getOpenOrNot()
/*     */   {
/* 161 */     return this.openOrNot;
/*     */   }
/*     */ 
/*     */   public void setOpenOrNot(int openOrNot)
/*     */   {
/* 169 */     this.openOrNot = openOrNot;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 177 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 185 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public Set<Point> getPointSet()
/*     */   {
/* 193 */     return this.pointSet;
/*     */   }
/*     */ 
/*     */   public void setPointSet(Set<Point> pointSet)
/*     */   {
/* 201 */     this.pointSet = pointSet;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 220 */     return 0;
/*     */   }
/*     */ 
/*     */   public String toString(Flow flow)
/*     */   {
/* 231 */     StringBuffer stringBuffer = new StringBuffer();
/* 232 */     stringBuffer.append("id=" + flow.getId() + ", ");
/* 233 */     stringBuffer.append("code=" + flow.getCode() + ", ");
/* 234 */     stringBuffer.append("name=" + flow.getName() + ", ");
/* 235 */     stringBuffer.append("department=" + flow.getDepartment().getName() + ", ");
/* 236 */     stringBuffer.append("allowTime=" + flow.getAllowTime() + ", ");
/* 237 */     stringBuffer.append("openOrNot=" + flow.getOpenOrNot() + ", ");
/* 238 */     stringBuffer.append("remark=" + flow.getRemark() + ", ");
/* 239 */     stringBuffer.append("disabled=" + flow.getDisabled());
/* 240 */     return stringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workflow.Flow
 * JD-Core Version:    0.6.2
 */