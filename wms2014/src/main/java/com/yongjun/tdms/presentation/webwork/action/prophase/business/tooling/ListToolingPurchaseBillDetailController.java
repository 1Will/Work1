package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.business.ToolingPurchaseBillDetailView;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class ListToolingPurchaseBillDetailController extends JasperReportsController{
	   private final PurchaseBillManager purchaseBillManager;
	   private List<ToolingPurchaseBillDetailView> toolingPurchaseBillDetails;
	   public ListToolingPurchaseBillDetailController(PurchaseBillManager purchaseBillManager){
		   
		     this.purchaseBillManager=purchaseBillManager;
	   }
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String purchaseBillId = RequestUtils.getStringParameter(httpservletrequest, "purchaseBillid");
		if(purchaseBillId!=null&&!purchaseBillId.equals("")){
			toolingPurchaseBillDetails=purchaseBillManager.loadPurchaseBillDtlByBillId(Long.parseLong(purchaseBillId));
		}
		model.put("reportData", toolingPurchaseBillDetails );
		return model;
	}

}
