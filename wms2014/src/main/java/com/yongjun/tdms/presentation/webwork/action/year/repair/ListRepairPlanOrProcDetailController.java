package com.yongjun.tdms.presentation.webwork.action.year.repair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

public class ListRepairPlanOrProcDetailController extends JasperReportsController{

	private final RepairPlanAndProcManager yearRepairPlanAndProcManager;
	private RepairPlanAndProc repairPlanAndProc;
	private Set<RepairPlanAndProcDetail> details = new HashSet();
	
	public ListRepairPlanOrProcDetailController (RepairPlanAndProcManager yearRepairPlanAndProcManager){
		this.yearRepairPlanAndProcManager = yearRepairPlanAndProcManager;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		
		Map model = new HashMap();
		String flag = RequestUtils.getStringParameter(httpServletRequest,"flag");
		String repairPlanOrProcId = RequestUtils.getStringParameter(httpServletRequest,"repairPlanOrProcId");
		if (repairPlanOrProcId != null && !repairPlanOrProcId.equals("")) {
			this.repairPlanAndProc = this.yearRepairPlanAndProcManager.loadRepairPlanOrProc(Long.valueOf(repairPlanOrProcId));
		}
		
		if (flag.equals("PLAN")){
			  details = this.repairPlanAndProc.getPlanDetails();
			  }
		  else{
			  details = this.repairPlanAndProc.getProcDetails();
			  }
	model.put("reportData", details );
	return model;
	}
}
