package com.yongjun.tdms.model.workReport.weekPlan;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workReport.week.Week;
import com.yongjun.tdms.model.workReport.weekly.Weekly;

public class WeekPlan extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProjectInfo projectInfo;
	private Weekly weekly;
	private String lastPlan;
	private String thisPlan;
	private String nextPlan;
	private Week week;
	private User user;
	private String isSaved;// 提交判断

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof Weekly)) {
			return false;
		}
		return false;
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

	public String getLastPlan() {
		return lastPlan;
	}

	public void setLastPlan(String lastPlan) {
		this.lastPlan = lastPlan;
	}

	public String getThisPlan() {
		return thisPlan;
	}

	public void setThisPlan(String thisPlan) {
		this.thisPlan = thisPlan;
	}

	public String getNextPlan() {
		return nextPlan;
	}

	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

}
