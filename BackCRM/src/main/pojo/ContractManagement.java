package main.pojo;

import java.util.Date;    // implements Serializable 

public class ContractManagement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version; //版本
	private String code;
	private String contractName;  // 合同名称
	private CustomerInfo customerInfo;
	private ContactArchives linkman;
	private ProjectInfo project;
	private String address;
	private String telephone;
	private PersonnelFiles saleman;
	private Department dept;
	private Date ciemdinghTime;
	private Date startTime;
	private Date endTime;
	private CodeValue contractType;
	private Double contractMoney; //合同金额
	private Double paidMoney;  // 已付金额
	private CodeValue moneyType;
	private CodeValue payType;
	private CodeValue payWay;
	private CodeValue state;
	private String contractContent;
	private String remark;
	private String overGet = "no";
	private String overReturnPlan = "no";
	private String isSaved;
	private Long submitNum;
	private Double lastSubmitMoney;
	
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

    
	public Double getContractMoney() {
		return contractMoney;
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

    

	public Double getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(Double paidMoney) {
		this.paidMoney = paidMoney;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Long getSubmitNum() {
		return submitNum;
	}

	public void setSubmitNum(Long submitNum) {
		this.submitNum = submitNum;
	}

	public Double getLastSubmitMoney() {
		return lastSubmitMoney;
	}

	public void setLastSubmitMoney(Double lastSubmitMoney) {
		this.lastSubmitMoney = lastSubmitMoney;
	}
	
	
	
	
}
