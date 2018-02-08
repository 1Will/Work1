package com.yongjun.tdms.presentation.webwork.action.runmaintenance.wash;

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
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

public class ListWashDetailRepaortController extends  JasperReportsController{
   private final WashManager   washManager;
   private Wash wash;
   private Set<WashDetail> details = new HashSet();
   List result;
   HttpServletRequest request;
   public ListWashDetailRepaortController(WashManager   washManager){
	   this.washManager=washManager;
   }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String washid=RequestUtils.getStringParameter(httpservletrequest, "washid");
		String flag =RequestUtils.getStringParameter(httpservletrequest,"flag");
		if(washid!=null&&!washid.equals("")){
			wash=	washManager.loadWash(Long.parseLong(washid));
			if(flag.equals("PLAN")){
				details=wash.getPlanDetails();
			}
			else{
				details=wash.getProcDetails();
			}
		}
		
		model.put("reportData",details );
		return model;
	
	}

}
