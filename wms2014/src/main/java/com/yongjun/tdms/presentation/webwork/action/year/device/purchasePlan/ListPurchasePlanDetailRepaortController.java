package com.yongjun.tdms.presentation.webwork.action.year.device.purchasePlan;

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
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

public class ListPurchasePlanDetailRepaortController extends  JasperReportsController{
   private final PurchasePlanManager   purchasePlanManager;
   private PurchasePlan purchasePlan;
   private Set<PurchasePlanDetail> details = new HashSet();
   List result;
   HttpServletRequest request;
   public ListPurchasePlanDetailRepaortController(PurchasePlanManager   purchasePlanManager){
	   this.purchasePlanManager=purchasePlanManager;
   }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String purchasePlanid=RequestUtils.getStringParameter(httpservletrequest, "purchasePlanid");
		if(purchasePlanid!=null&&!purchasePlanid.equals("")){
			purchasePlan=	purchasePlanManager.loadPurchasePlan((Long.parseLong(purchasePlanid)));
			details=purchasePlan.getPurchasePlanDetail();
		}
		model.put("reportData", details );
		return model;
	
	}

}
