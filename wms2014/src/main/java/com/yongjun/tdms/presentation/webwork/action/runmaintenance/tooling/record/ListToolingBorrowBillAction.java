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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingBorrowBillManager;

/**
 * @author qs
 * @version $Id: ListToolingBorrowBillAction.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class ListToolingBorrowBillAction extends ValueListAction {
	private static final long serialVersionUID = 7389958708788706238L;

	private final ToolingBorrowBillManager toolingBorrowBillManager;

	private final DepartmentManager departmentManager;

	private final ToolingTypeManager toolingTypeManager;

	private final CodeValueManager codeValueManager;

	private List<ToolingBorrowBill> borrowBills;
	
	private String flag="Borrow";		//标实为工装领用
	
	private String toolingDev_Flag;    // 标示为设备或工装,默认为工装



	public ListToolingBorrowBillAction(
			ToolingBorrowBillManager toolingBorrowBillManager,
			DepartmentManager departmentManager,
			ToolingTypeManager toolingTypeManager,
			CodeValueManager codeValueManager) {
		this.toolingBorrowBillManager = toolingBorrowBillManager;
		this.departmentManager = departmentManager;
		this.toolingTypeManager = toolingTypeManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() {
		if(!StringUtils.isEmpty(request.getParameter("toolingDev_Flag"))){
			if(request.getParameter("toolingDev_Flag").equals("DEVICE")){
				this.toolingDev_Flag = "DEVICE";
			}else{
				this.toolingDev_Flag = "TOOLING";
			}
		}
		if (this.hasIds("borrowBillIds")) {
			this.borrowBills = this.toolingBorrowBillManager
					.loadAllToolingBorrowBills(this.getIds("borrowBillIds"));
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
		this.toolingBorrowBillManager.disabledAllToolingBorrowBills(borrowBills);
		if(this.toolingDev_Flag.equalsIgnoreCase("DEVICE")){
			this.addActionMessage(this.getText("DeviceBorrowBill.disabled.success"));
		}else{
			this.addActionMessage(this.getText("ToolingBorrowBill.disabled.success"));
		}
		return SUCCESS;
	}

	public String enabled() {
		this.toolingBorrowBillManager.enabledAllToolingBorrowBills(borrowBills);
		if(this.toolingDev_Flag.equalsIgnoreCase("DEVICE")){
			this.addActionMessage(this.getText("DeviceBorrowBill.enabled.success"));
		}else{
			this.addActionMessage(this.getText("ToolingBorrowBill.enabled.success"));
		}
		return SUCCESS;
	}

	private boolean isEnabled() {
		return this.hasKey("enabled");
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

	public List getToolingTypes() {
		return this.toolingTypeManager.createSelectToolingType(this
				.getText("select.option.all"));
	}

	public List getToolingStatus() {
		return this.codeValueManager.createSelectCodeValues(this
				.getText("select.option.all"), CodeConstants.TOOLING_STATUS);
	}

	public List<ToolingBorrowBill> getBorrowBills() {
		return borrowBills;
	}

	public void setBorrowBills(List<ToolingBorrowBill> borrowBills) {
		this.borrowBills = borrowBills;
	}

	@Override
	protected String getAdapterName() {
			return "borrowBills";

	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getToolingDev_Flag() {
		return toolingDev_Flag;
	}

	public void setToolingDev_Flag(String toolingDev_Flag) {
		this.toolingDev_Flag = toolingDev_Flag;
	}
	
}
