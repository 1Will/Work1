package com.yongjun.tdms.dao.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.statistic.DailyStatistic;

public abstract interface DailyStatisticDao
{

  public abstract List<DailyStatistic> loadAllDailyStatistic(Long[] paramArrayOfLong);

  public abstract DailyStatistic loadDailyStatistic(Long paramLong);

  public abstract List<DailyStatistic> loadAllDailyStatistic();
  public abstract List<Object[]> loadAllDailyStatisticByContion();

  public abstract List<DailyStatistic> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<DailyStatistic> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  public abstract void storeDailyStatistic(DailyStatistic dailyStatistic);

  public abstract List<Object[]> loadAllDailyStatisticDayByContion();
 

  

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.leaveBill.LeaveBillDao
 * JD-Core Version:    0.6.2
 */