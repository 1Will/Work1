package com.github.wp.business.pojo.cw;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * 费用使用
 */
@Entity
@Table(name = "CW_EXPENSEUSE", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class CExpenseuse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Character effectflag = 'E';
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;
	private String userperson;//使用人
	private String usedmoney;//使用金额
	private Integer customerinfoId;//关联客户
	private String linkman;//关联联系人
	private String contractmanagement;//关联合同
	private String remark;//备注
	
	private CExpenseapply apply;

	public CExpenseuse() {
	}
	
	public CExpenseuse(Long id) {
		this.id = id;
	}


	public CExpenseuse(Long id, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons,
			String userperson, String usedmoney, Integer customerinfoId, String linkman, String contractmanagement,
			String remark) {
		this.id = id;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
		this.userperson = userperson;
		this.usedmoney = usedmoney;
		this.customerinfoId = customerinfoId;
		this.linkman = linkman;
		this.contractmanagement = contractmanagement;
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

	public void setVersons(Integer versons) {
		this.versons = versons;
	}

	@Column(name = "USERPERSON", length = 20)
	public String getUserperson() {
		return this.userperson;
	}

	public void setUserperson(String userperson) {
		this.userperson = userperson;
	}

	@Column(name = "USEDMONEY", length = 20)
	public String getUsedmoney() {
		return this.usedmoney;
	}

	public void setUsedmoney(String usedmoney) {
		this.usedmoney = usedmoney;
	}

	@Column(name = "CUSTOMERINFO_ID", precision = 22, scale = 0)
	public Integer getCustomerinfoId() {
		return this.customerinfoId;
	}

	public void setCustomerinfoId(Integer customerinfoId) {
		this.customerinfoId = customerinfoId;
	}

	@Column(name = "LINKMAN", length = 20)
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "CONTRACTMANAGEMENT", length = 20)
	public String getContractmanagement() {
		return this.contractmanagement;
	}

	public void setContractmanagement(String contractmanagement) {
		this.contractmanagement = contractmanagement;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="apply_id")
	@Where(clause="EFFECTFLAG='E'")
	public CExpenseapply getApply() {
		return apply;
	}

	public void setApply(CExpenseapply apply) {
		this.apply = apply;
	}


}
