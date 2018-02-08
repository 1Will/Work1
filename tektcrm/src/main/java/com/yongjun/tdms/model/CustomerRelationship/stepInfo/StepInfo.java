/*    */ package com.yongjun.tdms.model.CustomerRelationship.stepInfo;
/*    */ 
/*    */ import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*    */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*    */ 
/*    */ public class StepInfo extends BaseInfoEntity
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private CustomerInfo customerId;
/*    */   private BackVisit backVisitId;
/*    */   private CodeValue customerSteping;
/*    */   private CodeValue customerSteped;
/*    */   private String changeReason;
		   private User user;
		   private Date changeDate;
/*    */ 
/*    */   public String getChangeReason()
/*    */   {
/* 21 */     return this.changeReason;
/*    */   }
/*    */ 
/*    */   public void setChangeReason(String changeReason)
/*    */   {
/* 27 */     this.changeReason = changeReason;
/*    */   }
/*    */ 
/*    */   public BackVisit getBackVisitId()
/*    */   {
/* 34 */     return this.backVisitId;
/*    */   }
/*    */ 
/*    */   public void setBackVisitId(BackVisit backVisitId)
/*    */   {
/* 40 */     this.backVisitId = backVisitId;
/*    */   }
/*    */ 
/*    */   public CustomerInfo getCustomerId()
/*    */   {
/* 46 */     return this.customerId;
/*    */   }
/*    */ 
/*    */   public void setCustomerId(CustomerInfo customerId)
/*    */   {
/* 52 */     this.customerId = customerId;
/*    */   }
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 58 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id)
/*    */   {
/* 64 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object arg0)
/*    */   {
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 75 */     return 0;
/*    */   }
/*    */ 
/*    */   public CodeValue getCustomerSteped()
/*    */   {
/* 81 */     return this.customerSteped;
/*    */   }
/*    */ 
/*    */   public void setCustomerSteped(CodeValue customerSteped)
/*    */   {
/* 87 */     this.customerSteped = customerSteped;
/*    */   }
/*    */ 
/*    */   public CodeValue getCustomerSteping()
/*    */   {
/* 93 */     return this.customerSteping;
/*    */   }
/*    */ 
/*    */   public void setCustomerSteping(CodeValue customerSteping)
/*    */   {
/* 99 */     this.customerSteping = customerSteping;
/*    */   }
		   public User getUser() {
			   return user;
		   }
		   public void setUser(User user) {
			   this.user = user;
		   }
		   public Date getChangeDate() {
			   return changeDate;
		   }
		   public void setChangeDate(Date changeDate) {
			   this.changeDate = changeDate;
		   }

/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo
 * JD-Core Version:    0.6.2
 */