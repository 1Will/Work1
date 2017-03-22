/*     */ package com.yongjun.tdms.model.project.projectInfoPlan;
/*     */ 
/*     */ import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ 
/*     */ public class ProjectInfoPlan extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ProjectInfo projectInfo;//项目
            private String name;//任务名称
            private Date startDate;//预计开始时间
            private Date endDate;//预计结束时间
			private PersonnelFiles personnelFiles;//责任人
			private String assist  ;//协助者
			private String outline;//主要说明
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  94 */     if (arg0 == this) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(arg0 instanceof ProjectInfoPlan)) {
/*  98 */       return false;
/*     */     }
/*     */ 
/* 101 */     ProjectInfoPlan contact = (ProjectInfoPlan)arg0;
/*     */ 
/* 103 */     if (!contact.getId().equals(getId())) {
/* 104 */       return false;
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 111 */     return 0;
/*     */   }
/*     */
			public ProjectInfo getProjectInfo() {
				return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}
			public String getOutline() {
				return outline;
			}
			public void setOutline(String outline) {
				this.outline = outline;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public Date getStartDate() {
				return startDate;
			}
			public void setStartDate(Date startDate) {
				this.startDate = startDate;
			}
			public Date getEndDate() {
				return endDate;
			}
			public void setEndDate(Date endDate) {
				this.endDate = endDate;
			}
			public PersonnelFiles getPersonnelFiles() {
				return personnelFiles;
			}
			public void setPersonnelFiles(PersonnelFiles personnelFiles) {
				this.personnelFiles = personnelFiles;
			}
			public String getAssist() {
				return assist;
			}
			public void setAssist(String assist) {
				this.assist = assist;
			}
			
			
			
			
			

/*     */  
/*     */ 
/*     */  
			
			
            
/*     */ 
/*     */   
			

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives
 * JD-Core Version:    0.6.2
 */