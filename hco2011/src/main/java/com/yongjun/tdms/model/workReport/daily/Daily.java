/*     */ package com.yongjun.tdms.model.workReport.daily;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;

/*     */ import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/*     */ 
/*     */ public class Daily extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Date currentDate;
/*     */   private String weekDate;
/*     */   private User rapporteur;
/*     */   private PersonnelFiles person;
/*     */   private Institution inst;
/*     */   private Department dept;
/*     */   private Duty duty;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private String leaderIdea;
/*     */   private String workContext;
/*     */   private String questions;
/*     */   private String solutions;
/*     */   private String tomorrowPlan;
/*     */   private String comment;
			private String backVisitContext;
/*     */   private Weekly weekly;
			private String isSaved;//存在并且等于0，，方可提交
			public Set bvtList =new HashSet();
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  63 */     if (arg0 == this) {
/*  64 */       return true;
/*     */     }
/*  66 */     if (!(arg0 instanceof Daily)) {
/*  67 */       return false;
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  75 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/*  82 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/*  89 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public Date getCurrentDate()
/*     */   {
/*  96 */     return this.currentDate;
/*     */   }
/*     */ 
/*     */   public void setCurrentDate(Date currentDate)
/*     */   {
/* 103 */     this.currentDate = currentDate;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/* 110 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/* 117 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Duty getDuty()
/*     */   {
/* 124 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(Duty duty)
/*     */   {
/* 131 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 138 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 145 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Institution getInst()
/*     */   {
/* 152 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(Institution inst)
/*     */   {
/* 159 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */   public String getLeaderIdea()
/*     */   {
/* 166 */     return this.leaderIdea;
/*     */   }
/*     */ 
/*     */   public void setLeaderIdea(String leaderIdea)
/*     */   {
/* 173 */     this.leaderIdea = leaderIdea;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPerson()
/*     */   {
/* 180 */     return this.person;
/*     */   }
/*     */ 
/*     */   public void setPerson(PersonnelFiles person)
/*     */   {
/* 187 */     this.person = person;
/*     */   }
/*     */ 
/*     */   public String getQuestions()
/*     */   {
/* 194 */     return this.questions;
/*     */   }
/*     */ 
/*     */   public void setQuestions(String questions)
/*     */   {
/* 201 */     this.questions = questions;
/*     */   }
/*     */ 
/*     */   public User getRapporteur()
/*     */   {
/* 208 */     return this.rapporteur;
/*     */   }
/*     */ 
/*     */   public void setRapporteur(User rapporteur)
/*     */   {
/* 215 */     this.rapporteur = rapporteur;
/*     */   }
/*     */ 
/*     */   public String getSolutions()
/*     */   {
/* 222 */     return this.solutions;
/*     */   }
/*     */ 
/*     */   public void setSolutions(String solutions)
/*     */   {
/* 229 */     this.solutions = solutions;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 236 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 243 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public String getTomorrowPlan()
/*     */   {
/* 250 */     return this.tomorrowPlan;
/*     */   }
/*     */ 
/*     */   public void setTomorrowPlan(String tomorrowPlan)
/*     */   {
/* 257 */     this.tomorrowPlan = tomorrowPlan;
/*     */   }
/*     */ 
/*     */   public String getWeekDate()
/*     */   {
/* 264 */     return this.weekDate;
/*     */   }
/*     */ 
/*     */   public void setWeekDate(String weekDate)
/*     */   {
/* 271 */     this.weekDate = weekDate;
/*     */   }
/*     */ 
/*     */   public Weekly getWeekly()
/*     */   {
/* 278 */     return this.weekly;
/*     */   }
/*     */ 
/*     */   public void setWeekly(Weekly weekly)
/*     */   {
/* 285 */     this.weekly = weekly;
/*     */   }
/*     */ 
/*     */   public String getWorkContext()
/*     */   {
/* 292 */     return this.workContext;
/*     */   }
/*     */ 
/*     */   public void setWorkContext(String workContext)
/*     */   {
/* 299 */     this.workContext = workContext;
/*     */   }
			public Set getBvtList() {
				return bvtList;
			}
			public void setBvtList(Set bvtList) {
				this.bvtList = bvtList;
			}
			public String getBackVisitContext() {
				return backVisitContext;
			}
			public void setBackVisitContext(String backVisitContext) {
				this.backVisitContext = backVisitContext;
			}
			public String getIsSaved() {
				return isSaved;
			}
			public void setIsSaved(String isSaved) {
				this.isSaved = isSaved;
			}
			
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workReport.daily.Daily
 * JD-Core Version:    0.6.2
 */