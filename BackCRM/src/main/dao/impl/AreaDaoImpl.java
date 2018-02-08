package main.dao.impl;

import java.util.List;

import main.dao.AreaDao;
import main.pojo.Area;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AreaDaoImpl extends HibernateDaoSupport implements AreaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAllCountryByType() {
		String hql="select a from Area a where a.type='country'";
		return this.getHibernateTemplate().find(hql);
	}
	
    @SuppressWarnings("unchecked")
    @Override
    public List<Area> getAreaByAreaid(Long areaId){
    	String hql="select a from Area a where a.areaId="+areaId;
    	return this.getHibernateTemplate().find(hql);
    }
    
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}

	
}
