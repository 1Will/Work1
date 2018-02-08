package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;

import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;

public class ListSupplierEvaluateAction extends ValueListAction{

	/**
	 * 
	 */
	private final SupplierEavluateManager supplierEavluateManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private String toolingDevFlag;
	private List<SupplierEvaluate>  evaluates;
	private static final long serialVersionUID = 1L;
	public ListSupplierEvaluateAction(SupplierEavluateManager supplierEavluateManager
		,DepartmentManager departmentManager,CodeValueManager codeValueManager){
		this.supplierEavluateManager=supplierEavluateManager;
		this.departmentManager=departmentManager;
		this.codeValueManager=codeValueManager;
		
		
	}
	
	public void prepare() throws Exception {
		if (this.evaluates == null && this.hasIds("supplierEvaluateIds")) {
			this.evaluates = this.supplierEavluateManager.loadSupplierEvaluates(this
					.getIds("supplierEvaluateIds"));
		}
		if(this.hasId("toolingDevFlag")){
			if (request.getParameter("toolingDevFlag").equals("DEVICE")) {
				this.toolingDevFlag = "DEVICE";
			} else {
				this.toolingDevFlag = "TOOLING";
			}
		}
	}
    
	@Override
	protected String getAdapterName() {
		 if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
        	 return "devicesupplierevaluate";
        }else{
        	return "toolingsupplierevaluate";	
        }
	}

	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		return SUCCESS;
	}

	public String disabled() {
		this.supplierEavluateManager.disabledAllEvaluates(evaluates);
		this.addActionMessage(this.getText("evaluates.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.supplierEavluateManager.enabledAllEvaluates(evaluates);
		this.addActionMessage(this.getText("evaluates.enabled.success"));
		return SUCCESS;
	}

	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
//	获得验收单所有部门
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }

	public List getSupplierLevel() {
		return codeValueManager.createSelectCodeValues(this
				.getText("supplier.all"), CodeConstants.SUPPLIER_LEVEL);
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}


	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}


	public List<SupplierEvaluate> getEvaluates() {
		return evaluates;
	}


	public void setEvaluates(List<SupplierEvaluate> evaluates) {
		this.evaluates = evaluates;
	}

}
