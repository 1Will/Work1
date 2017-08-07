package com.yongjun.tdms.model.CustomerRelationship.customerProfiles;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.advisory.Advisory;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class CustomerInfo extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String abbreviations;
	private CodeValue customerType;
	private CodeValue classification;
	private CodeValue businessType;
	private String isPartner;//1 是0或者null
	private CodeValue industry;
	private CodeValue companyNature;
	private String legalPerson;
	private Area country;
	private Area province;
	private Area city;
	private String keyContacter;
	private String telphone;
	private String mobilePhone;
	private String chuanzhen;
	private String qitalink;
	private String web;
	private String fax;
	private String email;
	private String qq;
	private Date setupTime;
	private Double registeredCapital = Double.valueOf(0.0D);
	private Integer personCount = Integer.valueOf(0);
	private String saleman;
	private PersonnelFiles salesman;
	private String postCode;
	private String address;
	private String businessScope;
	private String dept;
	private String duty;
	private String isOrNot;
	private CodeValue familiarityType;
	private CusAdditionalInfo additional;
	private CodeValue step;
	private CodeValue state;
	private Set<ContactArchives> contact = new HashSet();
	private Set<Advisory> advisory = new HashSet();
	private String effectDescribe;
	private String advisoryContent;
	private Date archiveTime;
	private String parlorDept;
	private CodeValue resource;
	private Float customerInfoIntegrity = Float.valueOf(0.0F);
	private Long unconnect = Long.valueOf(0L);
	private Long backVisitSum = Long.valueOf(0L);
	private Date nearestBackVisitDate;
	private String isSaved;// 存在并且等于0，，方可提交

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

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Float getCustomerInfoIntegrity() {
		if (null == this.customerInfoIntegrity) {
			return Float.valueOf(0.0F);
		}
		return this.customerInfoIntegrity;
	}

	public void setCustomerInfoIntegrity(Float customerInfoIntegrity) {
		this.customerInfoIntegrity = customerInfoIntegrity;
	}

	public Set<Advisory> getAdvisory() {
		return this.advisory;
	}

	public void setAdvisory(Set<Advisory> advisory) {
		this.advisory = advisory;
	}

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof CustomerInfo)) {
			return false;
		}

		CustomerInfo customer = (CustomerInfo) arg0;

		if (!customer.getId().equals(getId())) {
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

	public CusAdditionalInfo getAdditional() {
		return this.additional;
	}

	public void setAdditional(CusAdditionalInfo additional) {
		this.additional = additional;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessScope() {
		return this.businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public Area getCity() {
		return this.city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CodeValue getCompanyNature() {
		return this.companyNature;
	}

	public void setCompanyNature(CodeValue companyNature) {
		this.companyNature = companyNature;
	}

	public Set<ContactArchives> getContact() {
		return this.contact;
	}

	public void setContact(Set<ContactArchives> contact) {
		this.contact = contact;
	}

	public Area getCountry() {
		return this.country;
	}

	public void setCountry(Area country) {
		this.country = country;
	}

	public CodeValue getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CodeValue customerType) {
		this.customerType = customerType;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public CodeValue getIndustry() {
		return this.industry;
	}

	public void setIndustry(CodeValue industry) {
		this.industry = industry;
	}

	public String getKeyContacter() {
		return this.keyContacter;
	}

	public void setKeyContacter(String keyContacter) {
		this.keyContacter = keyContacter;
	}

	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPersonCount() {
		return this.personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Area getProvince() {
		return this.province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Double getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getSaleman() {
		return this.saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	public PersonnelFiles getSalesman() {
		return this.salesman;
	}

	public void setSalesman(PersonnelFiles salesman) {
		this.salesman = salesman;
	}

	public Date getSetupTime() {
		return this.setupTime;
	}

	public void setSetupTime(Date setupTime) {
		this.setupTime = setupTime;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public CodeValue getResource() {
		return this.resource;
	}

	public void setResource(CodeValue resource) {
		this.resource = resource;
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

	public CodeValue getFamiliarityType() {
		return this.familiarityType;
	}

	public void setFamiliarityType(CodeValue familiarityType) {
		this.familiarityType = familiarityType;
	}

	public String getIsOrNot() {
		return this.isOrNot;
	}

	public void setIsOrNot(String isOrNot) {
		this.isOrNot = isOrNot;
	}

	public CodeValue getStep() {
		return this.step;
	}

	public void setStep(CodeValue step) {
		this.step = step;
	}

	public String getAdvisoryContent() {
		return this.advisoryContent;
	}

	public void setAdvisoryContent(String advisoryContent) {
		this.advisoryContent = advisoryContent;
	}

	public String getParlorDept() {
		return this.parlorDept;
	}

	public void setParlorDept(String parlorDept) {
		this.parlorDept = parlorDept;
	}

	public Date getArchiveTime() {
		return this.archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public String getEffectDescribe() {
		return this.effectDescribe;
	}

	public void setEffectDescribe(String effectDescribe) {
		this.effectDescribe = effectDescribe;
	}

	public CodeValue getState() {
		return state;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public Long getUnconnect() {
		return this.unconnect;
	}

	public void setUnconnect(Long unconnect) {
		this.unconnect = unconnect;
	}

	public Long getBackVisitSum() {
		return this.backVisitSum;
	}

	public void setBackVisitSum(Long backVisitSum) {
		this.backVisitSum = backVisitSum;
	}

	public Date getNearestBackVisitDate() {
		return nearestBackVisitDate;
	}

	public void setNearestBackVisitDate(Date nearestBackVisitDate) {
		this.nearestBackVisitDate = nearestBackVisitDate;
	}

	public CodeValue getClassification() {
		return classification;
	}

	public void setClassification(CodeValue classification) {
		this.classification = classification;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}

	public String getIsPartner() {
		return isPartner;
	}

	public void setIsPartner(String isPartner) {
		this.isPartner = isPartner;
	}
	
	

}
