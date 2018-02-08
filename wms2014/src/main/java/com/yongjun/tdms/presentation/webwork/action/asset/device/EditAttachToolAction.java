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
import com.yongjun.tdms.model.asset.device.AttachTool;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.AttachToolManager;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * @author qs
 * @version $Id: EditAttachToolAction.java 7953 2007-10-23 05:58:17Z qsun $
 */
public class EditAttachToolAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
	private final AttachToolManager attachToolManager;
	private final DeviceCardManager deviceCardManager;
	private AttachTool attachTool;
	private DeviceCard device;
	
	public EditAttachToolAction(AttachToolManager attachToolManager, DeviceCardManager deviceCardManager) {
		this.attachToolManager = attachToolManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	public void prepare() throws Exception {
		if (null == attachTool) {
			if (this.hasId("attachTool.id")) {
				this.attachTool = this.attachToolManager.loadAttachTool(this.getId("attachTool.id"));
			}
			else {
				this.attachTool = new AttachTool();
			}
		}
		
		if (null == device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			}
		}
	}
	
	public String save() {
		boolean isNew = this.attachTool.isNew();
		attachTool.setDevice(device);
		
		this.attachToolManager.storeAttachTool(attachTool);
		
		if (isNew) {
			this.addActionMessage(this.getText("attachTool.add.success", Arrays
					.asList(new Object[] { attachTool.getName() })));

			return NEW;
		} else {			
			this.addActionMessage(this.getText("attachTool.edit.success", Arrays
					.asList(new Object[] { attachTool.getName() })));
			return SUCCESS;
		}
	}

	public void setAttachTool(AttachTool attachTool) {
		this.attachTool = attachTool;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public AttachTool getAttachTool() {
		return attachTool;
	}

}
