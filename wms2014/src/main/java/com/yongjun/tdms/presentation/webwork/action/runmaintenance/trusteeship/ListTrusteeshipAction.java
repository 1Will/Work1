package com.yongjun.tdms.presentation.webwork.action.runmaintenance.trusteeship;

import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.trusteeship.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.trusteeship.*;

public class ListTrusteeshipAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final TrusteeshipManager trusteeshipManager;
	private final DepartmentManager departmentManager;
	
	private List<Trusteeship> trusteeships;//托管单集合
	private String toolingDevFlag;//标示[工装][设备]
	public ListTrusteeshipAction(TrusteeshipManager trusteeshipManager,
			DepartmentManager departmentManager) {
		this.trusteeshipManager = trusteeshipManager;
		this.departmentManager=departmentManager;
	}
	public void prepare() throws Exception {
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		
		if (this.trusteeships == null && this.hasIds("trusteeshipIds")) {
			this.trusteeships = this.trusteeshipManager.loadTrusteeships(this
					.getIds("trusteeshipIds"));

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
		this.trusteeshipManager.disabledAllTrusteeships(trusteeships);
		this.addActionMessage(this.getText("trusteeship.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.trusteeshipManager.enabledAllTrusteeships(trusteeships);
		this.addActionMessage(this.getText("trusteeship.enabled.success"));
		return SUCCESS;
	}

	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
    //获得托管单所有部门
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) { //如果用户不属于任何部门,置部门ID为-2
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
	@Override
	protected String getAdapterName() {
        
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "deviceTrusteeships";
		} else {
			return "toolingTrusteeships";
		}
	}
	
	/**
	 * 如果不是点击查询按钮，则默认是根据当前登陆人的部门来对日常检查结果进行筛选
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (!this.isSearch()) {
			map.put("department.id", this.getLoginUser().getDepartment().getId());
		}
		return map;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public List<Trusteeship> getTrusteeships() {
		return trusteeships;
	}

	public void setTrusteeships(List<Trusteeship> trusteeships) {
		this.trusteeships = trusteeships;
	}

}
