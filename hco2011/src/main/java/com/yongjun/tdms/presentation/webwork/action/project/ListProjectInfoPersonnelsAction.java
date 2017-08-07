package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListProjectInfoPersonnelsAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final UserManager userManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;

	private List<ProjectInfoPersonnels> projectInfoPersonnels;
	private String contactArchivesFlag;
	private String projectInfoId;

	public ListProjectInfoPersonnelsAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;

	}

	public CodeValueManager getCodeValueManager() {
		return this.codeValueManager;
	}

	public PersonnelFilesManager getPersonnelFilesManager() {
		return this.personnelFilesManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	protected String getAdapterName() {
		return "projectInfoPersonnels";
	}

	protected Map getRequestParameterMap() {
		// Map map= new HashMap();//super.getRequestParameterMap();
		// if (hasId("projectInfo.id")) {
		// map.put("projectInfo.id",
		// this.request.getParameter("projectInfo.id"));
		// }
		// map.put("onlyValid", true);
		Map map = super.getRequestParameterMap();
		if (hasId("projectInfo.id")) {
			map.put("projectInfoId", getId("projectInfo.id"));
		}

		return map;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfoId = this.request.getParameter("projectInfo.id");
		}
		if ((this.projectInfoPersonnels == null) && (hasIds("projectInfoPersonnelsIds"))) {
			this.projectInfoPersonnels = this.projectInfoPersonnelsManager
					.loadAllProjectInfoPersonnels(getIds("projectInfoPersonnelsIds"));
		}
		if (this.request.getParameter("contactArchivesFlag") != null) {
			this.contactArchivesFlag = this.request.getParameter("contactArchivesFlag");

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

	public boolean getIsPersonnelFiles() throws Exception {
		if (null == this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode())) {
			return false;
		}
		return true;
	}

	private String delete() {
		try {
			this.projectInfoPersonnelsManager.deleteAllProjectInfoPersonnels(this.projectInfoPersonnels);
			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.projectInfoPersonnelsManager.enableProjectInfoPersonnelss(this.projectInfoPersonnels);
			addActionMessage(getText("projectInfos.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.enable.failer"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.projectInfoPersonnelsManager.disabledProjectInfoPersonnelss(this.projectInfoPersonnels);
			addActionMessage(getText("backVisits.disabled.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.disabled.failer"));
		}
		return "error";
	}

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("201"));
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

	public ProjectInfoManager getProjectInfoManager() {
		return projectInfoManager;
	}

	public String getContactArchivesFlag() {
		return contactArchivesFlag;
	}

	public void setContactArchivesFlag(String contactArchivesFlag) {
		this.contactArchivesFlag = contactArchivesFlag;
	}

	public String getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(String projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public List<ProjectInfoPersonnels> getProjectInfoPersonnels() {
		return projectInfoPersonnels;
	}

	public void setProjectInfoPersonnels(List<ProjectInfoPersonnels> projectInfoPersonnels) {
		this.projectInfoPersonnels = projectInfoPersonnels;
	}

}
