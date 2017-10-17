package com.github.wp.business.pojo.qiandan;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * 回款计划
 */
@Entity
@Table(name = "QD_RETURN_PLAN", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class HReturnPlan implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Character effectflag='E';//状态标识
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;
	private Integer contractManagerId;//合同Id
	private String phone;//联系电话
	private Timestamp planDate;//计划日期
	private String payment;//付款方式
	private String batch;//批次
	private String yingfu;//应付金额
	private String factSum;//付款金额
	private String currency;//货币种类
	private Character notOrNot;//是否提醒
	private Character billingOrIs;//是否开票
	private Character isOrNot;//是否到款
	private Timestamp paytime;//付款日期
	private Integer payeeId;//收款人id
	private String remark;//备注
	
	
	public HReturnPlan() {
	}

	public HReturnPlan(Long id) {
		this.id = id;
	}

	public HReturnPlan(Long id, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons,Integer contractManagerId,String phone, Timestamp planDate, String payment,
			String batch, String yingfu, String factSum, String currency, Character notOrNot, Character billingOrIs,
			Character isOrNot, Timestamp paytime, Integer payeeId, String remark) {
		this.id = id;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
		this.contractManagerId=contractManagerId;
		this.phone = phone;
		this.planDate = planDate;
		this.payment = payment;
		this.batch = batch;
		this.yingfu = yingfu;
		this.factSum = factSum;
		this.currency = currency;
		this.notOrNot = notOrNot;
		this.billingOrIs = billingOrIs;
		this.isOrNot = isOrNot;
		this.paytime = paytime;
		this.payeeId = payeeId;
		this.remark = remark;
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
	
	public void setVersons(Integer versons){
		this.versons = versons;
	}
	
	@Column(name = "CONTRACT_MANAGEMENT_ID", precision = 22, scale = 0)
	public Integer getContractManagerId() {
		return this.contractManagerId;
	}

	public void setContractManagerId(Integer contractManagerId) {
		this.contractManagerId = contractManagerId;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "PLAN_DATE")
	public Timestamp getPlanDate() {
		return this.planDate;
	}

	public void setPlanDate(Timestamp planDate) {
		this.planDate = planDate;
	}

	@Column(name = "PAYMENT", length = 20)
	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Column(name = "BATCH", length = 20)
	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Column(name = "YINGFU", length = 20)
	public String getYingfu() {
		return this.yingfu;
	}

	public void setYingfu(String yingfu) {
		this.yingfu = yingfu;
	}

	@Column(name = "FACT_SUM", length = 20)
	public String getFactSum() {
		return this.factSum;
	}

	public void setFactSum(String factSum) {
		this.factSum = factSum;
	}

	@Column(name = "CURRENCY", length = 20)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "NOT_OR_NOT", length = 1)
	public Character getNotOrNot() {
		return this.notOrNot;
	}

	public void setNotOrNot(Character notOrNot) {
		this.notOrNot = notOrNot;
	}

	@Column(name = "BILLING_OR_IS", length = 1)
	public Character getBillingOrIs() {
		return this.billingOrIs;
	}

	public void setBillingOrIs(Character billingOrIs) {
		this.billingOrIs = billingOrIs;
	}

	@Column(name = "IS_OR_NOT", length = 1)
	public Character getIsOrNot() {
		return this.isOrNot;
	}

	public void setIsOrNot(Character isOrNot) {
		this.isOrNot = isOrNot;
	}

	@Column(name = "PAYTIME")
	public Timestamp getPaytime() {
		return this.paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	@Column(name = "PAYEE_ID", precision = 22, scale = 0)
	public Integer getPayeeId() {
		return this.payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
