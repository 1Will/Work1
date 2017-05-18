package main.service.impl;

import java.util.List;

import main.dao.EventDao;
import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.Notification;
import main.pojo.NotificationLog;
import main.service.EventService;

import org.hibernate.Session;

public class EventServiceImpl implements EventService {
	
	private EventDao eventDao;

	public EventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public void saveEvent(EventNew event) {
		eventDao.saveEvent(event);
		
	}

	public EventType getEventTypeByCode(String code) {
		return eventDao.getEventTypeByCode(code);
	}

	public List<EventNew> getEvent() {
		return eventDao.getEvent();
	}

	
	public EventLog saveEventLog(EventNew event) {
		return eventDao.saveEventLog(event);
	}
	
	public void saveNotificationLog(NotificationLog notiLog) {
		eventDao.saveNotificationLog(notiLog);
	}
	
	public void saveNotification(Notification noti){
		eventDao.saveNotification(noti);
	}
	
	public Session getSuperSession() {
		return eventDao.getSuperSession();
	}

}
