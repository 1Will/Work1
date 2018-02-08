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
package com.yongjun.tdms.presentation.webwork.action.year.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

/**
 * <p>Title: ListRepairPlanAndProcAction
 * <p>Description: 大项修计划实施页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListRepairPlanAndProcAction.java 11184 2008-03-03 13:39:26Z zbzhang $
 */
public class ListRepairPlanAndProcAction extends ValueListAction{
	private static final long serialVersionUID = 63280815808246575L;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final DepartmentManager departmentManager;
	
	private List<RepairPlanAndProc> repairPlanAndProcs;
	private String planProcFlag;
	private String toolingDevFlag;
 	
	public ListRepairPlanAndProcAction (RepairPlanAndProcManager repairPlanAndProcManager,
			DepartmentManager departmentManager) {
		this.repairPlanAndProcManager = repairPlanAndProcManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 获取页面参数 <b>repairPlanOrProcIds</b>，如果得到，就根据ID获取大维修计划或实施List
	 */
	@Override
	public void prepare() throws Exception {
		if (this.repairPlanAndProcs == null && this.hasIds("repairPlanOrProcIds")) {
			this.repairPlanAndProcs = this.repairPlanAndProcManager.loadAllRepairPlanOrProcs(
					this.getIds("repairPlanOrProcIds"));
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
			this.repairPlanAndProcManager.deleteAllRepairPlanOrProcs(this.repairPlanAndProcs);
		} catch (Exception e) {
			this.addActionMessage(this.getText("repairPlanOrProc.delete.error"));
			return ERROR;
		}
		this.addActionMessage(this.getText("repairPlanOrProc.delete.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "yearRepairPlans";
		} else {
			return "yearRepairProcs";
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("toolingDevFlag")) {
			map.put("toolingDevFlag", this.toolingDevFlag);
		}
		if (!this.isSearch()) {
			map.put("department.id", this.getLoginUser().getDepartment().getId());
		}
		return map;
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
