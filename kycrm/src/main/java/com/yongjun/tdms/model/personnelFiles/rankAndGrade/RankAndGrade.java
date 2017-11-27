package com.yongjun.tdms.model.personnelFiles.rankAndGrade;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class RankAndGrade extends BaseInfoEntity{

	private static final long serialVersionUID = -5569744038807660273L;

	private PersonnelFiles personnelFiles;
	private CodeValue beforeRank;
	private CodeValue newRank;
	private CodeValue beforeGrade;
	private CodeValue newGrade;
	private String explain;//说明
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public CodeValue getBeforeRank() {
		return beforeRank;
	}

	public CodeValue getNewRank() {
		return newRank;
	}

	public CodeValue getBeforeGrade() {
		return beforeGrade;
	}

	public CodeValue getNewGrade() {
		return newGrade;
	}

	public String getExplain() {
		return explain;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public void setBeforeRank(CodeValue beforeRank) {
		this.beforeRank = beforeRank;
	}

	public void setNewRank(CodeValue newRank) {
		this.newRank = newRank;
	}

	public void setBeforeGrade(CodeValue beforeGrade) {
		this.beforeGrade = beforeGrade;
	}

	public void setNewGrade(CodeValue newGrade) {
		this.newGrade = newGrade;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
