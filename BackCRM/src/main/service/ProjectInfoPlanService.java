package main.service;


import java.util.List;

import org.hibernate.Session;

import main.pojo.ProjectInfoPlan;

public interface ProjectInfoPlanService {
	
	   public ProjectInfoPlan getProjectInfoPlanById(Long id); //根据id 获取工作计划集合
	   
	   public List<ProjectInfoPlan> getProjectInfoPlanByPerId(Long personnelId); // 根据负责人id 获取计划集合
	   
	    public void updateProjectInfoPlan(ProjectInfoPlan projectInfoPlan); // 更新工作计划
	    
	    public Session getSuperSession();
	    
	    
	    
}
