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
package com.yongjun.tdms.util.listener;

import java.util.Calendar;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.service.base.event.EventNewManager;

/**
 * <p>Title: LogoutSuccessListener
 * <p>Description: session创建和销毁监听器类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class LogoutSuccessListener implements HttpSessionListener {

	private EventNewManager eventNewManager;
	private UserManager userManager;
	
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	public void sessionDestroyed(HttpSessionEvent sEvent) {
		//ServletRequestEvent evnet = (ServletRequestEvent)sEvent;
		User user = this.userManager.getUser();
		System.out.println("===================" + (null == user));
		if (null != user) {
			EventNew userLoginEvent = new EventNew();
			userLoginEvent.setTime(Calendar.getInstance().getTime());
			userLoginEvent.setEventType(101);
			userLoginEvent.setUserId(user.getId());
			userLoginEvent.setMoreInfo(LoginSuccessListener.getRemoteIp());
			userLoginEvent.setOrganization(user.getOrganization());
			userLoginEvent.setStatus(0);
			userLoginEvent.setDocId(0);
			userLoginEvent.setDocType(0);
//			Context context = SecureContextUtils.getSecureContext();
//			ServletRequest sr = context.
			this.eventNewManager.storeEventNew(userLoginEvent);
		}
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
