/*     */ package com.yongjun.tdms.presentation.webwork.action.base.helpManual;
/*     */ 
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.presentation.webwork.FileTransportAction;
/*     */ import com.yongjun.pluto.service.file.FileTransportManager;
/*     */ import com.yongjun.tdms.model.base.document.ApplicationDoc;
/*     */ import com.yongjun.tdms.model.base.document.ApplicationDocType;
/*     */ import com.yongjun.tdms.service.base.document.ApplicationDocManager;
/*     */ import java.util.Arrays;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditManualAction extends FileTransportAction
/*     */ {
/*     */   private static final long serialVersionUID = -877987215782827292L;
/*     */   private final ApplicationDocManager applicationDocManager;
/*     */   private final FileTransportManager fileTransportManager;
/*     */   private ApplicationDoc manual;
/*     */ 
/*     */   public EditManualAction(ApplicationDocManager applicationDocManager, FileTransportManager fileTransportManager)
/*     */   {
/*  46 */     this.applicationDocManager = applicationDocManager;
/*  47 */     this.fileTransportManager = fileTransportManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  54 */     if (null == this.manual)
/*  55 */       if (hasId("manual.id")) {
/*  56 */         this.manual = this.applicationDocManager.loadApplicationDoc(getId("manual.id"));
/*     */       }
/*     */       else
/*  59 */         this.manual = new ApplicationDoc();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/*  70 */     boolean isNew = this.manual.isNew();
/*     */ 
/*  72 */     if ((isNew) || (getFile() != null)) {
/*  73 */       if (!isNew) {
/*  74 */         this.fileTransportManager.delete(this.request, this.manual.getPosition());
/*     */       }
/*  76 */       String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");
/*     */ 
/*  78 */       this.manual.setPosition(location);
/*  79 */       String orgFileName = this.request.getParameter("origFileName");
/*  80 */       this.manual.setFileName(orgFileName);
/*  81 */       this.manual.setFileNo(location);
/*     */     }
/*     */ 
/*  84 */     this.manual.setDocFlag(ApplicationDocType.HELP_MANUAL_DOC);
/*     */ 
/*  86 */     this.applicationDocManager.storeApplicationDoc(this.manual);
/*  87 */     String logContent = "";
/*  88 */     if (isNew)
/*  89 */       logContent = "被添加";
/*     */     else {
/*  91 */       logContent = "被修改";
/*     */     }
/*  93 */     getLogger().logStore(this.manual, "(文件名称:" + this.manual.getName() + ")" + logContent, "help_manual");
/*  94 */     if (isNew) {
/*  95 */       if (!isMostNumberForTheManualDoc()) {
/*  96 */         addActionMessage(getText("manual.add.success", Arrays.asList(new Object[] { this.manual.getName() })));
/*     */       }
/*     */       else {
/*  99 */         addActionMessage(getText("manual.most.number", Arrays.asList(new Object[] { this.manual.getName(), this.applicationDocManager.getMostUploadNumberForManualDoc() })));
/*     */       }
/*     */ 
/* 102 */       return "new";
/*     */     }
/* 104 */     addActionMessage(getText("manual.edit.success", Arrays.asList(new Object[] { this.manual.getName() })));
/*     */ 
/* 106 */     return "success";
/*     */   }
/*     */ 
/*     */   public ApplicationDoc getManual()
/*     */   {
/* 111 */     return this.manual;
/*     */   }
/*     */ 
/*     */   public void setManual(ApplicationDoc manual) {
/* 115 */     this.manual = manual;
/*     */   }
/*     */ 
/*     */   public Integer getNumberOfManualDoc()
/*     */   {
/* 123 */     return this.applicationDocManager.getNumberOfManualDoc();
/*     */   }
/*     */ 
/*     */   public boolean isMostNumberForTheManualDoc()
/*     */   {
/* 131 */     return this.applicationDocManager.isMostNumberForTheManualDoc();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.helpManual.EditManualAction
 * JD-Core Version:    0.6.2
 */