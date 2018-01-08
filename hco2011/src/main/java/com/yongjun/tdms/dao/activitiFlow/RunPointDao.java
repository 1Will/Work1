package com.yongjun.tdms.dao.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.workflow.Point;

import java.util.Collection;
import java.util.List;

public abstract interface RunPointDao
{
  public abstract void storeRunPoint(RunPoint paramPoint);

  public abstract void deletePoint(RunPoint paramPoint);

  public abstract void deleteAllPoints(Collection<RunPoint> paramCollection);

  public abstract List<RunPoint> loadAllPoints(Long[] paramArrayOfLong);

  public abstract RunPoint loadRunPoint(Long paramLong);

  public abstract List<RunPoint> loadAllPoints();
  public abstract List<RunPoint> loadAllByHql(String hql);

  public abstract List<RunPoint> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<RunPoint> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.PointDao
 * JD-Core Version:    0.6.2
 */