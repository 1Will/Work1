package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.qualification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.CustomerRelationship.qualification.CustomerRelationshipQualification;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.CustomerRelationship.qualification.QualificationManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectPartner.ProjectPartnerManager;
import com.yongjun.tdms.service.supplier.SupplierManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListQualificationAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final QualificationManager customerRelationshipQualificationManager;
	private List<CustomerRelationshipQualification> cas;
	private Long customerId;

	public ListQualificationAction(QualificationManager customerRelationshipQualificationManager) {
		this.customerRelationshipQualificationManager = customerRelationshipQualificationManager;
	}

	public void prepare() throws Exception {
		if (hasIds("qualificationIds")) {
			this.cas = this.customerRelationshipQualificationManager.loadAllQualification(getIds("qualificationIds"));
		}


		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
		}
	}

	protected String getAdapterName() {
		return "customerRelationshipQualificationHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();

		return map;
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	public String delete() {
		try {
			this.customerRelationshipQualificationManager.deleteAllQualification(this.cas);
			addActionMessage(getText("qualification.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("qualification.disabled.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.customerRelationshipQualificationManager.disabledAllQualification(this.cas);
			addActionMessage(getText("qualification.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("qualification.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.customerRelationshipQualificationManager.enabledAllQualification(this.cas);
			addActionMessage(getText("qualification.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("qualification.enabled.error"));
		}
		return "error";
	}

	

	public List<CustomerRelationshipQualification> getCas() {
		return this.cas;
	}

	public void setCas(List<CustomerRelationshipQualification> cas) {
		this.cas = cas;
	}


	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


}
