package main.service;

import java.util.List;

import main.pojo.WeekPlan;

import org.hibernate.Session;

public interface WeekPlanService {
    
	public void saveWeekPlan(WeekPlan weekPlan);//保存周计划
	
	public void updateWeekPlan(WeekPlan weekPlan);//更新周计划
	
	public List<WeekPlan> getWeekPlanByWeekId(Long weekId,Long userid);
    
    public WeekPlan getWeekPlanById(Long id);//根据id获取周计划
    
    public List<WeekPlan> getWeekPlanByWeeklyId(Long weeklyId);//根据weeklyId获取周计划
    
	public Session getSuperSession();
	
	    
		
		
}
