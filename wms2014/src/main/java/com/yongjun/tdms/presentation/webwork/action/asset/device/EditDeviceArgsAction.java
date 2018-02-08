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
import com.yongjun.tdms.model.asset.device.DeviceArgs;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.DeviceArgsManager;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * @author qs
 * @version $Id: EditDeviceArgsAction.java 7953 2007-10-23 05:58:17Z qsun $
 */
public class EditDeviceArgsAction extends PrepareAction {
	private static final long serialVersionUID = 1099465544096220217L;
	private final DeviceArgsManager deviceArgsManager;
	private final DeviceCardManager deviceCardManager;
	private DeviceArgs deviceArgs;
	private DeviceCard device;
	
	public EditDeviceArgsAction(DeviceArgsManager deviceArgsManager, DeviceCardManager deviceCardManager){
		this.deviceArgsManager = deviceArgsManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			}
		}
		
		if (null == this.deviceArgs) {
			if (this.hasId("deviceArgs.id")) {
				this.deviceArgs = this.deviceArgsManager.loadDeviceArgs(this.getId("deviceArgs.id"));
			} else {
				this.deviceArgs = new DeviceArgs();
			}
			
		}
	}
	
	public String save() {
		boolean isNew = this.deviceArgs.isNew();
		deviceArgs.setDevice(device);
		
		this.deviceArgsManager.storeDeviceArgs(deviceArgs);
		
		if (isNew) {
			this.addActionMessage(this.getText("deviceArgs.add.success", Arrays
					.asList(new Object[] { deviceArgs.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("deviceArgs.edit.success", Arrays
					.asList(new Object[] { deviceArgs.getName() })));
			return SUCCESS;
		}
	}

	public DeviceArgs getDeviceArgs() {
		return deviceArgs;
	}

	public void setDeviceArgs(DeviceArgs deviceArgs) {
		this.deviceArgs = deviceArgs;
	}

	public DeviceCard getDevice() {
		return device;
	}
}
