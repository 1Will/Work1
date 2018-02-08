package main.service;

import java.util.List;

import main.pojo.WeekPlan;

import org.hibernate.Session;

public interface WeekPlanService {
    
	public void saveWeekPlan(WeekPlan weekPlan);//�����ܼƻ�
	
	public void updateWeekPlan(WeekPlan weekPlan);//�����ܼƻ�
	
	public List<WeekPlan> getWeekPlanByWeekId(Long weekId,Long userid);
    
    public WeekPlan getWeekPlanById(Long id);//����id��ȡ�ܼƻ�
    
    public List<WeekPlan> getWeekPlanByWeeklyId(Long weeklyId);//����weeklyId��ȡ�ܼƻ�
    
	public Session getSuperSession();
	
	    
		
		
}
