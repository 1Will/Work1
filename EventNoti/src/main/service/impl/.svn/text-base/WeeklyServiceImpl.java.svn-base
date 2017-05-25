package main.service.impl;

import org.hibernate.Session;

import main.dao.WeeklyDao;
import main.pojo.Weekly;
import main.service.WeeklyService;

public class WeeklyServiceImpl implements WeeklyService {

	private WeeklyDao weeklyDao;
	@Override
	public void saveWeekly(Weekly weekly) {
		weeklyDao.saveWeekly(weekly);		
	}

	
	@Override
	public Weekly getWeeklyById(Long id) {
		return weeklyDao.getWeeklyById(id);
	}
	
	@Override
	public String getMaxWeeklyCode(String code, Long ratId) {
		return weeklyDao.getMaxWeeklyCode(code, ratId);
	}

	@Override
	public Session getSuperSession() {
		return weeklyDao.getSuperSession();
	}

	
	
	public WeeklyDao getWeeklyDao() {
		return weeklyDao;
	}

	public void setWeeklyDao(WeeklyDao weeklyDao) {
		this.weeklyDao = weeklyDao;
	}






  
	
	 
}
