package com.yongjun.tdms.dao.COM.co;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.COM.co.Co;
import java.util.Collection;
import java.util.List;

public abstract interface CoDao
{
  public abstract void storeCo(Co paramCo);

  public abstract void deleteCo(Co paramCo);

  public abstract void deleteAllCo(Collection<Co> paramCollection);

  public abstract List<Co> loadAllCo(Long[] paramArrayOfLong);

  public abstract Co loadCo(Long paramLong);

  public abstract List<Co> loadAllCo();

  public abstract List<Co> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Co> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.co.CoDao
 * JD-Core Version:    0.6.2
 */