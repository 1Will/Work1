package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditProjectInfoPlanAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private final ContractManagementManager contractManagementManager;
	private final UserManager userManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private String projectInfoId;
	private ProjectInfoPlan projectInfoPlan;
	private String contractManagementId;
	private String editFlag;

	private PersonnelFiles personnelFiles;

	public EditProjectInfoPlanAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager,
			ProjectInfoPlanManager projectInfoPlanManager,ContractManagementManager contractManagementManager,
			EventNewManager eventNewManager,EventTypeManager eventTypeManager,PersonnelFilesToUserManager personnelFilesToUserManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
		this.contractManagementManager=contractManagementManager;
		this.eventNewManager=eventNewManager;
		this.eventTypeManager=eventTypeManager;
		this.personnelFilesToUserManager=personnelFilesToUserManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfoId = getId("projectInfo.id") + "";
		}
		if (hasId("contractManagement.id")) {
			this.contractManagementId = getId("contractManagement.id") + "";
		}

		if (this.projectInfoPlan == null)
			if (hasId("projectInfoPlan.id")) {
				this.projectInfoPlan = this.projectInfoPlanManager.loadProjectInfoPlan(getId("projectInfoPlan.id"));
			} else {
				this.projectInfoPlan = new ProjectInfoPlan();
			}
		if(this.request.getParameter("editFlag")!=null){
			this.editFlag =this.request.getParameter("editFlag");
		}
	}

	public String save() {
		boolean isNew = this.projectInfoPlan.isNew();

		if (hasId("projectInfo.id")) {
			ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.projectInfoPlan.setProjectInfo(projectInfo);
		}
		if (hasId("contractManagement.id")) {
			ContractManagement contractManagement =this.contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.projectInfoPlan.setContractManagement(contractManagement);
		}
		if (hasId("planState.id")) {
			CodeValue  planState = this.codeValueManager.loadCodeValue(getId("planState.id"));
			this.projectInfoPlan.setPlanState(planState);
		}
		if (hasId("projectInfoPlan.percentt")) {
			this.projectInfoPlan.setPercentt(Integer.parseInt(request.getParameter("projectInfoPlan.percentt")));
		}
		if (hasId("projectInfoPlan.priority")) {
			this.projectInfoPlan.setPriority(Integer.parseInt(request.getParameter("projectInfoPlan.priority")));
		}

		if (hasId("personnelFiles.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
			this.projectInfoPlan.setPersonnelFiles(personnelFiles);
		}
		User u = this.userManager.getUser();
		if (isNew) {
			this.projectInfoPlan.setCreator(u.getName());
			this.projectInfoPlan.setLastOperator(u.getName());
		} else {
			this.projectInfoPlan.setLastOperator(u.getName());
		}
		if (request.getParameter("projectInfoPlan.isSaved") != null) {
			this.projectInfoPlan.setIsSaved(request.getParameter("projectInfoPlan.isSaved"));
		}
		
		String submit = null;
		
		this.projectInfoPlanManager.storeProjectInfoPlan(projectInfoPlan);
		
		if ("1".equals(this.request.getParameter("projectInfoPlan.isSaved"))) {
			try {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10012");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventType不存在！");
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				//项目工作计划提交
				if(this.projectInfoPlan.getProjectInfo()!=null){
					event.setName("项目工作计划");
					String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfoPlan.getProjectInfo().getId(),
							this.projectInfoPlan.getProjectInfo().getCreateUser());
					map.put("users", pids);
					map.put("projectInfoPlanId", this.projectInfoPlan.getId() + "");
				}
				//合同工作计划提交
				if(this.projectInfoPlan.getContractManagement()!=null){
					event.setName("合同工作计划");
					String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfoPlan.getContractManagement().getProject().getId(),
							this.projectInfoPlan.getContractManagement().getProject().getCreateUser());
					map.put("users", pids);
					map.put("projectInfoPlanId", this.projectInfoPlan.getId() + "");
				}
				
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				submit = "submit";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (isNew) {
			addActionMessage(getText("projectInfoPlan.add.success",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));

			return "success";
		}
		if("submit".equals(submit)){
			addActionMessage(getText("projectInfoPlan.submit.success",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
			return "success";
		}else {
			addActionMessage(getText("projectInfoPlan.edit.success",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
			return "success";
		}
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

	public ProjectInfoPlan getProjectInfoPlan() {
		return projectInfoPlan;
	}

	public void setProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
		this.projectInfoPlan = projectInfoPlan;
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

	public String getContractManagementId() {
		return contractManagementId;
	}

	public void setContractManagementId(String contractManagementId) {
		this.contractManagementId = contractManagementId;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	
	

	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
