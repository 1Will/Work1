/*     */ package com.yongjun.tdms.presentation.webwork.action.project;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
		  import com.yongjun.tdms.service.project.ProjectInfoManager;
		  import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
		  import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListProjectInfoContractAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private ProjectInfoManager projectInfoManager;
/*     */   private List<ProjectInfoContract> ProjectInfoContract;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
            private String contactArchivesFlag;
            private final ProjectInfoContractManager ProjectInfoContractManager;
            private String projectInfoId;
            private String customerInfoId;
            
/*     */ 
/*     */   public ListProjectInfoContractAction(CodeValueManager codeValueManager,ProjectInfoManager projectInfoManager, PersonnelFilesManager personnelFilesManager,UserManager userManager,ProjectInfoContractManager ProjectInfoContractManager)
/*     */   {
/*  35 */    this.codeValueManager=codeValueManager;
              this.projectInfoManager=projectInfoManager;
              this.personnelFilesManager =personnelFilesManager;
              this.userManager = userManager;
              this.ProjectInfoContractManager =ProjectInfoContractManager;

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
/*  76 */     return "projectInfoContract";
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
	        		 if (hasId("customerInfo.id")) {
	        			 /*  56 */         this.customerInfoId =this.request.getParameter("customerInfo.id");
	        			 /*     */       }
/*  96 */     if ((this.ProjectInfoContract == null) && (hasIds("projectInfoContractIds"))){
/*  97 */       this.ProjectInfoContract = this.ProjectInfoContractManager.loadAllProjectInfoContract(getIds("projectInfoContractIds"));
				}
             if(this.request.getParameter("contactArchivesFlag") != null){
            	 this.contactArchivesFlag =this.request.getParameter("contactArchivesFlag");
            	 
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
/* 126 */       this.ProjectInfoContractManager.deleteAllProjectInfoContract(this.ProjectInfoContract);
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
/* 137 */       this.ProjectInfoContractManager.enableProjectInfoContracts(this.ProjectInfoContract);
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
/* 148 */       this.ProjectInfoContractManager.disabledProjectInfoContracts(this.ProjectInfoContract);
/* 149 */       addActionMessage(getText("backVisits.disabled.success"));
/* 150 */       return "success";
/*     */     } catch (Exception e) {
/* 152 */       addActionMessage(getText("backVisits.disabled.failer"));
/* 153 */     }return "error";
/*     */   }
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
/*     */ 
/*     */ 

			public ProjectInfoManager getProjectInfoManager() {
				return projectInfoManager;
			}
			public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
				this.projectInfoManager = projectInfoManager;
			}
			
			public String getContactArchivesFlag() {
				return contactArchivesFlag;
			}
			public void setContactArchivesFlag(String contactArchivesFlag) {
				this.contactArchivesFlag = contactArchivesFlag;
			}
			
			public String getProjectInfoId() {
				return projectInfoId;
			}
			public void setProjectInfoId(String projectInfoId) {
				this.projectInfoId = projectInfoId;
			}
			
			public List<ProjectInfoContract> getProjectInfoContract() {
				return ProjectInfoContract;
			}
			public void setProjectInfoContract(List<ProjectInfoContract> ProjectInfoContract) {
				this.ProjectInfoContract = ProjectInfoContract;
			}
			
			public String getCustomerInfoId() {
				return customerInfoId;
			}
			public void setCustomerInfoId(String customerInfoId) {
				this.customerInfoId = customerInfoId;
			}
/*     */ 
/* 229 */   public void setCodeValueManager(CodeValueManager codeValueManager) { this.codeValueManager = codeValueManager; }
/*     */ 
/*     */ }
        

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListBackVisitAction
 * JD-Core Version:    0.6.2
 */