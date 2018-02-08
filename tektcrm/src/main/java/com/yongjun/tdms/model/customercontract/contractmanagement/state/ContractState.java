package com.yongjun.tdms.model.customercontract.contractmanagement.state;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class  ContractState extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String explain;//说明
	private ContractManagement contractManagement;
	private String fileName;
	private String position;
	private String creatorName;
	private Date uploadDate;
	private CodeValue beforeState;
	private CodeValue newState;
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public CodeValue getBeforeState() {
		return beforeState;
	}

	public void setBeforeState(CodeValue beforeState) {
		this.beforeState = beforeState;
	}

	public CodeValue getNewState() {
		return newState;
	}

	public void setNewState(CodeValue newState) {
		this.newState = newState;
	}
	
}
