package com.yongjun.tdms.model.customercontract.contractmanagement.additionalInfo;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class ContractAdditionalInfo extends BaseInfoEntity {

	private static final long serialVersionUID = 1L;
	
	private ContractManagement contractManagement;//合同
	private ContactArchives financialPer;//财务联系人
	private String address;//地址
	private String bank;//开户行
	private String bankAccount;//开户行账号
	private String comment;//备注

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public ContactArchives getFinancialPer() {
		return financialPer;
	}

	public void setFinancialPer(ContactArchives financialPer) {
		this.financialPer = financialPer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
