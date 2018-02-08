package com.yongjun.tdms.model.workReport.boardroom;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

/**
 * 预定会议室
 */
public class BookBoardroom extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	private PersonnelFiles applicant;
	private Date startDate;
	private Date endDate;
	private CodeValue state;
	private String explain;
	private Boardroom boardroom;
	private String isSaved;//存在并且等于0，，方可提交
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object paramObject) {
		return false;
	}

	public PersonnelFiles getApplicant() {
		return applicant;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public CodeValue getState() {
		return state;
	}

	public String getExplain() {
		return explain;
	}

	public void setApplicant(PersonnelFiles applicant) {
		this.applicant = applicant;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Boardroom getBoardroom() {
		return boardroom;
	}

	public void setBoardroom(Boardroom boardroom) {
		this.boardroom = boardroom;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}


}
