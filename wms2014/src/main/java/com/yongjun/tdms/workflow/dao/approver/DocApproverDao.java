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
package com.yongjun.tdms.workflow.dao.approver;

import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.workflow.model.approver.DocApprover;
import com.yongjun.tdms.workflow.model.doctype.DocType;

/**
 * @author qs
 * @version $Id: DocApproverDao.java 7925 2007-10-22 02:37:55Z qsun $
 */
public interface DocApproverDao {
	void storeApprover(DocApprover docApprover);
	DocApprover loadDocApprover(Long id);
	List<User> loadAllApprovers(DocType docType, boolean onlyFinalApprover);
}
