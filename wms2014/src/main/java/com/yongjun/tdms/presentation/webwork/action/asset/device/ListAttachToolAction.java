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
import com.yongjun.tdms.model.asset.device.AttachTool;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.AttachToolManager;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * @author qs
 * @version $Id: ListAttachToolAction.java 11246 2008-03-10 11:35:30Z wzou $
 */
public class ListAttachToolAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final AttachToolManager attachToolManager;
	private final DeviceCardManager deviceCardManager;
	private List<AttachTool> attachTools;
	private DeviceCard device;
	
	public ListAttachToolAction(AttachToolManager attachToolManager, DeviceCardManager deviceCardManager) {
		this.attachToolManager = attachToolManager;
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		
		if (null == this.attachTools && this.hasId("attachToolIds")) {
			this.attachTools = this.attachToolManager.loadAllAttachTool(this.getIds("attachToolIds"));
		}
		this.setFirst(false);
	}

	public String execute() {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.attachToolManager.deleteAllAttachTool(attachTools);
		this.addActionMessage(this.getText("attachTool.delete.success"));
		return SUCCESS;
	}
	
	public List<AttachTool> getAttachTools() {
		return attachTools;
	}

	public void setAttachTools(List<AttachTool> attachTools) {
		this.attachTools = attachTools;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("device.id",this.getId("device.id"));	
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "attachTools";
	}
}
