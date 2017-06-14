/*     */ package com.yongjun.tdms.presentation.webwork.action.project;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
		  import com.yongjun.tdms.model.project.ProjectInfo;
		  import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
		  import com.yongjun.tdms.service.project.ProjectInfoManager;
		  import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
/*     */ import java.util.Arrays;
/*     */ public class EditProjectInfoPlanAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final ProjectInfoManager projectInfoManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
            private final ProjectInfoPlanManager projectInfoPlanManager;
/*     */   private final UserManager userManager;
/*     */   private String  projectInfoId;
            private ProjectInfoPlan projectInfoPlan;
/*     */
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public EditProjectInfoPlanAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager, PersonnelFilesManager personnelFilesManager, UserManager userManager,ProjectInfoPlanManager projectInfoPlanManager)
/*     */   {
/*  40 */     this.codeValueManager = codeValueManager;
/*  41 */     this.projectInfoManager = projectInfoManager;
/*  42 */     this.personnelFilesManager = personnelFilesManager;
/*  45 */     this.userManager = userManager;
              this.projectInfoPlanManager =projectInfoPlanManager;
/*     */   }
/*     */   public void prepare() throws Exception {
/*  55 */       if (hasId("projectInfo.id")) {
/*  56 */         this.projectInfoId = getId("projectInfo.id")+"";
/*     */       } 

				if (this.projectInfoPlan == null)
/*  55 */       if (hasId("projectInfoPlan.id")) {
/*  56 */         this.projectInfoPlan = this.projectInfoPlanManager.loadProjectInfoPlan(getId("projectInfoPlan.id"));
/*     */       } else {
/*  58 */         this.projectInfoPlan = new ProjectInfoPlan();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String save() {
/*  74 */     boolean isNew = this.projectInfoPlan.isNew();
/*     */ 
/*  76 */     if (hasId("projectInfo.id")) {
/*  77 */       ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/*  78 */       this.projectInfoPlan.setProjectInfo(projectInfo);
/*     */     }
			  if(hasId("projectInfoPlan.percentt")){
				this.projectInfoPlan.setPercentt(Integer.parseInt(request.getParameter("projectInfoPlan.percentt")));
			  }
			  if(hasId("projectInfoPlan.priority")){
				  this.projectInfoPlan.setPriority(Integer.parseInt(request.getParameter("projectInfoPlan.priority")));
			  }
/*  86 */    
/*  91 */     if (hasId("personnelFiles.id")) {
/*  92 */       this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
                  this.projectInfoPlan.setPersonnelFiles(personnelFiles);
/*     */     }
				User u=this.userManager.getUser();
              if(isNew){
            	  this.projectInfoPlan.setCreator(u.getName());
            	  this.projectInfoPlan.setLastOperator(u.getName());
              }else{
            	  this.projectInfoPlan.setLastOperator(u.getName());
              }
              this.projectInfoPlanManager.storeProjectInfoPlan(projectInfoPlan);
			  
/*     */ 
/*     */ 
			
/* 172 */     if (isNew) {
/* 173 */       addActionMessage(getText("projectInfoPlan.add.success", Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
/*     */ 
/* 175 */       return "success";
/*     */     }
/* 177 */     addActionMessage(getText("projectInfoPlan.edit.success", Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
/* 179 */     return "success";
/*     */   }
/*     */ 
/*     */ 
/*     */   public UserManager getUserManager() {
/* 202 */     return this.userManager;
/*     */   }
/*     */ 
		public String getProjectInfoId() {
			return projectInfoId;
		}
		public void setProjectInfoId(String projectInfoId) {
			this.projectInfoId = projectInfoId;
		}
		public ProjectInfoPlan getProjectInfoPlan() {
			return projectInfoPlan;
		}
		public void setProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
			this.projectInfoPlan = projectInfoPlan;
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