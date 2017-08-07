package com.yongjun.tdms.presentation.webwork.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;

public abstract class SubmitAction extends PrepareAction {

	private static final long serialVersionUID = 1L;
	private UserManager userManager;
	private EventNewManager eventNewManager;
	private EventTypeManager eventTypeManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * 
	 * @param tagName 页面isSave标签name
	 * @param code	eventType的code
	 * @param userIds 要通知人的所有id（1，2.。。）
	 * @param base 改类的对象
	 * @return submit为提交了，save没有，error为错误
	 */
	public String submit(String tagName, String code, String userIds, BaseInfoEntity base) {
		if ("1".equals(this.request.getParameter(tagName))) {
			try {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", code);
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventType不存在！");
					return "error";
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName(eventType.getName());
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				map.put("users", userIds.toString());
				map.put("projectInfoId", base.getId() + "");
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			return "submit";
		} else {
			return "save";
		}
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public EventNewManager getEventNewManager() {
		return eventNewManager;
	}

	public EventTypeManager getEventTypeManager() {
		return eventTypeManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setEventNewManager(EventNewManager eventNewManager) {
		this.eventNewManager = eventNewManager;
	}

	public void setEventTypeManager(EventTypeManager eventTypeManager) {
		this.eventTypeManager = eventTypeManager;
	}

}
