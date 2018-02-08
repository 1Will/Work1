package com.yongjun.tdms.service.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.RunTask;

import java.util.Collection;
import java.util.List;

public abstract interface RunTaskManager
{
  public abstract void storeRunTask(RunTask paramTask);

  public abstract void deleteRunTask(RunTask paramTask);

  public abstract void deleteAllRunTasks(Collection<RunTask> paramCollection);

  public abstract List<RunTask> loadAllRunTasks(Long[] paramArrayOfLong);

  public abstract RunTask loadRunTask(Long paramLong);

  public abstract List<RunTask> loadAllTasks();

  public abstract List<RunTask> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<RunTask> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<RunTask> paramList);

  public abstract String enabled(List<RunTask> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.PointManager
 * JD-Core Version:    0.6.2
 */