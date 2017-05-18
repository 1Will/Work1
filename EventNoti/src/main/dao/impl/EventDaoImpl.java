package main.dao.impl;

import java.util.List;

import main.dao.EventDao;
import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.Notification;
import main.pojo.NotificationLog;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class EventDaoImpl extends HibernateDaoSupport implements EventDao {

	
	public void saveEvent(EventNew event) {
		try{
		    this.getHibernateTemplate().save(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EventType getEventTypeByCode(String code) {
		String sql ="";
		String hql = "from EventType temp where temp.code = '" + code + "'";
		return (EventType) this.getHibernateTemplate().find(hql).get(0);
	}

	
	@SuppressWarnings("unchecked")
	public List<EventNew> getEvent() {
		String hql = "from EventNew temp where temp.effectflag = 'E'";
		return this.getHibernateTemplate().find(hql);
	}

	
	public EventLog saveEventLog(EventNew event) {
		EventLog eventLog = new EventLog();
		eventLog.setEventType(event.getEventType());
		eventLog.setId(event.getId());
		eventLog.setEffectflag("E");
		eventLog.setName(event.getName());
		eventLog.setMoreinfo(event.getMoreinfo());
		eventLog.setUserid(event.getUserId());
		this.getHibernateTemplate().save(eventLog);
		this.getHibernateTemplate().delete(event);
		this.getHibernateTemplate().flush();
		return eventLog;
	}
	

	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	
	public void saveNotification(Notification noti) {
		try{
		    this.getHibernateTemplate().save(noti);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveNotificationLog(NotificationLog notiLog) {
		try{
		    this.getHibernateTemplate().save(notiLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
