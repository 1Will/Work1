package com.yongjun.tdms.presentation.webwork.action.workReport.weekPlan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.project.ProjectInfoManager;
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
	private WeekPlan weekPlan;
	private Long weekPlanId;
	private String lastPlan;
	private String mes;

	public EditWeekPlanAction(WeekPlanManager weekPlanManager,WeeklyManager weeklyManager,ProjectInfoManager projectInfoManager) {
		this.weekPlanManager = weekPlanManager;
		this.weeklyManager = weeklyManager;
		this.projectInfoManager = projectInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("weekPlan.id")) {
			this.weekPlanId = getId("weekPlan.id");
			weekPlan = weekPlanManager.loadWeekPlan(weekPlanId);
		}else{
			this.weekPlan=new WeekPlan();
			lastPlan="上周计划暂时不获取";
		}
		if(request.getParameter("mes")!=null){
		addActionMessage(getText(request.getParameter("mes")));
		}
	}

	public String save() {
		boolean isNew = this.weekPlan.isNew();
		if (hasId("weekly.id")) {
			this.weekPlan.setWeekly(weeklyManager.loadWeekly(getId("weekly.id")));
		}
		if (hasId("projectInfo.id")) {
			this.weekPlan.setProjectInfo(projectInfoManager.loadProjectInfo(getId("projectInfo.id")));
		}
		if(hasId("weekPlan.thisPlan")){
			this.weekPlan.setThisPlan(request.getParameter("weekPlan.thisPlan"));
		}
		if(hasId("weekPlan.nextPlan")){
			this.weekPlan.setNextPlan(request.getParameter("weekPlan.nextPlan"));
		}
		this.weekPlanManager.storeWeekPlan(this.weekPlan);
		
		if(isNew){
			mes ="weekPlan.add.success";
			return "success";
		}else{
			mes ="weekPlan.update.success";
			return "success";
		}
	}

//	public String execute() throws Exception {
//		return super.execute();
//	}
	public String getLastPlanMethod(Long projecjId,Long weeklyId){
		String key[]={"projectInfo.id","weekly.rapporteur.id"};
		Weekly weekly = weeklyManager.loadWeekly(weeklyId);
		Long ids[] ={projecjId,weekly.getRapporteur().getId()};
		List<WeekPlan> wpList =null;
		try {
			wpList = weekPlanManager.loadByKeyArray(key, ids);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if(wpList==null){
			Long days =betweenDays(wpList.get(wpList.size()-1).getWeekly().getEndDate(),weekly.getStartDate());
			if(days>1 && days<=7){
				String plan = wpList.get(wpList.size()-1).getNextPlan();
				return plan;
			}else {
				return "";
			}
		}else {
			return "";
		}
	}
	
	/**
	 * 
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return 返回间隔天数,正負代表前後
	 */
	public static Long betweenDays(Date beginDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(beginDate);
		c2.setTime(endDate);
		//可以用正負代表前後
		return (Long)((c1.getTimeInMillis() - c2.getTimeInMillis())/(1000*60*60*24));
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
	

}
