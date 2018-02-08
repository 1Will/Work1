package com.yongjun.tdms.service.task;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.task.Task;
import java.util.Collection;
import java.util.List;

public abstract interface TaskManager
{
  public abstract void storeTask(Task paramTask);

  public abstract void deleteTask(Task paramTask);

  public abstract void deleteAllTasks(Collection<Task> paramCollection);

  public abstract List<Task> loadAllTasks(Long[] paramArrayOfLong);

  public abstract Task loadTask(Long paramLong);

  public abstract List<Task> loadAllTasks();

  public abstract List<Task> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Task> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<Task> paramList);

  public abstract String enabled(List<Task> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.task.TaskManager
 * JD-Core Version:    0.6.2
 */