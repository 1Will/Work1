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
package com.yongjun.tdms.model.base.information;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: Msg.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Msg extends BaseInfoEntity {
	private static final long serialVersionUID = -3915696377317842648L;
	// 名称 
	private String name;
	// 内容
	private String content;
	// 发布日期
	private Date publishDate = DateUtil.getSystemDate();
	// 消息类型（默认是消息）
	private MsgType msgType = MsgType.MESSAGE;
	// 记录状态（标记是否读取）
	private MsgStatus msgStatus = MsgStatus.UNREAD;
	// 接收人
	private User receiveUser;
	
	public Msg() {
		
	}
	
	public MsgType getMsgType() {
		return msgType;
	}
	
	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
	
	public MsgStatus getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(MsgStatus msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public User getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
