/*    */ package com.yongjun.tdms.presentation.webwork.action.notice;
/*    */ 
/*    */ import com.yongjun.pluto.service.file.FileTransportManager;
/*    */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*    */ import com.yongjun.tdms.model.notice.ReadStatus;
/*    */ import com.yongjun.tdms.model.notice.ReceviceNotice;
/*    */ import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
/*    */ 
/*    */ public class EditReceviceNoticeAction extends PrepareAction
/*    */ {
/*    */   private static final long serialVersionUID = -8342978203395583399L;
/*    */   private final ReceviceNoticeManager receviceNoticeManager;
/*    */   private final FileTransportManager fileTransportManager;
/*    */   private ReceviceNotice receviceNotice;
/*    */ 
/*    */   public EditReceviceNoticeAction(ReceviceNoticeManager receviceNoticeManager, FileTransportManager fileTransportManager)
/*    */   {
/* 43 */     this.receviceNoticeManager = receviceNoticeManager;
/* 44 */     this.fileTransportManager = fileTransportManager;
/*    */   }
/*    */ 
/*    */   public void prepare() throws Exception {
/* 48 */     if (hasId("receviceNotice.id")) {
/* 49 */       this.receviceNotice = this.receviceNoticeManager.loadReceviceNotice(getId("receviceNotice.id"));
/*    */ 
/* 51 */       this.receviceNotice.setReadStatus(ReadStatus.READED);
/* 52 */       this.receviceNoticeManager.storeReceviceNotice(this.receviceNotice);
/*    */     }
/* 54 */     if (hasId("downloadDoc.id"))
/* 55 */       this.receviceNotice = this.receviceNoticeManager.loadReceviceNotice(getId("downloadDoc.id"));
/*    */   }
/*    */ 
/*    */   public String download() {
/* 59 */     this.fileTransportManager.download(this.request, this.response, this.receviceNotice.getFileName(), this.receviceNotice.getPosition());
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */   public ReceviceNotice getReceviceNotice() {
/* 64 */     return this.receviceNotice;
/*    */   }
/*    */ 
/*    */   public void setReceviceNotice(ReceviceNotice receviceNotice) {
/* 68 */     this.receviceNotice = receviceNotice;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.notice.EditReceviceNoticeAction
 * JD-Core Version:    0.6.2
 */