package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierLevelHistoryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class ListSupplierEvaluateLevelHostroyAction extends ValueListAction{
	
	private final SupplierEavluateManager supplierEavluateManager;
	private String toolingDevFlag;
	private SupplierLevelHistory supplierLevelHistory;
	private Supplier supplier;
	 private final SupplierLevelHistoryManager supplierLevelHistoryManager;
	private final SupplierManager supplierManager;
	private SupplierEvaluate supplierEvaluate;
	private List<SupplierEvaluate>  evaluates;
	private static final long serialVersionUID = 1L;
	public ListSupplierEvaluateLevelHostroyAction(SupplierEavluateManager supplierEavluateManager,
			SupplierManager supplierManager,SupplierLevelHistoryManager supplierLevelHistoryManager){
		this.supplierEavluateManager=supplierEavluateManager;
		this.supplierManager=supplierManager;
		this.supplierLevelHistoryManager=supplierLevelHistoryManager;
	}
	
	public void prepare() throws Exception {
		if (this.evaluates == null && this.hasIds("supplierLevelhostoryIds")) {
			this.evaluates = this.supplierEavluateManager.loadSupplierEvaluates(this
					.getIds("supplierLevelhostoryIds"));
		}
		if (this.hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
		}
		if (this.hasId("supplierLevelHistory.id")) {
			this.supplierLevelHistory = this.supplierLevelHistoryManager.loadSupplierLevelHistory(this.getId("supplierLevelHistory.id"));
		}
		if(this.hasId("toolingDevFlag")){
			if (request.getParameter("toolingDevFlag").equals("DEVICE")) {
				this.toolingDevFlag = "DEVICE";
			} else {
				this.toolingDevFlag = "TOOLING";
			}
		}
		this.setFirst(false);
	}
	@Override
	protected String getAdapterName() {
		 
	 return "supplierEvaluateLevelHistory";
		
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public SupplierEvaluate getSupplierEvaluate() {
		return supplierEvaluate;
	}

	public void setSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		this.supplierEvaluate = supplierEvaluate;
	}

}
