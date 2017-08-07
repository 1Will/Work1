package com.yongjun.tdms.model.workReport.weekly;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.week.Week;

public class Weekly extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private User rapporteur;
	private PersonnelFiles person;
	private Institution inst;
	private Department dept;
	private Duty duty;
	private Date startDate;
	private Date endDate;
	private String summary;
	private String leaderIdea;
	private String comment;
	private Week week;
	private String isSaved;// 提交判断
	private long submitNum=0;//提交次数

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof Weekly)) {
			return false;
		}
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Department getDept() {
		return this.dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Duty getDuty() {
		return this.duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Institution getInst() {
		return this.inst;
	}

	public void setInst(Institution inst) {
		this.inst = inst;
	}

	public String getLeaderIdea() {
		return this.leaderIdea;
	}

	public void setLeaderIdea(String leaderIdea) {
		this.leaderIdea = leaderIdea;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonnelFiles getPerson() {
		return this.person;
	}

	public void setPerson(PersonnelFiles person) {
		this.person = person;
	}

	public User getRapporteur() {
		return this.rapporteur;
	}

	public void setRapporteur(User rapporteur) {
		this.rapporteur = rapporteur;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public long getSubmitNum() {
		return submitNum;
	}

	public void setSubmitNum(long submitNum) {
		this.submitNum = submitNum;
	}
	
}
