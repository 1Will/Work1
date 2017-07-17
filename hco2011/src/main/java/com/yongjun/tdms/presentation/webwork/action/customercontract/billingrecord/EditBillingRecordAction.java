package com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class EditBillingRecordAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final BillingRecordManager billingRecordManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContractManagementManager contractManagementManager;
	private BillingRecord billingRecord;
	private final ContactArchivesManager contactArchivesManager;
	private final UserManager userManager;
	private String popWindowFlag;

	public EditBillingRecordAction(BillingRecordManager billingRecordManager,
			ContactArchivesManager contactArchivesManager, PersonnelFilesManager personnelFilesManager,
			CustomerInfoManager customerInfoManager, ContractManagementManager contractManagementManager,
			UserManager userManager) {
		this.billingRecordManager = billingRecordManager;
		this.contactArchivesManager = contactArchivesManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.userManager = userManager;
	}

	public void prepare() throws Exception {
		if (hasId("billingRecord.id")) {
			this.billingRecord = this.billingRecordManager.loadBillingRecord(getId("billingRecord.id"));
		} else {
			this.billingRecord = new BillingRecord();
		}

		User user = this.userManager.getUser();
		List list = this.personnelFilesManager.loadByKey("code", user.getCode());
		if (null != list) {
			PersonnelFiles payee = (PersonnelFiles) list.get(0);
			this.billingRecord.setPayee(payee);
		}
		if (null != request.getParameter("popWindowFlag")) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
	}

	public String save() throws Exception {
		boolean isNew = this.billingRecord.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
			CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request
					.getParameter("customer.id")));

			if (null != customer) {
				this.billingRecord.setCustomerInfo(customer);
			}

		}

		if (!StringUtils.isEmpty(this.request.getParameter("contactArchives.id"))) {
			ContactArchives contactArchives = this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request
					.getParameter("contactArchives.id")));

			if (null != contactArchives) {
				this.billingRecord.setContactArchives(contactArchives);
			}
		}

		if (!StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
			ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(Long
					.valueOf(this.request.getParameter("contractManagement.id")));

			if (null != contractManagement) {
				this.billingRecord.setContractManagement(contractManagement);
			}
		}

		if (!StringUtils.isEmpty(this.request.getParameter("payee.id"))) {
			PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request
					.getParameter("payee.id")));

			if (null != payee) {
				this.billingRecord.setPayee(payee);
			}
		}
		try {
			
			this.billingRecordManager.storeBillingRecord(this.billingRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isNew) {
			addActionMessage(getText("billingRecord.save.success"));
			return "new";
		}
		addActionMessage(getText("billingRecord.edit.success"));
		return "success";
	}

	public BillingRecord getBillingRecord() {
		return this.billingRecord;
	}

	public void setBillingRecord(BillingRecord billingRecord) {
		this.billingRecord = billingRecord;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
}
