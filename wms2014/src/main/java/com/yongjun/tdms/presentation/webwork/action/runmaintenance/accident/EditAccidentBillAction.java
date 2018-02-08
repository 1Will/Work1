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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.accident;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.accident.AccidentBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.accident.AccidentBillManager;

/**
 * <p>Title: EditAccidentBillAction
 * <p>Description: 事故单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: EditAccidentBillAction.java 10121 2008-01-04 09:13:21Z zbzhang $
 */
public class EditAccidentBillAction extends PrepareAction {
	private static final long serialVersionUID = -5939000968794333043L;
    private final UserManager userManager;
    private final DeviceCardManager deviceCardManager;
    private final AccidentBillManager accidentBillManager;
    private AccidentBill accidentBill;
    private DeviceCard tooling;
    private DeviceCard device;
//    private boolean bTooling = false; //默认是设备
    private String toolingDevFlag;
    
    public EditAccidentBillAction(UserManager userManager,
    		                 DeviceCardManager deviceCardManager,
    		                 AccidentBillManager accidentBillManager) {
    	this.userManager = userManager;
    	this.deviceCardManager = deviceCardManager;
    	this.accidentBillManager = accidentBillManager;
    }
    
	/**
	 * 初始化，获取"toolingDevFlag",以判断该事故单是工装还是设备
	 * 获取参数<b>accidentBill.id</b>，如果存在就获取这个事故单实体和工装（或设备）实体，
	 * 如果不存在，就新建一个事故单实体，同时根据先前的判断将该事故单标示为工装或设备
	 */
    public void prepare() {
    	if(request.getParameter("toolingDevFlag").equals("TOOLING")){
			this.toolingDevFlag="TOOLING";
		}
		else {
			this.toolingDevFlag="DEVICE";
		}
		
    	if (null == this.accidentBill) {
    		if (this.hasId("accidentBill.id")) {
    			this.accidentBill = this.accidentBillManager.loadAccidentBill(this.getId("accidentBill.id"));
    			if(this.toolingDevFlag.equals("TOOLING")) {
    				if(!this.hasId("tooling.id")) {
    					this.tooling = this.accidentBill.getToolingDev();
    				}
    			} else {
    				if (!this.hasId("device.id")) {
    					this.device = this.accidentBill.getToolingDev();
    				}
    			}
    		} else {
    			this.accidentBill = new AccidentBill();
    		}
    	}
    	
    	if (this.toolingDevFlag.equals("TOOLING")) {
    		this.accidentBill.setToolingDevFlag(SysModel.TOOLING);
    	} else {
    		this.accidentBill.setToolingDevFlag(SysModel.DEVICE);
    	}
    }

	/**
	 * 保存一个事故单信息
	 * @return String SUCCESS 或者 NEW
	 */
    public String save() {
    	boolean isNew = this.accidentBill.isNew();
		if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
			accidentBill.setToolingDev(this.deviceCardManager.loadDevice(this.getId("tooling.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			accidentBill.setToolingDev(this.deviceCardManager.loadDevice(this.getId("device.id")));
		}
    	if (!StringUtils.isEmpty(request.getParameter("manager.id"))) {
    		this.accidentBill.setManager(this.userManager.loadUser(this.getId("manager.id")));
    	}
    	this.accidentBillManager.storeAccidentBill(accidentBill);
    	
		if (isNew) {
			if(accidentBill.getToolingDevFlag()==SysModel.TOOLING){
				this.addActionMessage(this.getText("toolingAccidentBill.add.success", Arrays
						.asList(new Object[] { accidentBill.getBillName() })));
			}else{
				this.addActionMessage(this.getText("deviceAccidentBill.add.success", Arrays
						.asList(new Object[] { accidentBill.getBillName() })));
			}
			return NEW;
		} else {
			if(accidentBill.getToolingDevFlag()==SysModel.DEVICE){
				this.addActionMessage(this.getText("toolingAccidentBill.edit.success", Arrays
						.asList(new Object[] { accidentBill.getBillName() })));
			}else{
				this.addActionMessage(this.getText("deviceAccidentBill.edit.success", Arrays
						.asList(new Object[] { accidentBill.getBillName() })));
			}
			return SUCCESS;
		}
    }
    
	public DeviceCard getTooling() {
		return tooling;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public AccidentBill getAccidentBill() {
		return accidentBill;
	}

	public void setAccidentBill(AccidentBill accidentBill) {
		this.accidentBill = accidentBill;
	}


}
