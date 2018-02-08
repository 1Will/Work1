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
package com.yongjun.tdms.presentation.webwork.action.year.device.runmaintainPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanManager;

/**
 * 
 * <p>Title: EditRunmaintainPlanAction
 * <p>Description: 年度运维计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditRunmaintainPlanAction extends PrepareAction {
	private static final long serialVersionUID = -5242003558755473220L;
	
	private final RunmaintainPlanManager runmaintainPlanManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	//年度运维计划
	private RunmaintainPlan runmaintainPlan;
	
	public EditRunmaintainPlanAction(RunmaintainPlanManager runmaintainPlanManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.runmaintainPlanManager = runmaintainPlanManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}

	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == this.runmaintainPlan) {
			if (this.hasId("runmaintainPlan.id")) {
				//如果请求中包含"runmaintainPlan.id",则根据"runmaintainPlan.id"获取设备年度运维计划对象
				this.runmaintainPlan = this.runmaintainPlanManager.loadRunmaintainPlan(this.getId("runmaintainPlan.id"));
			} else {
				//如果请求中不包含"runmaintainPlan.id",则创建设备年度运维计划对象
				this.runmaintainPlan = new RunmaintainPlan();
			}
		}
	}

	/**
	 * 当页面点击保存按钮,保存设备年度运维计划的信息
	 * @return
	 */
	public String save() {
		boolean isNew = this.runmaintainPlan.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {          //设置设备年度运维计划中的部门
			this.runmaintainPlan.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {        //设置设备年度运维计划中的编制人
			this.runmaintainPlan.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		this.runmaintainPlanManager.storeRunmaintainPlan(this.runmaintainPlan);
		if (isNew) {
			this.addActionMessage(this.getText("runmaintainPlan.add.success", Arrays
					.asList(new Object[] { runmaintainPlan.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("runmaintainPlan.edit.success",
							Arrays.asList(new Object[] { runmaintainPlan.getName() })));
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
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
		
	public RunmaintainPlan getRunmaintainPlan() {
		return runmaintainPlan;
	}

	public void setRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.runmaintainPlan = runmaintainPlan;
	}

}
