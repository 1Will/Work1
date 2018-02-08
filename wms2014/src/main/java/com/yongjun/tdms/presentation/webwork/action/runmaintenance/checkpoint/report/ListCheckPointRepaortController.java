package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.report;

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
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

public class ListCheckPointRepaortController extends  JasperReportsController{
   private final CheckPointReportManager   checkPointManager;
   private CheckPointReport checkPointReport;
   private Set<CheckPointReportDetail> details = new HashSet();
   List result;
   HttpServletRequest request;
   public ListCheckPointRepaortController(CheckPointReportManager   checkPointManager){
	   this.checkPointManager=checkPointManager;
   }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String reportid=RequestUtils.getStringParameter(httpservletrequest, "reportid");
		if(reportid!=null&&!reportid.equals("")){
			checkPointReport=	checkPointManager.loadCheckPointReport(Long.parseLong(reportid));
			details=checkPointReport.getReportDetails();
		}
		model.put("reportData", details );
		return model;
	
	}

}
