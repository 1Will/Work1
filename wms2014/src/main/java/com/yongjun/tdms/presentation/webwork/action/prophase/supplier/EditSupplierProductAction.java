package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.supplier.SupplierProduct;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.prophase.supplier.SupplierProductManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class EditSupplierProductAction extends PrepareAction{

	private static final long serialVersionUID = 6128196318048040272L;
	private final SupplierManager supplierManager;
	private final SupplierProductManager supplierProductManager;
	private Supplier supplier;
	private SupplierProduct product;
	private String toolingDevFlag;
	public EditSupplierProductAction(SupplierManager supplierManager, SupplierProductManager supplierProductManager) {
		this.supplierManager = supplierManager;
		this.supplierProductManager = supplierProductManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.supplier) {
			if (this.hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
			}
			else{
				this.supplier=new Supplier();
			}
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		if (null == this.product) {
			if (this.hasId("product.id")) {
				this.product = this.supplierProductManager.loadProduct(this.getId("product.id"));
				System.out.println("product name1114444 = " + product.getName());
			}
			else {
				this.product = new SupplierProduct();
			}
		}

	}

	public String save() {
		boolean isNew = this.product.isNew();
//		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
//			supplier.setToolingDevFlag(SysModel.DEVICE);
//		} else {
//			supplier.setToolingDevFlag(SysModel.TOOLING);
//		}
		this.product.setSupplier(supplier);
		System.out.println("product name111 = " + product.getName());
		this.supplierProductManager.storeProduct(product);
		System.out.println("product name = " + product.getName());
		
		if (isNew) {
			this.addActionMessage(this.getText("product.add.success", Arrays
					.asList(new Object[] { product.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("product.edit.success", Arrays
					.asList(new Object[] { product.getName() })));
			return SUCCESS;
		}
	}
	public SupplierProduct getProduct() {
		return product;
	}

	public void setProduct(SupplierProduct product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
