/*     */ package com.yongjun.tdms.model.workspace.workingcycle;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class WorkingCycle extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 4196133295472333647L;
/*     */   private Date upStartTime;
/*     */   private Date upEndTime;
/*     */   private Date downStartTime;
/*     */   private Date downEndTime;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  50 */     return 0;
/*     */   }
/*     */ 
/*     */   public Date getDownEndTime()
/*     */   {
/*  57 */     return this.downEndTime;
/*     */   }
/*     */ 
/*     */   public void setDownEndTime(Date downEndTime)
/*     */   {
/*  65 */     this.downEndTime = downEndTime;
/*     */   }
/*     */ 
/*     */   public Date getDownStartTime()
/*     */   {
/*  72 */     return this.downStartTime;
/*     */   }
/*     */ 
/*     */   public void setDownStartTime(Date downStartTime)
/*     */   {
/*  80 */     this.downStartTime = downStartTime;
/*     */   }
/*     */ 
/*     */   public Date getUpEndTime()
/*     */   {
/*  87 */     return this.upEndTime;
/*     */   }
/*     */ 
/*     */   public void setUpEndTime(Date upEndTime)
/*     */   {
/*  95 */     this.upEndTime = upEndTime;
/*     */   }
/*     */ 
/*     */   public Date getUpStartTime()
/*     */   {
/* 102 */     return this.upStartTime;
/*     */   }
/*     */ 
/*     */   public void setUpStartTime(Date upStartTime)
/*     */   {
/* 110 */     this.upStartTime = upStartTime;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workspace.workingcycle.WorkingCycle
 * JD-Core Version:    0.6.2
 */