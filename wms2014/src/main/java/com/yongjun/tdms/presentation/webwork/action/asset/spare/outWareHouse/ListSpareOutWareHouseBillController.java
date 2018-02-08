package com.yongjun.tdms.presentation.webwork.action.asset.spare.outWareHouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;

import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailView;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;

public class ListSpareOutWareHouseBillController extends JasperReportsController{
	
   private final SpareOutBillManager spareOutBillManager;
   private List<SpareOutBillDetailView> spareOutBillDtls;
   public ListSpareOutWareHouseBillController(SpareOutBillManager spareOutBillManager){
	   
	     this.spareOutBillManager=spareOutBillManager;
   }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String spareOutWareBillId = RequestUtils.getStringParameter(httpservletrequest, "spareOutWareBillId");
		if(spareOutWareBillId!=null&&!spareOutWareBillId.equals("")){
			spareOutBillDtls=spareOutBillManager.loadSpareOutBillDetail(Long.parseLong(spareOutWareBillId));
		}
		model.put("reportData", spareOutBillDtls );
		return model;
		
	}

}
