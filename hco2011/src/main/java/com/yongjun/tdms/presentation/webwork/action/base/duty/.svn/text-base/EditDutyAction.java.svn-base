/*     */ package com.yongjun.tdms.presentation.webwork.action.base.duty;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.jobs.JobsManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditDutyAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -5962205322448741405L;
/*     */   private final DutyManager dutyManager;
/*     */   private final JobsManager jobsManager;
/*     */   private final UserManager userManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private Duty duty;
/*     */   private Long departmentId;
/*     */   private Long deptId;
/*     */ 
/*     */   public EditDutyAction(DutyManager dutyManager, DepartmentManager departmentManager, JobsManager jobsManager, UserManager userManager, CodeValueManager codeValueManager)
/*     */   {
/*  76 */     this.dutyManager = dutyManager;
/*  77 */     this.departmentManager = departmentManager;
/*  78 */     this.jobsManager = jobsManager;
/*  79 */     this.userManager = userManager;
/*  80 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  87 */     if ((this.duty == null) && (hasId("duty.id"))) {
/*  88 */       this.duty = this.dutyManager.loadDuty(getId("duty.id"));
/*     */     }
/*     */     else
/*  91 */       this.duty = new Duty();
/*     */   }
/*     */ 
/*     */   public List<Jobs> getAllJobNames()
/*     */   {
/*     */     try
/*     */     {
/* 104 */       List jobs = this.jobsManager.loadByKey("disabled", Boolean.valueOf(false));
/* 105 */       if (jobs == null);
/* 106 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 110 */       e.printStackTrace();
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 124 */     List depts = this.departmentManager.loadAllDepartments();
/* 125 */     if (depts == null) {
/* 126 */       depts = new ArrayList();
/*     */     }
/* 128 */     return depts;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 139 */     boolean isNew = this.duty.isNew();
/*     */ 
/* 141 */     if (!StringUtils.isEmpty(this.request.getParameter("jobName.id"))) {
/* 142 */       this.duty.setJobName(this.jobsManager.loadJobs(Long.valueOf(this.request.getParameter("jobName.id"))));
/*     */     }
/*     */ 
/* 146 */     if (!StringUtils.isEmpty(this.request.getParameter("dept"))) {
/* 147 */       this.duty.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept"))));
/*     */     }
/*     */     else
/*     */     {
/* 151 */       this.duty.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept.id"))));
/*     */     }
/*     */ 
/* 155 */     if (!StringUtils.isEmpty(this.request.getParameter("perType.id"))) {
/* 156 */       this.duty.setPerType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("perType.id"))));
/*     */     }
/*     */ 
/* 160 */     this.duty.setOrganization(this.userManager.getUser().getOrganization());
/*     */     try
/*     */     {
/* 163 */       this.dutyManager.storeDuty(this.duty);
/* 164 */       if (isNew) {
/* 165 */         addActionMessage(getText("duty.add.success", Arrays.asList(new Object[] { this.duty.getCode() })));
/*     */ 
/* 167 */         return "new";
/*     */       }
/*     */ 
/* 170 */       addActionMessage(getText("duty.edit.success", Arrays.asList(new Object[] { this.duty.getCode() })));
/*     */ 
/* 172 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */       addActionError(getText("code.add.exist", Arrays.asList(new Object[] { this.duty.getCode() })));
/*     */     }
/* 178 */     return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPerType()
/*     */   {
/* 187 */     List codes = null;
/*     */     try {
/* 189 */       codes = new ArrayList();
/* 190 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("049"));
/*     */ 
/* 192 */       if ((null != one) && (one.size() > 0)) {
/* 193 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 195 */         if ((null != list) && (list.size() > 0)) {
/* 196 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 199 */       return codes;
/*     */     } catch (DaoException e) {
/* 201 */       e.printStackTrace();
/*     */     }
/* 203 */     return codes;
/*     */   }
/*     */ 
/*     */   public Duty getDuty()
/*     */   {
/* 211 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(Duty duty)
/*     */   {
/* 219 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public Long getDepartmentId()
/*     */   {
/* 226 */     if (hasId("dept.id")) {
/* 227 */       return getId("dept.id");
/*     */     }
/* 229 */     return null;
/*     */   }
/*     */   public Long getDeptId() {
/* 232 */     if (hasId("dept")) {
/* 233 */       return getId("dept");
/*     */     }
/* 235 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.duty.EditDutyAction
 * JD-Core Version:    0.6.2
 */