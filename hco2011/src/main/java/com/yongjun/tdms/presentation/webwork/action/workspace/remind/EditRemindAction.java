/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.remind;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workspace.warnning.Remind;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workspace.remind.RemindManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditRemindAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 8502276251480265122L;
/*     */   private final RemindManager remindManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private Remind remind;
/*     */   private PersonnelFiles employee;
/*     */   private ContactArchives contactArchive;
/*  60 */   private List<PersonnelFiles> personnelFiles = new ArrayList();
/*  61 */   private List<ContactArchives> contacts = new ArrayList();
/*     */ 
/*     */   public EditRemindAction(RemindManager remindManager, PersonnelFilesManager personnelFilesManager, ContactArchivesManager contactArchivesManager)
/*     */   {
/*  65 */     this.remindManager = remindManager;
/*  66 */     this.personnelFilesManager = personnelFilesManager;
/*  67 */     this.contactArchivesManager = contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  71 */     if (hasId("remind.id"))
/*  72 */       this.remind = this.remindManager.loadRemind(getId("remind.id"));
/*     */     else {
/*  74 */       this.remind = new Remind();
/*     */     }
/*  76 */     if (hasId("employee.id")) {
/*  77 */       this.employee = this.personnelFilesManager.loadPersonnel(getId("employee.id"));
/*     */     }
/*     */ 
/*  80 */     if (hasId("contactArchive.id")) {
/*  81 */       this.contactArchive = this.contactArchivesManager.loadContactArchives(getId("contactArchive.id"));
/*     */     }
/*     */ 
/*  84 */     if (hasIds("employeeIds")) {
/*  85 */       this.personnelFiles = this.personnelFilesManager.loadAllPersonnel(getIds("employeeIds"));
/*     */     }
/*  87 */     if (hasIds("contactArchivesIds"))
/*  88 */       this.contacts = this.contactArchivesManager.loadAllContactArchives(getIds("contactArchivesIds"));
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  94 */     boolean isNew = this.remind.isNew();
/*  95 */     this.remindManager.storeRemind(this.remind);
/*  96 */     if (isNew) {
/*  97 */       addActionMessage(getText("remind.add.success"));
/*  98 */       return "new";
/*     */     }
/* 100 */     addActionMessage(getText("remind.edit.success"));
/* 101 */     return "success";
/*     */   }
/*     */ 
/*     */   private boolean isGrantUser()
/*     */   {
/* 110 */     return hasKey("grant_employee");
/*     */   }
/*     */ 
/*     */   private boolean isRevokeUser()
/*     */   {
/* 118 */     return hasKey("revoke_employee");
/*     */   }
/*     */ 
/*     */   public Remind getRemind()
/*     */   {
/* 123 */     return this.remind;
/*     */   }
/*     */   public void setRemind(Remind remind) {
/* 126 */     this.remind = remind;
/*     */   }
/*     */   public List<PersonnelFiles> getUsers() {
/* 129 */     List list = new ArrayList();
/* 130 */     list = this.personnelFilesManager.loadAllPersonnel();
/* 131 */     return list;
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> getContactArchives() {
/* 135 */     List list = new ArrayList();
/* 136 */     list = this.contactArchivesManager.loadAllContactArchives();
/* 137 */     return list;
/*     */   }
/*     */   public PersonnelFiles getEmployee() {
/* 140 */     return this.employee;
/*     */   }
/*     */   public void setEmployee(PersonnelFiles employee) {
/* 143 */     this.employee = employee;
/*     */   }
/*     */   public List<PersonnelFiles> getPersonnelFiles() {
/* 146 */     return this.personnelFiles;
/*     */   }
/*     */   public void setPersonnelFiles(List<PersonnelFiles> personnelFiles) {
/* 149 */     this.personnelFiles = personnelFiles;
/*     */   }
/*     */   public ContactArchives getContactArchive() {
/* 152 */     return this.contactArchive;
/*     */   }
/*     */   public void setContactArchive(ContactArchives contactArchive) {
/* 155 */     this.contactArchive = contactArchive;
/*     */   }
/*     */   public List<ContactArchives> getContacts() {
/* 158 */     return this.contacts;
/*     */   }
/*     */   public void setContacts(List<ContactArchives> contacts) {
/* 161 */     this.contacts = contacts;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.remind.EditRemindAction
 * JD-Core Version:    0.6.2
 */