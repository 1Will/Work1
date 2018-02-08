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
package com.yongjun.tdms.presentation.webwork.action.year.device.purchasePlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

/**
 * 
 * <p>Title: ListPurchasePlanAction
 * <p>Description: 设备年度采购计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class ListPurchasePlanAction extends ValueListAction {
	private static final long serialVersionUID = -4588992643318865198L;
	
	private final PurchasePlanManager purchasePlanManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	//设备年度采购计划集合
	private List<PurchasePlan> purchasePlans;
	
	public ListPurchasePlanAction(PurchasePlanManager purchasePlanManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.purchasePlanManager = purchasePlanManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}

	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		//根据页面传入的"yearPlanIds"(设备年度采购计划ID集合),获取设备年度采购计划集合
		if (this.hasId("purchasePlanIds")) {
			this.purchasePlans = this.purchasePlanManager.loadAllPurchasePlans(this.getIds("purchasePlanIds"));
		}
	}
	
	/**
	 * 处理有效和失效的操作
	 */
	public String execute() {
		//处理失效
		if (this.isDisabled()) {
			return disabled();
		}
		//处理有效
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	/**
	 * 失效页面选中的设备年度采购计划
	 * @return SUCCESS
	 */
	public String disabled() {
		this.purchasePlanManager.disabledAllPurchasePlans(this.purchasePlans);
		this.addActionMessage(this.getText("purchasePlans.disabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 有效页面选中的设备年度采购计划
	 * @return SUCCESS
	 */
	public String enabled() {
		this.purchasePlanManager.enabledAllPurchasePlans(this.purchasePlans);
		this.addActionMessage(this.getText("purchasePlans.enabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "devicePurchasePlans";
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
