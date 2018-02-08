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
	private String contractName;//项目名
	private CustomerInfo customerInfo;//客户信息
	private ContactArchives linkman;
	private ProjectInfo project;//项目
	private String address;
	private String telephone;
	private PersonnelFiles saleman;
	private Institution institution;
	private Department deparment;//部门
	private Date ciemdinghTime;//签订时间
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private CodeValue contractType;
	private double contractMoney;//合同金额
	private double lastSubmitMoney;//提交之前的合同金额
	private double paidMoney;//已收金额
	private CodeValue moneyType;
	private CodeValue payType;
	private CodeValue payWay;
	private CodeValue state;
	private String contractContent;//合同内容
	private String remark;//备注
	private String overGet = "no";//合同金额是否付完
	private String isSaved ;//是否保存
	private long submitNum=0;
	
	private String twoCFourF;//两厂四方
	private String receipt;//原件
	private String back;//取消,是否返回
	
	private Date backTime;//取消世间
	private Double hasBillSum = Double.valueOf(0.0D);//已开票金额
	private String isExport;//0为不可导（未作修改），1则需要修改，2则需要插入
	
	
	public Double getHasBillSum() {
		return hasBillSum;
	}

	public void setHasBillSum(Double hasBillSum) {
		this.hasBillSum = hasBillSum;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getTwoCFourF() {
		return twoCFourF;
	}

	public void setTwoCFourF(String twoCFourF) {
		this.twoCFourF = twoCFourF;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

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

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}
	
	
}
