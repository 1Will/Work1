/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.edu;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.personnelFiles.Education;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.edu.EducationManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListEducationAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -5811607154602943579L;
/*     */   private EducationManager educationManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
            private ContactArchivesManager contactArchivesManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private List<Education> educationList;
/*     */   private PersonnelFiles personnelFiles;
            private ContactArchives contactArchives;
/*     */ 
/*     */   public ListEducationAction(EducationManager educationManager, PersonnelFilesManager personnelFilesManager, CodeValueManager codeValueManager,ContactArchivesManager contactArchivesManager)
/*     */   {
/*  64 */     this.educationManager = educationManager;
/*  65 */     this.personnelFilesManager = personnelFilesManager;
/*  66 */     this.codeValueManager = codeValueManager;
              this.contactArchivesManager =contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception {
/*  70 */     if (isDisabled()) {
/*  71 */       return disable();
/*     */     }
/*  73 */     if (isEnable()) {
/*  74 */       return enable();
/*     */     }
/*  76 */     if (isDelete()) {
/*  77 */       return delete();
/*     */     }
/*  79 */     return super.execute();
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  84 */     if (hasIds("educationIds")) {
/*  85 */       this.educationList = this.educationManager.loadAllEducation(getIds("educationIds"));
/*     */     }
/*     */ 
/*  88 */     if (hasId("personnelFiles.id")) {
/*  89 */       this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
/*     */     }
			if (hasId("contactArchives.id")) {
/*  89 */       this.contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
/*     */     }
/*     */ 
/*  92 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  97 */     return "education";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 102 */     Map map = super.getRequestParameterMap();
/*     */ 
/* 104 */     if (this.personnelFiles != null) {
/* 105 */       map.put("personnelFiles.id", this.personnelFiles.getId());
/* 106 */      // map.put("onlyValid", "onlyValid");
/*     */     }
			  if (this.contactArchives != null) {
/* 105 */       map.put("contactArchives.id", this.contactArchives.getId());
/* 106 */      
/*     */     }
			  map.put("onlyValid", "onlyValid");
/* 108 */     return map;
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/*     */     try
/*     */     {
/* 118 */       this.educationManager.disabledEducations(this.educationList);
/* 119 */       addActionMessage(getText("job.disable.success"));
/* 120 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 123 */       addActionMessage(getText("job.disable.error"));
/* 124 */     }return "error";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 135 */       this.educationManager.deleteAllEducation(this.educationList);
/* 136 */       addActionMessage(getText("job.delete.success"));
/* 137 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 140 */       addActionMessage(getText("job.delete.error"));
/* 141 */     }return "error";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/*     */     try
/*     */     {
/* 152 */       this.educationManager.enabledEducations(this.educationList);
/* 153 */       addActionMessage(getText("job.enable.success"));
/* 154 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 157 */       addActionMessage(getText("job.esnable.error"));
/* 158 */     }return "error";
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFiles()
/*     */   {
/* 163 */     return this.personnelFiles;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFiles(PersonnelFiles personnelFiles) {
/* 167 */     this.personnelFiles = personnelFiles;
/*     */   }
			public ContactArchives getContactArchives() {
				return contactArchives;
			}
			public void setContactArchives(ContactArchives contactArchives) {
				this.contactArchives = contactArchives;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.edu.ListEducationAction
 * JD-Core Version:    0.6.2
 */