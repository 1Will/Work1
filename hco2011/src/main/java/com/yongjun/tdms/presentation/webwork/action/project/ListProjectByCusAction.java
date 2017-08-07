package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

public class ListProjectByCusAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CodeValueManager codeValueManager;
	private ProjectInfoManager projectInfoManager;
	private List<ProjectInfo> projectInfos;
	private PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private String contactArchivesFlag;
	private String backVisitCheckBox;
	private Long customerId;

	public ListProjectByCusAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;

	}

	public CodeValueManager getCodeValueManager() {
		return this.codeValueManager;
	}

	public PersonnelFilesManager getPersonnelFilesManager() {
		return this.personnelFilesManager;
	}

	public void setPersonnelFilesManager(PersonnelFilesManager personnelFilesManager) {
		this.personnelFilesManager = personnelFilesManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	protected String getAdapterName() {
		return "projectByCus";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public void prepare() throws Exception {
		if (hasId("customer.id")) {
			customerId =getId("customer.id");
		}
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			return disabled();
		}

		if (isEnable()) {
			return enable();
		}

		if (isDelete()) {
			return delete();
		}
		return "success";
	}


	private String delete() {
		try {
			this.projectInfoManager.deleteAllProjectInfo(this.projectInfos);
			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.projectInfoManager.enableProjectInfos(this.projectInfos);
			addActionMessage(getText("projectInfos.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.enable.failer"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.projectInfoManager.disabledProjectInfos(this.projectInfos);
			addActionMessage(getText("project.disabled.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("project.disabled.fail"));
		}
		return "error";
	}


	public String getBackVisitCheckBox() {
		return backVisitCheckBox;
	}

	public void setBackVisitCheckBox(String backVisitCheckBox) {
		this.backVisitCheckBox = backVisitCheckBox;
	}

	public ProjectInfoManager getProjectInfoManager() {
		return projectInfoManager;
	}

	public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public List<ProjectInfo> getProjectInfos() {
		return projectInfos;
	}

	public void setProjectInfos(List<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

	public String getContactArchivesFlag() {
		return contactArchivesFlag;
	}

	public void setContactArchivesFlag(String contactArchivesFlag) {
		this.contactArchivesFlag = contactArchivesFlag;
	}

	public void setCodeValueManager(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
