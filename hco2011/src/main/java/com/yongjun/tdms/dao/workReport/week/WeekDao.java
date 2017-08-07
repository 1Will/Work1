package com.yongjun.tdms.dao.workReport.week;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.week.Week;

public abstract interface WeekDao
{

  public abstract List<Week> loadAllWeek(Long[] paramArrayOfLong);

  public abstract Week loadWeek(Long paramLong);

  public abstract List<Week> loadAllWeek();

  public abstract List<Week> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Week> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

}

