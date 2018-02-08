package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

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
import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBillDtl;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

public class ListDeviceDiscardRepaortController extends  JasperReportsController{
   private final DiscardBillManager   discardBillManager;
   private DiscardBill discardbill;
   private Set<DiscardBillDtl> details = new HashSet();
   List result;
   HttpServletRequest request;
   public ListDeviceDiscardRepaortController(DiscardBillManager   discardBillManager){
	   this.discardBillManager=discardBillManager;
   }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String discardBillId=RequestUtils.getStringParameter(httpservletrequest, "discardBillid");
		if(discardBillId!=null&&!discardBillId.equals("")){
			discardbill = discardBillManager.loadDiscardBill(Long.parseLong(discardBillId));
			details=discardbill.getDiscardBillDtl();
		}
		model.put("reportData",details);
		return model;
	
	}

}
