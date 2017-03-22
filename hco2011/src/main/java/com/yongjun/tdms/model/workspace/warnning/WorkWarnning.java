/*     */ package com.yongjun.tdms.model.workspace.warnning;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class WorkWarnning extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 448275123928744135L;
/*     */   private String type;
/*     */   private String content;
/*     */   private Date warnningDate;
/*  40 */   private boolean readFlag = false;
/*     */   private User warnedPeople;
/*     */   private Long remindObjectId;
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  52 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  57 */     if (o == this) return true;
/*  58 */     if (!(o instanceof WorkWarnning)) {
/*  59 */       return false;
/*     */     }
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/*  65 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/*  69 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getType() {
/*  73 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  77 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public User getWarnedPeople() {
/*  81 */     return this.warnedPeople;
/*     */   }
/*     */ 
/*     */   public void setWarnedPeople(User warnedPeople) {
/*  85 */     this.warnedPeople = warnedPeople;
/*     */   }
/*     */ 
/*     */   public Date getWarnningDate() {
/*  89 */     return this.warnningDate;
/*     */   }
/*     */ 
/*     */   public void setWarnningDate(Date warnningDate) {
/*  93 */     this.warnningDate = warnningDate;
/*     */   }
/*     */ 
/*     */   public boolean isReadFlag() {
/*  97 */     return this.readFlag;
/*     */   }
/*     */ 
/*     */   public void setReadFlag(boolean readFlag) {
/* 101 */     this.readFlag = readFlag;
/*     */   }
/*     */ 
/*     */   public Long getRemindObjectId()
/*     */   {
/* 108 */     return this.remindObjectId;
/*     */   }
/*     */ 
/*     */   public void setRemindObjectId(Long remindObjectId)
/*     */   {
/* 116 */     this.remindObjectId = remindObjectId;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workspace.warnning.WorkWarnning
 * JD-Core Version:    0.6.2
 */