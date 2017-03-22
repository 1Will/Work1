/*     */ package com.yongjun.tdms.presentation.webwork.action.advisory;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.advisory.Advisory;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.advisory.AdvisoryManager;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.util.comparator.CodeValueComparator;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
import java.util.Collections;
/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditAdvisoryAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6722017437417848485L;
/*     */   private final AdvisoryManager advisoryManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final AreaManager areaManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final UserManager userManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private Advisory advisory;
/*     */   private CustomerInfo custormerInfo;
/*     */   private PersonnelFiles personnelFiles;
/*     */   private CodeValue staue;
/*  76 */   private int isAddCustomer = 0;
/*     */ 
/*     */   public EditAdvisoryAction(AdvisoryManager advisoryManager, CodeValueManager codeValueManager, AreaManager areaManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, UserManager userManager, ContactArchivesManager contactArchivesManager)
/*     */   {
/*  87 */     this.advisoryManager = advisoryManager;
/*  88 */     this.codeValueManager = codeValueManager;
/*  89 */     this.areaManager = areaManager;
/*  90 */     this.personnelFilesManager = personnelFilesManager;
/*  91 */     this.customerInfoManager = customerInfoManager;
/*  92 */     this.userManager = userManager;
/*  93 */     this.contactArchivesManager = contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public boolean getIsCustomer()
/*     */   {
/* 100 */     if ((null != this.advisory) && 
/* 101 */       (null != this.advisory.getCustomer())) {
/* 102 */       return true;
/*     */     }
/*     */ 
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 112 */     if (this.advisory == null)
/* 113 */       if (hasId("advisory.id")) {
/* 114 */         this.advisory = this.advisoryManager.loadAdvisory(getId("advisory.id"));
/*     */       } else {
/* 116 */         this.advisory = new Advisory();
/* 117 */         if ((null != this.userManager.getUser().getCode()) && (!"".equals(this.userManager.getUser().getCode()))) {
/* 118 */           this.personnelFiles = ((PersonnelFiles)this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode()).get(0));
/* 119 */           this.advisory.setCustomerServiceName(this.personnelFiles.getName());
/* 120 */           this.advisory.setParlorDept(this.personnelFiles.getDept().getName());
/* 121 */           this.advisory.setCustomerServicePerson(this.personnelFiles);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   private boolean changToCustomer()
/*     */   {
/* 133 */     return hasKey("changToCustomer");
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 142 */     boolean isNew = this.advisory.isNew();
/*     */     try
/*     */     {
/* 145 */       if (!StringUtils.isEmpty(this.request.getParameter("customerType"))) {
/* 146 */         this.advisory.setCustomerType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("customerType"))));
/*     */       }
/*     */ 
/* 150 */       if (!StringUtils.isEmpty(this.request.getParameter("industry"))) {
/* 151 */         this.advisory.setIndustry(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("industry"))));
/*     */       }
/*     */ 
/* 155 */       if (!StringUtils.isEmpty(this.request.getParameter("companyNature"))) {
/* 156 */         this.advisory.setCompanyNature(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("companyNature"))));
/*     */       }
/*     */ 
/* 160 */       if (!StringUtils.isEmpty(this.request.getParameter("advisory.legalPerson"))) {
/* 161 */         this.advisory.setLegalPerson(this.request.getParameter("advisory.legalPerson"));
/*     */       }
/*     */ 
/* 164 */       if (!StringUtils.isEmpty(this.request.getParameter("advisory.dept"))) {
/* 165 */         this.advisory.setDept(this.request.getParameter("advisory.dept"));
/*     */       }
/*     */ 
/* 168 */       if (!StringUtils.isEmpty(this.request.getParameter("advisory.personCount"))) {
/* 169 */         this.advisory.setPersonCount(Integer.valueOf(Integer.parseInt(this.request.getParameter("advisory.personCount"))));
/*     */       }
/*     */ 
/* 172 */       if (!StringUtils.isEmpty(this.request.getParameter("advisory.registeredCapital"))) {
/* 173 */         this.advisory.setRegisteredCapital(Double.valueOf(Double.parseDouble(this.request.getParameter("advisory.registeredCapital"))));
/*     */       }
/*     */ 
/* 176 */       if (!StringUtils.isEmpty(this.request.getParameter("advisory.parlorDept"))) {
/* 177 */         this.advisory.setParlorDept(this.request.getParameter("advisory.parlorDept"));
/*     */       }
/*     */ 
/* 180 */       if (!StringUtils.isEmpty(this.request.getParameter("advisory.duty"))) {
/* 181 */         this.advisory.setDuty(this.request.getParameter("advisory.duty"));
/*     */       }
/*     */ 
/* 184 */       if (!StringUtils.isEmpty(this.request.getParameter("country.id"))) {
/* 185 */         this.advisory.setCountry(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("country.id"))));
/*     */       }
/*     */ 
/* 189 */       if (!StringUtils.isEmpty(this.request.getParameter("province.id"))) {
/* 190 */         this.advisory.setProvince(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("province.id"))));
/*     */       }
/*     */ 
/* 194 */       if ((!StringUtils.isEmpty(this.request.getParameter("city.id"))) && (!this.request.getParameter("city.id").equals("-1"))) {
/* 195 */         this.advisory.setCity(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("city.id"))));
/*     */       }
/*     */ 
/* 199 */       if (!StringUtils.isEmpty(this.request.getParameter("infoSource"))) {
/* 200 */         this.advisory.setInfoSource(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("infoSource"))));
/*     */       }
/*     */ 
/* 204 */       if (!StringUtils.isEmpty(this.request.getParameter("customerService"))) {
/* 205 */         this.personnelFiles = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("customerService")));
/* 206 */         this.advisory.setCustomerServicePerson(this.personnelFiles);
/* 207 */         this.advisory.setCustomerServiceName(this.personnelFiles.getName());
/*     */       }
/*     */ 
/* 210 */       if (!StringUtils.isEmpty(this.request.getParameter("enterpriseSynopsis"))) {
/* 211 */         this.advisory.setEnterpriseSynopsis(this.request.getParameter("enterpriseSynopsis"));
/*     */       }
/*     */ 
/* 214 */       if (!StringUtils.isEmpty(this.request.getParameter("advisoryContent"))) {
/* 215 */         this.advisory.setAdvisoryContent(this.request.getParameter("advisoryContent"));
/*     */       }
/*     */ 
/* 218 */       if (!StringUtils.isEmpty(this.request.getParameter("effectDescribe"))) {
/* 219 */         this.advisory.setEffectDescribe(this.request.getParameter("effectDescribe"));
/*     */       }
/*     */ 
/* 222 */       if (!StringUtils.isEmpty(this.request.getParameter("customerInfoIntegrity"))) {
/* 223 */         this.advisory.setCustomerInfoIntegrity(Float.valueOf(Float.parseFloat(this.request.getParameter("customerInfoIntegrity"))));
/*     */       }
/*     */ 
/* 226 */       if (Integer.parseInt(this.request.getParameter("isNoB")) == 0)
/* 227 */         this.advisory.setIsNoBack(false);
/*     */       else {
/* 229 */         this.advisory.setIsNoBack(true);
/*     */       }
/*     */ 
/* 232 */       if (Integer.parseInt(this.request.getParameter("advisorySex")) == 0)
/* 233 */         this.advisory.setSex(Boolean.valueOf(false).booleanValue());
/*     */       else {
/* 235 */         this.advisory.setSex(Boolean.valueOf(true).booleanValue());
/*     */       }
/*     */ 
/* 238 */       if (hasId("customerId")) {
/* 239 */         this.advisory.setCustomer(this.customerInfoManager.loadCustomerInfo(getId("customerId")));
/* 240 */         CodeValue statue = (CodeValue)this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "023").get(0)).getId()).get(1);
/*     */ 
/* 243 */         this.advisory.setStatue(statue);
/*     */       }
/* 245 */       else if (!StringUtils.isEmpty(this.request.getParameter("advisory.statue")))
/*     */       {
/* 247 */         CodeValue statue = this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("advisory.statue")));
/* 248 */         this.advisory.setStatue(statue);
/*     */       }
/*     */       else
/*     */       {
/* 252 */         CodeValue statue = (CodeValue)this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "023").get(0)).getId()).get(0);
/*     */ 
/* 255 */         this.advisory.setStatue(statue);
/*     */       }
/*     */ 
/* 258 */       if (changToCustomer())
/*     */       {
/* 260 */         saveCustByAdvisory();
/* 261 */         this.advisory.setCustomerType((CodeValue)getAllTypes().get(1));
/* 262 */         this.advisoryManager.storeAdvisory(this.advisory);
/*     */       }
/* 264 */       if (isNew) {
/* 265 */         List li = this.advisoryManager.loadByKey("name", this.advisory.getName());
/*     */ 
/* 267 */         if ((li != null) && 
/* 268 */           (li.size() > 0)) {
/* 269 */           addActionMessage(getText("advisory.add.exist", Arrays.asList(new Object[] { this.advisory.getName() })));
/* 270 */           return "error";
/*     */         }
/*     */       }
/* 273 */       this.advisoryManager.storeAdvisory(this.advisory);
/*     */     } catch (Exception e) {
/* 275 */       addActionMessage(getText("advisory.add.error", Arrays.asList(new Object[] { this.advisory.getName() })));
/* 276 */       e.printStackTrace();
/* 277 */       return "error";
/*     */     }
/* 279 */     if (getIsAddCustomer() == 0)
/*     */     {
/* 281 */       if (isNew) {
/* 282 */         addActionMessage(getText("advisory.add.success", Arrays.asList(new Object[] { this.advisory.getName() })));
/* 283 */         return "new";
/*     */       }
/* 285 */       addActionMessage(getText("advisory.edit.success", Arrays.asList(new Object[] { this.advisory.getName() })));
/* 286 */       return "success";
/*     */     }
/*     */ 
/* 291 */     addActionMessage(getText("advisory1.add.success", Arrays.asList(new Object[] { this.advisory.getName() })));
/* 292 */     return "success";
/*     */   }
/*     */ 
/*     */   public void saveCustByAdvisory()
/*     */   {
/* 302 */     this.custormerInfo = new CustomerInfo();
/* 303 */     ContactArchives contactArchives = new ContactArchives();
/* 304 */     Boolean flag = Boolean.valueOf(false);
/* 305 */     this.custormerInfo.setName(this.advisory.getName());
/* 306 */     this.custormerInfo.setAbbreviations(this.advisory.getShortName());
/*     */     try
/*     */     {
/* 311 */       this.custormerInfo.setCustomerType((CodeValue)getAllTypes().get(1));
/*     */ 
/* 313 */       this.custormerInfo.setStep((CodeValue)this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "022").get(0)).getId()).get(0));
/*     */     }
/*     */     catch (DaoException e1) {
/* 316 */       e1.printStackTrace();
/*     */     }
/* 318 */     this.custormerInfo.setIndustry(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("industry"))));
/*     */ 
/* 320 */     this.custormerInfo.setCompanyNature(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("companyNature"))));
/*     */ 
/* 322 */     this.custormerInfo.setCountry(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("country.id"))));
/*     */ 
/* 324 */     this.custormerInfo.setProvince(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("province.id"))));
/*     */ 
/* 327 */     if (this.request.getParameter("advisorySex").equals("0"))
/* 328 */       this.custormerInfo.setIsOrNot("0");
/*     */     else {
/* 330 */       this.custormerInfo.setIsOrNot("1");
/*     */     }
/* 332 */     this.custormerInfo.setCity(this.advisory.getCity());
/* 333 */     this.custormerInfo.setSalesman(this.personnelFiles);
/* 334 */     this.custormerInfo.setSaleman(this.personnelFiles.getName());
/* 335 */     this.custormerInfo.setKeyContacter(this.advisory.getConnectPeople());
/* 336 */     this.custormerInfo.setMobilePhone(this.advisory.getMobile());
/* 337 */     this.custormerInfo.setFax(this.advisory.getFax());
/* 338 */     this.custormerInfo.setEmail(this.advisory.getEmail());
/* 339 */     this.custormerInfo.setQq(this.advisory.getQq());
/* 340 */     this.custormerInfo.setTelphone(this.advisory.getOfficePhone());
/* 341 */     this.custormerInfo.setDept(this.advisory.getDept());
/* 342 */     this.custormerInfo.setDuty(this.advisory.getDuty());
/* 343 */     this.custormerInfo.setAddress(this.advisory.getAddress());
/* 344 */     this.custormerInfo.setAdvisoryContent(this.advisory.getAdvisoryContent());
/* 345 */     this.custormerInfo.setLegalPerson(this.advisory.getLegalPerson());
/* 346 */     this.custormerInfo.setRegisteredCapital(this.advisory.getRegisteredCapital());
/* 347 */     this.custormerInfo.setPersonCount(this.advisory.getPersonCount());
/* 348 */     this.custormerInfo.setSetupTime(this.advisory.getSetupTime());
/* 349 */     this.custormerInfo.setBusinessScope(this.advisory.getEnterpriseSynopsis());
/* 350 */     this.custormerInfo.setEffectDescribe(this.advisory.getEffectDescribe());
/* 351 */     this.custormerInfo.setParlorDept(this.advisory.getParlorDept());
/*     */     try
/*     */     {
/* 354 */       this.custormerInfo.setFamiliarityType((CodeValue)this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "010").get(0)).getId()).get(1));
/*     */     }
/*     */     catch (DaoException e1) {
/* 357 */       this.logger.info("熟悉程度查找出错！");
/*     */     }
/*     */ 
/* 361 */     this.custormerInfo.setMobilePhone(this.advisory.getMobile());
/*     */     try
/*     */     {
/* 367 */       List cs = this.customerInfoManager.loadByKey("name", this.advisory.getName());
/*     */ 
/* 369 */       if (null != cs)
/*     */       {
/* 371 */         if (cs.size() <= 0)
/*     */         {
/* 374 */           setIsAddCustomer(1);
/* 375 */           this.customerInfoManager.storeCustomerInfo(this.custormerInfo);
/*     */           try
/*     */           {
/* 378 */             String[] keys = new String[2];
/* 379 */             Object[] values = new Object[2];
/* 380 */             keys[0] = "customerInfoCode";
/* 381 */             keys[1] = "name";
/* 382 */             values[0] = this.custormerInfo.getCode();
/* 383 */             values[1] = this.advisory.getConnectPeople();
/* 384 */             List conList = new ArrayList();
/* 385 */             conList = this.contactArchivesManager.loadByKeyArray(keys, values);
/* 386 */             if ((null == conList) || (conList.isEmpty())) {
/* 387 */               contactArchives.setName(this.custormerInfo.getKeyContacter());
/* 388 */               contactArchives.setCustName(this.advisory.getName());
/* 389 */               contactArchives.setCustomerInfoCode(this.custormerInfo.getCode());
/* 390 */               contactArchives.setCustomerType(this.custormerInfo.getCustomerType());
/* 391 */               contactArchives.setIndustry(this.custormerInfo.getIndustry().getName().toString());
/* 392 */               contactArchives.setPhone(this.custormerInfo.getTelphone());
/* 393 */               contactArchives.setMobilePhone(this.custormerInfo.getMobilePhone());
/* 394 */               contactArchives.setFax(this.custormerInfo.getFax());
/* 395 */               contactArchives.setEmail(this.custormerInfo.getEmail());
/* 396 */               contactArchives.setQq(this.custormerInfo.getQq());
/* 397 */               contactArchives.setDept(this.advisory.getDept());
/* 398 */               contactArchives.setDuty(this.advisory.getDuty());
/* 399 */               CodeValue o = (CodeValue)this.codeValueManager.loadByKey("code", "0102").get(0);
/* 400 */               contactArchives.setType(o);
/*     */ 
/* 402 */               if (this.request.getParameter("advisorySex").equals("0"))
/* 403 */                 contactArchives.setSex(false);
/*     */               else {
/* 405 */                 contactArchives.setSex(true);
/*     */               }
/* 407 */               contactArchives.setType(this.custormerInfo.getFamiliarityType());
/* 408 */               flag = Boolean.valueOf(true);
/*     */             }
/*     */           } catch (Exception e) {
/* 411 */             this.logger.info("联系人添加出错");
/*     */           }
/* 413 */           if (flag.booleanValue()) {
/* 414 */             contactArchives.setCustomerName(this.custormerInfo);
/* 415 */             this.contactArchivesManager.storeContactArchives(contactArchives);
/*     */           }
/* 417 */           this.advisory.setCustomer(this.custormerInfo);
/*     */         }
/*     */         else
/*     */         {
/* 421 */           setIsAddCustomer(1);
/* 422 */           this.advisory.setCustomer((CustomerInfo)cs.get(0));
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 427 */         setIsAddCustomer(1);
/* 428 */         this.customerInfoManager.storeCustomerInfo(this.custormerInfo);
/*     */         try
/*     */         {
/* 431 */           String[] keys = new String[2];
/* 432 */           Object[] values = new Object[2];
/* 433 */           keys[0] = "customerInfoCode";
/* 434 */           keys[1] = "name";
/* 435 */           values[0] = this.custormerInfo.getCode();
/* 436 */           values[1] = this.advisory.getConnectPeople();
/* 437 */           List conList = new ArrayList();
/* 438 */           conList = this.contactArchivesManager.loadByKeyArray(keys, values);
/* 439 */           if ((null == conList) || (conList.isEmpty())) {
/* 440 */             contactArchives.setName(this.custormerInfo.getKeyContacter());
/* 441 */             contactArchives.setCustName(this.advisory.getName());
/* 442 */             contactArchives.setCustomerInfoCode(this.custormerInfo.getCode());
/* 443 */             contactArchives.setCustomerType(this.custormerInfo.getCustomerType());
/* 444 */             contactArchives.setIndustry(this.custormerInfo.getIndustry().getName().toString());
/* 445 */             contactArchives.setPhone(this.custormerInfo.getTelphone());
/* 446 */             contactArchives.setMobilePhone(this.custormerInfo.getMobilePhone());
/* 447 */             contactArchives.setFax(this.custormerInfo.getFax());
/* 448 */             contactArchives.setEmail(this.custormerInfo.getEmail());
/* 449 */             contactArchives.setQq(this.custormerInfo.getQq());
/* 450 */             contactArchives.setDept(this.advisory.getDept());
/* 451 */             contactArchives.setDuty(this.advisory.getDuty());
/* 452 */             CodeValue o = (CodeValue)this.codeValueManager.loadByKey("code", "0102").get(0);
/* 453 */             contactArchives.setType(o);
/*     */ 
/* 455 */             if (this.request.getParameter("advisorySex").equals("0"))
/* 456 */               contactArchives.setSex(false);
/*     */             else {
/* 458 */               contactArchives.setSex(true);
/*     */             }
/* 460 */             contactArchives.setType(this.custormerInfo.getFamiliarityType());
/* 461 */             flag = Boolean.valueOf(true);
/*     */           }
/*     */         } catch (Exception e) {
/* 464 */           this.logger.info("联系人添加出错");
/*     */         }
/* 466 */         if (flag.booleanValue()) {
/* 467 */           contactArchives.setCustomerName(this.custormerInfo);
/* 468 */           this.contactArchivesManager.storeContactArchives(contactArchives);
/*     */         }
/* 470 */         CodeValue statue = (CodeValue)this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "023").get(0)).getId()).get(1);
/*     */ 
/* 473 */         this.advisory.setStatue(statue);
/* 474 */         this.advisory.setCustomer(this.custormerInfo);
/*     */       }
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 479 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllCustTypes()
/*     */   {
/*     */     try
/*     */     {
/* 490 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "001").get(0);
/*     */ 
/* 492 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/*     */ 
/* 494 */       if (list != null) {
/* 495 */         return list;
/*     */       }
/* 497 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 501 */       e.printStackTrace();
/* 502 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllIndustrys()
/*     */   {
/*     */     try
/*     */     {
/* 514 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "002").get(0);
/*     */ 
/* 516 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/* 517 */       if (list != null) {
/* 518 */         return list;
/*     */       }
			Collections.sort(list, new CodeValueComparator());
/* 520 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 524 */       e.printStackTrace();
/* 525 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllEnterNature()
/*     */   {
/*     */     try
/*     */     {
/* 537 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "003").get(0);
/*     */ 
/* 539 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/* 540 */       if (list != null) {
/* 541 */         return list;
/*     */       }
/* 543 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 547 */       e.printStackTrace();
/* 548 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllInfoOriginId()
/*     */   {
/*     */     try
/*     */     {
/* 560 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "006").get(0);
/*     */ 
/* 562 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/* 563 */       if (list != null) {
/* 564 */         return list;
/*     */       }
/* 566 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 570 */       e.printStackTrace();
/* 571 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllCountry()
/*     */   {
/*     */     try
/*     */     {
/* 583 */       List list = this.areaManager.loadByKey("type", "country");
/* 584 */       if (list != null) {
/* 585 */         return list;
/*     */       }
/* 587 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 591 */       e.printStackTrace();
/* 592 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllProvince()
/*     */   {
/* 603 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List getAllCity()
/*     */   {
/* 612 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List getAllAdvisoryStatues()
/*     */   {
/*     */     try
/*     */     {
/* 624 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "023").get(0);
/*     */ 
/* 626 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/* 627 */       if (list != null) {
/* 628 */         return list;
/*     */       }
/* 630 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 636 */       e.printStackTrace();
/* 637 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Advisory getAdvisory()
/*     */   {
/* 643 */     return this.advisory;
/*     */   }
/*     */ 
/*     */   public void setAdvisory(Advisory advisory) {
/* 647 */     this.advisory = advisory;
/*     */   }
/*     */   public int getIsAddCustomer() {
/* 650 */     return this.isAddCustomer;
/*     */   }
/*     */   public void setIsAddCustomer(int isAddCustomer) {
/* 653 */     this.isAddCustomer = isAddCustomer;
/*     */   }
/*     */ 
/*     */   public CodeValue getStaue()
/*     */   {
/* 659 */     return this.staue;
/*     */   }
/*     */ 
/*     */   public void setStaue(CodeValue staue)
/*     */   {
/* 665 */     this.staue = staue;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllSteps()
/*     */   {
/*     */     try
/*     */     {
/* 674 */       List codes = new ArrayList();
/* 675 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
/* 676 */       if ((null != one) && (one.size() > 0)) {
/* 677 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(1)).getId());
/* 678 */         if ((null != list) && (list.size() > 0)) {
/* 679 */           codes.addAll(list);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 686 */       return codes;
/*     */     } catch (DaoException e) {
/* 688 */       e.printStackTrace();
/* 689 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/*     */     try
/*     */     {
/* 704 */       List codes = new ArrayList();
/* 705 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
/* 706 */       if ((null != one) && (one.size() > 0)) {
/* 707 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 708 */         if ((null != list) && (list.size() > 0)) {
/* 709 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 712 */       return codes;
/*     */     } catch (DaoException e) {
/* 714 */       e.printStackTrace();
/* 715 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.advisory.EditAdvisoryAction
 * JD-Core Version:    0.6.2
 */