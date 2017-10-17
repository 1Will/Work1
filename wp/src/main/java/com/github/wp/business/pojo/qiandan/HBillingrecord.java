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
 * 开票记录
 */
@Entity
@Table(name = "QD_BILLINGRECORD", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class HBillingrecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Character effectflag = 'E';
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;
	private Integer contractManagementId;//合同id
	private Integer payeeId;//开票人id
	private Integer customerinfoId;//客户id
	private Integer contactId;//联系人id
	private String code;//发票编码
	private String invoiceTitle;//发票抬头
	private Timestamp billingTime;//开票时间
	private String jine;//金额
	private String currency;//金额备注货币种类
	private String content;//发票内容

	public HBillingrecord() {
	}

	public HBillingrecord(Long id) {
		this.id = id;
	}

	public HBillingrecord(Long id, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons, Integer contractManagementId,
			Integer payeeId, Integer customerinfoId, Integer contactId, String code, String invoiceTitle,
			Timestamp billingTime, String jine, String currency, String content) {
		this.id = id;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
		this.contractManagementId = contractManagementId;
		this.payeeId = payeeId;
		this.customerinfoId = customerinfoId;
		this.contactId = contactId;
		this.code = code;
		this.invoiceTitle = invoiceTitle;
		this.billingTime = billingTime;
		this.jine = jine;
		this.currency = currency;
		this.content = content;
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

	public void setVersons(Integer versons) {
		this.versons = versons;
	}

	@Column(name = "CONTRACT_MANAGEMENT_ID", precision = 22, scale = 0)
	public Integer getContractManagementId() {
		return this.contractManagementId;
	}

	public void setContractManagementId(Integer contractManagementId) {
		this.contractManagementId = contractManagementId;
	}

	@Column(name = "PAYEE_ID", precision = 22, scale = 0)
	public Integer getPayeeId() {
		return this.payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	@Column(name = "CUSTOMERINFO_ID", precision = 22, scale = 0)
	public Integer getCustomerinfoId() {
		return this.customerinfoId;
	}

	public void setCustomerinfoId(Integer customerinfoId) {
		this.customerinfoId = customerinfoId;
	}

	@Column(name = "CONTACT_ID", precision = 22, scale = 0)
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "INVOICE_TITLE", length = 200)
	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	@Column(name = "BILLING_TIME")
	public Timestamp getBillingTime() {
		return this.billingTime;
	}

	public void setBillingTime(Timestamp billingTime) {
		this.billingTime = billingTime;
	}

	@Column(name = "JINE", length = 20)
	public String getJine() {
		return this.jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}

	@Column(name = "CURRENCY", length = 20)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "CONTENT", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
