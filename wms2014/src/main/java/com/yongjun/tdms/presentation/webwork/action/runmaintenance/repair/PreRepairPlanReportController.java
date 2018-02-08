package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.RequestUtils;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;

public class PreRepairPlanReportController extends JasperReportsController {
	private final PreRepairPlanManager preRepairPlanManager;
	private PreRepairPlan preRepairPlan;
	private Set<PreRepairPlanDetail> details = new HashSet();
	
	public PreRepairPlanReportController(PreRepairPlanManager preRepairPlanManager){
		this.preRepairPlanManager = preRepairPlanManager;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		
		Map model = new WeakHashMap();
		String flag = RequestUtils.getStringParameter(httpServletRequest,"flag");
		String preRepairPlanId = RequestUtils.getStringParameter(httpServletRequest,"preRepairPlanId");
		if (preRepairPlanId !=null && !preRepairPlanId.equals("")) {
			this.preRepairPlan = this.preRepairPlanManager.loadPreRepairPlan(Long.valueOf(preRepairPlanId));
		}
		
		if (flag.equals(SysModel.DEVICE.toString())){
		  preRepairPlan.setToolingDevFlag(SysModel.DEVICE);
		  if (flag.equals("PLAN")){
			  details = this.preRepairPlan.getPlanDetails();
			  }
		  else{
			  details = this.preRepairPlan.getProcDetails();
			  }
		  }
		else{
			preRepairPlan.setToolingDevFlag(SysModel.TOOLING);
			if (flag.equals("PLAN")){
				details = this.preRepairPlan.getPlanDetails();
				}
			else{
			    details = this.preRepairPlan.getProcDetails();
			    }
			}
		model.put("reportData",details);
		return model;
	}
} 
