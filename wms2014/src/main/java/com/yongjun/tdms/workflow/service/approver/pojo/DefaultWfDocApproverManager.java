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
package com.yongjun.tdms.workflow.service.approver.pojo;

import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.workflow.dao.approver.DocApproverDao;
import com.yongjun.tdms.workflow.dao.doctype.DocTypeDao;
import com.yongjun.tdms.workflow.model.approver.DocApprover;
import com.yongjun.tdms.workflow.model.doctype.DocType;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;

/**
 * @author qs
 * @version $Id: DefaultWfDocApproverManager.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class DefaultWfDocApproverManager extends BaseManager implements
		WfDocApproverManager {
	private final DocApproverDao docApproverDao;
	private final DocTypeDao docTypeDao;
	
	public DefaultWfDocApproverManager(DocApproverDao docApproverDao, 
			DocTypeDao docTypeDao) {
		this.docApproverDao = docApproverDao;
		this.docTypeDao = docTypeDao;
	}
	public DocApprover loadDocApprover(Long id){
		 return docApproverDao.loadDocApprover(id);
	}
	public void storeApprover(DocApprover docApprover) {
		docApproverDao.storeApprover(docApprover);
	}
	public <T> List<User> loadAllFinalApprovers(Class<T> clz) {
		String clzId = clz.getName();
		DocType docType = docTypeDao.loadDocTypeByCode(clzId);
		return docApproverDao.loadAllApprovers(docType, true);
	}

	public <T> List<User> loadAllApprovers(Class<T> clz) {
		String clzId = clz.getName();
		DocType docType = docTypeDao.loadDocTypeByCode(clzId);
		return docApproverDao.loadAllApprovers(docType, false);
	}

}
