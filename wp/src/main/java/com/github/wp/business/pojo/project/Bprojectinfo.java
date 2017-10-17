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
 * 项目档案
 */
@Entity
@Table(name = "PROJECT_PROJECTINFO", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class Bprojectinfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private Timestamp startDate;
	private Timestamp endDate;
	private String objectives;
	private String projectScope;
	private Integer majorCustomerid;
	private Integer majorContactid;
	private String manager;
	private String status;
	private Character effectflag;
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	private Integer versons;

	public Bprojectinfo() {
	}

	public Bprojectinfo(Long id) {
		this.id = id;
	}

	public Bprojectinfo(Long id, String code, String name, Timestamp startDate, Timestamp endDate,
			String objectives, String projectScope, Integer majorCustomerid, Integer majorContactid,
			String manager, String status, Character effectflag, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator, Integer versons) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.objectives = objectives;
		this.projectScope = projectScope;
		this.majorCustomerid = majorCustomerid;
		this.majorContactid = majorContactid;
		this.manager = manager;
		this.status = status;
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

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "START_DATE")
	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE")
	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Column(name = "OBJECTIVES", length = 50)
	public String getObjectives() {
		return this.objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	@Column(name = "PROJECT_SCOPE", length = 250)
	public String getProjectScope() {
		return this.projectScope;
	}

	public void setProjectScope(String projectScope) {
		this.projectScope = projectScope;
	}

	@Column(name = "MAJOR_CUSTOMERID", precision = 22, scale = 0)
	public Integer getMajorCustomerid() {
		return this.majorCustomerid;
	}

	public void setMajorCustomerid(Integer majorCustomerid) {
		this.majorCustomerid = majorCustomerid;
	}

	@Column(name = "MAJOR_CONTACTID", precision = 22, scale = 0)
	public Integer getMajorContactid() {
		return this.majorContactid;
	}

	public void setMajorContactid(Integer majorContactid) {
		this.majorContactid = majorContactid;
	}

	@Column(name = "MANAGER", length = 20)
	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
