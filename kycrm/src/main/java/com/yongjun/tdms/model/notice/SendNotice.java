/*     */ package com.yongjun.tdms.model.notice;
/*     */ 
/*     */ import com.yongjun.pluto.model.VersionalEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SendNotice extends VersionalEntity
/*     */ {
/*     */   private static final long serialVersionUID = 5790087785401819404L;
/*     */   private String title;
/*     */   private String content;
/*  39 */   private SendStatus sendStatus = SendStatus.UNSEND;
/*     */   private String fileName;
/*     */   private String name;
/*     */   private String position;
/*  43 */   private Date sendDate = new Date();
/*     */   private Date validityDate;
/*  45 */   private boolean disabled = false;
/*     */   private User noticeUser;
/*     */   private User sendUser;
/*     */   private CodeValue noticeType;
/*  50 */   private Set<User> users = new HashSet();
/*  51 */   private Set<ReceviceNotice> receviceNotices = new HashSet();
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  55 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  60 */     if (this == o) {
/*  61 */       return true;
/*     */     }
/*  63 */     if (!(o instanceof SendNotice)) {
/*  64 */       return false;
/*     */     }
/*  66 */     SendNotice sNotice = (SendNotice)o;
/*  67 */     if (!sNotice.getId().equals(getId())) return false;
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/*  72 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/*  76 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getFileName() {
/*  80 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String fileName) {
/*  84 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   public String getPosition() {
/*  88 */     return this.position;
/*     */   }
/*     */ 
/*     */   public void setPosition(String position) {
/*  92 */     this.position = position;
/*     */   }
/*     */ 
/*     */   public SendStatus getSendStatus() {
/*  96 */     return this.sendStatus;
/*     */   }
/*     */ 
/*     */   public void setSendStatus(SendStatus sendStatus) {
/* 100 */     this.sendStatus = sendStatus;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 104 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 108 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Set<User> getUsers() {
/* 112 */     return this.users;
/*     */   }
/*     */ 
/*     */   public void setUsers(Set<User> users) {
/* 116 */     this.users = users;
/*     */   }
/*     */ 
/*     */   public boolean isDisabled() {
/* 120 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(boolean disabled) {
/* 124 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 128 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 132 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Date getSendDate() {
/* 136 */     return this.sendDate;
/*     */   }
/*     */ 
/*     */   public void setSendDate(Date sendDate) {
/* 140 */     this.sendDate = sendDate;
/*     */   }
/*     */ 
/*     */   public User getNoticeUser() {
/* 144 */     return this.noticeUser;
/*     */   }
/*     */ 
/*     */   public void setNoticeUser(User noticeUser) {
/* 148 */     this.noticeUser = noticeUser;
/*     */   }
/*     */ 
/*     */   public Date getValidityDate() {
/* 152 */     return this.validityDate;
/*     */   }
/*     */ 
/*     */   public void setValidityDate(Date validityDate) {
/* 156 */     this.validityDate = validityDate;
/*     */   }
/*     */ 
/*     */   public Set<ReceviceNotice> getReceviceNotices() {
/* 160 */     return this.receviceNotices;
/*     */   }
/*     */ 
/*     */   public void setReceviceNotices(Set<ReceviceNotice> receviceNotices) {
/* 164 */     this.receviceNotices = receviceNotices;
/*     */   }
/*     */ 
/*     */   public CodeValue getNoticeType() {
/* 168 */     return this.noticeType;
/*     */   }
/*     */ 
/*     */   public void setNoticeType(CodeValue noticeType) {
/* 172 */     this.noticeType = noticeType;
/*     */   }
/*     */ 
/*     */   public static long getSerialVersionUID()
/*     */   {
/* 179 */     return 5790087785401819404L;
/*     */   }
/*     */ 
/*     */   public User getSendUser()
/*     */   {
/* 186 */     return this.sendUser;
/*     */   }
/*     */ 
/*     */   public void setSendUser(User sendUser)
/*     */   {
/* 193 */     this.sendUser = sendUser;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.notice.SendNotice
 * JD-Core Version:    0.6.2
 */