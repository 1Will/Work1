package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.history;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;

public class ListContactToHistoryAction extends ValueListAction {

	private static final long serialVersionUID = 1L;
	private final ContactToHistoryManager contactToHistoryManager;
	private Long contactId;
	private List<ContactToHistory> contactToHistorys;

	public ListContactToHistoryAction(ContactToHistoryManager contactToHistoryManager) {
		this.contactToHistoryManager = contactToHistoryManager;
	}

	protected String getAdapterName() {
		return "historys";
	}

	public void prepare() throws Exception {
		if (hasId("cr.id")) {
			this.contactId = Long.valueOf(getId("cr.id").longValue());
			setFirst(false);
		}
		if (hasIds("contactsJobResumeIds"))
			this.contactToHistorys = this.contactToHistoryManager
					.loadAllContactToHistory(getIds("contactToHistoryIds"));
	}

	public String delete() {
		this.contactToHistoryManager.deleteAllContactToHistory(this.contactToHistorys);
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
