package main.dao.impl;
import main.dao.BackVisitDao;
import main.pojo.BackVisit;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BackVisitDaoImpl extends HibernateDaoSupport implements BackVisitDao {

	
	public void saveBackVisit(BackVisit backVisit)
	{
		try{
		    this.getHibernateTemplate().save(backVisit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	
	
	
}
