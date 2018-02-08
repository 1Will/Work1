package com.yongjun.tdms.service.workReport.newTask;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.newTask.NewTask;

public interface NewTaskManager {
	  public abstract void storeNewTask(NewTask paramNewTask);

	  public abstract void deleteNewTask(NewTask paramNewTask);

	  public abstract void deleteAllNewTask(Collection<NewTask> paramCollection);

	  public abstract List<NewTask> loadAllNewTask(Long[] paramArrayOfLong);

	  public abstract NewTask loadNewTask(Long paramLong);

	  public abstract List<NewTask> loadAllNewTask();

	  public abstract List<NewTask> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<NewTask> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
