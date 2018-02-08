package main.dao.impl;

import java.util.List;

import main.dao.MessageDao;
import main.pojo.Message;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {

	@Override
	public void saveMessage(Message message) {
		try {
			this.getHibernateTemplate().save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMessage(Message message) {
		try {
			this.getHibernateTemplate().update(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMessage(Message message) {
		try {
			this.getHibernateTemplate().delete(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAllMessage(Long[] messageIds) {
		String hql = "from Message m where m.id in (:alist)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("alist", messageIds);
		return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getSomeMessage(){
		String hql = "from Message";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);   //从第0条开始
		query.setMaxResults(100);//一共取10条
		return query.list();
	}
	
	@Override
	public Message getMessageById(Long id) {
		Message message = null;
		try {
			message = (Message) getSession().load(Message.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}

}
