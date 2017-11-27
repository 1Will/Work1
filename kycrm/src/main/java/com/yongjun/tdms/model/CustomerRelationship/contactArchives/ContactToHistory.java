package com.yongjun.tdms.model.CustomerRelationship.contactArchives;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class ContactToHistory extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ContactArchives contactArchivesId;
	private ContractManagement contractManagement;
	private String conment;

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ContactsJobResume)) {
			return false;
		}
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public ContactArchives getContactArchivesId() {
		return contactArchivesId;
	}

	public void setContactArchivesId(ContactArchives contactArchivesId) {
		this.contactArchivesId = contactArchivesId;
	}

	public String getConment() {
		return conment;
	}

	public void setConment(String conment) {
		this.conment = conment;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

}
