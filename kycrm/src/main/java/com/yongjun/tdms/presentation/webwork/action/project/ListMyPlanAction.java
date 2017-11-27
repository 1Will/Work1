package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class ListMyPlanAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CodeValueManager codeValueManager;
	private ProjectInfoManager projectInfoManager;
	private List<ProjectInfoPlan> ProjectInfoPlan;
	private PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private String projectInfoId;
	private String contractManagementId;

	public ListMyPlanAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager,
			ProjectInfoPlanManager ProjectInfoPlanManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.projectInfoPlanManager = ProjectInfoPlanManager;

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
		return "myPlanHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User user  = this.userManager.getUser();
		 String codeString = user.getCode();
		 if(codeString==null|codeString.equals("")){
			 codeString="yjkj-52";
		 }
		
		map.put("personnelFiles.code", codeString);
		if(request.getParameter("sortColumn")==null||"".equals(request.getParameter("sortColumn"))){
			map.put("sortColumn", "endDate");
			map.put("sortDirection", "0");
		}
		return map;
	}

	public void prepare() throws Exception {
		if ((this.ProjectInfoPlan == null) && (hasIds("projectInfoPlanIds"))) {
			this.ProjectInfoPlan = this.projectInfoPlanManager.loadAllProjectInfoPlan(getIds("projectInfoPlanIds"));
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
			this.projectInfoPlanManager.deleteAllProjectInfoPlan(this.ProjectInfoPlan);
			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.projectInfoPlanManager.enableProjectInfoPlans(this.ProjectInfoPlan);
			addActionMessage(getText("projectInfos.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.enable.failer"));
		}
		return "error";
	}
	public List<CodeValue> getAllPlanState() {
		try {
			List codes = new ArrayList();
			String[] keys ={"name","code"};
			String[] values ={"计划状态","211"};
			List one =  this.codeValueManager.loadByKeyArray(keys, values);//this.codeValueManager.loadByKey("code", Long.valueOf("211"));
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

	private String disabled() {
		try {
			this.projectInfoPlanManager.disabledProjectInfoPlans(this.ProjectInfoPlan);
			addActionMessage(getText("backVisits.disabled.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.disabled.failer"));
		}
		return "error";
	}

	public ProjectInfoManager getProjectInfoManager() {
		return projectInfoManager;
	}

	public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public String getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(String projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public List<ProjectInfoPlan> getProjectInfoPlan() {
		return ProjectInfoPlan;
	}

	public void setProjectInfoPlan(List<ProjectInfoPlan> ProjectInfoPlan) {
		this.ProjectInfoPlan = ProjectInfoPlan;
	}

	public void setCodeValueManager(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}

	public String getContractManagementId() {
		return contractManagementId;
	}

	public void setContractManagementId(String contractManagementId) {
		this.contractManagementId = contractManagementId;
	}
	

}
