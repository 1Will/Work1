package main.dao;


import java.util.List;

import main.pojo.ProjectInfo;

import org.hibernate.Session;


public interface ProjectInfoDao {

	public void saveProjectInfo(ProjectInfo projectInfo);
	
	public  List<ProjectInfo> getProjectInfoById(); 
	
	public  ProjectInfo getProjectInfo_ById(Long id); //��ʾҳ����÷���
	
	public List<ProjectInfo> getProjectInfoByName(String projectName);//����projectName ģ����ѯ��ȡ��Ŀ�б�

	public  List<ProjectInfo> getAllProjectInfo(Long id)  ; //���ݿͻ�id��ȡ��Ŀ����

	public ProjectInfo getById(Long id);

    public Object findLastProjectId();//��ȡ��ǰ���ݱ������id
    
    public  List<ProjectInfo> getProjectInfosByNameAndBusinessType(String name,Long businessType) ;
	
    public Session getSuperSession();

	void updateProjectInfo(ProjectInfo projectInfo);

}
