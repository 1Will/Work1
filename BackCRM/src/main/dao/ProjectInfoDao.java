package main.dao;


import java.util.List;

import main.pojo.ProjectInfo;

import org.hibernate.Session;


public interface ProjectInfoDao {

	public void saveProjectInfo(ProjectInfo projectInfo);
	
	public  List<ProjectInfo> getProjectInfoById(); 
	
	public  ProjectInfo getProjectInfo_ById(Long id); //显示页面调用方法
	
	public List<ProjectInfo> getProjectInfoByName(String projectName);//根据projectName 模糊查询获取项目列表

	public  List<ProjectInfo> getAllProjectInfo(Long id)  ; //根据客户id获取项目集合

	public ProjectInfo getById(Long id);

    public Object findLastProjectId();//获取当前数据表中最大id
    
    public  List<ProjectInfo> getProjectInfosByNameAndBusinessType(String name,Long businessType) ;
	
    public Session getSuperSession();

	void updateProjectInfo(ProjectInfo projectInfo);

}
