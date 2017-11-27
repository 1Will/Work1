package com.yongjun.tdms.model.customercontract.contractmanagement;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;

import java.util.Date;

public class ContractManagement extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private String code;
	private String contractName;
	private CustomerInfo customerInfo;
	private ContactArchives linkman;
	private ProjectInfo project;
	private String address;
	private String telephone;
	private PersonnelFiles saleman;
	private Institution institution;
	private Department deparment;
	private Date ciemdinghTime;
	private Date startTime;
	private Date endTime;
	private CodeValue contractType;
	private double contractMoney;
	private double lastSubmitMoney;
	private double paidMoney;
	private double backMoney=0.0;//退款金额
	private CodeValue moneyType;
	private CodeValue payType;
	private CodeValue payWay;
	private CodeValue state;
	private String contractContent;
	private String remark;
	private Double square= Double.valueOf(0.0D);//总面积 
	private String overGet = "no";
	private String isSaved ;
	private long submitNum=0;

	private String overReturnPlan = "no";

	public String getOverGet() {
		return this.overGet;
	}

	public void setOverGet(String overGet) {
		this.overGet = overGet;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCiemdinghTime() {
		return this.ciemdinghTime;
	}

	public void setCiemdinghTime(Date ciemdinghTime) {
		this.ciemdinghTime = ciemdinghTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContractContent() {
		return this.contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public double getContractMoney() {
		return this.contractMoney;
	}

	public void setContractMoney(double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public CodeValue getContractType() {
		return this.contractType;
	}

	public void setContractType(CodeValue contractType) {
		this.contractType = contractType;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Department getDeparment() {
		return this.deparment;
	}

	public void setDeparment(Department deparment) {
		this.deparment = deparment;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ContactArchives getLinkman() {
		return this.linkman;
	}

	public void setLinkman(ContactArchives linkman) {
		this.linkman = linkman;
	}

	public CodeValue getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(CodeValue moneyType) {
		this.moneyType = moneyType;
	}

	public double getPaidMoney() {
		return this.paidMoney;
	}

	public void setPaidMoney(double paidMoney) {
		this.paidMoney = paidMoney;
	}

	public CodeValue getPayType() {
		return this.payType;
	}

	public void setPayType(CodeValue payType) {
		this.payType = payType;
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

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public CodeValue getState() {
		return this.state;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public CodeValue getPayWay() {
		return this.payWay;
	}

	public void setPayWay(CodeValue payWay) {
		this.payWay = payWay;
	}

	public String getOverReturnPlan() {
		return this.overReturnPlan;
	}

	public void setOverReturnPlan(String overReturnPlan) {
		this.overReturnPlan = overReturnPlan;
	}

	public ProjectInfo getProject() {
		return project;
	}

	public void setProject(ProjectInfo project) {
		this.project = project;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
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

	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	public double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(double backMoney) {
		this.backMoney = backMoney;
	}
	
	
}
