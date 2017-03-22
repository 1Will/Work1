/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.daily;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.daily.DailyManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class ListDailyAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final DailyManager dailyManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Daily> dailys;
/*     */   private Long orgId;
/*     */   private User loginUser;
/*     */   private String isSuperSys;
/*     */ 
/*     */   public ListDailyAction(DailyManager dailyManager, UserManager userManager, PersonnelFilesManager personnelFilesManager, InstitutionManager institutionManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/*  98 */     this.dailyManager = dailyManager;
/*  99 */     this.userManager = userManager;
/* 100 */     this.personnelFilesManager = personnelFilesManager;
/* 101 */     this.institutionManager = institutionManager;
/* 102 */     this.departmentManager = departmentManager;
/* 103 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 112 */     return "daily";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 120 */     Map map = super.getRequestParameterMap();
/* 121 */     User user = getUser();
/* 122 */     if (user.getName().trim().equals("admin")) {
/* 123 */       return map;
/*     */     }
/*     */ 
/* 126 */     String flag = permission();
/*     */ 
/* 128 */     if (flag.equals("0")) {
/* 129 */       map.put("user.id", user.getId());
/*     */     }
/*     */     else
/*     */     {
/* 133 */       List dailyIds = getPerDailyIds(user);
/* 134 */       if ((null != dailyIds) && (!dailyIds.isEmpty())) {
/* 135 */         map.put("dailyIds", dailyIds);
/*     */       } else {
/* 137 */         dailyIds = new ArrayList();
/* 138 */         dailyIds.add(Long.valueOf(1L));
/* 139 */         map.put("dailyIds", dailyIds);
/*     */       }
/* 141 */       map.put("deptList", getDeptsByuserId(user));
/*     */     }
/*     */ 
/* 144 */     if (null != this.request.getParameter("daily.id")) {
/* 145 */       Long rdId = Long.valueOf(this.request.getParameter("daily.id"));
/* 146 */       map.put("daily.id", rdId);
/*     */     }
/* 148 */     return map;
/*     */   }
/*     */ 
/*     */   public List<Long> getPerDailyIds(User user)
/*     */   {
/* 156 */     Department userDept = user.getDepartment();
/* 157 */     Long deptId = user.getDepartment().getId();
/* 158 */     Set childDeptList = userDept.getChildDepts();
/* 159 */     List<Daily> dailyList = this.dailyManager.loadAllDaily();
/* 160 */     List dailyIds = new ArrayList();
/* 161 */     dailyIds.clear();
/* 162 */     if ((null != dailyList) && (!dailyList.isEmpty())) {
/* 163 */       for (Daily dObj : dailyList)
/*     */       {
/* 165 */         if (dObj.getDept().getId().equals(deptId)) {
/* 166 */           dailyIds.add(dObj.getId());
/*     */         }
/*     */ 
/* 169 */         if ((childDeptList.contains(dObj.getDept())) && ((dObj.getDuty().getPerType().getName().trim().equals("部门经理")) || (dObj.getDuty().getPerType().getName().trim().equals("公司经理"))))
/*     */         {
/* 172 */           dailyIds.add(dObj.getId());
/*     */         }
/*     */       }
/*     */     }
/* 176 */     if ((null != dailyIds) && (!dailyIds.isEmpty())) {
/* 177 */       return dailyIds;
/*     */     }
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */   public String permission()
/*     */   {
/* 190 */     String flag = "0";
/* 191 */     if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser())) || (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理")))
/*     */     {
/* 195 */       flag = "2";
/* 196 */     } else if (dutyByUser(getUser()).equals("部门经理"))
/* 197 */       flag = "1";
/*     */     else {
/* 199 */       flag = "0";
/*     */     }
/* 201 */     return flag;
/*     */   }
/*     */ 
/*     */   public String dutyByUser(User user)
/*     */   {
/* 210 */     List list = new ArrayList();
/* 211 */     PersonnelFiles personnelFiles = new PersonnelFiles();
/* 212 */     String dutyName = null;
/*     */     try {
/* 214 */       list = this.personnelFilesManager.loadByKey("code", user.getCode());
/*     */     } catch (DaoException e) {
/* 216 */       this.logger.info("查询用户对应的人事档案出错！");
/*     */     }
/* 218 */     if ((null != list) && (list.size() > 0)) {
/* 219 */       personnelFiles = (PersonnelFiles)list.get(0);
/* 220 */       dutyName = personnelFiles.getDuty().getPerType().getName().trim();
/*     */     }
/* 222 */     return dutyName;
/*     */   }
/*     */ 
/*     */   public List<Long> getDeptsByuserId(User user)
/*     */   {
/* 231 */     List<Department> deptList = new ArrayList();
/*     */     try {
/* 233 */       deptList = this.departmentManager.getDeptsByUserId(getUser().getId(), "search");
/*     */     }
/*     */     catch (Exception e) {
/* 236 */       this.logger.info("查询当前用户的组织区域出错");
/*     */     }
/* 238 */     deptList.add(user.getDepartment());
/* 239 */     List deptListId = new ArrayList();
/* 240 */     for (Department d : deptList) {
/* 241 */       Long dId = d.getId();
/* 242 */       if (!deptListId.contains(dId)) {
/* 243 */         deptListId.add(dId);
/*     */       }
/*     */     }
/* 246 */     return deptListId;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 256 */     this.orgId = this.userManager.getUser().getOrganization().getId();
/*     */ 
/* 258 */     if (hasIds("dailyIds")) {
/* 259 */       this.dailys = this.dailyManager.loadAllDaily(getIds("dailyIds"));
/*     */     }
/*     */ 
/* 263 */     if (this.request.getParameter("weekly.id") != null) {
/* 264 */       setFirst(false);
/*     */     }
/*     */ 
/* 267 */     boolean b = isSuperManager();
/* 268 */     if (b)
/* 269 */       this.isSuperSys = "true";
/*     */     else
/* 271 */       this.isSuperSys = "false";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 283 */       this.dailyManager.deleteAllDaily(this.dailys);
/* 284 */       addActionMessage(getText("daily.delete.success"));
/*     */     } catch (Exception e) {
/* 286 */       addActionError(getText("daily.delete.error"));
/*     */     }
/* 288 */     return "success";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 296 */     if (isDelete()) {
/* 297 */       return delete();
/*     */     }
/* 299 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 325 */     List agencyList = this.institutionManager.loadAllInstitution();
/*     */ 
/* 332 */     Institution first = new Institution();
/* 333 */     first.setId(Long.valueOf(-1L));
/* 334 */     first.setName(getText("select.option.all"));
/* 335 */     agencyList.add(0, first);
/*     */ 
/* 337 */     return agencyList;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 348 */     List list = new ArrayList();
/* 349 */     Department agency = new Department();
/* 350 */     agency.setId(Long.valueOf(-1L));
/* 351 */     agency.setName(getText("select.option.all"));
/* 352 */     list.add(0, agency);
/* 353 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 362 */     List list = new ArrayList();
/* 363 */     Duty duty = new Duty();
/* 364 */     duty.setId(Long.valueOf(-1L));
/* 365 */     duty.setName(getText("select.option.all"));
/* 366 */     list.add(0, duty);
/* 367 */     return list;
/*     */   }
/*     */ 
/*     */   public boolean isSuperManager()
/*     */   {
/* 376 */     String[] keyNames = { "code", "organization.id" };
/* 377 */     Object[] keyValues = { this.userManager.getUser().getCode(), this.userManager.getUser().getOrganization().getId() };
/*     */     try {
/* 379 */       List pf = this.personnelFilesManager.loadByKeyArray(keyNames, keyValues);
/* 380 */       if (pf.size() > 0)
/* 381 */         return false;
/*     */     }
/*     */     catch (DaoException e) {
/* 384 */       e.printStackTrace();
/*     */     }
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllReportType()
/*     */   {
/* 395 */     List reportTypeList = new ArrayList();
/*     */     try {
/* 397 */       String[] keyNames1 = { "code", "disabled" };
/* 398 */       Object[] keyValues1 = { String.valueOf("049"), Boolean.valueOf(false) };
/* 399 */       List reportType = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 401 */       if (reportType != null) {
/* 402 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 403 */         Object[] keyValues2 = { ((CodeValue)reportType.get(0)).getId(), Boolean.valueOf(false) };
/* 404 */         reportTypeList = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 408 */       return new ArrayList();
/*     */     }
/* 410 */     CodeValue cv = new CodeValue();
/* 411 */     cv.setId(Long.valueOf(-1L));
/* 412 */     cv.setName(getText("select.option.all"));
/* 413 */     reportTypeList.add(0, cv);
/* 414 */     return reportTypeList;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 422 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public Long getOrgId()
/*     */   {
/* 430 */     return this.orgId;
/*     */   }
/*     */ 
/*     */   public void setOrgId(Long orgId)
/*     */   {
/* 438 */     this.orgId = orgId;
/*     */   }
/*     */ 
/*     */   public User getLoginUser()
/*     */   {
/* 447 */     this.loginUser = this.userManager.getUser();
/* 448 */     return this.loginUser;
/*     */   }
/*     */ 
/*     */   public void setLoginUser(User u)
/*     */   {
/* 456 */     this.loginUser = u;
/*     */   }
/*     */ 
/*     */   public String getRapporteur()
/*     */   {
/* 462 */     return this.request.getParameter("rapporteur.name");
/*     */   }
/*     */ 
/*     */   public String getIsSuperSys()
/*     */   {
/* 469 */     return this.isSuperSys;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.daily.ListDailyAction
 * JD-Core Version:    0.6.2
 */