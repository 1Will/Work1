/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceManager;

/**
 * <p>Title: ListMaintenanceAction
 * <p>Description: 设备保养计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class ListMaintenanceAction extends ValueListAction{
	private static final long serialVersionUID = 7665553516224423600L;
	private final MaintenanceManager maintenanceManager;
	private final DepartmentManager departmentManager;
	
	private List<Maintenance> maintenances;
	private String planProcFlag;
	private boolean onlyValid;						//标示为有效
	private boolean onlyInvalid;					//标示为无效
	
	public ListMaintenanceAction(MaintenanceManager maintenanceManager,
			DepartmentManager departmentManager) {
		this.maintenanceManager = maintenanceManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 获取页面参数 <b>maintenanceIds</b>，如果得到，就根据ID获取工装标定List
	 */
	public void prepare() throws Exception {
		if(this.maintenances == null && this.hasIds("maintenanceIds")) {
			this.maintenances = this.maintenanceManager.loadAllMaintenances(
					this.getIds("maintenanceIds"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	/**
	 * 页面执行，如果选择了失效就调用 <b>disabled</b> 函数处理
	 * 
	 * @return	String SUCCESS
	 */
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return this.enabled();
		}
		return SUCCESS;
	}
	
	public String disabled() {
		try{
			this.maintenanceManager.disabledAllMaintenances(maintenances);
		}catch(Exception e){
			e.printStackTrace();
			this.addActionMessage(this.getText("maintenance.disabled.fariler"));
			return ERROR; 
		}
		
		this.addActionMessage(this.getText("maintenance.disabled.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.maintenanceManager.enabledAllMaintenances(maintenances);
		this.addActionMessage(this.getText("maintenances.enabled.success"));
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (onlyValid) {
			map.put("onlyValid", true);
		}
		if (onlyInvalid) {
			map.put("onlyInvalid", true);
		}
		if (!this.isSearch()) {
			map.put("department.id", this.getLoginUser().getDepartment().getId());
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "maintenancePlans";
		} else {
			return "maintenanceProcs";
		}
	}
	
	public List<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}

	public boolean isOnlyInvalid() {
		return onlyInvalid;
	}

	public void setOnlyInvalid(boolean onlyInvalid) {
		this.onlyInvalid = onlyInvalid;
	}

	public boolean isOnlyValid() {
		return onlyValid;
	}

	public void setOnlyValid(boolean onlyValid) {
		this.onlyValid = onlyValid;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
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
}
