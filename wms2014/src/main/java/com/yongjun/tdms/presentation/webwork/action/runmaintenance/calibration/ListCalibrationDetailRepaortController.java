package com.yongjun.tdms.presentation.webwork.action.runmaintenance.calibration;

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
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationManager;

public class ListCalibrationDetailRepaortController extends  JasperReportsController{
   private final CalibrationManager   calibrationManager;
   private Calibration calibration;
   private Set<CalibrationDetail> details = new HashSet();
   List result;
   HttpServletRequest request;
   public ListCalibrationDetailRepaortController(CalibrationManager   calibrationManager){
	   this.calibrationManager=calibrationManager;
   }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String flag = RequestUtils.getStringParameter(httpservletrequest,"flag");
		String calibrationid=RequestUtils.getStringParameter(httpservletrequest, "calibrationid");
		if(calibrationid!=null&&!calibrationid.equals("")){
			calibration = calibrationManager.loadCalibration(Long.parseLong(calibrationid));
			
		}
		if (flag.equals("PLAN")){
			details=calibration.getCalibrationPlanDetails();
		}else{
			details=calibration.getCalibrationProcDetails();
		}
		
		model.put("reportData", details );
		return model;
	
	}

}
