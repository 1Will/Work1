package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.ManagementLevel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.supplier.EvaluateDetailFlag;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class EditSupplierEvaluateAction extends PrepareAction{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final SupplierEavluateManager supplierEavluateManager;
	private String toolingDevFlag;
	private Supplier supplier;
	private Department deparment;
	private SupplierEvaluate supplierEvaluate;
	
	public EditSupplierEvaluateAction(DepartmentManager departmentManager,
			UserManager userManager,SupplierEavluateManager supplierEavluateManager,
			CodeValueManager codeValueManager,SupplierManager supplierManager){
		this.departmentManager=departmentManager;
		this.userManager=userManager;
		this.supplierEavluateManager=supplierEavluateManager;
		this.codeValueManager=codeValueManager;
		this.supplierManager=supplierManager;
	}
	public void prepare() throws Exception {
		if (null == this.supplier) {
			if (this.hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(this.getId("supplier.id"));
			}
		}
		
		if (null == supplierEvaluate) {
			if (this.hasId("supplierEvaluate.id")) {
				this.supplierEvaluate = this.supplierEavluateManager.loadSupplierEvaluate(this.getId("supplierEvaluate.id"));
			  }
		   else {
				  this.supplierEvaluate = new SupplierEvaluate();
				}
	     }
	
		if(this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		
	}

	public String save() {
		
		boolean isNew = this.supplierEvaluate.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("evaluateUser.id"))) {
			supplierEvaluate.setEvaluateUser(this.userManager.loadUser(this
					.getId("evaluateUser.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			
			supplierEvaluate.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("supplierEvaluateYear"))) {
			supplierEvaluate.setUnitYear(DateUtil.toDate(request.getParameter("supplierEvaluateYear"),"yyyy"));
		}
		if (!StringUtils.isEmpty(request.getParameter("evaluateLevelId"))) {
			supplierEvaluate.setLevel(this.codeValueManager.loadCodeValue(this.getId("evaluateLevelId")));
		}
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			supplierEvaluate.setToolingDevFlag(SysModel.DEVICE);
		}
		else{
			supplierEvaluate.setToolingDevFlag(SysModel.TOOLING);
		}
		supplierEvaluate.setSupplier(supplier);
		LabelValue [] supplierEvaluateDetailFlag = this.wrapEnum(EvaluateDetailFlag.class);
		
		try {
			this.supplierEavluateManager.storeSupplierEvaluate(supplierEvaluate, supplierEvaluateDetailFlag);	
		}catch (Exception e) {
			this.addActionMessage(this.getText("supplierEvaluateBill.add.error", Arrays
					.asList(new Object[] { supplier.getName(), DateUtil.getYear(supplierEvaluate.getUnitYear())})));
			return SUCCESS;
		}
		

		if (isNew) {
			this.addActionMessage(this.getText("supplierEvaluateBill.add.success", Arrays
					.asList(new Object[] { supplier.getName()})));
				
			
			return NEW;
		} else {
			this.addActionMessage(this.getText("supplierEvaluateBill.edit.success", Arrays
					.asList(new Object[] { supplier.getName()})));
				
			   
			return SUCCESS;
		}
	}

	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
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
		return departmentManager.loadAllDepartments();
	}
	//获得等级
	
	 public List getSupplierLevel(){
		   return codeValueManager.LoadAllValuesByCode(CodeConstants.SUPPLIER_LEVEL);
	   }
	/**
	 * 获取系统当前登录的人 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	public SupplierEvaluate getSupplierEvaluate() {
		return supplierEvaluate;
	}

	public void setSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		this.supplierEvaluate = supplierEvaluate;
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
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Department getDeparment() {
		return deparment;
	}
	public void setDeparment(Department deparment) {
		this.deparment = deparment;
	}
}
