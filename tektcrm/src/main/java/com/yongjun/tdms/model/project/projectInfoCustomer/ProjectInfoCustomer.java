package com.yongjun.tdms.model.project.projectInfoCustomer;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectInfoCustomer extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ProjectInfo projectInfo;// 项目
	private CustomerInfo customerInfo;// 项目客户
	private String outline;// 主要说明
	private String isKeyCustomer;//是否是绑定的客户
	
	
	public String getIsKeyCustomer() {
		return isKeyCustomer;
	}

	public void setIsKeyCustomer(String isKeyCustomer) {
		this.isKeyCustomer = isKeyCustomer;
	}

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfoCustomer)) {
			return false;
		}

		ProjectInfoCustomer contact = (ProjectInfoCustomer) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
}
