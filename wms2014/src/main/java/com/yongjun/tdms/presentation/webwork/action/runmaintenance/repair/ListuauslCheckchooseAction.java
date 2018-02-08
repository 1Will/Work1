package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;

public class ListuauslCheckchooseAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CheckManager checkManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	private List<Check> checks;
	private String toolingDevFlag;                       //资产标识[工装]|[设备]
	
	public ListuauslCheckchooseAction(CheckManager checkManager, 
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.checkManager = checkManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}

	/**
	 * 根据页面传来的日常检查的ID集合,获取日常检查的集合对象
	 */
	public void prepare() {
		if (this.hasIds("checkIds")) {
			this.checks = this.checkManager.loadAllChecks(this.getIds("checkIds"));
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 * 如果点击删除按钮,执行删除
	 */

	
	public String delete() {
		this.checkManager.deleteAllChecks(checks);
		this.addActionMessage(this.getText("delete.checks.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "deviceunChecks";
		} else {
			return "toolingunChecks";
		}
	}
	
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
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
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
