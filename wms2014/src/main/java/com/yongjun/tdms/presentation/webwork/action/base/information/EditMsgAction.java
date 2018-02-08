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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.information.Msg;
import com.yongjun.tdms.model.base.information.MsgStatus;
import com.yongjun.tdms.model.base.information.MsgType;

import com.yongjun.tdms.service.base.information.MsgManager;

/**
 * @author qs
 * @version $Id: EditMsgAction.java 10841 2008-02-01 02:15:51Z qsun $
 */
@SuppressWarnings("serial")
public class EditMsgAction extends PrepareAction {
	private final MsgManager msgManager;

	private final UserManager userManager;
   
	private Msg msg;

	public EditMsgAction(MsgManager nsgManager, UserManager userManager) {

		this.msgManager = nsgManager;
		this.userManager = userManager;
	}

	public Msg getMsg() {
		return msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	public void prepare() throws Exception {
		if (null == msg) {
			if (this.hasId("msg.id")) {
				this.msg = this.msgManager.loadMsg(this.getId("msg.id"));
			} else {
				this.msg = new Msg();
			}
		}

	}

	public String save() {

		boolean isNew = this.msg.isNew();
		if (!StringUtils.isEmpty(request.getParameter("msg.msgType"))) {
			if (request.getParameter("msg.msgType").equals(
					MsgType.MESSAGE.toString())) {
				msg.setMsgType(MsgType.MESSAGE);
			} else {
				msg.setMsgType(MsgType.ALERT);
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("receiveUser.id"))) {
			msg.setReceiveUser(this.userManager.loadUser(this
					.getId("receiveUser.id")));
		}

		if (!StringUtils.isEmpty(request.getParameter("msg.msgStatus"))) {
			if (request.getParameter("msg.msgStatus").equals(
					MsgStatus.UNREAD.toString())) {
				msg.setMsgStatus(MsgStatus.UNREAD);
			} else {
				msg.setMsgStatus(MsgStatus.READED);
			}
		}
		this.msgManager.storeMsg(msg);
		if (isNew) {
			this.addActionMessage(this.getText("msg.add.success", Arrays
					.asList(new Object[] { msg.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("area.edit.success", Arrays
					.asList(new Object[] { msg.getName() })));
			return SUCCESS;
		}

	}

	public List<LabelValue> getMsgTypes() {
		LabelValue[] arrays = this.wrapEnum(MsgType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public List<LabelValue> getMsgStatus() {
		LabelValue[] arrays = this.wrapEnum(MsgStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}



	
}
