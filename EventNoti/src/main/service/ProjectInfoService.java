package main.service;


import java.util.List;

import main.pojo.ProjectInfo;

import org.hibernate.Session;

public interface ProjectInfoService {

	public void saveProjectInfo(ProjectInfo projectInfo);

	public  List<ProjectInfo> getProjectInfoById();

	public  ProjectInfo getProjectInfo_ById(Long id);

	public Session getSuperSession();
	

	public  List<ProjectInfo> getAllProjectInfo(Long id);
	public ProjectInfo getById(Long id);

	public Object findLastProjectId();//获取当前数据表中最大id

}
