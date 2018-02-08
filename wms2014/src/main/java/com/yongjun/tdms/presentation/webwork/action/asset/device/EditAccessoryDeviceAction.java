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
package com.yongjun.tdms.presentation.webwork.action.asset.device;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.AccessoryDevice;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.AccessoryDeviceManager;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * @author qs
 * @version $Id: EditAccessoryDeviceAction.java 11194 2008-03-04 12:45:26Z wzou $
 */
public class EditAccessoryDeviceAction extends PrepareAction {
	private static final long serialVersionUID = -7767090472538193260L;
	private final AccessoryDeviceManager accessoryDeviceManager;
	private final DeviceCardManager deviceCardManager;
	private DeviceCard device;
	private AccessoryDevice accessoryDevice;
	
	public EditAccessoryDeviceAction(AccessoryDeviceManager accessoryDeviceManager, 
			DeviceCardManager deviceCardManager){
		this.accessoryDeviceManager = accessoryDeviceManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		
		if (null == this.accessoryDevice) {
			if (this.hasId("accessoryDevice.id")) {
				this.accessoryDevice = this.accessoryDeviceManager.loadAccessoryDevice(this.getId("accessoryDevice.id"));
			} else {
				this.accessoryDevice = new AccessoryDevice();
			}
		}
	}
	
	public String save() {
		boolean isNew = this.accessoryDevice.isNew();
		this.accessoryDevice.setMasterDevice(device);
		
		this.accessoryDeviceManager.storeAccessoryDevice(accessoryDevice);
		
		if (isNew) {
			this.addActionMessage(this.getText("accessoryDevice.add.success", Arrays
					.asList(new Object[] { accessoryDevice.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("accessoryDevice.edit.success", Arrays
					.asList(new Object[] { accessoryDevice.getName() })));
			return SUCCESS;
		}
	}

	public AccessoryDevice getAccessoryDevice() {
		return accessoryDevice;
	}

	public void setAccessoryDevice(AccessoryDevice accessoryDevice) {
		this.accessoryDevice = accessoryDevice;
	}

	public DeviceCard getDevice() {
		return device;
	}

}
