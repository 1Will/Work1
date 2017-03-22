/*     */ package com.yongjun.tdms.model.project.projectInfoContract;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ 
/*     */ public class ProjectInfoContract extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ProjectInfo projectInfo;//项目
			private ContactArchives contactArchives;//项目联系人
			private String outline;//主要说明
/*     */   private CodeValue businessType;//业务属性
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  94 */     if (arg0 == this) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(arg0 instanceof ProjectInfoContract)) {
/*  98 */       return false;
/*     */     }
/*     */ 
/* 101 */     ProjectInfoContract contact = (ProjectInfoContract)arg0;
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
			public ContactArchives getContactArchives() {
				return contactArchives;
			}
			public void setContactArchives(ContactArchives contactArchives) {
				this.contactArchives = contactArchives;
			}
			public CodeValue getBusinessType() {
				return businessType;
			}
			public void setBusinessType(CodeValue businessType) {
				this.businessType = businessType;
			}
			public String getOutline() {
				return outline;
			}
			public void setOutline(String outline) {
				this.outline = outline;
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