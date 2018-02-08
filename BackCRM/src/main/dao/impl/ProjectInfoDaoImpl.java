package main.dao.impl;
import java.util.List;

import main.dao.ProjectInfoDao;
import main.pojo.CustomerInfo;
import main.pojo.ProjectInfo;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ProjectInfoDaoImpl extends HibernateDaoSupport implements ProjectInfoDao {

	@Override
	public void saveProjectInfo(ProjectInfo projectInfo)
	{
		try{
		    this.getHibernateTemplate().save(projectInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateProjectInfo(ProjectInfo projectInfo)
	{
		try{
			this.getHibernateTemplate().update(projectInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectInfo> getProjectInfoById() {
		String hql = "from ProjectInfo";
		System.out.println("====sql===="+hql);
		return this.getHibernateTemplate().find(hql);
	}
	
	public Session getSuperSession() {
		
		return this.getSession(true);
	}
	
	//根据客户id获取项目集合
	@SuppressWarnings("unchecked")
	public  List<ProjectInfo> getAllProjectInfo(Long id) {
		String hql="from ProjectInfo temp where temp.customerId="+id;
		
		return this.getHibernateTemplate().find(hql);
	}
	
	//根据projectName 模糊查询
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectInfo> getProjectInfoByName(String projectName) {
		String hql= "select p from  ProjectInfo p where p.projectName like '%"+projectName+"%' order by p.id desc ";
		return this.getHibernateTemplate().find(hql);
		
	}
	
	//  项目表中，通过项目名称和客户的businessType属性 查询项目列表
	@SuppressWarnings("unchecked")
	@Override
	public  List<ProjectInfo> getProjectInfosByNameAndBusinessType(String name,Long businessType) {
		return getHibernateTemplate().find(" select info from main.pojo.ProjectInfo info where info.projectName like '%"+name+"%' and info.customerId.businessType.id="+businessType+" order by info.id desc ");
	}

	
	
	public  ProjectInfo getById(Long id) {
		String hql="from ProjectInfo temp where temp.id="+id;
		
		return (ProjectInfo) this.getHibernateTemplate().find(hql).get(0);
	}

	//返回显示页面调用方法
	public  ProjectInfo getProjectInfo_ById(Long id) {
		ProjectInfo projectInfo=null;
		try {
			projectInfo=(ProjectInfo)getSession().load(ProjectInfo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectInfo;
	}
   
	//查找最大id
	@Override
	public Object findLastProjectId() {
		String hql="select  p.id from ProjectInfo p order by Id desc";
		System.out.println("projectInfo最大id为: ");
		return this.getHibernateTemplate().find(hql).get(0);
	}

	
}
