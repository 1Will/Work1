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
package com.yongjun.tdms.service.base.document;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.document.Document;

/**
 * @author qs
 * @version $Id: DocumentManager.java 8356 2007-11-16 04:03:06Z zbzhang $
 */
@Transactional(readOnly = true)
public interface DocumentManager {
	Document loadDocument(Long documentId);

	List<Document> loadAllDocuments(Long[] documentIds);
	
	List<Document> loadAllDocuments();
	
	@Transactional
	void storeDocument(Document document);
	
	@Transactional
	void deleteDocument(Document document);
	
	@Transactional
	void deleteAllDocument(Collection <Document> documentIds);
}
