package com.yongjun.tdms.dao.workspace.warnning;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnningDetail;
import java.util.Collection;
import java.util.List;

public abstract interface WorkWarnningDetailDao
{
  public abstract WorkWarnningDetail loadWorkWarnningDetail(Long paramLong);

  public abstract List<WorkWarnningDetail> loadAllWorkWarnningDetails(Long[] paramArrayOfLong);

  public abstract List<WorkWarnningDetail> loadAllWorkWarnningDetails();

  public abstract void storeWorkWarnningDetail(WorkWarnningDetail paramWorkWarnningDetail);

  public abstract void deleteWorkWarnningDetail(WorkWarnningDetail paramWorkWarnningDetail);

  public abstract void deleteAllWorkWarnningDetails(Collection<WorkWarnningDetail> paramCollection);

  public abstract List<WorkWarnningDetail> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List<WorkWarnningDetail> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDetailDao
 * JD-Core Version:    0.6.2
 */