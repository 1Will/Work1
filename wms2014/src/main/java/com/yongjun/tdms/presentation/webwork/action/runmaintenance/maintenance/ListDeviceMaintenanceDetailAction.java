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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDeviceDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDetailManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDeviceDetailManager;

/**
 * <p>Title: ListDeviceMaintenanceDetailAction
 * <p>Description: 设备保养详细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditMaintenanceAction.java 10972 2008-02-17 03:05:50Z wzou $
 */
public class ListDeviceMaintenanceDetailAction extends ValueListAction {
	private static final long serialVersionUID = 224202462379545833L;
	
	private final MaintenanceDetailManager maintenanceDetailManager;
	private final MaintenanceDeviceDetailManager maintenanceDeviceDetailManager;
	private final DeviceCardManager deviceCardManager;
	
	private MaintenanceDetail detail;
	private DeviceCard device;
	private List<MaintenanceDeviceDetail> maintenanceDeviceDetails;
	private String planProcFlag;                   //计划或实施标识	
	public ListDeviceMaintenanceDetailAction(MaintenanceDetailManager maintenanceDetailManager,
			MaintenanceDeviceDetailManager maintenanceDeviceDetailManager,
			DeviceCardManager deviceCardManager) {
		this.maintenanceDetailManager = maintenanceDetailManager;
		this.maintenanceDeviceDetailManager = maintenanceDeviceDetailManager;
		this.deviceCardManager = deviceCardManager;
	}
	public void prepare() throws Exception {
		if (null == this.detail) {
			if(this.hasId("maintenanceDetail.id")) {
				this.detail = this.maintenanceDetailManager.loadMaintenanceDetail(this.getId("maintenanceDetail.id"));
			}
		}
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(Long.valueOf(request.getParameter("device.id").replace(",", "")));
			}
		}
		if (this.hasIds("deviceMaintenanceDetailIds")) {
			this.maintenanceDeviceDetails = this.maintenanceDeviceDetailManager.
				loadAllMaintenanceDeviceDetails(this.getIds("deviceMaintenanceDetailIds"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			this.maintenanceDeviceDetailManager.deleteMaintenanceDeviceDetails(maintenanceDeviceDetails);
		} catch (Exception e) {
			this.addActionMessage(this.getText("maintenanceDeviceDetails.delete.error"));
		}
		this.addActionMessage(this.getText("maintenanceDeviceDetails.delete.success"));
		return SUCCESS; 
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("maintenanceDetail.id")){
        		map.put("detail.id", this.getId("maintenanceDetail.id"));
		}
		return map;
	}
	@Override
	protected String getAdapterName() {
		return "deviceMaintenanceDetail";
	}
	public MaintenanceDetail getDetail() {
		return detail;
	}
	public void setDetail(MaintenanceDetail detail) {
		this.detail = detail;
	}
	public List<MaintenanceDeviceDetail> getMaintenanceDeviceDetails() {
		return maintenanceDeviceDetails;
	}
	public void setMaintenanceDeviceDetails(
			List<MaintenanceDeviceDetail> maintenanceDeviceDetails) {
		this.maintenanceDeviceDetails = maintenanceDeviceDetails;
	}
	public String getPlanProcFlag() {
		return planProcFlag;
	}
	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}
	public DeviceCard getDevice() {
		return device;
	}
	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	
}
