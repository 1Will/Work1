/*     */ package com.yongjun.tdms.model.workflow;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ 
/*     */ public class Point extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private int myNum;
/*     */   private PersonnelFiles personnelFiles;
/*     */   private Flow flow;
/*     */   private int openOrNot;
/*     */   private Long previousPointId;
/*     */   private Long rearPointId;
/*     */   private String remark;
/*     */ 
/*     */   public Point()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Point(String code, String name, PersonnelFiles personnelFiles, Flow flow, int openOrNot, Long previousPointId, Long rearPointId, String remark, int myNum)
/*     */   {
/*  93 */     this.code = code;
/*  94 */     this.name = name;
/*  95 */     this.personnelFiles = personnelFiles;
/*  96 */     this.flow = flow;
/*  97 */     this.openOrNot = openOrNot;
/*  98 */     this.previousPointId = previousPointId;
/*  99 */     this.rearPointId = rearPointId;
/* 100 */     this.remark = remark;
/* 101 */     this.myNum = myNum;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 109 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 117 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Flow getFlow()
/*     */   {
/* 125 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public void setFlow(Flow flow)
/*     */   {
/* 133 */     this.flow = flow;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 141 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 149 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getMyNum()
/*     */   {
/* 157 */     return this.myNum;
/*     */   }
/*     */ 
/*     */   public void setMyNum(int myNum)
/*     */   {
/* 165 */     this.myNum = myNum;
/*     */   }
/*     */ 
/*     */   public int getOpenOrNot()
/*     */   {
/* 173 */     return this.openOrNot;
/*     */   }
/*     */ 
/*     */   public void setOpenOrNot(int openOrNot)
/*     */   {
/* 181 */     this.openOrNot = openOrNot;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFiles()
/*     */   {
/* 189 */     return this.personnelFiles;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFiles(PersonnelFiles personnelFiles)
/*     */   {
/* 197 */     this.personnelFiles = personnelFiles;
/*     */   }
/*     */ 
/*     */   public Long getPreviousPointId()
/*     */   {
/* 205 */     return this.previousPointId;
/*     */   }
/*     */ 
/*     */   public void setPreviousPointId(Long previousPointId)
/*     */   {
/* 213 */     this.previousPointId = previousPointId;
/*     */   }
/*     */ 
/*     */   public Long getRearPointId()
/*     */   {
/* 221 */     return this.rearPointId;
/*     */   }
/*     */ 
/*     */   public void setRearPointId(Long rearPointId)
/*     */   {
/* 229 */     this.rearPointId = rearPointId;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 237 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 245 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 264 */     return 0;
/*     */   }
/*     */ 
/*     */   public String toString(Point point)
/*     */   {
/* 275 */     StringBuffer stringBuffer = new StringBuffer();
/* 276 */     stringBuffer.append("id=" + point.getId() + ", ");
/* 277 */     stringBuffer.append("code=" + point.getCode() + ", ");
/* 278 */     stringBuffer.append("name=" + point.getName() + ", ");
/* 279 */     stringBuffer.append("myNum=" + point.getMyNum() + ", ");
/* 280 */     stringBuffer.append("personnelFiles=" + point.getPersonnelFiles().getName() + ", ");
/* 281 */     stringBuffer.append("flow=" + point.getFlow().getName() + ", ");
/* 282 */     stringBuffer.append("previousPointId=" + point.getPreviousPointId() + ", ");
/* 283 */     stringBuffer.append("rearPointId=" + point.getRearPointId() + ", ");
/* 284 */     stringBuffer.append("openOrNot=" + point.getOpenOrNot() + ", ");
/* 285 */     stringBuffer.append("remark=" + point.getRemark() + ", ");
/* 286 */     stringBuffer.append("disabled=" + point.getDisabled() + ", ");
/* 287 */     return stringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workflow.Point
 * JD-Core Version:    0.6.2
 */