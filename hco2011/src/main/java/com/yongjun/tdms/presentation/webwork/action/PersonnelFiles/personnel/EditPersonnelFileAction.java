/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.model.security.UserType;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles.LeavePerson;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.LeavePersonManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;

/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditPersonnelFileAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6543880456612721423L;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final DutyManager dutyManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final LeavePersonManager leavePersonManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final AreaManager areaManager;
/*     */   private PersonnelFiles personnelFile;
/*     */   private boolean sex;
/*  90 */   private boolean first = true;
/*     */ 
/*     */   public EditPersonnelFileAction(PersonnelFilesManager personnelFilesManager, UserManager userManager, InstitutionManager institutionManager, DutyManager dutyManager, CodeValueManager codeValueManager, AreaManager areaManager, DepartmentManager departmentManager, LeavePersonManager leavePersonManager)
/*     */   {
/* 107 */     this.personnelFilesManager = personnelFilesManager;
/* 108 */     this.userManager = userManager;
/* 109 */     this.institutionManager = institutionManager;
/* 110 */     this.dutyManager = dutyManager;
/* 111 */     this.codeValueManager = codeValueManager;
/* 112 */     this.areaManager = areaManager;
/* 113 */     this.departmentManager = departmentManager;
/* 114 */     this.leavePersonManager = leavePersonManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 123 */     if (hasId("personnelFile.id")) {
/* 124 */       this.personnelFile = this.personnelFilesManager.loadPersonnel(getId("personnelFile.id"));
/*     */ 
/* 126 */       this.sex = this.personnelFile.isSex();
/* 127 */       setFirst(false);
/*     */     } else {
/* 129 */       this.personnelFile = new PersonnelFiles();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 135 */     boolean isNew = this.personnelFile.isNew();
/*     */ 
/* 137 */     if (hasId("inst.id")) {
/* 138 */       this.personnelFile.setInst(this.institutionManager.loadInstitution(getId("inst.id")));
/*     */     }
/*     */ 
/* 141 */     if (hasId("dept.id")) {
/* 142 */       this.personnelFile.setDept(this.departmentManager.loadDepartment(getId("dept.id")));
/*     */     }
/*     */ 
/* 145 */     if (hasId("duty.id")) {
/* 146 */       this.personnelFile.setDuty(this.dutyManager.loadDuty(getId("duty.id")));
/*     */     }
/*     */ 
/* 149 */     if (hasId("personnelFile.birthplace")) {
/* 150 */       this.personnelFile.setBirthplace(this.areaManager.loadArea(getId("personnelFile.birthplace")));
/*     */     }
/*     */ 
/* 154 */     if (hasId("personnelFile.politice")) {
/* 155 */       this.personnelFile.setPolitice(this.codeValueManager.loadCodeValue(getId("personnelFile.politice")));
/*     */     }
/*     */ 
/* 159 */     if (hasId("personnelFile.status")) {
/* 160 */       this.personnelFile.setStatus(this.codeValueManager.loadCodeValue(getId("personnelFile.status")));
/*     */     }
/*     */ 
/* 164 */     if ((null != this.request.getParameter("sex")) && ("" != this.request.getParameter("sex")))
/*     */     {
/* 166 */       this.sex = Boolean.valueOf(this.request.getParameter("sex")).booleanValue();
/* 167 */       this.personnelFile.setSex(this.sex);
/*     */     }
/*     */ 
/* 170 */     if (hasId("personnelFile.marriage")) {
/* 171 */       this.personnelFile.setMarriage(this.codeValueManager.loadCodeValue(getId("personnelFile.marriage")));
/*     */     }
/*     */ 
/* 175 */     if (hasId("personnelFile.national")) {
/* 176 */       this.personnelFile.setNational(this.codeValueManager.loadCodeValue(getId("personnelFile.national")));
/*     */     }
/*     */ 
/* 180 */     if (hasId("personnelFile.education")) {
/* 181 */       this.personnelFile.setEducation(this.codeValueManager.loadCodeValue(getId("personnelFile.education")));
/*     */     }
/*     */ 
/* 185 */     if (hasId("personnelFile.workway")) {
/* 186 */       this.personnelFile.setWorkway(this.codeValueManager.loadCodeValue(getId("personnelFile.workway")));
/*     */     }
				if (hasId("businessType.id")) {
/* 186 */       this.personnelFile.setBusinessType(this.codeValueManager.loadCodeValue(getId("businessType.id")));
/*     */     }
/* 185 */     if (hasId("personnelFile.superiorLeader")) {
/* 186 */       this.personnelFile.setSuperiorLeader(this.personnelFilesManager.loadPersonnel(getId("personnelFile.superiorLeader")));
/*     */     }
/*     */ 
/* 190 */     this.personnelFile.setOrganization(this.userManager.getOrganization());
/*     */     try
/*     */     {
/* 201 */       if (isNew) {
/* 202 */         String newCode = autoCompleteCode();
/* 203 */         this.personnelFile.setCode(newCode);
/* 204 */         this.personnelFilesManager.storePersonnel(this.personnelFile);
/*     */ 
/* 208 */         addActionMessage(getText("personnel.add.success", Arrays.asList(new Object[] { this.personnelFile.getCode() })));
/*     */ 
/* 211 */         return "success";
/*     */       }
/* 213 */       this.personnelFilesManager.storePersonnel(this.personnelFile);
/*     */ 
/* 218 */       if (this.personnelFile.getStatus().getId().longValue() == 177L) {
/* 219 */         List lpList = new ArrayList();
/* 220 */         LeavePerson person = null;
/*     */         try {
/* 222 */           String[] keyNames = new String[1];
/* 223 */           Object[] keyValues = new Object[1];
/* 224 */           keyNames[0] = "code";
/* 225 */           keyValues[0] = this.personnelFile.getCode().trim();
/* 226 */           lpList = this.leavePersonManager.loadByKeyArray(keyNames, keyValues);
/*     */         } catch (Exception e) {
/* 228 */           System.out.println("error search leavePerson!");
/*     */         }
/* 230 */         if ((null == lpList) || (lpList.isEmpty())) {
/* 231 */           person = new LeavePerson();
/* 232 */           person.setCode(this.personnelFile.getCode());
/* 233 */           person.setName(this.personnelFile.getName());
/* 234 */           person.setFileNo(this.personnelFile.getFileNo());
/* 235 */           person.setInst(this.personnelFile.getInst());
/* 236 */           person.setDept(this.personnelFile.getDept());
/* 237 */           person.setDuty(this.personnelFile.getDuty());
/* 238 */           person.setMobile(this.personnelFile.getMobile());
/* 239 */           person.setTelphone(this.personnelFile.getTelphone());
/* 240 */           person.setStatus(this.personnelFile.getStatus());
/* 241 */           Date now = new Date();
/* 242 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 243 */           now = sdf.parse(sdf.format(now));
/* 244 */           person.setLeaveDate(now);
/* 245 */           this.leavePersonManager.storePersonnel(person);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 250 */       addActionMessage(getText("personnel.edit.success", Arrays.asList(new Object[] { this.personnelFile.getCode() })));
/*     */ 
/* 253 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 257 */       e.printStackTrace();
/* 258 */       addActionMessage(getText("personnel.add.exists", Arrays.asList(new Object[] { this.personnelFile.getCode() })));
/*     */     }
/* 260 */     return "error";
/*     */   }
/*     */ 
/*     */   public void updateUser()
/*     */   {
/* 267 */     List userList = new ArrayList();
/* 268 */     User user = null;
/* 269 */     boolean isNew = false;
/*     */     try {
/* 271 */       userList = this.userManager.loadByKey("code", this.personnelFile.getCode().trim());
/*     */     } catch (Exception e) {
/* 273 */       this.logger.info("查询对应的用户出错！");
/*     */     }
/* 275 */     if ((null != userList) && (!userList.isEmpty())) {
/* 276 */       user = (User)userList.get(0);
/* 277 */       isNew = false;
/*     */     } else {
/* 279 */       user = new User();
/* 280 */       isNew = true;
/* 281 */       user.setUserType(UserType.SYSTEM_USER);
/* 282 */       user.setCode(this.personnelFile.getCode());
/*     */     }
/*     */ 
/* 285 */     user.setName(this.personnelFile.getName());
/* 286 */     user.setOrganization(getUser().getOrganization());
/* 287 */     user.setInstitustion(this.personnelFile.getInst());
/* 288 */     user.setDepartment(this.personnelFile.getDept());
/* 289 */     user.setTelphoneNumber(this.personnelFile.getMobile());
/* 290 */     user.setBrith(this.personnelFile.getBirthday());
/* 291 */     user.setEmail(this.personnelFile.getEmail());
/* 292 */     if (isNew)
/* 293 */       this.userManager.changePassword(user, "1111");
/*     */     else
/* 295 */       this.userManager.storeUser(user);
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 304 */     String maxCode = this.personnelFilesManager.getMaxPFCode("yg", this.userManager.getOrganization().getId());
/*     */ 
/* 306 */     if (null != maxCode) {
/* 307 */       int num = Integer.parseInt(maxCode) + 1;
/* 308 */       if (num < 10)
/* 309 */         return "yg-00" + num;
/* 310 */       if (num < 100) {
/* 311 */         return "yg-0" + num;
/*     */       }
/* 313 */       return "yg-" + num;
/*     */     }
/*     */ 
/* 317 */     return "yg-001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllMarriage()
/*     */   {
/*     */     try
/*     */     {
/* 330 */       Long orgId = this.userManager.getOrganization().getId();
/* 331 */       String[] keyNames1 = { "code", "disabled" };
/* 332 */       Object[] keyValues1 = { "011", Boolean.valueOf(false) };
/* 333 */       List marriage = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 335 */       if (marriage != null) {
/* 336 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 337 */         Object[] keyValues2 = { ((CodeValue)marriage.get(0)).getId(), Boolean.valueOf(false) };
/* 338 */         List marriages = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 340 */         if (marriages != null)
/* 341 */           return marriages;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 345 */       e.printStackTrace();
/* 346 */       return new ArrayList();
/*     */     }
/* 348 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<Area> getAllBirthplace()
/*     */   {
/*     */     try
/*     */     {
/* 360 */       List areas = new ArrayList();
/* 361 */       String[] keyArray = new String[2];
/* 362 */       Object[] valueArray = new Object[2];
/*     */ 
/* 364 */       keyArray[0] = "type";
/* 365 */       keyArray[1] = "parentArea";
/*     */ 
/* 367 */       valueArray[0] = "province";
/* 368 */       valueArray[1] = Integer.valueOf(43);
/*     */ 
/* 370 */       List list = this.areaManager.loadByKeyArray(keyArray, valueArray);
/* 371 */       if ((null != list) && (list.size() > 0)) {
/* 372 */         areas.addAll(list);
/*     */       }
/* 374 */       return areas;
/*     */     } catch (Exception e) {
/* 376 */       e.printStackTrace();
/* 377 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNational()
/*     */   {
/*     */     try
/*     */     {
/* 390 */       String[] keyNames1 = { "code", "disabled" };
/* 391 */       Object[] keyValues1 = { "004", Boolean.valueOf(false) };
/* 392 */       List nationality = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 394 */       if (nationality != null) {
/* 395 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 396 */         Object[] keyValues2 = { ((CodeValue)nationality.get(0)).getId(), Boolean.valueOf(false) };
/*     */ 
/* 398 */         List nationalitys = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 400 */         if (nationalitys != null)
/* 401 */           return nationalitys;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 405 */       e.printStackTrace();
/* 406 */       return new ArrayList();
/*     */     }
/* 408 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPolitice()
/*     */   {
/*     */     try
/*     */     {
/* 419 */       Long orgId = this.userManager.getOrganization().getId();
/* 420 */       String[] keyNames1 = { "code", "disabled" };
/* 421 */       Object[] keyValues1 = { "005", Boolean.valueOf(false) };
/* 422 */       List politice = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 424 */       if (politice != null) {
/* 425 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 426 */         Object[] keyValues2 = { ((CodeValue)politice.get(0)).getId(), Boolean.valueOf(false) };
/* 427 */         List politices = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 429 */         if (politices != null)
/* 430 */           return politices;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 434 */       e.printStackTrace();
/* 435 */       return new ArrayList();
/*     */     }
/* 437 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllEducation()
/*     */   {
/*     */     try
/*     */     {
/* 449 */       String[] keyNames1 = { "code", "disabled" };
/* 450 */       Object[] keyValues1 = { "017", Boolean.valueOf(false) };
/* 451 */       List education = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 453 */       if (education != null) {
/* 454 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 455 */         Object[] keyValues2 = { ((CodeValue)education.get(0)).getId(), Boolean.valueOf(false) };
/* 456 */         List educations = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 458 */         if (educations != null)
/* 459 */           return educations;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 463 */       e.printStackTrace();
/* 464 */       return new ArrayList();
/*     */     }
/* 466 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllWorkway()
/*     */   {
/*     */     try
/*     */     {
/* 477 */       Long orgId = this.userManager.getOrganization().getId();
/* 478 */       String[] keyNames1 = { "code", "disabled" };
/* 479 */       Object[] keyValues1 = { "015", Boolean.valueOf(false) };
/* 480 */       List workmode = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 482 */       if (workmode != null) {
/* 483 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 484 */         Object[] keyValues2 = { ((CodeValue)workmode.get(0)).getId(), Boolean.valueOf(false) };
/* 485 */         List workmodes = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 487 */         if (workmodes != null)
/* 488 */           return workmodes;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 492 */       e.printStackTrace();
/* 493 */       return new ArrayList();
/*     */     }
/* 495 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/*     */     try
/*     */     {
/* 506 */       Long orgId = this.userManager.getOrganization().getId();
/* 507 */       String[] keyNames1 = { "code", "disabled" };
/* 508 */       Object[] keyValues1 = { "012", Boolean.valueOf(false) };
/* 509 */       List workmode = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 511 */       if (workmode != null) {
/* 512 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 513 */         Object[] keyValues2 = { ((CodeValue)workmode.get(0)).getId(), Boolean.valueOf(false) };
/* 514 */         List workmodes = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 516 */         if (workmodes != null)
/* 517 */           return workmodes;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 521 */       e.printStackTrace();
/* 522 */       return new ArrayList();
/*     */     }
/* 524 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFile()
/*     */   {
/* 533 */     return this.personnelFile;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFile(PersonnelFiles personnelFile) {
/* 537 */     this.personnelFile = personnelFile;
/*     */   }
			public List<CodeValue> getAllBusinessType() {
				try {
					List companyNatures = new ArrayList();
		String[] keys={"code","name"};
		String[] values={"210","客户属性"};
		List one =this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code", Long.valueOf("210"));
		
		if ((null != one) && (one.size() > 0)) {
			List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
			if ((null != list) && (list.size() > 0)) {
				companyNatures.addAll(list);
			}
		}
		return companyNatures;
	} catch (Exception e) {
		e.printStackTrace();
		return new ArrayList();
	}
}
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 546 */     List list = this.institutionManager.loadAllInstitution();
/* 547 */     Institution agency = new Institution();
/* 548 */     agency.setId(Long.valueOf(-1L));
/* 549 */     agency.setName(getText(""));
/* 550 */     list.add(0, agency);
/* 551 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 561 */     List list = new ArrayList();
/* 562 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 571 */     List list = new ArrayList();
/* 572 */     return list;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 581 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public boolean isFirst()
/*     */   {
/* 589 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(boolean first)
/*     */   {
/* 597 */     this.first = first;
/*     */   }
/*     */ 
/*     */   public boolean isSex()
/*     */   {
/* 604 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(boolean sex)
/*     */   {
/* 612 */     this.sex = sex;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel.EditPersonnelFileAction
 * JD-Core Version:    0.6.2
 */