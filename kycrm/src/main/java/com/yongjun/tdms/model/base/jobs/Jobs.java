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
           private CodeValue  hierarchy;
           private CodeValue  serialNumber;
           private CodeValue  rank;
           private String  jobReaker;
           private CodeValue grade;
        public CodeValue getHierarchy() {
			return hierarchy;
		}
		public CodeValue getRank() {
			return rank;
		}
		public void setRank(CodeValue rank) {
			this.rank = rank;
		}
		public String getJobReaker() {
			return jobReaker;
		}
		public void setJobReaker(String jobReaker) {
			this.jobReaker = jobReaker;
		}
		public void setHierarchy(CodeValue hierarchy) {
			this.hierarchy = hierarchy;
		}
		public CodeValue getSerialNumbe() {
			return serialNumber;
		}
		public void setSerialNumbe(CodeValue serialNumbe) {
			this.serialNumber = serialNumbe;
		}
		public CodeValue getGrade() {
			return grade;
		}
		public void setGrade(CodeValue grade) {
			this.grade = grade;
		}
           
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