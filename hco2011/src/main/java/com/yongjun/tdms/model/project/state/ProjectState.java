package com.yongjun.tdms.model.project.state;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectState extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3061846594849843863L;
	private ProjectInfo projectInfo;
	private CodeValue beforeState;
	private CodeValue newState;
	private String explain;//说明
	
	@Override
	public boolean equals(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public CodeValue getBeforeState() {
		return beforeState;
	}

	public void setBeforeState(CodeValue beforeState) {
		this.beforeState = beforeState;
	}

	public CodeValue getNewState() {
		return newState;
	}

	public void setNewState(CodeValue newState) {
		this.newState = newState;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
