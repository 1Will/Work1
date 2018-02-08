package main.service.impl;

import java.util.Date;
import java.util.List;

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
	public void updateWeekly(Weekly weekly) {
        weeklyDao.updateWeekly(weekly);		
	}
	
	@Override
	public Weekly getWeeklyById(Long id) {
		return weeklyDao.getWeeklyById(id);
	}
	
	@Override
	public List<Weekly> getWeeklyByweeklyName(String weeklyName,String loginName) {
		return weeklyDao.getWeeklyByweeklyName(weeklyName,loginName);
	}
	
	@Override
	public List<Weekly> getWeeklyByweekId(Long weekId, String loginName) {
		return weeklyDao.getWeeklyByweekId(weekId, loginName);
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
