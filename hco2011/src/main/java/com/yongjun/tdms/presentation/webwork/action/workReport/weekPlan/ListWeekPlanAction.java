package com.yongjun.tdms.presentation.webwork.action.workReport.weekPlan;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.workReport.weekPlan.WeekPlanManager;

public class ListWeekPlanAction extends ValueListAction {
	private final WeekPlanManager weekPlanManager;

	public ListWeekPlanAction(WeekPlanManager weekPlanManager) {
		this.weekPlanManager = weekPlanManager;
	}

	@Override
	protected String getAdapterName() {
		return "weekPlan";
	}
	
	
	protected Map getRequestParameterMap(){
		Map map = super.getRequestParameterMap();
		return map;
	}

}
