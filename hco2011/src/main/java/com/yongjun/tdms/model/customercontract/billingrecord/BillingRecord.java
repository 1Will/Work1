package com.yongjun.tdms.model.customercontract.billingrecord;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class BillingRecord extends BaseInfoEntity {
	private static final long serialVersionUID = -541892861357668687L;
	private PersonnelFiles payee;
	private CustomerInfo customerInfo;
	private ContractManagement contractManagement;
	private ContactArchives contactArchives;
	private String code;
	private String invoiceTitle;
	private Date billingTime;
	private Double sum = Double.valueOf(0.0D);//本次开票金额
	private Double hasBillSum = Double.valueOf(0.0D);//已开票金额
	private Double planSum = Double.valueOf(0.0D);//计划开票金额
	private Double restSum = Double.valueOf(0.0D);//剩下未开开票金额
	private String currency;
	private String content;
	private String isSaved;
	private long submitNum=0;
	private double lastSubmitMoney;
	private CodeValue batch;

	public Date getBillingTime() {
		return this.billingTime;
	}

	public void setBillingTime(Date billingTime) {
		this.billingTime = billingTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getCurrency() {
		return this.currency;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public PersonnelFiles getPayee() {
		return this.payee;
	}

	public void setPayee(PersonnelFiles payee) {
		this.payee = payee;
	}

	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public ContactArchives getContactArchives() {
		return this.contactArchives;
	}

	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public Double getHasBillSum() {
		return hasBillSum;
	}

	public void setHasBillSum(Double hasBillSum) {
		this.hasBillSum = hasBillSum;
	}

	public CodeValue getBatch() {
		return batch;
	}

	public void setBatch(CodeValue batch) {
		this.batch = batch;
	}

	public Double getRestSum() {
		return restSum;
	}

	public void setRestSum(Double restSum) {
		this.restSum = restSum;
	}

	public Double getPlanSum() {
		return planSum;
	}

	public void setPlanSum(Double planSum) {
		this.planSum = planSum;
	}

	public long getSubmitNum() {
		return submitNum;
	}

	public void setSubmitNum(long submitNum) {
		this.submitNum = submitNum;
	}
	public double getLastSubmitMoney() {
		return lastSubmitMoney;
	}

	public void setLastSubmitMoney(double lastSubmitMoney) {
		this.lastSubmitMoney = lastSubmitMoney;
	}
	
}
