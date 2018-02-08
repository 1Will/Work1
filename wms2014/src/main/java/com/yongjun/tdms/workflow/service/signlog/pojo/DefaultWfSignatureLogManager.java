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
package com.yongjun.tdms.workflow.service.signlog.pojo;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.workflow.dao.signlog.SignatureLogDao;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.model.signlog.SignatureLog;
import com.yongjun.tdms.workflow.service.signlog.WfSignatureLogManager;

/**
 * @author qs
 * @version $Id: DefaultWfSignatureLogManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class DefaultWfSignatureLogManager extends BaseManager implements
		WfSignatureLogManager {
	private final SignatureLogDao signatureLogDao;
	
	public DefaultWfSignatureLogManager(SignatureLogDao signatureLogDao) {
		this.signatureLogDao = signatureLogDao;
	}
	
	public void makeLog(Job job, DocState docState, User user) {
		SignatureLog signatureLog = new SignatureLog();
		signatureLog.setJob(job);
		signatureLog.setState(docState.getCode());
		signatureLog.setApprover(user);
		signatureLog.setDocText("--");
		
		signatureLogDao.storeSignatureLog(signatureLog);
	}

}
