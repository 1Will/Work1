/*     */ package com.yongjun.tdms.presentation.webwork.action.project;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

/*     */ import java.util.ArrayList;
import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListProjectInfoAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private ProjectInfoManager projectInfoManager;
/*     */   private List<ProjectInfo> projectInfos;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
            private String contactArchivesFlag;
            private String backVisitCheckBox;
            private Long customerId;


/*     */ 
/*     */   public ListProjectInfoAction(CodeValueManager codeValueManager,ProjectInfoManager projectInfoManager, PersonnelFilesManager personnelFilesManager,UserManager userManager)
/*     */   {
/*  35 */    this.codeValueManager=codeValueManager;
              this.projectInfoManager=projectInfoManager;
              this.personnelFilesManager =personnelFilesManager;
              this.userManager = userManager;

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
/*  76 */     return "projectInfo";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
	         Map map=super.getRequestParameterMap();
	         if (hasId("customer.id")) {
/* 126 */       map.put("customerId", getId("customer.id"));
/*     */     }
	         if (this.request.getParameter("projectInfo.createdTime")!=null) {
/* 126 */       map.put("projectInfo.createdTime", this.request.getParameter("projectInfo.createdTime")+"%");
/*     */     }
/*  89 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  96 */     if ((this.projectInfos == null) && (hasIds("projectInfoIds"))){
/*  97 */       this.projectInfos = this.projectInfoManager.loadAllProjectInfo(getIds("projectInfoIds"));
				}
             if(this.request.getParameter("contactArchivesFlag") != null){
            	 this.contactArchivesFlag =this.request.getParameter("contactArchivesFlag");
            	 
             }
             if (this.request.getParameter("backVisitCheckBox") != null) {
/*  99 */       this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
/*     */     }
             if (hasId("customerInfo.id")) {
/*     */       this.customerId = getId("customerInfo.id");
/*     */       setFirst(false);
/*     */     }
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
/* 126 */       this.projectInfoManager.deleteAllProjectInfo(this.projectInfos);
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
/* 137 */       this.projectInfoManager.enableProjectInfos(this.projectInfos);
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
/* 148 */       this.projectInfoManager.disabledProjectInfos(this.projectInfos);
/* 149 */       addActionMessage(getText("project.disabled.success"));
/* 150 */       return "success";
/*     */     } catch (Exception e) {
/* 152 */       addActionMessage(getText("project.disabled.fail"));
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
					CodeValue cv = new CodeValue();
					cv.setId(null);
					cv.setName(getText("所有"));
					codes.add(0, cv);
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
			public String getBackVisitCheckBox() {
				return backVisitCheckBox;
			}
			public void setBackVisitCheckBox(String backVisitCheckBox) {
				this.backVisitCheckBox = backVisitCheckBox;
			}

			public ProjectInfoManager getProjectInfoManager() {
				return projectInfoManager;
			}
			public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
				this.projectInfoManager = projectInfoManager;
			}
			public List<ProjectInfo> getProjectInfos() {
				return projectInfos;
			}
			public void setProjectInfos(List<ProjectInfo> projectInfos) {
				this.projectInfos = projectInfos;
			}
			
			public String getContactArchivesFlag() {
				return contactArchivesFlag;
			}
			public void setContactArchivesFlag(String contactArchivesFlag) {
				this.contactArchivesFlag = contactArchivesFlag;
			}
/*     */ 
/* 229 */   public void setCodeValueManager(CodeValueManager codeValueManager) { 
				this.codeValueManager = codeValueManager; 
			}
			
			public Long getCustomerId() {
				return customerId;
			}
			public void setCustomerId(Long customerId) {
				this.customerId = customerId;
			}
/*     */ 
/*     */ }
        

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListBackVisitAction
 * JD-Core Version:    0.6.2
 */