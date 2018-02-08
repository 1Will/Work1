package com.yongjun.tdms.presentation.webwork.action.activitiFlow;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.activitiFlow.RunTaskManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;

import java.util.List;
import java.util.Map;

public class ListMyRunTaskAction extends ValueListAction {

	private static final long serialVersionUID = 1L;
	private RunTaskManager runTaskManager;
	private FlowManager flowManager;
	private List<RunTask> runTaskList;
	private Flow flow;

	public ListMyRunTaskAction(RunTaskManager runTaskManager, FlowManager flowManager) {
		this.runTaskManager = runTaskManager;
		this.flowManager = flowManager;
	}

	protected String getAdapterName() {
		return "runTask";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User user = this.userManager.getUser();
		map.put("assignee.code", user.getCode());
		return map;
	}

	public void prepare() throws Exception {
		if (hasIds("myRunTaskIds")) {
			this.runTaskList = this.runTaskManager.loadAllRunTasks(getIds("myRunTaskIds"));
		}
		System.out.println(hasId("flow.id"));
		System.out.println(this.request.getParameter("flow.id"));
		if (hasId("flow.id")) {
			Long flowId = getId("flow.id");
			this.flow = this.flowManager.loadFlow(flowId);
		}
	}

	private String delete() {
		try {
			System.out.println(this.runTaskList);
			this.runTaskManager.deleteAllRunTasks(this.runTaskList);
			addActionMessage(getText("runTask.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("runTask.delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		String result = this.runTaskManager.disabled(this.runTaskList);
		if (result.equals("success")) {
			addActionMessage(getText("runTask.disabled.success"));
		} else {
			addActionMessage(getText("runTask.disabled.error"));
		}
		return result;
	}

	private String enabled() {
		String result = this.runTaskManager.enabled(this.runTaskList);
		if (result.equals("success")) {
			addActionMessage(getText("runTask.enabled.success"));
		} else {
			addActionMessage(getText("runTask.enabled.error"));
		}
		return result;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		if (isDisabled()) {
			return disabled();
		}
		if (isEnable()) {
			return enabled();
		}
		return "success";
	}
}
