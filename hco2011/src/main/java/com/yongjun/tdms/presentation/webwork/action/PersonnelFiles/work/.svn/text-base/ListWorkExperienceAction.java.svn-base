/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.work;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.WorkExperience;
/*     */ import com.yongjun.tdms.service.personnelFiles.work.WorkExperienceManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListWorkExperienceAction extends ValueListAction
/*     */ {
/*     */   private final WorkExperienceManager workExperienceManager;
/*     */   private Long personnelFileId;
/*     */   private List<WorkExperience> workExperiences;
/*     */ 
/*     */   public ListWorkExperienceAction(WorkExperienceManager workExperienceManager)
/*     */   {
/*  58 */     this.workExperienceManager = workExperienceManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  67 */     return "works";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  74 */     if (hasId("pf.id")) {
/*  75 */       this.personnelFileId = Long.valueOf(getId("pf.id").longValue());
/*  76 */       setFirst(false);
/*     */     }
/*  78 */     if (hasIds("workExperienceIds"))
/*  79 */       this.workExperiences = this.workExperienceManager.loadAllWorkExperience(getIds("workExperienceIds"));
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*  92 */     this.workExperienceManager.deleteAllWorkExperience(this.workExperiences);
/*  93 */     addActionMessage(getText("delete.works.success"));
/*  94 */     return "success";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 102 */     if (isDelete()) {
/* 103 */       return delete();
/*     */     }
/* 105 */     return "success";
/*     */   }
/*     */ 
/*     */   public Long getPersonnelFileId()
/*     */   {
/* 115 */     return this.personnelFileId;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFileId(Long personnelFileId) {
/* 119 */     this.personnelFileId = personnelFileId;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.work.ListWorkExperienceAction
 * JD-Core Version:    0.6.2
 */