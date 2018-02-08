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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.fault;

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
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.financeType.FeeSourceType;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.model.runmaintenance.fault.FaultType;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultBillManager;

/**
 * <p>Title: EditFaultBillAction
 * <p>Description: 故障单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditFaultBillAction.java 10972 2008-02-17 03:05:50Z wzou $
 */
public class EditFaultBillAction extends PrepareAction {
	private static final long serialVersionUID = 4709131344840548128L;
	private final FaultBillManager faultBillManager;
	private final DepartmentManager departmentManager;
    private final UserManager userManager;
    private final CodeValueManager codeValueManager;
    private final DeviceCardManager deviceCardManager;
    private FaultBill faultBill;
    private DeviceCard asset;
    private Department dept;
    
    
//    private boolean bTooling = false; //默认是设备
    private String toolingDevFlag;
    private String IsPopWindow;
    
    public String getIsPopWindow() {
		return IsPopWindow;
	}

	public void setIsPopWindow(String isPopWindow) {
		IsPopWindow = isPopWindow;
	}

	public EditFaultBillAction(FaultBillManager faultBillManager,
    		              DepartmentManager departmentManager,
    		              UserManager userManager,
    		              CodeValueManager codeValueManager,
    		              DeviceCardManager deviceCardManager) {
    	this.faultBillManager = faultBillManager;
    	this.departmentManager = departmentManager;
    	this.userManager = userManager;
    	this.codeValueManager = codeValueManager;
    	this.deviceCardManager = deviceCardManager;
    }

	/**
	 * 初始化，获取"toolingDevFlag",以判断该故障单是工装还是设备
	 * 获取参数<b>toolingFaultBill.id</b>，如果存在就获取这个故障单实体和工装（或设备）实体，
	 * 如果不存在，就新建一个故障单实体，同时根据先前的判断将该故障单标示为工装或设备
	 */
	public void prepare() throws Exception {
		if(request.getParameter("toolingDevFlag").equals("TOOLING")){
			this.toolingDevFlag="TOOLING";
		}
		else {
			this.toolingDevFlag="DEVICE";
		}
		if(this.hasId("IsPopWindow")){
			this.IsPopWindow=(String)request.getParameter("IsPopWindow");
		}else{
			this.IsPopWindow="F";
		}
		if (null == faultBill) {
			if (this.hasId("faultBill.id")) {
				this.faultBill = this.faultBillManager.loadFaultBill(this.getId("faultBill.id"));
				if(!this.hasId("tooling.id") && !this.hasId("device.id")) {
					this.asset = faultBill.getToolingDev();
				}
			} else {
				this.faultBill = new FaultBill();
			}
		}
		if (this.toolingDevFlag.equals("TOOLING")) {
    		this.faultBill.setToolingDevFlag(SysModel.TOOLING);
    	} else {
    		this.faultBill.setToolingDevFlag(SysModel.DEVICE);
    	}
		if(!StringUtils.isEmpty(request.getParameter("department.id"))){
			this.dept = this.departmentManager.loadDepartment(this.getId("department.id"));
		}
	}
	
	/**
	 * 保存一个故障单信息
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		boolean isNew = this.faultBill.isNew();
		if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
			this.asset = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
			faultBill.setToolingDev(asset);
			faultBill.setDepartment(asset.getDepartment());
		} 
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			this.asset = this.deviceCardManager.loadDevice(this.getId("device.id"));
			faultBill.setToolingDev(asset);
			asset.setDepartment(dept);
			faultBill.setDepartment(asset.getDepartment());
		} 
		if (StringUtils.isEmpty(request.getParameter("tooling.id")) && 
				StringUtils.isEmpty(request.getParameter("device.id"))) {
			if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
				faultBill.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("manager.id"))) {
			faultBill.setManager(this.userManager.loadUser(this.getId("manager.id")));
		}

		if (!StringUtils.isEmpty(request.getParameter("faultStateFlag"))) {
			faultBill.setFaultFlag(true);
		} else {
			faultBill.setFaultFlag(false);
		}
		if (asset != null) {
			if (!StringUtils.isEmpty(request.getParameter("toolingState"))){
				asset.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("toolingState")));
			}
			if (!StringUtils.isEmpty(request.getParameter("deviceState"))){
				asset.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("deviceState")));
			}
		}
		if (request.getParameter("faultBill.result").equals("ACCIDENT")){
			faultBill.setResult(FaultType.ACCIDENT);
		} else {
			faultBill.setResult(FaultType.FAULT);
		}
		if(!StringUtils.isEmpty(request.getParameter("faultBill.faultCatorgy"))){
			faultBill.setFaultCatorgy(this.codeValueManager.loadCodeValue(this.getId("faultBill.faultCatorgy")));
		}
		if(!StringUtils.isEmpty(request.getParameter("device.category"))){
			faultBill.setDeviceCatorgy(request.getParameter("device.category"));
		}
		this.faultBillManager.storeFaultBill(faultBill, asset);
		
		String logContent = "";
		if(isNew){
			logContent = "被添加";
		}else{
			logContent = "被修改";
		}
		logContent = faultBill.getBillNo() + logContent;
		this.getLogger().logStore(faultBill, logContent);
		
		if (isNew) {
			if(faultBill.getToolingDevFlag()==SysModel.TOOLING){
				this.addActionMessage(this.getText("toolingFaultBill.add.success", Arrays
					.asList(new Object[] { faultBill.getBillName() })));
			}else{
				this.addActionMessage(this.getText("deviceFaultBill.add.success", Arrays
						.asList(new Object[] { faultBill.getBillName() })));
			}
			return NEW;
		} else {
			if(faultBill.getToolingDevFlag()==SysModel.TOOLING){
				this.addActionMessage(this.getText("toolingFaultBill.edit.success", Arrays
					.asList(new Object[] { faultBill.getBillName() })));
			}else{
				this.addActionMessage(this.getText("deviceFaultBill.edit.success", Arrays
						.asList(new Object[] { faultBill.getBillName() })));
			}
			return SUCCESS;
		}
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	public List<LabelValue> getResults() {
		LabelValue[] arrays = this.wrapEnum(FaultType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	/**
	 * 获取故障类别集合
	 * @return List 故障类别集合
	 */
	public List<CodeValue> getFaultCatorgys(){
		CodeValue cv = new CodeValue();
		List<CodeValue> list = this.codeValueManager.LoadAllValuesByCode(CodeConstants.FAULT_CATEGORY);
		list.add(0,cv);
		return list;
	}
	
	public DeviceCard getDevice(){
		DeviceCard device = asset;
		return device;
	}
	
	public FaultBill getFaultBill() {
		return faultBill;
	}

	public void setFaultBill(FaultBill faultBill) {
		this.faultBill = faultBill;
	}

	/**
	 * 获取所有部门集合
	 * @return  List 部门集合
	 */
	public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
    }

	public DeviceCard getTooling() {
		return asset;
	}


	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	/**
	 * @return the dept
	 */
	public Department getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}


}
