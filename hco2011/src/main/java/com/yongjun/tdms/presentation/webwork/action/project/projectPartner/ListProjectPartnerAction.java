package com.yongjun.tdms.presentation.webwork.action.project.projectPartner;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectPartner.ProjectPartnerManager;

@SuppressWarnings("rawtypes")
public class ListProjectPartnerAction extends ValueListAction {

	private static final long serialVersionUID = 1L;
	private final ProjectPartnerManager projectPartnerManager;
	private final ProjectInfoManager projectInfoManager;
	private ProjectInfo projectInfo;
	private List<ProjectPartner> projectPartners;

	public ListProjectPartnerAction(ProjectPartnerManager projectPartnerManager,ProjectInfoManager projectInfoManager) {
		this.projectPartnerManager = projectPartnerManager;
		this.projectInfoManager = projectInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectPartnerIds")) {
			this.projectPartners = projectPartnerManager.loadAllProjectPartner(getIds("projectPartnerIds"));
		}
		if(hasId("projectInfo.id")){
			this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
	}

	protected String getAdapterName() {
		return "getProjectPartner";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	private String delete() {
		try {
			this.projectPartnerManager.deleteAllProjectPartner(projectPartners);
			addActionMessage(getText("projectPartner.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("projectPartner.delete.failer"));
		}
		return "error";
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public List<ProjectPartner> getProjectPartners() {
		return projectPartners;
	}

	public void setProjectPartners(List<ProjectPartner> projectPartners) {
		this.projectPartners = projectPartners;
	}

}
