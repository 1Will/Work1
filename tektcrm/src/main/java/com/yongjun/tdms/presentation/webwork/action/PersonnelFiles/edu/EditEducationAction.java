/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.edu;
/*     */ 
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.personnelFiles.Education;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.edu.EducationManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditEducationAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private EducationManager educationManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private UserManager userManager;
/*     */   private Education education;
/*     */   private PersonnelFiles personnelFiles;
            private ContactArchives contactArchives;
            private ContactArchivesManager contactArchivesManager;
/*     */ 
/*     */   public EditEducationAction(EducationManager educationManager, PersonnelFilesManager personnelFilesManager, CodeValueManager codeValueManager, UserManager userManager,ContactArchivesManager contactArchivesManager)
/*     */   {
/*  75 */     this.educationManager = educationManager;
/*  76 */     this.personnelFilesManager = personnelFilesManager;
/*  77 */     this.codeValueManager = codeValueManager;
/*  78 */     this.userManager = userManager;
              this.contactArchivesManager=contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  82 */     if (hasId("education.id")) {
/*  83 */       this.education = this.educationManager.loadEducation(getId("education.id"));
/*     */     }
/*     */     else
/*     */     {
/*  87 */       this.education = new Education();
/*     */     }
/*  89 */     loadpf();
              loadcr();
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  94 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save() {
/*  98 */     boolean isNew = this.education.isNew();
/*  99 */     loadpf();
              loadcr();
/* 100 */     this.education.setOrganization(this.userManager.getOrganization());
/*     */ 
/* 102 */     if (hasId("education.edu1.id")) {
/* 103 */       this.education.setEdu(this.codeValueManager.loadCodeValue(getId("education.edu1.id")));
/*     */     }
/*     */ 
/* 106 */     if (isNew) {
/*     */       try {
/* 108 */         this.educationManager.storeEducation(this.education);
/* 109 */         addActionMessage(getText("education.add.success", Arrays.asList(new Object[] { this.education.getDegree() })));
/*     */ 
/* 111 */         return "new";
/*     */       }
/*     */       catch (Exception e) {
/* 114 */         addActionMessage(getText("education.add.error"));
/* 115 */         return "error";
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 120 */       this.educationManager.storeEducation(this.education);
/* 121 */       addActionMessage(getText("education.edit.success", Arrays.asList(new Object[] { this.education.getId() })));
/*     */ 
/* 123 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 126 */       addActionMessage(getText("education.eidt.error"));
/* 127 */     }return "error";
/*     */   }
/*     */ 
/*     */   public void loadpf()
/*     */   {
/* 138 */     if (hasId("personnelFiles.id")) {
/* 139 */       this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
/*     */ 
/* 141 */       this.education.setPf(this.personnelFiles);
/*     */     }
/*     */   }
			public void loadcr()
/*     */   {
/* 138 */     if (hasId("contactArchives.id")) {
/* 139 */       this.contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
/*     */ 
/* 141 */       this.education.setCr(this.contactArchives);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllEdu()
/*     */   {
/* 152 */     List list = new ArrayList();
/*     */     try {
/* 154 */       String[] keyNames = { "code" };
/* 155 */       Object[] keyValues = { "017" };
/* 156 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/* 158 */       if ((null != cvs) && 
/* 159 */         (cvs.size() > 0)) {
/* 160 */         CodeValue cv = (CodeValue)cvs.get(0);
/* 161 */         keyNames = new String[] { "parentCV" };
/* 162 */         keyValues = new Object[] { cv.getId() };
/* 163 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 170 */     return list;
/*     */   }
/*     */ 
/*     */   public Education getEducation() {
/* 174 */     return this.education;
/*     */   }
/*     */ 
/*     */   public void setEducation(Education education) {
/* 178 */     this.education = education;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFiles() {
/* 182 */     return this.personnelFiles;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFiles(PersonnelFiles personnelFiles) {
/* 186 */     this.personnelFiles = personnelFiles;
/*     */   }
			public ContactArchives getContactArchives() {
				return contactArchives;
			}
			public void setContactArchives(ContactArchives contactArchives) {
				this.contactArchives = contactArchives;
			}
          
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.edu.EditEducationAction
 * JD-Core Version:    0.6.2
 */