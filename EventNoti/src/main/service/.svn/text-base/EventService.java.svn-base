package main.service;

import java.util.List;

import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.Notification;
import main.pojo.NotificationLog;

import org.hibernate.Session;

public interface EventService {

	public void saveEvent(EventNew event);

	public EventType getEventTypeByCode(String code);

	public List<EventNew> getEvent();

	public EventLog saveEventLog(EventNew event);
	
	public void saveNotification(Notification noti);
	
	public void saveNotificationLog(NotificationLog notiLog);
	
	public Session getSuperSession();
	
	

}
