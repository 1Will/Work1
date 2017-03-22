package com.yongjun.tdms.service.customerservicemanagement.repairs;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
import java.util.List;

public abstract interface RepairsManager
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

  public abstract void disabledAllRepairs(List<Repairs> paramList);

  public abstract void enabledAllRepairs(List<Repairs> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.repairs.RepairsManager
 * JD-Core Version:    0.6.2
 */