package main.dao;

import java.util.List;

import main.pojo.ProjectInfoPersonnels;

import org.hibernate.Session;


public interface ProjectInfoPersonnelsDao {

	public void saveProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnels);
	
	//������Ŀid�����Ŀ���Ա����
	public  List<ProjectInfoPersonnels> getProjectInfoPersonnelsById(long id);
	
	public Session getSuperSession();

}
