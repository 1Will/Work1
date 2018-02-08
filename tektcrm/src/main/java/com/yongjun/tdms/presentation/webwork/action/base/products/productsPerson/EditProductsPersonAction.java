package com.yongjun.tdms.presentation.webwork.action.base.products.productsPerson;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.products.productsPerson.ProductsPerson;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.products.productsPerson.ProductsPersonManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditProductsPersonAction extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final ProductsPersonManager productsPersonManager;
	private final CodeValueManager codeValueManager;
	private final ProductsManager productsManager;
	private final PersonnelFilesManager personnelFilesManager;
	private ProductsPerson productsPerson;
	private Products products;

	public EditProductsPersonAction(ProductsPersonManager productsPersonManager, CodeValueManager codeValueManager,
			ProductsManager productsManager, PersonnelFilesManager personnelFilesManager) {
		this.productsPersonManager = productsPersonManager;
		this.codeValueManager = codeValueManager;
		this.productsManager = productsManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public void prepare() throws Exception {
		if (hasId("products.id")) {
			this.products = productsManager.loadProducts(getId("products.id"));
		}
		if (hasId("productsPerson.id")) {
			this.productsPerson = productsPersonManager.loadProductsPerson(getId("productsPerson.id"));
		} else {
			this.productsPerson = new ProductsPerson();
		}
	}

	public String save() {
		boolean isNew = this.productsPerson.isNew();
		try {
			this.productsPerson.setProducts(this.products);
			this.productsPerson.setDeveloper(personnelFilesManager.loadPersonnel(getId("developer.id")));
			this.productsPerson.setRole(codeValueManager.loadCodeValue(getId("role.id")));
			this.productsPerson.setExplain(request.getParameter("productsPerson.explain"));
			this.productsPersonManager.storeProductsPerson(this.productsPerson);
			if (isNew)
				addActionMessage(getText("productsPerson.add.success"));
			else {
				addActionMessage(getText("productsPerson.edit.success"));
			}
			return SUCCESS;
		} catch (Exception e) {
			if (isNew)
				addActionMessage(getText("productsPerson.add.error"));
			else {
				addActionMessage(getText("productsPerson.edit.error"));
			}
			return ERROR;
		}
	}

	public List<CodeValue> getAllRole() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "21000");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public ProductsPerson getProductsPerson() {
		return productsPerson;
	}

	public void setProductsPerson(ProductsPerson productsPerson) {
		this.productsPerson = productsPerson;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

}
