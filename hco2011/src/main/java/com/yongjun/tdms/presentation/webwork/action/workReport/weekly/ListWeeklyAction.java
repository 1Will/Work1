/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.weekly;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class ListWeeklyAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private DutyManager dutyManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private WeeklyManager weeklyManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private UserManager userManager;
/*     */   private List<Weekly> weeklylist;
/*     */   private String isSuperSys;
/*     */ 
/*     */   public ListWeeklyAction(DutyManager dutyManager, PersonnelFilesManager personnelFilesManager, InstitutionManager institutionManager, WeeklyManager weeklyManager, UserManager userManager, DepartmentManager departmentManager)
/*     */   {
/*  82 */     this.dutyManager = dutyManager;
/*  83 */     this.personnelFilesManager = personnelFilesManager;
/*  84 */     this.institutionManager = institutionManager;
/*  85 */     this.weeklyManager = weeklyManager;
/*  86 */     this.userManager = userManager;
/*  87 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  92 */     if (hasIds("weeklyIds")) {
/*  93 */       this.weeklylist = this.weeklyManager.loadAllWeekly(getIds("weeklyIds"));
/*     */     }
/*     */     else
/*     */     {
/*  97 */       this.weeklylist = new ArrayList();
/*     */     }
/*     */ 
/* 100 */     boolean b = isSuperAdmin();
/* 101 */     if (b)
/* 102 */       this.isSuperSys = "true";
/*     */     else
/* 104 */       this.isSuperSys = "false";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 112 */     System.out.println("execute!!!");
/* 113 */     if (isDisabled()) {
/* 114 */       return disable();
/*     */     }
/* 116 */     if (isEnable()) {
/* 117 */       return enable();
/*     */     }
/* 119 */     if (isDelete()) {
/* 120 */       return delete();
/*     */     }
/* 122 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 127 */     return "weekly";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 153 */     Map map = super.getRequestParameterMap();
/* 154 */     User user = getUser();
/* 155 */     if (user.getName().trim().equals("admin")) {
/* 156 */       return map;
/*     */     }
/*     */ 
/* 159 */     String flag = permission();
/*     */ 
/* 161 */     if (flag.equals("0")) {
/* 162 */       map.put("user.id", user.getId());
/*     */     }
/*     */     else
/*     */     {
/* 166 */       List deptIds = getDeptsByuserId(user);
/* 167 */       if ((null != deptIds) && (!deptIds.isEmpty())) {
/* 168 */         map.put("deptIds", deptIds);
/*     */       } else {
/* 170 */         deptIds = new ArrayList();
/* 171 */         deptIds.add(Long.valueOf(1L));
/* 172 */         map.put("deptIds", deptIds);
/*     */       }
/* 174 */       map.put("deptList", getDeptsByuserId(user));
/*     */     }
/*     */ 
/* 177 */     if (null != this.request.getParameter("dept.id")) {
/* 178 */       Long rdId = Long.valueOf(this.request.getParameter("dept.id"));
/* 179 */       map.put("dept.id", rdId);
/*     */     }
/* 181 */     return map;
/*     */   }
/*     */ 
/*     */   public String permission()
/*     */   {
/* 189 */     String flag = "0";
/* 190 */     if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser())) || (getUser().isPrivilegeUser()) || (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理")))
/*     */     {
/* 194 */       flag = "2";
/* 195 */     } else if (dutyByUser(getUser()).equals("部门经理"))
/* 196 */       flag = "1";
/*     */     else {
/* 198 */       flag = "0";
/*     */     }
/* 200 */     return flag;
/*     */   }
/*     */ 
/*     */   public String dutyByUser(User user)
/*     */   {
/* 209 */     List list = new ArrayList();
/* 210 */     PersonnelFiles personnelFiles = new PersonnelFiles();
/* 211 */     String dutyName = null;
/*     */     try {
/* 213 */       list = this.personnelFilesManager.loadByKey("code", user.getCode());
/*     */     } catch (DaoException e) {
/* 215 */       this.logger.info("查询用户对应的人事档案出错！");
/*     */     }
/* 217 */     if ((null != list) && (list.size() > 0)) {
/* 218 */       personnelFiles = (PersonnelFiles)list.get(0);
/* 219 */       if (null != personnelFiles.getDuty().getPerType()) {
/* 220 */         dutyName = personnelFiles.getDuty().getPerType().getName().trim();
/*     */       }
/*     */     }
/* 223 */     return dutyName;
/*     */   }
/*     */ 
/*     */   public List<Long> getDeptsByuserId(User user)
/*     */   {
/* 232 */     List<Department> deptList = new ArrayList();
/*     */     try {
/* 234 */       deptList = this.departmentManager.getDeptsByUserId(getUser().getId(), "search");
/*     */     }
/*     */     catch (Exception e) {
/* 237 */       this.logger.info("查询当前用户的组织区域出错");
/*     */     }
/* 239 */     deptList.add(user.getDepartment());
/* 240 */     List deptListId = new ArrayList();
/* 241 */     for (Department d : deptList) {
/* 242 */       Long dId = d.getId();
/* 243 */       if (!deptListId.contains(dId)) {
/* 244 */         deptListId.add(dId);
/*     */       }
/*     */     }
/* 247 */     return deptListId;
/*     */   }
/*     */ 
/*     */   public List<Long> getPerDailyIds(User user) {
/* 251 */     Department userDept = user.getDepartment();
/* 252 */     Long deptId = user.getDepartment().getId();
/* 253 */     Set childDeptList = userDept.getChildDepts();
/* 254 */     List<Weekly> dailyList = this.weeklyManager.loadAllWeekly();
/* 255 */     List dailyIds = new ArrayList();
/* 256 */     dailyIds.clear();
/* 257 */     if ((null != dailyList) && (!dailyList.isEmpty())) {
/* 258 */       for (Weekly dObj : dailyList)
/*     */       {
/* 260 */         if (dObj.getDept().getId().equals(deptId)) {
/* 261 */           dailyIds.add(dObj.getId());
/*     */         }
/*     */ 
/* 264 */         if ((childDeptList.contains(dObj.getDept())) && ((dObj.getDuty().getPerType().getName().trim().equals("部门经理")) || (dObj.getDuty().getPerType().getName().trim().equals("公司经理"))))
/*     */         {
/* 267 */           dailyIds.add(dObj.getId());
/*     */         }
/*     */       }
/*     */     }
/* 271 */     if ((null != dailyIds) && (!dailyIds.isEmpty())) {
/* 272 */       return dailyIds;
/*     */     }
/* 274 */     return null;
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 302 */     User user = this.userManager.loadUser(getUser().getId());
/*     */ 
/* 304 */     List agencyList = this.institutionManager.loadAllInstitution();
/*     */ 
/* 311 */     Institution first = new Institution();
/* 312 */     first.setId(Long.valueOf(-1L));
/* 313 */     first.setName(getText("select.option.all"));
/* 314 */     agencyList.add(0, first);
/*     */ 
/* 316 */     return agencyList;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 329 */     List list = new ArrayList();
/* 330 */     Department agency = new Department();
/* 331 */     agency.setId(Long.valueOf(-1L));
/* 332 */     agency.setName(getText("select.option.all"));
/* 333 */     list.add(0, agency);
/* 334 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 346 */     List list = new ArrayList();
/* 347 */     Duty duty = new Duty();
/* 348 */     duty.setId(Long.valueOf(-1L));
/* 349 */     duty.setName(getText("select.option.all"));
/* 350 */     list.add(0, duty);
/* 351 */     return list;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 360 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public String disable() {
/*     */     try {
/* 365 */       this.weeklyManager.disabledWeekly(this.weeklylist);
/*     */     }
/*     */     catch (Exception e) {
/* 368 */       e.printStackTrace();
/*     */     }
/* 370 */     addActionMessage(getText("weekly.disable.success"));
/* 371 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete() {
/*     */     try {
/* 376 */       this.weeklyManager.deleteAllWeekly(this.weeklylist);
/* 377 */       addActionMessage(getText("weekly.delete.success"));
/*     */     }
/*     */     catch (Exception e) {
/* 380 */       e.printStackTrace();
/* 381 */       addActionMessage(getText("weekly.delete.error"));
/*     */     }
/* 383 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable() {
/* 387 */     this.weeklyManager.enabledWeekly(this.weeklylist);
/* 388 */     addActionMessage(getText("weekly.enable.success"));
/* 389 */     return "success";
/*     */   }
/*     */ 
/*     */   public User getLoginUser() {
/* 393 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public boolean isSuperAdmin()
/*     */   {
/* 401 */     String[] keyNames = { "code", "organization.id" };
/* 402 */     Object[] keyValues = { this.userManager.getUser().getCode(), this.userManager.getUser().getOrganization().getId() };
/*     */     try {
/* 404 */       List pf = this.personnelFilesManager.loadByKeyArray(keyNames, keyValues);
/* 405 */       if (null!=pf && pf.size() > 0)
/* 406 */         return false;
/*     */     }
/*     */     catch (DaoException e) {
/* 409 */       e.printStackTrace();
/*     */     }
/* 411 */     return true;
/*     */   }
/*     */ 
/*     */   public String getIsSuperSys()
/*     */   {
/* 420 */     return this.isSuperSys;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.weekly.ListWeeklyAction
 * JD-Core Version:    0.6.2
 */