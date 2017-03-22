package com.yongjun.tdms.service.historytask;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.historytask.HistoryTask;
import java.util.Collection;
import java.util.List;

public abstract interface HistoryTaskManager
{
  public abstract void storeHistoryTask(HistoryTask paramHistoryTask);

  public abstract void deleteHistoryTask(HistoryTask paramHistoryTask);

  public abstract void deleteAllHistoryTask(Collection<HistoryTask> paramCollection);

  public abstract List<HistoryTask> loadAllHistoryTask(Long[] paramArrayOfLong);

  public abstract HistoryTask loadHistoryTask(Long paramLong);

  public abstract List<HistoryTask> loadAllHistoryTask();

  public abstract List<HistoryTask> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<HistoryTask> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllHistoryTask(Collection<HistoryTask> paramCollection);

  public abstract void enabledAllHistoryTask(Collection<HistoryTask> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.historytask.HistoryTaskManager
 * JD-Core Version:    0.6.2
 */