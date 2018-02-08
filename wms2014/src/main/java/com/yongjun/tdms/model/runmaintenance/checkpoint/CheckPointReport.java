package com.yongjun.tdms.model.runmaintenance.checkpoint;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

public class CheckPointReport extends BaseInfoEntity{
	private static final long serialVersionUID = 5555459398872907429L;
	private String reportNo;				//报告单编号
	private String name;					//报告单名称
	private Department department;			//点检报告单部门
	private String month;						//报告月份
	private Date reportTime;				//报告日期
	private User speaker;					//报告人
	private String deptName;                //部门名称
	private Set<CheckPointReportDetail>	reportDetails = new HashSet<CheckPointReportDetail>();  
	private String comment;                 //备注
	private Boolean submit = false;			//是否‘提交’标识

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public User getSpeaker() {
		return speaker;
	}

	public void setSpeaker(User speaker) {
		this.speaker = speaker;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<CheckPointReportDetail> getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(Set<CheckPointReportDetail> reportDetails) {
		this.reportDetails = reportDetails;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
}
