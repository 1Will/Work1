package com.yongjun.tdms.service.dataStatistics;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.statistic.DailyStatistic;

public interface DataStatisticsManager {
	  public abstract List<DailyStatistic> loadAllDailyStatistic(Long[] paramArrayOfLong);

	  public abstract DailyStatistic loadDailyStatistic(Long paramLong);
	  public abstract List<DailyStatistic> loadAllDailyStatistic();
	  public abstract String loadAllDailyStatisticByContion(String flag);
	  
	  public abstract List<DailyStatistic> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<DailyStatistic> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
	  public abstract void storeDailyStatistic(DailyStatistic dailyStatistic);
	  public List<Department> getAllClassifications(String btype);
	  public String loadAllMyDataByContion(String timeStart,String timeEnd,String businessType ,String classification,String flag);
	 /* public String loadAllMyDataByBusinessType(String timeStart,String timeEnd,String flag);*/
	  public abstract List<List<List<Object>>>  listStatistilcalBusinessTypes(String date,String name, String businessType, String classification);
	  public abstract List<List<List<Object>>> listYearStatistilcalBusinessTypes(String dates, String name, String businessType, String classification);

	public abstract List<List<List<Object>>> listSumStatistilcalBusinessTypes(String startTime, String endTime,
			String name, String businessType, String classification);

	
}
