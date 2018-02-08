package com.yongjun.tdms.dao.workReport.weekPlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;

public abstract interface WeekPlanDao {

	void storeWeekPlan(WeekPlan weekPlan);

	void deleteWeekPlan(WeekPlan weekPlan);

	void deleteAllWeekPlan(Collection<WeekPlan> weekPlans);

	List<WeekPlan> loadAllWeekPlan(Long[] weekPlanIds);

	WeekPlan loadWeekPlan(Long weekPlanId);

	List<WeekPlan> loadAllWeekPlan();

	List<WeekPlan> loadByKey(String keyName, Object keyValue) throws DaoException;

	List<WeekPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;

}
