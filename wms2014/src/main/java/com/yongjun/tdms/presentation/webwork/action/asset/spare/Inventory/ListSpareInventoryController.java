package com.yongjun.tdms.presentation.webwork.action.asset.spare.Inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;

public class ListSpareInventoryController extends JasperReportsController{

	private SpareInventoryManager spareInventoryManager;
	private SpareInventory spareInventory;
	private Set<SpareInventoryDetail> details = new HashSet();
	List result;
	HttpServletRequest request;
	
	public ListSpareInventoryController (SpareInventoryManager spareInventoryManager){
		this.spareInventoryManager = spareInventoryManager;
	}
	
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		// TODO Auto-generated method stub
		Map model = new HashMap();
		String inventoryId = RequestUtils.getStringParameter(httpservletrequest, "inventoryId");
		if(inventoryId!=null&&!inventoryId.equals("")){
			spareInventory=	spareInventoryManager.loadSpareInventory((Long.parseLong(inventoryId)));
			details=spareInventory.getInventoryDetails();
		}
		model.put("reportData", details );
		return model;
	}
}
