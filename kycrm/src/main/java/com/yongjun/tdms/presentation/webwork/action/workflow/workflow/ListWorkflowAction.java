package com.yongjun.tdms.presentation.webwork.action.workflow.workflow;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workflow.Workflow;
import com.yongjun.tdms.service.workflow.workflow.WorkflowManager;

public class ListWorkflowAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private WorkflowManager workflowManager;
	private List<Workflow> workflowList;

	public ListWorkflowAction(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}

	protected String getAdapterName() {
		return "workflow";
	}

	public void prepare() throws Exception {
		if (hasIds("workflowIds")) {
			System.out.println(hasId("workflowIds"));
			this.workflowList = this.workflowManager.loadAllWorkflows(getIds("workflowIds"));
			System.out.println(this.workflowList);
		}

		setFirst(false);
	}

	private String delete() {
		try {
			this.workflowManager.deleteAllWorkflows(this.workflowList);
			addActionMessage(getText("delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		String result = this.workflowManager.disabled(this.workflowList);
		if (result.equals("success")) {
			addActionMessage(getText("disabled.success"));
		} else {
			addActionMessage(getText("disabled.failer"));
		}
		return result;
	}

	private String enabled() {
		String result = this.workflowManager.enabled(this.workflowList);
		if (result.equals("success")) {
			addActionMessage(getText("enabled.success"));
		} else {
			addActionMessage(getText("enabled.failer"));
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
