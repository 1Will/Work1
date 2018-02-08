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
package com.yongjun.tdms.presentation.webwork.action.base.information;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.information.MsgManager;

/**
 * @author qs
 * @version $Id: MsglistAction.java 10841 2008-02-01 02:15:51Z qsun $
 */
public class MsglistAction extends ValueListAction {
	private static final long serialVersionUID = -494536594168404683L;

	private final MsgManager msgManager;

	private List msgs;

	public List getMsgs() {
		return msgs;
	}

	public void setMsgs(List msgs) {
		this.msgs = msgs;
	}

	public MsglistAction(MsgManager msgManager) {
		this.msgManager = msgManager;

	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (this.msgs == null && this.hasIds("msgIds")) {
			this.msgs = this.msgManager.loadAllMsgs(this.getIds("msgIds"));
		}
	}

	@Override
	protected String getAdapterName() {
		return "msgs";
	}

	@SuppressWarnings("unchecked")
	private void delete() {
		this.msgManager.deleteAllMsgs(this.msgs);
		this.addActionMessage(this.getText("msg.delete.success"));
	}
}
