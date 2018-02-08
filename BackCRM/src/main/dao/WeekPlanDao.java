package main.dao;


import java.util.List;

import org.hibernate.Session;

import main.pojo.WeekPlan;

public interface WeekPlanDao {
	
	public void saveWeekPlan(WeekPlan weekPlan);//保存周计划

	public void updateWeekPlan(WeekPlan weekPlan);//更新周计划
	
	public List<WeekPlan> getWeekPlanByWeekId(Long weekId,Long userid); //根据周id 和用户id weeklyId为null 获取集合 
    
    public WeekPlan getWeekPlanById(Long id);//根据id获取周计划

    public List<WeekPlan> getWeekPlanByWeeklyId(Long weeklyId);//根据weeklyId获取周计划
    
  //  public String getMaxWeeklyCode(String code,Long ratId);//根据记录者和code前缀获取当前最大code
	
	public Session getSuperSession();
	
	
}
