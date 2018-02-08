package com.yongjun.tdms.model.personnelFiles.qualification;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class Qualification extends BaseInfoEntity{

	/**
	 * 资格证书
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date starTime;
	private Date endTime;
	private String explain;
	private PersonnelFiles personnelFiles;
	private Institution institution;
	private Department dept;
	private CodeValue type;
	

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getName() {
		return name;
	}

	public Date getStarTime() {
		return starTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getExplain() {
		return explain;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public CodeValue getType() {
		return type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}

}
