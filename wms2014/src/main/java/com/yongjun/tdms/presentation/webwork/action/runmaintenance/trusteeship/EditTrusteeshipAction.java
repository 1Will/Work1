package com.yongjun.tdms.presentation.webwork.action.runmaintenance.trusteeship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.service.runmaintenance.trusteeship.TrusteeshipManager;

public class EditTrusteeshipAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final TrusteeshipManager trusteeshipManager;
	private final DepartmentManager departmentManager;
	private final SupplierManager supplierManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;

	private Supplier supplier;
	private Trusteeship trusteeship;
	private Department trusteeshipDepartment;
	private Department trusteeshipRequestDepartment;
	private String toolingDevFlag;
	public EditTrusteeshipAction(TrusteeshipManager trusteeshipManager,
			DepartmentManager departmentManager,
			SupplierManager supplierManager, CodeValueManager codeValueManager,
			UserManager userManager ) {
		this.trusteeshipManager = trusteeshipManager;
		this.departmentManager = departmentManager;
		this.supplierManager = supplierManager;
		this.codeValueManager = codeValueManager;
		this.userManager=userManager;

	}
	public void prepare() throws Exception {
		if (null == trusteeship) {
			if (this.hasId("trusteeship.id")) {
				this.trusteeship = trusteeshipManager.loadTrusteeship(this.getId("trusteeship.id"));
			} else {
				this.trusteeship = new Trusteeship();
			}
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}

	public String save() {

		if (!StringUtils.isEmpty(request
				.getParameter("trusteeshipDepartment.id"))) {
			trusteeship.setTrusteeshipDepartment(this.departmentManager
					.loadDepartment(this.getId("trusteeshipDepartment.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {
			trusteeship.setSupplier(this.supplierManager.loadSupplier(this
					.getId("supplier.id")));
		}
		if (!StringUtils.isEmpty(request
				.getParameter("trusteeshipRequestDepartment.id"))) {
			trusteeship.setTrusteeshipRequestDepartment(this.departmentManager
					.loadDepartment(this
							.getId("trusteeshipRequestDepartment.id")));
		}

		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			trusteeship.setToolingDevFlag(SysModel.DEVICE);
		} else {
			trusteeship.setToolingDevFlag(SysModel.TOOLING);
		}
		boolean isNew = this.trusteeship.isNew();
		this.trusteeshipManager.storeTrusteeship(trusteeship);
		if (isNew) {
			this.addActionMessage(this.getText("trusteeship.add.success",
					Arrays.asList(new Object[] { trusteeship.getBillName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("trusteeship.edit.success",
					Arrays.asList(new Object[] { trusteeship.getBillName() })));
			return SUCCESS;
		}
	}

	// 获取托管设备的原部门
	public List getTrusteeshipDepartments() {
	
		return this.departmentManager.loadAllDepartments();
	}

	// 托管设备的申请部门
	public List getTrusteeshipRequestDepartments() {
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
  
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	// 获取托管设备的外协厂
	public List<Supplier> getExternalHelps() {

		return this.supplierManager
				.loadAllExternalHelpSupplier(this.codeValueManager
						.loadCodeTypeByRealCode(
								CodeConstants.SUPPLIER_EXTERNAL_HELP_TYPE)
						.getId());
	}

	public Trusteeship getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(Trusteeship trusteeship) {
		this.trusteeship = trusteeship;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setTrusteeshipDepartment(Department trusteeshipDepartment) {
		this.trusteeshipDepartment = trusteeshipDepartment;
	}

	public void setTrusteeshipRequestDepartment(
			Department trusteeshipRequestDepartment) {
		this.trusteeshipRequestDepartment = trusteeshipRequestDepartment;
	}

	public Department getTrusteeshipDepartment() {
		return trusteeshipDepartment;
	}

	public Department getTrusteeshipRequestDepartment() {
		return trusteeshipRequestDepartment;
	}

}
