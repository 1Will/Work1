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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingBorrowBillManager;

/**
 * @author qs
 * @version $Id: EditToolingBorrowBillAction.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class EditToolingBorrowBillAction extends PrepareAction {
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private static final long serialVersionUID = 6675914441752704686L;
	private final ToolingBorrowBillManager toolingBorrowBillManager;
    private final DeviceCardManager deviceCardManager;
    private final UserManager userManager;
    private final CodeValueManager codeValueManager;
    private ToolingBorrowBill toolingBorrowBill;
    private DeviceCard tooling;
    private String flag="Borrow";		//标实为工装领用

    private String toolingDev_Flag;    // 标示为设备或工装
	private List<Long> oldDeviceIds;   // 需要过滤的设备ID集合
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public EditToolingBorrowBillAction(ToolingBorrowBillManager toolingBorrowBillManager,
    		                        DeviceCardManager deviceCardManager,
    		                        UserManager userManager,
    		                        CodeValueManager codeValueManager) {
    	this.toolingBorrowBillManager = toolingBorrowBillManager;
    	this.deviceCardManager = deviceCardManager;
    	this.userManager = userManager;
    	this.codeValueManager = codeValueManager;
    }
    
	public void prepare() throws Exception {

		if(request.getParameter("toolingDev_Flag").equals("DEVICE")){
			this.toolingDev_Flag = "DEVICE";
		}else{
			this.toolingDev_Flag = "TOOLING";
		}

		if (null == this.toolingBorrowBill) {
			if (this.hasId("toolingBorrowBill.id")) {
				this.toolingBorrowBill = this.toolingBorrowBillManager.loadToolingBorrowBill(this.getId("toolingBorrowBill.id"));
				if(!this.hasId("tooling.id")) {
					this.tooling = toolingBorrowBill.getTooling();
				}
			} else {
				this.toolingBorrowBill = new ToolingBorrowBill();
			}
		}
		
		if(this.toolingDev_Flag.equals("DEVICE")){
			this.toolingBorrowBill.setToolingDevFlag(SysModel.DEVICE);
		}else{
			this.toolingBorrowBill.setToolingDevFlag(SysModel.TOOLING);
		}
	}
	
	public String save() {

		boolean isNew = this.toolingBorrowBill.isNew();
		if(this.toolingBorrowBill.getToolingDevFlag() == SysModel.TOOLING){
			if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
				this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
				toolingBorrowBill.setTooling(tooling);
			}
		}else{
			if(!StringUtils.isEmpty(request.getParameter("device.id"))){
				this.tooling = this.deviceCardManager.loadDevice(this.getId("device.id"));
				toolingBorrowBill.setTooling(tooling);
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("borrower.id"))) {
			toolingBorrowBill.setBorrower(this.userManager.loadUser(this.getId("borrower.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			toolingBorrowBill.setDevice(this.deviceCardManager.loadDevice(this.getId("device.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("checker.id"))) {
			toolingBorrowBill.setChecker(this.userManager.loadUser(this.getId("checker.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("storeman.id"))) {
			toolingBorrowBill.setStoreman(this.userManager.loadUser(this.getId("storeman.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("returnStateFlag"))) {
			toolingBorrowBill.setReturnFlag(true);
		} else {
			toolingBorrowBill.setReturnFlag(false);
		}
		if(!StringUtils.isEmpty(request.getParameter("toolingBorrowBill.repayPeople"))){
			toolingBorrowBill.setRepayPeople(request.getParameter("toolingBorrowBill.repayPeople"));
		}
		
		if(this.toolingBorrowBill.getToolingDevFlag() == SysModel.TOOLING){
			if (!StringUtils.isEmpty(request.getParameter("toolingState"))){
				tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("toolingState")));
			}
		}
		if (!isNew) {
			Long oldProduceNum = null;
			if (!StringUtils.isEmpty(request.getParameter("produceNum"))) {
				oldProduceNum = Long.valueOf(request.getParameter("produceNum"));
			}
			this.toolingBorrowBillManager.storeToolingBorrowBill(toolingBorrowBill, tooling, oldProduceNum);
		} else {
			this.toolingBorrowBillManager.storeToolingBorrowBill(toolingBorrowBill,tooling,null);
		}
		
		if (isNew) {
			if(this.toolingBorrowBill.getToolingDevFlag() == SysModel.DEVICE){
				this.addActionMessage(this.getText("deviceBorrowBill.add.success", Arrays
						.asList(new Object[] { toolingBorrowBill.getBillName() })));
			}else{
				this.addActionMessage(this.getText("toolingBorrowBill.add.success", Arrays
						.asList(new Object[] { toolingBorrowBill.getBillName() })));
			}
			return NEW;
		} else {
			if(this.toolingBorrowBill.getToolingDevFlag() == SysModel.DEVICE){
				this.addActionMessage(this.getText("deviceBorrowBill.edit.success", Arrays
					.asList(new Object[] { toolingBorrowBill.getBillName() })));
			}else{
				this.addActionMessage(this.getText("toolingBorrowBill.edit.success", Arrays
						.asList(new Object[] { toolingBorrowBill.getBillName() })));
			}
			return SUCCESS;
		}
	}
	
	public User getLoginUser(){
    	return this.userManager.getUser();
    }
	public ToolingBorrowBill getToolingBorrowBill() {
		return toolingBorrowBill;
	}
	
	public void setToolingBorrowBill(ToolingBorrowBill toolingBorrowBill) {
		this.toolingBorrowBill = toolingBorrowBill;
	}
	
	public List getToolingStates() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.TOOLING_STATUS);
	}

	public String getToolingDev_Flag() {
		return toolingDev_Flag;
	}

	public void setToolingDev_Flag(String toolingDev_Flag) {
		this.toolingDev_Flag = toolingDev_Flag;
	}

	public List<Long> getOldDeviceIds() {
		return this.toolingBorrowBillManager.getOldDeviceIds(toolingDev_Flag, this.toolingBorrowBill.isReturnFlag());
	}

	public void setOldDeviceIds(List<Long> oldDeviceIds) {
		this.oldDeviceIds = oldDeviceIds;
	}
	
	
	
}
