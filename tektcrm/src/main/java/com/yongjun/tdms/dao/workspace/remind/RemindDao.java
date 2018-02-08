package com.yongjun.tdms.dao.workspace.remind;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workspace.warnning.Remind;
import java.util.Collection;
import java.util.List;

public abstract interface RemindDao
{
  public abstract Remind loadRemind(Long paramLong);

  public abstract List<Remind> loadReminds(Long[] paramArrayOfLong);

  public abstract void storeRemind(Remind paramRemind);

  public abstract void deleteRemind(Remind paramRemind);

  public abstract void deleteAllReminds(Collection<Remind> paramCollection);

  public abstract List<Remind> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Remind> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.remind.RemindDao
 * JD-Core Version:    0.6.2
 */