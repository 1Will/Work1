package main.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import main.dao.DepartmentDao;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDeptnameById(Long id) {
		String hql="select d.name from Department d where d.id="+id;
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}

	

}
