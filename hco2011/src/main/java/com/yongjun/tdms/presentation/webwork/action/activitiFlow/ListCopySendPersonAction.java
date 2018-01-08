package com.yongjun.tdms.presentation.webwork.action.activitiFlow;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;
import com.yongjun.tdms.service.activitiFlow.CopySendPersonManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;

import java.util.List;
import java.util.Map;


public class ListCopySendPersonAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CopySendPersonManager copySendPersonManager;
	private List<CopySendPerson> copySendPersonList;
	private long flowId;
	private long bussnessId;
	
	
	public ListCopySendPersonAction(CopySendPersonManager copySendPersonManager) {
		this.copySendPersonManager = copySendPersonManager;
	}

	protected String getAdapterName() {
		return "copySendPerson";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public void prepare() throws Exception {
		if (hasIds("copySendPersonIds")) {
			this.copySendPersonList = this.copySendPersonManager.loadAllCopySendPersons(getIds("copySendPersonIds"));
		}
		if (hasId("flow.id")) {
			this.flowId = getId("flow.id");
		}
		if (hasId("bussnessId")) {
			this.bussnessId = getId("bussnessId");
		}
	}

	private String delete() {
		try {
			System.out.println(this.copySendPersonList);
			this.copySendPersonManager.deleteAllCopySendPersons(this.copySendPersonList);
			addActionMessage(getText("copySendPerson.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("copySendPerson.delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		String result = this.copySendPersonManager.disabled(this.copySendPersonList);
		if (result.equals("success")) {
			addActionMessage(getText("copySendPerson.disabled.success"));
		} else {
			addActionMessage(getText("copySendPerson.disabled.error"));
		}
		return result;
	}

	private String enabled() {
		String result = this.copySendPersonManager.enabled(this.copySendPersonList);
		if (result.equals("success")) {
			addActionMessage(getText("copySendPerson.enabled.success"));
		} else {
			addActionMessage(getText("copySendPerson.enabled.error"));
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


	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public long getBussnessId() {
		return bussnessId;
	}

	public void setBussnessId(long bussnessId) {
		this.bussnessId = bussnessId;
	}
	
	
}
