/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.plan;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditReturnPlanAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final ReturnPlanManager returnPlanManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private ReturnPlan returnPlan;
/*     */ 
/*     */   public EditReturnPlanAction(ReturnPlanManager returnPlanManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, ContractManagementManager contractManagementManager)
/*     */   {
/*  80 */     this.returnPlanManager = returnPlanManager;
/*  81 */     this.codeValueManager = codeValueManager;
/*  82 */     this.personnelFilesManager = personnelFilesManager;
/*  83 */     this.customerInfoManager = customerInfoManager;
/*  84 */     this.contactArchivesManager = contactArchivesManager;
/*  85 */     this.contractManagementManager = contractManagementManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  94 */     if (hasId("returnPlan.id")) {
/*  95 */       this.returnPlan = this.returnPlanManager.loadReturnPlan(getId("returnPlan.id"));
/*     */     }
/*     */     else
/*  98 */       this.returnPlan = new ReturnPlan();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 108 */     boolean isNew = this.returnPlan.isNew();
/*     */ 
/* 110 */     if (!StringUtils.isEmpty(this.request.getParameter("payment.id"))) {
/* 111 */       this.returnPlan.setPayment(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("payment.id"))));
/*     */     }
/*     */ 
/* 115 */     if (!StringUtils.isEmpty(this.request.getParameter("batch.id"))) {
/* 116 */       this.returnPlan.setBatch(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("batch.id"))));
/*     */     }
/*     */ 
/* 120 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 121 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 123 */       if (null != customer) {
/* 124 */         this.returnPlan.setCustomerInfo(customer);
/*     */       }
/*     */     }
/* 127 */     ContractManagement contractManagement = null;
/*     */ 
/* 129 */     if (!StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
/* 130 */       contractManagement = this.contractManagementManager.loadContractManagement(Long.valueOf(this.request.getParameter("contractManagement.id")));
/*     */ 
/* 132 */       if (null != contractManagement) {
/* 133 */         this.returnPlan.setContractManagement(contractManagement);
/*     */       }
/*     */     }
/*     */ 
/* 137 */     if (!StringUtils.isEmpty(this.request.getParameter("payee.id"))) {
/* 138 */       PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("payee.id")));
/*     */ 
/* 140 */       if (null != payee) {
/* 141 */         this.returnPlan.setPayee(payee);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 146 */     if (!StringUtils.isEmpty(this.request.getParameter("contactArchives.id"))) {
/* 147 */       ContactArchives linkman = this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("contactArchives.id")));
/*     */ 
/* 149 */       if (null != linkman) {
/* 150 */         this.returnPlan.setContactArchives(linkman);
/*     */       }
/*     */     }
/*     */ 
/* 154 */     this.returnPlan.setIsOrNot("1");
/*     */ 
/* 157 */     if (hasId("notOrIs")) {
/* 158 */       String notOrIs = this.request.getParameter("notOrIs");
/* 159 */       this.returnPlan.setNotOrIs(notOrIs);
/*     */     }
/*     */ 
/* 163 */     if (hasId("billingOrNot")) {
/* 164 */       String billingOrNot = this.request.getParameter("billingOrNot");
/* 165 */       this.returnPlan.setBillingOrNot(billingOrNot);
/*     */     }
/* 167 */     this.returnPlanManager.storeReturnPlan(this.returnPlan);
/* 168 */     List<ReturnPlan> lists = this.returnPlanManager.loadAllReturnPlans();
/* 169 */     Double sum = Double.valueOf(0.0D);
/* 170 */     if (null != lists) {
/* 171 */       for (ReturnPlan p : lists) {
/* 172 */         sum = Double.valueOf(sum.doubleValue() + p.getSum().doubleValue());
/*     */       }
/*     */     }
/* 175 */     if (StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
/* 176 */       contractManagement = this.returnPlan.getContractManagement();
/*     */     }
/* 178 */     if ((null != contractManagement) && 
/* 179 */       (sum.doubleValue() == contractManagement.getContractMoney())) {
/* 180 */       contractManagement.setOverReturnPlan("yes");
/* 181 */       this.contractManagementManager.storeContractManagement(contractManagement);
/*     */     }
/*     */ 
/* 185 */     if (isNew) {
/* 186 */       addActionMessage(getText("returnPlan.save.success"));
/* 187 */       return "new";
/*     */     }
/* 189 */     addActionMessage(getText("returnPlan.edit.success"));
/* 190 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBatchs()
/*     */   {
/* 201 */     List codes = null;
/*     */     try {
/* 203 */       codes = new ArrayList();
/* 204 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("03330"));
/*     */ 
/* 206 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 208 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 210 */         if ((null != list) && (list.size() > 0)) {
/* 211 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 214 */       return codes;
/*     */     } catch (DaoException e) {
/* 216 */       e.printStackTrace();
/*     */     }
/* 218 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayments()
/*     */   {
/* 226 */     List codes = null;
/*     */     try {
/* 228 */       codes = new ArrayList();
/* 229 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));
/*     */ 
/* 231 */       if ((null != one) && (one.size() > 0)) {
/* 232 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 234 */         if ((null != list) && (list.size() > 0)) {
/* 235 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 238 */       return codes;
/*     */     } catch (DaoException e) {
/* 240 */       e.printStackTrace();
/*     */     }
/* 242 */     return codes;
/*     */   }
/*     */ 
/*     */   public void setReturnPlan(ReturnPlan returnPlan)
/*     */   {
/* 251 */     this.returnPlan = returnPlan;
/*     */   }
/*     */ 
/*     */   public ReturnPlan getReturnPlan()
/*     */   {
/* 259 */     return this.returnPlan;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.plan.EditReturnPlanAction
 * JD-Core Version:    0.6.2
 */