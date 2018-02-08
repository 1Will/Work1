package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseForm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.expensemanagement.expenseForm.ExpenseFormManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditExpenseFormAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ExpenseFormManager expenseFormManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final ContractManagementManager contractManagementManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private ExpenseForm expenseForm;
	private ProjectInfo projectInfo;
	private PersonnelFiles applyPeople;
	private ContractManagement contractManagement;
	private String popWindowFlag;
	private final FlowManager flowManager;
	private String activitiFLow ;

	public EditExpenseFormAction(ExpenseFormManager expenseFormManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager, DepartmentManager departmentManager,
			CodeValueManager codeValueManager,ContractManagementManager contractManagementManager,EventNewManager eventNewManager
			,EventTypeManager eventTypeManager,PersonnelFilesToUserManager personnelFilesToUserManager,FlowManager flowManager) {
		this.expenseFormManager = expenseFormManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.contractManagementManager = contractManagementManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.flowManager=flowManager;
	}

	public void prepare() throws Exception {
		if (request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
		if (request.getParameter("activitiFLow") != null) {
			this.activitiFLow = request.getParameter("activitiFLow");
		}
		if (hasId("expenseForm.id")) {
			this.expenseForm = expenseFormManager.loadExpenseForm(getId("expenseForm.id"));
		} else {
			this.expenseForm = new ExpenseForm();
			List<PersonnelFiles> pers = personnelFilesManager.loadByKey("code", userManager.getUser().getCode());
			if(pers!=null&&pers.size()>0){
				this.expenseForm.setApplyPeople(pers.get(0));
			}
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if (hasId("applyPeople.id")) {
			this.applyPeople = personnelFilesManager.loadPersonnel(getId("applyPeople.id"));
		}
		if(hasId("contractManagement.id")){
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.expenseForm.setContractManagement(this.contractManagement);
		}
	}

	public String save() {
		boolean isNew = this.expenseForm.isNew();
		this.expenseForm.setProjectInfo(this.projectInfo);
		this.expenseForm.setApplyPeople(this.applyPeople);
		
		if (!StringUtils.isEmpty(this.request.getParameter("flow.id"))) {
			this.expenseForm.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request
					.getParameter("flow.id"))));;
		}
		if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
			this.expenseForm.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("status.id"))));	
		} else {
			try {
				this.expenseForm.setStatus((CodeValue) this.codeValueManager.loadByKey("code", "02000").get(0));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.expenseForm.setIsSaved(this.request.getParameter("expenseForm.isSaved"));
		String submit = null;
		try {
			this.expenseFormManager.storeExpenseForm(this.expenseForm);
			if ("1".equals(this.request.getParameter("expenseForm.isSaved"))) {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10015");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventType不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					event.setUserId(this.userManager.getUser().getId() + "");
					Map<String, String> map = new HashMap();
					String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.expenseForm.getProjectInfo().getId(),
							this.expenseForm.getProjectInfo().getCreateUser());
					map.put("users", pids);
					map.put("expenseFormId", this.expenseForm.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.expenseForm.getCreatedTime())+","+this.expenseForm.getApplyPeople().getName()+"提交了报销单");
					map.put("url", "expenseForm/editExpenseFormAction.html?popWindowFlag=popWindowFlag&expenseForm.id="+this.expenseForm.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
				}
			
			if (isNew) {
				addActionMessage(getText("expenseForm.add.success"));
				return NEW;
			}
			if ("submit".equals(submit)) {
				addActionMessage(getText("expenseForm.submit.success"));
				return SUCCESS;
			}
			addActionMessage(getText("expenseForm.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("expenseForm.add.error"));
				return ERROR;
			}
			if ("submit".equals(submit)) {
				addActionMessage(getText("expenseForm.submit.error"));
				return ERROR;
			}
			addActionMessage(getText("expenseForm.edit.error"));
			return ERROR;
		}
	}

	public ExpenseForm getExpenseForm() {
		return expenseForm;
	}

	public void setExpenseForm(ExpenseForm expenseForm) {
		this.expenseForm = expenseForm;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public PersonnelFiles getApplyPeople() {
		return applyPeople;
	}

	public void setApplyPeople(PersonnelFiles applyPeople) {
		this.applyPeople = applyPeople;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
	public List<Flow> getAllFlows() {
		List flowList = null;
		try {
			CodeValue codeValue =codeValueManager.loadByKey("code","t_ExpenseForm").get(0);
			String[] keys = {"openOrNot","flowCode"};
			Object[] objects = {"0",codeValue.getId()};
			 flowList = flowManager.loadByKeyArray(keys, objects);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return flowList;
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

	public String getActivitiFLow() {
		return activitiFLow;
	}

	public void setActivitiFLow(String activitiFLow) {
		this.activitiFLow = activitiFLow;
	}
	
}
