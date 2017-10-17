package com.github.wp.business.pojo.custom;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

/**
 * 联系人
 */
@Entity
@Table(name = "KH_CONTACTARCHIVES", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class Bcontactarchives implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name; //联系人名称
	private Character sex;
	private String dept;//
	private String industry;//行业
	private String office;//职位
	private String mobile;
	private String phone;
	private String homePhone;
	private String qq;
	private String email;
	private String enterpriseSynopsis;//印象描述
	private String nativePlace;//籍贯
	private String nationality;//民族
	private String school;//学校
	private String profession;//专业
	private String interest;//兴趣
	private Timestamp birthDate;
	private String character;//性格
	private String type;//熟悉程度
	private String customerInfoIntegrity;//信息完整度
	private String homeAddress;	
	private String remarks;
	
	private Integer versons;
	private Character effectflag='E';
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	
	//客户类
	@JsonIgnore
	private Bcustomerinfo customInfo;

	public Bcontactarchives() {
	}
	
	public Bcontactarchives(Long id) {
		this.id=id;
	}

	public Bcontactarchives(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Bcontactarchives(Long id, String name, Character sex, String dept,
			String industry, String office, String mobile, String phone, String homePhone, String qq, String email,
			String enterpriseSynopsis, String nativePlace, String nationality, String school, String profession,
			String interest, Timestamp birthDate, String character, String type, String customerInfoIntegrity,
			String homeAddress, String remarks, Integer versons, Character effectflag, Timestamp createdTime,
			String creator, Timestamp lastModifiedTime, String lastOperator) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.dept = dept;
		this.industry = industry;
		this.office = office;
		this.mobile = mobile;
		this.phone = phone;
		this.homePhone = homePhone;
		this.qq = qq;
		this.email = email;
		this.enterpriseSynopsis = enterpriseSynopsis;
		this.nativePlace = nativePlace;
		this.nationality = nationality;
		this.school = school;
		this.profession = profession;
		this.interest = interest;
		this.birthDate = birthDate;
		this.character = character;
		this.type = type;
		this.customerInfoIntegrity = customerInfoIntegrity;
		this.homeAddress = homeAddress;
		this.remarks = remarks;
		this.versons = versons;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
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

	@Column(name = "NAME", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 1)
	public Character getSex() {
		return this.sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	@Column(name = "DEPT", length = 20)
	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "INDUSTRY", length = 20)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "OFFICE", length = 20)
	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "HOME_PHONE", length = 20)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "QQ", length = 20)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "EMAIL", length = 20)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ENTERPRISE_SYNOPSIS", length = 200)
	public String getEnterpriseSynopsis() {
		return this.enterpriseSynopsis;
	}

	public void setEnterpriseSynopsis(String enterpriseSynopsis) {
		this.enterpriseSynopsis = enterpriseSynopsis;
	}

	@Column(name = "NATIVE_PLACE", length = 40)
	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@Column(name = "NATIONALITY", length = 20)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "SCHOOL", length = 20)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "PROFESSION", length = 20)
	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "INTEREST", length = 128)
	public String getInterest() {
		return this.interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Column(name = "BIRTH_DATE")
	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "CHARACTER", length = 40)
	public String getCharacter() {
		return this.character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CUSTOMER_INFO_INTEGRITY", length = 20)
	public String getCustomerInfoIntegrity() {
		return this.customerInfoIntegrity;
	}

	public void setCustomerInfoIntegrity(String customerInfoIntegrity) {
		this.customerInfoIntegrity = customerInfoIntegrity;
	}

	@Column(name = "HOME_ADDRESS", length = 20)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "REMARKS", length = 100)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "VERSONS", precision = 22, scale = 0)
	public Integer getVersons() {
		return this.versons;
	}

	public void setVersons(Integer versons) {
		this.versons = versons;
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


	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="customer_id")
	public Bcustomerinfo getCustomInfo() {
		return customInfo;
	}

	public void setCustomInfo(Bcustomerinfo customInfo) {
		this.customInfo = customInfo;
	}

	
}
