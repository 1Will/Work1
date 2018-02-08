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
package com.yongjun.tdms.presentation.webwork.action.base.document;

import java.util.Arrays;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

/**
 * <p>Title: EditApplicationDocAction
 * <p>Description: 制度文档访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: EditApplicationDocAction.java 9887 2007-12-28 03:11:36Z qsun $
 */
public class EditApplicationDocAction extends FileTransportAction {
	private static final long serialVersionUID = 8713980566688885805L;
	private final DeviceCardManager deviceCardManager;
	private final ToolingChangeBillManager toolingChangeBillManager;
    private final ApplicationDocManager applicationDocManager;
    private ApplicationDoc applicationDoc;
    private final FileTransportManager fileTransportManager;
    private DeviceCard device;						//设备台帐的技术资料所关联的设备
    private ToolingChangeBill toolingChangeBill;	//工装变更单的技术资料所关联的变更单
    
    public EditApplicationDocAction(DeviceCardManager deviceCardManager,
    		ToolingChangeBillManager toolingChangeBillManager,
    		ApplicationDocManager applicationDocManager,
    		FileTransportManager fileTransportManager) {
    	this.deviceCardManager = deviceCardManager;
    	this.toolingChangeBillManager = toolingChangeBillManager;
    	this.applicationDocManager = applicationDocManager;
    	this.fileTransportManager = fileTransportManager;
    }
    
	public void prepare() throws Exception {
		if (this.hasId("toolingChangeBill.id")) {
			this.toolingChangeBill = this.toolingChangeBillManager.loadToolingChangeBill(this.getId("toolingChangeBill.id"));
		}
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		if (null == this.applicationDoc) {
			if (this.hasId("doc.id")) {
				this.applicationDoc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			} else {
				this.applicationDoc = new ApplicationDoc();
			}
		}
	}
	public String save() throws Exception {
		boolean isNew = this.applicationDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		applicationDoc.setFileName(orgFileName);
		applicationDoc.setPosition(location);
		if (this.hasId("toolingChangeBill.id")){
			applicationDoc.setChangeBill(toolingChangeBill);		//工装变更单的技术资料上传时，保存变更单ID
		}
		if (this.hasId("device.id")) {
			applicationDoc.setDevice(device);						//设备台帐的技术资料上传时，保存设备ID
		}
		
		this.applicationDocManager.storeApplicationDoc(applicationDoc);
		
		if (isNew) {
			this.addActionMessage(this.getText("toolingChangeDoc.add.success", Arrays
					.asList(new Object[] { applicationDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
					.asList(new Object[] { applicationDoc.getName() })));
			return SUCCESS;
		}
	}
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("doc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
		}
		if (this.hasId("toolingChangeDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("toolingChangeDoc.id"));
			doc.setFileNo(applicationDoc.getFileNo());				//工装变更单的技术资料上传时,要求保存文件编号
		}
		doc.setName(applicationDoc.getName());
		doc.setDescription(applicationDoc.getDescription());
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("deviceDoc.edit.success", Arrays
				.asList(new Object[] { applicationDoc.getName() })));
		
		return SUCCESS;
	}

	public ApplicationDoc getApplicationDoc() {
		return applicationDoc;
	}

	public void setApplicationDoc(ApplicationDoc applicationDoc) {
		this.applicationDoc = applicationDoc;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public ToolingChangeBill getToolingChangeBill() {
		return toolingChangeBill;
	}

	public void setToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.toolingChangeBill = toolingChangeBill;
	}

	
}
