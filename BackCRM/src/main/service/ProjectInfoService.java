package main.service;


import java.util.List;

import main.pojo.ProjectInfo;

import org.hibernate.Session;

public interface ProjectInfoService {

	public void saveProjectInfo(ProjectInfo projectInfo);

	public  List<ProjectInfo> getProjectInfoById();

	public  ProjectInfo getProjectInfo_ById(Long id);

	public List<ProjectInfo> getProjectInfoByName(String projectName);//根据projectName 模糊查询获取项目列表	

  public  List<ProjectInfo> getProjectInfosByNameAndBusinessType(String name,Long businessType) ;
	
	public  List<ProjectInfo> getAllProjectInfo(Long id);
	
	public ProjectInfo getById(Long id);

	public Object findLastProjectId();//获取当前数据表中最大id

	public Session getSuperSession();

}
