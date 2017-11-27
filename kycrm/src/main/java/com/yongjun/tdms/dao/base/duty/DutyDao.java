package com.yongjun.tdms.dao.base.duty;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.duty.Duty;
import java.util.Collection;
import java.util.List;

public abstract interface DutyDao
{
  public abstract void storeDuty(Duty paramDuty);

  public abstract void deleteDuty(Duty paramDuty);

  public abstract void deleteAllDuty(Collection<Duty> paramCollection);

  public abstract List<Duty> loadAllDuty(Long[] paramArrayOfLong);

  public abstract Duty loadDuty(Long paramLong);

  public abstract List<Duty> loadAllDuty();

  public abstract List<Duty> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Duty> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  public abstract List<Duty> loadByDeptId(String deptid)
		    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.duty.DutyDao
 * JD-Core Version:    0.6.2
 */