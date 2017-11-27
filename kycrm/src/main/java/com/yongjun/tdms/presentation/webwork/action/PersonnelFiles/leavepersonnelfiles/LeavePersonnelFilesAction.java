/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.leavepersonnelfiles;
/*     */ 
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.LeavePersonManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class LeavePersonnelFilesAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private InstitutionManager institutionManager;
/*     */   private UserManager userManager;
/*     */   private DutyManager dutyManager;
/*     */   private LeavePersonManager leavePersonManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private List<PersonnelFiles> pfList;
/*     */   private String sysUser;
/*     */ 
/*     */   public LeavePersonnelFilesAction(InstitutionManager institutionManager, UserManager userManager, DutyManager dutyManager, LeavePersonManager leavePersonManager, DepartmentManager departmentManager)
/*     */   {
/*  79 */     this.institutionManager = institutionManager;
/*  80 */     this.userManager = userManager;
/*  81 */     this.dutyManager = dutyManager;
/*  82 */     this.leavePersonManager = leavePersonManager;
/*  83 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/*  94 */     List list = this.institutionManager.loadAllInstitution();
/*     */ 
/* 100 */     Institution agency = new Institution();
/* 101 */     agency.setId(Long.valueOf(-1L));
/* 102 */     agency.setName(getText("select.option.all"));
/* 103 */     list.add(0, agency);
/* 104 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 114 */     List list = new ArrayList();
/* 115 */     Department agency = new Department();
/* 116 */     agency.setId(Long.valueOf(-1L));
/* 117 */     agency.setName(getText("select.option.all"));
/* 118 */     list.add(0, agency);
/* 119 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 128 */     List list = new ArrayList();
/* 129 */     Duty duty = new Duty();
/* 130 */     duty.setId(Long.valueOf(-1L));
/* 131 */     duty.setName(getText("select.option.all"));
/* 132 */     list.add(0, duty);
/* 133 */     return list;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 142 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 148 */     return "leavePersonnelFiles";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 158 */     Map map = super.getRequestParameterMap();
/* 159 */     if ((null != this.request.getParameter("sysUser")) && (this.request.getParameter("sysUser") != "")) {
/* 160 */       List users = this.userManager.loadValidUsers();
/* 161 */       List codes = new ArrayList();
/* 162 */       for (int i = 0; i < users.size(); i++) {
/* 163 */         if (((User)users.get(i)).getCode() != null) {
/* 164 */           codes.add(((User)users.get(i)).getCode());
/*     */         }
/*     */       }
/* 167 */       if (codes.size() > 0) {
/* 168 */         map.put("validUser", codes);
/*     */       }
/*     */     }
/* 171 */     return map;
/*     */   }
/*     */ 
/*     */   public String getSysUser()
/*     */   {
/* 178 */     return this.sysUser;
/*     */   }
/*     */ 
/*     */   public void setSysUser(String sysUser)
/*     */   {
/* 185 */     this.sysUser = sysUser;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.leavepersonnelfiles.LeavePersonnelFilesAction
 * JD-Core Version:    0.6.2
 */