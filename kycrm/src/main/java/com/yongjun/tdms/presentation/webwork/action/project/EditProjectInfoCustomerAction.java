package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;

public class EditProjectInfoCustomerAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ProjectInfoManager projectInfoManager;
	private final CustomerInfoManager customerInfoManager;
	private final ProjectInfoCustomerManager projectInfoCustomerManager;
	private final UserManager userManager;
	private String projectInfoId;
	private ProjectInfoCustomer projectInfoCustomer;
	public EditProjectInfoCustomerAction(ProjectInfoManager projectInfoManager,
			 CustomerInfoManager customerInfoManager,
			 UserManager userManager,
			ProjectInfoCustomerManager projectInfoCustomerManager) {
		this.projectInfoManager = projectInfoManager;
		this.customerInfoManager = customerInfoManager;
		this.userManager = userManager;
		this.projectInfoCustomerManager = projectInfoCustomerManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfoId = getId("projectInfo.id") + "";
		}

		if (this.projectInfoCustomer == null)
			if (hasId("projectInfoCustomer.id")) {
				this.projectInfoCustomer = this.projectInfoCustomerManager
						.loadProjectInfoCustomer(getId("projectInfoCustomer.id"));
			} else {
				this.projectInfoCustomer = new ProjectInfoCustomer();
			}
	}

	public String save() {
		boolean isNew = this.projectInfoCustomer.isNew();

		if (hasId("projectInfo.id")) {
			ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.projectInfoCustomer.setProjectInfo(projectInfo);
		}

		if (hasId("customerInfo.id")) {
			CustomerInfo customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			this.projectInfoCustomer.setCustomerInfo(customerInfo);
		}
		User u = this.userManager.getUser();
		if (isNew) {
			this.projectInfoCustomer.setCreator(u.getName());
			this.projectInfoCustomer.setLastOperator(u.getName());
		} else {
			this.projectInfoCustomer.setLastOperator(u.getName());
		}
		projectInfoCustomer.setIsKeyCustomer("0");
		this.projectInfoCustomerManager.storeProjectInfoCustomer(projectInfoCustomer);

		if (isNew) {
			addActionMessage(getText("projectInfoCustomer.add.success",
					Arrays.asList(new Object[] { this.projectInfoCustomer.getCustomerInfo().getName() })));

			return "success";
		}
		addActionMessage(getText("projectInfoCustomer.edit.success",
				Arrays.asList(new Object[] { this.projectInfoCustomer.getCustomerInfo().getName() })));
		return "success";
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	
	public String getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(String projectInfoId) {
		this.projectInfoId = projectInfoId;
	}


	public ProjectInfoCustomer getProjectInfoCustomer() {
		return projectInfoCustomer;
	}

	public void setProjectInfoCustomer(ProjectInfoCustomer projectInfoCustomer) {
		this.projectInfoCustomer = projectInfoCustomer;
	}


	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
