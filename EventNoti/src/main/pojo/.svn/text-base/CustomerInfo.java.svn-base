package main.pojo;


import java.util.Date;


public class CustomerInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code; // 代码 
	private Long version; //版本
	private Integer disabled; //失效
	
	private String customerName;   //客户名称
	private Date setupTime; //公司创建时间 
	private Date archiveTime; //存档日期 
	private Long customerType;//客户状态
	private Long companyNature;//企业性质
	private Long industry;// 行业
	private Long step;//客户等级 
	private Long country;// 国家
	private Long province;//省份
	private Long city;//城市
	private Long resource;//信息来源
	private String web; //公司网站
	private String address;        //地址
	private String businessScope; //业务范围 
	private Long backVisitSum = 0L; //回访次数
	private String keyContacter;   //主要联系人
	private String sex; //性别
	private String dept; //部门
	private String duty; //职务
	private String mobilePhone;
	private String phone;
	private String effectDescribe; //印象描述 
	private String advisoryContent; //咨询内容 
	private String parlorDept; //所属部门
	
	private String saleman; //业务员 !!联表获取 
	private Long businessmanId; //业务员Id 非空
	
	private Date lastModifiedTime; //最后跟进时间 后台处理
	private Date createdTime; //开始跟进时间 后台处理
	private Long state; //固定值
	private String creatorName;//开始创建人
	private String lastOperator;//最后修改人
	private Double registeredCapital; //注册资本 
	private Integer personCount; //公司人数
	private Long unconnect;
	
	/*	//未使用字段
	
	private String abbreviations; //公司简称 
	private String legalPerson ; //法人代表
	private String email; //
	private String qq; //
	
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
	
	public String getKeyContacter() {
		return keyContacter;
	}
	
	public void setKeyContacter(String keyContacter) {
		this.keyContacter = keyContacter;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}


	public CustomerInfo(Long gid, String gname) {
		this.id = gid;
		this.customerName = gname;
	}
	public CustomerInfo() {
    }

	public Date getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(Date setupTime) {
		this.setupTime = setupTime;
	}

	public String getEffectDescribe() {
		return effectDescribe;
	}

	public void setEffectDescribe(String effectDescribe) {
		this.effectDescribe = effectDescribe;
	}

	public String getAdvisoryContent() {
		return advisoryContent;
	}

	public void setAdvisoryContent(String advisoryContent) {
		this.advisoryContent = advisoryContent;
	}

	public String getParlorDept() {
		return parlorDept;
	}

	public void setParlorDept(String parlorDept) {
		this.parlorDept = parlorDept;
	}

	public String getSaleman() {
		return saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Long customerType) {
		this.customerType = customerType;
	}

	public Long getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(Long companyNature) {
		this.companyNature = companyNature;
	}

	public Long getIndustry() {
		return industry;
	}

	public void setIndustry(Long industry) {
		this.industry = industry;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getResource() {
		return resource;
	}

	public void setResource(Long resource) {
		this.resource = resource;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Long getBusinessmanId() {
		return businessmanId;
	}

	public void setBusinessmanId(Long businessmanId) {
		this.businessmanId = businessmanId;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public Long getUnconnect() {
		return unconnect;
	}

	public void setUnconnect(Long unconnect) {
		this.unconnect = unconnect;
	}
    
	public Long getBackVisitSum() {
		return backVisitSum;
	}
	public void setBackVisitSum(Long backVisitSum) {
		this.backVisitSum = backVisitSum;
	}

	
}