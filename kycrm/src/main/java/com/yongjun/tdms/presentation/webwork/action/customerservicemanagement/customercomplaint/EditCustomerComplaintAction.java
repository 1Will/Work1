/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customercomplaint;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customercomplaint.CustomerComplaint;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.customercomplaint.CustomerComplaintManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditCustomerComplaintAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CustomerComplaintManager customerComplaintManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final CodeValueManager codeValueManager;
/*  75 */   private CustomerComplaint customerComplaint = null;
/*     */ 
/*  79 */   private CustomerInfo customerInfo = null;
/*     */ 
/*  83 */   private PersonnelFiles salesman = null;
/*     */ 
/*  87 */   private ContactArchives linkman = null;
/*     */   private CodeValue complaintType;
/*     */   private CodeValue urgencyDegree;
/*     */   private CodeValue disposeState;
/*     */   private CodeValue disposeWay;
/*     */   private CodeValue backValidate;
/*     */ 
/*     */   public EditCustomerComplaintAction(CustomerComplaintManager customerComplaintManager, CustomerInfoManager customerInfoManager, PersonnelFilesManager personnelFilesManager, ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager)
/*     */   {
/* 127 */     this.customerComplaintManager = customerComplaintManager;
/* 128 */     this.customerInfoManager = customerInfoManager;
/* 129 */     this.personnelFilesManager = personnelFilesManager;
/* 130 */     this.contactArchivesManager = contactArchivesManager;
/* 131 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 139 */     if (null == this.customerComplaint) {
/* 140 */       if (hasId("customerComplaint.id")) {
/* 141 */         this.customerComplaint = this.customerComplaintManager.loadCustomerComplaint(getId("customerComplaint.id"));
/*     */       }
/*     */       else {
/* 144 */         this.customerComplaint = new CustomerComplaint();
/*     */       }
/*     */     }
/*     */ 
/* 148 */     if (null == this.customerInfo) {
/* 149 */       if (hasId("customerInfo.id")) {
/* 150 */         this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/*     */       }
/*     */       else {
/* 153 */         this.customerInfo = new CustomerInfo();
/*     */       }
/*     */     }
/*     */ 
/* 157 */     if (null == this.salesman) {
/* 158 */       if (hasId("salesman.id")) {
/* 159 */         this.salesman = this.personnelFilesManager.loadPersonnel(getId("salesman.id"));
/*     */       }
/*     */       else {
/* 162 */         this.salesman = new PersonnelFiles();
/*     */       }
/*     */     }
/*     */ 
/* 166 */     if (null == this.linkman) {
/* 167 */       if (hasId("linkman.id")) {
/* 168 */         this.linkman = this.contactArchivesManager.loadContactArchives(getId("linkman.id"));
/*     */       }
/*     */       else {
/* 171 */         this.linkman = new ContactArchives();
/*     */       }
/*     */     }
/*     */ 
/* 175 */     if (null == this.complaintType) {
/* 176 */       if (hasId("complaintType.id")) {
/* 177 */         this.complaintType = this.codeValueManager.loadCodeValue(getId("complaintType.id"));
/*     */       }
/*     */       else {
/* 180 */         this.complaintType = null;
/*     */       }
/*     */     }
/* 183 */     if (null == this.urgencyDegree) {
/* 184 */       if (hasId("urgencyDegree.id")) {
/* 185 */         this.urgencyDegree = this.codeValueManager.loadCodeValue(getId("urgencyDegree.id"));
/*     */       }
/*     */       else {
/* 188 */         this.urgencyDegree = null;
/*     */       }
/*     */     }
/* 191 */     if (null == this.disposeState) {
/* 192 */       if (hasId("disposeState.id")) {
/* 193 */         this.disposeState = this.codeValueManager.loadCodeValue(getId("disposeState.id"));
/*     */       }
/*     */       else {
/* 196 */         this.disposeState = null;
/*     */       }
/*     */     }
/* 199 */     if (null == this.disposeWay) {
/* 200 */       if (hasId("disposeWay.id")) {
/* 201 */         this.disposeWay = this.codeValueManager.loadCodeValue(getId("disposeWay.id"));
/*     */       }
/*     */       else {
/* 204 */         this.disposeWay = null;
/*     */       }
/*     */     }
/* 207 */     if (null == this.backValidate)
/* 208 */       if (hasId("backValidate.id")) {
/* 209 */         this.backValidate = this.codeValueManager.loadCodeValue(getId("backValidate.id"));
/*     */       }
/*     */       else
/* 212 */         this.backValidate = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 224 */     boolean isNew = this.customerComplaint.isNew();
/* 225 */     if (isNew) {
/* 226 */       String code = autoCompleteCode();
/* 227 */       this.customerComplaint.setCode(code);
/*     */     }
/*     */     try {
/* 230 */       this.customerComplaint.setCustomerInfo(this.customerInfo);
/* 231 */       this.customerComplaint.setLinkman(this.linkman);
/* 232 */       this.customerComplaint.setSalesman(this.salesman);
/* 233 */       this.customerComplaint.setSalesman(this.salesman);
/*     */ 
/* 235 */       this.customerComplaint.setBackValidate(this.backValidate);
/* 236 */       this.customerComplaint.setComplaintType(this.complaintType);
/* 237 */       this.customerComplaint.setDisposeState(this.disposeState);
/* 238 */       this.customerComplaint.setDisposeWay(this.disposeWay);
/* 239 */       this.customerComplaint.setUrgencyDegree(this.urgencyDegree);
/*     */ 
/* 241 */       this.customerComplaintManager.storeCustomerComplaint(this.customerComplaint);
/* 242 */       if (isNew) {
/* 243 */         addActionMessage(getText("customerComplaint.add.success"));
/* 244 */         return "new";
/*     */       }
/* 246 */       addActionMessage(getText("customerComplaint.edit.success"));
/* 247 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 251 */       ex.printStackTrace();
/* 252 */       if (isNew) {
/* 253 */         addActionMessage(getText("customerComplaint.add.error"));
/* 254 */         return "new";
/*     */       }
/* 256 */       addActionMessage(getText("customerComplaint.edit.error"));
/* 257 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllComplaintType()
/*     */   {
/* 268 */     List codes = null;
/*     */     try {
/* 270 */       codes = new ArrayList();
/* 271 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("058"));
/*     */ 
/* 273 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 275 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 277 */         if ((null != list) && (list.size() > 0)) {
/* 278 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 281 */       return codes;
/*     */     } catch (DaoException e) {
/* 283 */       e.printStackTrace();
/*     */     }
/* 285 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllUrgencyDegree()
/*     */   {
/* 293 */     List codes = null;
/*     */     try {
/* 295 */       codes = new ArrayList();
/* 296 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("059"));
/*     */ 
/* 298 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 300 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 302 */         if ((null != list) && (list.size() > 0)) {
/* 303 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 306 */       return codes;
/*     */     } catch (DaoException e) {
/* 308 */       e.printStackTrace();
/*     */     }
/* 310 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllDisposeState()
/*     */   {
/* 317 */     List codes = null;
/*     */     try {
/* 319 */       codes = new ArrayList();
/* 320 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("061"));
/*     */ 
/* 322 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 324 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 326 */         if ((null != list) && (list.size() > 0)) {
/* 327 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 330 */       return codes;
/*     */     } catch (DaoException e) {
/* 332 */       e.printStackTrace();
/*     */     }
/* 334 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllDisposeWay()
/*     */   {
/* 341 */     List codes = null;
/*     */     try {
/* 343 */       codes = new ArrayList();
/* 344 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("062"));
/*     */ 
/* 346 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 348 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 350 */         if ((null != list) && (list.size() > 0)) {
/* 351 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 354 */       return codes;
/*     */     } catch (DaoException e) {
/* 356 */       e.printStackTrace();
/*     */     }
/* 358 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackValidate()
/*     */   {
/* 365 */     List codes = null;
/*     */     try {
/* 367 */       codes = new ArrayList();
/* 368 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("063"));
/*     */ 
/* 370 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 372 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 374 */         if ((null != list) && (list.size() > 0)) {
/* 375 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 378 */       return codes;
/*     */     } catch (DaoException e) {
/* 380 */       e.printStackTrace();
/*     */     }
/* 382 */     return codes;
/*     */   }
/*     */ 
/*     */   public CustomerComplaint getCustomerComplaint()
/*     */   {
/* 392 */     return this.customerComplaint;
/*     */   }
/*     */ 
/*     */   public void setCustomerComplaint(CustomerComplaint customerComplaint)
/*     */   {
/* 399 */     this.customerComplaint = customerComplaint;
/*     */   }
/*     */ 
/*     */   public CustomerInfo getCustomerInfo()
/*     */   {
/* 407 */     return this.customerInfo;
/*     */   }
/*     */ 
/*     */   public void setCustomerInfo(CustomerInfo customerInfo)
/*     */   {
/* 414 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ 
/*     */   public ContactArchives getLinkman()
/*     */   {
/* 422 */     return this.linkman;
/*     */   }
/*     */ 
/*     */   public void setLinkman(ContactArchives linkman)
/*     */   {
/* 429 */     this.linkman = linkman;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSalesman()
/*     */   {
/* 437 */     return this.salesman;
/*     */   }
/*     */ 
/*     */   public void setSalesman(PersonnelFiles salesman)
/*     */   {
/* 444 */     this.salesman = salesman;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 452 */     String maxCode = this.customerComplaintManager.getMaxPFCode("KHTS");
/* 453 */     if (null != maxCode) {
/* 454 */       int num = Integer.parseInt(maxCode) + 1;
/* 455 */       if (num < 10)
/* 456 */         return "KHTS--000" + num;
/* 457 */       if (num < 100)
/* 458 */         return "KHTS--00" + num;
/* 459 */       if (num < 1000) {
/* 460 */         return "KHTS--0" + num;
/*     */       }
/* 462 */       return "KHTS--" + num;
/*     */     }
/*     */ 
/* 465 */     return "KHTS--0001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customercomplaint.EditCustomerComplaintAction
 * JD-Core Version:    0.6.2
 */