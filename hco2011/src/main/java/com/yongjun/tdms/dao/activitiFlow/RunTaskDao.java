package com.yongjun.tdms.dao.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
import com.yongjun.tdms.model.workflow.Point;

import java.util.Collection;
import java.util.List;

public abstract interface RunTaskDao
{
  public abstract void storeRunTask(RunTask paramPoint);

  public abstract void deleteRunTask(RunTask paramPoint);

  public abstract void deleteAllRunTask(Collection<RunTask> paramCollection);

  public abstract List<RunTask> loadAllRunTask(Long[] paramArrayOfLong);

  public abstract RunTask loadRunTask(Long paramLong);

  public abstract List<RunTask> loadAllRunTask();

  public abstract List<RunTask> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  public void storeBussnessState(StartActiviti startActiviti);
  public abstract List<String> loadCodySendPerson(StartActiviti startActiviti);

  public abstract List<RunTask> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.PointDao
 * JD-Core Version:    0.6.2
 */