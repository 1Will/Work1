package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class ListSupplierBusinessHistoryAction extends PrepareAction{

	private static final long serialVersionUID = -8441455048225410172L;
	private final SupplierManager supplierManager;
	private Supplier supplier;
	
	public ListSupplierBusinessHistoryAction(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void prepare() throws Exception {
		if (null == supplier && this.hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
		}
	}
	
	public String execute() {
		return SUCCESS;
	}

}
