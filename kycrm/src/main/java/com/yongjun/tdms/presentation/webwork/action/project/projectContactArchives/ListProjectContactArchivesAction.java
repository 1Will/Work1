package com.yongjun.tdms.presentation.webwork.action.project.projectContactArchives;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectContactArchives.ProjectContactArchives;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectContactArchives.ProjectContactArchivesManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListProjectContactArchivesAction extends ValueListAction {
	private static final long serialVersionUID = 1L;

	private final ProjectContactArchivesManager projectContactArchivesManager;
	private final ProjectInfoManager projectInfoManager;
	private final CodeValueManager codeValueManager;

	private ProjectInfo projectInfo;
	private List<ProjectContactArchives> projectContactArchiveses;

	public ListProjectContactArchivesAction(ProjectContactArchivesManager projectContactArchivesManager,
			ProjectInfoManager projectInfoManager, CodeValueManager codeValueManager) {
		this.projectContactArchivesManager = projectContactArchivesManager;
		this.projectInfoManager = projectInfoManager;
		this.codeValueManager = codeValueManager;

	}

	protected String getAdapterName() {
		return "projectContactArchivesHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if ((hasIds("projectContactArchivesIds"))) {
			this.projectContactArchiveses = this.projectContactArchivesManager
					.loadAllProjectContactArchives(getIds("projectContactArchivesIds"));
		}
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return SUCCESS;
	}

	private String delete() {
		try {
			this.projectContactArchivesManager.deleteAllProjectContactArchives(projectContactArchiveses);
			;
			addActionMessage(getText("projectCon.delete.success"));
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage(getText("projectCon.delete.error"));
		}
		return ERROR;
	}

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("201"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public List<ProjectContactArchives> getProjectContactArchiveses() {
		return projectContactArchiveses;
	}

	public void setProjectContactArchiveses(List<ProjectContactArchives> projectContactArchiveses) {
		this.projectContactArchiveses = projectContactArchiveses;
	}

}
