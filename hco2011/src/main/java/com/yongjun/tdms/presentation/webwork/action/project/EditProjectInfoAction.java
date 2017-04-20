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
/*     */ public class EditProjectInfoAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final ProjectInfoManager projectInfoManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
            private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
            private final ProjectInfoContractManager projectInfoContractManager;
/*     */   private final UserManager userManager;
/*     */   private ProjectInfo projectInfo;
            private ProjectInfoPersonnels projectInfoPersonnels;
            private String openFlag;
            private String notNewFlag;
/*     */
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public EditProjectInfoAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, UserManager userManager,ProjectInfoPersonnelsManager projectInfoPersonnelsManager,ProjectInfoContractManager projectInfoContractManager)
/*     */   {
/*  40 */     this.codeValueManager = codeValueManager;
/*  41 */     this.projectInfoManager = projectInfoManager;
/*  42 */     this.personnelFilesManager = personnelFilesManager;
/*  43 */     this.customerInfoManager = customerInfoManager;
/*  44 */     this.contactArchivesManager = contactArchivesManager;
/*  45 */     this.userManager = userManager;
              this.projectInfoPersonnelsManager =projectInfoPersonnelsManager;
              this.projectInfoContractManager=projectInfoContractManager;
/*     */   }
/*     */   public ProjectInfo getProjectInfo() {
/*  48 */     return this.projectInfo;
/*     */   }
/*     */   public void setProjectInfo(ProjectInfo projectInfo) {
/*  51 */     this.projectInfo = projectInfo;
/*     */   }
/*     */   public void prepare() throws Exception {
/*  54 */     if (this.projectInfo == null)
/*  55 */       if (hasId("projectInfo.id")) {
/*  56 */         this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/*     */       } else {
/*  58 */         this.projectInfo = new ProjectInfo();
/*  59 */         if (this.userManager.getUser().getCode() != null) {
/*  60 */           List pfs = this.personnelFilesManager.loadByKey("name", this.userManager.getUser().getName());
/*  61 */           if ((null != pfs) && (pfs.size() > 0))
/*     */           {
/*  64 */             this.personnelFiles = ((PersonnelFiles)pfs.get(0));
/*     */           }
/*     */         }
/*     */       }
				if(this.request.getParameter("openFlag")!=null){
					   this.openFlag =this.request.getParameter("openFlag");
				}
				if (request.getParameter("notNewFlag")!=null) {
					  this.notNewFlag=request.getParameter("notNewFlag");
				  }
/*     */   }
/*     */ 
/*     */   public String save() throws NumberFormatException, DaoException {
			this.projectInfoPersonnels =new ProjectInfoPersonnels();
/*  74 */     boolean isNew = this.projectInfo.isNew();
/*     */ 
/*  76 */     if (hasId("customer.id")) {
/*  77 */       CustomerInfo ci = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
/*  78 */       this.projectInfo.setCustomer(ci);
/*     */     }
/*     */ 
/*  82 */     if (hasId("stateId")) {
/*  83 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("stateId"));
              this.projectInfo.setState(cv);
/*     */     }
			
/*  86 */     if (hasId("contact.id")) {
/*  87 */       ContactArchives ca = this.contactArchivesManager.loadContactArchives(getId("contact.id"));
                 this.projectInfo.setContact(ca);
/*     */     }
				if(hasId("businessType.id")){
				CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
				this.projectInfo.setBusinessType(cv);
				}
/*  91 */     if (hasId("controller.id")) {
/*  92 */       this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("controller.id"));
                  this.projectInfo.setController(personnelFiles);
                  this.projectInfoPersonnels.setProPerson(personnelFiles);
/*     */     }
				User u=this.userManager.getUser();
              if(isNew){
            	  
//            	  CodeValue cv = this.codeValueManager.loadCodeValue(464l);//立项
            	  
            	  List one = this.codeValueManager.loadByKey("code", Long.valueOf("20101"));
					 if ((null != one) && (one.size() > 0)) {
						 CodeValue co =(CodeValue)one.get(0);
            	  this.projectInfo.setState(co);
					 }
            	  
            	  
            	  this.projectInfo.setCreator(u.getName());
            	  this.projectInfo.setLastOperator(u.getName());
            	  this.projectInfo.setCreateUser(u);
            	  
              }else{
            	  if (hasId("stateId")) {
            	  CodeValue cv = this.codeValueManager.loadCodeValue(getId("stateId"));
            	  this.projectInfo.setState(cv);
            	  }
            	  this.projectInfo.setLastOperator(u.getName());
              }
			  
/*     */ 
/*     */ 
             //保存项目
			this.projectInfoManager.storeProjectInfo(projectInfo);
			
			 ProjectInfoContract projectInfoContract =null;
			 //先判断项目是否绑定了最新的联系人和其业务属性
			 if(this.projectInfo.getContact()!=null&&this.projectInfo.getBusinessType()!=null){
				
					String[] arrayKey = {"projectInfo.id", "contactArchives.id"};
					Long [] arrayValue={projectInfo.getId(),projectInfo.getContact().getId()};
					//根据项目id和联系人id查询关系表中是否有记录， 有的话就修改，没有就增加
					List<ProjectInfoContract> list = this.projectInfoContractManager.loadByKeyArray(arrayKey, arrayValue);
					if(list!=null&&list.size()>0){
						
						projectInfoContract =list.get(0);
						projectInfoContract.setBusinessType(projectInfo.getBusinessType());
						projectInfoContract.setOutline(this.projectInfo.getConOutline());
						 this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
					}else {
						projectInfoContract = new ProjectInfoContract();
						projectInfoContract.setProjectInfo(this.projectInfo);
						projectInfoContract.setContactArchives(this.projectInfo.getContact());
						projectInfoContract.setBusinessType(this.projectInfo.getBusinessType());
						projectInfoContract.setOutline(this.projectInfo.getConOutline());
						this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
						
					}
					
					
				}
			
			if (isNew) {
				this.projectInfoPersonnels.setProjectInfo(projectInfo);
				 try {
					List one = this.codeValueManager.loadByKey("code", Long.valueOf("20201"));
					 if ((null != one) && (one.size() > 0)) {
						 CodeValue co =(CodeValue)one.get(0);
						 this.projectInfoPersonnels.setBusinessType(co);
					 }
					 this.projectInfoPersonnels.setOutline("项目负责人");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(projectInfoPersonnels);
				 
				
				 
			}
			
/* 172 */     if (isNew) {
/* 173 */       addActionMessage(getText("projectInfo.add.success", Arrays.asList(new Object[] { this.projectInfo.getName() })));
/*     */ 
/* 175 */       return "success";
/*     */     }
/* 177 */     addActionMessage(getText("projectInfo.edit.success", Arrays.asList(new Object[] { this.projectInfo.getName() })));
/* 179 */     return "success";
/*     */   }
/*     */ 
/*     */ 
/*     */   public UserManager getUserManager() {
/* 202 */     return this.userManager;
/*     */   }
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
/*     */ 
		public List<CodeValue> getAllStates()
/*     */   {
/*     */     try
/*     */     {
/* 211 */       List codes = new ArrayList();
/* 212 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("201"));
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
		public ProjectInfoPersonnels getProjectInfoPersonnels() {
			return projectInfoPersonnels;
		}
		public void setProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnels) {
			this.projectInfoPersonnels = projectInfoPersonnels;
		}
		public String getOpenFlag() {
			return openFlag;
		}
		public void setOpenFlag(String openFlag) {
			this.openFlag = openFlag;
		}
		public String getNotNewFlag() {
			return notNewFlag;
		}
		public void setNotNewFlag(String notNewFlag) {
			this.notNewFlag = notNewFlag;
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