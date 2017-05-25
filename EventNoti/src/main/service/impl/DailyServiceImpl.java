package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.DailyDao;
import main.pojo.Daily;
import main.service.DailyService;

public class DailyServiceImpl implements DailyService {

	private DailyDao dailyDao;
	@Override
	public void saveDaily(Daily daily) {
          dailyDao.saveDaily(daily);		
	}

	@Override
	public List<Daily> getAllDaily() {
		return dailyDao.getAllDaily();
	}
	
	@Override
	public Daily getDailyById(Long id) {
		return dailyDao.getDailyById(id);
	}
	
	@Override
	public Long getLastId() {
		return dailyDao.getLastId();
	}
	
	@Override
	public void updateDaily(String leaderIdea, Long id) {
		dailyDao.updateDaily(leaderIdea, id);
	}
	
	@Override
	public List<Daily> getDailyByDate(String date, String name) {
		return dailyDao.getDailyByDate(date, name);
	}
	
	@Override
	public Session getSuperSession() {
		return dailyDao.getSuperSession();
	}

	
	public DailyDao getDailyDao() {
		return dailyDao;
	}

	public void setDailyDao(DailyDao dailyDao) {
		this.dailyDao = dailyDao;
	}




	
	 
}
