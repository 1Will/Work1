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
package com.yongjun.tdms.service.base.document.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.document.DocumentDao;
import com.yongjun.tdms.model.base.document.Document;
import com.yongjun.tdms.service.base.document.DocumentManager;

/**
 * @author qs
 * @version $Id: DefaultDocumentManager.java 8356 2007-11-16 04:03:06Z zbzhang $
 */
public class DefaultDocumentManager extends BaseManager implements DocumentManager{
	private final DocumentDao documentDao;
	
	public DefaultDocumentManager(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public Document loadDocument(Long documentId) {
		return this.documentDao.loadDocument(documentId);
	}

	public List<Document> loadAllDocuments(Long[] documentIds) {
		return this.documentDao.loadAllDocuments(documentIds);
	}

	public List<Document> loadAllDocuments() {
		return this.documentDao.loadAllDocuments();
	}

	public void storeDocument(Document document) {
		this.documentDao.storeDocument(document);
	}

	public void deleteDocument(Document document) {
		this.documentDao.deleteDocument(document);
	}

	public void deleteAllDocument(Collection<Document> documentIds) {
		this.documentDao.deleteAllDocuments(documentIds);
	}
}
