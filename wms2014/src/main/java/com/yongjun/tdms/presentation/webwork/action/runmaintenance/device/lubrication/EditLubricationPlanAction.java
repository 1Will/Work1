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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanManager;

/**
 * @author qs
 * @version $Id: EditLubricationPlanAction.java 11076 2008-02-25 07:26:14Z zbzhang $
 */
public class EditLubricationPlanAction extends PrepareAction {
	private static final long serialVersionUID = -1293008850246633198L;
	
	private final LubricationPlanManager lubricationPlanManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	
	private LubricationPlan lubrication;
	private String planProcFlag;
	
	public EditLubricationPlanAction(LubricationPlanManager lubricationPlanManager,
			UserManager userManager, DepartmentManager departmentManager) {
		this.lubricationPlanManager = lubricationPlanManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
	}
	
	public void prepare() {
		if (null == lubrication) {
			if (this.hasId("lubrication.id")) {
				this.lubrication = this.lubricationPlanManager.loadLubricationPlan(this.getId("lubrication.id"));
			} else {
				this.lubrication = new LubricationPlan();
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.lubrication.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			lubrication.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {
			lubrication.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("reportor.id"))) {
			lubrication.setReportor(this.userManager.loadUser(this.getId("reportor.id")));
		}
		this.lubricationPlanManager.storeLubricationPlan(lubrication);
		if (isNew) {
			this.addActionMessage(this.getText("lubricationPlan.add.success", Arrays
					.asList(new Object[] { lubrication.getName() })));

			return NEW;
		} else if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {       
			this.addActionMessage(this.getText("lubricationPlan.edit.success", Arrays
						.asList(new Object[] { lubrication.getName() })));
			return SUCCESS;
		} else {
			this.addActionMessage(this.getText("lubricationProc.edit.success", Arrays
					.asList(new Object[] { lubrication.getName() })));
		    return SUCCESS;
		}
	}
	public LubricationPlan getLubrication() {
		return lubrication;
	}
	public void setLubrication(LubricationPlan lubrication) {
		this.lubrication = lubrication;
	}
	
	/**
	 * 获取所有部门
	 * @return List 部门集合
	 */
	public List<Department> getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
	/**
	 * 获取当前登录的用户
	 * @return  User 当前登录用户
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
}
