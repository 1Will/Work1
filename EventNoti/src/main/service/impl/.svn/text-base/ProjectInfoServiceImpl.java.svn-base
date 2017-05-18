package main.service.impl;


import java.util.List;

import main.dao.ProjectInfoDao;
import main.pojo.ProjectInfo;
import main.service.ProjectInfoService;

import org.hibernate.Session;

public class ProjectInfoServiceImpl implements ProjectInfoService {
	
	private ProjectInfoDao projectInfoDao;

	public ProjectInfoDao getProjectInfoDao() {
		return projectInfoDao;
	}

	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}

	public void saveProjectInfo(ProjectInfo projectInfo) {
		projectInfoDao.saveProjectInfo(projectInfo);
		
	}

	public  List<ProjectInfo> getProjectInfoById(){
		System.out.println(33);
		return projectInfoDao.getProjectInfoById();
	}

	public  ProjectInfo getProjectInfo_ById(Long id){
		System.out.println(44);
		return projectInfoDao.getProjectInfo_ById(id);
	}

	@Override  //获取最大id
	public Object findLastProjectId() {
		// TODO Auto-generated method stub
		return projectInfoDao.findLastProjectId();
	}
	
	public Session getSuperSession() {
		return projectInfoDao.getSuperSession();
	}
	public  List<ProjectInfo> getAllProjectInfo(Long id){
		return projectInfoDao.getAllProjectInfo( id);
	}


	public ProjectInfo getById(Long id)
	{
		return projectInfoDao.getById(id);
	};

}
