/*     */ package com.yongjun.tdms.model.base.document;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.OrganizationTracking;
/*     */ import com.yongjun.tdms.model.advisory.Advisory;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;

/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class ApplicationDoc extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String fileNo;
/*     */   private String fileName;
/*     */   private String name;
/*     */   private String description;
/*     */   private String position;
/*     */   private String manualVersion;
/*     */   private String creatorName;
/*     */   private Date uploadDate;
/*     */   private ApplicationDocType docFlag;
/*     */   private Advisory advisory;
/*     */   private BackVisit backVisit;
/*     */   private Supplier supplier;
            private ProjectInfo projectInfo;
/*     */   private ContractAdministrator contractAdministrator;
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  38 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/*  42 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getFileName() {
/*  46 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String fileName) {
/*  50 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   public String getFileNo() {
/*  54 */     return this.fileNo;
/*     */   }
/*     */ 
/*     */   public void setFileNo(String fileNo) {
/*  58 */     this.fileNo = fileNo;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  62 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  66 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getPosition() {
/*  70 */     return this.position;
/*     */   }
/*     */ 
/*     */   public void setPosition(String position) {
/*  74 */     this.position = position;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  79 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  84 */     if (o == this) return true;
/*  85 */     if (!(o instanceof ApplicationDoc)) return false;
/*     */ 
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   public ApplicationDocType getDocFlag() {
/*  91 */     return this.docFlag;
/*     */   }
/*     */ 
/*     */   public void setDocFlag(ApplicationDocType docFlag) {
/*  95 */     this.docFlag = docFlag;
/*     */   }
/*     */ 
/*     */   public String getManualVersion() {
/*  99 */     return this.manualVersion;
/*     */   }
/*     */ 
/*     */   public void setManualVersion(String manualVersion) {
/* 103 */     this.manualVersion = manualVersion;
/*     */   }
/*     */ 
/*     */   public String getCreatorName() {
/* 107 */     return this.creatorName;
/*     */   }
/*     */ 
/*     */   public void setCreatorName(String creatorName) {
/* 111 */     this.creatorName = creatorName;
/*     */   }
/*     */ 
/*     */   public String getDomainModelProperty(String mark)
/*     */   {
/* 116 */     return getProperties().getProperty(mark);
/*     */   }
/*     */ 
/*     */   public Advisory getAdvisory() {
/* 120 */     return this.advisory;
/*     */   }
/*     */ 
/*     */   public void setAdvisory(Advisory advisory) {
/* 124 */     this.advisory = advisory;
/*     */   }
/*     */ 
/*     */   public Date getUploadDate() {
/* 128 */     return this.uploadDate;
/*     */   }
/*     */ 
/*     */   public void setUploadDate(Date uploadDate) {
/* 132 */     this.uploadDate = uploadDate;
/*     */   }
/*     */ 
/*     */   public BackVisit getBackVisit() {
/* 136 */     return this.backVisit;
/*     */   }
/*     */ 
/*     */   public void setBackVisit(BackVisit backVisit) {
/* 140 */     this.backVisit = backVisit;
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier() {
/* 144 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(Supplier supplier) {
/* 148 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   public ContractAdministrator getContractAdministrator()
/*     */   {
/* 154 */     return this.contractAdministrator;
/*     */   }
/*     */ 
/*     */   public void setContractAdministrator(ContractAdministrator contractAdministrator)
/*     */   {
/* 161 */     this.contractAdministrator = contractAdministrator;
/*     */   }
			public ProjectInfo getProjectInfo() {
				return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}
               
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.document.ApplicationDoc
 * JD-Core Version:    0.6.2
 */