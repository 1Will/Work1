/*     */ package com.yongjun.tdms.presentation.webwork.action.base.jobs;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import com.yongjun.tdms.service.base.jobs.JobsManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditJobsAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Jobs job;
/*     */   private CodeValue jobsType;
/*     */   private JobsManager jobsManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private UserManager userManager;
/*     */ 
/*     */   public EditJobsAction(JobsManager jobsManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  64 */     this.jobsManager = jobsManager;
/*  65 */     this.codeValueManager = codeValueManager;
/*  66 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  70 */     if ((this.job == null) && (hasId("job.id"))) {
/*  71 */       this.job = this.jobsManager.loadJobs(getId("job.id"));
/*     */     }
/*     */     else {
/*  74 */       this.job = new Jobs();
/*     */     }
/*     */ 
/*  77 */     if (hasId("jobType")) {
/*  78 */       CodeValue type = this.codeValueManager.loadCodeValue(getId("jobType"));
/*     */ 
/*  80 */       this.jobsType = type;
/*     */     }
/*  82 */     if (hasId("jobsType")) {
/*  83 */       CodeValue type = this.codeValueManager.loadCodeValue(getId("jobsType"));
/*     */ 
/*  85 */       this.job.setJobType(type);
/*     */     }
/*  87 */     if ((null != this.request.getParameter("jobType")) && (this.request.getParameter("jobType") != ""))
/*     */     {
/*  89 */       CodeValue type = this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("jobType"))));
/*     */ 
/*  91 */       this.jobsType = type;
/*  92 */       this.job.setJobType(type);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  98 */     boolean isNew = this.job.isNew();
/*  99 */     this.job.setOrganization(this.userManager.getUser().getOrganization());
/*     */ 
/* 101 */     if (hasId("jobsType")) {
/* 102 */       CodeValue jobType = this.codeValueManager.loadCodeValue(getId("jobsType"));
/*     */ 
/* 104 */       this.job.setJobType(jobType);
/*     */     }
/* 106 */     if (isNew) {
/*     */       try {
/* 108 */         if (this.jobsManager.loadByKey("code", this.job.getCode()) == null) {
/* 109 */           this.jobsManager.storeJob(this.job);
/* 110 */           this.job = ((Jobs)this.jobsManager.loadByKey("code", this.job.getCode()).get(0));
/*     */ 
/* 112 */           addActionMessage(getText("job.save.success", Arrays.asList(new Object[] { this.job.getName() })));
/*     */ 
/* 114 */           return "success";
/*     */         }
/* 116 */         addActionMessage(getText("code.add.exist", Arrays.asList(new Object[] { this.job.getCode() })));
/*     */ 
/* 118 */         return "error";
/*     */       }
/*     */       catch (DaoException e) {
/* 121 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/*     */       try
/*     */       {
/* 127 */         List list = this.jobsManager.loadByKey("code", this.job.getCode());
/* 128 */         if ((list == null) || (((Jobs)list.get(0)).getId() == this.job.getId())) {
/* 129 */           this.jobsManager.storeJob(this.job);
/* 130 */           addActionMessage(getText("job.update.success", Arrays.asList(new Object[] { this.job.getName() })));
/*     */         }
/*     */         else {
/* 133 */           addActionMessage(getText("job.update.error", Arrays.asList(new Object[] { this.job.getCode() })));
/*     */ 
/* 135 */           return "error";
/*     */         }
/*     */       }
/*     */       catch (DaoException e) {
/* 139 */         addActionMessage(getText("job.update.error", Arrays.asList(new Object[] { this.job.getCode() })));
/*     */ 
/* 141 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 145 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllJobType() {
/* 149 */     List list = null;
/*     */     try
/*     */     {
/* 153 */       String[] keyNames = { "code", "disabled" };
/*     */ 
/* 155 */       Object[] keyValues = { "016", Integer.valueOf(0) };
/* 156 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/* 158 */       if ((null != cvs) && 
/* 159 */         (cvs.size() > 0)) {
/* 160 */         CodeValue cv = (CodeValue)cvs.get(0);
/* 161 */         keyNames = new String[] { "parentCV.id", "disabled" };
/*     */ 
/* 163 */         keyValues = new Object[] { cv.getId(), Integer.valueOf(0) };
/* 164 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 170 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/* 173 */       if (list == null) {
/* 174 */         list = new ArrayList();
/*     */       }
/*     */     }
/* 177 */     return list;
/*     */   }
/*     */ 
/*     */   public Jobs getJob() {
/* 181 */     return this.job;
/*     */   }
/*     */ 
/*     */   public void setJob(Jobs job) {
/* 185 */     this.job = job;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/* 190 */     return super.execute();
/*     */   }
/*     */ 
/*     */   public CodeValue getJobsType() {
/* 194 */     return this.jobsType;
/*     */   }
/*     */ 
/*     */   public void setJobsType(CodeValue jobsType) {
/* 198 */     this.jobsType = jobsType;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.jobs.EditJobsAction
 * JD-Core Version:    0.6.2
 */