/*     */ package com.yongjun.tdms.presentation.webwork.action.project;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoProduct.ProjectInfoProductManager;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;

/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListProjectInfoProductAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private ProjectInfoManager projectInfoManager;
/*     */   private List<ProjectInfoProduct> ProjectInfoProduct;
/*     */   private final UserManager userManager;
            private final ProjectInfoProductManager projectInfoProductManager;
            private String projectInfoId;
            private String productIds;
            private String saveFlag;
            
/*     */ 
/*     */   public ListProjectInfoProductAction(CodeValueManager codeValueManager,ProjectInfoManager projectInfoManager,UserManager userManager,ProjectInfoProductManager ProjectInfoProductManager)
/*     */   {
/*  35 */    this.codeValueManager=codeValueManager;
              this.projectInfoManager=projectInfoManager;
              this.userManager = userManager;
              this.projectInfoProductManager =ProjectInfoProductManager;

/*     */   }
/*     */ 
/*     */ 
/*     *
/*     */ 
/*     */   public CodeValueManager getCodeValueManager() {
/*  58 */     return this.codeValueManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   public UserManager getUserManager() {
/*  70 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  76 */     return "projectInfoProduct";
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
	        		 if (hasId("productIds")) {
	        			 /*  56 */         this.productIds =this.request.getParameter("productIds");
	        			 /*     */       }
/*  96 */     if ((this.ProjectInfoProduct == null) && (hasIds("projectInfoProductIds"))){
/*  97 */       this.ProjectInfoProduct = this.projectInfoProductManager.loadAllProjectInfoProduct(getIds("projectInfoProductIds"));
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
              if(saveFlag!=null&&saveFlag.equals("saveFlag")){
            	  return saveProduct();
              }
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */ 
/*     */   private String delete() {
/*     */     try {
/* 126 */       this.projectInfoProductManager.deleteAllProjectInfoProduct(this.ProjectInfoProduct);
/* 127 */       addActionMessage(getText("projectInfos.delete.success"));
/* 128 */       return "success";
/*     */     } catch (Exception e) {
/* 130 */       addActionMessage(getText("backVisits.delete.failer"));
/* 131 */     }return "error";
/*     */   }
			private String saveProduct() {
/*     */     try {
				ProjectInfo projectInfo =this.projectInfoManager.loadProjectInfo(Long.parseLong(projectInfoId));
				if(projectInfo!=null&&productIds!=null){
					String [] arrid =productIds.split(",");
					 this.projectInfoProductManager.storeProjectInfoProducts(projectInfo,arrid);
				}
	
	            
/* 127 */       addActionMessage(getText("projectInfos.delete.success"));
/* 128 */       return "success";
/*     */     } catch (Exception e) {
	e.printStackTrace();
/* 130 */       addActionMessage(getText("backVisits.delete.failer"));
/* 131 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enable()
/*     */   {
/*     */     try {
/* 137 */       this.projectInfoProductManager.enableProjectInfoProducts(this.ProjectInfoProduct);
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
/* 148 */       this.projectInfoProductManager.disabledProjectInfoProducts(this.ProjectInfoProduct);
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
			public String getProjectInfoId() {
				return projectInfoId;
			}
			public void setProjectInfoId(String projectInfoId) {
				this.projectInfoId = projectInfoId;
			}
			
			public List<ProjectInfoProduct> getProjectInfoProduct() {
				return ProjectInfoProduct;
			}
			public void setProjectInfoProduct(List<ProjectInfoProduct> ProjectInfoProduct) {
				this.ProjectInfoProduct = ProjectInfoProduct;
			}
			
			
			public String getSaveFlag() {
				return saveFlag;
			}
			public void setSaveFlag(String saveFlag) {
				this.saveFlag = saveFlag;
			}
			
			public String getProductIds() {
				return productIds;
			}
			public void setProductIds(String productIds) {
				this.productIds = productIds;
			}
/*     */ 
/* 229 */   public void setCodeValueManager(CodeValueManager codeValueManager) { this.codeValueManager = codeValueManager; }
/*     */ 
/*     */ }
        

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListBackVisitAction
 * JD-Core Version:    0.6.2
 */