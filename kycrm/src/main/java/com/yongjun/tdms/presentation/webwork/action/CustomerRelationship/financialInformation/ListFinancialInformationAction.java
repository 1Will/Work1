package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.financialInformation;

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
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.CustomerRelationship.financialInformation.FinancialInformationManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectPartner.ProjectPartnerManager;
import com.yongjun.tdms.service.supplier.SupplierManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListFinancialInformationAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final FinancialInformationManager financialInformationManager;
	private List<FinancialInformation> cas;
	private Long customerId;

	public ListFinancialInformationAction(FinancialInformationManager financialInformationManager) {
		this.financialInformationManager = financialInformationManager;
	}

	public void prepare() throws Exception {
		if (hasIds("financialInformationIds")) {
			this.cas = this.financialInformationManager.loadAllFinancialInformation(getIds("financialInformationIds"));
		}


		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
		}
	}

	protected String getAdapterName() {
		return "financialInformationHQL";
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
			this.financialInformationManager.deleteAllFinancialInformation(this.cas);
			addActionMessage(getText("financialInformation.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("financialInformation.disabled.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.financialInformationManager.disabledAllFinancialInformation(this.cas);
			addActionMessage(getText("financialInformation.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("financialInformation.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.financialInformationManager.enabledAllFinancialInformation(this.cas);
			addActionMessage(getText("financialInformation.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("financialInformation.enabled.error"));
		}
		return "error";
	}

	

	public List<FinancialInformation> getCas() {
		return this.cas;
	}

	public void setCas(List<FinancialInformation> cas) {
		this.cas = cas;
	}


	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


}
