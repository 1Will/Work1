package main.dao.impl;
import java.util.List;

import main.dao.BackVisitDao;
import main.pojo.BackVisit;

import org.hibernate.Query;
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
	
	public void updateBackVisit(BackVisit backVisit)
	{
		try{
			this.getHibernateTemplate().update(backVisit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据日期和姓名返回backVisit集合   convert(varchar,c.createdTime,120) like '% "+date+" %'   b.visitDate
	@SuppressWarnings("unchecked")
	@Override
	public List<BackVisit> getBackVisitByDate(String date,String name ) {
		String hql="from BackVisit b where convert(varchar,b.visitDate,120) like '"+date+"%' and b.visitor='"+name+"'";
		return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BackVisit> getBackVisitByCustomerName(String customerName) {
		String hql="from BackVisit b where b.customerName='"+customerName+"' order by b.visitDate desc ";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BackVisit> getAllBackVisit(Long[] backVisitIds) {
	//	String hql="select b from BackVisit b where b.id in (:"+backVisitIds+")";
		String hql="from BackVisit b where b.id in (:alist)"; 
		Query query = getSession().createQuery(hql); 
		query.setParameterList("alist", backVisitIds);
		return this.getHibernateTemplate().find(hql);
	}
	
	
	@Override
	public BackVisit getBackVisitById(Long id) {
		BackVisit backVisit=null;
		try {
			backVisit=(BackVisit)getSession().load(BackVisit.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return backVisit;
	}
	
	public Session getSuperSession() {
		
		return this.getSession(true);
	}



	
	
}
