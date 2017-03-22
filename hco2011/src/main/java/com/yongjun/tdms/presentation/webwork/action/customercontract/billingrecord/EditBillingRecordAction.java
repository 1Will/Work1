/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditBillingRecordAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final BillingRecordManager billingRecordManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private BillingRecord billingRecord;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final UserManager userManager;
/*     */ 
/*     */   public EditBillingRecordAction(BillingRecordManager billingRecordManager, ContactArchivesManager contactArchivesManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContractManagementManager contractManagementManager, UserManager userManager)
/*     */   {
/*  77 */     this.billingRecordManager = billingRecordManager;
/*  78 */     this.contactArchivesManager = contactArchivesManager;
/*  79 */     this.personnelFilesManager = personnelFilesManager;
/*  80 */     this.customerInfoManager = customerInfoManager;
/*  81 */     this.contractManagementManager = contractManagementManager;
/*  82 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  90 */     if (hasId("billingRecord.id")) {
/*  91 */       this.billingRecord = this.billingRecordManager.loadBillingRecord(getId("billingRecord.id"));
/*     */     }
/*     */     else {
/*  94 */       this.billingRecord = new BillingRecord();
/*     */     }
/*     */ 
/*  97 */     User user = this.userManager.getUser();
/*  98 */     List list = this.personnelFilesManager.loadByKey("code", user.getCode());
/*  99 */     if (null != list) {
/* 100 */       PersonnelFiles payee = (PersonnelFiles)list.get(0);
/* 101 */       this.billingRecord.setPayee(payee);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 111 */     boolean isNew = this.billingRecord.isNew();
/*     */ 
/* 113 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 114 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 116 */       if (null != customer) {
/* 117 */         this.billingRecord.setCustomerInfo(customer);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 122 */     if (!StringUtils.isEmpty(this.request.getParameter("contactArchives.id"))) {
/* 123 */       ContactArchives contactArchives = this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("contactArchives.id")));
/*     */ 
/* 125 */       if (null != contactArchives) {
/* 126 */         this.billingRecord.setContactArchives(contactArchives);
/*     */       }
/*     */     }
/*     */ 
/* 130 */     if (!StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
/* 131 */       ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(Long.valueOf(this.request.getParameter("contractManagement.id")));
/*     */ 
/* 133 */       if (null != contractManagement) {
/* 134 */         this.billingRecord.setContractManagement(contractManagement);
/*     */       }
/*     */     }
/*     */ 
/* 138 */     if (!StringUtils.isEmpty(this.request.getParameter("payee.id"))) {
/* 139 */       PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("payee.id")));
/*     */ 
/* 141 */       if (null != payee) {
/* 142 */         this.billingRecord.setPayee(payee);
/*     */       }
/*     */     }
/*     */ 
/* 146 */     this.billingRecordManager.storeBillingRecord(this.billingRecord);
/* 147 */     if (isNew) {
/* 148 */       addActionMessage(getText("billingRecord.save.success"));
/* 149 */       return "new";
/*     */     }
/* 151 */     addActionMessage(getText("billingRecord.edit.success"));
/* 152 */     return "success";
/*     */   }
/*     */ 
/*     */   public BillingRecord getBillingRecord()
/*     */   {
/* 163 */     return this.billingRecord;
/*     */   }
/*     */ 
/*     */   public void setBillingRecord(BillingRecord billingRecord)
/*     */   {
/* 172 */     this.billingRecord = billingRecord;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord.EditBillingRecordAction
 * JD-Core Version:    0.6.2
 */