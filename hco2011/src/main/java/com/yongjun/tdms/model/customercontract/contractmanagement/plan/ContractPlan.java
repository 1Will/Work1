package com.yongjun.tdms.model.customercontract.contractmanagement.plan;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class ContractPlan extends BaseInfoEntity {

	private static final long serialVersionUID = 1L;
	private Date startTime;
	private Date endTime;
	private String content;
	private PersonnelFiles executor;
	private ContractManagement contractManagement;

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PersonnelFiles getExecutor() {
		return executor;
	}

	public void setExecutor(PersonnelFiles executor) {
		this.executor = executor;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

}
