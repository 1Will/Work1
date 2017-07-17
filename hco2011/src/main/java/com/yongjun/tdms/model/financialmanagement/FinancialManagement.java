package com.yongjun.tdms.model.financialmanagement;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class FinancialManagement extends BaseInfoEntity {
	private static final long serialVersionUID = -6500861920079420052L;
	private String code;
	private ContractManagement contractManagement;
	private CustomerInfo customerInfo;
	private PersonnelFiles saleman;
	private CodeValue collectionType;
	private CodeValue payment;
	private String accountNumber;
	private String accountName;
	private CodeValue batch;
	private Double sumReceivable = Double.valueOf(0.0D);

	private Double trueSum = Double.valueOf(0.0D);

	private Double totalSum = Double.valueOf(0.0D);

	private Double withoutGotSum = Double.valueOf(0.0D);
	private String invoice;
	private String invoiceCode;
	private Date collectionDate;
	private PersonnelFiles payee;
	private Department dept;
	private String remark;
	private String isSaved = "0";

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public CodeValue getBatch() {
		return this.batch;
	}

	public void setBatch(CodeValue batch) {
		this.batch = batch;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public CodeValue getCollectionType() {
		return this.collectionType;
	}

	public void setCollectionType(CodeValue collectionType) {
		this.collectionType = collectionType;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Department getDept() {
		return this.dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public PersonnelFiles getPayee() {
		return this.payee;
	}

	public void setPayee(PersonnelFiles payee) {
		this.payee = payee;
	}

	public CodeValue getPayment() {
		return this.payment;
	}

	public void setPayment(CodeValue payment) {
		this.payment = payment;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PersonnelFiles getSaleman() {
		return this.saleman;
	}

	public void setSaleman(PersonnelFiles saleman) {
		this.saleman = saleman;
	}

	public Double getSumReceivable() {
		return this.sumReceivable;
	}

	public void setSumReceivable(Double sumReceivable) {
		this.sumReceivable = sumReceivable;
	}

	public Double getTotalSum() {
		return this.totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public Double getTrueSum() {
		return this.trueSum;
	}

	public void setTrueSum(Double trueSum) {
		this.trueSum = trueSum;
	}

	public Double getWithoutGotSum() {
		return this.withoutGotSum;
	}

	public void setWithoutGotSum(Double withoutGotSum) {
		this.withoutGotSum = withoutGotSum;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
}
