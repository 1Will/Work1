 package com.yongjun.tdms.presentation.webwork.action.notice;
 
 import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.notice.ReadStatus;
import com.yongjun.tdms.model.notice.ReceviceNotice;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
 
 public class EditReceviceNoticeAction extends PrepareAction
 {
   private static final long serialVersionUID = -8342978203395583399L;
   private final ReceviceNoticeManager receviceNoticeManager;
   private final FileTransportManager fileTransportManager;
   private ReceviceNotice receviceNotice;
 
	public EditReceviceNoticeAction(ReceviceNoticeManager receviceNoticeManager, FileTransportManager fileTransportManager)
   {
     this.receviceNoticeManager = receviceNoticeManager;
     this.fileTransportManager = fileTransportManager;
   }
 
   public void prepare() throws Exception {
     if (hasId("receviceNotice.id")) {
       this.receviceNotice = this.receviceNoticeManager.loadReceviceNotice(getId("receviceNotice.id"));
 
       this.receviceNotice.setReadStatus(ReadStatus.READED);
       this.receviceNoticeManager.storeReceviceNotice(this.receviceNotice);
     }
     if (hasId("downloadDoc.id"))
       this.receviceNotice = this.receviceNoticeManager.loadReceviceNotice(getId("downloadDoc.id"));
   }
 
   public String download() {
     this.fileTransportManager.download(this.request, this.response, this.receviceNotice.getFileName(), this.receviceNotice.getPosition());
     return null;
   }
 
   public ReceviceNotice getReceviceNotice() {
     return this.receviceNotice;
   }
 
   public void setReceviceNotice(ReceviceNotice receviceNotice) {
     this.receviceNotice = receviceNotice;
   }
 }

