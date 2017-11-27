package com.yongjun.tdms.presentation.webwork.action.project.state;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.state.ProjectState;
import com.yongjun.tdms.service.project.state.ProjectStateManager;

@SuppressWarnings("rawtypes")
public class ListProjectStateAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ProjectStateManager projectStateManager;
	
	private List<ProjectState> projectStates = null;
	private ProjectState projectState;

	public ListProjectStateAction(ProjectStateManager projectStateManager) {
		this.projectStateManager = projectStateManager;
	}
	public void prepare() throws Exception {
		if(hasId("projectState.id")){
			this.projectState =projectStateManager.loadProjectState(getId("projectState.id"));
		}
		
		if(hasId("projectStates.id")){
			this.projectStates =projectStateManager.loadAllProjectState(getIds("projectStates.id"));
		}
	}

	protected String getAdapterName() {
		return "projectStateHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return "success";
	}
	
	public String delete() {
		this.projectStateManager.deleteAllProjectState(projectStates);
		addActionMessage(getText("contractManagement.delete.success"));
		return "success";
	}
	public ProjectState getProjectState() {
		return projectState;
	}
	public void setProjectState(ProjectState projectState) {
		this.projectState = projectState;
	}
}
