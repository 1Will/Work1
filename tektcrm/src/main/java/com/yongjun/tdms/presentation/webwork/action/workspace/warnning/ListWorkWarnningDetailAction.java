package com.yongjun.tdms.presentation.webwork.action.workspace.warnning;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningDetailManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

public class ListWorkWarnningDetailAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private WorkWarnningManager workWarnningManager;
	private WorkWarnning workWarnning;
	String type = "";

	public void prepare() {
		Long warnId = Long.valueOf(this.request.getParameter("workWarnningId"));
		this.workWarnning = this.workWarnningManager.loadWorkWarnning(warnId);
		this.type = workWarnning.getType().trim();
	}
	
	public void setRead(Long warnId){
		if ((null != warnId) && (warnId.longValue() > 0L)) {
			WorkWarnning w = this.workWarnningManager.loadWorkWarnning(warnId);
			w.setReadFlag(true);
			this.workWarnningManager.storeWorkWarnning(w);
		}
	}
	
	protected String getAdapterName() {
		return "workWarnningDetail";
	}

	public void setWorkWarnningDetailManager(WorkWarnningDetailManager workWarnningDetailManager) {
	}

	public void setWorkWarnningManager(WorkWarnningManager workWarnningManager) {
		this.workWarnningManager = workWarnningManager;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WorkWarnning getWorkWarnning() {
		return workWarnning;
	}

	public void setWorkWarnning(WorkWarnning workWarnning) {
		this.workWarnning = workWarnning;
	}
}
