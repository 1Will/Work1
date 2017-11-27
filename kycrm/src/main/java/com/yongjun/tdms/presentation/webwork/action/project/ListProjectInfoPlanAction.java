package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class ListProjectInfoPlanAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CodeValueManager codeValueManager;
	private ProjectInfoManager projectInfoManager;
	private List<ProjectInfoPlan> ProjectInfoPlan;
	private PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private String projectInfoId;
	private String contractManagementId;
    private Long []ids;
    private List orderNum;
    private String idsString;
    private String backCheckBox;
    private String projectInfoPlan_2Id;
	public ListProjectInfoPlanAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
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
		return "projectInfoPlan";
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
		if (hasId("contractManagement.id")) {
			map.put("contractManagementId", getId("contractManagement.id"));
		}
		if (hasId("projectInfoPlan.id")) {
			String projectInfoPlanId=request.getParameter("projectInfoPlan.id");
			String[] projectInfoPlanIds_1 = projectInfoPlanId.split(",");
			Long []projectInfoPlanIds_2 =new Long[projectInfoPlanIds_1.length];
			for(int i=0;i<projectInfoPlanIds_1.length;i++){
				String id=projectInfoPlanIds_1[i];
				projectInfoPlanIds_2[i]=Long.parseLong(id);
			}
		    List projectInfoPlanIds = Arrays.asList(projectInfoPlanIds_2);
			map.put("projectInfoPlanId",projectInfoPlanIds);
		}
        if (request.getParameter("sortColumn")==null) {
			map.put("sortColumn", "orderNumber");
		}
        if(ids!=null&& ids.length>0){
        	map.put("orderNum",orderNum);
        }
		return map;
	}

	public void prepare() throws Exception {
		if (this.request.getParameter("backCheckBox") != null) {
			this.backCheckBox = this.request.getParameter("backCheckBox");
		}
		if (hasId("contractManagement.id")) {
			this.contractManagementId = this.request.getParameter("contractManagement.id");
		}
		if (hasId("projectInfo.id")) {
			this.projectInfoId = this.request.getParameter("projectInfo.id");
		}
		if ((this.ProjectInfoPlan == null) && (hasIds("projectInfoPlanIds"))) {
			this.ProjectInfoPlan = this.projectInfoPlanManager.loadAllProjectInfoPlan(getIds("projectInfoPlanIds"));
		}
		if(hasId("ids")){
			idsString=request.getParameter("ids");
			String[] ids_1 = idsString.split(",");
			ids =new Long[ids_1.length];
			for(int i=0;i<ids_1.length;i++){
				String id=ids_1[i];
				ids[i]=Long.parseLong(id);
			}
			this.ProjectInfoPlan =this.projectInfoPlanManager.loadAllProjectInfoPlan(ids);
			orderNum =new ArrayList();
			for(int i=0;i<ProjectInfoPlan.size();i++){
				ProjectInfoPlan plan=ProjectInfoPlan.get(i);
				Integer integer=plan.getOrderNumber();
				Long iLong=(long)integer;
				orderNum.add(iLong);
			}
		}
		if(hasId("projectInfoPlan_2Id")){
			this.projectInfoPlan_2Id=request.getParameter("projectInfoPlan_2Id");
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

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public List getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(List orderNum) {
		this.orderNum = orderNum;
	}

	public String getIdsString() {
		return idsString;
	}

	public void setIdsString(String idsString) {
		this.idsString = idsString;
	}

	public String getBackCheckBox() {
		return backCheckBox;
	}

	public void setBackCheckBox(String backCheckBox) {
		this.backCheckBox = backCheckBox;
	}

	public String getProjectInfoPlan_2Id() {
		return projectInfoPlan_2Id;
	}

	public void setProjectInfoPlan_2Id(String projectInfoPlan_2Id) {
		this.projectInfoPlan_2Id = projectInfoPlan_2Id;
	}
	

}
