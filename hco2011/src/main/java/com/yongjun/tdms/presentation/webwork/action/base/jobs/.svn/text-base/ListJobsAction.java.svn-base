/*     */ package com.yongjun.tdms.presentation.webwork.action.base.jobs;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import com.yongjun.tdms.service.base.jobs.JobsManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListJobsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JobsManager jobsManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private UserManager userManager;
/*     */   private CodeValue jobsType;
/*     */   private String orgName;
/*     */   private Long orgId;
/*     */   private List<Jobs> jobsList;
/*     */ 
/*     */   public ListJobsAction(JobsManager jobsManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  73 */     this.jobsManager = jobsManager;
/*  74 */     this.userManager = userManager;
/*  75 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  80 */     if (isDisabled()) {
/*  81 */       return disable();
/*     */     }
/*  83 */     if (isEnable()) {
/*  84 */       return enable();
/*     */     }
/*  86 */     if (isDelete()) {
/*  87 */       return delete();
/*     */     }
/*  89 */     return super.execute();
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  94 */     return "job";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  99 */     Map map = super.getRequestParameterMap();
/* 100 */     CodeValue type = loadJobsType();
/* 101 */     this.jobsType = type;
/* 102 */     if (type != null) {
/* 103 */       map.put("jobsType.id", type.getId());
/* 104 */       if (!hasId("onlyInvalid")) {
/* 105 */         map.put("onlyValid", Boolean.valueOf(false));
/*     */       }
/*     */     }
/* 108 */     map.put("orgId", this.orgId);
/* 109 */     return map;
/*     */   }
/*     */ 
/*     */   public String disable() {
/*     */     try {
/* 114 */       this.jobsManager.disabledAllJobs(this.jobsList);
/* 115 */       addActionMessage(getText("job.disable.success"));
/*     */     } catch (Exception e) {
/* 117 */       addActionMessage(getText("job.disable.error"));
/*     */     }
/* 119 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete() {
/*     */     try {
/* 124 */       this.jobsManager.deleteAllJobs(this.jobsList);
/* 125 */       addActionMessage(getText("job.delete.success"));
/*     */     } catch (Exception e) {
/* 127 */       addActionMessage(getText("job.delete.error"));
/*     */     }
/* 129 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable() {
/*     */     try {
/* 134 */       this.jobsManager.enabledAllJobs(this.jobsList);
/* 135 */       addActionMessage(getText("job.enable.success"));
/*     */     } catch (Exception e) {
/* 137 */       addActionMessage(getText("job.enable.error"));
/*     */     }
/* 139 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllJobType() {
/* 143 */     List list = null;
/*     */     try
/*     */     {
/* 146 */       String[] keyNames = { "code", "disabled" };
/* 147 */       Object[] keyValues = { "016", Boolean.valueOf(false) };
/* 148 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/* 149 */       if ((null != cvs) && 
/* 150 */         (cvs.size() > 0)) {
/* 151 */         CodeValue cv = (CodeValue)cvs.get(0);
/* 152 */         keyNames = new String[] { "parentCV.id", "disabled" };
/* 153 */         keyValues = new Object[] { cv.getId(), Boolean.valueOf(false) };
/* 154 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */       }
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 159 */       e.printStackTrace();
/*     */     }
/* 161 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Jobs> getJobsList() {
/* 165 */     return this.jobsList;
/*     */   }
/*     */ 
/*     */   public void setJobsList(List<Jobs> jobsList) {
/* 169 */     this.jobsList = jobsList;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/* 174 */     if (hasIds("jobs.ids")) {
/* 175 */       this.jobsList = this.jobsManager.loadAllJobs(getIds("jobs.ids"));
/*     */     }
/*     */ 
/* 178 */     this.jobsType = loadJobsType();
/* 179 */     if (this.jobsType != null) {
/* 180 */       setFirst(false);
/*     */     }
/* 182 */     this.orgName = this.userManager.getUser().getOrganization().getName();
/* 183 */     this.orgId = this.userManager.getUser().getId();
/*     */   }
/*     */ 
/*     */   public CodeValue loadJobsType()
/*     */   {
/* 194 */     if ((null != this.request.getParameter("jobsType")) && (this.request.getParameter("jobsType") != ""))
/*     */     {
/* 196 */       CodeValue type = this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("jobsType"))));
/*     */ 
/* 198 */       return type;
/*     */     }
/* 200 */     return null;
/*     */   }
/*     */ 
/*     */   public CodeValue getJobsType() {
/* 204 */     return this.jobsType;
/*     */   }
/*     */ 
/*     */   public void setJobsType(CodeValue jobsType) {
/* 208 */     this.jobsType = jobsType;
/*     */   }
/*     */ 
/*     */   public String getOrgName() {
/* 212 */     return this.orgName;
/*     */   }
/*     */ 
/*     */   public void setOrgName(String orgName) {
/* 216 */     this.orgName = orgName;
/*     */   }
/*     */ 
/*     */   public Long getOrgId() {
/* 220 */     return this.orgId;
/*     */   }
/*     */ 
/*     */   public void setOrgId(Long orgId) {
/* 224 */     this.orgId = orgId;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.jobs.ListJobsAction
 * JD-Core Version:    0.6.2
 */