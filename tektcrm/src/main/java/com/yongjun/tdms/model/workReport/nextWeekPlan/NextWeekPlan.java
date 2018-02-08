/*     */ package com.yongjun.tdms.model.workReport.nextWeekPlan;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class NextWeekPlan extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Date currentDate;
/*     */   private String weekDate;
/*     */   private User rapporteur;
/*     */   private PersonnelFiles person;
/*     */   private String workPlan;
/*     */   private String comment;
/*     */   private Weekly weekly;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  52 */     if (arg0 == this) {
/*  53 */       return true;
/*     */     }
/*  55 */     if (!(arg0 instanceof NextWeekPlan)) {
/*  56 */       return false;
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  64 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/*  71 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/*  78 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public Date getCurrentDate()
/*     */   {
/*  85 */     return this.currentDate;
/*     */   }
/*     */ 
/*     */   public void setCurrentDate(Date currentDate)
/*     */   {
/*  92 */     this.currentDate = currentDate;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPerson()
/*     */   {
/*  99 */     return this.person;
/*     */   }
/*     */ 
/*     */   public void setPerson(PersonnelFiles person)
/*     */   {
/* 106 */     this.person = person;
/*     */   }
/*     */ 
/*     */   public User getRapporteur()
/*     */   {
/* 113 */     return this.rapporteur;
/*     */   }
/*     */ 
/*     */   public void setRapporteur(User rapporteur)
/*     */   {
/* 120 */     this.rapporteur = rapporteur;
/*     */   }
/*     */ 
/*     */   public String getWeekDate()
/*     */   {
/* 127 */     return this.weekDate;
/*     */   }
/*     */ 
/*     */   public void setWeekDate(String weekDate)
/*     */   {
/* 134 */     this.weekDate = weekDate;
/*     */   }
/*     */ 
/*     */   public Weekly getWeekly()
/*     */   {
/* 141 */     return this.weekly;
/*     */   }
/*     */ 
/*     */   public void setWeekly(Weekly weekly)
/*     */   {
/* 148 */     this.weekly = weekly;
/*     */   }
/*     */ 
/*     */   public String getWorkPlan()
/*     */   {
/* 155 */     return this.workPlan;
/*     */   }
/*     */ 
/*     */   public void setWorkPlan(String workPlan)
/*     */   {
/* 162 */     this.workPlan = workPlan;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workReport.nextWeekPlan.NextWeekPlan
 * JD-Core Version:    0.6.2
 */