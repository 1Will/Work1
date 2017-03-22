/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.work;
/*     */ 
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.WorkExperience;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.work.WorkExperienceManager;
/*     */ import java.util.Arrays;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditWorkExperienceAction extends PrepareAction
/*     */ {
/*     */   private final WorkExperienceManager workExperienceManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
/*     */   private WorkExperience workExperience;
/*     */   private Long personnelFileId;
/*     */ 
/*     */   public EditWorkExperienceAction(WorkExperienceManager workExperienceManager, PersonnelFilesManager personnelFilesManager, UserManager userManager)
/*     */   {
/*  59 */     this.workExperienceManager = workExperienceManager;
/*  60 */     this.personnelFilesManager = personnelFilesManager;
/*  61 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  68 */     if (hasId("pf.id")) {
/*  69 */       this.personnelFileId = Long.valueOf(this.request.getParameter("pf.id"));
/*     */     }
/*  71 */     if ((this.workExperience == null) && (hasId("workExperience.id"))) {
/*  72 */       this.workExperience = this.workExperienceManager.loadWorkExperience(Long.valueOf(this.request.getParameter("workExperience.id")));
/*     */     }
/*     */     else
/*     */     {
/*  76 */       this.workExperience = new WorkExperience();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  88 */     boolean isNew = this.workExperience.isNew();
/*     */ 
/*  90 */     this.workExperience.setOrganization(this.userManager.getOrganization());
/*     */ 
/*  92 */     this.workExperience.setPf(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("pf.id"))));
/*     */     try
/*     */     {
/*  95 */       this.workExperienceManager.storeWorkExperience(this.workExperience);
/*  96 */       if (isNew) {
/*  97 */         addActionMessage(getText("work.add.success", Arrays.asList(new Object[] { this.workExperience.getId() })));
/*     */ 
/*  99 */         return "new";
/*     */       }
/*     */ 
/* 102 */       addActionMessage(getText("work.edit.success", Arrays.asList(new Object[] { this.workExperience.getId() })));
/*     */ 
/* 104 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 108 */       e.printStackTrace();
/* 109 */       addActionMessage(getText("work.add.error", Arrays.asList(new Object[] { this.workExperience.getId() })));
/*     */     }
/* 111 */     return "error";
/*     */   }
/*     */ 
/*     */   public Long getPersonnelFileId()
/*     */   {
/* 122 */     return this.personnelFileId;
/*     */   }
/*     */ 
/*     */   public WorkExperience getWorkExperience()
/*     */   {
/* 130 */     return this.workExperience;
/*     */   }
/*     */ 
/*     */   public void setWorkExperience(WorkExperience workExperience)
/*     */   {
/* 138 */     this.workExperience = workExperience;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFileId(Long personnelFileId) {
/* 142 */     this.personnelFileId = personnelFileId;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.work.EditWorkExperienceAction
 * JD-Core Version:    0.6.2
 */