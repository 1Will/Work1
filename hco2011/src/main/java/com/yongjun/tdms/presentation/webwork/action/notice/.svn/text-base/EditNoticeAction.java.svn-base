/*     */ package com.yongjun.tdms.presentation.webwork.action.notice;
/*     */ 
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.presentation.webwork.FileTransportAction;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.file.FileTransportManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.tdms.dao.notice.ReceviceNoticeDao;
/*     */ import com.yongjun.tdms.model.notice.ReceviceNotice;
/*     */ import com.yongjun.tdms.model.notice.SendNotice;
/*     */ import com.yongjun.tdms.model.notice.SendStatus;
/*     */ import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
/*     */ import com.yongjun.tdms.service.notice.SendNoticeManager;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditNoticeAction extends FileTransportAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SendNoticeManager sendNoticeManager;
/*     */   private final FileTransportManager fileTransportManager;
/*     */   private final UserManager userManager;
/*     */   private final ReceviceNoticeManager receviceNoticeManager;
/*     */   private final ReceviceNoticeDao receviceNoticeDao;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<User> users;
/*     */   private SendNotice sendNotice;
/*     */   private String saveOrSendFlag;
/*     */   private String cancelFlag;
/*     */   private String sendFlag;
/*  42 */   Set<User> userSended = new HashSet();
/*     */ 
/*     */   public EditNoticeAction(SendNoticeManager sendNoticeManager, FileTransportManager fileTransportManager, UserManager userManager, ReceviceNoticeManager receviceNoticeManager, ReceviceNoticeDao receviceNoticeDao, CodeValueManager codeValueManager)
/*     */   {
/*  48 */     this.sendNoticeManager = sendNoticeManager;
/*  49 */     this.fileTransportManager = fileTransportManager;
/*  50 */     this.userManager = userManager;
/*  51 */     this.receviceNoticeManager = receviceNoticeManager;
/*  52 */     this.receviceNoticeDao = receviceNoticeDao;
/*  53 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */   public void prepare() throws Exception {
/*  56 */     if (this.sendNotice == null) {
/*  57 */       if (hasId("sendNotice.id")) {
/*  58 */         this.sendNotice = this.sendNoticeManager.loadSendNotice(getId("sendNotice.id"));
/*     */       }
/*     */       else {
/*  61 */         this.sendNotice = new SendNotice();
/*     */       }
/*     */     }
/*  64 */     if (!StringUtils.isEmpty(this.request.getParameter("sendFlag")))
/*  65 */       this.sendFlag = "send";
/*     */   }
/*     */ 
/*     */   public String save() throws Exception
/*     */   {
/*  70 */     boolean isNew = this.sendNotice.isNew();
/*  71 */     if (hasKey("cancel")) {
/*  72 */       this.cancelFlag = "cancel";
/*  73 */       Set rNotice = this.sendNotice.getReceviceNotices();
/*  74 */       this.sendNotice.getReceviceNotices().removeAll(rNotice);
/*  75 */       this.receviceNoticeManager.deleteReceviceNotices(rNotice);
/*  76 */       this.sendNotice.setSendStatus(SendStatus.UNSEND);
/*     */ 
/*  78 */       User user = getLoginUser();
/*  79 */       this.sendNotice.setSendUser(user);
/*  80 */       this.sendNoticeManager.storeSendNotice(this.sendNotice);
/*  81 */       addActionMessage(getText("noticeSend.cancel.success", Arrays.asList(new Object[] { this.sendNotice.getTitle() })));
/*     */ 
/*  83 */       return "success";
/*     */     }
/*     */ 
/*  88 */     if ((null != getFile()) && (getFile().exists())) {
/*  89 */       String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");
/*  90 */       String orgFileName = this.request.getParameter("origFileName");
/*  91 */       this.sendNotice.setFileName(orgFileName);
/*  92 */       this.sendNotice.setPosition(location);
/*     */     }
/*     */ 
/*  96 */     if (!StringUtils.isEmpty(this.request.getParameter("loginUser.id"))) {
/*  97 */       this.sendNotice.setNoticeUser(this.userManager.loadUser(getId("loginUser.id")));
/*     */     }
/*     */ 
/* 102 */     if (hasKey("save")) {
/* 103 */       if (hasId("noticeType.id")) {
/* 104 */         this.sendNotice.setNoticeType(this.codeValueManager.loadCodeValue(getId("noticeType.id")));
/*     */       }
/* 106 */       this.sendNotice.setSendStatus(SendStatus.UNSEND);
/* 107 */       this.saveOrSendFlag = "save";
/* 108 */     } else if (hasKey("send")) {
/* 109 */       this.sendNotice.setSendStatus(SendStatus.SENDED);
/* 110 */       this.sendNotice.setNoticeType(this.codeValueManager.loadCodeValue(getId("noticeType.id")));
/* 111 */       this.saveOrSendFlag = "send";
/*     */     }
/*     */ 
/* 116 */     if (hasIds("availableUserIds")) {
/* 117 */       String availableUserIds = this.request.getParameter("availableUserIds");
/* 118 */       String[] availableUserId = availableUserIds.split(",");
/* 119 */       for (int i = 0; i < availableUserId.length; i++) {
/* 120 */         if ((availableUserId[i].indexOf("_user") == -1) && (availableUserId[i].indexOf("_group") == -1) && (availableUserId[i].indexOf("_dept") == -1) && (availableUserId[i] != null) && (!availableUserId[i].isEmpty()))
/*     */         {
/*     */           int tmp436_434 = i;
/*     */           String[] tmp436_433 = availableUserId; tmp436_433[tmp436_434] = (tmp436_433[tmp436_434] + "_user");
/*     */         }
/*     */       }
/* 126 */       if ((availableUserId != null) && (!availableUserId[0].isEmpty()))
/*     */       {
/* 128 */         this.sendNoticeManager.joinUsersForSend(availableUserId, this.sendNotice, this.userSended);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 133 */     User u = getLoginUser();
/* 134 */     System.out.println(u.getName());
/* 135 */     this.sendNotice.setSendUser(u);
/* 136 */     this.sendNoticeManager.storeSendNotice(this.sendNotice);
/*     */ 
/* 138 */     if (hasKey("send"))
/*     */     {
/* 140 */       this.users = this.userManager.loadAllUsers();
/*     */ 
/* 146 */       for (User user : this.userSended) {
/* 147 */         ReceviceNotice reNotice = new ReceviceNotice();
/* 148 */         reNotice.setTitle(this.sendNotice.getTitle());
/* 149 */         reNotice.setContent(this.sendNotice.getContent());
/* 150 */         reNotice.setNoticeType(this.codeValueManager.loadCodeValue(getId("noticeType.id")));
/* 151 */         if (this.sendNotice.getFileName() != null) {
/* 152 */           reNotice.setFileName(this.sendNotice.getFileName());
/*     */         }
/* 154 */         reNotice.setName(this.sendNotice.getName());
/* 155 */         reNotice.setNoticeUser(this.sendNotice.getNoticeUser());
/* 156 */         reNotice.setPosition(this.sendNotice.getPosition());
/* 157 */         reNotice.setReceviceUser(user);
/* 158 */         reNotice.setValidityDate(this.sendNotice.getValidityDate());
/* 159 */         reNotice.setSendNotice(this.sendNotice);
/* 160 */         this.receviceNoticeManager.storeReceviceNotice(reNotice);
/*     */       }
/*     */ 
/* 165 */       addActionMessage(getText("noticeSend.send.success", Arrays.asList(new Object[] { this.sendNotice.getTitle() })));
/*     */ 
/* 167 */       return "success";
/*     */     }
/*     */ 
/* 170 */     if (isNew) {
/* 171 */       addActionMessage(getText("noticeSend.add.success", Arrays.asList(new Object[] { this.sendNotice.getTitle() })));
/*     */ 
/* 173 */       return "new";
/*     */     }
/* 175 */     addActionMessage(getText("noticeSend.edit.success", Arrays.asList(new Object[] { this.sendNotice.getTitle() })));
/*     */ 
/* 177 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNoticeTypes()
/*     */   {
/* 187 */     List list = null;
/* 188 */     list = this.codeValueManager.loadAllChildByParentCode("090");
/*     */ 
/* 190 */     return list;
/*     */   }
/*     */ 
/*     */   public SendNotice getSendNotice() {
/* 194 */     return this.sendNotice;
/*     */   }
/*     */   public void setSendNotice(SendNotice sendNotice) {
/* 197 */     this.sendNotice = sendNotice;
/*     */   }
/*     */   public User getLoginUser() {
/* 200 */     return this.userManager.getUser();
/*     */   }
/*     */   public String getSaveOrSendFlag() {
/* 203 */     return this.saveOrSendFlag;
/*     */   }
/*     */   public void setSaveOrSendFlag(String saveOrSendFlag) {
/* 206 */     this.saveOrSendFlag = saveOrSendFlag;
/*     */   }
/*     */   public String getCancelFlag() {
/* 209 */     return this.cancelFlag;
/*     */   }
/*     */   public void setCancelFlag(String cancelFlag) {
/* 212 */     this.cancelFlag = cancelFlag;
/*     */   }
/*     */   public String getSendFlag() {
/* 215 */     return this.sendFlag;
/*     */   }
/*     */   public void setSendFlag(String sendFlag) {
/* 218 */     this.sendFlag = sendFlag;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.notice.EditNoticeAction
 * JD-Core Version:    0.6.2
 */