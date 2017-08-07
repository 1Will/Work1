/*     */ package com.yongjun.tdms.model.project.projectInfoCustomer;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ 
/*     */ public class ProjectInfoCustomer extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ProjectInfo projectInfo;//项目
			private CustomerInfo customerInfo;//项目客户
			private String outline;//主要说明
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  94 */     if (arg0 == this) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(arg0 instanceof ProjectInfoCustomer)) {
/*  98 */       return false;
/*     */     }
/*     */ 
/* 101 */     ProjectInfoCustomer contact = (ProjectInfoCustomer)arg0;
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
			public CustomerInfo getCustomerInfo() {
				return customerInfo;
			}
			public void setCustomerInfo(CustomerInfo customerInfo) {
				this.customerInfo = customerInfo;
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