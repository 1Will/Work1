package com.yongjun.tdms.service.workflow.point;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workflow.Point;
import java.util.Collection;
import java.util.List;

public abstract interface PointManager
{
  public abstract void storePoint(Point paramPoint);

  public abstract void deletePoint(Point paramPoint);

  public abstract void deleteAllPoints(Collection<Point> paramCollection);

  public abstract List<Point> loadAllPoints(Long[] paramArrayOfLong);

  public abstract Point loadPoint(Long paramLong);

  public abstract List<Point> loadAllPoints();

  public abstract List<Point> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Point> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<Point> paramList);

  public abstract String enabled(List<Point> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.PointManager
 * JD-Core Version:    0.6.2
 */