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

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceArgs;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.DeviceArgsManager;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * @author qs
 * @version $Id: ListDeviceArgsAction.java 11246 2008-03-10 11:35:30Z wzou $
 */
public class ListDeviceArgsAction extends ValueListAction{
	private static final long serialVersionUID = 8493003445110774602L;
	private final DeviceArgsManager deviceArgsManager;
	private final DeviceCardManager deviceCardManager;
	private List<DeviceArgs> deviceArgs;
	private DeviceCard device;
	
	public ListDeviceArgsAction(DeviceArgsManager deviceArgsManager, DeviceCardManager deviceCardManager) {
		this.deviceArgsManager = deviceArgsManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	public void prepare() {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this
					.getId("device.id"));
		}

		if (null == this.deviceArgs && this.hasId("deviceArgsIds")) {
			this.deviceArgs = this.deviceArgsManager.loadAllDeviceArgs(this
					.getIds("deviceArgsIds"));
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isDelete()) {
			this.delete();
		}
		
		return SUCCESS;
	}
	
	public void delete() {
		this.deviceArgsManager.deleteAllDeviceArgs(deviceArgs);
		this.addActionMessage(this.getText("deviceArgs.delete.success"));
	}

	public DeviceCard getDevice() {
		return device;
	}

	public List<DeviceArgs> getDeviceArgs() {
		return deviceArgs;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("device.id",this.getId("device.id"));	
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "deviceArgs";
	}

}
