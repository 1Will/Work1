package main.dao;

import java.util.List;

import main.pojo.ProjectInfoPersonnels;

import org.hibernate.Session;


public interface ProjectInfoPersonnelsDao {

	public void saveProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnels);
	
	//根据项目id获得项目组成员集合
	public  List<ProjectInfoPersonnels> getProjectInfoPersonnelsById(long id);
	
	public Session getSuperSession();

}
