package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.WeekPlanDao;
import main.pojo.WeekPlan;
import main.service.WeekPlanService;

public class WeekPlanServiceImpl implements WeekPlanService {

	private WeekPlanDao weekPlanDao;
	@Override
	public void saveWeekPlan(WeekPlan weekPlan) {
		weekPlanDao.saveWeekPlan(weekPlan);		
	}
    
	@Override
	public void updateWeekPlan(WeekPlan weekPlan) {
        weekPlanDao.updateWeekPlan(weekPlan);		
	}

	
	@Override
	public WeekPlan getWeekPlanById(Long id) {
		return weekPlanDao.getWeekPlanById(id);
	}
	
	@Override
	public List<WeekPlan> getWeekPlanByWeeklyId(Long weeklyId) {
		return weekPlanDao.getWeekPlanByWeeklyId(weeklyId);
	}

	@Override
	public List<WeekPlan> getWeekPlanByWeekId(Long weekId, Long userid) {
		return weekPlanDao.getWeekPlanByWeekId(weekId, userid);
	} 
	
	@Override
	public Session getSuperSession() {
		return weekPlanDao.getSuperSession();
	}


	public WeekPlanDao getWeekPlanDao() {
		return weekPlanDao;
	}


	public void setWeekPlanDao(WeekPlanDao weekPlanDao) {
		this.weekPlanDao = weekPlanDao;
	}





	
	

  
	
	 
}
