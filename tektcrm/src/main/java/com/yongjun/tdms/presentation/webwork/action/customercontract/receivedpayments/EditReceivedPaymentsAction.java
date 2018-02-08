/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.receivedpayments;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.customercontract.receivedpayments.ReceivedPayments;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.customercontract.receivedpayments.ReceivedPaymentsManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditReceivedPaymentsAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final ReceivedPaymentsManager receivedPaymentsManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private ReceivedPayments receivedPayments;
/*     */ 
/*     */   public EditReceivedPaymentsAction(ReceivedPaymentsManager receivedPaymentsManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, ContractManagementManager contractManagementManager)
/*     */   {
/*  78 */     this.receivedPaymentsManager = receivedPaymentsManager;
/*  79 */     this.codeValueManager = codeValueManager;
/*  80 */     this.personnelFilesManager = personnelFilesManager;
/*  81 */     this.customerInfoManager = customerInfoManager;
/*  82 */     this.contactArchivesManager = contactArchivesManager;
/*  83 */     this.contractManagementManager = contractManagementManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  91 */     if (hasId("receivedPayments.id")) {
/*  92 */       this.receivedPayments = this.receivedPaymentsManager.loadReceivedPayments(getId("receivedPayments.id"));
/*     */     }
/*     */     else
/*  95 */       this.receivedPayments = new ReceivedPayments();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 105 */     boolean isNew = this.receivedPayments.isNew();
/*     */ 
/* 107 */     if (!StringUtils.isEmpty(this.request.getParameter("payment.id"))) {
/* 108 */       this.receivedPayments.setPayment(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("payment.id"))));
/*     */     }
/*     */ 
/* 113 */     if (!StringUtils.isEmpty(this.request.getParameter("currency.id"))) {
/* 114 */       this.receivedPayments.setCurrency(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("currency.id"))));
/*     */     }
/*     */ 
/* 118 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 119 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 121 */       if (null != customer) {
/* 122 */         this.receivedPayments.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 126 */     if (!StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
/* 127 */       ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(Long.valueOf(this.request.getParameter("contractManagement.id")));
/*     */ 
/* 129 */       if (null != contractManagement) {
/* 130 */         this.receivedPayments.setContractManagement(contractManagement);
/*     */       }
/*     */     }
/*     */ 
/* 134 */     if (!StringUtils.isEmpty(this.request.getParameter("payee.id"))) {
/* 135 */       PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("payee.id")));
/*     */ 
/* 137 */       if (null != payee) {
/* 138 */         this.receivedPayments.setPayee(payee);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 143 */     if (!StringUtils.isEmpty(this.request.getParameter("contactArchives.id"))) {
/* 144 */       ContactArchives linkman = this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("contactArchives.id")));
/*     */ 
/* 146 */       if (null != linkman) {
/* 147 */         this.receivedPayments.setContactArchives(linkman);
/*     */       }
/*     */     }
/*     */ 
/* 151 */     if (hasId("isOrNot")) {
/* 152 */       String isOrNot = this.request.getParameter("isOrNot");
/* 153 */       this.receivedPayments.setIsOrNot(isOrNot);
/*     */     }
/*     */ 
/* 156 */     this.receivedPaymentsManager.storeReceivedPayments(this.receivedPayments);
/* 157 */     if (isNew) {
/* 158 */       addActionMessage(getText("receivedPayments.save.success"));
/* 159 */       return "new";
/*     */     }
/* 161 */     addActionMessage(getText("receivedPayments.edit.success"));
/* 162 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllCurrencys()
/*     */   {
/* 173 */     List codes = null;
/*     */     try {
/* 175 */       codes = new ArrayList();
/* 176 */       List one = this.codeValueManager.loadByKey("code", "067");
/*     */ 
/* 178 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 180 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 182 */         if ((null != list) && (list.size() > 0)) {
/* 183 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 186 */       return codes;
/*     */     } catch (DaoException e) {
/* 188 */       e.printStackTrace();
/*     */     }
/* 190 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayments()
/*     */   {
/* 198 */     List codes = null;
/*     */     try {
/* 200 */       codes = new ArrayList();
/* 201 */       List one = this.codeValueManager.loadByKey("code", "065");
/*     */ 
/* 203 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 205 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 207 */         if ((null != list) && (list.size() > 0)) {
/* 208 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 211 */       return codes;
/*     */     } catch (DaoException e) {
/* 213 */       e.printStackTrace();
/*     */     }
/* 215 */     return codes;
/*     */   }
/*     */ 
/*     */   public ReceivedPayments getReceivedPayments()
/*     */   {
/* 223 */     return this.receivedPayments;
/*     */   }
/*     */ 
/*     */   public void setReceivedPayments(ReceivedPayments receivedPayments)
/*     */   {
/* 232 */     this.receivedPayments = receivedPayments;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.receivedpayments.EditReceivedPaymentsAction
 * JD-Core Version:    0.6.2
 */