/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.history;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactsJobResumeManager;

import java.util.List;
/*     */ 
/*     */ public class ListContactToHistoryAction extends ValueListAction
/*     */ {
/**
	 * 
	 */
			private static final long serialVersionUID = 1L;
/*     */   private final ContactToHistoryManager contactToHistoryManager;
/*     */   private Long contactId;
/*     */   private List<ContactToHistory> contactToHistorys;
/*     */ 
/*     */   public ListContactToHistoryAction(ContactToHistoryManager contactToHistoryManager)
/*     */   {
/*  58 */     this.contactToHistoryManager = contactToHistoryManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  67 */     return "historys";
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
/*  79 */       this.contactToHistorys = this.contactToHistoryManager.loadAllContactToHistory(getIds("contactToHistoryIds"));
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*  92 */     this.contactToHistoryManager.deleteAllContactToHistory(this.contactToHistorys);
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