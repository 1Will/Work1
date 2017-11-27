package com.yongjun.tdms.presentation.webwork.action.project.state;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.model.project.state.ProjectState;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
import com.yongjun.tdms.service.project.state.ProjectStateManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditProjectStateAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ProjectStateManager projectStateManager;
	private final ProjectInfoManager projectInfoManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;

	private ProjectInfo projectInfo;
	private ProjectState projectState;

	public EditProjectStateAction(ProjectStateManager projectStateManager, ProjectInfoManager projectInfoManager,
			UserManager userManager, CodeValueManager codeValueManager, ProjectInfoPlanManager projectInfoPlanManager) {
		this.projectStateManager = projectStateManager;
		this.projectInfoManager = projectInfoManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfo = projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if (hasId("projectState.id")) {
			this.projectState = projectStateManager.loadProjectState(getId("projectState.id"));
		} else {
			this.projectState = new ProjectState();
			this.projectState.setCreator(userManager.getUser().getName());
		}
	}

	public String save() {
		boolean isNew = this.projectState.isNew();
		this.projectState.setNewState(codeValueManager.loadCodeValue(getId("newStateid")));
		this.projectState.setBeforeState(codeValueManager.loadCodeValue(getId("beforeStateid")));
		this.projectState.setProjectInfo(this.projectInfo);
		try {
			this.projectStateManager.storeProjectState(this.projectState);
			//改变项目状态
			CodeValue codeValue = codeValueManager.loadCodeValue(getId("newStateid"));
			this.projectInfo.setState(codeValue);
			this.projectInfoManager.storeProjectInfo(projectInfo);
			
			List<ProjectInfoPlan> projectInfoPlans = projectInfoPlanManager.loadByKey("projectInfo.id",
					this.projectInfo.getId());
			ProjectInfoPlan projectInfoPlan = null;
			// 项目状态变为失败
			if (codeValue.getCode().equals("20105")) {
				if (projectInfoPlans != null && projectInfoPlans.size() > 0) {
					for (int i = 0; i < projectInfoPlans.size(); i++) {
						projectInfoPlan = projectInfoPlans.get(i);
						projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21105").get(0));
						projectInfoPlanManager.storeProjectInfoPlan(projectInfoPlan);
					}
				}
			}
			// 项目状态变为立项
			if (codeValue.getCode().equals("20101")) {
				if (projectInfoPlans != null && projectInfoPlans.size() > 0) {
					for (int i = 0; i < projectInfoPlans.size(); i++) {
						projectInfoPlan = projectInfoPlans.get(i);
						if(projectInfoPlan.getPercentt()==0){
							if(projectInfoPlan.getEndDate().after(new Date())){
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21101").get(0));
							}else {
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21103").get(0));
							}
						}
						if(projectInfoPlan.getPercentt()>0 && projectInfoPlan.getPercentt()<100){
							if(projectInfoPlan.getEndDate().after(new Date())){
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21102").get(0));
							}else {
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21103").get(0));
							}
						}
						projectInfoPlanManager.storeProjectInfoPlan(projectInfoPlan);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("projectState.add.error"));
			return ERROR;
		}
		if (isNew) {
			addActionMessage(getText("projectState.add.success"));
			return "new";
		} else {
			addActionMessage(getText("projectState.update.success"));
		}

		return "success";
	}

	public List<CodeValue> getChangeState() {

		List codes = getAllState();
		if ((null != codes) && (codes.size() > 3)) {
			codes.remove(1);
			codes.remove(1);
		}
		return codes;
	}

	public List<CodeValue> getAllState() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("201"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 3)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ProjectState getProjectState() {
		return projectState;
	}

	public void setProjectState(ProjectState projectState) {
		this.projectState = projectState;
	}

}
