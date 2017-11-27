package com.yongjun.tdms.presentation.webwork.action.workspace.ontheroadBill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
import com.yongjun.tdms.service.workspace.ontheroadBill.OnTheRoadBillManager;

public class EditOnTheRoadBillAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final OnTheRoadBillManager onTheRoadBillManager;
	private final CodeValueManager codeValueManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final WorkflowCaseManager workflowCaseManager;
	private final ProjectInfoManager projectInfoManager;
	private final ContractManagementManager contractManagementManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	protected final GroupManager groupManager;
	private OnTheRoadBill onTheRoadBill;
	private PersonnelFiles personnelFiles;
	private ProjectInfo projectInfo;
	private ContractManagement contractManagement;

	public EditOnTheRoadBillAction(OnTheRoadBillManager onTheRoadBillManager, CodeValueManager codeValueManager,
			DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager,
			WorkflowCaseManager workflowCaseManager, ProjectInfoManager projectInfoManager,
			ContractManagementManager contractManagementManager, EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, GroupManager groupManager) {
		this.onTheRoadBillManager = onTheRoadBillManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.personnelFilesManager = personnelFilesManager;
		this.workflowCaseManager = workflowCaseManager;
		this.projectInfoManager = projectInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.groupManager = groupManager;
	}

	public void prepare() throws Exception {
		if (hasId("onTheRoadBill.id")) {
			this.onTheRoadBill = this.onTheRoadBillManager.loadOnTheRoadBill(getId("onTheRoadBill.id"));

			this.personnelFiles = this.onTheRoadBill.getApplyPerson();
		} else {
			this.onTheRoadBill = new OnTheRoadBill();
			User user = getUser();
			if (null != user.getCode()) {
				List list = this.personnelFilesManager.loadByKey("code", user.getCode());
				if (null != list) {
					this.personnelFiles = ((PersonnelFiles) list.get(0));
					this.onTheRoadBill.setApplyPerson(this.personnelFiles);
				}
			}
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if (hasId("contractManagement.id")) {
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
		}
	}

	public String save() throws Exception {
		boolean isNew = this.onTheRoadBill.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
			this.onTheRoadBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("status.id"))));
		} else {
			this.onTheRoadBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(219L)));
		}

		if (null == this.onTheRoadBill.getDept()) {
			this.onTheRoadBill.setDept(this.personnelFiles.getDept());
		}
		if (null == this.onTheRoadBill.getApplyPerson()) {
			this.onTheRoadBill.setApplyPerson(this.personnelFiles);
		}
		if(hasId("projectInfo.id")){
			this.onTheRoadBill.setProjectInfo(projectInfo);
		}
		if(hasId("contractManagement.id")){
			this.onTheRoadBill.setContractManagement(contractManagement);
		}
		this.onTheRoadBill.setOrganization(this.userManager.getOrganization());
		this.onTheRoadBill.setIsSaved(this.request.getParameter("isSaved"));
		if (isNew) {
			String newCode = autoCompleteCode();
			this.onTheRoadBill.setCode(newCode);
		}
		String submit = null;
		try {
			this.onTheRoadBillManager.storeOnTheRoadBill(this.onTheRoadBill);
			if ("1".equals(this.request.getParameter("isSaved"))) {
				Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
				Set<Long> idSet = new HashSet<Long>();
				idSet.add(getUser().getId());
				for (User user : noticePers) {
					idSet.add(user.getId());
				}
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10026");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventTypes不存在！");
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName(eventType.getName());
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap<String, String>();
				// 查询领导
				PersonnelFiles pFiles = this.personnelFiles.getSuperiorLeader();
				while (pFiles != null) {
					List<User> leader = userManager.loadByKey("code", pFiles.getCode());
					idSet.add(leader.get(0).getId());
					pFiles = pFiles.getSuperiorLeader();
				}
				String ids = "";
				for (Long id : idSet) {
					ids += id + ",";
				}
				map.put("users", ids.substring(0, ids.length() - 1));
				map.put("onTheRoadBillId", this.onTheRoadBill.getId() + "");
				map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.onTheRoadBill.getCreatedTime())+","+this.onTheRoadBill.getApplyPerson().getName()+"提交了公出单");
				map.put("url", "onTheRoadBill/editOnTheRoadBill.html?readOnly=false&onTheRoadBill.id="
						+ this.onTheRoadBill.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				submit = "submit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("onTheRoadBill.save.error"));
				return ERROR;
			}
			if(submit!=null){
				addActionMessage(getText("onTheRoadBill.update.error"));
				return ERROR;
			}
			addActionMessage(getText("onTheRoadBill.edit.error"));
			return ERROR;
		}

		if (isNew) {
			WorkflowTrigger(this.personnelFiles);
			addActionMessage(getText("onTheRoadBill.save.success"));
			return "new";
		}
		if(submit!=null){
			addActionMessage(getText("onTheRoadBill.update.success"));
			return SUCCESS;
		}
		addActionMessage(getText("onTheRoadBill.edit.success"));
		return SUCCESS;
	}

	public void WorkflowTrigger(PersonnelFiles person) {
		if (null != this.onTheRoadBill.getId()) {
			String url = "onTheRoadBill/editOnTheRoadBill.html?onTheRoadBill.id="
					+ String.valueOf(this.onTheRoadBill.getId());
			this.workflowCaseManager.startWorkflowCase("02103", this.onTheRoadBill.getId(), person, url);
		} else {
			List otrList = null;
			try {
				otrList = this.onTheRoadBillManager.loadByKey("code", this.onTheRoadBill.getCode());
			} catch (Exception e) {
				this.logger.info("根据code查询公出单出错！");
			}
			if ((null != otrList) && (!otrList.isEmpty())) {
				this.onTheRoadBill = ((OnTheRoadBill) otrList.get(0));
				WorkflowTrigger(person);
			}
		}
	}

	public String autoCompleteCode() {
		String maxCode = this.onTheRoadBillManager.getMaxPFCode("GCD", this.userManager.getOrganization().getId());

		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return "GCD-000" + num;
			if (num < 100)
				return "GCD-00" + num;
			if (num < 1000) {
				return "GCD-0" + num;
			}
			return "GCD-" + num;
		}

		return "GCD-0001";
	}

	public OnTheRoadBill getOnTheRoadBill() {
		return this.onTheRoadBill;
	}

	public void setOnTheRoadBill(OnTheRoadBill onTheRoadBill) {
		this.onTheRoadBill = onTheRoadBill;
	}

	public List<CodeValue> getAllStatus() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "020");

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<Department> getAllDepts() {
		List depts = this.departmentManager.loadAllDepartments();
		return depts;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}
}
