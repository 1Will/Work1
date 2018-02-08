package main.dao.impl;

import java.util.List;

import main.dao.WeekDao;
import main.pojo.Week;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class WeekDaoImpl extends HibernateDaoSupport implements WeekDao {

	@Override
	public Week getWeekById(Long weekId) {
		Week week=null;
		try {
			week=(Week)getSession().load(Week.class, weekId);
		} catch (Exception e) {
		       e.printStackTrace();
		}
		return week;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Week> getWeekByMonth(String month) {
		
		String hql= "from  Week w where w.name like '"+month+"%' " ;
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Week> getWeekByMonthAndYear(String month,int year) {
		
		String hql= "from  Week w where w.name like '"+month+"%' and convert(varchar,w.startDate,120) like '"+year+"%' " ;
		return this.getHibernateTemplate().find(hql);
	}
    
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}



	
}
