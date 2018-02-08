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

import java.util.Calendar;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.LogoutAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.service.base.event.EventNewManager;

/**
 * <p>Title: TdmsLogoutAction
 * <p>Description: 用户退出页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class TdmsLogoutAction extends LogoutAction {
	private EventNewManager eventNewManager;
	private UserManager userManager;
	
	public String execute() throws Exception {
		User user = this.userManager.getUser();
		String result = super.execute();
		if (null != user) {
			EventNew userLoginEvent = new EventNew();
			userLoginEvent.setTime(Calendar.getInstance().getTime());
			userLoginEvent.setEventType(101);
			userLoginEvent.setUserId(user.getId());
			userLoginEvent.setMoreInfo(this.getRequest().getRemoteAddr());
			userLoginEvent.setOrganization(user.getOrganization());
			userLoginEvent.setStatus(0);
			userLoginEvent.setDocId(0);
			userLoginEvent.setDocType(0);
			this.eventNewManager.storeEventNew(userLoginEvent);
		}
		return result;
	}
	public EventNewManager getEventNewManager() {
		return eventNewManager;
	}
	public void setEventNewManager(EventNewManager eventNewManager) {
		this.eventNewManager = eventNewManager;
	}
	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
