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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.wash;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

/**
 * <p>Title: EditWashAction
 * <p>Description: 清洗计划或实施维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: EditWashAction.java 10852 2008-02-01 09:40:50Z zbzhang $
 */
public class EditWashAction extends PrepareAction {
	private static final long serialVersionUID = 3557065517908941863L;
	
	private final UserManager userManager;
	private final WashManager washManager;
	private final DepartmentManager departmentManager;
	
	private Wash wash;
	private String planProcFlag;                       //计划或实施标识
	
	public EditWashAction(UserManager userManager,
			WashManager washManager,
			DepartmentManager departmentManager) {
		this.userManager = userManager;
		this.washManager = washManager;
		this.departmentManager = departmentManager;
	}

	public void prepare() throws Exception {
		if (null == wash) {
			if (this.hasId("wash.id")) {
				this.wash = this.washManager.loadWash(this.getId("wash.id"));
			} else {
				wash = new Wash();
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	/**
	 * 点击保存按钮，保存清洗计划或实施
	 * @return String SUCCESS | NEW
	 */
	public String save() {
		boolean isNew = wash.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {
			wash.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("planAuditor.id"))) {
			wash.setPlanAuditor(this.userManager.loadUser(this.getId("planAuditor.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
			wash.setDepartment(dept);
			wash.setDeptName(dept.getName());
		}
		if (!StringUtils.isEmpty(request.getParameter("reportor.id"))) {
			wash.setReportor(this.userManager.loadUser(this.getId("reportor.id")));
		}
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			wash.setPlanProcFlag(PreRepairModel.PLAN);
		} else {
			wash.setPlanProcFlag(PreRepairModel.PROC);
		}
		this.washManager.storeWash(wash);
		
		if (isNew) {
			 this.addActionMessage(this.getText("washPlan.add.success",
						Arrays.asList(new Object[] { wash.getName() })));
			 return NEW;
		} else if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			this.addActionMessage(this.getText("washPlan.edit.success",
					Arrays.asList(new Object[] { wash.getName() })));
			return SUCCESS;
		} else {
			this.addActionMessage(this.getText("washProc.edit.success",
					Arrays.asList(new Object[] { wash.getName() })));
			return SUCCESS;
		}
	}
	
	/**
	 * 获取所有部门集合
	 * @return  List 部门集合
	 */
	public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
    }
	
	/**
	 * 获取系统当前登录的人 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public Wash getWash() {
		return wash;
	}

	public void setWash(Wash wash) {
		this.wash = wash;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

}
