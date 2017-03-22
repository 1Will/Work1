/*     */ package com.yongjun.tdms.presentation.webwork.action.base.org;
/*     */ 
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.model.DomainModel;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.List;
/*     */ 
/*     */ @Deprecated
/*     */ public class ListDepartmentAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -557026634791401535L;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private List<Department> department;
/*     */   private List<Institution> institutions;
/*     */ 
/*     */   public ListDepartmentAction(DepartmentManager departmentManager, InstitutionManager institutionManager)
/*     */   {
/*  44 */     this.departmentManager = departmentManager;
/*  45 */     this.institutionManager = institutionManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */   {
/*  52 */     if ((null == this.department) && (hasIds("departmentIds"))) {
/*  53 */       this.department = this.departmentManager.loadAllDepartments(getIds("departmentIds"));
/*     */     }
/*  55 */     if (hasId("institution.id"))
/*  56 */       setFirst(false);
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  65 */     if (isDisabled()) {
/*  66 */       disabled();
/*     */     }
/*  68 */     if (isEnable()) {
/*  69 */       enabled();
/*     */     }
/*     */ 
/*  72 */     if (isDelete()) {
/*     */       try {
/*  74 */         delete();
/*     */       } catch (Exception e) {
/*  76 */         addActionError(getText("department.delete.failed"));
/*  77 */         return "error";
/*     */       }
/*     */     }
/*  80 */     return "success";
/*     */   }
/*     */ 
/*     */   public void delete()
/*     */   {
/*  87 */     this.departmentManager.deleteAllDepartments(this.department);
/*     */ 
/*  89 */     getLogger().logStore((DomainModel)this.department.get(0), "(部门代码:" + logContentDepartment(this.department) + "的部门)被删除", "department_manager");
/*  90 */     addActionMessage(getText("department.delete.success"));
/*     */   }
/*     */ 
/*     */   private String logContentDepartment(List<Department> department)
/*     */   {
/*  97 */     String delDept = "";
/*  98 */     Integer index = null;
/*  99 */     for (Department dept : department) {
/* 100 */       delDept = delDept + dept.getCode() + ",";
/*     */     }
/* 102 */     index = Integer.valueOf(delDept.lastIndexOf(","));
/* 103 */     delDept = delDept.substring(0, index.intValue());
/* 104 */     return delDept;
/*     */   }
/*     */ 
/*     */   public List<Department> getDepartment()
/*     */   {
/* 111 */     return this.department;
/*     */   }
/*     */ 
/*     */   public void setDepartment(List<Department> department) {
/* 115 */     this.department = department;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 123 */     return "department";
/*     */   }
/*     */ 
/*     */   public List<Department> getParentDepts()
/*     */   {
/* 128 */     List depts = this.departmentManager.createSelectParentGroups(getText("select.option.non"));
/* 129 */     Department dept = new Department();
/* 130 */     dept.setId(Long.valueOf(-1L));
/* 131 */     dept.setName(getText("select.option.all"));
/* 132 */     depts.add(0, dept);
/* 133 */     return depts;
/*     */   }
/*     */ 
/*     */   public List<Institution> getParentInsts()
/*     */   {
/* 143 */     List insts = this.institutionManager.loadAllInstitution(getText("select.option.all"));
/* 144 */     return insts;
/*     */   }
/*     */ 
/*     */   private String disabled() {
/*     */     try {
/* 149 */       this.departmentManager.disabledAllDepartment(this.department);
/* 150 */       addActionMessage(getText("department.disabled.success"));
/* 151 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 153 */       addActionMessage(getText("department.disabled.failer"));
/* 154 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try {
/* 160 */       this.departmentManager.enabledAllDepartment(this.department);
/* 161 */       addActionMessage(getText("department.enabled.success"));
/* 162 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 164 */       addActionMessage(getText("department.enabled.failer"));
/* 165 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<Institution> getInstitutions()
/*     */   {
/* 173 */     return this.institutions;
/*     */   }
/*     */ 
/*     */   public void setInstitutions(List<Institution> institutions)
/*     */   {
/* 180 */     this.institutions = institutions;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.org.ListDepartmentAction
 * JD-Core Version:    0.6.2
 */