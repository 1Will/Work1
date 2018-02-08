package com.yongjun.tdms.presentation.webwork.action.workReport.weekPlan;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workReport.weekPlan.WeekPlanManager;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;

public class ListWeekPlanAction extends ValueListAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final WeekPlanManager weekPlanManager;
	private final ProjectInfoManager projectInfoManager;
	private final WeeklyManager weeklyManager;
	private List<WeekPlan> weekPlans;
	private ProjectInfo projectInfo;
	private Weekly weekly;

	public ListWeekPlanAction(WeekPlanManager weekPlanManager, ProjectInfoManager projectInfoManager,
			WeeklyManager weeklyManager) {
		this.weekPlanManager = weekPlanManager;
		this.projectInfoManager = projectInfoManager;
		this.weeklyManager = weeklyManager;
	}

	public void prepare() throws Exception {
		if (hasId("weekPlanIds")) {
			this.weekPlans = weekPlanManager.loadAllWeekPlan(getIds("weekPlanIds"));
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if (hasId("weekly.id")) {
			this.weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));
		}
		//被逼无奈加在这里
		if (isDelete()) {
			delete();
		}
	}

	@Override
	protected String getAdapterName() {
		return "weekPlan";
	}

	@SuppressWarnings("rawtypes")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		return "success";
	}

	private String delete() {
		try {
			this.weekPlanManager.deleteAllWeekPlan(weekPlans);
			addActionMessage(getText("weekPlan.delete.success"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("weekPlan.delete.failer"));
		}
		return "error";
	}

	public List<WeekPlan> getWeekPlans() {
		return weekPlans;
	}

	public void setWeekPlans(List<WeekPlan> weekPlans) {
		this.weekPlans = weekPlans;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public Weekly getWeekly() {
		return weekly;
	}

	public void setWeekly(Weekly weekly) {
		this.weekly = weekly;
	}

}
