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

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.AccessoryDevice;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.AccessoryDeviceManager;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * <p>Title: ListAccessoryDeviceAction
 * <p>Description: 附属设备访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListAccessoryDeviceAction.java 11417 2008-03-18 10:43:51Z wzou $
 */
public class ListAccessoryDeviceAction extends ValueListAction{
	private static final long serialVersionUID = -7537829046086029525L;
	private final DeviceCardManager deviceCardManager;
	private final AccessoryDeviceManager accessoryDeviceManager;
	
	private DeviceCard device;
	private List<AccessoryDevice> accessoryDevice;
	
	public ListAccessoryDeviceAction(DeviceCardManager deviceCardManager, 
							AccessoryDeviceManager accessoryDeviceManager) {
		this.deviceCardManager = deviceCardManager;
		this.accessoryDeviceManager = accessoryDeviceManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this
					.getId("device.id"));
		}
        
		if (this.hasId("accessoryDeviceIds")) {
			this.accessoryDevice = this.accessoryDeviceManager
					.loadAllAccessoryDevice(this.getIds("accessoryDeviceIds"));
		}
		this.setFirst(false);
	}

	public String execute() {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public void delete() {
		this.accessoryDeviceManager.deleteAllAccessoryDevice(this.accessoryDevice);
		this.addActionMessage(this.getText("accessoryDevice.delete.success"));
	}

	public DeviceCard getDevice() {
		return device;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("device.id",this.getId("device.id"));	
		return map;
	}
	

	public List<AccessoryDevice> getAccessoryDevice() {
		return accessoryDevice;
	}

	public void setAccessoryDevice(List<AccessoryDevice> accessoryDevice) {
		this.accessoryDevice = accessoryDevice;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	@Override
	protected String getAdapterName() {
		return "accessoryDevices";
	}

}
