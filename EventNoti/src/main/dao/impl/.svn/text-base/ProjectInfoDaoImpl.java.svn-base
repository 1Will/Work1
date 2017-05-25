package main.dao.impl;
import java.util.List;

import main.dao.ProjectInfoDao;
import main.pojo.ProjectInfo;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ProjectInfoDaoImpl extends HibernateDaoSupport implements ProjectInfoDao {

	
	public void saveProjectInfo(ProjectInfo projectInfo)
	{
		try{
		    this.getHibernateTemplate().save(projectInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectInfo> getProjectInfoById() {
		System.out.println(333);
		String hql = "from ProjectInfo";
		System.out.println("====sql===="+hql);
		return this.getHibernateTemplate().find(hql);
	}
	
	public Session getSuperSession() {
		
		return this.getSession(true);
	}
	

	@SuppressWarnings("unchecked")
	public  List<ProjectInfo> getAllProjectInfo(Long id) {
		String hql="from ProjectInfo temp where temp.customerId="+id;
		
		return this.getHibernateTemplate().find(hql);
	}
	public  ProjectInfo getById(Long id) {
		String hql="from ProjectInfo temp where temp.id="+id;
		
		return (ProjectInfo) this.getHibernateTemplate().find(hql).get(0);
	}

	//返回显示页面调用方法
	@SuppressWarnings("unchecked")
	public  ProjectInfo getProjectInfo_ById(Long id) {
	    System.out.println(444);
		ProjectInfo projectInfo=null;
		try {
			projectInfo=(ProjectInfo)getSession().load(ProjectInfo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectInfo;
	}
   
	//查找最大id
	@SuppressWarnings("unchecked")
	@Override
	public Object findLastProjectId() {
		String hql="select  p.id from ProjectInfo p order by Id desc";
		System.out.println("projectInfo最大id为: ");
		return this.getHibernateTemplate().find(hql).get(0);
	}

	
}
