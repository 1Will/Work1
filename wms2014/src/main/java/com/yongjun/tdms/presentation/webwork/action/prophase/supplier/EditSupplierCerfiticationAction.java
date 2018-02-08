package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierCertification;
import com.yongjun.tdms.service.prophase.supplier.SupplierCerfiticationManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class EditSupplierCerfiticationAction extends PrepareAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final SupplierCerfiticationManager supplierCerfiticationManager;
	
	private final SupplierManager supplierManager;
	
	private Supplier supplier;
	
	private SupplierCertification cerfitication;
	
	public EditSupplierCerfiticationAction(SupplierCerfiticationManager supplierCerfiticationManager,
			SupplierManager supplierManager){
		this.supplierCerfiticationManager=supplierCerfiticationManager;
		this.supplierManager=supplierManager;
	}
	public void prepare() throws Exception {
		if (null == this.supplier) {
			if (this.hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
			}
		}
		
		if (null == this.cerfitication) {
			if (this.hasId("cerfitication.id")) {
				this.cerfitication = this.supplierCerfiticationManager.loadCertification(this.getId("cerfitication.id"));
				
			}
			else {
				this.cerfitication = new SupplierCertification();
			}
		}
		
		
	}
	public String save() {
		boolean isNew = this.cerfitication.isNew();
//		this.product.setSupplier(supplier);
//		System.out.println("product name111 = " + product.getName());
//		this.supplierProductManager.storeProduct(product);
//		System.out.println("product name = " + product.getName());
		cerfitication.setSupplier(supplier);
		supplierCerfiticationManager.storeCertification(cerfitication);
		if (isNew) {
			this.addActionMessage(this.getText("certification.add.success", Arrays
					.asList(new Object[] { cerfitication.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("certification.edit.success", Arrays
					.asList(new Object[] { cerfitication.getName() })));
			return SUCCESS;
		}
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public SupplierCertification getCerfitication() {
		return cerfitication;
	}
	public void setCerfitication(SupplierCertification cerfitication) {
		this.cerfitication = cerfitication;
	}

}
