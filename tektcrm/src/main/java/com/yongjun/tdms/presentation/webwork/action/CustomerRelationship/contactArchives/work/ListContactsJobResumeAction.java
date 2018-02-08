/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.work;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
		  import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;
		  import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactsJobResumeManager;

		  import java.util.List;
/*     */ 
/*     */ public class ListContactsJobResumeAction extends ValueListAction
/*     */ {
/**
	 * 
	 */
			private static final long serialVersionUID = 1L;
/*     */   private final ContactsJobResumeManager contactsJobResumeManager;
/*     */   private Long contactId;
/*     */   private List<ContactsJobResume> contactsJobResumes;
/*     */ 
/*     */   public ListContactsJobResumeAction(ContactsJobResumeManager contactsJobResumeManager)
/*     */   {
/*  58 */     this.contactsJobResumeManager = contactsJobResumeManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  67 */     return "jobs";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  74 */     if (hasId("cr.id")) {
/*  75 */       this.contactId = Long.valueOf(getId("cr.id").longValue());
/*  76 */       setFirst(false);
/*     */     }
/*  78 */     if (hasIds("contactsJobResumeIds"))
/*  79 */       this.contactsJobResumes = this.contactsJobResumeManager.loadAllContactsJobResume(getIds("contactsJobResumeIds"));
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*  92 */     this.contactsJobResumeManager.deleteAllContactsJobResume(this.contactsJobResumes);
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
			public Long getContactId() {
				return contactId;
			}
			public void setContactId(Long contactId) {
				this.contactId = contactId;
			}

/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.work.ListWorkExperienceAction
 * JD-Core Version:    0.6.2
 */