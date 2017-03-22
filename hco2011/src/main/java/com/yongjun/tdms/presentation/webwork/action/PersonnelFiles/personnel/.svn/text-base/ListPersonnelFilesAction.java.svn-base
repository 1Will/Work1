/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel;
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
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListPersonnelFilesAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private InstitutionManager institutionManager;
/*     */   private UserManager userManager;
/*     */   private DutyManager dutyManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private List<PersonnelFiles> pfList;
			private String backVisitCheckBox;
/*     */   private String sysUser;
/*     */ 
/*     */   public ListPersonnelFilesAction(InstitutionManager institutionManager, UserManager userManager, DutyManager dutyManager, PersonnelFilesManager personnelFilesManager, DepartmentManager departmentManager)
/*     */   {
/*  78 */     this.institutionManager = institutionManager;
/*  79 */     this.userManager = userManager;
/*  80 */     this.dutyManager = dutyManager;
/*  81 */     this.personnelFilesManager = personnelFilesManager;
/*  82 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  87 */     if (isDisabled()) {
/*  88 */       return disable();
/*     */     }
/*  90 */     if (isEnable()) {
/*  91 */       return enable();
/*     */     }
/*  93 */     if (isDelete()) {
/*  94 */       return delete();
/*     */     }
/*  96 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/* 101 */     if (hasIds("personnelFileIds")) {
/* 102 */       this.pfList = this.personnelFilesManager.loadAllPersonnel(getIds("personnelFileIds"));
/*     */     }
/*     */ 
/* 106 */     if (hasId("sysUser"))
/* 107 */       this.sysUser = this.request.getParameter("sysUser");
			if (this.request.getParameter("backVisitCheckBox") != null) {
/*  99 */       this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 118 */     List list = this.institutionManager.loadAllInstitution();
/*     */ 
/* 124 */     Institution agency = new Institution();
/* 125 */     agency.setId(Long.valueOf(-1L));
/* 126 */     agency.setName(getText("select.option.all"));
/* 127 */     list.add(0, agency);
/* 128 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 138 */     List list = new ArrayList();
/* 139 */     Department agency = new Department();
/* 140 */     agency.setId(Long.valueOf(-1L));
/* 141 */     agency.setName(getText("select.option.all"));
/* 142 */     list.add(0, agency);
/* 143 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 152 */     List list = new ArrayList();
/* 153 */     Duty duty = new Duty();
/* 154 */     duty.setId(Long.valueOf(-1L));
/* 155 */     duty.setName(getText("select.option.all"));
/* 156 */     list.add(0, duty);
/* 157 */     return list;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 166 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public String disable() {
/* 170 */     this.personnelFilesManager.disabledPersonnels(this.pfList);
/* 171 */     addActionMessage(getText("personnel.disable.success"));
/* 172 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete() {
/*     */     try {
/* 177 */       this.personnelFilesManager.deleteAllPersonnel(this.pfList);
/* 178 */       addActionMessage(getText("personnel.delete.success"));
/*     */     } catch (Exception e) {
/* 180 */       addActionMessage(getText("personnel.delete.error"));
/*     */     }
/* 182 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable() {
/* 186 */     this.personnelFilesManager.enabledPersonnels(this.pfList);
/* 187 */     addActionMessage(getText("personnel.enable.success"));
/* 188 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 193 */     return "personnelFiles";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 203 */     Map map = super.getRequestParameterMap();
/* 204 */     if ((null != this.request.getParameter("sysUser")) && (this.request.getParameter("sysUser") != "")) {
/* 205 */       List users = this.userManager.loadValidUsers();
/* 206 */       List codes = new ArrayList();
/* 207 */       for (int i = 0; i < users.size(); i++) {
/* 208 */         if (((User)users.get(i)).getCode() != null) {
/* 209 */           codes.add(((User)users.get(i)).getCode());
/*     */         }
/*     */       }
/* 212 */       if (codes.size() > 0) {
/* 213 */         map.put("validUser", codes);
/*     */       }
/*     */     }
/* 216 */     return map;
/*     */   }
/*     */ 
/*     */   public String getSysUser()
/*     */   {
/* 223 */     return this.sysUser;
/*     */   }
/*     */ 
/*     */   public void setSysUser(String sysUser)
/*     */   {
/* 230 */     this.sysUser = sysUser;
/*     */   }
			public String getBackVisitCheckBox() {
				return backVisitCheckBox;
			}
			public void setBackVisitCheckBox(String backVisitCheckBox) {
				this.backVisitCheckBox = backVisitCheckBox;
			}
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel.ListPersonnelFilesAction
 * JD-Core Version:    0.6.2
 */