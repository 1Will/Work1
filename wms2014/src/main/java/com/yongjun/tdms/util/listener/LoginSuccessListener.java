package com.yongjun.tdms.util.listener;

import java.util.Calendar;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.acegisecurity.Authentication;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.service.base.event.EventNewManager;

public class LoginSuccessListener implements ServletRequestListener,ApplicationListener {
	private EventNewManager eventNewManager;
	private UserManager userManager;
	private static String remoteIp ="";
	
	public void onApplicationEvent(ApplicationEvent event) {
//		ServletRequestEvent srEvent = (ServletRequestEvent)event.getSource();
//		ServletRequest sr = srEvent.getServletRequest();
//		remoteIp = sr.getRemoteAddr();
//		System.out.println("=========================" + remoteIp);
		if (event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent)event;
			Authentication auth = authEvent.getAuthentication();
			String userName = auth.getName();           //获取用户名
			User user = this.userManager.getUserByLoginName(userName);
			if (null != user) {
				EventNew userLoginEvent = new EventNew();
				userLoginEvent.setTime(Calendar.getInstance().getTime());
				userLoginEvent.setEventType(100);
				userLoginEvent.setUserId(user.getId());
				userLoginEvent.setMoreInfo(remoteIp);
				userLoginEvent.setOrganization(user.getOrganization());
				userLoginEvent.setStatus(0);
				userLoginEvent.setDocId(0);
				userLoginEvent.setDocType(0);
//				Context context = SecureContextUtils.getSecureContext();
//				ServletRequest sr = context.
				this.eventNewManager.storeEventNew(userLoginEvent);
			}
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
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void requestInitialized(ServletRequestEvent srEvent) {
		ServletRequest sr = srEvent.getServletRequest();
		remoteIp = sr.getRemoteAddr();
	}
	public static String getRemoteIp() {
		return remoteIp;
	}
	public static void setRemoteIp(String remoteIp) {
		LoginSuccessListener.remoteIp = remoteIp;
	}


}
