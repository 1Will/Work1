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

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * @author qs
 * @version $Id: EditDeviceDocAction.java 10255 2008-01-08 08:58:27Z wzou $
 */
public class EditDeviceDocAction extends FileTransportAction {
	private static final long serialVersionUID = -2706192037102232098L;
	private final ApplicationDocManager applicationDocManager;
	private final DeviceCardManager deviceCardManager;
	private final FileTransportManager fileTransportManager;
	private ApplicationDoc deviceDoc;
	private DeviceCard device;
	private String planProcFlag;                    //标识为是计划,还是实施
	
	public EditDeviceDocAction(ApplicationDocManager applicationDocManager,
			DeviceCardManager deviceCardManager,
			FileTransportManager fileTransportManager) {
		this.applicationDocManager = applicationDocManager;
		this.deviceCardManager = deviceCardManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		if (null == this.deviceDoc) {
			if (this.hasId("doc.id")) {
				this.deviceDoc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			} else {
				deviceDoc = new ApplicationDoc();
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			delete();
		} 
		return SUCCESS;
	}
	
	public String save() throws Exception {
		
		boolean isNew = this.deviceDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		deviceDoc.setFileName(orgFileName);           //设置文件原始的名字
		deviceDoc.setPosition(location);            //设置文件在服务器上的名字，GUUID
		deviceDoc.setDevice(device);
		deviceDoc.setFileNo(location);       //设置默认的技术文件编号
		this.applicationDocManager.storeApplicationDoc(deviceDoc);
//		try{
//			this.applicationDocManager.storeApplicationDoc(deviceDoc);
//		}catch(Exception e) {
//			this.addActionMessage(this.getText("deviceDoc.add.error", Arrays
//					.asList(new Object[] { deviceDoc.getName() })));
//			return SUCCESS;
//		}
		
		if (isNew) {
			this.addActionMessage(this.getText("deviceDoc.add.success", Arrays
					.asList(new Object[] { deviceDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("deviceDoc.edit.success", Arrays
					.asList(new Object[] { deviceDoc.getName() })));
			return SUCCESS;
		}
	}
	
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("deviceDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("deviceDoc.id"));
		}
		doc.setName(deviceDoc.getName());
		doc.setDescription(deviceDoc.getDescription());
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("deviceDoc.edit.success", Arrays
				.asList(new Object[] { deviceDoc.getName() })));
		
		return SUCCESS;
	}
	
	//TODO: 删除文件
	public void delete() {
		this.applicationDocManager.deleteApplicationDoc(this.deviceDoc);
	}

	public ApplicationDoc getDeviceDoc() {
		return deviceDoc;
	}

	public void setDeviceDoc(ApplicationDoc deviceDoc) {
		this.deviceDoc = deviceDoc;
	}

	public DeviceCard getDevice() {
		return this.device;
	}
	
	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}
}
