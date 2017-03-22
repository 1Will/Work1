/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.work;
/*     */ 
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;
/*     */ import com.yongjun.tdms.model.personnelFiles.WorkExperience;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactsJobResumeManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.work.WorkExperienceManager;

/*     */ import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditContactsJobResumeAction extends PrepareAction
/*     */ {
/*     */   private final ContactsJobResumeManager contactsJobResumeManager;
/*     */   private final ContactArchivesManager  contactArchivesManager;
/*     */   private final UserManager userManager;
/*     */   private  ContactsJobResume    contactsJobResume;
/*     */   private Long contactId;
/*     */ 
/*     */   public EditContactsJobResumeAction(ContactsJobResumeManager contactsJobResumeManager, ContactArchivesManager contactArchivesManager, UserManager userManager)
/*     */   {
/*  59 */     this.contactsJobResumeManager = contactsJobResumeManager;
/*  60 */     this.contactArchivesManager = contactArchivesManager;
/*  61 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  68 */     if (hasId("cr.id")) {
/*  69 */       this.contactId = Long.valueOf(this.request.getParameter("cr.id"));
/*     */     }
/*  71 */     if ((this.contactsJobResume == null) && (hasId("contactsJobResume.id"))) {
/*  72 */       this.contactsJobResume = this.contactsJobResumeManager.loadContactsJobResume(Long.valueOf(this.request.getParameter("contactsJobResume.id")));
/*     */     }
/*     */     else
/*     */     {
/*  76 */       this.contactsJobResume = new ContactsJobResume();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  88 */     boolean isNew = this.contactsJobResume.isNew();
/*     */ 
/*     */ 
/*  92 */     this.contactsJobResume.setCr(this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("cr.id"))));
/*     */     try
/*     */     {
/*  95 */       this.contactsJobResumeManager.storeContactsJobResume(this.contactsJobResume);
/*  96 */       if (isNew) {
/*  97 */         addActionMessage(getText("work.add.success", Arrays.asList(new Object[] { this.contactsJobResume.getCr().getName() })));
/*     */ 
/*  99 */         return "new";
/*     */       }
/*     */ 
/* 102 */       addActionMessage(getText("work.edit.success", Arrays.asList(new Object[] { this.contactsJobResume.getCr().getName() })));
/*     */ 
/* 104 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 108 */       e.printStackTrace();
/* 109 */       addActionMessage(getText("work.add.error", Arrays.asList(new Object[] { this.contactsJobResume.getCr().getName() })));
/*     */     }
/* 111 */     return "error";
/*     */   }

public Long getContactId() {
	return contactId;
}
public void setContactId(Long contactId) {
	this.contactId = contactId;
}
		/*     */ 
		public ContactsJobResume getContactsJobResume() {
			return contactsJobResume;
		}
		public void setContactsJobResume(ContactsJobResume contactsJobResume) {
			this.contactsJobResume = contactsJobResume;
		}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.work.EditWorkExperienceAction
 * JD-Core Version:    0.6.2
 */