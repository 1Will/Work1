package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.repair.BrockenRateManager;


public class ListBrockenRateChoosesAction extends  ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BrockenRateManager brockenRateManager;
	private final DepartmentManager departmentManager;
    private BrockenRate brockenRate;
	private List<BrockenRate> brockenRates;
	private String toolingDevFlag;                       //资产标识[工装]|[设备]
	public ListBrockenRateChoosesAction( BrockenRateManager brockenRateManager, 
			DepartmentManager departmentManager
			) {
		       this.brockenRateManager = brockenRateManager;
		       this.departmentManager = departmentManager;
	         }
	
	/**
	 * 根据页面传来的日常检查的ID集合,获取日常检查的集合对象
	 */
	public void prepare() {
		if (this.hasIds("brokenRateIds")) {
			this.brockenRates = this.brockenRateManager.loadAllBrockenRates(this.getIds("brokenRateIds"));
		     
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "devicebrokenRates";
		} else {
			return "toolingbrokenRates";
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

	public BrockenRate getBrockenRate() {
		return brockenRate;
	}

	public void setBrockenRate(BrockenRate brockenRate) {
		this.brockenRate = brockenRate;
	}

	public List<BrockenRate> getBrockenRates() {
		return brockenRates;
	}

	public void setBrockenRates(List<BrockenRate> brockenRates) {
		this.brockenRates = brockenRates;
	}
}
