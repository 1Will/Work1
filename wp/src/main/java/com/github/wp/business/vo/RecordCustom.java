package com.github.wp.business.vo;

import java.sql.Timestamp;


/**
 * 开票记录--合同--客户 字段合集表
 */

public class RecordCustom implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String code;//合同编码
	private String contractname;//合同名称
	private String customCode;//客户编码
	private String custoName;//客户名称
	private String recordCode;//发票编码
	private String jine;//金额
	private String currency;//货币种类
	private String invoiceTitle;//发票抬头
	private Timestamp billingTime;//开票时间
	
	
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
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getJine() {
		return jine;
	}
	public void setJine(String jine) {
		this.jine = jine;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public Timestamp getBillingTime() {
		return billingTime;
	}
	public void setBillingTime(Timestamp billingTime) {
		this.billingTime = billingTime;
	}
	
	
	
}
