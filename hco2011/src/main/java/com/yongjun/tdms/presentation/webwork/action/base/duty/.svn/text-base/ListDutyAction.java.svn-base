/*     */ package com.yongjun.tdms.presentation.webwork.action.base.duty;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.jobs.JobsManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListDutyAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final DutyManager dutyManager;
/*     */   private final JobsManager jobsManager;
/*     */   private final UserManager userManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private List<Duty> duties;
/*     */   private Long departmentId;
/*     */   private Long jobNameId;
/*     */   private String organizationName;
/*     */ 
/*     */   public ListDutyAction(DutyManager dutyManager, JobsManager jobsManager, DepartmentManager departmentManager, UserManager userManager)
/*     */   {
/*  76 */     this.dutyManager = dutyManager;
/*  77 */     this.jobsManager = jobsManager;
/*  78 */     this.departmentManager = departmentManager;
/*  79 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  88 */     return "duties";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  93 */     Map map = super.getRequestParameterMap();
/*     */ 
/*  95 */     if (getDepartmentId() != null) {
/*  96 */       map.put("dept.id", getDepartmentId());
/*  97 */       if (!hasId("onlyInvalid")) {
/*  98 */         map.put("onlyValid", Boolean.valueOf(false));
/*     */       }
/*     */     }
/* 101 */     map.put("orgId", this.userManager.getUser().getId());
/* 102 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 111 */     if (hasIds("dutyIds")) {
/* 112 */       this.duties = this.dutyManager.loadAllDuty(getIds("dutyIds"));
/*     */     }
/*     */ 
/* 116 */     if (getDepartmentId() != null)
/* 117 */       setFirst(false);
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 126 */     if (isDisabled()) {
/* 127 */       return disabled();
/*     */     }
/* 129 */     if (isEnable()) {
/* 130 */       return enabled();
/*     */     }
/* 132 */     if (isDelete()) {
/* 133 */       return delete();
/*     */     }
/* 135 */     return "success";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 144 */       this.dutyManager.enabledAllChecks(this.duties);
/* 145 */       addActionMessage(getText("enabled.duties.success"));
/*     */     } catch (Exception e) {
/* 147 */       addActionMessage(getText("enabled.duties.error"));
/*     */     }
/* 149 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 159 */       this.dutyManager.disabledAllDuties(this.duties);
/* 160 */       addActionMessage(getText("disabled.duties.success"));
/*     */     } catch (Exception e) {
/* 162 */       addActionMessage(getText("disabled.duties.error"));
/*     */     }
/* 164 */     return "success";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 173 */       this.dutyManager.deleteAllDuty(this.duties);
/* 174 */       addActionMessage(getText("delete.duties.success"));
/*     */     } catch (Exception e) {
/* 176 */       addActionMessage(getText("delete.duties.error"));
/*     */     }
/* 178 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Jobs> getAllJobNames()
/*     */   {
/* 187 */     Long organizationId = this.userManager.getOrganization().getId();
/* 188 */     String[] keyNames = { "organization.id" };
/* 189 */     Object[] keyValues = { organizationId };
/*     */     try {
/* 191 */       List jobs = this.jobsManager.loadByKeyArray(keyNames, keyValues);
/* 192 */       if (jobs == null) {
/* 193 */         jobs = new ArrayList();
/*     */       }
/* 195 */       Jobs job = new Jobs();
/* 196 */       job.setId(Long.valueOf(-1L));
/* 197 */       job.setName(getText("select.option.all"));
/* 198 */       jobs.add(0, job);
/* 199 */       return jobs;
/*     */     } catch (DaoException e) {
/* 201 */       e.printStackTrace();
/* 202 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 212 */     return this.departmentManager.loadAllDepartments();
/*     */   }
/*     */ 
/*     */   public Long getDepartmentId()
/*     */   {
/* 221 */     if (hasId("dept.id")) {
/* 222 */       return getId("dept.id");
/*     */     }
/* 224 */     return null;
/*     */   }
/*     */   public Long getJobNameId() {
/* 227 */     if (hasId("jobName.id")) {
/* 228 */       return getId("jobName.id");
/*     */     }
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */   public String getOrganizationName()
/*     */   {
/* 238 */     return this.userManager.getUser().getOrganization().getName();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.duty.ListDutyAction
 * JD-Core Version:    0.6.2
 */