package com.yongjun.tdms.model.financeManagement.paymentorder;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.supplier.Supplier;

public class Paymentorder extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private String code;
	private Supplier supplier;//不用了
	private CustomerInfo customerInfo;//新供应商
	private CodeValue produceType;
	private double totalMoney;
	private PersonnelFiles paymentPersion;
	private Department department;
	private String remark;
	private ContractManagement contractManagement;
	private ProjectInfo projectInfo;
	private String fileName;
	private String position;
	private String isSaved;
	private String bankNum;//银行账户
	private CodeValue moneyType;//币种
	private String bankName;//开户行
	private CodeValue payType;//支付方式

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PersonnelFiles getPaymentPersion() {
		return this.paymentPersion;
	}

	public void setPaymentPersion(PersonnelFiles paymentPersion) {
		this.paymentPersion = paymentPersion;
	}

	public CodeValue getProduceType() {
		return this.produceType;
	}

	public void setProduceType(CodeValue produceType) {
		this.produceType = produceType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public CodeValue getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(CodeValue moneyType) {
		this.moneyType = moneyType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public CodeValue getPayType() {
		return payType;
	}

	public void setPayType(CodeValue payType) {
		this.payType = payType;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

}
