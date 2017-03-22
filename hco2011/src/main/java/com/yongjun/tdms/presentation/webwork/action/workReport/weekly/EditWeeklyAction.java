/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.weekly;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditWeeklyAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final DutyManager dutyManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final WeeklyManager weeklyManager;
/*     */   private Weekly weekly;
/*  70 */   private boolean first = false;
/*     */   private String isSuperSys;
/*     */   private String perType;
/*     */ 
/*     */   public EditWeeklyAction(PersonnelFilesManager personnelFilesManager, InstitutionManager institutionManager, UserManager userManager, DutyManager dutyManager, WeeklyManager weeklyManager, DepartmentManager departmentManager)
/*     */   {
/*  85 */     this.personnelFilesManager = personnelFilesManager;
/*  86 */     this.institutionManager = institutionManager;
/*  87 */     this.userManager = userManager;
/*  88 */     this.dutyManager = dutyManager;
/*  89 */     this.weeklyManager = weeklyManager;
/*  90 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  95 */     return super.execute();
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  99 */     if (hasId("weekly.id")) {
/* 100 */       this.weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));
/*     */     }
/*     */     else
/*     */     {
/* 104 */       this.weekly = new Weekly();
/* 105 */       this.first = true;
/*     */     }
/*     */ 
/* 108 */     boolean b = isSuperAdmin();
/* 109 */     if (b) {
/* 110 */       this.isSuperSys = "true";
/*     */     }
/*     */     else {
/* 113 */       this.isSuperSys = "false";
/*     */     }
/* 115 */     this.perType = permission();
/*     */   }
/*     */ 
/*     */   public String save() {
/* 119 */     boolean isNew = this.weekly.isNew();
/*     */ 
/* 122 */     if (hasId("personId")) {
/* 123 */       this.weekly.setRapporteur(this.userManager.loadUser(getId("personId")));
/*     */     }
/*     */ 
/* 127 */     if (hasId("inst.id")) {
/* 128 */       this.weekly.setInst(this.institutionManager.loadInstitution(getId("inst.id")));
/*     */     }
/*     */ 
/* 132 */     if (hasId("dept.id")) {
/* 133 */       this.weekly.setDept(this.departmentManager.loadDepartment(getId("dept.id")));
/*     */     }
/*     */ 
/* 137 */     if (hasId("duty.id")) {
/* 138 */       this.weekly.setDuty(this.dutyManager.loadDuty(getId("duty.id")));
/*     */     }
/*     */ 
/* 143 */     this.weekly.setOrganization(this.userManager.getOrganization());
/*     */     try {
/* 145 */       if (isNew)
/*     */       {
/* 147 */         String newCode = autoCompleteCode();
/* 148 */         this.weekly.setCode(newCode);
/*     */ 
/* 150 */         Calendar c = Calendar.getInstance();
/* 151 */         String[] months = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
/*     */ 
/* 153 */         String[] weeks = { "一", "二", "三", "四", "五" };
/* 154 */         this.weekly.setName(months[c.get(2)] + "月第" + weeks[(c.get(4) - 1)] + "周");
/*     */       }
/*     */ 
/* 157 */       this.weeklyManager.storeWeekly(this.weekly);
/* 158 */       if (isNew) {
/* 159 */         addActionMessage(getText("weekly.add.success", Arrays.asList(new Object[] { this.weekly.getCode() })));
/*     */ 
/* 161 */         return "new";
/*     */       }
/*     */ 
/* 164 */       addActionMessage(getText("weekly.edit.success", Arrays.asList(new Object[] { this.weekly.getCode() })));
/*     */ 
/* 166 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 170 */       e.printStackTrace();
/* 171 */       if (isNew) {
/* 172 */         addActionMessage(getText("weekly.add.error", Arrays.asList(new Object[] { this.weekly.getName() })));
/*     */       }
/*     */       else
/*     */       {
/* 176 */         addActionMessage(getText("weekly.edit.error", Arrays.asList(new Object[] { this.weekly.getCode() })));
/*     */       }
/*     */     }
/* 179 */     return "error";
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 189 */     User user = this.userManager.loadUser(getUser().getId());
/*     */ 
/* 191 */     List agencyList = this.institutionManager.loadAllInstitution();
/* 192 */     Institution first = new Institution();
/* 193 */     first.setId(Long.valueOf(-1L));
/* 194 */     first.setName(getText(""));
/* 195 */     agencyList.add(0, first);
/* 196 */     return agencyList;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 208 */     List list = new ArrayList();
/* 209 */     Department agency = new Department();
/* 210 */     agency.setId(Long.valueOf(-1L));
/* 211 */     agency.setName(getText(""));
/* 212 */     list.add(0, agency);
/* 213 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 225 */     List list = new ArrayList();
/* 226 */     Duty duty = new Duty();
/* 227 */     duty.setId(Long.valueOf(-1L));
/* 228 */     duty.setName(getText(""));
/* 229 */     list.add(0, duty);
/* 230 */     return list;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 239 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPeronnelF()
/*     */     throws DaoException
/*     */   {
/* 247 */     List list = this.personnelFilesManager.loadByKey("code", getUser().getCode().trim());
/* 248 */     if ((null != list) && (list.size() > 0)) {
/* 249 */       return (PersonnelFiles)list.get(0);
/*     */     }
/* 251 */     return new PersonnelFiles();
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 260 */     Calendar time = Calendar.getInstance();
/* 261 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
/* 262 */     String currentDate = sdf.format(time.getTime());
/* 263 */     String maxCode = this.weeklyManager.getMaxWeeklyCode(this.userManager.getUser().getLoginName() + "_" + currentDate, this.userManager.getUser().getId(), this.userManager.getOrganization().getId());
/*     */ 
/* 267 */     if (null != maxCode) {
/* 268 */       int num = Integer.parseInt(maxCode) + 1;
/* 269 */       return this.userManager.getUser().getLoginName() + "_" + currentDate + "_0" + num;
/*     */     }
/*     */ 
/* 273 */     return this.userManager.getUser().getLoginName() + "_" + currentDate + "_" + "01";
/*     */   }
/*     */ 
/*     */   public boolean isSuperAdmin()
/*     */   {
/* 284 */     boolean flag = false;
/* 285 */     String[] keyNames = { "code", "organization.id" };
/* 286 */     Object[] keyValues = { this.userManager.getUser().getCode(), this.userManager.getUser().getOrganization().getId() };
/*     */     try
/*     */     {
/* 290 */       List pf = this.personnelFilesManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/* 292 */       if (pf.size() > 0)
/* 293 */         flag = false;
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 297 */       e.printStackTrace();
/*     */     }
/* 299 */     return flag;
/*     */   }
/*     */ 
/*     */   public String permission()
/*     */   {
/* 308 */     String flag = "0";
/* 309 */     boolean isNew = this.weekly.isNew();
/*     */ 
/* 311 */     if (isNew)
/* 312 */       flag = "0";
/* 313 */     else if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser())) || (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理")))
/*     */     {
/* 317 */       flag = "2";
/* 318 */     } else if (dutyByUser(getUser()).equals("部门经理"))
/*     */     {
/* 320 */       if ((null != this.weekly) && (null != this.weekly.getRapporteur()) && (this.weekly.getRapporteur().getId().equals(getUser().getId())))
/*     */       {
/* 322 */         flag = "0";
/*     */       }
/* 324 */       else flag = "1";
/*     */     }
/*     */     else {
/* 327 */       flag = "0";
/*     */     }
/* 329 */     return flag;
/*     */   }
/*     */ 
/*     */   public String dutyByUser(User user)
/*     */   {
/* 337 */     List list = new ArrayList();
/* 338 */     PersonnelFiles personnelFiles = new PersonnelFiles();
/* 339 */     String dutyName = null;
/*     */     try {
/* 341 */       list = this.personnelFilesManager.loadByKey("code", user.getCode());
/*     */     } catch (DaoException e) {
/* 343 */       this.logger.info("查询用户对应的人事档案出错！");
/*     */     }
/* 345 */     if ((null != list) && (list.size() > 0)) {
/* 346 */       personnelFiles = (PersonnelFiles)list.get(0);
/* 347 */       dutyName = personnelFiles.getDuty().getPerType().getName().trim();
/*     */     }
/* 349 */     return dutyName;
/*     */   }
/*     */ 
/*     */   public Weekly getWeekly() {
/* 353 */     return this.weekly;
/*     */   }
/*     */ 
/*     */   public void setWeekly(Weekly weekly) {
/* 357 */     this.weekly = weekly;
/*     */   }
/*     */ 
/*     */   public boolean isFirst() {
/* 361 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(boolean first) {
/* 365 */     this.first = first;
/*     */   }
/*     */ 
/*     */   public String getIsSuperSys()
/*     */   {
/* 373 */     return this.isSuperSys;
/*     */   }
/*     */ 
/*     */   public String getPerType()
/*     */   {
/* 381 */     return this.perType;
/*     */   }
/*     */ 
/*     */   public void setPerType(String perType)
/*     */   {
/* 389 */     this.perType = perType;
/*     */   }
/*     */ 
/*     */   public void setIsSuperSys(String isSuperSys)
/*     */   {
/* 397 */     this.isSuperSys = isSuperSys;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.weekly.EditWeeklyAction
 * JD-Core Version:    0.6.2
 */