package main.service.impl;

import java.util.List;

import main.dao.ProjectInfoPersonnelsDao;
import main.pojo.ProjectInfoPersonnels;
import main.service.ProjectInfoPersonnelsService;

import org.hibernate.Session;

public class ProjectInfoPersonnelsServiceImpl implements ProjectInfoPersonnelsService {
	
	private ProjectInfoPersonnelsDao projectInfoPersonnelsDao;

	public ProjectInfoPersonnelsDao getProjectInfoPersonnelsDao() {
		return projectInfoPersonnelsDao;
	}

	public void setProjectInfoPersonnelsDao(ProjectInfoPersonnelsDao projectInfoPersonnelsPersonnelsDao) {
		this.projectInfoPersonnelsDao = projectInfoPersonnelsPersonnelsDao;
	}

	public void saveProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnelsPersonnels) {
		projectInfoPersonnelsDao.saveProjectInfoPersonnels(projectInfoPersonnelsPersonnels);
		
	}

	public  List<ProjectInfoPersonnels> getProjectInfoPersonnelsById(long id){
		System.out.println(33);
		return projectInfoPersonnelsDao.getProjectInfoPersonnelsById(id);
	}

	
	public Session getSuperSession() {
		return projectInfoPersonnelsDao.getSuperSession();
	}

}
