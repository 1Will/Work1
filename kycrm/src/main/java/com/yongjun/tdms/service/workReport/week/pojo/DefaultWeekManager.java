package com.yongjun.tdms.service.workReport.week.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.week.WeekDao;
import com.yongjun.tdms.model.workReport.week.Week;
import com.yongjun.tdms.service.workReport.week.WeekManager;

public class DefaultWeekManager extends BaseManager implements WeekManager {
	private final WeekDao weekDao;

	public DefaultWeekManager(WeekDao weekDao) {
		this.weekDao = weekDao;
	}

	public List<Week> loadAllWeek(Long[] weekIds) {
		return this.weekDao.loadAllWeek(weekIds);
	}

	public Week loadWeek(Long weekId) {
		return this.weekDao.loadWeek(weekId);
	}

	public List<Week> loadAllWeek() {
		return this.weekDao.loadAllWeek();
	}

	public List<Week> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.weekDao.loadByKey(keyName, keyValue);
	}

	public List<Week> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.weekDao.loadByKeyArray(keyNames, keyValues);
	}
	
	public List<String> getDate(Long weekId){
		List<String> dateList =new ArrayList<String>();
		Week week = loadWeek(weekId);
		dateList.add(week.getStartDate()+"");
		dateList.add(week.getEndDate()+"");
		return dateList;
	}

}
