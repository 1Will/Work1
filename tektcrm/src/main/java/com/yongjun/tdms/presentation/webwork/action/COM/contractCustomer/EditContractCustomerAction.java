/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.contractCustomer;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.model.COM.contractCustomer.ContractCustomer;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.COM.co.CoManager;
/*     */ import com.yongjun.tdms.service.COM.contractCustomer.ContractCustomerManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditContractCustomerAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final ContractCustomerManager contractCustomerManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ProductsManager productsManager;
/*     */   private final CoManager coManager;
/*     */   private ContractCustomer contractCustomer;
/*     */ 
/*     */   public EditContractCustomerAction(ContractCustomerManager contractCustomerManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ProductsManager productsManager, CoManager coManager)
/*     */   {
/*  82 */     this.contractCustomerManager = contractCustomerManager;
/*  83 */     this.codeValueManager = codeValueManager;
/*  84 */     this.personnelFilesManager = personnelFilesManager;
/*  85 */     this.customerInfoManager = customerInfoManager;
/*  86 */     this.productsManager = productsManager;
/*  87 */     this.coManager = coManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  95 */     if (hasId("contractCustomer.id")) {
/*  96 */       this.contractCustomer = this.contractCustomerManager.loadContractCustomer(getId("contractCustomer.id"));
/*     */     }
/*     */     else
/*  99 */       this.contractCustomer = new ContractCustomer();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 109 */     boolean isNew = this.contractCustomer.isNew();
/*     */ 
/* 111 */     if (!StringUtils.isEmpty(this.request.getParameter("payment.id"))) {
/* 112 */       this.contractCustomer.setPayment(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("payment.id"))));
/*     */     }
/*     */ 
/* 117 */     if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
/* 118 */       this.contractCustomer.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("status.id"))));
/*     */     }
/*     */ 
/* 123 */     if (!StringUtils.isEmpty(this.request.getParameter("type.id"))) {
/* 124 */       this.contractCustomer.setType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("type.id"))));
/*     */     }
/*     */ 
/* 128 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 129 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 131 */       if (null != customer) {
/* 132 */         this.contractCustomer.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 136 */     if (!StringUtils.isEmpty(this.request.getParameter("salesman.id"))) {
/* 137 */       PersonnelFiles salesman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("salesman.id")));
/*     */ 
/* 139 */       if (null != salesman) {
/* 140 */         this.contractCustomer.setSaleman(salesman);
/*     */       }
/*     */     }
/*     */ 
/* 144 */     if (!StringUtils.isEmpty(this.request.getParameter("products.id"))) {
/* 145 */       Products products = this.productsManager.loadProducts(Long.valueOf(this.request.getParameter("products.id")));
/*     */ 
/* 147 */       if (null != products) {
/* 148 */         this.contractCustomer.setProducts(products);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 153 */     if (!StringUtils.isEmpty(this.request.getParameter("co.id"))) {
/* 154 */       Co co = this.coManager.loadCo(Long.valueOf(this.request.getParameter("co.id")));
/*     */ 
/* 156 */       if (null != co) {
/* 157 */         this.contractCustomer.setCo(co);
/*     */       }
/*     */     }
/*     */ 
/* 161 */     this.contractCustomerManager.storeContractCustomer(this.contractCustomer);
/* 162 */     if (isNew) {
/* 163 */       String code = autoCompleteCode();
/* 164 */       this.contractCustomer.setCode(code);
/* 165 */       this.contractCustomerManager.storeContractCustomer(this.contractCustomer);
/* 166 */       addActionMessage(getText("contractCustomer.save.success"));
/* 167 */       return "new";
/*     */     }
/* 169 */     this.contractCustomerManager.storeContractCustomer(this.contractCustomer);
/* 170 */     addActionMessage(getText("contractCustomer.edit.success"));
/* 171 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayments()
/*     */   {
/* 181 */     List codes = null;
/*     */     try {
/* 183 */       codes = new ArrayList();
/* 184 */       List one = this.codeValueManager.loadByKey("code", "046");
/*     */ 
/* 186 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 188 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 190 */         if ((null != list) && (list.size() > 0)) {
/* 191 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 194 */       return codes;
/*     */     } catch (DaoException e) {
/* 196 */       e.printStackTrace();
/*     */     }
/* 198 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 206 */     List codes = null;
/*     */     try {
/* 208 */       codes = new ArrayList();
/* 209 */       List one = this.codeValueManager.loadByKey("code","081001");
/*     */ 
/* 211 */       if ((null != one) && (one.size() > 0)) {
/* 212 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 214 */         if ((null != list) && (list.size() > 0)) {
/* 215 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 218 */       return codes;
/*     */     } catch (DaoException e) {
/* 220 */       e.printStackTrace();
/*     */     }
/* 222 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 230 */     List codes = null;
/*     */     try {
/* 232 */       codes = new ArrayList();
/* 233 */       List one = this.codeValueManager.loadByKey("code", "082001");
/*     */ 
/* 235 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 237 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 239 */         if ((null != list) && (list.size() > 0)) {
/* 240 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 243 */       return codes;
/*     */     } catch (DaoException e) {
/* 245 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 248 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 257 */     String maxCode = this.contractCustomerManager.getMaxPFCode("KHHT");
/* 258 */     if (null != maxCode) {
/* 259 */       int num = Integer.parseInt(maxCode) + 1;
/* 260 */       if (num < 10)
/* 261 */         return "KHHT-000" + num;
/* 262 */       if (num < 100)
/* 263 */         return "KHHT-00" + num;
/* 264 */       if (num < 1000) {
/* 265 */         return "KHHT-0" + num;
/*     */       }
/* 267 */       return "KHHT-" + num;
/*     */     }
/*     */ 
/* 270 */     return "KHHT-0001";
/*     */   }
/*     */ 
/*     */   public ContractCustomer getContractCustomer()
/*     */   {
/* 279 */     return this.contractCustomer;
/*     */   }
/*     */ 
/*     */   public void setContractCustomer(ContractCustomer contractCustomer)
/*     */   {
/* 288 */     this.contractCustomer = contractCustomer;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.contractCustomer.EditContractCustomerAction
 * JD-Core Version:    0.6.2
 */