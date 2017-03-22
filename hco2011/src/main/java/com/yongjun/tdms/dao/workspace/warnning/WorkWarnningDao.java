package com.yongjun.tdms.dao.workspace.warnning;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import java.util.Collection;
import java.util.List;

public abstract interface WorkWarnningDao
{
  public abstract WorkWarnning loadWorkWarnning(Long paramLong);

  public abstract List<WorkWarnning> loadAllWorkWarnnings(Long[] paramArrayOfLong);

  public abstract List<WorkWarnning> loadAllWorkWarnnings();

  public abstract void storeWorkWarnning(WorkWarnning paramWorkWarnning);

  public abstract void deleteWorkWarnning(WorkWarnning paramWorkWarnning);

  public abstract void deleteAllWorkWarnnings(Collection<WorkWarnning> paramCollection);

  public abstract Integer GetNumberOfUnReadWarnningByUserID(Long paramLong);

  public abstract List<WorkWarnning> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List<WorkWarnning> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao
 * JD-Core Version:    0.6.2
 */