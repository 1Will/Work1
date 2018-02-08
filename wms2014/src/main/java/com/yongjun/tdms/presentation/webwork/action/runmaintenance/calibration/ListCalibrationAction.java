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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.calibration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationManager;

/**
 * <p>Title: ListCalibrationAction
 * <p>Description: 工装标定计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class ListCalibrationAction extends ValueListAction{
	private static final long serialVersionUID = 2973489541010925589L;
	private final CalibrationManager calibrationManager;
	private final DepartmentManager departmentManager;
	
	private List<Calibration> calibrations;
	private String planProcFlag;

	public ListCalibrationAction(CalibrationManager calibrationManager,
			DepartmentManager departmentManager){
		this.calibrationManager = calibrationManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 获取页面参数 <b>calibrationIds</b>，如果得到，就根据ID获取工装标定List
	 */
	public void prepare() throws Exception {
		if(this.calibrations == null && this.hasIds("calibrationIds")) {
			this.calibrations = this.calibrationManager.loadAllCalibrations(
					this.getIds("calibrationIds"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b>函数处理。
	 */
	public String execute() throws Exception {
//		if (this.isDelete()) {
//			delete();
//		}
		if(this.isDisabled()){
			return disabled();
		}
		if(this.isEnabled()){
			return enabled();
		}
		return SUCCESS;
	}
	
	public String enabled() {
		try {
			calibrationManager.enabledAllCalibration(calibrations);
			this.addActionMessage(this.getText("calibrations.enabled.success"));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String disabled() {
		try {
			calibrationManager.disabledAllCalibration(calibrations);
			this.addActionMessage(this.getText("calibrations.disabled.success"));
		} catch (Exception e) {
			this.addActionMessage(this.getText("calibrations.disabled.fialer",Arrays.asList(new Object[] {e.getMessage()})));
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.calibrationManager.deleteAllCalibrations(this.calibrations);
		} catch (Exception e) {
			this.addActionMessage(this.getText("calibration.delete.error"));
			return ERROR;
		}
		this.addActionMessage(this.getText("calibration.delete.success"));
		return SUCCESS;
	}
	/**
	 * 判断是否有效
	 * @return
	 */
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}	
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "calibrationPlans";
		} else {
			return "calibrationProcs";
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

	public List<Calibration> getCalibrations() {
		return calibrations;
	}

	public void setCalibrations(List<Calibration> calibrations) {
		this.calibrations = calibrations;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
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
