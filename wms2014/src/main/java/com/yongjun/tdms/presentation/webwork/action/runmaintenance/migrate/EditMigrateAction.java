package com.yongjun.tdms.presentation.webwork.action.runmaintenance.migrate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.util.Logger;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateManager;

public class EditMigrateAction extends PrepareAction {

	/**
	 * 
	 */
	private final MigrateManager migrateManager;

	private final DepartmentManager departmentManager;

	private final UserManager userManager;

	private final ProductionLineManager productionLineManager;
	

	private Migrate migrate;
	private Department olddepartment;
	private Department newdepartment;
	private ProductionLine oldproductionLine;
	private ProductionLine newproductionLine;
	private String toolingDevFlag; 
	
	private static final long serialVersionUID = 1L;

	public EditMigrateAction(DepartmentManager departmentManager,
			UserManager userManager, MigrateManager migrateManager,
			ProductionLineManager productionLineManager) {
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.migrateManager = migrateManager;
		this.productionLineManager = productionLineManager;
	}

	public void prepare() throws Exception {
		if (null==migrate) {
			if (this.hasId("migrate.id")){
				migrate = migrateManager.loadMigrate(this.getId("migrate.id"));
				olddepartment=migrate.getOldDepartment();
			    newdepartment=migrate.getNewDepartment();
			    oldproductionLine=migrate.getOldProductionLine();
			    newproductionLine=migrate.getNewProductionLine();
			
			} else {
				this.migrate = new Migrate();
			}
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		
		

	}

	public List getoldDepartments() {
		return departmentManager.loadAllDepartments();
	}

	public List getnewDepartments() {
		return departmentManager.loadAllDepartments();

	}

	public List getoldProductionLines() {
		return productionLineManager.loadAllProductionLines();
	}

	public List getnewProductionLines() {
		return productionLineManager.loadAllProductionLines();
	}

	public List getrequestDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
    public User getLoginUser(){
    	return this.userManager.getUser();
    }
	public String save() {
		boolean isNew = this.migrate.isNew();
		if (!StringUtils.isEmpty(request.getParameter("oldDepartment.id"))) {
			migrate.setOldDepartment(this.departmentManager.loadDepartment(this
					.getId("oldDepartment.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("newDepartment.id"))) {
			
			migrate.setNewDepartment(this.departmentManager.loadDepartment(this
					.getId("newDepartment.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("requester.id"))) {
			migrate.setRequester(this.userManager.loadUser(this
					.getId("requester.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("oldProductionLine.id"))) {
			migrate.setOldProductionLine(this.productionLineManager
					.loadProductionLine(this.getId("oldProductionLine.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("newProductionLine.id"))) {
			migrate.setNewProductionLine(this.productionLineManager
					.loadProductionLine(this.getId("newProductionLine.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("requestDepartment.id"))) {
			migrate.setRequestDepartment(this.departmentManager
					.loadDepartment(this.getId("requestDepartment.id")));
		}
	
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			migrate.setToolingDevFlag(SysModel.DEVICE);
		}
		else{
			migrate.setToolingDevFlag(SysModel.TOOLING);
		}
		
		

		this.migrateManager.storeMigrate(migrate);
		if (isNew) {
			this.addActionMessage(this.getText("migrate.add.success", Arrays
					.asList(new Object[] { migrate.getBillName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("migrate.edit.success", Arrays
					.asList(new Object[] { migrate.getBillName() })));
			return SUCCESS;
		}

	}

	public Migrate getMigrate() {
		return migrate;
	}

	public void setMigrate(Migrate migrate) {
		this.migrate = migrate;
	}
	public List getAllDepartments() {
		return departmentManager.createSelectDepartments(StringUtils.EMPTY);
	}
	public List getProductionLines() {
		return productionLineManager.createSelectProductionLines(StringUtils.EMPTY);
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public void setOlddepartment(Department olddepartment) {
		this.olddepartment = olddepartment;
	}

	public void setNewdepartment(Department newdepartment) {
		this.newdepartment = newdepartment;
	}

	
	public void setOldproductionLine(ProductionLine oldproductionLine) {
		this.oldproductionLine = oldproductionLine;
	}

	public void setNewproductionLine(ProductionLine newproductionLine) {
		this.newproductionLine = newproductionLine;
	}



}
