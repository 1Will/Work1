package com.yongjun.tdms.service.base.jobs;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.jobs.Jobs;
import java.util.List;

public abstract interface JobsManager
{
  public abstract void storeJob(Jobs paramJobs);

  public abstract Jobs loadJobs(Long paramLong);

  public abstract List<Jobs> loadAllJobs();

  public abstract void disabledAllJobs(List<Jobs> paramList);

  public abstract void enabledAllJobs(List<Jobs> paramList);

  public abstract void delete(Jobs paramJobs);

  public abstract void deleteAllJobs(List<Jobs> paramList);

  public abstract List<Jobs> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Jobs> loadAllJobs(Long[] paramArrayOfLong);

  public abstract List<Jobs> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.jobs.JobsManager
 * JD-Core Version:    0.6.2
 */