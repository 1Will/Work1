package com.yongjun.tdms.dao.workReport.weekly;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import java.util.Collection;
import java.util.List;

public abstract interface WeeklyDao
{
  public abstract void storeWeekly(Weekly paramWeekly);

  public abstract void deleteWeekly(Weekly paramWeekly);

  public abstract void deleteAllWeekly(Collection<Weekly> paramCollection);

  public abstract List<Weekly> loadAllWeekly(Long[] paramArrayOfLong);

  public abstract Weekly loadWeekly(Long paramLong);

  public abstract List<Weekly> loadAllWeekly();

  public abstract List<Weekly> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Weekly> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxWeeklyCode(String paramString, Long paramLong1, Long paramLong2);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workReport.weekly.WeeklyDao
 * JD-Core Version:    0.6.2
 */