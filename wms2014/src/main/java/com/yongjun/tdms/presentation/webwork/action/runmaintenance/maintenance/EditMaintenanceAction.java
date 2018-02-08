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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.maintenance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.fault.FaultType;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceManager;

/**
 * <p>Title: EditMaintenanceAction
 * <p>Description: 设备保养页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditMaintenanceAction.java 10972 2008-02-17 03:05:50Z wzou $
 */
public class EditMaintenanceAction extends PrepareAction {
	private static final long serialVersionUID = 5592221870228988051L;
	private final MaintenanceManager maintenanceManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final DeviceCardManager deviceCardManager;
	
	private Maintenance maintenance;
	private Department department;
	private DeviceCard device;
	private User writer;
	private User reporter;
	private User verifyPeople;
	private String planProcFlag;
	
	public EditMaintenanceAction(MaintenanceManager maintenanceManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			DeviceCardManager deviceCardManager) {
		this.maintenanceManager = maintenanceManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.deviceCardManager = deviceCardManager; 
	}
	public void prepare() throws Exception {
		if(null == this.maintenance) {
			if(this.hasId("maintenance.id")) {
				this.maintenance = this.maintenanceManager.loadMaintenance(this.getId("maintenance.id"));
			} else {
				this.maintenance = new Maintenance();
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.maintenance.isNew();
		if(this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			maintenance.setPlanProcFlag(PreRepairModel.PLAN);
		}else{
			maintenance.setPlanProcFlag(PreRepairModel.PROC);
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			maintenance.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("writer.id"))) {
			maintenance.setWriter(this.userManager.loadUser(this.getId("writer.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("verifyPeople.id"))) {
			maintenance.setVerifyPeople(this.userManager.loadUser(this.getId("verifyPeople.id")));
		}
//		if(this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
//			if (request.getParameter("maintenance.result").equals("one_maintenance")){
//				maintenance.setResult(MaintenanceType.one_maintenance);
//			} else {
//				maintenance.setResult(MaintenanceType.two_maintenance);
//			}
//		}
		
		if (!StringUtils.isEmpty(request.getParameter("reporter.id"))) {
			maintenance.setReporter(this.userManager.loadUser(this.getId("reporter.id")));
		}
		
		this.maintenanceManager.storeMaintenance(maintenance);
			
		if (isNew) {
			this.addActionMessage(this.getText("maintenancePlan.add.success",
					Arrays.asList(new Object[] { maintenance.getPlanName() })));
			return NEW;
		} else if(this.maintenance.equals(PreRepairModel.PLAN.toString())){
			this.addActionMessage(this.getText("maintenancePlan.edit.success",
					Arrays.asList(new Object[] { maintenance.getPlanName() })));
			return SUCCESS;
		} else {
			this.addActionMessage(this.getText("maintenanceProc.edit.success",
					Arrays.asList(new Object[] { maintenance.getPlanName() })));
			return SUCCESS;
		}
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public DeviceCard getDevice() {
		return device;
	}
	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	public Maintenance getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}
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
	
	public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
	
	public User getLoginUser() {
		return userManager.getUser();
	}
	
	public List<LabelValue> getResults() {
		LabelValue[] arrays = this.wrapEnum(MaintenanceType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

}
