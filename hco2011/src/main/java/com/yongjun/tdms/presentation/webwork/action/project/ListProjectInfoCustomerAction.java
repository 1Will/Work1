package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;

public class ListProjectInfoCustomerAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CodeValueManager codeValueManager;
	private ProjectInfoManager projectInfoManager;
	private List<ProjectInfoCustomer> projectInfoCustomer;
	private PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private String contactArchivesFlag;
	private final ProjectInfoCustomerManager projectInfoCustomerManager;
	private String projectInfoId;
	private String customerInfoId;
	private String contactArchivesId;

	public ListProjectInfoCustomerAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager,
			ProjectInfoCustomerManager projectInfoCustomerManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.projectInfoCustomerManager = projectInfoCustomerManager;

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
		return "projectInfoCustomer";
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
		if (hasId("customerInfo.id")) {
			this.customerInfoId = this.request.getParameter("customerInfo.id");
		}
		if (hasId("contactArchives.id")) {
			this.contactArchivesId = this.request.getParameter("contactArchives.id");
		}
		if ((this.projectInfoCustomer == null) && (hasIds("projectInfoCustomerIds"))) {
			this.projectInfoCustomer = this.projectInfoCustomerManager
					.loadAllProjectInfoCustomer(getIds("projectInfoCustomerIds"));
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
			this.projectInfoCustomerManager.deleteAllProjectInfoCustomer(this.projectInfoCustomer);
			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.projectInfoCustomerManager.enableProjectInfoCustomers(this.projectInfoCustomer);
			addActionMessage(getText("projectInfos.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.enable.failer"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.projectInfoCustomerManager.disabledProjectInfoCustomers(this.projectInfoCustomer);
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
			List one = this.codeValueManager.loadByKey("code", "201");
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

	public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
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

	public List<ProjectInfoCustomer> getProjectInfoCustomer() {
		return projectInfoCustomer;
	}

	public void setProjectInfoCustomer(List<ProjectInfoCustomer> projectInfoCustomer) {
		this.projectInfoCustomer = projectInfoCustomer;
	}

	public String getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(String customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

	public void setCodeValueManager(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}

}
