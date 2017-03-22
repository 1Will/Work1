package com.yongjun.tdms.service.workReport.daily;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.daily.Daily;
import java.util.Collection;
import java.util.List;

public abstract interface DailyManager
{
  public abstract void storeDaily(Daily paramDaily);

  public abstract void deleteDaily(Daily paramDaily);

  public abstract void deleteAllDaily(Collection<Daily> paramCollection);

  public abstract List<Daily> loadAllDaily(Long[] paramArrayOfLong);

  public abstract Daily loadDaily(Long paramLong);

  public abstract List<Daily> loadAllDaily();

  public abstract List<Daily> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Daily> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workReport.daily.DailyManager
 * JD-Core Version:    0.6.2
 */