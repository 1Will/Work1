package com.yongjun.tdms.presentation.webwork.action.base.products.productsPerson;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.products.productsPerson.ProductsPerson;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.products.productsPerson.ProductsPersonManager;

@SuppressWarnings("rawtypes")
public class ListProductsPersonAction extends ValueListAction {
	private static final long serialVersionUID = 5616960016127750986L;
	private final ProductsPersonManager productsPersonManager;
	private final ProductsManager productsManager;
	private List<ProductsPerson> productsPersons;
	private Products products;

	public ListProductsPersonAction(ProductsPersonManager productsPersonManager, ProductsManager productsManager) {
		this.productsPersonManager = productsPersonManager;
		this.productsManager = productsManager;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public void prepare() throws Exception {
		if ((this.productsPersons == null) && (hasIds("productsPersonIds"))) {
			this.productsPersons = this.productsPersonManager.loadAllProductsPerson(getIds("productsPersonIds"));
		}
		if (hasId("products.id")) {
			this.products = this.productsManager.loadProducts(getId("products.id"));
		}
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	protected String getAdapterName() {
		return "getProductsPerson";
	}

	private String delete() {
		try {
			this.productsPersonManager.deleteAllProductsPerson(this.productsPersons);
			addActionMessage(getText("productsPerson.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("productsPerson.delete.error"));
		}
		return ERROR;
	}

	public List<ProductsPerson> getProductsPersons() {
		return productsPersons;
	}

	public void setProductsPersons(List<ProductsPerson> productsPersons) {
		this.productsPersons = productsPersons;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

}
