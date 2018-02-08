package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.WeekDao;
import main.pojo.Week;
import main.service.WeekService;

public class WeekServiceImpl implements WeekService{
    private WeekDao weekDao ;

    @Override
    public Week getWeekById(Long weekId) {
    	return weekDao.getWeekById(weekId);
    }
    
	@Override
	public List<Week> getWeekByMonth(String month) {
		return weekDao.getWeekByMonth(month);
	}

	@Override
	public List<Week> getWeekByMonthAndYear(String month, int year) {
		return weekDao.getWeekByMonthAndYear(month, year);
	}

	@Override
	public Session getSuperSession() {
		return weekDao.getSuperSession();
	}

	public WeekDao getWeekDao() {
		return weekDao;
	}

	public void setWeekDao(WeekDao weekDao) {
		this.weekDao = weekDao;
	}


	

	
}
