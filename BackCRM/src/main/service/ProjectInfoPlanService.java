package main.service;


import java.util.List;

import org.hibernate.Session;

import main.pojo.ProjectInfoPlan;

public interface ProjectInfoPlanService {
	
	   public ProjectInfoPlan getProjectInfoPlanById(Long id); //����id ��ȡ�����ƻ�����
	   
	   public List<ProjectInfoPlan> getProjectInfoPlanByPerId(Long personnelId); // ���ݸ�����id ��ȡ�ƻ�����
	   
	    public void updateProjectInfoPlan(ProjectInfoPlan projectInfoPlan); // ���¹����ƻ�
	    
	    public Session getSuperSession();
	    
	    
	    
}
