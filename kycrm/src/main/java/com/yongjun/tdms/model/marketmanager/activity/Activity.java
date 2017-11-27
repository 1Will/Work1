/*     */ package com.yongjun.tdms.model.marketmanager.activity;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Activity extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 8379511829424854648L;
/*     */   private String code;
/*     */   private String theme;
/*     */   private String customers;
/*     */   private String target;
/*     */   private Date beginTime;
/*     */   private Date endTime;
/*     */   private String place;
/*     */   private CodeValue activityType;
/*     */   private CodeValue status;
/*     */   private CodeValue progress;
/*     */   private CodeValue priority;
/*     */   private PersonnelFiles persons;
/*     */   private Double fee;
/*     */   private String content;
/*     */   private String summary;
/*     */   private String comment;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 121 */     return 0;
/*     */   }
/*     */ 
/*     */   public CodeValue getActivityType()
/*     */   {
/* 128 */     return this.activityType;
/*     */   }
/*     */ 
/*     */   public void setActivityType(CodeValue activityType)
/*     */   {
/* 135 */     this.activityType = activityType;
/*     */   }
/*     */ 
/*     */   public Date getBeginTime()
/*     */   {
/* 142 */     return this.beginTime;
/*     */   }
/*     */ 
/*     */   public void setBeginTime(Date beginTime)
/*     */   {
/* 149 */     this.beginTime = beginTime;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 156 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 163 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 170 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 177 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getCustomers()
/*     */   {
/* 184 */     return this.customers;
/*     */   }
/*     */ 
/*     */   public void setCustomers(String customers)
/*     */   {
/* 191 */     this.customers = customers;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 198 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 205 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Double getFee()
/*     */   {
/* 212 */     return this.fee;
/*     */   }
/*     */ 
/*     */   public void setFee(Double fee)
/*     */   {
/* 219 */     this.fee = fee;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/* 226 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 233 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersons()
/*     */   {
/* 240 */     return this.persons;
/*     */   }
/*     */ 
/*     */   public void setPersons(PersonnelFiles persons)
/*     */   {
/* 247 */     this.persons = persons;
/*     */   }
/*     */ 
/*     */   public String getPlace()
/*     */   {
/* 254 */     return this.place;
/*     */   }
/*     */ 
/*     */   public void setPlace(String place)
/*     */   {
/* 261 */     this.place = place;
/*     */   }
/*     */ 
/*     */   public CodeValue getPriority()
/*     */   {
/* 268 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(CodeValue priority)
/*     */   {
/* 275 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public CodeValue getProgress()
/*     */   {
/* 282 */     return this.progress;
/*     */   }
/*     */ 
/*     */   public void setProgress(CodeValue progress)
/*     */   {
/* 289 */     this.progress = progress;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 296 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 303 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus()
/*     */   {
/* 310 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status)
/*     */   {
/* 317 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getSummary()
/*     */   {
/* 324 */     return this.summary;
/*     */   }
/*     */ 
/*     */   public void setSummary(String summary)
/*     */   {
/* 331 */     this.summary = summary;
/*     */   }
/*     */ 
/*     */   public String getTheme()
/*     */   {
/* 338 */     return this.theme;
/*     */   }
/*     */ 
/*     */   public void setTheme(String theme)
/*     */   {
/* 345 */     this.theme = theme;
/*     */   }
/*     */ 
/*     */   public String getTarget()
/*     */   {
/* 352 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void setTarget(String target)
/*     */   {
/* 359 */     this.target = target;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.marketmanager.activity.Activity
 * JD-Core Version:    0.6.2
 */