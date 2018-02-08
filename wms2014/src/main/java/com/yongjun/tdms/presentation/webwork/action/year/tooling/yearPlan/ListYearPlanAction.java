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
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;
/**
 * <p>Title: ListYearPlanAction
 * <p>Description: 年度计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
public class ListYearPlanAction extends ValueListAction {
	private static final long serialVersionUID = 245907226723734572L;
	
	private final YearPlanManager yearPlanManager;
	private final DepartmentManager departmentManager;
	
	private List<YearPlan> yearPlans;
	private String year;
	
	public ListYearPlanAction(YearPlanManager yearPlanManager,
			DepartmentManager departmentManager) {
		this.yearPlanManager = yearPlanManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		//根据页面传入的"yearPlanIds"(年度计划ID集合),获取年度计划集合
		if (this.hasId("yearPlanIds")) {
			this.yearPlans = this.yearPlanManager.loadAllYearPlans(this.getIds("yearPlanIds"));
		}
		
		if (this.hasId("year")) {
			this.year = request.getParameter("year");
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
		//锁定选中的年度计划
		if (this.isLocked()) {
			this.yearPlanManager.lockedAllYearPlan(yearPlans);
			this.addActionMessage(this.getText("yearPlan.locked.success"));
		}
		//解锁选中的年度计划
		if (this.isUnLocked()) {
			this.yearPlanManager.unLockedAllYearPlan(yearPlans);
			this.addActionMessage(this.getText("yearPlan.unLocked.success"));
		}
		if (this.isGenerateYearDudget()) {
			this.yearPlanManager.generateYearDudget(yearPlans,year);
			this.addActionMessage(this.getText("yearBudget.generate.success"));
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击了"锁定"按钮
	 * @return true | false
	 */
	private boolean isLocked() {
		return this.hasKey("locked");
	}
	
	/**
	 * 判断是否点击了"解锁"按钮
	 * @return true | false
	 */
	private boolean isUnLocked() {
		return this.hasKey("unLocked");
	}
	
	/**
	 * 判断是否点击了"生成年度预算"按钮 
	 * @return true | false
	 */
	private boolean isGenerateYearDudget() {
		return this.hasKey("generateYearBudget");
	}
	
	/**
	 * 失效页面选中的年度计划
	 * @return SUCCESS
	 */
	public String disabled() {
		this.yearPlanManager.disabledAllYearPlans(this.yearPlans);
		this.addActionMessage(this.getText("yearPlan.disabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 有效页面选中的年度计划
	 * @return SUCCESS
	 */
	public String enabled() {
		try {
			this.yearPlanManager.enabledAllYearPlans(this.yearPlans);
			this.addActionMessage(this.getText("yearPlan.enabled.success"));
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionMessage(this.getText("yearPlan.enabled.fauler"));
			return SUCCESS;
		}
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "toolingYearPlans";
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
