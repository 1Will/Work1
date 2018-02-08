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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

public class ListApplicationDocAction extends FileTransportAction {
	private static final long serialVersionUID = -2235227769245194297L;

	private final Log log = LogFactory.getLog(getClass());

	private final DeviceCardManager deviceCardManager; // 设备台帐的技术资料

	private DeviceCard device;

	private final ApplicationDocManager applicationDocManager;

	private final ToolingChangeBillManager toolingChangeBillManager;// 工装变更Service

	private ToolingChangeBill toolingChangeBill; // 工装变更单

	private List<ApplicationDoc> applicationDocs;

	private ApplicationDoc applicationDoc;

	private final FileTransportManager fileTransportManager;

	public ListApplicationDocAction(DeviceCardManager deviceCardManager,
			ToolingChangeBillManager toolingChangeBillManager,
			ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.deviceCardManager = deviceCardManager;
		this.toolingChangeBillManager = toolingChangeBillManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;

	}

	public void prepare() throws Exception {
		if (this.hasId("device.id")) { // 设备台帐的技术资料所关联的设备ID
			this.device = this.deviceCardManager.loadDevice(this
					.getId("device.id"));
			// log.debug("device.doc size is " +
			// device.getApplicationDoc().size());
		}
		if (this.hasId("toolingChangeBill.id")) { // 工装变更的技术资料所关联的变更单ID
			this.toolingChangeBill = this.toolingChangeBillManager
					.loadToolingChangeBill(this.getId("toolingChangeBill.id"));
			log.debug("device.doc size is "
					+ toolingChangeBill.getChangeDoc().size());
		}
		if (this.hasId("applicationDocIds")) {
			this.applicationDocs = this.applicationDocManager
					.loadAllApplicationDocs(this.getIds("applicationDocIds"));
		}
		if (this.hasId("doc.id")) {
			applicationDoc = this.applicationDocManager.loadApplicationDoc(this
					.getId("doc.id"));
			this.download();
		}
		if (isDelete()) {
			this.delete();
		}
	}

	private void delete() {
		for (Iterator iter = applicationDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc) iter.next();
			this.fileTransportManager.delete(request, doc.getPosition());
		}
		applicationDocManager.deleteAllApplicationDocs(applicationDocs);
		this.addActionMessage(this.getText("toolingChangeDoc.delete.success"));
	}

	public String download() {
		log.trace("request fileName is : " + applicationDoc.getFileName());
		fileTransportManager.download(request, response, applicationDoc
				.getFileName(), applicationDoc.getPosition());
		return null;
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
