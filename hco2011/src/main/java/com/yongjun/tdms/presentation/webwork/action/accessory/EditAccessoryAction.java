/*     */ package com.yongjun.tdms.presentation.webwork.action.accessory;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.presentation.webwork.FileTransportAction;
/*     */ import com.yongjun.pluto.service.file.FileTransportManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.tdms.model.advisory.Advisory;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.model.base.document.ApplicationDoc;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.advisory.AdvisoryManager;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
/*     */ import com.yongjun.tdms.service.base.document.ApplicationDocManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;

/*     */ import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditAccessoryAction extends FileTransportAction
/*     */ {
/*     */   private static final long serialVersionUID = -6722017437417848485L;
/*     */   private final ApplicationDocManager applicationDocManager;
/*     */   private final AdvisoryManager advisoryManager;
/*     */   private final FileTransportManager fileTransportManager;
/*     */   private final BackVisitManager backVisitManager;
/*     */   private final SupplierManager supplierManager;
/*     */   private UserManager userManager;
/*     */   private final ContractAdministratorManager contractAdministratorManager;
            private final ProjectInfoManager projectInfoManager;
/*     */   private ApplicationDoc applicationDoc;
/*     */   private Advisory advisory;
/*     */   private BackVisit backVisit;
/*     */   private Supplier supplier;
            private ProjectInfo projectInfo;
/*     */   private ContractAdministrator contractAdministrator;
/*     */ 
/*     */   public EditAccessoryAction(ApplicationDocManager applicationDocManager, AdvisoryManager advisoryManager, FileTransportManager fileTransportManager, BackVisitManager backVisitManager, SupplierManager supplierManager, ContractAdministratorManager contractAdministratorManager,ProjectInfoManager projectInfoManager)
/*     */   {
/*  55 */     this.applicationDocManager = applicationDocManager;
/*  56 */     this.advisoryManager = advisoryManager;
/*  57 */     this.fileTransportManager = fileTransportManager;
/*  58 */     this.backVisitManager = backVisitManager;
/*  59 */     this.supplierManager = supplierManager;
/*  60 */     this.contractAdministratorManager = contractAdministratorManager;
              this.projectInfoManager =projectInfoManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  69 */     if (this.applicationDoc == null) {
/*  70 */       if (hasId("applicationDoc.id")) {
/*  71 */         this.applicationDoc = this.applicationDocManager.loadApplicationDoc(getId("applicationDoc.id"));
/*     */       } else {
/*  73 */         this.applicationDoc = new ApplicationDoc();
/*  74 */         this.applicationDoc.setCreatorName(this.userManager.getUser().getName());
/*     */       }
/*     */     }
/*  77 */     if (hasId("advisory.id")) {
/*  78 */       this.advisory = this.advisoryManager.loadAdvisory(getId("advisory.id"));
/*     */     }
/*  80 */     if (hasId("projectInfo.id")) {
/*  81 */       this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/*     */     }
				if (hasId("backVisit.id")) {
/*  81 */       this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
/*     */     }
/*  83 */     if (hasId("supplier.id")) {
/*  84 */       this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/*     */     }
/*  86 */     if (hasId("contractAdministrator.id"))
/*  87 */       this.contractAdministrator = this.contractAdministratorManager.loadContractAdministrator(getId("contractAdministrator.id"));
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/*  99 */     boolean isNew = this.applicationDoc.isNew();
/* 100 */     if ((isNew) || (getFile() != null)) {
/* 101 */       if (!isNew) {
/* 102 */         this.fileTransportManager.delete(this.request, this.applicationDoc.getPosition());
/*     */       }
/* 104 */       String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");
/*     */ 
/* 106 */       this.applicationDoc.setPosition(location);
/* 107 */       String orgFileName = this.request.getParameter("origFileName");
/* 108 */       this.applicationDoc.setFileName(orgFileName);
/* 109 */       this.applicationDoc.setFileNo(location);
/*     */     }
/*     */     try {
/* 112 */       if (this.advisory != null) {
/* 113 */         this.applicationDoc.setAdvisory(this.advisory);
/*     */       }
/* 115 */       if (this.backVisit != null) {
/* 116 */         this.applicationDoc.setBackVisit(this.backVisit);
/*     */       }
				if (this.projectInfo != null) {
/* 116 */         this.applicationDoc.setProjectInfo(projectInfo);
/*     */       }
/* 118 */       if (this.supplier != null) {
/* 119 */         this.applicationDoc.setSupplier(this.supplier);
/*     */       }
/* 121 */       if (this.contractAdministrator != null) {
/* 122 */         this.applicationDoc.setContractAdministrator(this.contractAdministrator);
/*     */       }
/* 124 */       this.applicationDocManager.storeApplicationDoc(this.applicationDoc);
/*     */     } catch (Exception e) {
/* 126 */       addActionMessage(getText("applicationDoc.add.error", Arrays.asList(new Object[] { this.applicationDoc.getName() })));
/* 127 */       e.printStackTrace();
/* 128 */       return "error";
/*     */     }
/* 130 */     addActionMessage(getText("applicationDoc.add.success", Arrays.asList(new Object[] { this.applicationDoc.getName() })));
/* 131 */     return "success";
/*     */   }
/*     */   public ApplicationDoc getApplicationDoc() {
/* 134 */     return this.applicationDoc;
/*     */   }
/*     */   public void setApplicationDoc(ApplicationDoc applicationDoc) {
/* 137 */     this.applicationDoc = applicationDoc;
/*     */   }
/*     */   public Advisory getAdvisory() {
/* 140 */     return this.advisory;
/*     */   }
/*     */   public void setAdvisory(Advisory advisory) {
/* 143 */     this.advisory = advisory;
/*     */   }
/*     */   public BackVisit getBackVisit() {
/* 146 */     return this.backVisit;
/*     */   }
/*     */   public void setBackVisit(BackVisit backVisit) {
/* 149 */     this.backVisit = backVisit;
/*     */   }
/*     */   public void setUserManager(UserManager userManager) {
/* 152 */     this.userManager = userManager;
/*     */   }
/*     */   public Supplier getSupplier() {
/* 155 */     return this.supplier;
/*     */   }
/*     */   public void setSupplier(Supplier supplier) {
/* 158 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   public ContractAdministrator getContractAdministrator()
/*     */   {
/* 164 */     return this.contractAdministrator;
/*     */   }
/*     */ 
/*     */   public void setContractAdministrator(ContractAdministrator contractAdministrator)
/*     */   {
/* 170 */     this.contractAdministrator = contractAdministrator;
/*     */   }
			public ProjectInfo getProjectInfo() {
				return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.accessory.EditAccessoryAction
 * JD-Core Version:    0.6.2
 */