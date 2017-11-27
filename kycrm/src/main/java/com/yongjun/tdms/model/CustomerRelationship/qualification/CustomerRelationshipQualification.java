package com.yongjun.tdms.model.CustomerRelationship.qualification;


import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

public class CustomerRelationshipQualification extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	
	
	private String name;//资质名称
	private CodeValue level;//资质级别
	private String certificateNumber;//证书号/批准号
	private Date obtainDate;//获取时间
	private String approvalUnit;//批准单位
	private CustomerInfo customerName;
	@Override

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof CustomerRelationshipQualification)) {
			return false;
		}

		CustomerRelationshipQualification contact = (CustomerRelationshipQualification) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeValue getLevel() {
		return level;
	}

	public void setLevel(CodeValue level) {
		this.level = level;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Date getObtainDate() {
		return obtainDate;
	}

	public void setObtainDate(Date obtainDate) {
		this.obtainDate = obtainDate;
	}

	public String getApprovalUnit() {
		return approvalUnit;
	}

	public void setApprovalUnit(String approvalUnit) {
		this.approvalUnit = approvalUnit;
	}

	public CustomerInfo getCustomerName() {
		return customerName;
	}

	public void setCustomerName(CustomerInfo customerName) {
		this.customerName = customerName;
	}
	

	
}
