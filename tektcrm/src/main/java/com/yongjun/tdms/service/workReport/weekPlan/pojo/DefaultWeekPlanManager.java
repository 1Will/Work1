package com.yongjun.tdms.service.workReport.weekPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.weekPlan.WeekPlanDao;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;
import com.yongjun.tdms.service.workReport.weekPlan.WeekPlanManager;

public class DefaultWeekPlanManager implements WeekPlanManager {
	private final WeekPlanDao weekPlanDao;

	public DefaultWeekPlanManager(WeekPlanDao weekPlanDao) {
		this.weekPlanDao = weekPlanDao;
	}

	public void storeWeekPlan(WeekPlan paramWeekPlan) {
		weekPlanDao.storeWeekPlan(paramWeekPlan);
	}

	public void deleteWeekPlan(WeekPlan paramWeekPlan) {
		weekPlanDao.deleteWeekPlan(paramWeekPlan);
	}

	public void deleteAllWeekPlan(Collection<WeekPlan> paramCollection) {
		weekPlanDao.deleteAllWeekPlan(paramCollection);
	}

	public List<WeekPlan> loadAllWeekPlan(Long[] paramArrayOfLong) {
		return weekPlanDao.loadAllWeekPlan(paramArrayOfLong);
	}

	public WeekPlan loadWeekPlan(Long paramLong) {
		return weekPlanDao.loadWeekPlan(paramLong);
	}

	public List<WeekPlan> loadAllWeekPlan() {
		return weekPlanDao.loadAllWeekPlan();
	}

	public List<WeekPlan> loadByKey(String paramString, Object paramObject) throws DaoException {
		return weekPlanDao.loadByKey(paramString, paramObject);
	}

	public List<WeekPlan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject) throws DaoException {
		return weekPlanDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
