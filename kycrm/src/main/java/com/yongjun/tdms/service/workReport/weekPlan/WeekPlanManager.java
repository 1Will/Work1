package com.yongjun.tdms.service.workReport.weekPlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;

public interface WeekPlanManager {
	public abstract void storeWeekPlan(WeekPlan paramWeekPlan);

	public abstract void deleteWeekPlan(WeekPlan paramWeekPlan);

	public abstract void deleteAllWeekPlan(Collection<WeekPlan> paramCollection);

	public abstract List<WeekPlan> loadAllWeekPlan(Long[] paramArrayOfLong);

	public abstract WeekPlan loadWeekPlan(Long paramLong);

	public abstract List<WeekPlan> loadAllWeekPlan();

	public abstract List<WeekPlan> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<WeekPlan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}
