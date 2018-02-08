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
package com.yongjun.tdms.presentation.webwork.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.notice.ReceviceNotice;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.BaseAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;


/**
 * 
 * @author <a href="mailto:268889u.xp@gmail.com">qsun</a>
 * @version $Id: FramesetAction.java 8109 2007-10-29 07:43:15Z qsun $
 */
public class FramesetAction extends BaseAction {
	private static final long serialVersionUID = -2286308339990443246L;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private final UserManager userManager;
	private final WorkWarnningManager workWarnningManager;
	private final ApplicationDocManager applicationDocManager;
	private final ReceviceNoticeManager receviceNoticeManager;
	
	public FramesetAction(UserManager userManager,
			WorkWarnningManager workWarnningManager,
			ApplicationDocManager applicationDocManager,
			ReceviceNoticeManager receviceNoticeManager) {
		this.userManager = userManager;
		this.workWarnningManager = workWarnningManager;
		this.applicationDocManager = applicationDocManager;
		this.receviceNoticeManager = receviceNoticeManager;
	}
	
	public String execute() throws Exception{
		if (null != this.userManager.getUser()) {
			logger.info("登录用户名：" + this.userManager.getUser().getName());
		}
		return SUCCESS;
	}
	
	/**
	 * 获取未读提醒的个数
	 * @return
	 */
	public Integer getNumberOfUnRead() {
		Integer number = 0;
		if (null != this.userManager.getUser()) {
			number = this.workWarnningManager.GetNumberOfUnReadWarnningByUserID(this.userManager.getUser().getId());
		}
		return number;
	}
	
	/**
	 * 获取未读通知的个数
	 * @return
	 */
	public Integer getNumberOfUnReadNotice() {
		Integer number = 0;
		if (null != this.userManager.getUser()) {
			number = this.receviceNoticeManager.getNumberOfUnReadNoticByUserID(this.userManager.getUser().getId());
		}
		return number;
	}
	
	/**
	 * 获取未读通知的集合对象
	 * @return
	 */
	public List<ReceviceNotice> getUnReadNotices() {
		if (null != this.userManager.getUser()) {
			return this.receviceNoticeManager.getAllUnReadNoticByUserID(this.userManager.getUser().getId(),DateUtil.getSystemDate());
		}
		return null;
	}
	public String checkAuth() {
		return "login";
	}
	
	/**
	 * 获取当前登陆的用户 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	/**
	 * 获取所有手册集合
	 * @return
	 */
	public List<ApplicationDoc> getManualDocs() {
		return this.applicationDocManager.getAllManualDoc();
	}
	
	public String index() {
		return SUCCESS;
	}
	
}
