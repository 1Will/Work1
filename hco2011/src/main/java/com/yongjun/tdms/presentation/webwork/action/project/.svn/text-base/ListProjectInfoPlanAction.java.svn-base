/*     */ package com.yongjun.tdms.presentation.webwork.action.project;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
		  import com.yongjun.tdms.service.project.ProjectInfoManager;
		  import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
		  import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListProjectInfoPlanAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private ProjectInfoManager projectInfoManager;
/*     */   private List<ProjectInfoPlan> ProjectInfoPlan;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
            private final ProjectInfoPlanManager projectInfoPlanManager;
            private String projectInfoId;
            
/*     */ 
/*     */   public ListProjectInfoPlanAction(CodeValueManager codeValueManager,ProjectInfoManager projectInfoManager, PersonnelFilesManager personnelFilesManager,UserManager userManager,ProjectInfoPlanManager ProjectInfoPlanManager)
/*     */   {
/*  35 */    this.codeValueManager=codeValueManager;
              this.projectInfoManager=projectInfoManager;
              this.personnelFilesManager =personnelFilesManager;
              this.userManager = userManager;
              this.projectInfoPlanManager =ProjectInfoPlanManager;

/*     */   }
/*     */ 
/*     */ 
/*     *
/*     */ 
/*     */   public CodeValueManager getCodeValueManager() {
/*  58 */     return this.codeValueManager;
/*     */   }
/*     */ 
/*     */   public PersonnelFilesManager getPersonnelFilesManager() {
/*  62 */     return this.personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFilesManager(PersonnelFilesManager personnelFilesManager) {
/*  66 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public UserManager getUserManager() {
/*  70 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  76 */     return "projectInfoPlan";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
//	         Map map= new HashMap();//super.getRequestParameterMap();
//	         if (hasId("projectInfo.id")) {
//	        	 map.put("projectInfo.id", this.request.getParameter("projectInfo.id"));
//	         }
//	         map.put("onlyValid", true);
					Map map = super.getRequestParameterMap();
	 			  if (hasId("projectInfo.id")) {
	 /* 126 */       map.put("projectInfoId", getId("projectInfo.id"));
	 /*     */     }
    			
	         
/*  89 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	        		 if (hasId("projectInfo.id")) {
	        			 /*  56 */         this.projectInfoId =this.request.getParameter("projectInfo.id");
	        			 /*     */       }
/*  96 */     if ((this.ProjectInfoPlan == null) && (hasIds("projectInfoPlanIds"))){
/*  97 */       this.ProjectInfoPlan = this.projectInfoPlanManager.loadAllProjectInfoPlan(getIds("projectInfoPlanIds"));
				}
/*     */   }

/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 103 */     if (isDisabled()) {
/* 104 */       return disabled();
/*     */     }
/*     */ 
/* 107 */     if (isEnable()) {
/* 108 */       return enable();
/*     */     }
/*     */ 
/* 111 */     if (isDelete()) {
/* 112 */       return delete();
/*     */     }
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public boolean getIsPersonnelFiles() throws Exception {
/* 118 */     if (null == this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode())) {
/* 119 */       return false;
/*     */     }
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   private String delete() {
/*     */     try {
/* 126 */       this.projectInfoPlanManager.deleteAllProjectInfoPlan(this.ProjectInfoPlan);
/* 127 */       addActionMessage(getText("projectInfos.delete.success"));
/* 128 */       return "success";
/*     */     } catch (Exception e) {
/* 130 */       addActionMessage(getText("backVisits.delete.failer"));
/* 131 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enable()
/*     */   {
/*     */     try {
/* 137 */       this.projectInfoPlanManager.enableProjectInfoPlans(this.ProjectInfoPlan);
/* 138 */       addActionMessage(getText("projectInfos.enable.success"));
/* 139 */       return "success";
/*     */     } catch (Exception e) {
/* 141 */       addActionMessage(getText("backVisits.enable.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try {
/* 148 */       this.projectInfoPlanManager.disabledProjectInfoPlans(this.ProjectInfoPlan);
/* 149 */       addActionMessage(getText("backVisits.disabled.success"));
/* 150 */       return "success";
/*     */     } catch (Exception e) {
/* 152 */       addActionMessage(getText("backVisits.disabled.failer"));
/* 153 */     }return "error";
/*     */   }
/*     */ 
/*     */ 

			public ProjectInfoManager getProjectInfoManager() {
				return projectInfoManager;
			}
			public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
				this.projectInfoManager = projectInfoManager;
			}
			
			
			public String getProjectInfoId() {
				return projectInfoId;
			}
			public void setProjectInfoId(String projectInfoId) {
				this.projectInfoId = projectInfoId;
			}
			
			public List<ProjectInfoPlan> getProjectInfoPlan() {
				return ProjectInfoPlan;
			}
			public void setProjectInfoPlan(List<ProjectInfoPlan> ProjectInfoPlan) {
				this.ProjectInfoPlan = ProjectInfoPlan;
			}
			
/*     */ 
/* 229 */   public void setCodeValueManager(CodeValueManager codeValueManager) { this.codeValueManager = codeValueManager; }
/*     */ 
/*     */ }
        

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListBackVisitAction
 * JD-Core Version:    0.6.2
 */