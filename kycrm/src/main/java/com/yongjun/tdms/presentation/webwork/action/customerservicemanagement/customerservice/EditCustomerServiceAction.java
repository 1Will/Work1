/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customerservice;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customerservice.CustomerService;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.customerservice.CustomerServiceManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditCustomerServiceAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CustomerServiceManager customerServiceManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*  75 */   private CustomerService customerService = null;
/*     */ 
/*  79 */   private CustomerInfo customerInfo = null;
/*     */ 
/*  83 */   private PersonnelFiles salesman = null;
/*     */ 
/*  87 */   private ContactArchives linkman = null;
/*     */   private CodeValue serviceType;
/*     */   private CodeValue serviceWay;
/*     */   private CodeValue state;
/*     */ 
/*     */   public EditCustomerServiceAction(CustomerServiceManager customerServiceManager, CustomerInfoManager customerInfoManager, PersonnelFilesManager personnelFilesManager, ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager)
/*     */   {
/* 115 */     this.customerServiceManager = customerServiceManager;
/* 116 */     this.customerInfoManager = customerInfoManager;
/* 117 */     this.personnelFilesManager = personnelFilesManager;
/* 118 */     this.contactArchivesManager = contactArchivesManager;
/* 119 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 127 */     if (null == this.customerService) {
/* 128 */       if (hasId("customerService.id")) {
/* 129 */         this.customerService = this.customerServiceManager.loadCustomerService(getId("customerService.id"));
/*     */       }
/*     */       else {
/* 132 */         this.customerService = new CustomerService();
/*     */       }
/*     */     }
/*     */ 
/* 136 */     if (null == this.customerInfo) {
/* 137 */       if (hasId("customerInfo.id")) {
/* 138 */         this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/*     */       }
/*     */       else {
/* 141 */         this.customerInfo = new CustomerInfo();
/*     */       }
/*     */     }
/*     */ 
/* 145 */     if (null == this.salesman) {
/* 146 */       if (hasId("salesman.id")) {
/* 147 */         this.salesman = this.personnelFilesManager.loadPersonnel(getId("salesman.id"));
/*     */       }
/*     */       else {
/* 150 */         this.salesman = new PersonnelFiles();
/*     */       }
/*     */     }
/*     */ 
/* 154 */     if (null == this.linkman) {
/* 155 */       if (hasId("linkman.id")) {
/* 156 */         this.linkman = this.contactArchivesManager.loadContactArchives(getId("linkman.id"));
/*     */       }
/*     */       else {
/* 159 */         this.linkman = new ContactArchives();
/*     */       }
/*     */     }
/*     */ 
/* 163 */     if (null == this.serviceType) {
/* 164 */       if (hasId("serviceType.id")) {
/* 165 */         this.serviceType = this.codeValueManager.loadCodeValue(getId("serviceType.id"));
/*     */       }
/*     */       else {
/* 168 */         this.serviceType = null;
/*     */       }
/*     */     }
/* 171 */     if (null == this.serviceWay) {
/* 172 */       if (hasId("serviceWay.id")) {
/* 173 */         this.serviceWay = this.codeValueManager.loadCodeValue(getId("serviceWay.id"));
/*     */       }
/*     */       else {
/* 176 */         this.serviceWay = null;
/*     */       }
/*     */     }
/* 179 */     if (null == this.state)
/* 180 */       if (hasId("state.id")) {
/* 181 */         this.state = this.codeValueManager.loadCodeValue(getId("state.id"));
/*     */       }
/*     */       else
/* 184 */         this.state = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 197 */     boolean isNew = this.customerService.isNew();
/* 198 */     if (isNew) {
/* 199 */       String code = autoCompleteCode();
/* 200 */       this.customerService.setCode(code);
/*     */     }
/*     */     try {
/* 203 */       this.customerService.setCustomerInfo(this.customerInfo);
/* 204 */       this.customerService.setLinkman(this.linkman);
/* 205 */       this.customerService.setSalesman(this.salesman);
/* 206 */       this.customerService.setSalesman(this.salesman);
/*     */ 
/* 208 */       this.customerService.setServiceType(this.serviceType);
/* 209 */       this.customerService.setServiceWay(this.serviceWay);
/* 210 */       this.customerService.setState(this.state);
/*     */ 
/* 212 */       this.customerServiceManager.storeCustomerService(this.customerService);
/* 213 */       if (isNew) {
/* 214 */         addActionMessage(getText("customerService.add.success"));
/* 215 */         return "new";
/*     */       }
/* 217 */       addActionMessage(getText("customerService.edit.success"));
/* 218 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 222 */       ex.printStackTrace();
/* 223 */       if (isNew) {
/* 224 */         addActionMessage(getText("customerService.add.error"));
/* 225 */         return "new";
/*     */       }
/* 227 */       addActionMessage(getText("customerService.edit.error"));
/* 228 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllServiceType()
/*     */   {
/* 238 */     List codes = null;
/*     */     try {
/* 240 */       codes = new ArrayList();
/* 241 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055"));
/*     */ 
/* 243 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 245 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 247 */         if ((null != list) && (list.size() > 0)) {
/* 248 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 251 */       return codes;
/*     */     } catch (DaoException e) {
/* 253 */       e.printStackTrace();
/*     */     }
/* 255 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllServiceWay()
/*     */   {
/* 263 */     List codes = null;
/*     */     try {
/* 265 */       codes = new ArrayList();
/* 266 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("056"));
/*     */ 
/* 268 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 270 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 272 */         if ((null != list) && (list.size() > 0)) {
/* 273 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 276 */       return codes;
/*     */     } catch (DaoException e) {
/* 278 */       e.printStackTrace();
/*     */     }
/* 280 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStates()
/*     */   {
/* 288 */     List codes = null;
/*     */     try {
/* 290 */       codes = new ArrayList();
/* 291 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("057"));
/*     */ 
/* 293 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 295 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 297 */         if ((null != list) && (list.size() > 0)) {
/* 298 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 301 */       return codes;
/*     */     } catch (DaoException e) {
/* 303 */       e.printStackTrace();
/*     */     }
/* 305 */     return codes;
/*     */   }
/*     */ 
/*     */   public CustomerService getCustomerService()
/*     */   {
/* 313 */     return this.customerService;
/*     */   }
/*     */ 
/*     */   public void setCustomerService(CustomerService customerService)
/*     */   {
/* 320 */     this.customerService = customerService;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 328 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 335 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public ContactArchives getLinkman()
/*     */   {
/* 343 */     return this.linkman;
/*     */   }
/*     */ 
/*     */   public void setLinkman(ContactArchives linkman)
/*     */   {
/* 350 */     this.linkman = linkman;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSalesman()
/*     */   {
/* 358 */     return this.salesman;
/*     */   }
/*     */ 
/*     */   public void setSalesman(PersonnelFiles salesman)
/*     */   {
/* 365 */     this.salesman = salesman;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 373 */     String maxCode = this.customerServiceManager.getMaxPFCode("KHFW");
/* 374 */     if (null != maxCode) {
/* 375 */       int num = Integer.parseInt(maxCode) + 1;
/* 376 */       if (num < 10)
/* 377 */         return "KHFW--000" + num;
/* 378 */       if (num < 100)
/* 379 */         return "KHFW--00" + num;
/* 380 */       if (num < 1000) {
/* 381 */         return "KHFW--0" + num;
/*     */       }
/* 383 */       return "KHFW--" + num;
/*     */     }
/*     */ 
/* 386 */     return "KHFW--0001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customerservice.EditCustomerServiceAction
 * JD-Core Version:    0.6.2
 */