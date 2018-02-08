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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

/**
 * 
 * <p>Title: EditPurchasePlanAction
 * <p>Description: 设备年度采购计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditPurchasePlanAction extends PrepareAction {

	private static final long serialVersionUID = -5551406132744758802L;
	
	private final PurchasePlanManager purchasePlanManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	//设备年度采购计划
	private PurchasePlan purchasePlan;
	
	public EditPurchasePlanAction(PurchasePlanManager purchasePlanManager, 
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.purchasePlanManager = purchasePlanManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}

	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == purchasePlan) {
			if (this.hasId("purchasePlan.id")) {           //如果请求中包含"purchasePlan.id",则根据"purchasePlan.id"获取设备年度采购计划对象
				this.purchasePlan = this.purchasePlanManager.loadPurchasePlan(this.getId("purchasePlan.id"));
			} else {                                   //如果请求中不包含"yearPlan.id",则创建设备年度采购计划对象
				this.purchasePlan = new PurchasePlan();
			}
		}
	}

	/**
	 * 当页面点击保存按钮,保存设备年度采购计划的信息
	 * @return
	 */
	public String save() {
		boolean isNew = this.purchasePlan.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {          //设置设备年度采购计划中的部门
			this.purchasePlan.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {        //设置设备年度采购计划中的编制人
			this.purchasePlan.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		this.purchasePlanManager.storePurchasePlan(this.purchasePlan);
		if (isNew) {
			this.addActionMessage(this.getText("purchasePlan.add.success", Arrays
					.asList(new Object[] { purchasePlan.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("purchasePlan.edit.success",
							Arrays.asList(new Object[] { purchasePlan.getName() })));
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
		return this.departmentManager.loadAllDepartments();
	}
	 
	/**
	 * 获取当前登陆的用户 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

}
