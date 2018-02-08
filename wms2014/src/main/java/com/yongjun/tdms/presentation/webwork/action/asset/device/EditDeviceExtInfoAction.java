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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceExtInfo;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceExtInfoManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author qs
 * @version $Id: EditDeviceExtInfoAction.java 11436 2008-03-18 14:16:50Z wzou $
 */
public class EditDeviceExtInfoAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
//	private Log log = LogFactory.getLog(this.getClass());
//	private final DeviceExtInfoManager deviceExtInfoManager;
//	private final DeviceCardManager deviceCardManager;
//	private final CodeValueManager codeValueManager;
//	private DeviceExtInfo deviceExtInfo;
//	private DeviceCard device;
//	
//	public EditDeviceExtInfoAction(DeviceExtInfoManager deviceExtInfoManager, 
//			DeviceCardManager deviceCardManager, 
//			CodeValueManager codeValueManager) {
//		this.deviceExtInfoManager = deviceExtInfoManager;
//		this.deviceCardManager = deviceCardManager;
//		this.codeValueManager = codeValueManager;
//	}
//
//	public void prepare() throws Exception {
//		if (null == this.device) {
//			if (this.hasId("device.id")){
//				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
//				log.debug("device assert No. is : " + device.getAssetNo());
//			}
//		}
//		
//		if (null == this.deviceExtInfo) {
//			if (this.hasId("deviceExtInfo.id")) {
//				this.deviceExtInfo = this.deviceExtInfoManager.loadDeviceExtInfo(this.getId("deviceExtInfo.id"));
//			} else if (this.device.hasExtInfo()) {
//				this.deviceExtInfo = this.device.getDeviceExtensionInfo();
//			} else {
//				this.deviceExtInfo = new DeviceExtInfo();
//			}
//		}
//	}
//
//	public String save() {
//		boolean isNew = this.deviceExtInfo.isNew();
//		this.deviceExtInfo.setDevice(device);
//		
//		if (!StringUtils.isEmpty(request.getParameter("country.name"))) {
//			this.deviceExtInfo.setMadeIn(request.getParameter("country.name"));
//		}
//		
//		
//		this.deviceExtInfoManager.storeDeviceExtInfo(deviceExtInfo);
//		
//		if (isNew) {
//			this.addActionMessage(this.getText("deviceExtInfo.add.success", Arrays
//					.asList(new Object[] { deviceExtInfo.getDevice().getName() })));
//
//			return NEW;
//		} else {
//			this.addActionMessage(this.getText("deviceExtInfo.edit.success", Arrays
//					.asList(new Object[] { deviceExtInfo.getDevice().getName() })));
//			return SUCCESS;
//		}
//	}
//	
//	public DeviceExtInfo getDeviceExtInfo() {
//		return deviceExtInfo;
//	}
//
//	public void setDeviceExtInfo(DeviceExtInfo deviceExtInfo) {
//		this.deviceExtInfo = deviceExtInfo;
//	}
//
//	public DeviceCard getdevice() {
//		return device;
//	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
