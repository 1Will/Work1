package main.dao.impl;
import java.util.List;

import main.dao.ReplyDailyDao;
import main.pojo.ReplyDaily;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ReplyDailyDaoImpl extends HibernateDaoSupport implements ReplyDailyDao {

	
	public void saveReplyDaily(ReplyDaily replyDaily)
	{
		try{
		    this.getHibernateTemplate().save(replyDaily);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public  List<ReplyDaily> getReplyDailyById(Long id) {
		
		String hql = "from ReplyDaily temp where temp.dailyid = '" + id + "'";
		return this.getHibernateTemplate().find(hql);
	}

	

	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	
	
	
}
