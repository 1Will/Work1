/*    */ package com.yongjun.tdms.model.base.jobs;
/*    */ 
/*    */ import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*    */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*    */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*    */ 
/*    */ public class Jobs extends BaseInfoEntity
/*    */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String code;
/*    */   private String name;
/*    */   private CodeValue jobType;
/*    */ 
/*    */   public boolean equals(Object arg0)
/*    */   {
/* 44 */     if (arg0 == this) {
/* 45 */       return true;
/*    */     }
/* 47 */     if (!(arg0 instanceof Jobs)) {
/* 48 */       return false;
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 56 */     return 0;
/*    */   }
/*    */ 
/*    */   public String getCode()
/*    */   {
/* 63 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code)
/*    */   {
/* 70 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public CodeValue getJobType()
/*    */   {
/* 77 */     return this.jobType;
/*    */   }
/*    */ 
/*    */   public void setJobType(CodeValue jobType)
/*    */   {
/* 84 */     this.jobType = jobType;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 91 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 98 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.jobs.Jobs
 * JD-Core Version:    0.6.2
 */