package com.yongjun.tdms.model.base.products.productsPerson;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class ProductsPerson extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	private PersonnelFiles developer;//开发者
	private Products products;//产品
	private CodeValue role;//角色
	private String explain;//说明
	
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	public PersonnelFiles getDeveloper() {
		return developer;
	}
	public void setDeveloper(PersonnelFiles developer) {
		this.developer = developer;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public CodeValue getRole() {
		return role;
	}
	public void setRole(CodeValue role) {
		this.role = role;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
