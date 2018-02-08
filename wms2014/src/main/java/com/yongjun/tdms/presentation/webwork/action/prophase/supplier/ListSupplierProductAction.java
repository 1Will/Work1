package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.supplier.SupplierProduct;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.prophase.supplier.SupplierProductManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class ListSupplierProductAction extends ValueListAction{

	private static final long serialVersionUID = -3016607503214972787L;
	private final SupplierManager supplierManager;
	private final SupplierProductManager supplierProductManager;
	private Supplier supplier;
	private SupplierProduct supplierProduct;
	private List<SupplierProduct> products;
	private String toolingDevFlag;
	
	public ListSupplierProductAction(SupplierManager supplierManager, SupplierProductManager suppliersupplierProductManager) {
		this.supplierManager = supplierManager;
		this.supplierProductManager = suppliersupplierProductManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
			
		}
		
		if (null == this.products && this.hasIds("productIds")) {
			this.products = this.supplierProductManager.loadAllProducts(this.getIds("productIds"));
			
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
		
		this.supplierProductManager.deleteAllProducts(products);
		this.addActionMessage("");
	}
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public List<SupplierProduct> getProducts() {
		return products;
	}
	
	public void setProducts(List<SupplierProduct> products) {
		this.products = products;
	}

	@Override
	protected String getAdapterName() {
		return "deviceproSuppliers";
//		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
//			
//		} else {
//			return "toolingproSuppliers";
//		}
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public SupplierProduct getSupplierProduct() {
		return supplierProduct;
	}

	public void setSupplierProduct(SupplierProduct supplierProduct) {
		this.supplierProduct = supplierProduct;
	}

}
