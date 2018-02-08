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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import java.util.Arrays;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

/**
 * @author qs
 * @version $Id: EditToolingChangeDocAction.java 9888 2007-12-28 03:17:44Z qsun $
 */
public class EditToolingChangeDocAction extends FileTransportAction {
	private static final long serialVersionUID = 98780153226250135L;
    private final ToolingChangeBillManager toolingChangeBillManager;
    private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private ToolingChangeBill toolingChangeBill;
	private ApplicationDoc toolingChangeDoc;
	
	public EditToolingChangeDocAction(ToolingChangeBillManager toolingChangeBillManager, 
			ApplicationDocManager applicationDocManager,
			 FileTransportManager fileTransportManager) {
		this.toolingChangeBillManager = toolingChangeBillManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("toolingChangeBill.id")) {
			this.toolingChangeBill = this.toolingChangeBillManager.loadToolingChangeBill(this.getId("toolingChangeBill.id"));
		}
		
		if (null == this.toolingChangeDoc) {
			if (this.hasId("toolingChangeDoc.id")) {
				this.toolingChangeDoc = this.applicationDocManager.loadApplicationDoc(this.getId("toolingChangeDoc.id"));
			} else {
				this.toolingChangeDoc = new ApplicationDoc();
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.toolingChangeDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		toolingChangeDoc.setFileName(orgFileName);
		toolingChangeDoc.setPosition(location);
		toolingChangeDoc.setChangeBill(toolingChangeBill);
		
		this.applicationDocManager.storeApplicationDoc(toolingChangeDoc);
		
		if (isNew) {
			this.addActionMessage(this.getText("toolingChangeDoc.add.success", Arrays
					.asList(new Object[] { toolingChangeDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
					.asList(new Object[] { toolingChangeDoc.getName() })));
			return SUCCESS;
		}
	}
	
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("toolingChangeDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("toolingChangeDoc.id"));
		}
		doc.setName(toolingChangeDoc.getName());
		doc.setDescription(toolingChangeDoc.getDescription());
		doc.setFileNo(toolingChangeDoc.getFileNo());
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
				.asList(new Object[] { toolingChangeDoc.getName() })));
		
		return SUCCESS;
	}

	public ToolingChangeBill getToolingChangeBill() {
		return toolingChangeBill;
	}

	public void setToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.toolingChangeBill = toolingChangeBill;
	}

	
	public ApplicationDoc getToolingChangeDoc() {
		return toolingChangeDoc;
	}

	public void setToolingChangeDoc(ApplicationDoc toolingChangeDoc) {
		this.toolingChangeDoc = toolingChangeDoc;
	}

	public String getFirst() {
		return request.getParameter("first");
	}
}
