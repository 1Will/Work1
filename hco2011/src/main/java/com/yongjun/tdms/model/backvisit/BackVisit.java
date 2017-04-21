/*     */ package com.yongjun.tdms.model.backvisit;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workReport.daily.Daily;

import java.util.Date;
/*     */ 
/*     */ public class BackVisit extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValue backVisitType;
/*     */   private String customerName;
/*     */   private CustomerInfo customerInfo;
/*     */   private String caName;
/*     */   private ContactArchives contactArchive;
/*     */   private Date backVisitDate;
/*  38 */   private Double costTime = Double.valueOf(0.0D);
/*     */   private CodeValue backVisitWay;
/*     */   private String backVisiter;
/*     */   private PersonnelFiles employee;
/*     */   private String continueBackVisit;
/*     */   private Date nextBackVisitDate;
/*     */   private String backVisitContent;
/*     */   private String feedback;
/*     */   private String attention;
/*     */   private String remarks;
/*  48 */   private Long backVisitSum = Long.valueOf(0L);
/*  49 */   private Long unconnect = Long.valueOf(0L);
/*     */   private String gradeChang;
/*     */   private CodeValue customerSteping;
/*     */   private CodeValue customerSteped;
			private CodeValue customerStating;
/*     */   private CodeValue customerStated;
			private String changStateReason;
/*     */   private String changReason;
            private String contactArchives;
            private String  employees;
            private String isPublic;
            private String isSaved;//存在并且等于0，，方可提交
            private ProjectInfo projectInfo;
            private String projectName;
            private Daily daily;
            
            public String getIsPublic() {
				return isPublic;
			}
			public void setIsPublic(String isPublic) {
				this.isPublic = isPublic;
			}
/*     */ 
/*     */   public CodeValue getCustomerSteped()
/*     */   {
/*  60 */     return this.customerSteped;
/*     */   }
/*     */ 
/*     */   public void setCustomerSteped(CodeValue customerSteped)
/*     */   {
/*  67 */     this.customerSteped = customerSteped;
/*     */   }
/*     */ 
/*     */   public CodeValue getCustomerSteping()
/*     */   {
/*  74 */     return this.customerSteping;
/*     */   }
/*     */ 
/*     */   public void setCustomerSteping(CodeValue customerSteping)
/*     */   {
/*  81 */     this.customerSteping = customerSteping;
/*     */   }
/*     */ 
/*     */   public Long getUnconnect()
/*     */   {
/*  89 */     return this.unconnect;
/*     */   }
/*     */ 
/*     */   public void setUnconnect(Long unconnect)
/*     */   {
/*  98 */     this.unconnect = unconnect;
/*     */   }
/*     */ 
/*     */   public Long getBackVisitSum()
/*     */   {
/* 106 */     return this.backVisitSum;
/*     */   }
/*     */ 
/*     */   public void setBackVisitSum(Long backVisitSum)
/*     */   {
/* 115 */     this.backVisitSum = backVisitSum;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 120 */     if (this == obj) return true;
/* 121 */     if (!(obj instanceof BackVisit)) return false;
/* 122 */     BackVisit bv = (BackVisit)obj;
/* 123 */     if (!bv.getId().equals(getId())) return false;
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 129 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public String getAttention()
/*     */   {
/* 136 */     return this.attention;
/*     */   }
/*     */ 
/*     */   public void setAttention(String attention)
/*     */   {
/* 143 */     this.attention = attention;
/*     */   }
/*     */ 
/*     */   public String getBackVisitContent()
/*     */   {
/* 150 */     return this.backVisitContent;
/*     */   }
/*     */ 
/*     */   public void setBackVisitContent(String backVisitContent)
/*     */   {
/* 157 */     this.backVisitContent = backVisitContent;
/*     */   }
/*     */ 
/*     */   public Date getBackVisitDate()
/*     */   {
/* 164 */     return this.backVisitDate;
/*     */   }
/*     */ 
/*     */   public void setBackVisitDate(Date backVisitDate)
/*     */   {
/* 171 */     this.backVisitDate = backVisitDate;
/*     */   }
/*     */ 
/*     */   public String getBackVisiter()
/*     */   {
/* 178 */     return this.backVisiter;
/*     */   }
/*     */ 
/*     */   public void setBackVisiter(String backVisiter)
/*     */   {
/* 185 */     this.backVisiter = backVisiter;
/*     */   }
/*     */ 
/*     */   public CodeValue getBackVisitWay()
/*     */   {
/* 192 */     return this.backVisitWay;
/*     */   }
/*     */ 
/*     */   public void setBackVisitWay(CodeValue backVisitWay)
/*     */   {
/* 199 */     this.backVisitWay = backVisitWay;
/*     */   }
/*     */ 
/*     */   public String getCaName()
/*     */   {
/* 206 */     return this.caName;
/*     */   }
/*     */ 
/*     */   public void setCaName(String caName)
/*     */   {
/* 213 */     this.caName = caName;
/*     */   }
/*     */ 
/*     */   public ContactArchives getContactArchive()
/*     */   {
/* 220 */     return this.contactArchive;
/*     */   }
/*     */ 
/*     */   public void setContactArchive(ContactArchives contactArchive)
/*     */   {
/* 227 */     this.contactArchive = contactArchive;
/*     */   }
/*     */ 
/*     */   public String getContinueBackVisit()
/*     */   {
/* 234 */     return this.continueBackVisit;
/*     */   }
/*     */ 
/*     */   public void setContinueBackVisit(String continueBackVisit)
/*     */   {
/* 241 */     this.continueBackVisit = continueBackVisit;
/*     */   }
/*     */ 
/*     */   public Double getCostTime()
/*     */   {
/* 248 */     return this.costTime;
/*     */   }
/*     */ 
/*     */   public void setCostTime(Double costTime)
/*     */   {
/* 255 */     this.costTime = costTime;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 262 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 269 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public String getCustomerName()
/*     */   {
/* 276 */     return this.customerName;
/*     */   }
/*     */ 
/*     */   public void setCustomerName(String customerName)
/*     */   {
/* 283 */     this.customerName = customerName;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getEmployee()
/*     */   {
/* 290 */     return this.employee;
/*     */   }
/*     */ 
/*     */   public void setEmployee(PersonnelFiles employee)
/*     */   {
/* 297 */     this.employee = employee;
/*     */   }
/*     */ 
/*     */   public String getFeedback()
/*     */   {
/* 304 */     return this.feedback;
/*     */   }
/*     */ 
/*     */   public void setFeedback(String feedback)
/*     */   {
/* 311 */     this.feedback = feedback;
/*     */   }
/*     */ 
/*     */   public Date getNextBackVisitDate()
/*     */   {
/* 318 */     return this.nextBackVisitDate;
/*     */   }
/*     */ 
/*     */   public void setNextBackVisitDate(Date nextBackVisitDate)
/*     */   {
/* 325 */     this.nextBackVisitDate = nextBackVisitDate;
/*     */   }
/*     */ 
/*     */   public String getRemarks()
/*     */   {
/* 332 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 339 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public CodeValue getBackVisitType()
/*     */   {
/* 346 */     return this.backVisitType;
/*     */   }
/*     */ 
/*     */   public void setBackVisitType(CodeValue backVisitType)
/*     */   {
/* 353 */     this.backVisitType = backVisitType;
/*     */   }
/*     */ 
/*     */   public String getGradeChang()
/*     */   {
/* 360 */     return this.gradeChang;
/*     */   }
/*     */ 
/*     */   public void setGradeChang(String gradeChang)
/*     */   {
/* 367 */     this.gradeChang = gradeChang;
/*     */   }
/*     */ 
/*     */   public String getChangReason()
/*     */   {
/* 374 */     return this.changReason;
/*     */   }
/*     */ 
/*     */   public void setChangReason(String changReason)
/*     */   {
/* 381 */     this.changReason = changReason;
/*     */   }
			public String getContactArchives() {
				return contactArchives;
			}
			public void setContactArchives(String contactArchives) {
				this.contactArchives = contactArchives;
			}
			public String getEmployees() {
				return employees;
			}
			public void setEmployees(String employees) {
				this.employees = employees;
			}
			public CodeValue getCustomerStating() {
				return customerStating;
			}
			public void setCustomerStating(CodeValue customerStating) {
				this.customerStating = customerStating;
			}
			public CodeValue getCustomerStated() {
				return customerStated;
			}
			public void setCustomerStated(CodeValue customerStated) {
				this.customerStated = customerStated;
			}
			public String getChangStateReason() {
				return changStateReason;
			}
			public void setChangStateReason(String changStateReason) {
				this.changStateReason = changStateReason;
			}
			public ProjectInfo getProjectInfo() {
				return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}
			public String getProjectName() {
				return projectName;
			}
			public void setProjectName(String projectName) {
				this.projectName = projectName;
			}
			public String getIsSaved() {
				return isSaved;
			}
			public void setIsSaved(String isSaved) {
				this.isSaved = isSaved;
			}
			public Daily getDaily() {
				return daily;
			}
			public void setDaily(Daily daily) {
				this.daily = daily;
			}
			



/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.backvisit.BackVisit
 * JD-Core Version:    0.6.2
 */