package main.dao;

import java.util.List;

import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.Notification;
import main.pojo.NotificationLog;

import org.hibernate.Session;


public interface EventDao {

	public void saveEvent(EventNew event);
	
	public void deleteEvent(EventNew event);

	public EventType getEventTypeByCode(String code);

	public List<EventNew> getEvent();

	public EventLog saveEventLog(EventNew event);

	public Session getSuperSession();
	
	public void saveNotification(Notification noti);
	
	public void saveNotificationLog(NotificationLog notiLog);
	
	public void flushing(List<Long> ids);
	
	public void dealing(List<Long> ids);

}
