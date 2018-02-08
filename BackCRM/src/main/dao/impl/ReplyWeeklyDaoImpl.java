package main.dao.impl;
import java.util.List;

import main.dao.ReplyWeeklyDao;
import main.pojo.ReplyWeekly;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ReplyWeeklyDaoImpl extends HibernateDaoSupport implements ReplyWeeklyDao {

	
	public void saveReplyWeekly(ReplyWeekly replyWeekly)
	{
		try{
		    this.getHibernateTemplate().save(replyWeekly);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public  List<ReplyWeekly> getReplyWeeklyById(Long id) {
		
		String hql = "from ReplyWeekly temp where temp.weeklyId = '" + id + "'";
		return this.getHibernateTemplate().find(hql);
	}

	

	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	
	
	
}
