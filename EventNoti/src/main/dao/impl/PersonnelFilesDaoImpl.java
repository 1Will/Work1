package main.dao.impl;

import java.util.List;

import main.dao.PersonnelFilesDao;
import main.pojo.PersonnelFiles;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PersonnelFilesDaoImpl extends HibernateDaoSupport implements PersonnelFilesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonnelFiles> getPersonnelFilesById() {
		String hql="from PersonnelFiles";
		return this.getHibernateTemplate().find(hql);
	}

	

	@SuppressWarnings("unchecked")
	public  PersonnelFiles getPersonnelFilesById(Long id) {
		
		PersonnelFiles personnelFiles=null;
		try {
			personnelFiles=(PersonnelFiles)getSession().load(PersonnelFiles.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return personnelFiles;
	}
	
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}
	

}
