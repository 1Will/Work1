package com.yongjun.tdms.presentation.webwork.action.workflow.flow;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.workflow.flow.FlowManager;

import java.util.List;
import java.util.Map;

public class ListFlowAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private FlowManager flowManager;
	private List<Flow> flowList;

	public ListFlowAction(FlowManager flowManager) {
		this.flowManager = flowManager;
	}

	protected String getAdapterName() {
		return "flow";
	}
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public void prepare() throws Exception {
		if (hasIds("flowIds")) {
			this.flowList = this.flowManager.loadAllFlows(getIds("flowIds"));
		}
		setFirst(false);
	}

	private String delete() {
		try {
			this.flowManager.deleteAllFlows(this.flowList);
			addActionMessage(getText("delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		String result = this.flowManager.disabled(this.flowList);
		if (result.equals("success")) {
			addActionMessage(getText("disabled.success"));
		} else {
			addActionMessage(getText("disabled.error"));
		}
		return result;
	}

	private String enabled() {
		String result = this.flowManager.enabled(this.flowList);
		if (result.equals("success")) {
			addActionMessage(getText("enabled.success"));
		} else {
			addActionMessage(getText("enabled.error"));
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
