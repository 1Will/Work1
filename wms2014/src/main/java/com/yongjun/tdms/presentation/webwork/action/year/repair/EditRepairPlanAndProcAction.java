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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

/**
 * @author qs
 * @version $Id: EditRepairPlanAndProcAction.java 11184 2008-03-03 13:39:26Z zbzhang $
 */
public class EditRepairPlanAndProcAction extends PrepareAction {
	private static final long serialVersionUID = 4609411222427136065L;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;

	private RepairPlanAndProc repairPlanOrProc;   //大项修计划或实施对象
	private RepairPlanAndProcDetail repairPlanAndProcDetail;//大项修计划或实施对象
	private String planProcFlag;                  //表示大项修是计划，还是实施
	private String toolingDevFlag;                //表示资产[工装][设备]
	
	public EditRepairPlanAndProcAction(RepairPlanAndProcManager repairPlanAndProcManager,
			UserManager userManager,DepartmentManager departmentManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager) {
		this.repairPlanAndProcManager = repairPlanAndProcManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
	}
	
	public void prepare() throws Exception {
		if (null == repairPlanOrProc) {
			if (this.hasId("repairPlanAndProcDetail.id")) {
				this.repairPlanAndProcDetail = this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this.getId("repairPlanAndProcDetailManager.id"));
			} else {
				this.repairPlanOrProc = new RepairPlanAndProc();
			}
		}
		if (this.hasId("repairPlanOrProc.id")) {
			this.repairPlanOrProc = this.repairPlanAndProcManager.loadRepairPlanOrProc(this.getId("repairPlanOrProc.id"));
		}
		
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	public String save() {
		
		boolean isNew = this.repairPlanOrProc.isNew();
		
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			if (!StringUtils.isEmpty(request.getParameter("department.id"))) {    //设置部门
				//this.repairPlanOrProc.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
				Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
				repairPlanOrProc.setDepartment(dept);      //设置部门id
				repairPlanOrProc.setDepartName(dept.getName()); //设置部门名称
			}
			if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {   //设置编制人
				this.repairPlanOrProc.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
			}
			if (!StringUtils.isEmpty(request.getParameter("planAuditor.id"))) {   //设置审核人
				this.repairPlanOrProc.setPlanAuditor(this.userManager.loadUser(this.getId("planAuditor.id")));
			}
			if (this.toolingDevFlag.equals(SysModel.TOOLING.toString())) {
				repairPlanOrProc.setToolingDevFlag(SysModel.TOOLING);           //设置资产[工装]
			} else {
				repairPlanOrProc.setToolingDevFlag(SysModel.DEVICE);           //设置资产[设备]
			}
			this.repairPlanOrProc.setPlanProcFlag(PreRepairModel.PLAN);         //设置计划标识
			this.repairPlanAndProcManager.storeRepairPlan(repairPlanOrProc);    //保存大项修计划
		} else {
			 this.repairPlanOrProc.setPlanProcFlag(PreRepairModel.PROC);        //设置实施标识
			 if (!StringUtils.isEmpty(request.getParameter("reportor.id"))) {   //设置报告人
				 repairPlanOrProc.setReportor(this.userManager.loadUser(this.getId("reportor.id")));
			 }
			 this.repairPlanAndProcManager.storeRepairProc(repairPlanOrProc);   //保存大项修实施
			 this.addActionMessage(this.getText("repairProc.edit.success",
						Arrays.asList(new Object[] { repairPlanOrProc.getName() })));
			return SUCCESS;
			 
		}
		if (isNew) {
			this.addActionMessage(this.getText("repairPlan.add.success", Arrays
					.asList(new Object[] { repairPlanOrProc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("repairPlan.edit.success",
							Arrays.asList(new Object[] { repairPlanOrProc
									.getName() })));
			return SUCCESS;
		} 
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public RepairPlanAndProc getRepairPlanOrProc() {
		return repairPlanOrProc;
	}

	public void setRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.repairPlanOrProc = repairPlanOrProc;
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
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}

	public RepairPlanAndProcDetail getRepairPlanAndProcDetail() {
		return repairPlanAndProcDetail;
	}

	public void setRepairPlanAndProcDetail(
			RepairPlanAndProcDetail repairPlanAndProcDetail) {
		this.repairPlanAndProcDetail = repairPlanAndProcDetail;
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}

}
