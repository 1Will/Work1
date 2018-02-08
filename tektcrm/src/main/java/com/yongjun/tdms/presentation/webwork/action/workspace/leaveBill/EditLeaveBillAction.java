package com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
import com.yongjun.tdms.service.activitiFlow.CopySendPersonManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
import com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager;

import net.sf.json.JSONObject;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditLeaveBillAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final LeaveBillManager leaveBillManager;
	private final CodeValueManager codeValueManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final WorkflowCaseManager workflowCaseManager;
	private final FlowManager flowManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	protected final GroupManager groupManager;
	private final CopySendPersonManager copySendPersonManager;
	private LeaveBill leaveBill;
	private PersonnelFiles personnelFiles;
	private String activitiFLow ;

	public EditLeaveBillAction(LeaveBillManager leaveBillManager, CodeValueManager codeValueManager,
			DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager,
			WorkflowCaseManager workflowCaseManager, FlowManager flowManager,EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, GroupManager groupManager,CopySendPersonManager copySendPersonManager) {
		this.leaveBillManager = leaveBillManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.personnelFilesManager = personnelFilesManager;
		this.workflowCaseManager = workflowCaseManager;
		this.flowManager = flowManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.groupManager = groupManager;
		this.copySendPersonManager=copySendPersonManager;
	}

	public void prepare() throws Exception {
		if (request.getParameter("activitiFLow") != null) {
			this.activitiFLow = request.getParameter("activitiFLow");
		}
		if (hasId("leaveBill.id")) {
			this.leaveBill = this.leaveBillManager.loadLeaveBill(getId("leaveBill.id"));
			this.personnelFiles = this.leaveBill.getApplyPerson();
		} else {
			this.leaveBill = new LeaveBill();
			User user = getUser();
			if (null != user.getCode()) {
				List list = this.personnelFilesManager.loadByKey("code", user.getCode());
				if (null != list) {
					this.personnelFiles = ((PersonnelFiles) list.get(0));
					this.leaveBill.setApplyPerson(this.personnelFiles);
				}
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.leaveBill.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
			this.leaveBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("status.id"))));
		} else {
			this.leaveBill.setStatus((CodeValue) this.codeValueManager.loadByKey("code", "02000").get(0));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("type.id"))) {
			this.leaveBill.setType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("type.id"))));
		}
		if (!StringUtils.isEmpty(this.request.getParameter("flow.id"))) {
			this.leaveBill.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request
					.getParameter("flow.id"))));;
		}

		if (null != this.personnelFiles.getDept()) {
			this.leaveBill.setDept(this.personnelFiles.getDept());
		}
		if (null == this.leaveBill.getApplyPerson()) {
			this.leaveBill.setApplyPerson(this.personnelFiles);
		}

		this.leaveBill.setOrganization(this.userManager.getOrganization());
		this.leaveBill.setIsSaved(this.request.getParameter("isSaved"));
		if (isNew) {
		/*	String newCode = autoCompleteCode();
			this.leaveBill.setCode(newCode);*/
			this.leaveBillManager.storeLeaveBill(this.leaveBill);
			Set<User> users = groupManager.getGroupByGroupName("请假通知组").getUsers();
			if(users!=null && users.size()>0){
				for(User user:users){
					CopySendPerson c=new CopySendPerson();
					c.setBussnessId(this.leaveBill.getId());
					c.setFlow(this.leaveBill.getFlow());
					c.setPerson(personnelFilesManager.loadByKey("code", user.getCode()).get(0));
					copySendPersonManager.storeCopySendPerson(c);
				}
			}
			if (this.leaveBill.getType().getName().equals("调休"))
				addActionMessage(getText("leaveBill.save.success"));
			else {
				addActionMessage(getText("leave.save.success"));
			}
			WorkflowTrigger(this.personnelFiles);
			return "new";
		}
		String submit =null;
		try {
			this.leaveBillManager.storeLeaveBill(this.leaveBill);
			
			if ("1".equals(this.request.getParameter("isSaved"))) {
				Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
				Set<Long> idSet = new HashSet<Long>();
				idSet.add(getUser().getId());
				for (User user : noticePers) {
					idSet.add(user.getId());
				}
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10031");
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
					map.put("leaveBillId", this.leaveBill.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.leaveBill.getCreatedTime())+","+this.leaveBill.getApplyPerson().getName()+"提交了请假单");
					map.put("url", "leaveBill/editLeaveBill.html?readOnly=false&leaveBill.id="+this.leaveBill.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
			}
			}catch (Exception e) {
				e.printStackTrace();
				if (isNew) {
					addActionMessage(getText("leaveBill.save.error"));
					return ERROR;
				}
				if(submit!=null){
					addActionMessage(getText("leaveBill.submit.error"));
					return ERROR;
				}
				addActionMessage(getText("leaveBill.edit.error"));
				return ERROR;
			}
		if (isNew) {
			WorkflowTrigger(this.personnelFiles);
			addActionMessage(getText("leaveBill.save.success"));
			return "new";
		}
		if(submit!=null){
			addActionMessage(getText("leaveBill.submit.success"));
			return SUCCESS;
		}
		addActionMessage(getText("leaveBill.edit.success"));
		return SUCCESS;
	}

	public void WorkflowTrigger(PersonnelFiles person) {
		if (null != this.leaveBill.getId()) {
			String url = "leaveBill/editLeaveBill.html?leaveBill.id=" + String.valueOf(this.leaveBill.getId());
			String aoCode = this.leaveBill.getType().getCode().trim();
			if (aoCode.equals("03000")) {
				this.workflowCaseManager.startWorkflowCase("02101", this.leaveBill.getId(), person, url);
			} else {
				this.workflowCaseManager.startWorkflowCase("02102", this.leaveBill.getId(), person, url);
			}
		} else {
			List lbList = null;
			try {
				lbList = this.leaveBillManager.loadByKey("code", this.leaveBill.getCode());
			} catch (Exception e) {
				this.logger.info("根据code查询请假调休出错！");
			}
			if ((null != lbList) && (!lbList.isEmpty())) {
				this.leaveBill = ((LeaveBill) lbList.get(0));
				WorkflowTrigger(person);
			}
		}
	}

	/*public String autoCompleteCode() {
		String maxCode = this.leaveBillManager.getMaxPFCode("QJTX", this.userManager.getOrganization().getId());
		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return "QJTX-000" + num;
			if (num < 100)
				return "QJTX-00" + num;
			if (num < 1000) {
				return "QJTX-0" + num;
			}
			return "QJTX-" + num;
		}

		return "QJTX-0001";
	}*/

	public List<CodeValue> getAllStatus() {
		List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"020","类型"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);

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

	public List<CodeValue> getAllTypes() {
		List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"030","请假/调休"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);
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
	
	public List<Flow> getAllFlows() {
		List flowList = null;
		try {
			CodeValue codeValue =codeValueManager.loadByKey("code","t_LeaveBill").get(0);
			String[] keys = {"openOrNot","flowCode"};
			Object[] objects = {"0",codeValue.getId()};
		    flowList = flowManager.loadByKeyArray(keys, objects);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flowList;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public LeaveBill getLeaveBill() {
		return this.leaveBill;
	}

	public void setLeaveBill(LeaveBill leaveBill) {
		this.leaveBill = leaveBill;
	}

	public String getActivitiFLow() {
		return activitiFLow;
	}

	public void setActivitiFLow(String activitiFLow) {
		this.activitiFLow = activitiFLow;
	}
	
}
