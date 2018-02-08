package com.yongjun.tdms.dao.workReport.weekPlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.weekPlan.WeekPlanDao;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;

public class HibernateWeekPlan extends BaseHibernateDao implements WeekPlanDao {

	public void storeWeekPlan(WeekPlan weekPlan) {
		super.store(weekPlan);
	}

	public void deleteWeekPlan(WeekPlan weekPlan) {
		super.delete(weekPlan);
	}

	public void deleteAllWeekPlan(Collection<WeekPlan> weekPlans) {
		super.deleteAll(weekPlans);
	}

	public List<WeekPlan> loadAllWeekPlan(Long[] weekPlanIds) {
		return super.loadAll(WeekPlan.class, weekPlanIds);
	}

	public WeekPlan loadWeekPlan(Long weekPlanId) {
		return (WeekPlan) super.load(WeekPlan.class, weekPlanId);
	}

	public List<WeekPlan> loadAllWeekPlan() {
		return super.loadAll(WeekPlan.class);
	}

	public List<WeekPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(WeekPlan.class, keyName, keyValue);
	}

	public List<WeekPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(WeekPlan.class, keyNames, keyValues);
	}

}
