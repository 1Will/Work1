/*    */ package com.yongjun.tdms.model.base.org;
/*    */ 
/*    */ import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public class WorkGroup extends BaseInfoEntity
/*    */ {
/*    */   private static final long serialVersionUID = 5830606323676813015L;
/*    */   private String code;
/*    */   private String name;
/*    */ 
/*    */   public String getCode()
/*    */   {
/* 35 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 39 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 47 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 52 */     return this.code.hashCode();
/*    */   }
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 57 */     if (!(o instanceof WorkGroup)) return false;
/* 58 */     WorkGroup w = (WorkGroup)o;
/* 59 */     if (!this.code.equals(w.getCode())) return false;
/* 60 */     return true;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.org.WorkGroup
 * JD-Core Version:    0.6.2
 */