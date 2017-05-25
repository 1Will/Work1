package main.util;

import java.text.SimpleDateFormat;
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
			String url = "http://www.yj-tech.com/EventNoti/backVisitServlet?bid="+backVisitId+"&userid="+Long.parseLong(list[i])+"&sender="+sender;
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "您有一条回访消息";
			String stateName = SQLUtil.getNameById(map1.get("state"));
			String fankui = map1.get("feed");
			SendUtil.sendTemplateHf(openid, url, title, map1.get("visitor"), map1.get("projectName"), map1.get("customName"), stateName, fankui,"点击查看详情");
		}

	}
	/**
	 * 项目提交处理
	 */
	@SuppressWarnings("rawtypes")
	public void publishProjectNotification(EventNew event) {
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
		String projectInfoId = (String) map.get("projectInfoId");
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
			System.out.println(projectInfoId);
			
			Map<String,String> map1 = SQLUtil.getProject(Integer.parseInt(projectInfoId));
			String url = "http://www.yj-tech.com/EventNoti/projectInfoServlet?projectInfoId="+projectInfoId;
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "您有一条项目提交消息";
			SendUtil.sendTemplatePj(openid, url, title, map1.get("name"), map1.get("introduce"), map1.get("creator"),"点击查看详情");
		}
	}

	
	/**
	 * 日报信息处理
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void publishDailyNotification(EventNew event) {
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
		String dailyId = (String) map.get("dailyId");
		String users = (String) map.get("users");
		String deptName = (String) map.get("deptName");
		String dutyName = (String) map.get("dutyName");
		String userName = (String) map.get("userName");
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
			System.out.println(dailyId);
			
			//获取日报信息 填写模板内容
			Map<String,String> map1 = SQLUtil.getDaily(Integer.parseInt(dailyId));
			String url = "http://www.yj-tech.com/EventNoti/dailyServlet?dailyId="+dailyId+"&userName="+userName+"&dutyName="+dutyName+"&deptName="+deptName+"&userid="+Long.parseLong(list[i])+"&sender="+sender;
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "您收到一条新的日报信息";
			String workload="1天"; //工作量 1天
			String currentDate =map1.get("currentDate"); //获取日报日期
			SimpleDateFormat format1=new SimpleDateFormat("yyyy年MM月dd日");
			String workContext=map1.get("workContext"); //当天工作内容 对应模板中的描述
			String workContext2=workContext;
			if (workContext.length()>20) {
				 workContext2=workContext.substring(0,20);
			}
			
			String questions=map1.get("questions"); //问题 、感想、收获    对应模板中的备注
			String questions2=questions;
			if (questions2.length()>20) {
				 questions2=workContext.substring(0,20);
			}
			
			SendUtil.sendTemplateRB(openid, url, title, workContext2,workload,questions2,currentDate, userName,"点击查看详情");
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
