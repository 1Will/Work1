package com.yongjun.tdms.presentation.webwork.action.runmaintenance.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

public class ListMaintenanceRepaortController extends  JasperReportsController{
   private final MaintenanceManager   maintenanceManager;
   private Maintenance maintenance;
   private Set<MaintenanceDetail> maintenancePlanDetials;
   HttpServletRequest request;
   public ListMaintenanceRepaortController(MaintenanceManager   maintenaceManager){
	   this.maintenanceManager=maintenaceManager;
   }
  
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		
		Map model = new HashMap();
		String maintenanceid=RequestUtils.getStringParameter(httpservletrequest, "maintenanceid").trim();
		String flag = RequestUtils.getStringParameter(httpservletrequest,"flag").trim();
		if(maintenanceid!=null&&!maintenanceid.equals("")){
			maintenance=maintenanceManager.loadMaintenance(Long.valueOf(maintenanceid));
			if(flag.equals("PLAN")){
				maintenancePlanDetials=maintenance.getMaintenancePlanDetials();
			}else{ 
				maintenancePlanDetials=maintenance.getMaintenanceProcDetials();
			}
		}
		model.put("reportData", maintenancePlanDetials );
		return model;
	
	}

}
