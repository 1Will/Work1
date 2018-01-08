package com.yongjun.tdms.presentation.webwork.action.workspace.overTimeBill;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;

public class EditOverTimeBillAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
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
	private OverTimeBillManager overTimeBillManager;
	private OverTimeBill overTimeBill;
	private PersonnelFiles personnelFiles;
	private ProjectInfo projectInfo;
	private ContractManagement contractManagement;
	private String activitiFLow ;
	private final FlowManager flowManager;

	public EditOverTimeBillAction(OverTimeBillManager overTimeBillManager, CodeValueManager codeValueManager,
			DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager,
			WorkflowCaseManager workflowCaseManager, ProjectInfoManager projectInfoManager,
			ContractManagementManager contractManagementManager, EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, GroupManager groupManager, FlowManager flowManager) {
		this.overTimeBillManager = overTimeBillManager;
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
		this.flowManager=flowManager;
	}

	public void prepare() throws Exception {
		if (request.getParameter("activitiFLow") != null) {
			this.activitiFLow = request.getParameter("activitiFLow");
		}
		if (hasId("overTimeBill.id")) {
			this.overTimeBill = this.overTimeBillManager.loadOverTimeBill(getId("overTimeBill.id"));
			this.personnelFiles = this.overTimeBill.getApplyPerson();
		} else {
			this.overTimeBill = new OverTimeBill();

			User user = getUser();
			if (null != user.getCode()) {
				List list = this.personnelFilesManager.loadByKey("code", user.getCode());
				if (null != list) {
					this.personnelFiles = ((PersonnelFiles) list.get(0));
					this.overTimeBill.setApplyPerson(this.personnelFiles);
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
		boolean isNew = this.overTimeBill.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("dept.id"))) {
			this.overTimeBill.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request
					.getParameter("dept.id"))));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
			this.overTimeBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("status.id"))));
		} else {
			this.overTimeBill.setStatus((CodeValue) this.codeValueManager.loadByKey("code", "02000").get(0));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("flow.id"))) {
			this.overTimeBill.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request
					.getParameter("flow.id"))));
		}

		if (null == this.overTimeBill.getDept()) {
			this.overTimeBill.setDept(this.personnelFiles.getDept());
		}
		if (null == this.overTimeBill.getApplyPerson()) {
			this.overTimeBill.setApplyPerson(this.personnelFiles);
		}

		Date startTime = this.overTimeBill.getStartTime();
		Date endTime = this.overTimeBill.getEndTime();
		Long a = Long.valueOf(endTime.getTime());
		Double a1 = Double.valueOf(a.doubleValue());

		Long b = Long.valueOf(startTime.getTime());
		Double b1 = Double.valueOf(b.doubleValue());
		Double sum1 = Double.valueOf((a1.doubleValue() - b1.doubleValue()) / 3600000.0D);

		DecimalFormat df = new DecimalFormat("0.0");
		String sum2 = df.format(sum1);
		Double sum = Double.valueOf(Double.parseDouble(sum2));
		this.overTimeBill.setManHour(sum);

		this.overTimeBill.setOrganization(this.userManager.getOrganization());
		if(hasId("projectInfo.id")){
			this.overTimeBill.setProjectInfo(projectInfo);
		}
		if(hasId("contractManagement.id")){
			this.overTimeBill.setContractManagement(contractManagement);
		}
		this.overTimeBill.setIsSaved(this.request.getParameter("isSaved"));
		if (isNew) {
			String newCode = autoCompleteCode();
			this.overTimeBill.setCode(newCode);
		}
		String submit =null;
		try {
			this.overTimeBillManager.storeOverTimeBill(this.overTimeBill);
			
			if ("1".equals(this.request.getParameter("isSaved"))) {
				Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
				Set<Long> idSet = new HashSet<Long>();
				idSet.add(getUser().getId());
				for (User user : noticePers) {
					idSet.add(user.getId());
				}
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10025");
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
					map.put("overTimeBillId", this.overTimeBill.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.overTimeBill.getCreatedTime())+","+this.overTimeBill.getApplyPerson().getName()+"提交了加班单");
					map.put("url", "overTimeBill/listOverTimeBill.html?readOnly=false&overTimeBill.id="+this.overTimeBill.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
			}
			}catch (Exception e) {
				e.printStackTrace();
				if (isNew) {
					addActionMessage(getText("overTimeBill.save.error"));
					return ERROR;
				}
				if(submit!=null){
					addActionMessage(getText("overTimeBill.update.error"));
					return ERROR;
				}
				addActionMessage(getText("overTimeBill.edit.error"));
				return ERROR;
			}
		
		
		
		if (isNew) {
			WorkflowTrigger(this.personnelFiles);
			addActionMessage(getText("overTimeBill.save.success"));
			return "new";
		}
		if(submit!=null){
			addActionMessage(getText("overTimeBill.update.success"));
			return SUCCESS;
		}
		addActionMessage(getText("overTimeBill.edit.success"));
		return SUCCESS;
	}

	public void WorkflowTrigger(PersonnelFiles person) {
		if (null != this.overTimeBill.getId()) {
			String url = "overTimeBill/editOverTimeBill.html?overTimeBill.id="
					+ String.valueOf(this.overTimeBill.getId());
			this.workflowCaseManager.startWorkflowCase("02104", this.overTimeBill.getId(), person, url);
		} else {
			List lbList = null;
			try {
				lbList = this.overTimeBillManager.loadByKey("code", this.overTimeBill.getCode());
			} catch (Exception e) {
				this.logger.info("根据code查询加班单出错！");
			}
			if ((null != lbList) && (!lbList.isEmpty())) {
				this.overTimeBill = ((OverTimeBill) lbList.get(0));
				WorkflowTrigger(person);
			}
		}
	}

	public String autoCompleteCode() {
		String maxCode = this.overTimeBillManager.getMaxPFCode("JBD", this.userManager.getOrganization().getId());

		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return "JBD-000" + num;
			if (num < 100)
				return "JBD-00" + num;
			if (num < 1000) {
				return "JBD-0" + num;
			}
			return "JBD-" + num;
		}

		return "JBD-0001";
	}

	public OverTimeBill getOverTimeBill() {
		return this.overTimeBill;
	}

	public void setOverTimeBill(OverTimeBill overTimeBill) {
		this.overTimeBill = overTimeBill;
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

	public String getActivitiFLow() {
		return activitiFLow;
	}

	public void setActivitiFLow(String activitiFLow) {
		this.activitiFLow = activitiFLow;
	}
	public List<Flow> getAllFlows() {
		List depts = null;
		try {
			depts = this.flowManager.loadByKey("openOrNot", "0");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return depts;
	}
	
}
