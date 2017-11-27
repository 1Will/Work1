package com.yongjun.tdms.model.CustomerRelationship.technology;


import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

public class Technology extends BaseInfoEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String name;//知识产权名称
	private CodeValue genre;//知识产权类别
	private Date  applyTime;//申请时间
	private Date  authorizeTime;//授权公告日
	private Date  applyPassTime;//申请/实审/国家阶段时间
	private String patentNumber;//专利号
	private String applyNumber;//申请号
	private CodeValue type;//知识产权类型（申请/授权）
	private CustomerInfo  customerInfo;
	public Date getApplyPassTime() {
		return applyPassTime;
	}

	public void setApplyPassTime(Date applyPassTime) {
		this.applyPassTime = applyPassTime;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public CodeValue getType() {
		return type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeValue getGenre() {
		return genre;
	}

	public void setGenre(CodeValue genre) {
		this.genre = genre;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuthorizeTime() {
		return authorizeTime;
	}

	public void setAuthorizeTime(Date authorizeTime) {
		this.authorizeTime = authorizeTime;
	}

	public String getPatentNumber() {
		return patentNumber;
	}

	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
