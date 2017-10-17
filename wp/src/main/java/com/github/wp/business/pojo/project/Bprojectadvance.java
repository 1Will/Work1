package com.github.wp.business.pojo.project;

// Generated 2016-8-3 10:00:53 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * 项目进度计划
 */
@Entity
@Table(name = "PROJECT_PROJECTADVANCE", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class Bprojectadvance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer projectIdq;
	private String advanceContent;
	private Timestamp completeDate;
	private String status;
	private String captain;
	private Character effectflag;
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;

	public Bprojectadvance() {
	}
	
	public Bprojectadvance(Long id) {
		this.id = id;
	}


	public Bprojectadvance(Long id, Integer projectIdq, String advanceContent, Timestamp completeDate,
			String status, String captain, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons) {
		this.id = id;
		this.projectIdq = projectIdq;
		this.advanceContent = advanceContent;
		this.completeDate = completeDate;
		this.status = status;
		this.captain = captain;
		this.effectflag = effectflag;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
		this.versons = versons;
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

	@Column(name = "PROJECT_IDQ", precision = 22, scale = 0)
	public Integer getProjectIdq() {
		return this.projectIdq;
	}

	public void setProjectIdq(Integer projectIdq) {
		this.projectIdq = projectIdq;
	}

	@Column(name = "ADVANCE_CONTENT", length = 250)
	public String getAdvanceContent() {
		return this.advanceContent;
	}

	public void setAdvanceContent(String advanceContent) {
		this.advanceContent = advanceContent;
	}

	@Column(name = "COMPLETE_DATE")
	public Timestamp getCompleteDate() {
		return this.completeDate;
	}

	public void setCompleteDate(Timestamp completeDate) {
		this.completeDate = completeDate;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CAPTAIN", length = 20)
	public String getCaptain() {
		return this.captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
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

}
