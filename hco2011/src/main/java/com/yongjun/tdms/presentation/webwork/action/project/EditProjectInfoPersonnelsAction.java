package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

public class EditProjectInfoPersonnelsAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final UserManager userManager;
	private ProjectInfo projectInfo;
	private ProjectInfoPersonnels projectInfoPersonnels;

	private PersonnelFiles personnelFiles;

	public EditProjectInfoPersonnelsAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.userManager = userManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}

		if (this.projectInfoPersonnels == null)
			if (hasId("projectInfoPersonnels.id")) {
				this.projectInfoPersonnels = this.projectInfoPersonnelsManager
						.loadProjectInfoPersonnels(getId("projectInfoPersonnels.id"));
			} else {
				this.projectInfoPersonnels = new ProjectInfoPersonnels();
			}
	}

	public String save() {
		boolean isNew = this.projectInfoPersonnels.isNew();

		if (hasId("projectInfo.id")) {
			this.projectInfoPersonnels.setProjectInfo(projectInfo);
		}

		if (hasId("businessType.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
			this.projectInfoPersonnels.setBusinessType(cv);
		}

		if (hasId("proPerson.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("proPerson.id"));
			this.projectInfoPersonnels.setProPerson(personnelFiles);
		}
		User u = this.userManager.getUser();
		if (isNew) {
			this.projectInfoPersonnels.setCreator(u.getName());
			this.projectInfoPersonnels.setLastOperator(u.getName());
		} else {
			this.projectInfoPersonnels.setLastOperator(u.getName());
		}
		this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(projectInfoPersonnels);

		if (isNew) {
			addActionMessage(getText("projectInfoPer.add.success",
					Arrays.asList(new Object[] { this.projectInfoPersonnels.getProPerson().getName() })));

			return "success";
		}
		addActionMessage(getText("projectInfoPer.edit.success",
				Arrays.asList(new Object[] { this.projectInfoPersonnels.getProPerson().getName() })));
		return "success";
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public List<CodeValue> getAllBusinessType() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "202");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}

			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public ProjectInfoPersonnels getProjectInfoPersonnels() {
		return projectInfoPersonnels;
	}

	public void setProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnels) {
		this.projectInfoPersonnels = projectInfoPersonnels;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
