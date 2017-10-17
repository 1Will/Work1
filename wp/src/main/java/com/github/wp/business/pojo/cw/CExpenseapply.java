package com.github.wp.business.pojo.cw;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

/**
 * 费用申请
 */
@Entity
@Table(name = "CW_EXPENSEAPPLY", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class CExpenseapply implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Character effectflag = 'E';
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;
	private String code;
	private String expensename;//费用名称
	private String applyperson;//申请人
	private String deparment;//部门
	private String expensetype;//费用方式
	private String applymoney;//申请金额
	private Timestamp applytime;//申请时间
	private String applystate;//状态
	private String residualmoney;//剩余金额
	private String description;//说明
	
	public CExpenseapply() {
	}
	
	public CExpenseapply(Long id){
		this.id=id;
	}

	public CExpenseapply(Long id, String code, String expensename) {
		this.id = id;
		this.code = code;
		this.expensename = expensename;
	}

	public CExpenseapply(Long id, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons, String code, String expensename,
			String applyperson, String deparment, String expensetype, String applymoney, Timestamp applytime,
			String applystate, String residualmoney, String description) {
		this.id = id;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
		this.code = code;
		this.expensename = expensename;
		this.applyperson = applyperson;
		this.deparment = deparment;
		this.expensetype = expensetype;
		this.applymoney = applymoney;
		this.applytime = applytime;
		this.applystate = applystate;
		this.residualmoney = residualmoney;
		this.description = description;
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

	@Column(name = "EXPENSENAME", nullable = false, length = 20)
	public String getExpensename() {
		return this.expensename;
	}

	public void setExpensename(String expensename) {
		this.expensename = expensename;
	}

	@Column(name = "APPLYPERSON", length = 20)
	public String getApplyperson() {
		return this.applyperson;
	}

	public void setApplyperson(String applyperson) {
		this.applyperson = applyperson;
	}

	@Column(name = "DEPARMENT", length = 20)
	public String getDeparment() {
		return this.deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	@Column(name = "EXPENSETYPE", length = 20)
	public String getExpensetype() {
		return this.expensetype;
	}

	public void setExpensetype(String expensetype) {
		this.expensetype = expensetype;
	}

	@Column(name = "APPLYMONEY", length = 20)
	public String getApplymoney() {
		return this.applymoney;
	}

	public void setApplymoney(String applymoney) {
		this.applymoney = applymoney;
	}

	@Column(name = "APPLYTIME")
	public Timestamp getApplytime() {
		return this.applytime;
	}

	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
	}

	@Column(name = "APPLYSTATE", length = 20)
	public String getApplystate() {
		return this.applystate;
	}

	public void setApplystate(String applystate) {
		this.applystate = applystate;
	}

	@Column(name = "RESIDUALMONEY", length = 20)
	public String getResidualmoney() {
		return this.residualmoney;
	}

	public void setResidualmoney(String residualmoney) {
		this.residualmoney = residualmoney;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
