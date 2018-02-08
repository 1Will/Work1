package main.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import main.dao.DepartmentDao;
import main.pojo.Department;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDeptnameById(Long id) {
		String hql="select d.name from Department d where d.id="+id;
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department department=null;
		try {
			department=(Department) getSession().load(Department.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return department;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getClassiFicationFromDept() {
		String hql="select d from Department d where d.parentId=72 or d.parentId=73";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}


	

}
