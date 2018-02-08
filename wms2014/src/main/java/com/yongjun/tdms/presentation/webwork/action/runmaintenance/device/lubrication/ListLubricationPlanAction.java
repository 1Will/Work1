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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanStatus;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanManager;

public class ListLubricationPlanAction extends ValueListAction {
	private static final long serialVersionUID = 6501573383104496304L;

	private final DepartmentManager departmentManager;
	private final LubricationPlanManager lubricationPlanManager;

	private LubricationPlan lubricationPlan;
	private List<LubricationPlan> lubricationPlans;
	private String planProcFlag;
	
	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public ListLubricationPlanAction(DepartmentManager departmentManager,
			LubricationPlanManager lubricationPlanManager) {
		this.departmentManager = departmentManager;
		this.lubricationPlanManager = lubricationPlanManager;
	}

	public void prepare() {

		if (this.lubricationPlans == null && this.hasIds("lubricationPlanIds")) {
			this.lubricationPlans = this.lubricationPlanManager
					.loadAllLubricationPlan(this.getIds("lubricationPlanIds"));
		}
		
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	private String delete() {
		try {
			this.lubricationPlanManager
					.deleteAllLubricationPlan(lubricationPlans);
			this.addActionMessage(this
					.getText("lubricationPlan.delete.success"));
		} catch (Exception ex) {
			ex.printStackTrace();
			this.addActionMessage(this
					.getText("lubricationPlan.delete.failure",Arrays.asList(new Object[]{ex.getMessage()})));
		}
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "lubricationPlans";
		} else {
			return "lubricationProcs";
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

	public LubricationPlan getLubricationPlan() {
		return lubricationPlan;
	}

	public void setLubricationPlan(LubricationPlan lubricationPlan) {
		this.lubricationPlan = lubricationPlan;
	}

	public List<LubricationPlan> getLubricationPlans() {
		return lubricationPlans;
	}

	public void setLubricationPlans(List<LubricationPlan> lubricationPlans) {
		this.lubricationPlans = lubricationPlans;
	}

	public LabelValue[] getLubricationPlanStatus() {
		return this.wrapEnum(LubricationPlanStatus.class);
	}
}
