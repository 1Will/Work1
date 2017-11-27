package com.yongjun.tdms.model.personnelFiles.qualification;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
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

}
