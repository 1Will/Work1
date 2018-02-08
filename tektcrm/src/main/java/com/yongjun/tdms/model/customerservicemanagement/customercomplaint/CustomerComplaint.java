/*     */ package com.yongjun.tdms.model.customerservicemanagement.customercomplaint;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CustomerComplaint extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String complaintTitle;
/*     */   private CustomerInfo customerInfo;
/*     */   private PersonnelFiles salesman;
/*     */   private ContactArchives linkman;
/*     */   private String telephone;
/*     */   private CodeValue complaintType;
/*     */   private CodeValue urgencyDegree;
/*     */   private float costTime;
/*     */   private Date startTime;
/*     */   private CodeValue disposeState;
/*     */   private CodeValue disposeWay;
/*     */   private CodeValue backValidate;
/*     */   private String complaintContent;
/*     */   private String disposeContent;
/*     */   private String customerFeedback;
/*     */   private String remark;
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 101 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 109 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getComplaintContent()
/*     */   {
/* 118 */     return this.complaintContent;
/*     */   }
/*     */ 
/*     */   public void setComplaintContent(String complaintContent)
/*     */   {
/* 126 */     this.complaintContent = complaintContent;
/*     */   }
/*     */ 
/*     */   public String getComplaintTitle()
/*     */   {
/* 135 */     return this.complaintTitle;
/*     */   }
/*     */ 
/*     */   public void setComplaintTitle(String complaintTitle)
/*     */   {
/* 143 */     this.complaintTitle = complaintTitle;
/*     */   }
/*     */ 
/*     */   public float getCostTime()
/*     */   {
/* 153 */     return this.costTime;
/*     */   }
/*     */ 
/*     */   public void setCostTime(float costTime)
/*     */   {
/* 161 */     this.costTime = costTime;
/*     */   }
/*     */ 
/*     */   public String getCustomerFeedback()
/*     */   {
/* 170 */     return this.customerFeedback;
/*     */   }
/*     */ 
/*     */   public void setCustomerFeedback(String customerFeedback)
/*     */   {
/* 178 */     this.customerFeedback = customerFeedback;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 187 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 195 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public String getDisposeContent()
/*     */   {
/* 204 */     return this.disposeContent;
/*     */   }
/*     */ 
/*     */   public void setDisposeContent(String disposeContent)
/*     */   {
/* 212 */     this.disposeContent = disposeContent;
/*     */   }
/*     */ 
/*     */   public ContactArchives getLinkman()
/*     */   {
/* 222 */     return this.linkman;
/*     */   }
/*     */ 
/*     */   public void setLinkman(ContactArchives linkman)
/*     */   {
/* 230 */     this.linkman = linkman;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 239 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 247 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSalesman()
/*     */   {
/* 256 */     return this.salesman;
/*     */   }
/*     */ 
/*     */   public void setSalesman(PersonnelFiles salesman)
/*     */   {
/* 264 */     this.salesman = salesman;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 273 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 281 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public String getTelephone()
/*     */   {
/* 290 */     return this.telephone;
/*     */   }
/*     */ 
/*     */   public void setTelephone(String telephone)
/*     */   {
/* 298 */     this.telephone = telephone;
/*     */   }
/*     */ 
/*     */   public CodeValue getBackValidate()
/*     */   {
/* 307 */     return this.backValidate;
/*     */   }
/*     */ 
/*     */   public void setBackValidate(CodeValue backValidate)
/*     */   {
/* 315 */     this.backValidate = backValidate;
/*     */   }
/*     */ 
/*     */   public CodeValue getComplaintType()
/*     */   {
/* 324 */     return this.complaintType;
/*     */   }
/*     */ 
/*     */   public void setComplaintType(CodeValue complaintType)
/*     */   {
/* 332 */     this.complaintType = complaintType;
/*     */   }
/*     */ 
/*     */   public CodeValue getDisposeState()
/*     */   {
/* 341 */     return this.disposeState;
/*     */   }
/*     */ 
/*     */   public void setDisposeState(CodeValue disposeState)
/*     */   {
/* 349 */     this.disposeState = disposeState;
/*     */   }
/*     */ 
/*     */   public CodeValue getDisposeWay()
/*     */   {
/* 358 */     return this.disposeWay;
/*     */   }
/*     */ 
/*     */   public void setDisposeWay(CodeValue disposeWay)
/*     */   {
/* 366 */     this.disposeWay = disposeWay;
/*     */   }
/*     */ 
/*     */   public CodeValue getUrgencyDegree()
/*     */   {
/* 375 */     return this.urgencyDegree;
/*     */   }
/*     */ 
/*     */   public void setUrgencyDegree(CodeValue urgencyDegree)
/*     */   {
/* 383 */     this.urgencyDegree = urgencyDegree;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 393 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 402 */     return 0;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.customerservicemanagement.customercomplaint.CustomerComplaint
 * JD-Core Version:    0.6.2
 */