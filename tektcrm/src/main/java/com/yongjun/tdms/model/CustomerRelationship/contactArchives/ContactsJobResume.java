/*     */ package com.yongjun.tdms.model.CustomerRelationship.contactArchives;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ContactsJobResume extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private String dept;
/*     */   private String job;
/*     */   private String inst;
/*     */   private String comment;
/*     */   private ContactArchives cr;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  49 */     if (arg0 == this) {
/*  50 */       return true;
/*     */     }
/*  52 */     if (!(arg0 instanceof ContactsJobResume)) {
/*  53 */       return false;
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  61 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/*  68 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/*  75 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public String getDept()
/*     */   {
/*  82 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(String dept)
/*     */   {
/*  89 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public String getJob()
/*     */   {
/*  96 */     return this.job;
/*     */   }
/*     */ 
/*     */   public void setJob(String job)
/*     */   {
/* 103 */     this.job = job;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 110 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 117 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public String getInst()
/*     */   {
/* 124 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(String inst)
/*     */   {
/* 131 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 152 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 159 */     this.startTime = startTime;
/*     */   }
			public ContactArchives getCr() {
				return cr;
			}
			public void setCr(ContactArchives cr) {
				this.cr = cr;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.WorkExperience
 * JD-Core Version:    0.6.2
 */