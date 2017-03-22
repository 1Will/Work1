/*     */ package com.yongjun.tdms.model.project.projectInfoProduct;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ 
/*     */ public class ProjectInfoProduct extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ProjectInfo projectInfo;//项目
			private Products products;//项目联系人
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  94 */     if (arg0 == this) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(arg0 instanceof ProjectInfoProduct)) {
/*  98 */       return false;
/*     */     }
/*     */ 
/* 101 */     ProjectInfoProduct contact = (ProjectInfoProduct)arg0;
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
			public Products getProducts() {
				return products;
			}
			public void setProducts(Products products) {
				this.products = products;
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