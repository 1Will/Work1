package com.yongjun.tdms.model.project.projectInfoProduct;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectInfoProduct extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ProjectInfo projectInfo;// 项目
	private Products products;// 产品

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfoProduct)) {
			return false;
		}

		ProjectInfoProduct contact = (ProjectInfoProduct) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

}
