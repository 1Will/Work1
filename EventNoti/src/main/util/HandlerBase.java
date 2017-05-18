package main.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.Notification;
import main.pojo.NotificationLog;
import main.service.EventService;
import net.sf.json.JSONObject;
import souvc.util.SQLUtil;
import souvc.util.SendUtil;


/**
 * 事件处理基础类，包含一些基础数据
 * 
 * @author wangping
 * @version 1.0
 * @since 2016年5月26日, 上午9:29:10
 */
public class HandlerBase {

	public static EventService eventService;

	public EventNew event;

	public EventLog eventLog;

	/**
	 * 保存事件日志
	 * @author liyufeng
	 * @since 2016年10月26日
	 */
	public void saveEventLog() {
		EventLog eventLog = eventService.saveEventLog(event);
		this.eventLog = eventLog;

	}
	
	public void qingJiaEvent(){
		saveEventLog();
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//得到传递的参数，解析成map
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		String sender = event.getUserId();
		String backVisitId = (String) map.get("qid");
		String users = (String) map.get("users");
		String[] list = users.split(",");
		for(int i=0;i<list.length&&!"".equals(list[i]);i++){
			Notification noti = new Notification();
			noti.setEffectflag("E");
			noti.setEventType_id(event.getEventType().getId());
			noti.setHaveRead("N");
			noti.setReceiver(Long.parseLong(list[i]));
			noti.setSender(Long.parseLong(sender));
			noti.setSendTime(new Date());
			eventService.saveNotification(noti);
			NotificationLog notiLog = new NotificationLog();
			notiLog.setEffectflag("E");
			notiLog.setName(event.getName());
			notiLog.setNotificationId(noti.getId());
			eventService.saveNotificationLog(notiLog);
			noti.setEffectflag("N");
			
			
			
			
		}
	}
	
	/**
	 * 回访事件处理
	 */
	@SuppressWarnings("rawtypes")
	public void publishNotification(EventNew event) {
		saveEventLog();//保存log日志
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//得到传递的参数，解析成map
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		String sender = event.getUserId();
		String backVisitId = (String) map.get("backVisitId");
		String users = (String) map.get("users");
		String[] list = users.split(",");
		for(int i=0;i<list.length&&!"".equals(list[i]);i++){
			Notification noti = new Notification();
			noti.setEffectflag("E");
			noti.setEventType_id(event.getEventType().getId());
			noti.setHaveRead("N");
			noti.setReceiver(Long.parseLong(list[i]));
			noti.setSender(Long.parseLong(sender));
			noti.setSendTime(new Date());
			eventService.saveNotification(noti);
			NotificationLog notiLog = new NotificationLog();
			notiLog.setEffectflag("E");
			notiLog.setName(event.getName());
			notiLog.setNotificationId(noti.getId());
			eventService.saveNotificationLog(notiLog);
			noti.setEffectflag("N");
			System.out.println(list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			System.out.println(backVisitId);
			Map<String,String> map1 = SQLUtil.getBackVisit(Integer.parseInt(backVisitId));
			String url = "http://yjkj.ngrok.cc/EventNoti/backVisitServlet?bid="+backVisitId+"&userid="+Long.parseLong(list[i])+"&sender="+sender;
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "您有一条回访消息";
			String stateName = SQLUtil.getNameById(map1.get("state"));
			String fankui = map1.get("feed");
			SendUtil.sendTemplateHf(openid, url, title, map1.get("visitor"), map1.get("projectName"), map1.get("customName"), stateName, fankui,"点击查看详情");
		}

	}

	public static void setEventService(EventService eventService) {
		HandlerBase.eventService = eventService;
	}


	public void setEvent(EventNew event) {
		this.event = event;
	}

	public void setEventLog(EventLog eventLog) {
		this.eventLog = eventLog;
	}

}
