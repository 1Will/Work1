package main.pojo;

import java.util.Date;

public class ContactArchives implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version; //版本
	private Integer disabled; //失效
	private String contactName;  //联系人
	private Long leaderId; //上级领导
	private String mobilePhone;        //手机号
	private String phone;         //电话
	private Integer sex;       //性别
	private String address;    //地址
	private Date   birthday;    //生日
	private String honorific;   //称呼
	private String duty;        //职位
	private String customerName;  //所属公司名称（客户名称）
	private String industry;     //行业
	private String dept;         //部门
	private String projectName;   //项目名称  未使用
	private String remark;   //备注
	private String enterpriseSynopsis;//印象描述
	private String email;
	private String qq;
	private CodeValue type; //熟悉程度
	private java.util.Date createdTime;//创建时间
	private String creatorName;//创建人
	private String lastOperator;//最后修改人
	private Date   lastModifiedTime;  //最后修改时间
	private Long customerId;//客户Id
	private CodeValue customerTypeId;//客户类型
	private String customerInfoCode;//客户编号
	private String isSaved;
	private Float cusInfoIntegrity; //客户信息完整度
	

	/*//后面属性未使用
	private String  homePhone;  //家庭电话 
	private String  enterpriseSynopsis;  //印象描述
	private String  idCard;  //身份证
	private String  weChat;  //微信 
	private String  postalAddress;  //通讯地址
	private String  custType;  //顾客类型 
	*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date date) {
		this.birthday =  date;
	}
	public String getHonorific() {
		return honorific;
	}
	public void setHonorific(String honorific) {
		this.honorific = honorific;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getCustomerInfoCode() {
		return customerInfoCode;
	}
	public void setCustomerInfoCode(String customerInfoCode) {
		this.customerInfoCode = customerInfoCode;
	}

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getEnterpriseSynopsis() {
		return enterpriseSynopsis;
	}
	public void setEnterpriseSynopsis(String enterpriseSynopsis) {
		this.enterpriseSynopsis = enterpriseSynopsis;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	public CodeValue getType() {
		return type;
	}
	public void setType(CodeValue type) {
		this.type = type;
	}
	public java.util.Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = createdTime;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	

	public CodeValue getCustomerTypeId() {
		return customerTypeId;
	}
	public void setCustomerTypeId(CodeValue customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public Float getCusInfoIntegrity() {
		return cusInfoIntegrity;
	}
	public void setCusInfoIntegrity(Float cusInfoIntegrity) {
		this.cusInfoIntegrity = cusInfoIntegrity;
	}
    
	
    
	
}