package main.dao.impl;

import java.util.List;

import main.dao.ProjectInfoPlanDao;
import main.pojo.ProjectInfoPlan;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ProjectInfoPlanDaoImpl extends HibernateDaoSupport implements ProjectInfoPlanDao {
	

	@Override
	public ProjectInfoPlan getProjectInfoPlanById(Long id) {
		ProjectInfoPlan pInfoPlan = null;
		try {
			pInfoPlan=(ProjectInfoPlan) this.getSession().load(ProjectInfoPlan.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return pInfoPlan;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectInfoPlan> getProjectInfoPlanByPerId(Long personnelId) {
		String hql="select p from  ProjectInfoPlan p where p.personnelFiles= "+personnelId+" order by p.id desc";
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public void updateProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
		this.getHibernateTemplate().merge(projectInfoPlan);
		
	}
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}



    
	
}
