package com.yongjun.tdms.presentation.webwork.action.workReport.weekPlan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workReport.week.Week;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workReport.week.WeekManager;
import com.yongjun.tdms.service.workReport.weekPlan.WeekPlanManager;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;

public class EditWeekPlanAction extends PrepareAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final WeekPlanManager weekPlanManager;
	private final WeeklyManager weeklyManager;
	private final ProjectInfoManager projectInfoManager;
	private final WeekManager weekManager;
	private final UserManager userManager;
	private WeekPlan weekPlan;
	private Week week;
	private Weekly weekly;
	private Long weekPlanId;
	private String lastPlan;
	private String mes;
	private User user;
	private ProjectInfo projectInfo;

	public EditWeekPlanAction(WeekPlanManager weekPlanManager, WeeklyManager weeklyManager,
			ProjectInfoManager projectInfoManager, WeekManager weekManager,UserManager userManager) {
		this.weekPlanManager = weekPlanManager;
		this.weeklyManager = weeklyManager;
		this.projectInfoManager = projectInfoManager;
		this.weekManager = weekManager;
		this.userManager = userManager;
	}

	public void prepare() throws Exception {
		if (hasId("weekPlan.id")) {
			this.weekPlanId = getId("weekPlan.id");
			weekPlan = weekPlanManager.loadWeekPlan(weekPlanId);
		} else {
			this.weekPlan = new WeekPlan();
		}
		if (request.getParameter("mes") != null) {
			addActionMessage(getText(request.getParameter("mes")));
		}
		
		if(hasId("week.id")){
			this.week = weekManager.loadWeek(getId("week.id"));
		}
		
		if (hasId("weekly.id")) {
			this.weekly = weeklyManager.loadWeekly(getId("weekly.id"));
			this.weekPlan.setWeekly(this.weekly);
			this.weekPlan.setWeek(this.weekly.getWeek());
		}
		if(hasId("projectInfo.id")){
			this.projectInfo = projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.weekPlan.setProjectInfo(this.projectInfo);
		}
		this.user =userManager.getUser();
	}

	public String save() {
		boolean isNew = this.weekPlan.isNew();
		
		if(isNew){
			this.weekPlan.setUser(user);
			if (hasId("projectInfo.id")) {
				this.weekPlan.setProjectInfo(projectInfo);
			}
			if (hasId("week.id")) {
				this.weekPlan.setWeek(this.week);
				List<Weekly> weeklies = null; 
				try {
					weeklies = this.weeklyManager.loadByKey("week.id", this.week.getId());
				} catch (DaoException e) {
					e.printStackTrace();
				}
				if(weeklies!=null){
					this.weekPlan.setWeekly(weeklies.get(0));
				}
			}
		}

		if (hasId("weekPlan.thisPlan")) {
			this.weekPlan.setThisPlan(request.getParameter("weekPlan.thisPlan"));
		}
		if (hasId("weekPlan.nextPlan")) {
			this.weekPlan.setNextPlan(request.getParameter("weekPlan.nextPlan"));
		}
		try {
			this.weekPlanManager.storeWeekPlan(this.weekPlan);
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				mes = "weekPlan.add.error";
				return "error";
			} else {
				mes = "weekPlan.update.error";
				return "error";
			}
		}

		if (isNew) {
			mes = "weekPlan.add.success";
			return "success";
		} else {
			mes = "weekPlan.update.success";
			return "success";
		}
	}
	
	public String getLastPlanMethod(Long projecjId, Long weekId,Long userId) {
		String key[] = { "projectInfo.id", "week.id","user.id" };
		Long thisids[] = { projecjId, weekId ,userId};
		Long ids[] = { projecjId, (weekId-1L) ,userId};
		List<WeekPlan> wpList = null;
		try {
			wpList = weekPlanManager.loadByKeyArray(key, thisids);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if(wpList.size()==0){
			try {
				wpList = weekPlanManager.loadByKeyArray(key, ids);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			if (wpList.size() == 0) {
				return "上周无工作计划";
			} else {
				return wpList.get(0).getNextPlan();
			}
		}else {
			return "";
		}
	}

	/**
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回间隔天数,正負代表前後
	 */
	public static Long betweenDays(Date beginDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(beginDate);
		c2.setTime(endDate);
		// 可以用正負代表前後
		return (Long) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60 * 60 * 24));
	}

	public WeekPlan getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(WeekPlan weekPlan) {
		this.weekPlan = weekPlan;
	}

	public Long getWeekPlanId() {
		return weekPlanId;
	}

	public void setWeekPlanId(Long weekPlanId) {
		this.weekPlanId = weekPlanId;
	}

	public String getLastPlan() {
		return lastPlan;
	}

	public void setLastPlan(String lastPlan) {
		this.lastPlan = lastPlan;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
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

	public Weekly getWeekly() {
		return weekly;
	}

	public void setWeekly(Weekly weekly) {
		this.weekly = weekly;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

}
