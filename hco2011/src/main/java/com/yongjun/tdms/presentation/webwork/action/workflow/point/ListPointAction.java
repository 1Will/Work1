package com.yongjun.tdms.presentation.webwork.action.workflow.point;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.model.workflow.Point;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.point.PointManager;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ListPointAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private PointManager pointManager;
	private FlowManager flowManager;
	private List<Point> pointList;
	private Flow flow;

	public ListPointAction(PointManager pointManager, FlowManager flowManager) {
		this.pointManager = pointManager;
		this.flowManager = flowManager;
	}

	protected String getAdapterName() {
		return "point";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != this.flow) {
			map.put("flow.id", this.flow.getId());
		}
		return map;
	}

	public void prepare() throws Exception {
		if (hasIds("pointIds")) {
			this.pointList = this.pointManager.loadAllPoints(getIds("pointIds"));
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
			System.out.println(this.pointList);
			this.pointManager.deleteAllPoints(this.pointList);
			addActionMessage(getText("point.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("point.delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		String result = this.pointManager.disabled(this.pointList);
		if (result.equals("success")) {
			addActionMessage(getText("point.disabled.success"));
		} else {
			addActionMessage(getText("point.disabled.error"));
		}
		return result;
	}

	private String enabled() {
		String result = this.pointManager.enabled(this.pointList);
		if (result.equals("success")) {
			addActionMessage(getText("point.enabled.success"));
		} else {
			addActionMessage(getText("point.enabled.error"));
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
