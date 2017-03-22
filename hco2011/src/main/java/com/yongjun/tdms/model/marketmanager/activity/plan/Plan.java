/*     */ package com.yongjun.tdms.model.marketmanager.activity.plan;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Plan extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -6803012866403613997L;
/*     */   private String code;
/*     */   private Date planTime;
/*     */   private PersonnelFiles persons;
/*     */   private String theme;
/*     */   private String description;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  72 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  79 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  86 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  93 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 100 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersons()
/*     */   {
/* 108 */     return this.persons;
/*     */   }
/*     */ 
/*     */   public void setPersons(PersonnelFiles persons)
/*     */   {
/* 115 */     this.persons = persons;
/*     */   }
/*     */ 
/*     */   public Date getPlanTime()
/*     */   {
/* 122 */     return this.planTime;
/*     */   }
/*     */ 
/*     */   public void setPlanTime(Date planTime)
/*     */   {
/* 129 */     this.planTime = planTime;
/*     */   }
/*     */ 
/*     */   public String getTheme()
/*     */   {
/* 136 */     return this.theme;
/*     */   }
/*     */ 
/*     */   public void setTheme(String theme)
/*     */   {
/* 143 */     this.theme = theme;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.marketmanager.activity.plan.Plan
 * JD-Core Version:    0.6.2
 */