package main.dao;


import java.util.List;

import main.pojo.ProjectInfo;

import org.hibernate.Session;


public interface ProjectInfoDao {

	public void saveProjectInfo(ProjectInfo projectInfo);
	public  List<ProjectInfo> getProjectInfoById(); 
	public  ProjectInfo getProjectInfo_ById(Long id); //��ʾҳ����÷���
	public Session getSuperSession();

	public  List<ProjectInfo> getAllProjectInfo(Long id)  ;
	public ProjectInfo getById(Long id);

    public Object findLastProjectId();//��ȡ��ǰ���ݱ������id
	

}
