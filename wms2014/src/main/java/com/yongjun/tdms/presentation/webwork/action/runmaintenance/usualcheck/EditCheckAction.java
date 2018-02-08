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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.usualcheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.fault.FaultType;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.model.runmaintenance.usualcheck.CheckStatus;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;

/**
 * <p>Title: EditCheckAction
 * <p>Description: 设备或工装日常检查维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */

public class EditCheckAction extends PrepareAction {
	private static final long serialVersionUID = -731024172361260749L;
	
	private final CheckManager checkManager;
	private final UserManager userManager;
	private final DeviceCardManager deviceCardManager;
	private final DepartmentManager departmentManager;
	
	private Check check;
	private DeviceCard asset;
	private String toolingDevFlag;                       //资产标识[工装]|[设备]

	public EditCheckAction(CheckManager checkManager, 
			UserManager userManager, 
			DeviceCardManager deviceCardManager,
			DepartmentManager departmentManager) {
		this.checkManager = checkManager;
		this.userManager = userManager;
		this.deviceCardManager = deviceCardManager;
		this.departmentManager = departmentManager;
	}
	
	public void prepare() throws Exception {
		if (null == check) {
			if (this.hasId("check.id")) {
				this.check = this.checkManager.loadCheck(this.getId("check.id"));
				if (!this.hasId("device.id") && !this.hasId("tooling.id")) {
					this.asset = check.getAsset();
				}
			} else {
				this.check = new Check();
			}
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
  
	
	
	/**
	 * 点击保存按钮，保存设备或工装的日常检查
	 * @return String SUCCESS | NEW
	 */
	public String save() {
		boolean isNew = this.check.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("checker.name"))) {
			check.setChecker(request.getParameter("checker.name"));
		}
		if((!StringUtils.isEmpty(request.getParameter("check.status")))){
			
			if(request.getParameter("check.status").equals(CheckStatus.Enrol.toString())){
				check.setStatus(CheckStatus.Enrol);
			}else if (request.getParameter("check.status").equals(CheckStatus.unEnrol.toString())){
				check.setStatus(CheckStatus.unEnrol);
			}
		} else {
			check.setStatus(null);
		}
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			check.setToolingDevFlag(SysModel.DEVICE);
			if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
				check.setAsset(this.deviceCardManager.loadDevice(this.getId("device.id")));
				check.setDepartment(this.deviceCardManager.loadDevice(this.getId("device.id")).getDepartment());
			}else {
				if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
					check.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
				}
			}
		} else {
			check.setToolingDevFlag(SysModel.TOOLING);
			if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
				check.setAsset(this.deviceCardManager.loadDevice(this.getId("tooling.id")));
				check.setDepartment(this.deviceCardManager.loadDevice(this.getId("tooling.id")).getDepartment());
			}else {
				if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
					check.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
				}
			}
		}
		
		// 保存日常检查单的状态

		if (!StringUtils.isEmpty(request.getParameter("checkBox.status")) 
				&& request.getParameter("checkBox.status").equals("true")){
			check.setUnEnrol(true);
		}else{
			check.setUnEnrol(false);
		}
		this.checkManager.storeCheck(check);
		
		//日志
		String logContent = "";
		if(isNew){
			logContent = "被添加";
		}else{
			logContent = "被修改";
		}
		logContent = check.getBillNo()+logContent;
		this.getLogger().logStore(check, logContent);
		
		if (isNew) {
			 this.addActionMessage(this.getText("check.add.success",
						Arrays.asList(new Object[] { check.getBillNo() })));
			 return NEW;
		} else {
			this.addActionMessage(this.getText("check.edit.success",
					Arrays.asList(new Object[] { check.getBillNo() })));
			return SUCCESS;
		}
	}
	
	//获得日常检查的状态
	public List<LabelValue> getResults() {
		LabelValue[] arrays = this.wrapEnum(CheckStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText(""));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}
	
	public DeviceCard getTooling() {
		return asset;
	}
	
	public DeviceCard getDevice() {
		return asset;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	
	public User getLoginUser() {
		return userManager.getUser();
	}
	
	/**
	 * 获取所有的部门的值
	 * @return List 部门集合
	 */
	public List<Department> getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
}
