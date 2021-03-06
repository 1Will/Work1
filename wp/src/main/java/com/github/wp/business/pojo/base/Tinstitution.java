package com.github.wp.business.pojo.base;

// Generated 2016-8-2 15:14:10 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

/**
 * TInstitution generated by hbm2java
 */
@Entity
@Table(name = "BASE_INSTITUTION", schema = "CRM2016")
@Where(clause="EFFECTFLAG='E'")
public class Tinstitution implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;//类别编码
	private String name;//类别名称
	private String lader;//单位负责人
	private String tel;//电话
	private String address;//地址
	private String comment1;//备注
	private String postcode;//邮编
	private String legalperson;//法人代表
	private String email;//邮箱
	private String website;//网址
	private String fax;//传真
	private Character effectflag='E';//有效标识
	private Timestamp setuptime;//创办时间
	private Integer versons;
	private Integer step;//
	private Integer industryId;//所属行业
	private Integer natureId;//性质
	private Integer industry;//所属行业(可能多余)
	private Integer nature;//性质(可能多余)
	private Timestamp createdTime;
	private String creator;
	private Timestamp lastModifiedTime;
	private String lastOperator;
	
	@JsonIgnore
	private List<Tinstitution> sysTinstitutions = new ArrayList<Tinstitution>();
	@JsonIgnore
	private Tinstitution sysTinstitution;
	

	public Tinstitution() {
		
	}
	public Tinstitution(Long id) {
		this.id=id;
	}

	public Tinstitution(long id, String code, String name, String lader, String tel, String address, String comment1) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.lader = lader;
		this.tel = tel;
		this.address = address;
		this.comment1 = comment1;
	}

	public Tinstitution(long id, String code, String name, String lader, String tel, String address, String comment1,
			 String postcode, String legalperson, String email, String website, String fax,
			Character effectflag, Timestamp setuptime, Integer versons, Integer step, Integer industryId,
			Integer natureId, Integer industry, Integer nature, Timestamp createdTime, String creator,
			Timestamp lastModifiedTime, String lastOperator) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.lader = lader;
		this.tel = tel;
		this.address = address;
		this.comment1 = comment1;
		this.postcode = postcode;
		this.legalperson = legalperson;
		this.email = email;
		this.website = website;
		this.fax = fax;
		this.effectflag = effectflag;
		this.setuptime = setuptime;
		this.versons = versons;
		this.step = step;
		this.industryId = industryId;
		this.natureId = natureId;
		this.industry = industry;
		this.nature = nature;
		this.createdTime = createdTime;
		this.creator = creator;
		this.lastModifiedTime = lastModifiedTime;
		this.lastOperator = lastOperator;
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

	@Column(name = "CODE", nullable = false, length = 40)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LADER", nullable = false, length = 20)
	public String getLader() {
		return this.lader;
	}

	public void setLader(String lader) {
		this.lader = lader;
	}

	@Column(name = "TEL", nullable = false, length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "ADDRESS", nullable = false, length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "COMMENT1", nullable = false, length = 250)
	public String getComment1() {
		return this.comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}


	@Column(name = "POSTCODE", length = 20)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "LEGALPERSON", length = 20)
	public String getLegalperson() {
		return this.legalperson;
	}

	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "WEBSITE", length = 50)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "FAX", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "EFFECTFLAG", length = 1)
	public Character getEffectflag() {
		return this.effectflag;
	}

	public void setEffectflag(Character effectflag) {
		this.effectflag = effectflag;
	}

	@Column(name = "SETUPTIME")
	public Timestamp getSetuptime() {
		return this.setuptime;
	}

	public void setSetuptime(Timestamp setuptime) {
		this.setuptime = setuptime;
	}

	@Column(name = "VERSONS", precision = 22, scale = 0)
	public Integer getVersons() {
		return this.versons;
	}

	public void setVersons(Integer versons) {
		this.versons = versons;
	}

	@Column(name = "STEP", precision = 22, scale = 0)
	public Integer getStep() {
		return this.step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	@Column(name = "INDUSTRY_ID", precision = 22, scale = 0)
	public Integer getIndustryId() {
		return this.industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	@Column(name = "NATURE_ID", precision = 22, scale = 0)
	public Integer getNatureId() {
		return this.natureId;
	}

	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
	}

	@Column(name = "INDUSTRY", precision = 22, scale = 0)
	public Integer getIndustry() {
		return this.industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	@Column(name = "NATURE", precision = 22, scale = 0)
	public Integer getNature() {
		return this.nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	@Column(name = "CREATED_TIME")
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "CREATOR", length = 50)
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

	@Column(name = "LAST_OPERATOR", length = 50)
	public String getLastOperator() {
		return this.lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysTinstitution")
	@Where(clause="EFFECTFLAG='E'")
	public List<Tinstitution> getSysTinstitutions() {
		return sysTinstitutions;
	}
	public void setSysTinstitutions(List<Tinstitution> sysTinstitutions) {
		this.sysTinstitutions = sysTinstitutions;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inst_id")
	public Tinstitution getSysTinstitution() {
		return sysTinstitution;
	}
	public void setSysTinstitution(Tinstitution sysTinstitution) {
		this.sysTinstitution = sysTinstitution;
	}
	
}
