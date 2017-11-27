package com.yongjun.tdms.presentation.webwork.action.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
	private String contractManagementId;
	private ProjectInfoPlan projectInfoPlan;
	private ContractManagement contractManagement;
	private String editFlag;
	private String title;
	private String ids;
	private PersonnelFiles personnelFiles;

	public EditProjectInfoPlanAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager,
			ProjectInfoPlanManager projectInfoPlanManager, ContractManagementManager contractManagementManager,
			EventNewManager eventNewManager, EventTypeManager eventTypeManager,
			PersonnelFilesToUserManager personnelFilesToUserManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
		this.contractManagementManager = contractManagementManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfoId = getId("projectInfo.id") + "";
			this.title = "项目工作计划信息维护";
		}
		if (hasId("contractManagement.id")) {
			this.contractManagementId = getId("contractManagement.id") + "";
			this.contractManagement =contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.title = "合同工作计划信息维护";
		}

		if (this.projectInfoPlan == null)
			if (hasId("projectInfoPlan.id")) {
				this.projectInfoPlan = this.projectInfoPlanManager.loadProjectInfoPlan(getId("projectInfoPlan.id"));
			} else {
				this.projectInfoPlan = new ProjectInfoPlan();
			}
		if (this.request.getParameter("editFlag") != null) {
			this.editFlag = this.request.getParameter("editFlag");
		}
	}

	public String save() {
		boolean isNew = this.projectInfoPlan.isNew();
		HashMap map_1=new HashMap();
		Integer orderNumber;
		if (hasId("projectInfo.id")) {
			ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.projectInfoPlan.setProjectInfo(projectInfo);
			if(isNew){
				map_1.put("projectInfo", getId("projectInfo.id"));
				orderNumber= projectInfoPlanManager.loadMaxOrderNumber(map_1);
				projectInfoPlan.setOrderNumber(orderNumber);
			}
		}
		if (hasId("contractManagement.id")) {
			this.projectInfoPlan.setContractManagement(contractManagement);
			if(isNew){
				map_1.put("contractManagement", getId("contractManagement.id"));
				orderNumber=projectInfoPlanManager.loadMaxOrderNumber(map_1);
				projectInfoPlan.setOrderNumber(orderNumber);
			}
		}
		if (hasId("planState.id")) {
			CodeValue planState = this.codeValueManager.loadCodeValue(getId("planState.id"));
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

		if (hasId("assistIds")) {
			this.projectInfoPlan.setAssistIds(this.request.getParameter("assistIds"));
		}

		if (hasId("assist")) {
			this.projectInfoPlan.setAssist(this.request.getParameter("assist"));
		}

		if (request.getParameter("projectInfoPlan.isSaved") != null) {
			this.projectInfoPlan.setIsSaved(request.getParameter("projectInfoPlan.isSaved"));
		}
        if (request.getParameter("projectInfoPlan_2.id")!=null&&request.getParameter("projectInfoPlan_2.id").length()>0) {
			this.projectInfoPlan.setProjectInfoPlan_2(this.projectInfoPlanManager.loadProjectInfoPlan(Long.parseLong(request.getParameter("projectInfoPlan_2.id"))));
		}else{
			this.projectInfoPlan.setProjectInfoPlan_2(null);
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
				// 项目工作计划提交
				if (this.projectInfoPlan.getProjectInfo() != null) {
					event.setName(eventType.getName());

					List<User> users = this.userManager.loadByKey("code", this.projectInfoPlan.getPersonnelFiles()
							.getCode());
					String pids = "";
					if (users != null && users.size() > 0) {
						pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfoPlan
								.getProjectInfo().getId(), users.get(0));
					}
					map.put("users", pids);
					map.put("projectInfoPlanId", this.projectInfoPlan.getId() + "");
					if (isNew) {
						map.put("name",
								new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfoPlan.getCreatedTime()) + ","
										+ this.projectInfoPlan.getCreator() + "新建了项目计划:"
										+ this.projectInfoPlan.getName());
					} else {
						if (this.projectInfoPlan.getPercentt() == 100) {
							map.put("name",
									new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfoPlan.getCreatedTime())
											+ "," + this.projectInfoPlan.getCreator() + "完成了项目计划:"
											+ this.projectInfoPlan.getName());
						} else {
							map.put("name",
									new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfoPlan.getCreatedTime())
											+ "," + this.projectInfoPlan.getCreator() + "更新了项目计划:"
											+ this.projectInfoPlan.getName());
						}
					}
					map.put("url", "projectInfo/editProPlan.html?projectInfoPlan.id=" + this.projectInfoPlan.getId());
				}
				// 合同工作计划提交
				if (this.projectInfoPlan.getContractManagement() != null) {
					event.setName(eventType.getName());
					List<User> users = this.userManager.loadByKey("code", this.projectInfoPlan.getPersonnelFiles()
							.getCode());
					String pids = "";
					if (users != null && users.size() > 0) {
						pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfoPlan
								.getContractManagement().getProject().getId(), users.get(0));
					}
					map.put("users", pids);
					map.put("projectInfoPlanId", this.projectInfoPlan.getId() + "");
					if (isNew) {
						map.put("name",
								new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfoPlan.getCreatedTime()) + ","
										+ this.projectInfoPlan.getCreator() + "提交了合同计划:"
										+ this.projectInfoPlan.getName());
					} else {
						if (this.projectInfoPlan.getPercentt() == 100) {
							map.put("name",
									new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfoPlan.getCreatedTime())
											+ "," + this.projectInfoPlan.getCreator() + "完成了合同计划:"
											+ this.projectInfoPlan.getName());
						} else {
							map.put("name",
									new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfoPlan.getCreatedTime())
											+ "," + this.projectInfoPlan.getCreator() + "更新了合同计划:"
											+ this.projectInfoPlan.getName());
						}
					}

					map.put("url", "projectInfo/editProPlan.html?projectInfoPlan.id=" + this.projectInfoPlan.getId());
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
		if ("submit".equals(submit)) {
			addActionMessage(getText("projectInfoPlan.submit.success",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
			return "success";
		} else {
			addActionMessage(getText("projectInfoPlan.edit.success",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
			return "success";
		}
	}
	public String saveOrder(){
		try {
			List<ProjectInfoPlan> lInfoPlans = null;
			ids=request.getParameter("ids");
			HashMap map=new HashMap();
			map.put("ids", ids);
			if(hasId("contractManagement.id")){
				map.put("contractManagement.id", getId("contractManagement.id"));
				lInfoPlans=this.projectInfoPlanManager.loadOrderProjectInfoPlan(map);
			}
			if(hasId("projectInfo.id")){
				map.put("projectInfo.id", getId("projectInfo.id"));
				lInfoPlans=this.projectInfoPlanManager.loadOrderProjectInfoPlan(map);
			}
			if(lInfoPlans!=null&&lInfoPlans.size()>0){
				for(ProjectInfoPlan p: lInfoPlans){
					p.setOrderNumber(Integer.parseInt(request.getParameter("order"+p.getId())));
					projectInfoPlanManager.storeProjectInfoPlan(p);
				}
			}
		} catch (Exception e) {
			addActionMessage(getText("projectInfoPlanOrder.add.error",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
			return "error";
		}
		
			addActionMessage(getText("projectInfoPlanOrder.add.success",
					Arrays.asList(new Object[] { this.projectInfoPlan.getName() })));
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

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
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
			String[] keys = { "name", "code" };
			String[] values = { "计划状态", "211" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code",
																			// Long.valueOf("211"));
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


	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getContractManagementId() {
		return contractManagementId;
	}

	public void setContractManagementId(String contractManagementId) {
		this.contractManagementId = contractManagementId;
	}

	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
