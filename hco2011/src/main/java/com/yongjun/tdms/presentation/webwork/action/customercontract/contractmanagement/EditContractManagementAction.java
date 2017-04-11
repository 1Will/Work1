/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditContractManagementAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final UserManager userManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final ProductListManager productListManager;
/*     */   private final ProjectInfoManager projectInfoManager;

/* 102 */   private ContractManagement contractManagement = null;
/*     */ 
/* 108 */   private CustomerInfo customerInfo = null;

/* 108 */   private ProjectInfo projectInfo = null;

/*     */ 
/* 112 */   private Institution institution = null;
/*     */ 
/* 116 */   private PersonnelFiles saleman = null;
/*     */ 
/* 120 */   private ContactArchives linkman = null;
/*     */   private Department deparment;
/*     */   private CodeValue contractType;
/*     */   private CodeValue moneyType;
/*     */   private CodeValue payType;
/*     */   private CodeValue state;
			private String openWindowFlag;
/*     */ 
/*     */   public EditContractManagementAction(ContractManagementManager contractManagementManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, DepartmentManager departmentManager, InstitutionManager institutionManager, UserManager userManager, ProductListManager productListManager,ProjectInfoManager projectInfoManager)
/*     */   {
/* 170 */     this.contractManagementManager = contractManagementManager;
/* 171 */     this.customerInfoManager = customerInfoManager;
/* 172 */     this.personnelFilesManager = personnelFilesManager;
/* 173 */     this.contactArchivesManager = contactArchivesManager;
/* 174 */     this.codeValueManager = codeValueManager;
/* 175 */     this.departmentManager = departmentManager;
/* 176 */     this.institutionManager = institutionManager;
/* 177 */     this.userManager = userManager;
/* 178 */     this.productListManager = productListManager;
/* 178 */     this.projectInfoManager = projectInfoManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 186 */     if (null == this.contractManagement) {
/* 187 */       if (hasId("contractManagement.id")) {
/* 188 */         this.contractManagement = this.contractManagementManager.loadContractManagement(getId("contractManagement.id"));
/*     */ 
/* 190 */         DecimalFormat format = new DecimalFormat("0.00");
/* 191 */         this.contractManagement.setContractMoney(new Double(format.format(this.productListManager.getSumTotalPrice(getId("contractManagement.id").longValue()))).doubleValue());
/*     */       }
/*     */       else
/*     */       {
/* 200 */         this.contractManagement = new ContractManagement();
/* 201 */         User user = getUser();
/* 202 */         if (null != user.getCode()) {
/* 203 */           List list = this.personnelFilesManager.loadByKey("code", user.getCode());
/*     */ 
/* 205 */           if (null != list) {
/* 206 */             this.saleman = ((PersonnelFiles)list.get(0));
/* 207 */             this.contractManagement.setSaleman(this.saleman);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 213 */     if (null == this.customerInfo) {
/* 214 */       if (hasId("customerInfo.id")) {
/* 215 */         this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/*     */       }
/*     */       else {
/* 218 */         this.customerInfo = null;
/*     */       }
/*     */     }
/*     */ 
/* 222 */     if (null == this.linkman) {
/* 223 */       if (hasId("linkman.id")) {
/* 224 */         this.linkman = this.contactArchivesManager.loadContactArchives(getId("linkman.id"));
/*     */       }
/*     */       else {
/* 227 */         this.linkman = null;
/*     */       }
/*     */     }

/* 222 */     if (null == this.projectInfo) {
/* 223 */       if (hasId("project.id")) {
/* 224 */         this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("project.id"));
/*     */       }
/*     */       else {
/* 227 */         this.projectInfo = null;
/*     */       }
/*     */     }
/*     */ 
/* 231 */     if (null == this.deparment) {
/* 232 */       if (hasId("deparment.id")) {
/* 233 */         this.deparment = this.departmentManager.loadDepartment(getId("deparment.id"));
/*     */       }
/*     */       else {
/* 236 */         this.deparment = null;
/*     */       }
/*     */     }
/*     */ 
/* 240 */     if (null == this.contractType) {
/* 241 */       if (hasId("contractType.id")) {
/* 242 */         this.contractType = this.codeValueManager.loadCodeValue(getId("contractType.id"));
/*     */       }
/*     */       else {
/* 245 */         this.contractType = null;
/*     */       }
/*     */     }
/* 248 */     if (null == this.moneyType) {
/* 249 */       if (hasId("moneyType.id")) {
/* 250 */         this.moneyType = this.codeValueManager.loadCodeValue(getId("moneyType.id"));
/*     */       }
/*     */       else {
/* 253 */         this.moneyType = null;
/*     */       }
/*     */     }
/* 256 */     if (null == this.payType) {
/* 257 */       if (hasId("payType.id")) {
/* 258 */         this.payType = this.codeValueManager.loadCodeValue(getId("payType.id"));
/*     */       }
/*     */       else {
/* 261 */         this.payType = null;
/*     */       }
/*     */     }
/* 264 */     if (null == this.state) {
/* 265 */       if (hasId("state.id")) {
/* 266 */         this.state = this.codeValueManager.loadCodeValue(getId("state.id"));
/*     */       }
/*     */       else {
/* 269 */         this.state = null;
/*     */       }
/*     */     }
/* 272 */     if (null == this.institution)
/* 273 */       if (hasId("institution.id")) {
/* 274 */         this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
/*     */       }
/*     */       else
/* 277 */         this.institution = null;
			  if(this.request.getParameter("openWindowFlag")!=null){
				  this.openWindowFlag =this.request.getParameter("openWindowFlag");
			  	}
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 287 */     boolean isNew = this.contractManagement.isNew();
/* 288 */     this.contractManagement.setOrganization(this.userManager.getOrganization());
/* 289 */     if (isNew) {
/* 290 */       String code = autoCompleteCode();
/* 291 */       this.contractManagement.setCode(code);
/*     */     }
/*     */ 
/* 294 */     if (hasId("payWay.id")) {
/* 295 */       CodeValue payWay = this.codeValueManager.loadCodeValue(getId("payWay.id"));
/* 296 */       this.contractManagement.setPayWay(payWay);
/*     */     }
/*     */ 
/* 299 */     if (hasId("saleman.id")) {
/* 300 */       this.saleman = this.personnelFilesManager.loadPersonnel(getId("saleman.id"));
/*     */     }
/*     */     try
/*     */     {
/* 304 */       this.contractManagement.setCustomerInfo(this.customerInfo);
/* 305 */       this.contractManagement.setLinkman(this.linkman);
/* 305 */       this.contractManagement.setProject(this.projectInfo);
/* 306 */       this.contractManagement.setContractType(this.contractType);
/* 307 */       this.contractManagement.setSaleman(this.saleman);
/* 308 */       this.contractManagement.setDeparment(this.deparment);
/* 309 */       this.contractManagement.setMoneyType(this.moneyType);
/* 310 */       this.contractManagement.setPayType(this.payType);
/* 311 */       this.contractManagement.setState(this.state);
/* 312 */       this.contractManagement.setInstitution(this.institution);
/* 313 */       this.contractManagement.setDeparment(this.deparment);
/*     */ 
/* 315 */       this.contractManagementManager.storeContractManagement(this.contractManagement);
/*     */ 
/* 317 */       if (isNew) {
/* 318 */         addActionMessage(getText("contractManagement.add.success"));
/* 319 */         return "new";
/*     */       }
/* 321 */       addActionMessage(getText("contractManagement.edit.success"));
/* 322 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 326 */       ex.printStackTrace();
/* 327 */       if (isNew) {
/* 328 */         addActionMessage(getText("contractManagement.add.error"));
/* 329 */         return "new";
/*     */       }
/* 331 */       addActionMessage(getText("contractManagement.edit.error"));
/* 332 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepartment()
/*     */   {
/* 341 */     List depts = this.departmentManager.loadAllDepartments();
/* 342 */     return depts;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelF()
/*     */     throws Exception
/*     */   {
/* 350 */     List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
/*     */ 
/* 352 */     if ((null != pfs) && (pfs.size() > 0)) {
/* 353 */       return (PersonnelFiles)pfs.get(0);
/*     */     }
/* 355 */     return null;
/*     */   }
/*     */ 
/*     */   public String getStateDefault() {
/*     */     try {
/* 360 */       List list = this.codeValueManager.loadByKey("code", "06603");
/* 361 */       if ((null != list) && (list.size() > 0))
/* 362 */         return ((CodeValue)list.get(0)).getId().toString();
/*     */     }
/*     */     catch (DaoException e) {
/* 365 */       e.printStackTrace();
/*     */     }
/* 367 */     return null;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllComplaintType()
/*     */   {
/* 376 */     List codes = null;
/*     */     try {
/* 378 */       codes = new ArrayList();
/* 379 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("064"));
/*     */ 
/* 381 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 383 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 385 */         if ((null != list) && (list.size() > 0)) {
/* 386 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 389 */       return codes;
/*     */     } catch (DaoException e) {
/* 391 */       e.printStackTrace();
/*     */     }
/* 393 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayType()
/*     */   {
/* 401 */     List codes = null;
/*     */     try {
/* 403 */       codes = new ArrayList();
/* 404 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("065"));
/*     */ 
/* 406 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 408 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 410 */         if ((null != list) && (list.size() > 0)) {
/* 411 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 414 */       List reCodeValues = new ArrayList();
/* 415 */       if (codes.size() >= 1) {
/* 416 */         for (int i = 0; i < codes.size(); i++) {
/* 417 */           reCodeValues.add(codes.get(codes.size() - i - 1));
/*     */         }
/*     */       }
/* 420 */       return reCodeValues;
/*     */     } catch (DaoException e) {
/* 422 */       e.printStackTrace();
/*     */     }
/* 424 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllState()
/*     */   {
/* 431 */     List codes = null;
/*     */     try {
/* 433 */       codes = new ArrayList();
/* 434 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("066"));
/*     */ 
/* 436 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 438 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 440 */         if ((null != list) && (list.size() > 0)) {
/* 441 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 444 */       return codes;
/*     */     } catch (DaoException e) {
/* 446 */       e.printStackTrace();
/*     */     }
/* 448 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllMoneyType()
/*     */   {
/* 455 */     List codes = null;
/*     */     try {
/* 457 */       codes = new ArrayList();
/* 458 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("067"));
/*     */ 
/* 460 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 462 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 464 */         if ((null != list) && (list.size() > 0)) {
/* 465 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 468 */       return codes;
/*     */     } catch (DaoException e) {
/* 470 */       e.printStackTrace();
/*     */     }
/* 472 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 480 */     String prefix = "HTGLBM";
/* 481 */     String maxCode = this.contractManagementManager.getMaxPFCode(prefix);
/* 482 */     if (null != maxCode) {
/* 483 */       int num = Integer.parseInt(maxCode) + 1;
/* 484 */       if (num < 10)
/* 485 */         return prefix + "-00000" + num;
/* 486 */       if (num < 100)
/* 487 */         return prefix + "-0000" + num;
/* 488 */       if (num < 1000)
/* 489 */         return prefix + "-000" + num;
/* 490 */       if (num < 10000)
/* 491 */         return prefix + "-00" + num;
/* 492 */       if (num < 100000) {
/* 493 */         return prefix + "-0" + num;
/*     */       }
/* 495 */       return prefix + "-" + num;
/*     */     }
/*     */ 
/* 498 */     return prefix + "-000001";
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 509 */     List list = this.institutionManager.loadAllInstitution();
/* 510 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 519 */     List list = new ArrayList();
/* 520 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayWay()
/*     */   {
/* 527 */     List codes = null;
/*     */     try {
/* 529 */       codes = new ArrayList();
/* 530 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));
/*     */ 
/* 532 */       if ((null != one) && (one.size() > 0)) {
/* 533 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 535 */         if ((null != list) && (list.size() > 0)) {
/* 536 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 539 */       return codes;
/*     */     } catch (DaoException e) {
/* 541 */       e.printStackTrace();
/*     */     }
/* 543 */     return codes;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 552 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public ContractManagement getContractManagement()
/*     */   {
/* 561 */     return this.contractManagement;
/*     */   }
/*     */ 
/*     */   public void setContractManagement(ContractManagement contractManagement)
/*     */   {
/* 568 */     this.contractManagement = contractManagement;
/*     */   }
			public ProjectInfo getProjectInfo() {
				return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}
			public ProjectInfoManager getProjectInfoManager() {
				return projectInfoManager;
			}
			public String getOpenWindowFlag() {
				return openWindowFlag;
			}
			public void setOpenWindowFlag(String openWindowFlag) {
				this.openWindowFlag = openWindowFlag;
			}
			
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.EditContractManagementAction
 * JD-Core Version:    0.6.2
 */