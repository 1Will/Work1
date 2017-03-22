package com.yongjun.tdms.dao.COM.rejection;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.COM.rejection.Rejection;
import java.util.Collection;
import java.util.List;

public abstract interface RejectionDao
{
  public abstract void storeRejection(Rejection paramRejection);

  public abstract void deleteRejection(Rejection paramRejection);

  public abstract void deleteAllRejection(Collection<Rejection> paramCollection);

  public abstract List<Rejection> loadAllRejection(Long[] paramArrayOfLong);

  public abstract Rejection loadRejection(Long paramLong);

  public abstract List<Rejection> loadAllRejection();

  public abstract List<Rejection> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Rejection> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.rejection.RejectionDao
 * JD-Core Version:    0.6.2
 */