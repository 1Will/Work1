package com.github.wp.business.pojo.custom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

/**
 * 客户信息
 */
@Entity
@Table(name = "KH_CUSTOMERINFO", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class Bcustomerinfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private String nameShort;//客户简称
	private String nature;//企业性质
	private String industry;//行业
	private String corporate;//企业法人
	private String registerCapital;//注册资金
	private String employeeNum;//员工人数
	private Timestamp createDate;
	private String customerType;//客户状态
	private String step;
	private Integer countryId;//省
	private Integer provinceId;//市
	private Integer cityId;//县
	private Timestamp archiveTime;//存档日期
	private String address;//地址
	private String businessScope;//企业简介
	private Character effectflag = 'E';
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;
	private String maorContact;//主要联系人
	private String isornot;//联系人性别
	private String dept;//联系人部门
	private String duty;//联系人职位
	private String phone;
	private String mobile;
	private String email;
	private String qq;
	private String fex;
	private String postCode;
	private String effectDescribe;//印象描述
	private Integer businessmanId;//业务员
	private Integer parlorDept;//业务员部门
	private String customerInfoIntegrity;//资料完整度
	private String advisoryContent;//咨询内容
	private String source;//信息来源
	
	@JsonIgnore
	private List<Bcontactarchives> contactList = new ArrayList<Bcontactarchives>();
	
	public Bcustomerinfo() {
	}
	
	public Bcustomerinfo(Long id) {
		this.id=id;
	}

	public Bcustomerinfo(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Bcustomerinfo(Long id, String code, String name, String nameShort, String nature, String industry,
			String corporate, String registerCapital, String employeeNum, Timestamp createDate, String customerType,
			String step, Integer countryId, Integer provinceId, Integer cityId, Timestamp archiveTime,
			String address, String businessScope, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons, String maorContact, String isornot,
			String dept, String duty, String phone, String mobile, String email, String qq, String fex,
			String postCode, String effectDescribe, Integer businessmanId, Integer parlorDept,
			String customerInfoIntegrity, String advisoryContent, String source) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.nameShort = nameShort;
		this.nature = nature;
		this.industry = industry;
		this.corporate = corporate;
		this.registerCapital = registerCapital;
		this.employeeNum = employeeNum;
		this.createDate = createDate;
		this.customerType = customerType;
		this.step = step;
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.archiveTime = archiveTime;
		this.address = address;
		this.businessScope = businessScope;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
		this.maorContact = maorContact;
		this.isornot = isornot;
		this.dept = dept;
		this.duty = duty;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.qq = qq;
		this.fex = fex;
		this.postCode = postCode;
		this.effectDescribe = effectDescribe;
		this.businessmanId = businessmanId;
		this.parlorDept = parlorDept;
		this.customerInfoIntegrity = customerInfoIntegrity;
		this.advisoryContent = advisoryContent;
		this.source = source;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CODE", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_SHORT", length = 20)
	public String getNameShort() {
		return this.nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	@Column(name = "NATURE", length = 20)
	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	@Column(name = "INDUSTRY", length = 20)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "CORPORATE", length = 20)
	public String getCorporate() {
		return this.corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	@Column(name = "REGISTER_CAPITAL", length = 20)
	public String getRegisterCapital() {
		return this.registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	@Column(name = "EMPLOYEE_NUM", length = 20)
	public String getEmployeeNum() {
		return this.employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	@Column(name = "CREATE_DATE")
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CUSTOMER_TYPE", length = 20)
	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Column(name = "STEP", length = 20)
	public String getStep() {
		return this.step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	@Column(name = "COUNTRY_ID", precision = 22, scale = 0)
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "PROVINCE_ID", precision = 22, scale = 0)
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "CITY_ID", precision = 22, scale = 0)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "ARCHIVE_TIME")
	public Timestamp getArchiveTime() {
		return this.archiveTime;
	}

	public void setArchiveTime(Timestamp archiveTime) {
		this.archiveTime = archiveTime;
	}

	@Column(name = "ADDRESS", length = 30)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BUSINESS_SCOPE", length = 200)
	public String getBusinessScope() {
		return this.businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	@Column(name = "EFFECTFLAG", length = 1)
	public Character getEffectflag() {
		return this.effectflag;
	}

	public void setEffectflag(Character effectflag) {
		this.effectflag = effectflag;
	}

	@Column(name = "CREATED_TIME")
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "CREATOR", length = 20)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "LAST_MODIFIED_TIME")
	public Timestamp getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Column(name = "LAST_OPERATOR", length = 20)
	public String getLastOperator() {
		return this.lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	@Column(name = "VERSONS", precision = 22, scale = 0)
	public Integer getVersons() {
		return this.versons;
	}

	public void setVersons(Integer versons) {
		this.versons = versons;
	}

	@Column(name = "MAOR_CONTACT", length = 20)
	public String getMaorContact() {
		return this.maorContact;
	}

	public void setMaorContact(String maorContact) {
		this.maorContact = maorContact;
	}

	@Column(name = "ISORNOT", length = 2)
	public String getIsornot() {
		return this.isornot;
	}

	public void setIsornot(String isornot) {
		this.isornot = isornot;
	}

	@Column(name = "DEPT", length = 20)
	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "DUTY", length = 20)
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL", length = 20)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "QQ", length = 20)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "FEX", length = 20)
	public String getFex() {
		return this.fex;
	}

	public void setFex(String fex) {
		this.fex = fex;
	}

	@Column(name = "POST_CODE", length = 20)
	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "EFFECT_DESCRIBE", length = 100)
	public String getEffectDescribe() {
		return this.effectDescribe;
	}

	public void setEffectDescribe(String effectDescribe) {
		this.effectDescribe = effectDescribe;
	}

	@Column(name = "BUSINESSMAN_ID", precision = 22, scale = 0)
	public Integer getBusinessmanId() {
		return this.businessmanId;
	}

	public void setBusinessmanId(Integer businessmanId) {
		this.businessmanId = businessmanId;
	}

	@Column(name = "PARLOR_DEPT", precision = 22, scale = 0)
	public Integer getParlorDept() {
		return this.parlorDept;
	}

	public void setParlorDept(Integer parlorDept) {
		this.parlorDept = parlorDept;
	}

	@Column(name = "CUSTOMER_INFO_INTEGRITY", length = 20)
	public String getCustomerInfoIntegrity() {
		return this.customerInfoIntegrity;
	}

	public void setCustomerInfoIntegrity(String customerInfoIntegrity) {
		this.customerInfoIntegrity = customerInfoIntegrity;
	}

	@Column(name = "ADVISORY_CONTENT", length = 100)
	public String getAdvisoryContent() {
		return this.advisoryContent;
	}

	public void setAdvisoryContent(String advisoryContent) {
		this.advisoryContent = advisoryContent;
	}

	@Column(name = "SOURCE", length = 20)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customInfo")
	@Where(clause="EFFECTFLAG='E'")
	public List<Bcontactarchives> getContactList() {
		return contactList;
	}

	public void setContactList(List<Bcontactarchives> contactList) {
		this.contactList = contactList;
	}

}
