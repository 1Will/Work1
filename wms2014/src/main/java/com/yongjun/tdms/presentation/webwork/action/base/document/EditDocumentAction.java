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
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.base.document.ApplicationDocType;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * <p>Title: EditDocumentAction
 * <p>Description: 编辑文档类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditDocumentAction.java 9820 2007-12-27 03:04:24Z qsun $
 */
public class EditDocumentAction extends FileTransportAction {
	private static final long serialVersionUID = -6437456700598485255L;

	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final CodeValueManager codeValueManager;
	private ApplicationDoc document;

	public EditDocumentAction(ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager,
			CodeValueManager codeValueManager) {
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == this.document) {
			if (this.hasId("document.id")) {      
				this.document = this.applicationDocManager
						.loadApplicationDoc(this.getId("document.id"));
			} else {
				this.document = new ApplicationDoc();
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.document.isNew();

		String location = fileTransportManager.upload(request, getFile(),
				"origFileName");

		String orgFileName = request.getParameter("origFileName");
		document.setFileName(orgFileName);              //设置原文件名
		document.setPosition(location);                 //设置文件在服务器上的GUUID
		document.setFileNo(location);                   //设置技术编号的默认值
		document.setDocFlag(ApplicationDocType.SYSTEM_DOC);    //设置该上传文档为制度文档类型标识
		
		if (!StringUtils.isEmpty(request.getParameter("category.id"))) {
			document.setCategory(codeValueManager.loadCodeValue(this
					.getId("category.id")));
		} else {
			document.setCategory(null);
		}

		this.applicationDocManager.storeApplicationDoc(document);

		if (isNew) {
			this.addActionMessage(this.getText("document.add.success", Arrays
					.asList(new Object[] { document.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("document.edit.success", Arrays
					.asList(new Object[] { document.getName() })));
			return SUCCESS;
		}
	}

	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();

		if (this.hasId("document.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this
					.getId("document.id"));
		}

		doc.setName(document.getName());
		doc.setDescription(document.getDescription());
		if (!StringUtils.isEmpty(request.getParameter("category.id"))) {
			doc.setCategory(codeValueManager.loadCodeValue(this
					.getId("category.id")));
		} else {
			doc.setCategory(null);
		}
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("document.edit.success", Arrays
				.asList(new Object[] { document.getName() })));

		return SUCCESS;
	}

	public List getCategorys() {
		return this.codeValueManager
				.LoadAllValuesByCode(CodeConstants.DOCUMENT_TYPE);
	}

	public ApplicationDoc getDocument() {
		return document;
	}

	public void setDocument(ApplicationDoc document) {
		this.document = document;
	}

}
