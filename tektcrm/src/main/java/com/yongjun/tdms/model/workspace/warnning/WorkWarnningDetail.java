/*     */ package com.yongjun.tdms.model.workspace.warnning;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class WorkWarnningDetail extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 8166114571964234010L;
/*     */   private String name;
/*     */   private Long workWarnningId;
/*     */   private Long remaindays;
/*     */   private Date warnDate;
/*     */   private String code;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  64 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  68 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  72 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Long getRemaindays() {
/*  76 */     return this.remaindays;
/*     */   }
/*     */ 
/*     */   public void setRemaindays(Long remaindays) {
/*  80 */     this.remaindays = remaindays;
/*     */   }
/*     */ 
/*     */   public Date getWarnDate() {
/*  84 */     return this.warnDate;
/*     */   }
/*     */ 
/*     */   public void setWarnDate(Date warnDate) {
/*  88 */     this.warnDate = warnDate;
/*     */   }
/*     */ 
/*     */   public Long getWorkWarnningId() {
/*  92 */     return this.workWarnningId;
/*     */   }
/*     */ 
/*     */   public void setWorkWarnningId(Long workWarnningId) {
/*  96 */     this.workWarnningId = workWarnningId;
/*     */   }
/*     */ 
/*     */   public String getCode() {
/* 100 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code) {
/* 104 */     this.code = code;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workspace.warnning.WorkWarnningDetail
 * JD-Core Version:    0.6.2
 */