package main.dao.impl;
import java.util.List;

import main.dao.ReplyBackVisitDao;
import main.pojo.ReplyBackVisit;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ReplyBackVisitDaoImpl extends HibernateDaoSupport implements ReplyBackVisitDao {

	
	public void saveReplyBackVisit(ReplyBackVisit replyBackVisit)
	{
		try{
		    this.getHibernateTemplate().save(replyBackVisit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public  List<ReplyBackVisit> getReplyBackVisitById(int id) {
		
		String hql = "from ReplyBackVisit temp where temp.visitid = '" + id + "'";
		return this.getHibernateTemplate().find(hql);
	}

	

	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	
	
	
}
