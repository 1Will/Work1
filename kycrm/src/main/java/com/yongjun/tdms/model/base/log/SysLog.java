/*    */ package com.yongjun.tdms.model.base.log;
/*    */ 
/*    */ import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ 
/*    */ public class SysLog extends BaseInfoEntity
/*    */ {
/*    */   private static final long serialVersionUID = -4382822273007403500L;
/*    */   private SysLogType type;
/*    */   private String content;
/*    */ 
/*    */   public String getContent()
/*    */   {
/* 34 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 38 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public SysLogType getType() {
/* 42 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(SysLogType type) {
/* 46 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object arg0)
/*    */   {
/* 57 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.log.SysLog
 * JD-Core Version:    0.6.2
 */