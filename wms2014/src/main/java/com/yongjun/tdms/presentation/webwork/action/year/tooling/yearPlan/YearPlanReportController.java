package com.yongjun.tdms.presentation.webwork.action.year.tooling.yearPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;

import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

public class YearPlanReportController extends JasperReportsController{
    private YearPlanManager yearPlanManager;
    private List<YearPlanDetailView> details = new ArrayList();
   
	HttpServletRequest request;
    public YearPlanReportController(YearPlanManager yearPlanManager){
    	this.yearPlanManager=yearPlanManager;
    }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String yearPlanId = RequestUtils.getStringParameter(httpservletrequest, "yearPlanid");
		if(yearPlanId != null && !yearPlanId.equals("")){
			this.details = this.yearPlanManager.loadAllYearPlanDetailByYearPlanId(Long.parseLong(yearPlanId));
		}
		model.put("reportData", details);
		return model;
	
	}

}
