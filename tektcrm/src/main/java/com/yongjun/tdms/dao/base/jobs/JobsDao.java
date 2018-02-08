package com.yongjun.tdms.dao.base.jobs;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.jobs.Jobs;
import java.util.Collection;
import java.util.List;

public abstract interface JobsDao
{
  public abstract void storeJobs(Jobs paramJobs);

  public abstract void deleteJobs(Jobs paramJobs);

  public abstract void deleteAllJobs(Collection<Jobs> paramCollection);

  public abstract List<Jobs> loadAllJobs(Long[] paramArrayOfLong);

  public abstract Jobs loadJobs(Long paramLong);

  public abstract List<Jobs> loadAllJobs();

  public abstract List<Jobs> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Jobs> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.jobs.JobsDao
 * JD-Core Version:    0.6.2
 */