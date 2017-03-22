package com.yongjun.tdms.dao.COM.remit;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.COM.remit.Remit;
import java.util.Collection;
import java.util.List;

public abstract interface RemitDao
{
  public abstract void storeRemit(Remit paramRemit);

  public abstract void deleteRemit(Remit paramRemit);

  public abstract void deleteAllRemit(Collection<Remit> paramCollection);

  public abstract List<Remit> loadAllRemit(Long[] paramArrayOfLong);

  public abstract Remit loadRemit(Long paramLong);

  public abstract List<Remit> loadAllRemit();

  public abstract List<Remit> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Remit> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.remit.RemitDao
 * JD-Core Version:    0.6.2
 */