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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * <p>Title: EditPreRepairPlanAction
 * <p>Description: 预防性维修计划维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditPreRepairPlanAction.java 11187 2008-03-04 00:51:31Z zbzhang $
 */
public class EditPreRepairPlanAction extends PrepareAction {
	private static final long serialVersionUID = 6470140965295316393L;
	private final Log log = LogFactory.getLog(getClass());
	private final PreRepairPlanManager preRepairPlanManager;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final WfDocApproverManager wfDocApproverManager;
	private final WfJobManager wfJobManager;
	
	private PreRepairPlan preRepairPlan;
	private String planProcFlag;                  //标识是维修计划，还是维修实施
	private String toolingDevFlag;                //标识是工装、还是设备
	
	public EditPreRepairPlanAction(PreRepairPlanManager preRepairPlanManager,
			DepartmentManager departmentManager,
			PreRepairPlanDetailManager preRepairPlanDetailManager,
			UserManager userManager,
			WfDocApproverManager wfDocApproverManager,
			WfJobManager wfJobManager) {
		this.preRepairPlanManager = preRepairPlanManager;
		this.departmentManager = departmentManager;
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.userManager = userManager;
		this.wfDocApproverManager = wfDocApproverManager;
		this.wfJobManager = wfJobManager;
	}
	public void prepare() throws Exception {
		if(null == this.preRepairPlan){
			if(this.hasId("preRepairPlan.id")) {
				this.preRepairPlan = this.preRepairPlanManager.loadPreRepairPlan(this.getId("preRepairPlan.id"));
			} else {
				this.preRepairPlan = new PreRepairPlan();
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	 public String save() {
		 
		 boolean isNew = this.preRepairPlan.isNew();

		 if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {       //保存预防性维修计划
			 preRepairPlan.setPlanProcFlag(PreRepairModel.PLAN);
			 if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
				 Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
				 preRepairPlan.setDepartment(dept);
				 preRepairPlan.setDeptName(dept.getName());
			 }
			 if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {
				 preRepairPlan.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
			 }
			 if (!StringUtils.isEmpty(request.getParameter("planAuditor.id"))) {
				 preRepairPlan.setPlanAuditor(this.userManager.loadUser(this.getId("planAuditor.id")));
			 }
			 if (this.toolingDevFlag.equals(SysModel.TOOLING.toString())) {
				 preRepairPlan.setToolingDevFlag(SysModel.TOOLING);
			 } else {
				 preRepairPlan.setToolingDevFlag(SysModel.DEVICE);
			 }
			 this.preRepairPlanManager.storePreRepairPlan(preRepairPlan);
		 } else {
			 if (!StringUtils.isEmpty(request.getParameter("reportor.id"))) {
				 preRepairPlan.setReportor(this.userManager.loadUser(this.getId("reportor.id")));
			 }
			 this.preRepairPlanManager.storePreRepairProc(preRepairPlan);
			 this.addActionMessage(this.getText("preRepairProc.edit.success",
					Arrays.asList(new Object[] { preRepairPlan.getName() })));
			 return SUCCESS;
			 
		 }
		 //日志
		 String logContent = "";
		 logContent = preRepairPlan.getPlanNo();
		 this.getLogger().logStore(preRepairPlan, logContent, isNew);
		 
		 if (isNew) {
			 this.addActionMessage(this.getText("preRepairPlan.add.success",
						Arrays.asList(new Object[] { preRepairPlan.getName() })));
			 return NEW;
		 }  else if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())){
				this.addActionMessage(this.getText("preRepairPlan.edit.success",
						Arrays.asList(new Object[] { preRepairPlan.getName() })));
				return SUCCESS;
		 } 
		 return SUCCESS;
	 }
	
   	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门
	 * 
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
				list.add(this.departmentManager.loadDepartment(this.userManager
						.getUser().getDepartment().getId()));
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
	
	public PreRepairPlan getPreRepairPlan() {
		return preRepairPlan;
	}
	
	public void setPreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlan = preRepairPlan;
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
