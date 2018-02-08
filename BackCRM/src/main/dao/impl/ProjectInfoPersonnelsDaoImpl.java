package main.dao.impl;
import java.util.List;

import main.dao.ProjectInfoPersonnelsDao;
import main.pojo.ProjectInfoPersonnels;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ProjectInfoPersonnelsDaoImpl extends HibernateDaoSupport implements ProjectInfoPersonnelsDao {

	
	public void saveProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnels)
	{
		try{
		    this.getHibernateTemplate().save(projectInfoPersonnels);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据项目id获得项目组成员集合
	@SuppressWarnings("unchecked")
	public  List<ProjectInfoPersonnels> getProjectInfoPersonnelsById(long id) {
		System.out.println(444);
		String hql = "from ProjectInfoPersonnels where projectInfo_id=" + id ;
		   System.out.println("====sql===="+hql);
		return this.getHibernateTemplate().find(hql);
	}

	

	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	
	
	
}
