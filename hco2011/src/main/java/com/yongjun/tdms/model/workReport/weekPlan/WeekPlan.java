package com.yongjun.tdms.model.workReport.weekPlan;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workReport.weekly.Weekly;

public class WeekPlan extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private ProjectInfo projectInfo;
	private Weekly weekly;
	private String lastPlan;
	private String thisPlan;
	private String nextPlan;

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

}
