package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.additionalInfo;

import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.additionalInfo.ContractAdditionalInfo;
import com.yongjun.tdms.service.CustomerRelationship.additionalInfo.CusAdditionalInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.additionInfo.ContractAdditionInfoManager;

public class EditContractAdditionalAction extends PrepareAction {

	private static final long serialVersionUID = 1L;
	private final ContractAdditionInfoManager contractAdditionInfoManager;
	private final ContractManagementManager contractManagementManager;
	private final CusAdditionalInfoManager cusAdditionalInfoManager;
	private final ContactArchivesManager contactArchivesManager;

	private ContractAdditionalInfo contractAdditionalInfo;
	private ContractManagement contractManagement;

	public EditContractAdditionalAction(ContractAdditionInfoManager contractAdditionInfoManager,
			ContractManagementManager contractManagementManager, CusAdditionalInfoManager cusAdditionalInfoManager,
			ContactArchivesManager contactArchivesManager) {
		this.contractAdditionInfoManager = contractAdditionInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.cusAdditionalInfoManager = cusAdditionalInfoManager;
		this.contactArchivesManager = contactArchivesManager;
	}

	public void prepare() throws Exception {
		try {

			if (hasId("contractManagement.id")) {
				this.contractManagement = this.contractManagementManager
						.loadContractManagement(getId("contractManagement.id"));
				List<ContractAdditionalInfo> cAInfos = this.contractAdditionInfoManager.loadByKey(
						"contractManagement.id", getId("contractManagement.id"));
				if (cAInfos == null) {
					this.contractAdditionalInfo = new ContractAdditionalInfo();
					List<CusAdditionalInfo> cusAdditionalInfos = this.cusAdditionalInfoManager.loadByKey("ci.id",
							this.contractManagement.getCustomerInfo().getId());
					if (cusAdditionalInfos != null) {
						this.contractAdditionalInfo.setBank(cusAdditionalInfos.get(0).getBank());
						this.contractAdditionalInfo.setBankAccount(cusAdditionalInfos.get(0).getBankAccount());
					}
					this.contractAdditionalInfo.setAddress(this.contractManagement.getAddress());
				} else {
					this.contractAdditionalInfo = cAInfos.get(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() {
		try {
			if (hasId("contractManagement.id")) {
				this.contractAdditionalInfo.setContractManagement(contractManagementManager
						.loadContractManagement(getId("contractManagement.id")));
			}
			if (hasId("contractAdditionalInfo.financialPer.id")) {
				// System.out.println(getId("contractAdditionalInfo.financialPer.id"));
				ContactArchives contactArchives = contactArchivesManager
						.loadContactArchives(getId("contractAdditionalInfo.financialPer.id"));
				this.contractAdditionalInfo.setFinancialPer(contactArchives);
			}
			this.contractAdditionalInfo.setAddress(request.getParameter("contractAdditionalInfo.address"));
			this.contractAdditionalInfo.setBank(request.getParameter("contractAdditionalInfo.bank"));
			this.contractAdditionalInfo.setBankAccount(request.getParameter("contractAdditionalInfo.bankAccount"));
			this.contractAdditionalInfo.setComment(request.getParameter("contractAdditionalInfo.comment"));
			this.contractAdditionInfoManager.storeContractAdditionalInfo(contractAdditionalInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public ContractAdditionalInfo getContractAdditionalInfo() {
		return contractAdditionalInfo;
	}

	public void setContractAdditionalInfo(ContractAdditionalInfo contractAdditionalInfo) {
		this.contractAdditionalInfo = contractAdditionalInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

}
