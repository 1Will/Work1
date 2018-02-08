/*     */ package com.yongjun.tdms.model.notice;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ 
/*     */ public class Notice extends BaseInfoEntity
/*     */ {
/*     */   private User noticeUser;
/*     */   private String title;
/*     */   private String content;
/*     */   private Receive receiveStatus;
/*     */   private SendStatus send;
/*     */   private Read readStatus;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  59 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/*  65 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/*  71 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public User getNoticeUser()
/*     */   {
/*  77 */     return this.noticeUser;
/*     */   }
/*     */ 
/*     */   public void setNoticeUser(User noticeUser)
/*     */   {
/*  83 */     this.noticeUser = noticeUser;
/*     */   }
/*     */ 
/*     */   public Read getReadStatus()
/*     */   {
/*  89 */     return this.readStatus;
/*     */   }
/*     */ 
/*     */   public void setReadStatus(Read readStatus)
/*     */   {
/*  95 */     this.readStatus = readStatus;
/*     */   }
/*     */ 
/*     */   public Receive getReceiveStatus()
/*     */   {
/* 101 */     return this.receiveStatus;
/*     */   }
/*     */ 
/*     */   public void setReceiveStatus(Receive receiveStatus)
/*     */   {
/* 107 */     this.receiveStatus = receiveStatus;
/*     */   }
/*     */ 
/*     */   public SendStatus getSend()
/*     */   {
/* 113 */     return this.send;
/*     */   }
/*     */ 
/*     */   public void setSend(SendStatus send)
/*     */   {
/* 119 */     this.send = send;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 125 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 131 */     this.title = title;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.notice.Notice
 * JD-Core Version:    0.6.2
 */