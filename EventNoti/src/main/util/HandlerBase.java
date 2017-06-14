package main.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.pojo.Daily;
import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.Notification;
import main.pojo.NotificationLog;
import main.service.EventService;
import net.sf.json.JSONObject;
import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;
import souvc.util.SendUtil;


/**
 * �¼���������࣬����һЩ��������
 * 
 * @author wangping
 * @version 1.0
 * @since 2016��5��26��, ����9:29:10
 */
public class HandlerBase {

	public static EventService eventService;

	public EventNew event;

	public EventLog eventLog;

	/**
	 * �����¼���־
	 * @author liyufeng
	 * @since 2016��10��26��
	 */
	public void saveEventLog() {
		EventLog eventLog = eventService.saveEventLog(event);
		this.eventLog = eventLog;

	}
	
	public void qingJiaEvent(){
		saveEventLog();
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//�õ����ݵĲ�����������map
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
	 * �ط��¼�����
	 */
	@SuppressWarnings("rawtypes")
	public void publishNotification(EventNew event) {
		saveEventLog();//����log��־
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//�õ����ݵĲ�����������map
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
			String title = "����һ���ط���Ϣ";
			String stateName = SQLUtil.getNameById(map1.get("state"));
			String fankui = map1.get("feed");
			String fankui2=fankui;
			if (fankui.length()>50) {
				fankui2=fankui.substring(0,50);
			}
			SendUtil.sendTemplateHf(openid, url, title, map1.get("visitor"), map1.get("projectName"), map1.get("customName"), stateName, fankui,"����鿴����");
		}

	}
	/**
	 * ��Ŀ�ύ����
	 */
	@SuppressWarnings("rawtypes")
	public void publishProjectNotification(EventNew event) {
		saveEventLog();//����log��־
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//�õ����ݵĲ�����������map
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
			String url = "http://yjkj.ngrok.cc/EventNoti/projectInfoServlet?projectInfoId="+projectInfoId;
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "����һ����Ŀ�ύ��Ϣ";
			String introduce =map1.get("introduce"); //��Ŀ����
			String introduce2=introduce;
			if (introduce.length()>50) {
				introduce2=introduce.substring(0,50);
			}
			
			SendUtil.sendTemplatePj(openid, url, title, map1.get("name"), introduce2, map1.get("creator"),"�����鿴����");
		}
	}
	/**
	 * ���̼�鴦��
	 * wy
	 * 20170613
	 */
	@SuppressWarnings("rawtypes")
	public void spaceCheckerNotification(EventNew event) {
		saveEventLog();//����log��־
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//�õ����ݵĲ�����������map
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		String content = (String) map.get("content");
		
		String users = (String) map.get("users");
		String[] list = users.split(", ");
		
		for(int i=0;i<list.length&&!"".equals(list[i]);i++){
			Notification noti = new Notification();
			noti.setEffectflag("E");
			noti.setEventType_id(event.getEventType().getId());
			noti.setHaveRead("N");
			noti.setReceiver(Long.parseLong(list[i]));
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
			
			String url = "#";
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "����һ�����̿ռ���Ϣ";
			DateFormat format1=new SimpleDateFormat("yyyy-MM-dd  hh:mm");
			String currentDate=format1.format(new Date());
			
			SendUtil.sendTemplateJCRB(openid, url, title,content, "ϵͳ����",currentDate,event.getName(),"����鿴����");  
		
		}
	}
	/**
	 * �ձ���鴦��
	 * wy
	 * 20170613
	 */
	@SuppressWarnings("rawtypes")
	public void dailyCheckNotification(EventNew event) {
		saveEventLog();//����log��־
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//�õ����ݵĲ�����������map
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		String content = (String) map.get("content");
		
		String users = (String) map.get("users");
		String[] list = users.split(", ");
		
		for(int i=0;i<list.length&&!"".equals(list[i]);i++){
			Notification noti = new Notification();
			noti.setEffectflag("E");
			noti.setEventType_id(event.getEventType().getId());
			noti.setHaveRead("N");
			noti.setReceiver(Long.parseLong(list[i]));
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
			//String openid = SQLUtil.getUserDetail(Integer.parseInt("104")).getOpenid();
			
			String url = "#";
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "����һ���ձ�������Ϣ";
			DateFormat format1=new SimpleDateFormat("yyyy-MM-dd  hh:mm");
			String currentDate=format1.format(new Date());
			
			SendUtil.sendTemplateJCRB(openid, url, title,content, "ϵͳ����",currentDate,event.getName(),"����鿴����");  
		}
	}

	
	/**
	 * �ձ���Ϣ����
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void publishDailyNotification(EventNew event) {
		saveEventLog();//����log��־
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();//�õ����ݵĲ�����������map
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
		String[] str2 = users.split(",");
		List<String> list = new ArrayList<String>();  
		int k=0; //��¼���ظ���id����
		 for (int i=0; i<str2.length; i++) {  
	            if(!list.contains(str2[i])) {  
	                list.add(str2[i]);
	                k+=1;
	            }  
	        }
		 //����ת��Ϊ����
		String []str=new String[k];
		for (int i = 0; i < list.size(); i++) {
			str[i]=list.get(i);
			
		}
		
		for(int i=0;i<str.length&&!str.equals("");i++){
			Notification noti = new Notification();
			noti.setEffectflag("E");
			noti.setEventType_id(event.getEventType().getId());
			noti.setHaveRead("N");
			noti.setReceiver(Long.parseLong(str[i]));
			noti.setSender(Long.parseLong(sender));
			noti.setSendTime(new Date());
			eventService.saveNotification(noti);
			NotificationLog notiLog = new NotificationLog();
			notiLog.setEffectflag("E");
			notiLog.setName(event.getName());
			notiLog.setNotificationId(noti.getId());
			eventService.saveNotificationLog(notiLog);
			noti.setEffectflag("N");
			System.out.println(str[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(str[i])).getOpenid();
			System.out.println(dailyId);
			
			//��ȡ�ձ���Ϣ ��дģ������
			Map<String,String> map1 = SQLUtil.getDaily(Integer.parseInt(dailyId));
			String url = "http://yjkj.ngrok.cc/EventNoti/dailyServlet?dailyId="+dailyId+"&userid="+Long.parseLong(str[i])+"&sender="+sender;
			//String template_id = "rqNh79JYNOqLku06TFIPDUzezT3hddZ3COLIco0JLOE";
			String title = "���յ�һ���µ��ձ���Ϣ";
			String workload="1��"; //������ 1��
			String currentDate =map1.get("currentDate"); //��ȡ�ձ�����
			SimpleDateFormat format1=new SimpleDateFormat("yyyy��MM��dd��");
			String workContext=map1.get("workContext"); //���칤������ ��Ӧģ���е�����
			String workContext2=workContext;
			if (workContext.length()>30) {
				 workContext2=workContext.substring(0,30);
			}
			
			//��ȡ�û���
			String rapporteurId=map1.get("rapporteurId");
			UserInfo userInfo  = SQLUtil.getUserDetail(Integer.parseInt(rapporteurId));
			String userName=userInfo.getName();
			String questions=map1.get("questions"); //���� �����롢�ջ�    ��Ӧģ���еı�ע
			String questions2=questions;
			if (questions2.length()>26) {
				 questions2=workContext.substring(0,26);
			}
			
			SendUtil.sendTemplateRB(openid, url, title, workContext2,workload,questions2,currentDate, userName,"����鿴����");
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
