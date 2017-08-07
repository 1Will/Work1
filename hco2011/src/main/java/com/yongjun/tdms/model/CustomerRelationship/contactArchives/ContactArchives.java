package com.yongjun.tdms.model.CustomerRelationship.contactArchives;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.supplier.Supplier;

public class ContactArchives extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private String name;
	private String abbreviations;
	private boolean sex = false;
	private String custName;
	private String proName;
	private String custType;
	private String industry;
	private String customerInfoCode;
	private CustomerInfo customerName;
	private ProjectInfo projectInfo;
	private CodeValue customerType;
	private CodeValue businessType;
	private Supplier supplier;
	private String duty;
	private String phone;
	private String dept = "";
	private String homePhone;
	private String fax;
	private String qq;
	private String msn;
	private String email;
	private String mobilePhone;
	private String chuanzhen;
	private String qitalink;
	private CodeValue nationality;
	private String school;
	private String professional;
	private String favorite;
	private CodeValue temperament;
	private Date birthday;
	private CodeValue type;
	private String postCode;
	private String address;
	private String birthplace;
	private String comment;
	private String enterpriseSynopsis;
	private Float customerInfoIntegrity = Float.valueOf(0.0F);
	private ContactArchives leader;
	private String honorific;
	private String idCard;
	private String weChat;
	private String postalAddress;
	private String zipCode;
	private CodeValue bloodType;
	private CodeValue constellation;
	private CodeValue chineseZodiac;
	private CodeValue enneagram;
	private CodeValue religiousBelief;
	private CodeValue health;
	private String height;
	private String weight;
	private String outline;// 联系人角色说明
	private CodeValue vision;
	private CodeValue maritalStatus;
	private CodeValue politicalOutlook;
	private CodeValue education;
	private String isSaved;// 存在并且等于0，，方可提交

	public Float getCustomerInfoIntegrity() {
		if (null == this.customerInfoIntegrity) {
			return Float.valueOf(0.0F);
		}
		return this.customerInfoIntegrity;
	}

	public void setCustomerInfoIntegrity(Float customerInfoIntegrity) {
		this.customerInfoIntegrity = customerInfoIntegrity;
	}

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ContactArchives)) {
			return false;
		}

		ContactArchives contact = (ContactArchives) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public String getAbbreviations() {
		return this.abbreviations;
	}

	public void setAbbreviations(String abbreviations) {
		this.abbreviations = abbreviations;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public CustomerInfo getCustomerName() {
		return this.customerName;
	}

	public String getChuanzhen() {
		return chuanzhen;
	}

	public void setChuanzhen(String chuanzhen) {
		this.chuanzhen = chuanzhen;
	}

	public String getQitalink() {
		return qitalink;
	}

	public void setQitalink(String qitalink) {
		this.qitalink = qitalink;
	}

	public void setCustomerName(CustomerInfo customerName) {
		this.customerName = customerName;
	}

	public CodeValue getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CodeValue customerType) {
		this.customerType = customerType;
	}

	public String getCustType() {
		return this.custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFavorite() {
		return this.favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeValue getNationality() {
		return this.nationality;
	}

	public void setNationality(CodeValue nationality) {
		this.nationality = nationality;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public boolean isSex() {
		return this.sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public CodeValue getTemperament() {
		return this.temperament;
	}

	public void setTemperament(CodeValue temperament) {
		this.temperament = temperament;
	}

	public CodeValue getType() {
		return this.type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getEnterpriseSynopsis() {
		return this.enterpriseSynopsis;
	}

	public void setEnterpriseSynopsis(String enterpriseSynopsis) {
		this.enterpriseSynopsis = enterpriseSynopsis;
	}

	public String getCustomerInfoCode() {
		return this.customerInfoCode;
	}

	public void setCustomerInfoCode(String customerInfoCode) {
		this.customerInfoCode = customerInfoCode;
	}

	public String getHonorific() {
		return honorific;
	}

	public void setHonorific(String honorific) {
		this.honorific = honorific;
	}

	public ContactArchives getLeader() {
		return leader;
	}

	public void setLeader(ContactArchives leader) {
		this.leader = leader;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public CodeValue getBloodType() {
		return bloodType;
	}

	public void setBloodType(CodeValue bloodType) {
		this.bloodType = bloodType;
	}

	public CodeValue getConstellation() {
		return constellation;
	}

	public void setConstellation(CodeValue constellation) {
		this.constellation = constellation;
	}

	public CodeValue getChineseZodiac() {
		return chineseZodiac;
	}

	public void setChineseZodiac(CodeValue chineseZodiac) {
		this.chineseZodiac = chineseZodiac;
	}

	public CodeValue getEnneagram() {
		return enneagram;
	}

	public void setEnneagram(CodeValue enneagram) {
		this.enneagram = enneagram;
	}

	public CodeValue getReligiousBelief() {
		return religiousBelief;
	}

	public void setReligiousBelief(CodeValue religiousBelief) {
		this.religiousBelief = religiousBelief;
	}

	public CodeValue getHealth() {
		return health;
	}

	public void setHealth(CodeValue health) {
		this.health = health;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public CodeValue getVision() {
		return vision;
	}

	public void setVision(CodeValue vision) {
		this.vision = vision;
	}

	public CodeValue getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(CodeValue maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public CodeValue getPoliticalOutlook() {
		return politicalOutlook;
	}

	public void setPoliticalOutlook(CodeValue politicalOutlook) {
		this.politicalOutlook = politicalOutlook;
	}

	public CodeValue getEducation() {
		return education;
	}

	public void setEducation(CodeValue education) {
		this.education = education;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

}
