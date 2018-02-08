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

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDeviceDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDetailManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDeviceDetailManager;

/**
 * <p>Title: EditDeviceMaintenanceDetailAction
 * <p>Description: 设备保养详细访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public class EditDeviceMaintenanceDetailAction extends PrepareAction {
	private static final long serialVersionUID = 224202462379545833L;
	
	private final MaintenanceDeviceDetailManager maintenanceDeviceDetailManager;
	private final MaintenanceDetailManager maintenanceDetailManager;
	private final DeviceCardManager deviceCardManager;
	
	private MaintenanceDetail detail;
	private DeviceCard device;
	private MaintenanceDeviceDetail deviceMaintenanceDetail;
	
	public EditDeviceMaintenanceDetailAction(MaintenanceDeviceDetailManager maintenanceDeviceDetailManager,
			MaintenanceDetailManager maintenanceDetailManager,
			DeviceCardManager deviceCardManager) {
		this.maintenanceDeviceDetailManager = maintenanceDeviceDetailManager;
		this.maintenanceDetailManager = maintenanceDetailManager;
		this.deviceCardManager = deviceCardManager;
	}
	public void prepare() throws Exception {
		if (this.deviceMaintenanceDetail == null) {
			if (this.hasId("deviceMaintenanceDetail.id")){
				this.deviceMaintenanceDetail = this.maintenanceDeviceDetailManager.loadMaintenanceDeviceDetail(this
						.getId("deviceMaintenanceDetail.id"));
			}else {
				this.deviceMaintenanceDetail = new MaintenanceDeviceDetail();
			}
		}
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			}
		}
		if (this.hasId("detail.id")) {
			this.detail = this.maintenanceDetailManager.loadMaintenanceDetail(this.getId("detail.id"));
		}
	}
	
	public String save(){
		boolean isNew = this.deviceMaintenanceDetail.isNew();
		
		this.deviceMaintenanceDetail.setMaintenanceDetail(detail);
		this.deviceMaintenanceDetail.setDevice(this.device);
		this.maintenanceDeviceDetailManager.storeMaintenanceDeviceDetail(deviceMaintenanceDetail);
		
		if (isNew) {
			this.addActionMessage(this.getText("deviceMaintenanceDetail.add.success"));
			return NEW;
		} else {       
			this.addActionMessage(this.getText("deviceMaintenanceDetail.edit.success"));
			return SUCCESS;
		}
	}
	public MaintenanceDetail getDetail() {
		return detail;
	}
	public void setDetail(MaintenanceDetail detail) {
		this.detail = detail;
	}
	public MaintenanceDeviceDetail getDeviceMaintenanceDetail() {
		return deviceMaintenanceDetail;
	}
	public void setDeviceMaintenanceDetail(
			MaintenanceDeviceDetail deviceMaintenanceDetail) {
		this.deviceMaintenanceDetail = deviceMaintenanceDetail;
	}
	public DeviceCard getDevice() {
		return device;
	}
	public void setDevice(DeviceCard device) {
		this.device = device;
	}

}
