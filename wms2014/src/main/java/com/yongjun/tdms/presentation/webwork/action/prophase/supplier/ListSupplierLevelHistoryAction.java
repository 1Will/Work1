package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;


import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class ListSupplierLevelHistoryAction extends ValueListAction{
	
	private static final long serialVersionUID = 1L;
	private final SupplierManager supplierManager;
	
	private Supplier supplier;



	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ListSupplierLevelHistoryAction(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
		}
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {
		return null;
	}
	

	public Supplier getSupplier() {
		return supplier;
	}
}
