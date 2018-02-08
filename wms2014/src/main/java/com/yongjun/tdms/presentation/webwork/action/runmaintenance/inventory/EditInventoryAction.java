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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;

import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryManager;

/**
 * @author qs
 * @version $Id: EditInventoryAction.java 11086 2008-02-26 01:12:56Z mwei $
 */
public class EditInventoryAction extends PrepareAction {
	private static final long serialVersionUID = 6360455965022459099L;

	private final InventoryManager inventoryManager;

	private final UserManager userManager;

	private DepartmentManager departmentManager;

	private InventoryBill inventoryBill ;
	private String toolingDevFlag;
	
	public EditInventoryAction(InventoryManager inventoryManager,DepartmentManager departmentManager,
			UserManager userManager){
		this.inventoryManager=inventoryManager;
		this.departmentManager=departmentManager;
		this.userManager=userManager;

	}

	public InventoryBill getInventoryBill() {
		return inventoryBill;
	}

	public void setInventoryBill(InventoryBill inventoryBill) {
		this.inventoryBill = inventoryBill;
	}

	public void prepare() throws Exception {
		if (null == inventoryBill) {
			if (this.hasId("inventoryBill.id")) {
				this.inventoryBill = this.inventoryManager.loadInventoryBill(this.getId("inventoryBill.id"));
			  }
		   else {
				  this.inventoryBill = new InventoryBill();
				}
	     }
		if(this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
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
		return this.departmentManager.loadAllDepartments();
	}
	
	public String save() {
		boolean isNew = this.inventoryBill.isNew();

		if (!StringUtils.isEmpty(request.getParameter("inventoryPeople.id"))) {
			System.out.println("djfhgdfl"
					+ request.getParameter("inventoryPeople.id"));
			inventoryBill.setInventoryPeople(this.userManager.loadUser(this
					.getId("inventoryPeople.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			System.out.println("djfhgdfl"
					+ request.getParameter("department.id"));
			inventoryBill.setDepartment(this.departmentManager
					.loadDepartment(this.getId("department.id")));
		}
		
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			inventoryBill.setToolingDevFlag(SysModel.DEVICE);
		}
		else{
			inventoryBill.setToolingDevFlag(SysModel.TOOLING);
		}
		this.inventoryManager.storeInventoryBill(inventoryBill);

		if (isNew) {
			this.addActionMessage(this.getText("inventoryBill.add.success",
					Arrays.asList(new Object[] { inventoryBill.getName() })));
			   System.out.println(" 清查单名称"+inventoryBill.getName());
			return NEW;
		} else {
			this.addActionMessage(this.getText("inventoryBill.edit.success",
					Arrays.asList(new Object[] { inventoryBill.getName() })));
			   
			return SUCCESS;
		}
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	/**
	 * 获取系统当前登录的人 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

}
