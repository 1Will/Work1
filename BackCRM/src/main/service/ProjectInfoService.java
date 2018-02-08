package main.service;


import java.util.List;

import main.pojo.ProjectInfo;

import org.hibernate.Session;

public interface ProjectInfoService {

	public void saveProjectInfo(ProjectInfo projectInfo);

	public  List<ProjectInfo> getProjectInfoById();

	public  ProjectInfo getProjectInfo_ById(Long id);

	public List<ProjectInfo> getProjectInfoByName(String projectName);//����projectName ģ����ѯ��ȡ��Ŀ�б�	

  public  List<ProjectInfo> getProjectInfosByNameAndBusinessType(String name,Long businessType) ;
	
	public  List<ProjectInfo> getAllProjectInfo(Long id);
	
	public ProjectInfo getById(Long id);

	public Object findLastProjectId();//��ȡ��ǰ���ݱ������id

	public Session getSuperSession();

}
