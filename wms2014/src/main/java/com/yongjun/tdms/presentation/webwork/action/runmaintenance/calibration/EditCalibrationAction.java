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

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationManager;

/**
 * <p>Title: EditCalibrationAction
 * <p>Description: 大项修维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public class EditCalibrationAction extends PrepareAction {
	private static final long serialVersionUID = 9172628307182529529L;
	private final CalibrationManager calibrationManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	private Calibration calibration;
	//private Department department;
	private User writer;
	private User verifyPeople;
	private User approver;
	private User reporter;
	private String planProcFlag;
	
	public EditCalibrationAction(CalibrationManager calibrationManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.calibrationManager = calibrationManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}
	public void prepare() throws Exception {
		if(null == this.calibration) {
			if(this.hasId("calibration.id")) {
				this.calibration = this.calibrationManager.loadCalibration(this.getId("calibration.id"));
			} else {
				this.calibration = new Calibration();
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.calibration.isNew();
		
		if(this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			calibration.setPlanProcFlag(PreRepairModel.PLAN);
			if (!StringUtils.isEmpty(request.getParameter("department.id"))) {        //设置关联的部门以及部门名称
				Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
				calibration.setDepartment(dept);
				calibration.setDeptName(dept.getName());
			}
			
			if (!StringUtils.isEmpty(request.getParameter("writer.id"))) {
				calibration.setWriter(this.userManager.loadUser(this.getId("writer.id")));
			}
			if (!StringUtils.isEmpty(request.getParameter("verifyPeople.id"))) {
				calibration.setVerifyPeople(this.userManager.loadUser(this.getId("verifyPeople.id")));
			}
			if (!StringUtils.isEmpty(request.getParameter("approver.id"))) {
				calibration.setApprover(this.userManager.loadUser(this.getId("approver.id")));
			}
			this.calibrationManager.storeCalibration(calibration);
		} else {
			if (!StringUtils.isEmpty(request.getParameter("reporter.id"))) {
				calibration.setReporter(this.userManager.loadUser(this.getId("reporter.id")));
			}
			this.calibrationManager.storeCalibration(calibration);
		}
		if (isNew) {
			this.addActionMessage(this.getText("calibrationplan.add.success",
					Arrays.asList(new Object[] { calibration.getPlanName() })));
			return NEW;
		} else if(this.calibration.equals(PreRepairModel.PLAN.toString())){
			this.addActionMessage(this.getText("calibrationPlan.edit.success",
					Arrays.asList(new Object[] { calibration.getPlanName() })));
			return SUCCESS;
		} else {
			this.addActionMessage(this.getText("calibrationProc.edit.success",
					Arrays.asList(new Object[] { calibration.getPlanName() })));
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
		return departmentManager.createSelectDepartments(StringUtils.EMPTY);
	}
	
	public User getApprover() {
		return approver;
	}
	public void setApprover(User approver) {
		this.approver = approver;
	}
	public Calibration getCalibration() {
		return calibration;
	}
	public void setCalibration(Calibration calibration) {
		this.calibration = calibration;
	}
//	public Department getDepartment() {
//		return department;
//	}
//	public void setDepartment(Department department) {
//		this.department = department;
//	}
	public String getPlanProcFlag() {
		return planProcFlag;
	}
	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public User getVerifyPeople() {
		return verifyPeople;
	}
	public void setVerifyPeople(User verifyPeople) {
		this.verifyPeople = verifyPeople;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public User getLoginUser() {
		return userManager.getUser();
	}

}
