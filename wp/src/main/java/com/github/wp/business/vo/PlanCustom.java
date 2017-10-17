package com.github.wp.business.vo;

import java.sql.Timestamp;


/**
 * 回款计划--客户 字段合集表
 */

public class PlanCustom implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String code;//合同编码
	private String contractname;//合同名称
	private String customCode;//客户编码
	private String custoName;//客户名称
	private String phone;//联系电话
	private Character isOrNot;//是否到款
	private Character billingOrIs;//是否开票
	private String yingfu;//应付金额
	private String batch;//批次
	private Timestamp planDate;//计划日期
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContractname() {
		return contractname;
	}
	public void setContractname(String contractname) {
		this.contractname = contractname;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getCustoName() {
		return custoName;
	}
	public void setCustoName(String custoName) {
		this.custoName = custoName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Character getIsOrNot() {
		return isOrNot;
	}
	public void setIsOrNot(Character isOrNot) {
		this.isOrNot = isOrNot;
	}
	public Character getBillingOrIs() {
		return billingOrIs;
	}
	public void setBillingOrIs(Character billingOrIs) {
		this.billingOrIs = billingOrIs;
	}
	public String getYingfu() {
		return yingfu;
	}
	public void setYingfu(String yingfu) {
		this.yingfu = yingfu;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Timestamp getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Timestamp planDate) {
		this.planDate = planDate;
	}
	
	
}
