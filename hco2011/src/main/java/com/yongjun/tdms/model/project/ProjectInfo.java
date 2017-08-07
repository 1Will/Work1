package com.yongjun.tdms.model.project;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class ProjectInfo extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private String name;// 项目名称
	private String code;// 项目编号
	private CustomerInfo customer;// 客户
	private ContactArchives contact;// 项目联系人
	private PersonnelFiles controller;// 项目负责人
	private String outline;// 项目概要
	private CodeValue state;// 项目状态
	private CodeValue businessType;// 项目联系人业务属性
	private User createUser;
	private String conOutline;// 联系人角色说明
	private String isSaved;// 提交判断
	private long submitNum=0;

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfo)) {
			return false;
		}

		ProjectInfo contact = (ProjectInfo) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public ContactArchives getContact() {
		return contact;
	}

	public void setContact(ContactArchives contact) {
		this.contact = contact;
	}

	public PersonnelFiles getController() {
		return controller;
	}

	public void setController(PersonnelFiles controller) {
		this.controller = controller;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public CodeValue getState() {
		return state;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}

	public String getConOutline() {
		return conOutline;
	}

	public void setConOutline(String conOutline) {
		this.conOutline = conOutline;
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
	

}
