package com.yongjun.tdms.presentation.webwork.action.workspace.warnning;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

public class ListWorkWarnningAction extends ValueListAction {
	private static final long serialVersionUID = -5846328003010519171L;
	private final WorkWarnningManager workWarnningManager;
	private List<WorkWarnning> workWarnnings;
	private String isRead;

	public ListWorkWarnningAction(WorkWarnningManager workWarnningManager) {
		this.workWarnningManager = workWarnningManager;
	}

	protected String getAdapterName() {
		return "warnnings";
	}

	public String execute() {
		if (isRead()) {
			return readWorkWarnning();
		}
		
		if(isDelete()){
			return delete();
		}

		if (isUnRead()) {
			return unReadWorkWarnning();
		}
		return "success";
	}

	public String readWorkWarnning() {
		try {
			this.workWarnningManager.readAllWorkWarnnings(this.workWarnnings);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addActionMessage(getText("warnning.read.success"));
		return "success";
	}
	
	public String delete(){
		try {
			this.workWarnningManager.deleteAllWorkWarnnings(this.workWarnnings);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addActionMessage(getText("warnning.delete.success"));
		return "success";
	}

	public String unReadWorkWarnning() {
		try {
			this.workWarnningManager.unReadAllWorkWarnnings(this.workWarnnings);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addActionMessage(getText("warnning.unRead.success"));
		return "success";
	}

	public void prepare() {
		if (hasIds("workWarnningIds"))
			this.workWarnnings = this.workWarnningManager.loadAllWorkWarnnings(getIds("workWarnningIds"));
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.request.getParameter("workWarnning.id") != null) {
			int rNId = Integer.valueOf(this.request.getParameter("workWarnning.id")).intValue();
			map.put("workWarnning.id", Integer.valueOf(rNId));
		}
		if(this.request.getParameter("name")!=null){
			map.put("name", "%"+this.request.getParameter("name")+"%");
		}
		map.put("loginUser.id",getLoginUser().getId());
		if(null==request.getParameter("isRead")){
			map.put("isRead",0);
		}
		return map;
	}


	private boolean isRead() {
		return hasKey("read");
	}

	private boolean isUnRead() {
		return hasKey("unRead");
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
}
