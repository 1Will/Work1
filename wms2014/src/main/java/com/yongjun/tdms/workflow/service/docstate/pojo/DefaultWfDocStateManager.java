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
package com.yongjun.tdms.workflow.service.docstate.pojo;

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.workflow.dao.docstate.DocStateDao;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * @author qs
 * @version $Id: DefaultWfDocStateManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class DefaultWfDocStateManager extends BaseManager implements
		WfDocStateManager {
	private final DocStateDao docStateDao;
	private String docStateSubmitting;
	private String docStateSubmitted;
	private String docStateApproving;
	private String docStateApproved;
	private String docStateRejected;
	
	public DefaultWfDocStateManager(DocStateDao docStateDao) {
		this.docStateDao = docStateDao;
	}
	
	public List<DocState> createSelectDocStates(String label) {
		DocState docState = new DocState();
		docState.setId(-1L);
		docState.setValue(label);
		List<DocState> tmpList = this.docStateDao.loadAllDocStates();
		tmpList.add(0, docState);
		return tmpList;
	}

	public List<DocState> createSelectDocStatesForApprover(String label) {
		return this.createSelectDocStates(label);
	}
	
	public String getDocStateApproving() {
		return docStateApproving;
	}

	public void setDocStateApproving(String docStateApproving) {
		this.docStateApproving = docStateApproving;
	}

	public String getDocStateRejected() {
		return docStateRejected;
	}

	public void setDocStateRejected(String docStateRejected) {
		this.docStateRejected = docStateRejected;
	}

	public String getDocStateSubmitted() {
		return docStateSubmitted;
	}

	public void setDocStateSubmitted(String docStateSubmitted) {
		this.docStateSubmitted = docStateSubmitted;
	}

	public String getDocStateSubmitting() {
		return docStateSubmitting;
	}

	public String getDocStateApproved() {
		return docStateApproved;
	}

	public void setDocStateApproved(String docStateApproved) {
		this.docStateApproved = docStateApproved;
	}

	public void setDocStateSubmitting(String docStateSubmitting) {
		this.docStateSubmitting = docStateSubmitting;
	}

	public DocState loadDocStateByCode(String stateCode) {
		return docStateDao.loadDocStateByCode(stateCode);
	}
}
