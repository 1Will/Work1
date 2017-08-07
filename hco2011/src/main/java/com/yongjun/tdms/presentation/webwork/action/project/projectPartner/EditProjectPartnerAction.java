package com.yongjun.tdms.presentation.webwork.action.project.projectPartner;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectPartner.ProjectPartnerManager;

public class EditProjectPartnerAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ProjectPartnerManager projectPartnerManager;
	private final ProjectInfoManager projectInfoManager;
	private final CustomerInfoManager customerInfoManager;
	private ProjectInfo projectInfo;
	private ProjectPartner projectPartner;

	public EditProjectPartnerAction(ProjectPartnerManager projectPartnerManager, ProjectInfoManager projectInfoManager,
			CustomerInfoManager customerInfoManager) {
		this.projectPartnerManager = projectPartnerManager;
		this.projectInfoManager = projectInfoManager;
		this.customerInfoManager = customerInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectPartner.id")) {
			this.projectPartner = projectPartnerManager.loadProjectPartner(getId("projectPartner.id"));
		} else {
			this.projectPartner = new ProjectPartner();
		}

		if (hasId("projectInfo.id")) {
			this.projectPartner.setProjectInfo(projectInfoManager.loadProjectInfo(getId("projectInfo.id")));
		}
	}

	public String save() throws NumberFormatException, DaoException {
		boolean isNew = this.projectPartner.isNew();
		if (hasId("projectPartner.customerInfo.id")) {
			this.projectPartner.setCustomerInfo(this.customerInfoManager
					.loadCustomerInfo(getId("projectPartner.customerInfo.id")));
		}
		try {
			this.projectPartnerManager.storeProjectPartner(this.projectPartner);
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("projectPartner.add.error"));
			}
			addActionMessage(getText("projectPartner.update.error"));
			return ERROR;
		}
		if (isNew) {
			addActionMessage(getText("projectPartner.add.success"));
			return NEW;
		}
		addActionMessage(getText("projectPartner.update.success"));
		return SUCCESS;
	}

	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ProjectPartner getProjectPartner() {
		return projectPartner;
	}

	public void setProjectPartner(ProjectPartner projectPartner) {
		this.projectPartner = projectPartner;
	}
}
