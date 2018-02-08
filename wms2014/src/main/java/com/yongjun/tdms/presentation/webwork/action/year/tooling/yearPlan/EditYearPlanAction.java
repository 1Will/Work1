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
package com.yongjun.tdms.presentation.webwork.action.year.tooling.yearPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * 
 * <p>Title: EditYearPlanAction
 * <p>Description: 年度计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditYearPlanAction extends PrepareAction {

	private static final long serialVersionUID = 1156404061641510651L;
	
	private final YearPlanManager yearPlanManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	private YearPlan yearPlan;
	
	public EditYearPlanAction(YearPlanManager yearPlanManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.yearPlanManager = yearPlanManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}
	
	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == yearPlan) {
			if (this.hasId("yearPlan.id")) {           //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
				this.yearPlan = this.yearPlanManager.loadYearPlan(this.getId("yearPlan.id"));
			} else {                                   //如果请求中不包含"yearPlan.id",则创建年度计划对象
				this.yearPlan = new YearPlan();
			}
		}
	}
	
	/**
	 * 当页面点击保存按钮,保存年度计划的信息
	 * @return
	 */
	public String save() {
		boolean isNew = this.yearPlan.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {          //设置年度计划中的部门
			Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
			yearPlan.setDepartment(dept);      //设置部门id
			yearPlan.setDeptName(dept.getName()); //设置部门名称

		}
		if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {        //设置年度计划中的编制人
			yearPlan.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		//如果此年度计划在本年度被制定  那么提示出错信息  
			YearPlan yearPlanByDepartANDYear =this.yearPlanManager.loadYearPlanByDepartNameANDYear(yearPlan.getDepartment().getId(),yearPlan.getYear());
			if (null != yearPlanByDepartANDYear&& !yearPlanByDepartANDYear.equals(yearPlan)) {
				this.addActionMessage(this.getText("yearPlan.add.error", Arrays
						.asList(new Object[] { yearPlan.getName()})));
				return ERROR;
			}
			
		
		
		try {
			this.yearPlanManager.storeYearPlan(yearPlan);	
		}catch (Exception e) {
			
			return SUCCESS;
		}
		if (isNew) {
			this.addActionMessage(this.getText("yearPlan.add.success", Arrays
					.asList(new Object[] { yearPlan.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("yearPlan.edit.success",
							Arrays.asList(new Object[] { yearPlan.getName() })));
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
	
	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}

}
