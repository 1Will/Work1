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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * <p>Title: ListPreRepairAction
 * <p>Description: 预防性维修计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListPreRepairPlanAction.java 11132 2008-02-27 06:17:51Z zbzhang $
 */
public class ListPreRepairPlanAction extends ValueListAction{
	private static final long serialVersionUID = 6502940067610154591L;
	private final PreRepairPlanManager preRepairPlanManager;
	private final DepartmentManager departmentManager;
	private final WfDocStateManager wfDocStateManager;
	
	private List<PreRepairPlan> preRepairPlans;
	private String planProcFlag;
	private String toolingDevFlag;
	private boolean invalid;
	
	public ListPreRepairPlanAction(PreRepairPlanManager preRepairPlanManager,
			DepartmentManager departmentManager,
			WfDocStateManager wfDocStateManager){
		this.preRepairPlanManager = preRepairPlanManager;
		this.departmentManager = departmentManager;
		this.wfDocStateManager = wfDocStateManager;
	}
	
	/**
	 * 获取页面参数 <b>preRepairPlanIds</b>，如果得到，就根据ID获取预防性维修计划List
	 */
	public void prepare() throws Exception {
		if(this.preRepairPlans == null && this.hasIds("preRepairPlanIds")) {
			//System.out.println("ss:"+this.request.getParameterValues("preRepairPlanIds").toString());
			this.preRepairPlans = this.preRepairPlanManager.loadAllPreRepairPlans(
					this.getIds("preRepairPlanIds"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b>函数处理。
	 */
	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除选择的预防性维修计划或预防性维修实施
	 */
	public String delete() {
		try {
			this.preRepairPlanManager.deleteAllPreRepairPlan(this.preRepairPlans);
		} catch (Exception e) {
			this.addActionMessage(this.getText("preRepairPlan.delete.error"));
			return ERROR;
		}
		this.addActionMessage(this.getText("preRepairPlan.delete.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "preRepairPlans";
		} else {
			return "preRepairProcs";
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("toolingDevFlag")) {
			map.put("toolingDevFlag", this.toolingDevFlag);
		}
		if (invalid) {
			map.remove("docState.code");
		}
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

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
