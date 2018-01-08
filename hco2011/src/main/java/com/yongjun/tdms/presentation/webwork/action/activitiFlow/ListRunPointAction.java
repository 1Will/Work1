package com.yongjun.tdms.presentation.webwork.action.activitiFlow;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.model.workflow.Point;
import com.yongjun.tdms.service.activitiFlow.RunPointManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.point.PointManager;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ListRunPointAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private RunPointManager runPointManager;
	private FlowManager flowManager;
	private List<RunPoint> runPointList;
	private Flow flow;
	
	//historyTask区分审批人
	private String historyTask;
	
	public ListRunPointAction(RunPointManager runPointManager, FlowManager flowManager) {
		this.runPointManager = runPointManager;
		this.flowManager = flowManager;
	}

	protected String getAdapterName() {
		return "runPoint";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public void prepare() throws Exception {
		if (hasIds("runPointIds")) {
			this.runPointList = this.runPointManager.loadAllRunPoints(getIds("runPointIds"));
		}
		System.out.println(hasId("flow.id"));
		System.out.println(this.request.getParameter("flow.id"));
		if (hasId("flow.id")) {
			Long flowId = getId("flow.id");
			this.flow = this.flowManager.loadFlow(flowId);
		}
		String historyTask_req = request.getParameter("historyTask");
		if(historyTask_req != null && historyTask_req != ""){
			this.historyTask = historyTask_req;
		}
	}

	private String delete() {
		try {
			System.out.println(this.runPointList);
			this.runPointManager.deleteAllRunPoints(this.runPointList);
			addActionMessage(getText("runPoint.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("runPoint.delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		String result = this.runPointManager.disabled(this.runPointList);
		if (result.equals("success")) {
			addActionMessage(getText("runPoint.disabled.success"));
		} else {
			addActionMessage(getText("runPoint.disabled.error"));
		}
		return result;
	}

	private String enabled() {
		String result = this.runPointManager.enabled(this.runPointList);
		if (result.equals("success")) {
			addActionMessage(getText("runPoint.enabled.success"));
		} else {
			addActionMessage(getText("runPoint.enabled.error"));
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

	public String getHistoryTask() {
		return historyTask;
	}

	public void setHistoryTask(String historyTask) {
		this.historyTask = historyTask;
	}
	
}
