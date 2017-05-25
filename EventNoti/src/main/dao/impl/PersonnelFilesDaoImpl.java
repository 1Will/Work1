package main.dao.impl;

import java.util.List;

import main.dao.PersonnelFilesDao;
import main.pojo.PersonnelFiles;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PersonnelFilesDaoImpl extends HibernateDaoSupport implements PersonnelFilesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonnelFiles> getAllPersonnelFiles() {
		String hql="from PersonnelFiles p where p.disabled=0";
		System.out.println("===hql==="+hql);
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
	
	//���ݵ�¼�û��� �����±����ȡdeptId��dutyId
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonnelFiles> getPersonnelFilesByName(String name) {
		String hql="select p from PersonnelFiles p where p.name= '"+name+"'";
		return this.getHibernateTemplate().find(hql);
	}

	//����Ա����� ��ȡ������Ϣ
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonnelFiles> getPersonnelFilesByCode(String code) {
		String hql="select p from PersonnelFiles p where p.code= '"+code+"'";
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}


	

}
