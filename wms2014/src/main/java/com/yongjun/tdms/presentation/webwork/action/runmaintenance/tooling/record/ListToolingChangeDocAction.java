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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

/**
 * @author qs
 * @version $Id: ListToolingChangeDocAction.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class ListToolingChangeDocAction extends FileTransportAction {
	private static final long serialVersionUID = -1104105155991390145L;

	private final Log log = LogFactory.getLog(getClass());

	private final ToolingChangeBillManager toolingChangeBillManager;

	private final ApplicationDocManager applicationDocManager;

	private final FileTransportManager fileTransportManager;

	private ToolingChangeBill toolingChangeBill;

	private List<ApplicationDoc> toolingChangeDocs;

	private ApplicationDoc toolingChangeDoc;

	public ListToolingChangeDocAction(
			ToolingChangeBillManager toolingChangeBillManager,
			ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.toolingChangeBillManager = toolingChangeBillManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("toolingChangeBill.id")) {
			this.toolingChangeBill = this.toolingChangeBillManager
					.loadToolingChangeBill(this.getId("toolingChangeBill.id"));
			log.debug("device.doc size is "
					+ toolingChangeBill.getChangeDoc().size());
		}
		if (this.hasId("toolingChangeDocIds")) {
			this.toolingChangeDocs = this.applicationDocManager
					.loadAllApplicationDocs(this.getIds("toolingChangeDocIds"));
		}
		if (this.hasId("doc.id")) {
			toolingChangeDoc = this.applicationDocManager
					.loadApplicationDoc(this.getId("doc.id"));
			this.download();
		}

		if (isDelete()) {
			this.delete();
		}
	}

	private void delete() {
		for (Iterator iter = toolingChangeDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc) iter.next();
			this.fileTransportManager.delete(request, doc.getPosition());
		}
		this.toolingChangeBill.getChangeDoc().removeAll(toolingChangeDocs);
		this.toolingChangeBillManager.storeToolingChangeBill(toolingChangeBill);
		this.addActionMessage(this.getText("toolingChangeDoc.delete.success"));

	}

	public String download() {
		fileTransportManager.download(request, response, toolingChangeDoc
				.getFileName(), toolingChangeDoc.getPosition());
		return null;
	}

	public ToolingChangeBill getToolingChangeBill() {
		return toolingChangeBill;
	}

	public void setToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.toolingChangeBill = toolingChangeBill;
	}
}
