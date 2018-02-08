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

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * @author qs
 * @version $Id: ListDocumentAction.java 9887 2007-12-28 03:11:36Z qsun $
 */
public class ListDocumentAction extends ValueListAction  {
	private static final long serialVersionUID = -8619316678497582172L;
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final CodeValueManager codeValueManager;
	private ApplicationDoc applicationDoc;
	private List<ApplicationDoc> applicationDocs;
	
	public ListDocumentAction(ApplicationDocManager applicationDocManager, 
			                  FileTransportManager fileTransportManager, 
			                  CodeValueManager codeValueManager ) {
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("document.id")) {
			this.applicationDoc = this.applicationDocManager.loadApplicationDoc(this.getId("document.id"));
			this.download();
		}
		
		if (this.hasIds("documentIds")) {
			this.applicationDocs = this.applicationDocManager.loadAllApplicationDocs(this.getIds("documentIds"));
		}
		
	}
	
	public String execute() {
		if(this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public void delete() {
		for (Iterator iter = applicationDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc)iter.next();
			this.fileTransportManager.delete(request, doc.getPosition());
			this.applicationDocManager.deleteApplicationDoc(doc);
		}
		
		 this.addActionMessage(this.getText("document.delete.success"));
	}
	
	public String download() {
		this.fileTransportManager.download(request, response, applicationDoc.getFileName(), applicationDoc.getPosition());
		return null;
	}


	public ApplicationDoc getApplicationDoc() {
		return applicationDoc;
	}

	public void setApplicationDoc(ApplicationDoc applicationDoc) {
		this.applicationDoc = applicationDoc;
	}

	public List getCategorys() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"),CodeConstants.DOCUMENT_TYPE);
	}
	
	@Override
	protected String getAdapterName() {
		return "documents";
	}

}
