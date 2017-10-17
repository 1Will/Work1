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
 * 合同实体类
 */
@Entity
@Table(name = "QD_CONTRACTMANAGEMENT", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class Hcontractmanagement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Character effectflag='E';
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;
	private String code;//合同编码
	private String contractname;//合同名称
	private Integer customerInfoId;//客户ID
	private String address;//联系地址
	private String telephone;//联系电话
	private Integer salemanId;//业务员id
	private String deparment;
	private Timestamp ciemdinghtime;//签订日期
	private Timestamp starttime;//开始日期
	private Timestamp endtime;//终止日期
	private String contracttype;//合同类型
	private String contractmoney;//合同金额
	private String paidmoney;//已付金额
	private String moneytype;//货币种类
	private String paytype;//付款类型
	private String state;//状态
	private String contractcontent;//合同内容
	private String remart;//备注
	
	public Hcontractmanagement() {
	}
	
	public Hcontractmanagement(Long id) {
		this.id=id;
	}

	public Hcontractmanagement(Long id, String code, String contractname) {
		this.id = id;
		this.code = code;
		this.contractname = contractname;
	}

	public Hcontractmanagement(Long id, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons, String code, String contractname,
			 String address, String telephone, Integer salemanId,Integer customerInfoId,
			String deparment, Timestamp ciemdinghtime, Timestamp starttime, Timestamp endtime,
			String contracttype, String contractmoney, String paidmoney, String moneytype, String paytype,
			String state, String contractcontent, String remart) {
		this.id = id;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
		this.code = code;
		this.customerInfoId = customerInfoId;
		this.contractname = contractname;
		this.address = address;
		this.telephone = telephone;
		this.salemanId = salemanId;
		this.deparment = deparment;
		this.ciemdinghtime = ciemdinghtime;
		this.starttime = starttime;
		this.endtime = endtime;
		this.contracttype = contracttype;
		this.contractmoney = contractmoney;
		this.paidmoney = paidmoney;
		this.moneytype = moneytype;
		this.paytype = paytype;
		this.state = state;
		this.contractcontent = contractcontent;
		this.remart = remart;
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

	@Column(name = "CODE", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CONTRACTNAME", nullable = false, length = 20)
	public String getContractname() {
		return this.contractname;
	}

	public void setContractname(String contractname) {
		this.contractname = contractname;
	}


	@Column(name = "ADDRESS", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(name = "TELEPHONE", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "SALEMAN_ID", precision = 22, scale = 0)
	public Integer getSalemanId() {
		return this.salemanId;
	}

	public void setSalemanId(Integer salemanId) {
		this.salemanId = salemanId;
	}

	@Column(name = "DEPARMENT", length = 20)
	public String getDeparment() {
		return this.deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	@Column(name = "CIEMDINGHTIME")
	public Timestamp getCiemdinghtime() {
		return this.ciemdinghtime;
	}

	public void setCiemdinghtime(Timestamp ciemdinghtime) {
		this.ciemdinghtime = ciemdinghtime;
	}

	@Column(name = "STARTTIME")
	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	@Column(name = "ENDTIME")
	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	@Column(name = "CONTRACTTYPE", length = 20)
	public String getContracttype() {
		return this.contracttype;
	}

	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}

	@Column(name = "CONTRACTMONEY", length = 20)
	public String getContractmoney() {
		return this.contractmoney;
	}

	public void setContractmoney(String contractmoney) {
		this.contractmoney = contractmoney;
	}

	@Column(name = "PAIDMONEY", length = 20)
	public String getPaidmoney() {
		return this.paidmoney;
	}

	public void setPaidmoney(String paidmoney) {
		this.paidmoney = paidmoney;
	}

	@Column(name = "MONEYTYPE", length = 20)
	public String getMoneytype() {
		return this.moneytype;
	}

	public void setMoneytype(String moneytype) {
		this.moneytype = moneytype;
	}

	@Column(name = "PAYTYPE", length = 20)
	public String getPaytype() {
		return this.paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "CONTRACTCONTENT", length = 500)
	public String getContractcontent() {
		return this.contractcontent;
	}

	public void setContractcontent(String contractcontent) {
		this.contractcontent = contractcontent;
	}

	@Column(name = "REMART", length = 500)
	public String getRemart() {
		return this.remart;
	}

	public void setRemart(String remart) {
		this.remart = remart;
	}

	@Column(name = "CUSTOMERINFO_ID", precision = 22, scale = 0)
	public Integer getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(Integer customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

}
