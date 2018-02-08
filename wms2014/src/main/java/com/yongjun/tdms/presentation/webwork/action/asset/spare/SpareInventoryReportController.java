package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;

public class SpareInventoryReportController extends JasperReportsController {
	private final SpareInventoryManager spareInventoryManager;

	private SpareInventory spareInventory;

	public SpareInventoryReportController(
			SpareInventoryManager spareInventoryManager) {
		this.spareInventoryManager = spareInventoryManager;
	}

	public SpareInventory getSpareInventory() {
		return spareInventory;
	}

	public void setSpareInventory(SpareInventory spareInventory) {
		this.spareInventory = spareInventory;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest request)
			throws Exception {
		Map model = new HashMap();
		String spareInventoryId = RequestUtils.getStringParameter(request,"spareInventoryId");
		if (spareInventoryId != null && !spareInventoryId.equals("")) {
			this.spareInventory = this.spareInventoryManager.loadSpareInventory(Long.valueOf(spareInventoryId));
		}
		model.put("reportData", new SpareInventory[] { spareInventory });
		return model;
	}
}
