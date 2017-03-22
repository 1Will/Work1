/*     */ package com.yongjun.tdms.presentation.webwork.action.project;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;

import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditProjectInfoContractAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final ProjectInfoManager projectInfoManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
            private final ProjectInfoContractManager projectInfoContractManager;
/*     */   private final UserManager userManager;
/*     */   private String  projectInfoId;
			private String  customerInfoId;
            private ProjectInfoContract projectInfoContract;
/*     */
/*     */   private ContactArchives contactArchives;
/*     */ 
/*     */   public EditProjectInfoContractAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, UserManager userManager,ProjectInfoContractManager projectInfoContractManager)
/*     */   {
/*  40 */     this.codeValueManager = codeValueManager;
/*  41 */     this.projectInfoManager = projectInfoManager;
/*  42 */     this.personnelFilesManager = personnelFilesManager;
/*  43 */     this.customerInfoManager = customerInfoManager;
/*  44 */     this.contactArchivesManager = contactArchivesManager;
/*  45 */     this.userManager = userManager;
              this.projectInfoContractManager =projectInfoContractManager;
/*     */   }
/*     */   public void prepare() throws Exception {
/*  55 */       if (hasId("projectInfo.id")) {
/*  56 */         this.projectInfoId = getId("projectInfo.id")+"";
/*     */       } 
				if (hasId("customerInfo.id")) {
/*  56 */         this.customerInfoId = getId("customerInfo.id")+"";
/*     */       } 

				if (this.projectInfoContract == null)
/*  55 */       if (hasId("projectInfoContract.id")) {
/*  56 */         this.projectInfoContract = this.projectInfoContractManager.loadProjectInfoContract(getId("projectInfoContract.id"));
/*     */       } else {
/*  58 */         this.projectInfoContract = new ProjectInfoContract();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String save() {
/*  74 */     boolean isNew = this.projectInfoContract.isNew();
/*     */ 
/*  76 */     if (hasId("projectInfo.id")) {
/*  77 */       ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/*  78 */       this.projectInfoContract.setProjectInfo(projectInfo);
/*     */     }
/*     */ 
/*  82 */     if (hasId("businessType.id")) {
/*  83 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
              this.projectInfoContract.setBusinessType(cv);
/*     */     }
/*  86 */    
/*  91 */     if (hasId("contactArchives.id")) {
/*  92 */       this.contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
                  this.projectInfoContract.setContactArchives(contactArchives);
/*     */     }
				User u=this.userManager.getUser();
              if(isNew){
            	  this.projectInfoContract.setCreator(u.getName());
            	  this.projectInfoContract.setLastOperator(u.getName());
              }else{
            	  this.projectInfoContract.setLastOperator(u.getName());
              }
              this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
			  
/*     */ 
/*     */ 
			
/* 172 */     if (isNew) {
/* 173 */       addActionMessage(getText("projectInfoPer.add.success", Arrays.asList(new Object[] { this.projectInfoContract.getContactArchives().getName() })));
/*     */ 
/* 175 */       return "success";
/*     */     }
/* 177 */     addActionMessage(getText("projectInfoPer.edit.success", Arrays.asList(new Object[] { this.projectInfoContract.getContactArchives().getName() })));
/* 179 */     return "success";
/*     */   }
/*     */ 
/*     */ 
/*     */   public UserManager getUserManager() {
/* 202 */     return this.userManager;
/*     */   }
/*     */ 
		public List<CodeValue> getAllBusinessType()
/*     */   {
/*     */     try
/*     */     {
/* 211 */       List codes = new ArrayList();
/* 212 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("207"));
/* 213 */       if ((null != one) && (one.size() > 0)) {
/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 215 */         if ((null != list) && (list.size() > 0)) {
/* 216 */           codes.addAll(list);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 223 */       return codes;
/*     */     } catch (DaoException e) {
/* 225 */       e.printStackTrace();
/* 226 */       return new ArrayList();
/*     */     }
/*     */   }
		public String getProjectInfoId() {
			return projectInfoId;
		}
		public void setProjectInfoId(String projectInfoId) {
			this.projectInfoId = projectInfoId;
		}
		public String getCustomerInfoId() {
			return customerInfoId;
		}
		public void setCustomerInfoId(String customerInfoId) {
			this.customerInfoId = customerInfoId;
		}
		public ProjectInfoContract getProjectInfoContract() {
			return projectInfoContract;
		}
		public void setProjectInfoContract(ProjectInfoContract projectInfoContract) {
			this.projectInfoContract = projectInfoContract;
		}
		public ContactArchives getContactArchives() {
			return contactArchives;
		}
		public void setContactArchives(ContactArchives contactArchives) {
			this.contactArchives = contactArchives;
		}
		
		
		
/*     */ 
/*     */ 
/*     */ 
///*     */   public CodeValue getCustomerSteped()
///*     */   {
///* 265 */     return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
///*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.EditBackVisitAction
 * JD-Core Version:    0.6.2
 */