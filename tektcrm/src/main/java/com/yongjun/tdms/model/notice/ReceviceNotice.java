/*     */ package com.yongjun.tdms.model.notice;
/*     */ 
/*     */ import com.yongjun.pluto.model.VersionalEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ReceviceNotice extends VersionalEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1109609740334098206L;
/*     */   private String title;
/*     */   private String content;
/*  37 */   private ReadStatus readStatus = ReadStatus.UNREAD;
/*     */   private String fileName;
/*     */   private String name;
/*     */   private String position;
/*  41 */   private Date receviceDate = new Date();
/*     */   private Date validityDate;
/*  43 */   private boolean disabled = false;
/*     */   private User noticeUser;
/*     */   private User receviceUser;
/*     */   private SendNotice sendNotice;
/*     */   private CodeValue noticeType;
/*     */ 
/*     */   public CodeValue getNoticeType()
/*     */   {
/*  50 */     return this.noticeType;
/*     */   }
/*     */ 
/*     */   public void setNoticeType(CodeValue noticeType) {
/*  54 */     this.noticeType = noticeType;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  59 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  64 */     if (o == this) return true;
/*  65 */     if (!(o instanceof ReceviceNotice)) return false;
/*  66 */     ReceviceNotice rnotice = (ReceviceNotice)o;
/*  67 */     if (!rnotice.getId().equals(getId())) return false;
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
/*     */   public boolean isDisabled() {
/*  80 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(boolean disabled) {
/*  84 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public String getFileName() {
/*  88 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String fileName) {
/*  92 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   public User getNoticeUser() {
/*  96 */     return this.noticeUser;
/*     */   }
/*     */ 
/*     */   public void setNoticeUser(User noticeUser) {
/* 100 */     this.noticeUser = noticeUser;
/*     */   }
/*     */ 
/*     */   public String getPosition() {
/* 104 */     return this.position;
/*     */   }
/*     */ 
/*     */   public void setPosition(String position) {
/* 108 */     this.position = position;
/*     */   }
/*     */ 
/*     */   public ReadStatus getReadStatus() {
/* 112 */     return this.readStatus;
/*     */   }
/*     */ 
/*     */   public void setReadStatus(ReadStatus readStatus) {
/* 116 */     this.readStatus = readStatus;
/*     */   }
/*     */ 
/*     */   public User getReceviceUser() {
/* 120 */     return this.receviceUser;
/*     */   }
/*     */ 
/*     */   public void setReceviceUser(User receviceUser) {
/* 124 */     this.receviceUser = receviceUser;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 128 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 132 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 136 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 140 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Date getReceviceDate() {
/* 144 */     return this.receviceDate;
/*     */   }
/*     */ 
/*     */   public void setReceviceDate(Date receviceDate) {
/* 148 */     this.receviceDate = receviceDate;
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
/*     */   public SendNotice getSendNotice() {
/* 160 */     return this.sendNotice;
/*     */   }
/*     */ 
/*     */   public void setSendNotice(SendNotice sendNotice) {
/* 164 */     this.sendNotice = sendNotice;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.notice.ReceviceNotice
 * JD-Core Version:    0.6.2
 */