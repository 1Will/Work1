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

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.OperateCert;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.OperaterCertsManager;

public class ListOperateCertAction extends ValueListAction {

	private static final long serialVersionUID = -8954339478107845006L;
	private final OperaterCertsManager operaterCertsManager;
	private final DeviceCardManager deviceCardManager;
	private List<OperateCert> operateCerts;
	private DeviceCard device;
	
	public ListOperateCertAction(OperaterCertsManager operaterCertsManager,
			DeviceCardManager deviceCardManager){
		this.operaterCertsManager = operaterCertsManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	public void prepare() {
		if(this.hasId("device.id")){
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		
		if(null == this.operateCerts && this.hasId("operateCertIds")) {
			this.operateCerts = this.operaterCertsManager.loadAllOperateCert(this.
					getIds("operateCertIds"));
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
		this.operaterCertsManager.deleteAllOperateCert(operateCerts);
		this.addActionMessage(this.getText("operateCerts.delete.success"));
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("device.id")){
        	map.put("device.id", this.getId("device.id"));
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "operateCert";
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	
	
}
