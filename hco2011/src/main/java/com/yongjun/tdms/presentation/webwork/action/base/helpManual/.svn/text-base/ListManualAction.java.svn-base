/*     */ package com.yongjun.tdms.presentation.webwork.action.base.helpManual;
/*     */ 
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.model.DomainModel;
/*     */ import com.yongjun.pluto.service.file.FileTransportManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.document.ApplicationDoc;
/*     */ import com.yongjun.tdms.service.base.document.ApplicationDocManager;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListManualAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1028590432151252029L;
/*     */   private final ApplicationDocManager applicationDocManager;
/*     */   private final FileTransportManager fileTransportManager;
/*     */   private ApplicationDoc manual;
/*     */   private List<ApplicationDoc> helpManuals;
/*     */ 
/*     */   public ListManualAction(ApplicationDocManager applicationDocManager, FileTransportManager fileTransportManager)
/*     */   {
/*  47 */     this.applicationDocManager = applicationDocManager;
/*  48 */     this.fileTransportManager = fileTransportManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */   {
/*  55 */     if (hasId("manual.id")) {
/*  56 */       this.manual = this.applicationDocManager.loadApplicationDoc(getId("manual.id"));
/*  57 */       download();
/*     */     }
/*  59 */     if (hasIds("helpManualIds"))
/*  60 */       this.helpManuals = this.applicationDocManager.loadAllApplicationDocs(getIds("helpManualIds"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  65 */     if (isDelete()) {
/*  66 */       delete();
/*     */     }
/*  68 */     return "success";
/*     */   }
/*     */ 
/*     */   public void delete()
/*     */   {
/*  75 */     String strDoc = "";
/*  76 */     for (Iterator iter = this.helpManuals.iterator(); iter.hasNext(); ) {
/*  77 */       ApplicationDoc doc = (ApplicationDoc)iter.next();
/*     */ 
/*  79 */       strDoc = strDoc + doc.getName() + ",";
/*     */ 
/*  81 */       this.fileTransportManager.delete(this.request, doc.getPosition());
/*     */ 
/*  83 */       this.applicationDocManager.deleteApplicationDoc(doc);
/*     */     }
/*  85 */     Integer index = null;
/*  86 */     index = Integer.valueOf(strDoc.lastIndexOf(","));
/*  87 */     strDoc = strDoc.substring(0, index.intValue());
/*  88 */     getLogger().logStore((DomainModel)this.helpManuals.get(0), "(名称为:[" + strDoc + "]的文件)被删除", "help_manual");
/*     */ 
/*  90 */     addActionMessage(getText("manual.delete.success"));
/*     */   }
/*     */ 
/*     */   public String download()
/*     */   {
/*  98 */     this.fileTransportManager.download(this.request, this.response, this.manual.getFileName(), this.manual.getPosition());
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 107 */     return "helpManuals";
/*     */   }
/*     */ 
/*     */   public ApplicationDoc getManual() {
/* 111 */     return this.manual;
/*     */   }
/*     */ 
/*     */   public void setManual(ApplicationDoc manual) {
/* 115 */     this.manual = manual;
/*     */   }
/*     */ 
/*     */   public boolean isMostNumberForTheManualDoc()
/*     */   {
/* 123 */     return this.applicationDocManager.isMostNumberForTheManualDoc();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.helpManual.ListManualAction
 * JD-Core Version:    0.6.2
 */