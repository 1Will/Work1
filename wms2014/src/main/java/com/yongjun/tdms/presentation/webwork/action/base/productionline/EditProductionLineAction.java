package com.yongjun.tdms.presentation.webwork.action.base.productionline;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.exception.ErroSaveException;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;

public class EditProductionLineAction extends PrepareAction {
	private static final long serialVersionUID = -3918572358765245231L;

	private final ProductionLineManager productionLineManager;

	private final DepartmentManager departmentManager;
	
	private final UserManager userManager;

	private Department department;

	private ProductionLine productionLine;

	public EditProductionLineAction(
			UserManager userManager,
			ProductionLineManager productionLineManager,
			DepartmentManager departmentManager) {
		this.userManager = userManager;
		this.productionLineManager = productionLineManager;
		this.departmentManager = departmentManager;
	}

	public void prepare() throws Exception {
		if (null == this.productionLine) {
			if (this.hasId("productionLine.id")) {
				this.productionLine = this.productionLineManager
						.loadProductionLine(this.getId("productionLine.id"));
			} else {
				this.productionLine = new ProductionLine();
			}
		}
	}

	public String save() {
		boolean isNew = this.productionLine.isNew();
		if(!StringUtils.isEmpty(request.getParameter("department.id"))){
			this.department = this.departmentManager.loadDepartment(this.getId("department.id"));
			productionLine.setDepartment(department);
		}
		
		if (isNew) {
			productionLine.setOrganization(userManager.getOrganization());
		}
		
		try{
			try{
				this.productionLineManager.storeProductionLine(productionLine);
			}catch(Exception e){
				throw new ErroSaveException();
			}
		}catch(ErroSaveException e){
			e.printStackTrace();
			this.addActionMessage(this.getText("productionLine.code.exists",
					Arrays.asList(new Object[]{productionLine.getCode()})));
			return ERROR;
		}
			
		if (isNew) {
			this.addActionMessage(this.getText("productionLine.add.success",
					Arrays.asList(new Object[] { productionLine.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("productionLine.edit.success",
					Arrays.asList(new Object[] { productionLine.getName() })));
			return SUCCESS;
		}
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public ProductionLine getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(ProductionLine productionLine) {
		this.productionLine = productionLine;
	}

	public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}

}
