package com.yongjun.tdms.dao.customerservicemanagement.repairs;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
import java.util.List;

public abstract interface RepairsDao
{
  public abstract void storeRepairs(Repairs paramRepairs);

  public abstract Repairs loadRepairs(Long paramLong);

  public abstract List<Repairs> loadRepairs();

  public abstract List<Repairs> loadAllRepairs(Long[] paramArrayOfLong);

  public abstract void deleteRepairs(Repairs paramRepairs);

  public abstract void deleteAllRepairs(List<Repairs> paramList);

  public abstract List<Repairs> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Repairs> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customerservicemanagement.repairs.RepairsDao
 * JD-Core Version:    0.6.2
 */