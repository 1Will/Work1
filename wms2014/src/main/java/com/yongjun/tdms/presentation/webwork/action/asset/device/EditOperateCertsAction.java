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
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.OperateCert;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.OperaterCertsManager;

/**
 * @author wzou
 * @version $Id:$
 */
public class EditOperateCertsAction extends PrepareAction {

	private static final long serialVersionUID = 1122728208212809126L;
	private final OperaterCertsManager operaterCertsManager;
	private final DeviceCardManager deviceCardManager;
	private OperateCert operateCert;
	private DeviceCard device;
	
	public EditOperateCertsAction(OperaterCertsManager operaterCertsManager,
			DeviceCardManager deviceCardManager){
		this.operaterCertsManager = operaterCertsManager;
		this.deviceCardManager = deviceCardManager;
	}
	public void prepare() throws Exception {
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			}
		}
		
		if(null == this.operateCert) {
			if(this.hasId("operateCert.id")) {
				this.operateCert = this.operaterCertsManager.loadOperateCert(this.getId("operateCert.id"));
			} else {
				this.operateCert = new OperateCert();
			}
		}
	}
	
	public String save() {
		boolean isNew = this.operateCert.isNew();
		operateCert.setDevice(device);
		
		this.operaterCertsManager.storeOperateCert(operateCert);
		
		if (isNew) {
			this.addActionMessage(this.getText("operateCert.add.success", Arrays
					.asList(new Object[] { operateCert.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("operateCert.edit.success", Arrays
					.asList(new Object[] { operateCert.getName() })));
			return SUCCESS;
		}
	}
	public DeviceCard getDevice() {
		return device;
	}
	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	public OperateCert getOperateCert() {
		return operateCert;
	}
	public void setOperateCert(OperateCert operateCert) {
		this.operateCert = operateCert;
	}

}
