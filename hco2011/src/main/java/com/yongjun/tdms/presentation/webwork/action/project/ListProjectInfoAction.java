package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

public class ListProjectInfoAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CodeValueManager codeValueManager;
	private ProjectInfoManager projectInfoManager;
	private List<ProjectInfo> projectInfos;
	private PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private String contactArchivesFlag;
	private String backVisitCheckBox;
	private Long customerId;

	public ListProjectInfoAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
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
		return "getProjectInfo";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (hasId("customer.id")) {
			map.put("customerId", getId("customer.id"));
		}
		if (this.request.getParameter("projectInfo.createdTime") != null) {
			map.put("projectInfo.createdTime", this.request.getParameter("projectInfo.createdTime") + "%");
		}
		return map;
	}

	public void prepare() throws Exception {
		if ((this.projectInfos == null) && (hasIds("projectInfoIds"))) {
			this.projectInfos = this.projectInfoManager.loadAllProjectInfo(getIds("projectInfoIds"));
		}
		if (this.request.getParameter("contactArchivesFlag") != null) {
			this.contactArchivesFlag = this.request.getParameter("contactArchivesFlag");

		}
		if (this.request.getParameter("backVisitCheckBox") != null) {
			this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
		}
		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
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

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("201"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					CodeValue cv = new CodeValue();
					cv.setId(null);
					cv.setName(getText("所有"));
					codes.add(0, cv);
					codes.addAll(list);
				}

			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
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
