package com.yongjun.tdms.model.project.projectPartner;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectPartner extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectInfo projectInfo;
	private CustomerInfo customerInfo;
	private String remarks;

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
