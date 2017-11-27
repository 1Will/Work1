package com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import java.util.Date;

public class SalarySet extends BaseInfoEntity {
	private static final long serialVersionUID = -366175309059628074L;
	private PersonnelFiles emplyee;
	private Long baseSalary;//基本工资
	private Long postSalary;//岗位工资
	private Long senioritySalary;//工龄工资
	private Long trafficSubsidy;//通讯+交通补贴
	private Long dinnerSubsidy;//伙食补贴
	private Long holidaySubsidy;//节日补贴
	private Long travelSubsidy;//旅游补贴
	private String remark;//说明
	private Date createDate;//执行时间
	
	private CodeValue status;
	private String standard;
	private String reason;

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public PersonnelFiles getEmplyee() {
		return this.emplyee;
	}

	public void setEmplyee(PersonnelFiles emplyee) {
		this.emplyee = emplyee;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public CodeValue getStatus() {
		return this.status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public Long getBaseSalary() {
		return baseSalary;
	}

	public Long getPostSalary() {
		return postSalary;
	}

	public Long getSenioritySalary() {
		return senioritySalary;
	}

	public Long getTrafficSubsidy() {
		return trafficSubsidy;
	}

	public Long getDinnerSubsidy() {
		return dinnerSubsidy;
	}

	public Long getHolidaySubsidy() {
		return holidaySubsidy;
	}

	public Long getTravelSubsidy() {
		return travelSubsidy;
	}

	public void setBaseSalary(Long baseSalary) {
		this.baseSalary = baseSalary;
	}

	public void setPostSalary(Long postSalary) {
		this.postSalary = postSalary;
	}

	public void setSenioritySalary(Long senioritySalary) {
		this.senioritySalary = senioritySalary;
	}

	public void setTrafficSubsidy(Long trafficSubsidy) {
		this.trafficSubsidy = trafficSubsidy;
	}

	public void setDinnerSubsidy(Long dinnerSubsidy) {
		this.dinnerSubsidy = dinnerSubsidy;
	}

	public void setHolidaySubsidy(Long holidaySubsidy) {
		this.holidaySubsidy = holidaySubsidy;
	}

	public void setTravelSubsidy(Long travelSubsidy) {
		this.travelSubsidy = travelSubsidy;
	}
}
