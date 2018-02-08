package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.ProjectInfoPlanDao;
import main.pojo.ProjectInfoPlan;
import main.service.ProjectInfoPlanService;

public class ProjectInfoPlanServiceImpl implements ProjectInfoPlanService {
         
   private ProjectInfoPlanDao projectInfoPlanDao;

@Override
public ProjectInfoPlan getProjectInfoPlanById(Long id) {
	return projectInfoPlanDao.getProjectInfoPlanById(id);
}

@Override
public List<ProjectInfoPlan> getProjectInfoPlanByPerId(Long personnelId) {
	return projectInfoPlanDao.getProjectInfoPlanByPerId(personnelId);
}

@Override
public void updateProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
       projectInfoPlanDao.updateProjectInfoPlan(projectInfoPlan);	
}

@Override
public Session getSuperSession() {
	return projectInfoPlanDao.getSuperSession();
}

public ProjectInfoPlanDao getProjectInfoPlanDao() {
	return projectInfoPlanDao;
}

public void setProjectInfoPlanDao(ProjectInfoPlanDao projectInfoPlanDao) {
	this.projectInfoPlanDao = projectInfoPlanDao;
}







	
	
}
