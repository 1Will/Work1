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
package com.yongjun.tdms.workflow.presentation.webwork.action.approver;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;

/**
 * @author qs
 * @version $Id: ListApproverAction.java 10687 2008-01-23 09:49:08Z wzou $
 */
public class ListApproverAction extends PrepareAction {
	private static final long serialVersionUID = -2454934510933358694L;
	private List<User> approvers;	
	private final WfDocApproverManager wfDocApproverManager;
	
	public ListApproverAction(WfDocApproverManager wfDocApproverManager) {
		this.wfDocApproverManager = wfDocApproverManager;
	}
	
	public void prepare() throws Exception {
		String clzId = request.getParameter("wfDoc.id");
		System.out.println("clzId==="+clzId);
		if (!StringUtils.isEmpty(clzId)) {
			approvers = wfDocApproverManager.loadAllApprovers(Class.forName(clzId));
		}
	}
	
	public List<User> getApprovers() {
		return approvers;
	}
	
	public void setApprovers(List<User> approvers) {
		this.approvers = approvers;
	}

}
