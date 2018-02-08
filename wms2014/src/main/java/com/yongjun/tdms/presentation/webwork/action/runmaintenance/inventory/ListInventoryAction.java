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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryManager;

/**
 * @author qs
 * @version $Id: ListInventoryAction.java 11358 2008-03-17 07:13:51Z zbzhang $
 */
@SuppressWarnings("serial")
public class ListInventoryAction extends ValueListAction {

	private final InventoryManager inventoryManager;

	private DepartmentManager departmentManager;

    private String toolingDevFlag;
    
	private List<InventoryBill> inventorys;

	public ListInventoryAction(InventoryManager inventoryManager,
			DepartmentManager departmentManager) {
		this.inventoryManager = inventoryManager;
		this.departmentManager = departmentManager;

	}

	public void prepare() throws Exception {
		if (this.inventorys == null && this.hasIds("inventoryIds")) {
			this.inventorys = this.inventoryManager.loadAllInventoryBill(this
					.getIds("inventoryIds"));
		}
		if(this.hasId("toolingDevFlag")){
			if (request.getParameter("toolingDevFlag").equals("DEVICE")) {
				this.toolingDevFlag = "DEVICE";
			} else {
				this.toolingDevFlag = "TOOLING";
			}
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
		this.inventoryManager.disabledAllInventoryBills(inventorys);
		this.addActionMessage(this.getText("inventoryBill.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.inventoryManager.enabledAllInventoryBills(inventorys);
		this.addActionMessage(this.getText("inventoryBill.enabled.success"));
		return SUCCESS;
	}

	private boolean isEnabled() {
		return this.hasKey("enabled");
	}

	@Override
	protected String getAdapterName() {
        if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
        	 return "deviceInventory";
        }else{
        	return "toolingInventory";	
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

	public List<InventoryBill> getInventorys() {
		return inventorys;
	}

	public void setInventorys(List<InventoryBill> inventorys) {
		this.inventorys = inventorys;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
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
