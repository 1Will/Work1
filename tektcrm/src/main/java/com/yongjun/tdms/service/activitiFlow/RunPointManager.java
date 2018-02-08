package com.yongjun.tdms.service.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.workflow.Point;

import java.util.Collection;
import java.util.List;

public abstract interface RunPointManager
{
  public abstract void storeRunPoint(RunPoint paramPoint);
  public abstract void saveOrderNum(String id1,String id2);

  public abstract void deleteRunPoint(RunPoint paramPoint);

  public abstract void deleteAllRunPoints(Collection<RunPoint> paramCollection);

  public abstract List<RunPoint> loadAllRunPoints(Long[] paramArrayOfLong);

  public abstract RunPoint loadRunPoint(Long paramLong);

  public abstract List<RunPoint> loadAllPoints();

  public abstract List<RunPoint> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<RunPoint> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<RunPoint> paramList);

  public abstract String enabled(List<RunPoint> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.PointManager
 * JD-Core Version:    0.6.2
 */