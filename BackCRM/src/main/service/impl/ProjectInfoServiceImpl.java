package main.service.impl;


import java.util.List;

import main.dao.ProjectInfoDao;
import main.pojo.ProjectInfo;
import main.service.ProjectInfoService;

import org.hibernate.Session;

public class ProjectInfoServiceImpl implements ProjectInfoService {
	
	private ProjectInfoDao projectInfoDao;


	public void saveProjectInfo(ProjectInfo projectInfo) {
		projectInfoDao.saveProjectInfo(projectInfo);
		
	}

	public  List<ProjectInfo> getProjectInfoById(){
		return projectInfoDao.getProjectInfoById();
	}

	public  ProjectInfo getProjectInfo_ById(Long id){
		return projectInfoDao.getProjectInfo_ById(id);
	}

	@Override
	public List<ProjectInfo> getProjectInfoByName(String projectName) {
		return projectInfoDao.getProjectInfoByName(projectName);
	};

	@Override
	public List<ProjectInfo> getProjectInfosByNameAndBusinessType(String name,
			Long businessType) {
		return projectInfoDao.getProjectInfosByNameAndBusinessType(name, businessType);
	}
	
	@Override  //获取最大id
	public Object findLastProjectId() {
		return projectInfoDao.findLastProjectId();
	}
	
	
	public  List<ProjectInfo> getAllProjectInfo(Long id){
		return projectInfoDao.getAllProjectInfo( id);
	}


	public ProjectInfo getById(Long id)
	{
		return projectInfoDao.getById(id);
	}

	public Session getSuperSession() {
		return projectInfoDao.getSuperSession();
	}

	public ProjectInfoDao getProjectInfoDao() {
		return projectInfoDao;
	}

	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}


	
}
