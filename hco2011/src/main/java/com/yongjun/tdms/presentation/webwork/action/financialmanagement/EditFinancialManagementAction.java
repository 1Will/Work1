/*     */ package com.yongjun.tdms.presentation.webwork.action.financialmanagement;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
/*     */ import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
/*     */ import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditFinancialManagementAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -8934817289873115007L;
/*     */   private final FinancialManagementManager financialManagementManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final UserManager userManager;
/*     */   private final ReturnPlanManager returnPlanManager;
/*     */   private FinancialManagement financialManagement;
/*  79 */   private List<CodeValue> batchs = new ArrayList();
/*     */ 
/*     */   public EditFinancialManagementAction(FinancialManagementManager financialManagementManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContractManagementManager contractManagementManager, DepartmentManager departmentManager, UserManager userManager, ReturnPlanManager returnPlanManager)
/*     */   {
/* 101 */     this.financialManagementManager = financialManagementManager;
/* 102 */     this.codeValueManager = codeValueManager;
/* 103 */     this.personnelFilesManager = personnelFilesManager;
/* 104 */     this.customerInfoManager = customerInfoManager;
/* 105 */     this.contractManagementManager = contractManagementManager;
/* 106 */     this.departmentManager = departmentManager;
/* 107 */     this.userManager = userManager;
/* 108 */     this.returnPlanManager = returnPlanManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 116 */     if (hasId("financialManagement.id")) {
/* 117 */       this.financialManagement = this.financialManagementManager.loadFinancialManagement(getId("financialManagement.id"));
/*     */ 
/* 119 */       CodeValue c = this.codeValueManager.loadCodeValue(this.financialManagement.getBatch().getId());
/* 120 */       if (null != c)
/* 121 */         this.batchs.add(c);
/*     */     }
/*     */     else {
/* 124 */       this.financialManagement = new FinancialManagement();
/*     */     }
/* 126 */     User user = this.userManager.getUser();
/* 127 */     if (null != user.getCode()) {
/* 128 */       List salemans = this.personnelFilesManager.loadByKey("code", user.getCode());
/* 129 */       if (null != salemans) {
/* 130 */         PersonnelFiles saleman = (PersonnelFiles)salemans.get(0);
/* 131 */         this.financialManagement.setSaleman(saleman);
/* 132 */         this.financialManagement.setPayee(saleman);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 143 */     boolean isNew = this.financialManagement.isNew();
/*     */ 
/* 145 */     if (!StringUtils.isEmpty(this.request.getParameter("payment.id"))) {
/* 146 */       this.financialManagement.setPayment(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("payment.id"))));
/*     */     }
/*     */ 
/* 150 */     if (!StringUtils.isEmpty(this.request.getParameter("collectionType.id"))) {
/* 151 */       this.financialManagement.setCollectionType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("collectionType.id"))));
/*     */     }
/*     */ 
/* 156 */     if (!StringUtils.isEmpty(this.request.getParameter("batch.id"))) {
/* 157 */       this.financialManagement.setBatch(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("batch.id"))));
/*     */     }
/*     */ 
/* 161 */     if (!StringUtils.isEmpty(this.request.getParameter("dept.id"))) {
/* 162 */       Department dept = this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept.id")));
/*     */ 
/* 164 */       if (null != dept) {
/* 165 */         this.financialManagement.setDept(dept);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 170 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 171 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 173 */       if (null != customer) {
/* 174 */         this.financialManagement.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 178 */     if (!StringUtils.isEmpty(this.request.getParameter("payee.id"))) {
/* 179 */       PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("payee.id")));
/*     */ 
/* 181 */       if (null != payee) {
/* 182 */         this.financialManagement.setPayee(payee);
/*     */       }
/*     */     }
/*     */ 
/* 186 */     if (!StringUtils.isEmpty(this.request.getParameter("saleman.id"))) {
/* 187 */       PersonnelFiles saleman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("saleman.id")));
/*     */ 
/* 189 */       if (null != saleman) {
/* 190 */         this.financialManagement.setSaleman(saleman);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 195 */     if (hasId("invoice")) {
/* 196 */       String invoice = this.request.getParameter("invoice");
/* 197 */       this.financialManagement.setInvoice(invoice);
/*     */     }
/*     */ 
/* 201 */     if (!StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
/* 202 */       ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(Long.valueOf(this.request.getParameter("contractManagement.id")));
/*     */ 
/* 205 */       if (null != contractManagement) {
/* 206 */         this.financialManagement.setContractManagement(contractManagement);
/*     */       }
/*     */     }
/*     */ 
/* 210 */     if (isNew) {
/* 211 */       List<FinancialManagement> financialManagements = this.financialManagementManager.loadByKey("contractManagement", this.financialManagement.getContractManagement().getId());
/*     */ 
/* 213 */       if (null != financialManagements) {
/* 214 */         for (FinancialManagement f : financialManagements) {
/* 215 */           f.setTotalSum(f.getTotalSum());
/* 216 */           f.setWithoutGotSum(f.getWithoutGotSum());
/* 217 */           this.financialManagementManager.storeFinancialManagement(f);
/*     */         }
/*     */       }
/* 220 */       String code = autoCompleteCode();
/* 221 */       this.financialManagement.setCode(code);
/* 222 */       this.financialManagementManager.storeFinancialManagement(this.financialManagement);
/* 223 */       addActionMessage(getText("financialManagement.save.success"));
/*     */ 
/* 225 */       ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(this.financialManagement.getContractManagement().getId());
/*     */ 
/* 227 */       if (null != contractManagement) {
/* 228 */         contractManagement.setPaidMoney(this.financialManagement.getTotalSum().doubleValue());
/* 229 */         this.contractManagementManager.storeContractManagement(contractManagement);
/*     */       }
/*     */ 
/* 232 */       String[] keyArray = new String[2];
/* 233 */       Object[] valueArray = new Object[2];
/* 234 */       keyArray[0] = "contractManagement";
/* 235 */       keyArray[1] = "batch";
/* 236 */       valueArray[0] = this.financialManagement.getContractManagement().getId();
/* 237 */       valueArray[1] = this.financialManagement.getBatch();
/* 238 */       List list = this.returnPlanManager.loadByKeyArray(keyArray, valueArray);
/* 239 */       if (null != list) {
/* 240 */         ReturnPlan r = (ReturnPlan)list.get(0);
/*     */ 
/* 242 */         r.setFactSum(this.financialManagement.getTrueSum());
/* 243 */         r.setPaytime(this.financialManagement.getCollectionDate());
/* 244 */         r.setPayee(this.financialManagement.getPayee());
/* 245 */         r.setIsOrNot("0");
/* 246 */         this.returnPlanManager.storeReturnPlan(r);
/*     */       }
/*     */ 
/* 249 */       if (this.financialManagement.getWithoutGotSum().doubleValue() == 0.0D) {
/* 250 */         ContractManagement c = this.financialManagement.getContractManagement();
/* 251 */         if (null != c) {
/* 252 */           c.setOverGet("yes");
/* 253 */           this.contractManagementManager.storeContractManagement(c);
/*     */         }
/*     */ 
/* 256 */         List<ReturnPlan> returnPlans = null;
/* 257 */         if (null != this.financialManagement.getContractManagement()) {
/* 258 */           returnPlans = this.returnPlanManager.loadByKey("contractManagement", this.financialManagement.getContractManagement().getId());
/*     */ 
/* 260 */           if (null != returnPlans) {
/* 261 */             for (ReturnPlan r : returnPlans) {
/* 262 */               if (r.getFactSum().doubleValue() == 0.0D) {
/* 263 */                 r.setFactSum(Double.valueOf(0.0D));
/* 264 */                 r.setPaytime(this.financialManagement.getCollectionDate());
/* 265 */                 r.setPayee(this.financialManagement.getPayee());
/* 266 */                 r.setIsOrNot("0");
/* 267 */                 this.returnPlanManager.storeReturnPlan(r);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 273 */       return "new";
/*     */     }
/* 275 */     List<FinancialManagement> financialManagements = this.financialManagementManager.loadByKey("contractManagement", this.financialManagement.getContractManagement().getId());
/*     */ 
/* 277 */     if (null != financialManagements) {
/* 278 */       for (FinancialManagement f : financialManagements) {
/* 279 */         f.setTotalSum(f.getTotalSum());
/* 280 */         f.setWithoutGotSum(f.getWithoutGotSum());
/* 281 */         this.financialManagementManager.storeFinancialManagement(f);
/*     */       }
/*     */     }
/* 284 */     this.financialManagementManager.storeFinancialManagement(this.financialManagement);
/* 285 */     addActionMessage(getText("financialManagement.edit.success"));
/*     */ 
/* 287 */     ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(this.financialManagement.getContractManagement().getId());
/*     */ 
/* 289 */     contractManagement.setPaidMoney(this.financialManagement.getTotalSum().doubleValue());
/* 290 */     this.contractManagementManager.storeContractManagement(contractManagement);
/*     */ 
/* 292 */     String[] keyArray = new String[2];
/* 293 */     Object[] valueArray = new Object[2];
/* 294 */     keyArray[0] = "contractManagement";
/* 295 */     keyArray[1] = "batch";
/* 296 */     valueArray[0] = this.financialManagement.getContractManagement().getId();
/* 297 */     valueArray[1] = this.financialManagement.getBatch();
/* 298 */     List list = this.returnPlanManager.loadByKeyArray(keyArray, valueArray);
/* 299 */     if (null != list) {
/* 300 */       ReturnPlan r = (ReturnPlan)list.get(0);
/*     */ 
/* 302 */       r.setFactSum(this.financialManagement.getTrueSum());
/* 303 */       r.setPaytime(this.financialManagement.getCollectionDate());
/* 304 */       r.setPayee(this.financialManagement.getPayee());
/* 305 */       r.setIsOrNot("0");
/* 306 */       this.returnPlanManager.storeReturnPlan(r);
/*     */     }
/*     */ 
/* 309 */     if (this.financialManagement.getWithoutGotSum().doubleValue() == 0.0D) {
/* 310 */       List<ReturnPlan> returnPlans = null;
/* 311 */       if (null != this.financialManagement.getContractManagement()) {
/* 312 */         returnPlans = this.returnPlanManager.loadByKey("contractManagement", this.financialManagement.getContractManagement().getId());
/*     */ 
/* 314 */         if (null != returnPlans) {
/* 315 */           for (ReturnPlan r : returnPlans) {
/* 316 */             if (r.getFactSum().doubleValue() == 0.0D) {
/* 317 */               r.setFactSum(Double.valueOf(0.0D));
/* 318 */               r.setPaytime(this.financialManagement.getCollectionDate());
/* 319 */               r.setPayee(this.financialManagement.getPayee());
/* 320 */               r.setIsOrNot("0");
/* 321 */               this.returnPlanManager.storeReturnPlan(r);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 327 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBatchs()
/*     */   {
/* 353 */     return this.batchs;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayments()
/*     */   {
/* 361 */     List codes = null;
/*     */     try {
/* 363 */       codes = new ArrayList();
/* 364 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));
/*     */ 
/* 366 */       if ((null != one) && (one.size() > 0)) {
/* 367 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 369 */         if ((null != list) && (list.size() > 0)) {
/* 370 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 373 */       return codes;
/*     */     } catch (DaoException e) {
/* 375 */       e.printStackTrace();
/*     */     }
/* 377 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllCollectionTypes()
/*     */   {
/* 385 */     List codes = null;
/*     */     try {
/* 387 */       codes = new ArrayList();
/* 388 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("06513"));
/*     */ 
/* 390 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 392 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 394 */         if ((null != list) && (list.size() > 0)) {
/* 395 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 398 */       return codes;
/*     */     } catch (DaoException e) {
/* 400 */       e.printStackTrace();
/*     */     }
/* 402 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 410 */     List codes = this.departmentManager.loadAllDepartments();
/* 411 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 420 */     String maxCode = this.financialManagementManager.getMaxPFCode("SK");
/* 421 */     if (null != maxCode) {
/* 422 */       int num = Integer.parseInt(maxCode) + 1;
/* 423 */       if (num < 10)
/* 424 */         return "SK-00000" + num;
/* 425 */       if (num < 100)
/* 426 */         return "SK-0000" + num;
/* 427 */       if (num < 1000)
/* 428 */         return "SK-000" + num;
/* 429 */       if (num < 10000)
/* 430 */         return "SK-00" + num;
/* 431 */       if (num < 100000) {
/* 432 */         return "SK-0" + num;
/*     */       }
/* 434 */       return "SK-" + num;
/*     */     }
/*     */ 
/* 437 */     return "SK-000001";
/*     */   }
/*     */ 
/*     */   public FinancialManagement getFinancialManagement()
/*     */   {
/* 446 */     return this.financialManagement;
/*     */   }
/*     */ 
/*     */   public void setFinancialManagement(FinancialManagement financialManagement)
/*     */   {
/* 454 */     this.financialManagement = financialManagement;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getBatchs()
/*     */   {
/* 462 */     return this.batchs;
/*     */   }
/*     */ 
/*     */   public void setBatchs(List<CodeValue> batchs)
/*     */   {
/* 471 */     this.batchs = batchs;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.financialmanagement.EditFinancialManagementAction
 * JD-Core Version:    0.6.2
 */