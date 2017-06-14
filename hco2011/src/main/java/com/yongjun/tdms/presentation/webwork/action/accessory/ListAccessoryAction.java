/*     */ package com.yongjun.tdms.presentation.webwork.action.accessory;
/*     */ 
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.model.DomainModel;
/*     */ import com.yongjun.pluto.service.file.FileTransportManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
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

/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListAccessoryAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final ApplicationDocManager applicationDocManager;
/*     */   private final AdvisoryManager advisoryManager;
/*     */   private final FileTransportManager fileTransportManager;
/*     */   private final BackVisitManager backVisitManager;
/*     */   private final SupplierManager supplierManager;
            private final ProjectInfoManager projectInfoManager;
/*     */   private final ContractAdministratorManager contractAdministratorManager;
/*     */   private List<ApplicationDoc> applicationDocs;
/*     */   private ApplicationDoc applicationDoc;
/*     */   private Advisory advisory;
/*     */   private BackVisit backVisit;
/*     */   private Supplier supplier;
            private ProjectInfo projectInfo;
/*     */   private ContractAdministrator contractAdministrator;
/*     */ 
/*     */   public ListAccessoryAction(ApplicationDocManager applicationDocManager, FileTransportManager fileTransportManager, AdvisoryManager advisoryManager, BackVisitManager backVisitManager, SupplierManager supplierManager, ContractAdministratorManager contractAdministratorManager,ProjectInfoManager projectInfoManager)
/*     */   {
/*  55 */     this.applicationDocManager = applicationDocManager;
/*  56 */     this.fileTransportManager = fileTransportManager;
/*  57 */     this.advisoryManager = advisoryManager;
/*  58 */     this.backVisitManager = backVisitManager;
/*  59 */     this.supplierManager = supplierManager;
/*  60 */     this.contractAdministratorManager = contractAdministratorManager;
              this.projectInfoManager=projectInfoManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  68 */     if (hasId("applicationDoc.id")) {
/*  69 */       this.applicationDoc = this.applicationDocManager.loadApplicationDoc(getId("applicationDoc.id"));
/*  70 */       download();
/*     */     }
/*  72 */     if (hasId("advisory.id")) {
/*  73 */       this.advisory = this.advisoryManager.loadAdvisory(getId("advisory.id"));
/*     */     }
/*  75 */     if (hasId("backVisit.id")) {
/*  76 */       this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
/*     */     }
			  if (hasId("projectInfo.id")) {
/*  76 */       this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/*     */     }
/*  78 */     if (hasId("supplier.id")) {
/*  79 */       this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/*     */     }
/*  81 */     if (hasId("contractAdministrator.id")) {
/*  82 */       this.contractAdministrator = this.contractAdministratorManager.loadContractAdministrator(getId("contractAdministrator.id"));
/*     */     }
/*  84 */     if ((this.applicationDocs == null) && (hasIds("applicationDocIds"))) {
/*  85 */       this.applicationDocs = this.applicationDocManager.loadAllApplicationDocs(getIds("applicationDocIds"));
/*     */     }
/*  87 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  93 */     if (isDelete()) {
/*  94 */       delete();
/*     */     }
/*  96 */     return "success";
/*     */   }
/*     */ 
/*     */   private void delete()
/*     */   {
/* 102 */     String strDoc = "";
/* 103 */     for (Iterator iter = this.applicationDocs.iterator(); iter.hasNext(); ) {
/* 104 */       ApplicationDoc doc = (ApplicationDoc)iter.next();
/* 105 */       strDoc = strDoc + doc.getName() + ",";
/*     */ 
/* 107 */       this.fileTransportManager.delete(this.request, doc.getPosition());
/*     */ 
/* 109 */       this.applicationDocManager.deleteApplicationDoc(doc);
/*     */     }
/* 111 */     Integer index = null;
/* 112 */     index = Integer.valueOf(strDoc.lastIndexOf(","));
/* 113 */     strDoc = strDoc.substring(0, index.intValue());
/* 114 */     getLogger().logStore((DomainModel)this.applicationDocs.get(0), "(名称为:[" + strDoc + "]的文件)被删除", "applicationDoc");
/*     */ 
/* 116 */     addActionMessage(getText("applicationDocs.delete.success"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 121 */     return "applicationDoc";
/*     */   }
/*     */ 
/*     */   public String download()
/*     */   {
/* 128 */     this.fileTransportManager.download(this.request, this.response, this.applicationDoc.getFileName(), this.applicationDoc.getPosition());
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 137 */     Map map = super.getRequestParameterMap();
///* 138 */     if ((null != this.request.getParameter("advisory.id")) && ("" != this.request.getParameter("advisory.id"))) {
///* 139 */       map.put("advisory.id", Long.valueOf(this.request.getParameter("advisory.id")));
///*     */     }
///* 141 */     if ((null != this.request.getParameter("backVisit.id")) && ("" != this.request.getParameter("backVisit.id"))) {
///* 142 */       map.put("backVisit.id", Long.valueOf(this.request.getParameter("backVisit.id")));
///*     */     }
///* 144 */     if ((null != this.request.getParameter("supplier.id")) && ("" != this.request.getParameter("supplier.id"))) {
///* 145 */       map.put("supplier.id", Long.valueOf(this.request.getParameter("supplier.id")));
///*     */     }
///* 147 */     if ((null != this.request.getParameter("contractAdministrator.id")) && ("" != this.request.getParameter("contractAdministrator.id"))) {
///* 148 */       System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
///* 149 */       map.put("contractAdministrator.id", Long.valueOf(this.request.getParameter("contractAdministrator.id")));
///*     */     }
/* 151 */     return map;
/*     */   }
/*     */   public List<ApplicationDoc> getApplicationDocs() {
/* 154 */     return this.applicationDocs;
/*     */   }
/*     */   public void setApplicationDocs(List<ApplicationDoc> applicationDocs) {
/* 157 */     this.applicationDocs = applicationDocs;
/*     */   }
/*     */ 
/*     */   public Advisory getAdvisory() {
/* 161 */     return this.advisory;
/*     */   }
/*     */ 
/*     */   public void setAdvisory(Advisory advisory) {
/* 165 */     this.advisory = advisory;
/*     */   }
/*     */ 
/*     */   public ApplicationDoc getApplicationDoc() {
/* 169 */     return this.applicationDoc;
/*     */   }
/*     */ 
/*     */   public void setApplicationDoc(ApplicationDoc applicationDoc) {
/* 173 */     this.applicationDoc = applicationDoc;
/*     */   }
/*     */   public BackVisit getBackVisit() {
/* 176 */     return this.backVisit;
/*     */   }
/*     */   public void setBackVisit(BackVisit backVisit) {
/* 179 */     this.backVisit = backVisit;
/*     */   }
/*     */   public Supplier getSupplier() {
/* 182 */     return this.supplier;
/*     */   }
/*     */   public void setSupplier(Supplier supplier) {
/* 185 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   public ContractAdministrator getContractAdministrator()
/*     */   {
/* 191 */     return this.contractAdministrator;
/*     */   }
/*     */ 
/*     */   public void setContractAdministrator(ContractAdministrator contractAdministrator)
/*     */   {
/* 197 */     this.contractAdministrator = contractAdministrator;
/*     */   }
			public ProjectInfo getProjectInfo() {
				return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.accessory.ListAccessoryAction
 * JD-Core Version:    0.6.2
 */