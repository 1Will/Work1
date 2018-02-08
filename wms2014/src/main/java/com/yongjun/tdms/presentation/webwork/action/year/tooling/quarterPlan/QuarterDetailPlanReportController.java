package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.RequestUtils;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.year.tooling.QuarterPlanDetailView;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
public class QuarterDetailPlanReportController extends JasperReportsController{
	 private QuarterPlanManager quarterPlanManager;
	 private List<QuarterPlanDetailView> details = new ArrayList();
	public QuarterDetailPlanReportController(QuarterPlanManager quarterPlanManager){
		this.quarterPlanManager=quarterPlanManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String quarterPlanId = RequestUtils.getStringParameter(httpservletrequest, "quarterPlanid");
	
		if(quarterPlanId != null&&!quarterPlanId.equals("")){
			this.details = this.quarterPlanManager.loadAllQuarterPlanDetailByQuarterPlanId(Long.parseLong(quarterPlanId));
		}
		model.put("reportData", details);
		return model;
	}

}
