package main.dao.impl;

import java.util.List;

import main.dao.EventDao;
import main.pojo.EventLog;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.Notification;
import main.pojo.NotificationLog;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EventDaoImpl extends HibernateDaoSupport implements EventDao {

	public void saveEvent(EventNew event) {
		try {
			this.getHibernateTemplate().save(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEvent(EventNew event) {
		try {
			String sql = "delete from EventNew where id = " + event.getId();
			this.getSession().createSQLQuery(sql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EventType getEventTypeByCode(String code) {
		String hql = "from EventType temp where temp.code = '" + code + "'";
		return (EventType) this.getHibernateTemplate().find(hql).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<EventNew> getEvent() {
		String hql = "from EventNew temp where ( temp.dealFlag = 0 or temp.dealFlag is null )and temp.effectflag = 'E'";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	public void dealing(List<Long> ids) {
		String idstring = "";
		for (Long i : ids) {
			if ("".equals(idstring)) {
				idstring += i;
			} else {
				idstring += "," + i;
			}
		}
		String sql = "update EventNew set dealFlag = 1 where id in (" + idstring + ")";
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	public void flushing(List<Long> ids) {
		String idstring = "";
		for (Long i : ids) {
			if ("".equals(idstring)) {
				idstring += i;
			} else {
				idstring += "," + i;
			}
		}
		String sql = "update EventNew set dealFlag = 0 where id in (" + idstring + ")";
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	public EventLog saveEventLog(EventNew event) {
		EventLog eventLog = new EventLog();
		eventLog.setEventType(event.getEventType());
		eventLog.setEffectflag("E");
		eventLog.setName(event.getName());
		eventLog.setMoreinfo(event.getMoreinfo());
		eventLog.setUserid(event.getUserId());
		this.getHibernateTemplate().save(eventLog);
		return eventLog;
	}

	public Session getSuperSession() {

		return this.getSession(true);
	}

	public void saveNotification(Notification noti) {
		try {
			this.getHibernateTemplate().save(noti);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveNotificationLog(NotificationLog notiLog) {
		try {
			this.getHibernateTemplate().save(notiLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
