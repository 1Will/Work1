package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.List;


import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierCertification;

import com.yongjun.tdms.service.prophase.supplier.SupplierCerfiticationManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class ListSupplierCertificationAction extends ValueListAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SupplierCerfiticationManager supplierCerfiticationManager;
	
	private final SupplierManager supplierManager;
	
	private Supplier supplier;
	
	private String toolingDevFlag;
	
	private List<SupplierCertification> supplierCertificationucts;
	
	public ListSupplierCertificationAction(SupplierCerfiticationManager supplierCerfiticationManager,
			SupplierManager supplierManager){
		this.supplierCerfiticationManager=supplierCerfiticationManager;
		this.supplierManager=supplierManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
		}
		
		if (null == this.supplierCertificationucts && this.hasIds("cerficationIds")) {
			this.supplierCertificationucts = this.supplierCerfiticationManager.loadAllCertification(this.getIds("cerficationIds"));
			
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		
		}
		this.setFirst(false);
	}
	public String execute() {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public void delete() {
		this.supplierCerfiticationManager.deleteAllCertifications(supplierCertificationucts);
		
	}
	@Override
	protected String getAdapterName() {
	
		return "supplierCerfitications";
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<SupplierCertification> getSupplierCertificationucts() {
		return supplierCertificationucts;
	}

	public void setSupplierCertificationucts(
			List<SupplierCertification> supplierCertificationucts) {
		this.supplierCertificationucts = supplierCertificationucts;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
}
