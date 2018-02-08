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
package com.yongjun.tdms.workflow.service.approver;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.workflow.model.approver.DocApprover;

/**
 * @author qs
 * @version $Id: WfDocApproverManager.java 7925 2007-10-22 02:37:55Z qsun $
 */
@Transactional(readOnly=true)
public interface WfDocApproverManager {
	<T> List<User> loadAllFinalApprovers(Class<T> clz);

	<T> List<User> loadAllApprovers(Class<T> clz);
	DocApprover loadDocApprover(Long id);
	void storeApprover(DocApprover docApprover);
}
