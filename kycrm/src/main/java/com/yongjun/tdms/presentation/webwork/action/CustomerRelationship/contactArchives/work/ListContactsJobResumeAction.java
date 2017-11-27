package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.work;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactsJobResumeManager;

public class ListContactsJobResumeAction extends ValueListAction {

	private static final long serialVersionUID = 1L;
	private final ContactsJobResumeManager contactsJobResumeManager;
	private Long contactId;
	private List<ContactsJobResume> contactsJobResumes;

	public ListContactsJobResumeAction(ContactsJobResumeManager contactsJobResumeManager) {
		this.contactsJobResumeManager = contactsJobResumeManager;
	}

	protected String getAdapterName() {
		return "jobs";
	}

	public void prepare() throws Exception {
		if (hasId("cr.id")) {
			this.contactId = Long.valueOf(getId("cr.id").longValue());
			setFirst(false);
		}
		if (hasIds("contactsJobResumeIds"))
			this.contactsJobResumes = this.contactsJobResumeManager
					.loadAllContactsJobResume(getIds("contactsJobResumeIds"));
	}

	public String delete() {
		this.contactsJobResumeManager.deleteAllContactsJobResume(this.contactsJobResumes);
		addActionMessage(getText("delete.works.success"));
		return "success";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

}
